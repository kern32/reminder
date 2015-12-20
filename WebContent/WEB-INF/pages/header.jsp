<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" media="screen" href="${pageContext.request.contextPath}/app/resources/css/bootstrap.css">
</head>
<body>
	<nav class="navbar navbar-default" role="navigation">
	<div class="navbar-header">
		<a href="./index.html"><img alt="image" src="${pageContext.request.contextPath}/app/resources/img/reminder-card.png" /></a>
	</div>

	<!-- Collect the nav links, forms, and other content for toggling -->
	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		<ul class="nav navbar-nav">
			<li><a href="./index.html"><span class="badge pull-right"></span>
					<h4>Home</h4></a></li>
			<li><a href="./profile.html"><span class="badge pull-right"></span>
					<h4>Profile</h4></a></li>
			<li><a href="./messages.html"><span class="badge pull-right">${count}</span>
					<h4>Reminders</h4></a></li>
			<li><a href="./contact.html"><span class="badge pull-right"></span>
					<h4>Contact</h4></a></li>
		</ul>
		<form class="navbar-form navbar-left">
			<div class="form-group">
				<h4>
					<input type="text" class="form-control" placeholder="Search" />
				</h4>
			</div>
			<button type="submit" class="btn btn-primary">Submit</button>
		</form>
		<ul class="nav navbar-nav navbar-right">
			<li><c:if test="${pageContext.request.userPrincipal.name != null}">
					<a href="javascript:formSubmit()"><h4>Logout (${pageContext.request.userPrincipal.name})</h4></a>
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