<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="user.*"%>
<html>
<head>
<link rel="stylesheet" href="style2.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="ulfield">
		<ul>
			<li><a href="Index.jsp">Главная</a></li>
			<li><a href="SimpLogin.jsp">Вход/регистрация</a></li>
			<li><a>о сайте</a></li>
		</ul>
	</div>
	<form method="post" action='adduser.serv'>
		<div id="fieldset">
			<div id="a1">
				<fieldset>
					<legend>Login</legend>
					<label for="username">Username</label> <input type="text"
						title="username" name="username"><br> <label
						for="password">Password</label> <input type="password"
						title="password" name="password"><br> <label
						for="email">email</label> <input type="text" title="email"
						name="email"><br> <input type="submit" value="LogIn"
						name="sumbit"> <input type="reset" value="reset"
						name="reset">
				</fieldset>
			</div>
		</div>
	</form>
</body>
</html>