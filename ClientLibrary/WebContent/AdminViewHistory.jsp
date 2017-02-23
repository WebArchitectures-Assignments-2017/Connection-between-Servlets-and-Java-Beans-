<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href='css/header.css' rel='stylesheet'>
<link href='css/showCartContent.css' rel='stylesheet'>
<title>View buy opertions history</title>
<header> <c:out value="${sessionScope.username}" /> <input
	type="submit" value="LogOut"> </header>
</head>

<body>
<div class="cartContent">
	<h3>View buy operations</h3>
	<ul>
		<c:forEach var="History" items="${History}">
			<li><c:out value="${History}" /></li>
		</c:forEach>
	</ul>
	<form method="post" action='/ClientLibrary/ListBook.jsp'>
		<button name='BtnAddBook' type='submit'>Undo</button>
	</form><br/>
</div>
</body>
</html>