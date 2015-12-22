<%@include file="header.jsp"%>
<!DOCTYPE html">
<html>
<head>
<title>Reminders</title>
<!-- time  -->
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<!-- combo box selection -->
<script type="text/javascript" src="${pageContext.request.contextPath}/app/resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/app/resources/js/bootstrap-select.js"></script>
<link rel="stylesheet" media="screen" href="${pageContext.request.contextPath}/app/resources/css/bootstrap-select.css" />
</head>
<body onload="setReceiver()">
	<form id="login-form" action="${pageContext.request.contextPath}/reminder/save.html" method="POST">
		<div class="pull-left" style="margin-left: 40px; width: 18%;">
			<textarea id="reminderText" name="reminderText" class="form-control" rows="10" placeholder="Message"></textarea>
		</div>
		<div class="pull-left" style="margin-left: 20px; width: 15%;">
			<input type="text" id="reminderName" name="reminderName" class="form-control" placeholder="Subject"> <br /> 
			<input type="text" id="reminderDate" name="reminderDate" class="form-control" placeholder="Date"><br /> 
			<select class="selectpicker" id="reminderType" name="reminderType" onchange="setReceiver(this)">
				<option value="Email">Email</option>
				<option value="Phone">Phone</option>
				<option value="Skype">Skype</option>
			</select> <br />
			<button class="btn btn-primary" id="submit" type="submit" value="signup" style="margin-top: 15px;">Save</button>
		</div>
	</form>


<!-- select date -->
<script type="text/javascript">
$(function() {
  $( "#reminderDate" ).datepicker();
});
</script>
<!-- select reminder type -->
<script type="text/javascript">
$(document).ready(function(e) {
	$('.selectpicker').selectpicker();
});
</script>
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
<!-- set today as default/disable previous date -->
<script type="text/javascript">
var today = new Date();
var dd = today.getDate();
var mm = today.getMonth()+1; //January is 0!
var yyyy = today.getFullYear();
if(dd<10){
    dd='0'+dd;
} 
if(mm<10){
    mm='0'+mm;
} 
var today = dd+'/'+mm+'/'+yyyy;
$("#reminderDate").mask(today);
$("#reminderDate").datepicker({
    minDate: 0
});
$("#reminderDate").datepicker("option", "dateFormat", "dd/mm/yy");
</script>
</body>
</html>