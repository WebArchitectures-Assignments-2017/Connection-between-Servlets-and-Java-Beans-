<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="checkadmin" value="admin" />
<html>
<head>
<link href='css/header.css' rel='stylesheet'>
<link href='css/listbook.css' rel='stylesheet'>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Books List</title>
<header> <c:choose>
	<c:when test="${sessionScope.iduser==0 || sessionScope.iduser==null}">
		<form method="post" action='/ClientLibrary/LoginOperation'>
			Log in:<input name='Inputname' type='text' class='form-control'
				placeholder='Username' required autofocus> <input
				name='InputPassword' type='password' class='form-control'
				placeholder='Password' required>
			<button name='BtnLog' class='btn btn-lg btn-primary btn-block'
				type='submit'>Sign in</button>
		</form>
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
	<c:if test="${added}">
		<script>alert("Book added to the cart");
</script>
	</c:if>
	<c:if test="${AdminAdded}">
		<script>alert("Book added to the database");
</script>
	</c:if>

<div class="booklist">
	<h3>List of the books avilable</h3>



	<form action="/ClientLibrary/AddBookToCart">
	To add a book to the cart select it and then click on the button "add book"<br/><br/><br/>
		<c:forEach var="bookslist" items="${sessionScope.bookslist}">
			<c:set var="stringid" value="${fn:split(bookslist, ' ')}" />
			<c:choose>
				<c:when test="${param.bookid==stringid[1]}">
					<input type="checkbox" name="bookid" disabled="disabled"
						value="${stringid[1]}">
					<c:out value="${bookslist}" />
					<br/>
				</c:when>
				<c:otherwise>
					<input type="checkbox" name="bookid" value="${stringid[1]}">
					<c:out value="${bookslist}" />
					<br/>
				</c:otherwise>
			</c:choose>
		</c:forEach><br/></br>
		<input type="submit" value="Add selected books to the cart">
	</form><br/><br/>
<form ACTION="/ClientLibrary/ListCartOperation">
		<input type="submit" value="Visualize cart content">
	</form><br/>

</div>

	
	<c:if test="${sessionScope.username==checkadmin}">
<div class="admin">
		<h3>Admin Operations</h3>

		<form method="post" action='/ClientLibrary/AdminAddBook'
			onsubmit="return confirm('Do you really want to add the book on the database?')">
			<h4>Add a new Book</h4>
			<input name='bookname' type='text' placeholder='book name' required
				autofocus> <input name='bookprice' placeholder='book price'
				required>
			<button name='BtnAddBook' type='submit'>AddBook</button>
		</form>

<br/><br/>
		<form method="post" action='/ClientLibrary/AdminViewOperations'>
			<button name='BtnAddBook' type='submit'>View buy operations
				history</button>
		</form>
<br/><br/>
	</c:if>
</div>
</body>
</html>