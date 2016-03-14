<%@include file="header.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<title>User profile</title>
</head>
<body>
	<div class="panel panel-primary profile" >	
		<!-- Default panel contents -->
		<div class="panel-heading">Profile</div>		
		<form:form id="login-form" action="./profile.html" modelAttribute="userAttribute"  method="POST">
			<table class="table">
				<tr>
					<td>Name</td>
					<td>${username}</td>
				</tr>
				<tr>
					<td>Email</td>
					<td><input type="text" name="email" id="email" class="form-control" placeholder="user@example.com" value=${email } ></td>
				</tr>
				<tr>
					<td>Phone</td>
					<td><input type="text" name="phone" id="phone" class="form-control" placeholder="+38 (097) 999-9999" value=${phone } ></td>
				</tr>
				<tr>
					<td>Skype</td>
					<td><input type="text" name="skype" id="skype" class="form-control" placeholder="Skype name" value=${skype } ></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="password" name="password" id="password" class="form-control" placeholder="Password" value=${pass } ></td>
				</tr>
				<tr>
					<td>Repeat password</td>
					<td><input type="password" name="confirmPassword" id="confirmPassword"  class="form-control" placeholder="Confirm password" value=${pass } ></td>
				</tr>
			</table>
			<input type="hidden" name="username" value="${username }" />
			<input type="hidden" name="id" value="${id }" />
			<input type="hidden" name="enabled" value="${enabled }" />
			<input type="hidden" name="role.role" value="${role.role }" />
			<input type="hidden" name="role.id" value="${role.id}" />
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<button type="submit" class="btn btn-primary profile" >Update</button>
			<button type="reset" class="btn btn-primary profile" >Reset</button>
		</form:form>
		</div>
</body>
</html>