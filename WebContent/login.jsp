<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="utf-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="stylesheet.css" />
<title>Login</title>
</head>
<body>

<div id=wrapper>
	<jsp:include page="navbar.jsp"/> 
	<div id=content>
		<h1>Log In</h1>
		<span class=loginFirst>${ param.userMessage }</span>
		<div id=loginForm>
			<form action="Login" method="POST">
				<input type="text" name="username" placeholder="Username" size=30 required><br>
				<input type="password" name="password" placeholder ="Password" size=30 required><br>
				<input type="submit" value="Login"><br>
				${ errorMessage }
			</form>
		</div>
	</div>
</div>
	
</body>
</html>