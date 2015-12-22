<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>HTTP 403 Forbidden</title>
<!-- show counter -->			
<script>
<%
String clock = request.getParameter( "clock" );
if( clock == null ) clock = "6";
%>
var timeout = <%=clock%>;
function timer() {
	if( --timeout > 0 ) {
		document.forma.clock.value = timeout;
		window.setTimeout( "timer()", 1000 );
	} else {
		document.forma.clock.value = "0";
	}
}
</script>
<!-- redirect to default page -->
<script type="text/javascript" src="${pageContext.request.contextPath}/app/resources/js/counter.js"></script>
<!-- write a progress bar -->
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/app/resources/css/rotate-circle.css">
</head>
<body>
	<center>
		<h1>Forbidden</h1><p />
		<form action="<%=request.getRequestURL()%>" name="forma" >
			<h4> You will be redirect to the main page after <input type="text" name="clock" value="<%=clock%>" style="border:0px solid white; width:10px; font-weight: bold;"> seconds </h4>
		</form>
		<script>
			timer();
		</script>
	</center>
	<div id="container" style="text-align:center; padding: 100px;">
		<form class="waitMe_container">
			<div class="waitMe" style="background: rgba(255, 255, 255, 0.7)">
				<div class="waitMe_content" style="margin-top: -48px;">
					<div class="waitMe_progress roundBounce">
						<div class="waitMe_progress_elem1" style="background: #000"></div>
						<div class="waitMe_progress_elem2" style="background: #000"></div>
						<div class="waitMe_progress_elem3" style="background: #000"></div>
						<div class="waitMe_progress_elem4" style="background: #000"></div>
						<div class="waitMe_progress_elem5" style="background: #000"></div>
						<div class="waitMe_progress_elem6" style="background: #000"></div>
						<div class="waitMe_progress_elem7" style="background: #000"></div>
						<div class="waitMe_progress_elem8" style="background: #000"></div>
						<div class="waitMe_progress_elem9" style="background: #000"></div>
						<div class="waitMe_progress_elem10" style="background: #000"></div>
						<div class="waitMe_progress_elem11" style="background: #000"></div>
						<div class="waitMe_progress_elem12" style="background: #000"></div>
					</div>
					<div class="waitMe_text" style="color: #000">Please waiting...</div>
				</div>
			</div>
		</form>
		<form name="redirect">
				<input type="hidden" size="3" name="redirect2">
		</form>
	</div>

	<!-- write a progress bar -->
	<script src="${pageContext.request.contextPath}/app/resources/js/rotate-circle.js"></script>
	<script>
	$(function(){
		var current_effect = 'bounce';
		$('#waitMe_ex').click(function(){
			run_waitMe(current_effect);
		});
		$('#waitMe_ex_close').click(function(){
			$('#container > form').waitMe('hide');
		});

		$('#waitMe_ex_effect').change(function(){
			current_effect = $(this).val();
		});
	
		$('#waitMe_ex_effect').click(function(){
			current_effect = $(this).val();
		});
	
		function run_waitMe(effect){
			$('#container > form').waitMe({
				effect: effect,
				text: 'Please waiting...',
				bg: 'rgba(255,255,255,0.7)',
				color:'#000'
			});
		}
	
	});
	</script>

	<!-- redirect to default page -->
	<script>
		var targetURL = "${pageContext.request.contextPath}/app/index.html";
		var countdownfrom = 5;
		var currentsecond = document.redirect.redirect2.value = countdownfrom + 1;
		function countredirect() {
			if (currentsecond != 1) {
				currentsecond -= 1;
				document.redirect.redirect2.value = currentsecond;
			} else {
				window.location = targetURL;
				return
			}
			setTimeout("countredirect()", 1000);
		}
		countredirect();
	</script>
</body>
</html>