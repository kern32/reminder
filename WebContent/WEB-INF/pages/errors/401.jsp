<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Unauthorized</title>
<!-- redirect to default page -->
<script type="text/javascript" src="${pageContext.request.contextPath}/app/resources/js/counter.js"></script>
</head>
<body>
	<center>
		<h1>Unauthorized</h1>
		<hr align="center" width="500" size="2" />
		<p />
		<h4>
			You will be redirect to the main page after 5 seconds
			<form name="redirect">
				<input type="hidden" size="3" name="redirect2">
			</form>
		</h4>
		<p>
		<p>
		<p>
		<p>
		<p>
		<p>
		<h2>Waiting...</h2>
	</center>
	<!-- redirect to default page -->
	<script>
		var targetURL = "${pageContext.request.contextPath}/app/index.html";
		var countdownfrom = 5
		var currentsecond = document.redirect.redirect2.value = countdownfrom + 1
		function countredirect() {
			if (currentsecond != 1) {
				currentsecond -= 1
				document.redirect.redirect2.value = currentsecond
			} else {
				window.location = targetURL
				return

			}
			setTimeout("countredirect()", 1000)
		}
		countredirect()
	</script>
</body>
</html>