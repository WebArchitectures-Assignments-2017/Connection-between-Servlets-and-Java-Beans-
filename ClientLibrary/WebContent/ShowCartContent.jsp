<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href='css/header.css' rel='stylesheet'>
<link href='css/showCartContent.css' rel='stylesheet'>
<title>ShowCartContent</title>
<header> 
<c:choose>
	<c:when test="${sessionScope.iduser==0 || sessionScope.iduser==null}">
		<form method="post" action='/ClientLibrary/LoginOperation'>
			Log in:<input name='Inputname' type='text' class='form-control'
				placeholder='Username' required autofocus> <input
				name='InputPassword' type='password' class='form-control'
				placeholder='Password' required>
			<button name='BtnLog' type='submit'>Sign in</button>
		</form>
	</c:when>
	<c:otherwise>
		<form ACTION="/ClientLibrary/LogOut">
			<c:out value="${sessionScope.username}" />
			<input type="submit" value="LogOut">
		</form>
	</c:otherwise>

</c:choose> </header>
</head>
<body>
<div class="cartContent">
	<h3>Books on the cart</h3>
	<ul>
		<c:forEach var="booksOnCart" items="${booksOnCart}">
			<li><c:out value="${booksOnCart}" /></li>
		</c:forEach>
	</ul>
	<br />
	<br />

	<c:choose>
		<c:when test="${sessionScope.iduser==0 || sessionScope.iduser==null}">To buy the cart content please log in</c:when>
		<c:otherwise>

			<form ACTION="/ClientLibrary/BuyCartOperation"
				onsubmit="return confirm('Do you really want to buy all the books present in your card?')">
				<input type="submit" value="Buy books present in the cart">
			</form>

		</c:otherwise>

	</c:choose>

	<form ACTION="/ClientLibrary/LeaveCartOperation"
		onsubmit="return confirm('Do you really want to leave your cart? (after that operation the cart will be empty)')">
		<input type="submit" value="Leave the cart">
	</form>
	<br/>
	<form method="post" action='/ClientLibrary/ListBook.jsp'>
		<button name='BtnAddBook' type='submit'>Undo</button>
	</form><br/>
</body>
</div>
</html>
