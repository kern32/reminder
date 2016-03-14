<%@include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>Contacts</title>
</head>
<body>
	<div class="login-form contact">
		<form id="login-form" action="./comment.html" method="POST">
			<fieldset>
				<legend> Contact Us </legend>
			</fieldset>
			<input type="text" name="topic" id="topic" class="form-control contact subject" placeholder="Subject" /> 
			<textarea name="message" id="message" class="form-control contact message" placeholder="Message"  rows="10"></textarea><br>
			<button type="submit" class="btn btn-primary contact" >Submit</button>
			<button type="reset" class="btn btn-primary contact" >Reset</button>
		</form>
	</div>
</body>
</html>