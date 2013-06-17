package servAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.Admin;

public class PaySub implements Action {

  @Override
  public String action(HttpServletRequest request, HttpServletResponse response) {
	String title = request.getParameter("title");
	String username = request.getParameter("username");
	Admin admin = (Admin) request.getSession().getAttribute("user");
	admin.pay(title, username);
	return "/admin.jsp";
  }
}
