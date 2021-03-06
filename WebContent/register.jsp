<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="utf-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="stylesheet.css" />
<title>Register as a User</title>
</head>
<body>

<div id=wrapper>
	<jsp:include page="navbar.jsp"/> 
	<div id=content>
		<form id="loginForm" action="Register" method="POST">
			<h1>Log In</h1>
			<input type="text" name="username" placeholder="Username" required><br>
			<input type="text" name="firstname" placeholder="firstname" required><br>
			<input type="text" name="lastname" placeholder="lastname" required><br>
			<input type="text" name="dob" placeholder="dob" required><br>
			<input type="password" name="password" placeholder ="Password" required><br>
			<input type="submit" value="Login"><br>
			${errorMessage}
		</form>
	</div>
</div>
	
</body>
</html>