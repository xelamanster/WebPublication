package servAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import publication.*;

public class DeletePub implements Action {

  @Override
  public String action(HttpServletRequest request, HttpServletResponse response) {
	String title = (String) request.getParameter("title");
	Publication pub = new Publication(title);
	pub.deleteFromDb();
	return "/admin.jsp";
  }
}
