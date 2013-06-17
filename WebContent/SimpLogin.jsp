<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="user.*"%>
<html>
<head>
<title>Login Page</title>
<link rel="stylesheet" href="style2.css">
<body>
	<div id="ulfield">
		<ul>
			<li><a href="Index.jsp">Главная</a></li>
			<li><a href="#">Вход/регистрация</a></li>
			<li><a>о сайте</a></li>
		</ul>
	</div>
	<%
	  if (request.getAttribute("exist") == "notexist") {
			out.println("<h6>Неверное имя пользователя или пароль</h6>");
	  }
	%>
	<a href="Req.jsp">Регистрация</a>

	<form method="POST" action='login.serv'>
		<div id="fieldset">
			<div id="a1">
				<fieldset>
					<legend>Login</legend>
					<label for="username">Username</label> <input type="text"
						title="username" name="username"><br> <label
						for="password">Password</label> <input type="text"
						title="password" name="password"><br> <input
						type="submit" value="LogIn" name="sumbit"> <input
						type="reset" value="reset" name="reset">
				</fieldset>
			</div>
		</div>

	</form>
</body>
</html>