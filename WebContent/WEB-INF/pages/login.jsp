<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Authorization</title>
<!-- main design -->
<link rel="icon" href="./resources/img/favicon.ico" type="image/x-icon"/>
<link rel="stylesheet" href="./resources/css/bootstrap.css">
<link rel="stylesheet" href="./resources/css/user-form.css">
<link rel="stylesheet" href="./resources/css/entry.css">
<!-- submit on entry button -->
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script type="text/javascript">
	$(document).keypress(function(event) {
    if (event.which == 13) {
        event.preventDefault();
        $("form").submit();
    }
});
</script>
</head>
<body onload='document.loginform.username.focus();'>
	<div class="login-form">
		<h2>Log in</h2>
		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>
		<form action="./auth/login_check?targetUrl=${targetUrl}" method="POST" name="loginform" >
			<input type="text" class="form-control" placeholder="Username" name="username"  value="user"/>
			<input type="password" class="form-control" placeholder="Password" name="password"  value="111111"/>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<button type="submit" class="btn btn-primary btn-block">Login</button>
		</form>
		<div class="col-xs-6 col-md-4">
			<a class="btn btn-link small" href="./registration.html">Register new Account</a>
		</div>
	</div>
</body>
</html>