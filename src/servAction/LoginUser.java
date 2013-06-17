package servAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import user.Admin;
import user.DefaultUser;
import user.User;

public class LoginUser implements Action {
  private static Logger log = Logger.getLogger(LoginUser.class.getName());

  @Override
  public String action(HttpServletRequest request, HttpServletResponse response) {
	String name = (String) request.getParameter("username");
	String password = (String) request.getParameter("password");
	User user = new DefaultUser(name, password);

	if (user.existInDb()) {
	  log.error("userExist");
	  if (user.isAdmin()) {
		Admin admin = new Admin(name, password);
		request.getSession().setAttribute("user", admin);
		request.getSession().setAttribute("exist", "admin");
		return "/admin.jsp";
	  }
	  request.getSession().setAttribute("user", user);
	  request.getSession().setAttribute("exist", "true");
	  return "/Index.jsp";
	} else {
	  request.setAttribute("exist", "notexist");
	  return "/SimpLogin.jsp";
	}
  }
}
