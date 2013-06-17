package servAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.DefaultUser;

public class DeleteSub implements Action {

  @Override
  public String action(HttpServletRequest request, HttpServletResponse response) {
	DefaultUser user = (DefaultUser) request.getSession().getAttribute("user");
	// System.out.println((String) request.getParameter("pub_title"));
	user.deleteSub((String) request.getParameter("pub_title"));
	return "/Mypub.jsp";
  }
}
