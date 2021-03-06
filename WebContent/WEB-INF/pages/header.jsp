<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>
<head>
<!-- main design -->
<link rel="icon" href="./resources/img/favicon.ico" type="image/x-icon"/>
<link href='https://fonts.googleapis.com/css?family=Arvo' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="./resources/css/bootstrap.css">
<link rel="stylesheet" href="./resources/css/user-form.css">
<!-- user validation -->
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script type="text/javascript" src="./resources/js/user.validation.js"></script>
<script type="text/javascript" src="./resources/js/jquery.validate.min.js"></script>
<!-- mask -->
<script type="text/javascript" src="./resources/js/jquery.maskedinput.js"></script>
<sec:csrfMetaTags/>
</head>
<body>
	<nav class="navbar navbar-default" data-role="navigation">
	<div class="navbar-header">
		<a href="./index.html"><img alt="image" src="./resources/img/reminder-card.png" /></a>
	</div>

	<!-- Collect the nav links, forms, and other content for toggling -->
	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		<ul class="nav navbar-nav">
			<li><a href="./index.html"><span class="badge pull-right"></span>
					Home</a></li>
			<li><a href="./profile.html"><span class="badge pull-right"></span>
					Profile</a></li>
			<li><a href="./messages.html"><span class="badge pull-right">${count}</span>
					Reminders</a></li>
			<li><a href="./contact.html"><span class="badge pull-right"></span>
					Contact</a></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li><c:if test="${pageContext.request.userPrincipal.name != null}">
					<a href="javascript:formSubmit()">Logout (${pageContext.request.userPrincipal.name})</a>
				</c:if>
			</li>
		</ul>
	</div>
	</nav>
	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
	<!-- user logout -->
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>
</body>
</html>