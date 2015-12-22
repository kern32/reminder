<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Authorization</title>
<!-- main design -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/app/resources/css/bootstrap.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/app/resources/css/custom.css">
<!-- grey background-->
<style>
html,body {
	background-color: #eee;
	padding-top: 20px;
}
input{
	margin-bottom: 8px;
}
</style>
</head>
<body onload='document.loginForm.username.focus();'>
	<div class="container">
		<div class="login-form">
			<h2>Log in</h2>
			<c:if test="${not empty error}">
				<div class="error">${error}</div>
			</c:if>
			<c:if test="${not empty msg}">
				<div class="msg">${msg}</div>
			</c:if>
			<form action="${pageContext.request.contextPath}/app/auth/login_check?targetUrl=${targetUrl}" method="POST" id="login-form">
				<div class="control-group">
					<div class="controls">
						<input type="text" class="form-control" placeholder="Username" name="username"  value="user" />
					</div>
					<div class="controls">
						<input type="password" class="form-control" placeholder="Password" name="password" value="123456" />
					</div>
				</div>
				<div class="form-group">
					<button type="submit" class="btn btn-primary btn-block">Login</button>
				</div>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			</form>
			<div class="col-xs-6 col-md-4">
				<a class="btn btn-link small" href="./registration.html">Register new Account</a>
			</div>
		</div>
	</div>
</body>
</html>