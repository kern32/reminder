<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Sign up</title>
<!-- main design -->
<link rel="stylesheet" media="screen" href="${pageContext.request.contextPath}/app/resources/css/bootstrap.css">
<link rel="stylesheet" media="screen" href="${pageContext.request.contextPath}/app/resources/css/custom.css">
<!-- user validation -->
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/app/resources/js/validate/user.validation.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/app/resources/js/validate/jquery.validate.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/app/resources/js/validate/additional-methods.js"></script>
<!-- mask -->
<script type="text/javascript" src="${pageContext.request.contextPath}/app/resources/js/jquery.maskedinput.js"></script>
</head>
<body>
	<div class="container">
		<div class="login-form">
			<h2>Registration</h2>
			<form action="${pageContext.request.contextPath}/app/registration.html" method="POST" id="login-form">
				<input type="text" class="form-control" placeholder="Name" name="req_name" style="margin-bottom: 5px"> 
				<input type="email"	class="form-control" placeholder="username@example.com" name="req_email" style="margin-bottom: 5px"> 
				<input id="phone" type="text" class="form-control" placeholder="+38 (097) 999-9999" name="req_phone" style="margin-bottom: 5px"> 
				<input type="text" class="form-control" placeholder="Skype name" name="req_skype" style="margin-bottom: 5px"> 
				<input id="req_psw" type="password" class="form-control" placeholder="Password" name="req_psw" style="margin-bottom: 5px"> 
				<input id="req_confirmPsw" type="password" class="form-control" placeholder="Confirm password" name="req_confirmPsw" style="margin-bottom: 5px">
				<button type="submit" class="btn btn-primary btn-block">Create account</button>
			</form>
			<div class="col-xs-6 col-md-4">
				<a class="btn btn-link small" href="./login.html" style="margin-left: 85px">Log in</a>
			</div>
		</div>
	</div>
</body>
</html>