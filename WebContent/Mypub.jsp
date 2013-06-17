<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page
	import="user.*,java.util.Iterator, java.util.LinkedList, publication.Publication;"%>
<html>
<head>
<link rel="stylesheet" href="style2.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	  DefaultUser user = (DefaultUser) request.getSession()
			  .getAttribute("user");
	%>
	<div id="ulfield">
		<ul>
			<li><a href="Index.jsp">Главная</a></li>
			<li><a href="#">Мои публикации</a></li>
			<li><a href="logout.serv">выход</a></li>
			<li><a href="about.jsp">о сайте</a></li>
			<p style="float: right; color: #fff; display: block; 
			          font-size: 11px; font-weight: bold;">
				Добро пожаловать,<%=user.getName()%>.
			</p>
		</ul>
	</div>
	<div id="fieldset">
		<%
		  LinkedList list = user.subscribes();
		  Iterator it = list.iterator();
		  while (it.hasNext()) {
		%>
		<div class="pub">
			<%
			  Publication pub = (Publication) it.next();
			  out.println("<img alt=\"\" src=\"" + pub.getUrl() + "\">");
			  out.println(" <p><h3>" + pub.getTitle() + "</h3>" + pub.getPrice() + " usd");
			  if (request.getSession().getAttribute("exist") == "true"){
			   out.println("<a class=\"button button_type__remove\" href=\"deletesub.serv?pub_title="
						   + pub.getTitle()
						   + "\">Delete<span class=\"button_icon\"></span></a></p>" + "<br>");
			  } else {
			    out.println("<br>");
			  }
			%>
		</div>
		<%
		  }
		  out.println("<p><h3>Price: <h3>" + user.getSum() + "</p>");
		%>
		<p></p>
	</div>
</body>
</html>