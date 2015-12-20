<%@include file="header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Messages</title>
<!--required field message-->
<style>
label.error {
	font-weight: bold;
	color: red;
	padding: 2px 8px;
	margin-top: 2px;
}
</style>
<!-- time  -->
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<!-- combo box selection -->
<script type="text/javascript" src="${pageContext.request.contextPath}/app/resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/app/resources/js/bootstrap-select.js"></script>
<link rel="stylesheet" media="screen" href="${pageContext.request.contextPath}/app/resources/css/bootstrap-select.css" />
</head>
<body onload="setReceiver()">
	<form id = "message-form" action="${pageContext.request.contextPath}/reminder/save.html" method="POST">
		<div class="pull-left" style="margin-left: 40px; padding-right: 15px; width: 35%;">
			<textarea class="form-control" rows="10" placeholder="Message" name="message" required="true"></textarea>
		</div>
		<br />
		<div class="container" style="margin-top: -10px;">
			<input type="text" id="subject" name="subject" class="form-control" style="width: 15%; height: 35px;" placeholder="Subject" required="true"/> <br />
			<input type="text" id="datepicker" name="date" class="form-control" style="width: 15%; height: 35px;" placeholder="Date" required="true"/> <br /> 
			<select
				class="selectpicker" id="type" onchange="setReceiver(this)">
				<option value="Email">Email</option>
				<option value="Phone">Phone</option>
				<option value="Skype">Skype</option>
			</select> <br/>
			
			<button class="btn btn-primary" id="submit" type="submit" value="signup" style="width: 15%; margin-top: 10px;">Save</button>
		</div>
	</form>
<%@include file="footer.jsp"%>

<!-- validate required fields -->
<script src="http://jqueryvalidation.org/files/dist/jquery.validate.min.js"></script>
<script src="http://jqueryvalidation.org/files/dist/additional-methods.min.js"></script>
<script>
jQuery.validator.setDefaults({
	debug : true,
	success : "valid"
});
$("#message-form").validate({
	rules : {
		required : true
	}
});
</script>

<!-- select the date -->
<script>
$(function() {
  $( "#datepicker" ).datepicker();
});
</script>
<script type="text/javascript">
$(document).ready(function(e) {
	$('.selectpicker').selectpicker();
});
</script>

<!-- select reminder type -->
<script type="text/javascript">
function setReceiver() {
var output = document.getElementById("receiver");
var type = document.getElementById("type");
var selectedValue = type.options[type.selectedIndex].value;
if (selectedValue == "Email") {
	output.value = "${user.email}";
} else if (selectedValue == "Phone") {
	output.value = "${user.phone}";
} else if (selectedValue == "Skype") {
	output.value = "${user.skype}";
	}
};
</script>
</body>
</html>