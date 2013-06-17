<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page
	import="user.*,java.util.LinkedList, publication.Publication,java.util.Iterator;"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="style2.css">
<title>Insert title here</title>
</head>
<body>
	<%
	  if (request.getSession().getAttribute("exist") == "true") {
		User user = (User) request.getSession().getAttribute("user");
	%>

	<div id="ulfield">
		<ul>
			<li><a href="#">Главная</a></li>
			<li><a href="Mypub.jsp">Мои публикации</a></li>
			<li><a href="logout.serv">выход</a></li>
			<li><a href="about.jsp">о сайте</a></li>
			<p>
				Добро пожаловать,<%=user.getName()%>.
			</p>
		</ul>
		<%
		  } else {
		%>
		<div id="ulfield">
			<ul>
				<li><a href="#">Главная</a></li>
				<li><a href="SimpLogin.jsp">Вход/регистрация</a></li>
				<li><a>о сайте</a></li>
			</ul>
			<%
			  }
			%>

		</div>

		<%
		  if (request.getAttribute("adduser") == "true") {
				out.println("<h6>Пользователь добавлен</h6>");
		  }
		  if (request.getAttribute("sub_add") != null) {
			out.println("<h6>Подписка, " + request.getAttribute("sub_add")
					    + ",добавлена</h6>");
		  }
		%>
		<div id="fieldset">
			<%
			  Publication pub = new Publication();

			  LinkedList list = pub.viewAll();
			  Iterator it = list.iterator();
			  while (it.hasNext()) {
			%>
			<div class="pub">
				<%
				  pub = (Publication) it.next();
				  out.println("<img alt=\"\" src=\"" + pub.getUrl() + "\">");
				  out.println(" <p><h3>" + pub.getTitle() + "</h3>" + pub.getPrice() + " usd");
				  if (request.getSession().getAttribute("exist") == "true"){
				   out.println("<a class=\"button button_type__add\" href=\"addsub.serv?pub_title="
							  + pub.getTitle()
							  + "\">Subscribe<span class=\"button_icon\"></span></a></p>"
							  + "<br>");
				  } else {
					out.println("<br>");
				  }			  
				%>
			</div>
			<%
			  }
			%>
		</div>
</body>
</html>