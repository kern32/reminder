<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<title>Sign up</title>
<sec:csrfMetaTags/>
<!-- main design -->
<link rel="icon" href="./resources/img/favicon.ico" type="image/x-icon"/>
<link rel="stylesheet" href="./resources/css/bootstrap.css">
<link rel="stylesheet" href="./resources/css/user-form.css">
<link rel="stylesheet" href="./resources/css/entry.css">
<!-- user validation -->
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script type="text/javascript" src="./resources/js/user.validation.js"></script>
<script type="text/javascript" src="./resources/js/jquery.validate.min.js"></script>
<!-- phone mask -->
<script type="text/javascript" src="./resources/js/jquery.maskedinput.js"></script>
<!-- submit on entry button -->
<script type="text/javascript">
	$(document).keypress(function(event) {
    if (event.which == 13) {
        event.preventDefault();
        $("form").submit();
    }
});
</script>
</head>
<body onload='document.registerform.username.focus();'>
	<div class="login-form">
		<h2>Registration</h2>
		<form:form action="./registration.html" modelAttribute="userAttribute" method="POST" id="login-form" name="registerform">
			<input type="text" name="username" id="username" class="form-control" placeholder="Name"
			onchange="if(this.value != '') callAjax(this.id, this.value);" /> 
				<p id="existing_user"></p>
			
			<input type="text"	name="email" id="email" class="form-control" placeholder="username@example.com"
			onchange="if(this.value != '') callAjax(this.id, this.value);"/> 
				<p id="existing_email"></p>
				
			<input type="text" name="phone" id="phone" class="form-control" placeholder="+38 (097) 999-9999" /> 
			<input type="text" name="skype" id="skype" class="form-control" placeholder="Skype name" /> 
			<input type="password" name="password" id="password" class="form-control" placeholder="Password" /> 
			<input type="password" name="confirmPassword" id="confirmPassword" class="form-control" placeholder="Confirm password" />
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<button type="submit" class="btn btn-primary btn-block">Create account</button>
		</form:form>
		<div class="col-xs-6 col-md-4">
			<a class="btn btn-link small" href="./login.html" style="margin-left: 85px;">Log in</a>
		</div>
	</div>
<!-- validate if user/email exists -->
<script type="text/javascript">
  function callAjax(id, value) {
	  var param; var urlValidation;
	  if (id == "username"){
		  param = $("#username").val();
		  urlValidation = 'username/validation';
	  } else {
		  param = $("#email").val();
		  urlValidation = 'email/validation';
	  }
	  $header = $("meta[name='_csrf_header']").attr("content");
	  $token = $("meta[name='_csrf']").attr("content");
	  console.log("param", param);
	  
	  $.ajax({
		  type : "POST",
		  contentType : "application/json",
		  data : JSON.stringify({"param" : param}), 
		  dataType : 'json',
		  timeout : 100000,
		  url : urlValidation,
		  beforeSend : function(xhr) {
		 	  xhr.setRequestHeader($header, $token);
		  },
          success : function(data) {
          	if (id == "username" && data['result']){
        	  	$("#existing_user").addClass('error');
        	  	$("#existing_user").text(param.concat( ' exists'));
          	} else if (id == "username" && !data['result']){
          		$("#existing_user").removeClass('error');
          		$("#existing_user").html($("#existing_user").children());
          	} else if (id == "email" && data['result']) {
        	  	$("#existing_email").addClass('error');
          	  	$("#existing_email").text(param.concat(' exists'));
          	} else if (id == "email" && !data['result']){
          		$("#existing_email").removeClass('error');
          		$("#existing_email").html($("#existing_email").children());
          	}
          	console.log("return ", data['result']);
		}
      });
  }
</script>
</body>
</html>