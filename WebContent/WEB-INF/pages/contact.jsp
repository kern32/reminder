<%@include file="header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Contacts</title>
<!--required field message-->
<style>
label.error {
	font-weight: bold;
	color: red;
	padding: 2px 8px;
	margin-top: 2px;
}
</style>
</head>
<body>

	<div class="login-form" style="margin-left: 40px; width: 30%;">
		<form action="${pageContext.request.contextPath}/app/comment.html" method="POST" id="login-form" class="form-horizontal">
			<fieldset>
				<legend> Contact Us </legend>
			</fieldset>
			<input type="text" class="form-control" placeholder="Subject" name="subject" 
			style="margin-bottom: 5px" required="true" id="subject"
			onchange="if(this.value != '') callAjax('checkSubject', this.value, this.id);"
			
			> <p id="result_text" style="font-weight: bold;color: red;padding: 2px 8px;margin-top: 2px;"></p>
			
			<textarea class="form-control" placeholder="Message" name="message" rows="10" 
			style="margin-bottom: 5px" required="true" id="message"></textarea>
			<br>
			<button type="submit" class="btn btn-primary" style="margin-bottom: 5px;">Submit</button>
			<button type="reset" class="btn btn-primary" style="margin-bottom: 5px">Reset</button>
		</form>
	</div>
<!-- validate required fields -->

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript">


  function callAjax(method, value, target)
  {
	  var subject = $("#subject").val();
	  $.ajax({
		  type : "GET",
		  data : ({
			   text: subject
			  }),
          url : 'ajaxtest.html',
         
          success : function(response) {
				$("#result_text").text(response);
			},
          
          /* success : function(data) {
              $('#result').html(data);
          } */
      });
  }

</script>



<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/app/resources/js/validate/jquery.validate.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/app/resources/js/validate/additional-methods.js"></script>
<script>
jQuery.validator.setDefaults({
	debug : true,
	success : "valid"
});
$("#login-form").validate({
	rules : {
		required : true
	}
});
</script>
</body>
</html>