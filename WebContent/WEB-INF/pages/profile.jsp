<%@include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>User profile</title>
</head>
<body>
	<div class="panel panel-primary" style="width: 270px; margin-left: 40px;">	
		<!-- Default panel contents -->
		<div class="panel-heading">Profile</div>		
		<form id="login-form" action="${pageContext.request.contextPath}/app/profile.html" method="POST">
			<table class="table">
				<tr>
					<td>Name</td>
					<td>${username}</td>
				</tr>
				<tr>
					<td>Email</td>
					<td><input type="text" name="req_email" id="req_email" class="form-control" placeholder="user@example.com" value=${email } ></td>
				</tr>
				<tr>
					<td>Phone</td>
					<td><input type="text" name="req_phone" id="req_phone" class="form-control" placeholder="+38 (097) 999-9999" value=${phone } ></td>
				</tr>
				<tr>
					<td>Skype</td>
					<td><input type="text" name="req_skype" id="req_skype" class="form-control" placeholder="Skype name" value=${skype } ></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="password" name="req_psw" id="req_psw" class="form-control" placeholder="Password" value=${pass } ></td>
				</tr>
				<tr>
					<td>Repeat password</td>
					<td><input type="password" name="req_confirmPsw" id="req_confirmPsw" class="form-control" placeholder="Confirm password" value=${pass } ></td>
				</tr>
			</table>
			<button type="submit" class="btn btn-primary" name="editProfile" style="margin-left: 15px; margin-bottom: 15px; width: 40%;" >Update</button>
			<button type="reset" class="btn btn-primary" style="margin-left: 15px; margin-bottom: 15px; width: 40%;" >Reset</button>
		</form>
		</div>
</body>
</html>