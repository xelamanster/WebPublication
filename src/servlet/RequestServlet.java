package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import dbconnection.DAO;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.Session;

import servAction.Action;
import servAction.ActionFactory;
import user.*;

import com.mysql.jdbc.jdbc2.optional.SuspendableXAConnection;

/**
 * Implementation of class Servlet
 */
@WebServlet(name = "hw", urlPatterns = "*.serv")
public class RequestServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;
  protected ActionFactory factory = new ActionFactory();

  public RequestServlet() {
	super();
  }

  /**
   * Separates the name of the action on the request.<br>
   * For example:<br>
   * request.getServletPath() => "Action.serv";<br>
   * return => "Action".
   * 
   * @param request
   * @return name of the action
   */
  protected String getActionName(HttpServletRequest request) {
	String path = request.getServletPath();
	return path.substring(1, path.lastIndexOf("."));
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
	  throws ServletException, IOException {
	Action act = factory.create(getActionName(request));
	String url = act.action(request, response);
	if (url != null)
	  getServletContext().getRequestDispatcher(url).forward(request, response);
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
	  throws ServletException, IOException {
	doGet(request, response);
  }
}
