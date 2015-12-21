<%@include file="header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>User profile</title>

<style>
label.error {
	font-weight: bold;
	color: red;
	padding: 2px 8px;
	margin-top: 2px;
}

.error {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #a94442;
	background-color: #f2dede;
	border-color: #ebccd1;
}
</style>

<!-- user validation -->
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/app/resources/js/validate/user.validation.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/app/resources/js/validate/jquery.validate.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/app/resources/js/validate/additional-methods.js"></script>
<!-- mask -->
<script type="text/javascript" src="${pageContext.request.contextPath}/app/resources/js/jquery.maskedinput.js"></script>
</head>
<body>
	<div class="panel panel-default" style="width: 270px; margin-left: 40px;">
		<!-- Default panel contents -->
		<div class="panel-heading">Profile</div>
		<form action="${pageContext.request.contextPath}/app/profile.html" method="POST" id="login-form">
			<!-- Table -->
			<table class="table">
				<tr>
					<td>Name</td>
					<td>${username}</td>
				</tr>
				<tr>
					<td>Email</td>
					<td><input type="email" class="form-control" placeholder="user@example.com" name="req_email" value=${email } ></input></td>
				</tr>
				<tr>
					<td>Phone</td>
					<td><input type="tel" class="form-control" id="phone" placeholder="+38 (097) 999-9999" name="req_phone" value=${phone } ></input></td>
				</tr>
				<tr>
					<td>Skype</td>
					<td><input type="text" placeholder="Skype name" class="form-control" name="req_skype" value=${skype } ></<input></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="password" placeholder="Password" class="form-control" id="req_psw" name="req_psw" value=${pass } size="10"></<input></td>
				</tr>
				<tr>
					<td>Repeat password</td>
					<td><input type="password" placeholder="Confirm password" class="form-control" id="req_confirmPsw" name="req_confirmPsw" value=${pass} size="10"></<input></td>
				</tr>
			</table>
			<button type="submit" class="btn btn-primary" name="editProfile" style="margin-left: 15px; margin-top: -10px; margin-bottom: 15px; width: 40%;" >Update</button>
			<button type="reset" class="btn btn-primary" style="margin-top: -10px; margin-bottom: 15px; width: 40%;">Reset</button>
		</form>
	</div>
</body>
</html>