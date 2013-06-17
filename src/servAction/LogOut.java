package servAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogOut implements Action {

  @Override
  public String action(HttpServletRequest request, HttpServletResponse response) {
	request.getSession().invalidate();
	return "/Index.jsp";
  }
}
