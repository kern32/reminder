<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Sign up</title>
<!-- existing user/email error message -->
<style>
p.error{
font-weight: bold;
color: red;
padding: 2px 8px;
margin-top: 10px;
}
</style>
<!-- main design -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/app/resources/css/bootstrap.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/app/resources/css/custom.css">
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
				<input type="text" name="req_name" id="req_name" class="form-control" placeholder="Name"  
				onchange="if(this.value != '') callAjax(this.id, this.value);"> 
					<p id="existing_user"></p>
				
				<input type="text"	name="req_email" id="req_email" class="form-control" placeholder="username@example.com" style="margin-bottom: 8px"
				onchange="if(this.value != '') callAjax(this.id, this.value);"> 
					<p id="existing_email"></p>
				
				<input type="text" name="req_phone" id="phone" class="form-control" placeholder="+38 (097) 999-9999" style="margin-bottom: 8px"> 
				<input type="text" name="req_skype" id="req_skype" class="form-control" placeholder="Skype name" style="margin-bottom: 8px"> 
				<input type="password" name="req_psw" id="req_psw" class="form-control" placeholder="Password" style="margin-bottom: 8px"> 
				<input type="password" name="req_confirmPsw" id="req_confirmPsw"  class="form-control" placeholder="Confirm password" style="margin-bottom: 8px">
				<button type="submit" class="btn btn-primary btn-block">Create account</button>
			</form>
			<div class="col-xs-6 col-md-4">
				<a class="btn btn-link small" href="./login.html" style="margin-left: 85px">Log in</a>
			</div>
		</div>
	</div>
<!-- validate if user/email exists -->
<script type="text/javascript">
  function callAjax(id, value) {
	  var param; var urlValidation;
	  if (id == "req_name"){
		  param = $("#req_name").val();
		  urlValidation = 'username/validation.html';
	  } else {
		  param = $("#req_email").val();
		  urlValidation = 'email/validation.html';
	  }
	  
	  $.ajax({
		  type : "GET",
		  data : ({
			   text: param
			  }),
          url : urlValidation,
          success : function(response) {
          	if (id == "req_name"){
        	  	$("#existing_user").addClass('error');
        	  	$("#existing_user").text(response);
          	} else {
        	  	$("#existing_email").addClass('error');
          	  	$("#existing_email").text(response);
          	}
		}
      });
  }
</script>
</body>
</html>