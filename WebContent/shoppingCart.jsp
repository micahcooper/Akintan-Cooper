<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="utf-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="stylesheet.css" />
<title>Shopping Cart</title>
</head>
<body>

<div id=wrapper>
	<jsp:include page="navbar.jsp"/> 
	<div id=content>
		<h1>Shopping Cart</h1>
		
		<span style='color:red;'>${ message }</span>
		<span style='color:red;'>${ param.message }</span>
		${ table }
	</div>
</div>

</body>
</html>