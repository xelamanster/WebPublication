package servAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.DefaultUser;
import user.User;

public class AddUser implements Action {

  @Override
  public String action(HttpServletRequest request, HttpServletResponse response) {
	String name = (String) request.getParameter("username");
	String password = (String) request.getParameter("password");
	String email = (String) request.getParameter("email");
	User user = new DefaultUser(name, password, email);
	if (!user.existInDb()) {
	  user.addToDB();
	  request.setAttribute("adduser", "true");
	  request.getSession().setAttribute("user", user);
	  request.getSession().setAttribute("exist", "true");
	}
	return "/Index.jsp";
  }
}
