<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
#aa3 {
	background: #404853;
	background: linear-gradient(#687587, #404853);
	background: -o-linear-gradient(#687587, #404853);
	background: -webkit-linear-gradient(#687587, #404853);
	margin: 4px auto;
	border-radius: 10px;
	position: relative;
	width: 1000px;
	height: 0px;
	border: 2px solid #666;
	display: none;
}

@
-webkit-keyframes myfirst {
	from {height: 0px;
}

to {
	height: 100px;
}
}
</style>
<%@ page
	import="user.*,java.util.LinkedList, publication.Publication,java.util.Iterator;"%>
<link rel="stylesheet" href="style2.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	  if (!request.getSession().getAttribute("exist").equals("admin")) {
			response.sendRedirect("Index.jsp");
	  }
	%>
	<div id="ulfield">
		<ul>
			<li><a href="#id">Главная</a></li>
			<li><a href="#">Публикации</a></li>
			<li><a href="#">Подписки</a></li>
			<li>
				<%
				  if (request.getParameter("reg") == null) {
						out.println("<a href=\"?reg=1\">Добавить публикацию</a></li>");
				  } else
						out.println("<a >Добавить публикацию</a></li>");
				%>

				<p
					style="float: right; color: #fff; display: block; 
					font-size: 11px; font-weight: bold;">
					Добро пожаловать,.</p>
		</ul>
	</div>
	<div id="aa3" <%if (request.getParameter("reg") != null) {%>
		style="height: 100px; background-color: aqua; display: block; 
		       animation: myfirst 2s; -moz-animation: myfirst 2s; 
		       -webkit-animation: myfirst 2s;"
		<%}%>>
		<input type="text">dasggsd</input>
	</div>
	<div id="fieldset">

		<%
		  Publication pub = new Publication();

		  LinkedList list = pub.viewAll();
		  Iterator it = list.iterator();
		  while (it.hasNext()) {
				pub = (Publication) it.next();
				out.println(pub.getTitle() + " " + pub.getPrice());
				out.println("<a href=\"deletepub.serv?pub_title=" + pub.getTitle()
					        + "\">Удалить публикацию</a>" + "<br>");
		  }
		%>	
</body>
</html>