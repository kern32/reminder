<%@include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>Contacts</title>
</head>
<body>
	<div class="login-form" style="margin-left: 40px; width: 30%;">
		<form id="login-form" action="${pageContext.request.contextPath}/app/comment.html" method="POST" class="form-horizontal">
			<fieldset>
				<legend> Contact Us </legend>
			</fieldset>
			<input type="text" name="subject" id="subject" class="form-control" placeholder="Subject" 
			style="margin-bottom: 5px"> 
			<textarea name="message" id="message" class="form-control" placeholder="Message"  rows="10" 
			style="margin-bottom: 5px"></textarea>
			<br>
			<button type="submit" class="btn btn-primary" style="margin-bottom: 5px;">Submit</button>
			<button type="reset" class="btn btn-primary" style="margin-bottom: 5px">Reset</button>
		</form>
	</div>
</body>
</html>