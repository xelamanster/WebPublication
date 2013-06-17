package servAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.DefaultUser;

public class AddSub implements Action {

  @Override
  public String action(HttpServletRequest request, HttpServletResponse response) {
	DefaultUser user = (DefaultUser) request.getSession().getAttribute("user");
	String title = request.getParameter("pub_title");
	user.addSubscrib(title);
	request.setAttribute("sub_add", title);
	return "/Index.jsp";
  }
}
