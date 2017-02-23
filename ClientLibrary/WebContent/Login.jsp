<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<c:if test="${Registration}">
		<script>alert("User register succesfully");
</script>
	</c:if>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <link href='css/header.css' rel='stylesheet'>
 <link href='css/index.css' rel='stylesheet'>
<title>Library log in</title>
<header>
<form method="post" action='/ClientLibrary/LoginOperation'>
	Log in:<input name='Inputname' type='text' class='form-control'
		placeholder='Username' required autofocus> <input
		name='InputPassword' type='password' class='form-control'
		placeholder='Password' required>
	<button name='BtnLog' class='btn btn-lg btn-primary btn-block'
		type='submit'>Sign in</button>
</form>
</header>
</head>
<body>

<div class="container">
<div class= "initializeCart">
	<h3>Initialize a new cart without log in</h3>
	<form method="post" action='/ClientLibrary/InitializeCartOperation'>
		<button name='BtnLog' class='btn btn-lg btn-primary btn-block'
			type='submit'>Initialize a new cart</button>
	</form>
</div>
<div class= "registration">

	Register user:
	<form method="post" action='/ClientLibrary/RegisterOperation'>
		<input name='Inputname' type='text' class='form-control'
			placeholder='Username' required> <br/><input name='InputPassword'
			type='password' class='form-control' placeholder='Password' required>
		<br/><button name='BtnLog' class='btn btn-lg btn-primary btn-block'
			type='submit'>Register</button>
	</form>
</div>
</div>
</body>
</html>