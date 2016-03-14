<%@include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>Reminders</title>
<!-- date time picker -->
<link rel="stylesheet" media="screen" href="./resources/css/bootstrap-datetimepicker.css" />		
<!-- time  -->
<script type="text/javascript" src="./resources/js/bootstrap-datetimepicker.min.js"></script>
<!-- combo box selection -->
<script type="text/javascript" src="./resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="./resources/js/bootstrap-select.js"></script>
<link rel="stylesheet" media="screen" href="./resources/css/bootstrap-select.css" />
<!-- confirmation dialog -->
<link href="./resources/css/bootstrap.min.css" rel="stylesheet">
<script src="./resources/js/jquery.confirm.js"></script>
<script src="./resources/js/reminder-form.js"></script>
</head>
<body  onload="setReceiver()">
<table class="table reminders" id="listTable" /></table>
	<form id="login-form" >
		<div class="pull-left reminder">
			<textarea id="reminder" name="reminder" class="form-control" rows="16" placeholder="Message" ></textarea>
		</div>
		<div class="pull-left reminder" >
			<input type="text" id="subject" name="subject" class="form-control" placeholder="Subject"  ></input> <br /> 
			<div class="input-append date form_datetime" >		
    			<input type="text" id="date" name="date" class="form-control" size="16" value="" readonly  ></input>
    			<span class="add-on"><i class="icon-th"></i></span>	
			</div>		
			<input type="text" id="receiver" name="receiver" class="form-control" placeholder="Receiver" readonly ></input> 
			<p id="addContactMessage"></p>
			<br/>
			<select class="selectpicker" id="type" name="type" onchange="setReceiver(this)">
				<option value="Email">Email</option>
				<option value="Phone">Phone</option>
				<option value="Skype">Skype</option>
			</select> <br />
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<div class="checkbox">
  				<input type="checkbox" id="checkbox" value="" disabled/> <span>Delete</span>
			</div> 
			<button class="btn btn-primary reminder reset" id="reset" >Reset</button>
			<button class="btn btn-primary reminder submit" id="submit" >Save</button>
		</div>
	</form>
	<script type="text/javascript">
	function setReceiver() {
		var output = document.getElementById("receiver");
		var type = document.getElementById("type");
		var selectedValue = type.options[type.selectedIndex].value;
		console.log("selected value: ", "${user.email}");
		if (selectedValue == "Email") {
			output.value = "${user.email}";
			hideMessage();
		} else if (selectedValue == "Phone") {
			output.value = "${user.phone}";
			hideMessage();
		} else if (selectedValue == "Skype") {
			output.value = "${user.skype}";
			isSkypeContactAdded(output.value);
		}
	};
	</script>
</body>
</html>
<%@include file="footer.jsp"%>