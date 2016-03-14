/* initialize*/
$(document).ready(function() {
	$button = null;
	$checkbox = null;
	$reminderID = null;
	$userId = null;
	$status = null;
	$rows = 0;
	$firstResult = 0;
	$initialResult = 5;
	$list_table_header="<thead><tr bgcolor=\"#428bca\" style=\"color:#ffffff\"><th style=\"display:none;\">ID</th><th style=\"display:none;\">userId</th><th>Date</th><th>Delivery</th><th>Subject</th><th style=\"display:none;\">Message</th><th>Receiver</th><th>Status</th></tr></thead>";
	$header = $("meta[name='_csrf_header']").attr("content");
	$token = $("meta[name='_csrf']").attr("content");
	init();
	
	/* reminder type initialization */
	$('.selectpicker').selectpicker();
	
	/* datetime picker initialization */
	$(".form_datetime").datetimepicker({
			format: "M dd, yyyy hh:ii:ss",
			autoclose: true,
			todayBtn: false,
			minuteStep: 2,
			startDate: new Date()
		});
	 
	 /* set datetime by default */
	 $('.form_datetime').selectpicker({ format: 'M dd, yyyy hh:ii:ss'}).datetimepicker("setDate", new Date());
	 
	 console.log("list all reminders by link: ", $link);
	 callAjax($data, $link);

	/* if checkbox is selected */
	$("#checkbox").click(function () {
	   $('#reminder').attr("disabled", $(this).is(":checked"));
	   $('#subject').attr("disabled", $(this).is(":checked"));
	   $('#type').attr("disabled", $(this).is(":checked"));
	   $('#date').attr("disabled", $(this).is(":checked"));
	   $("#date").css("background-color", "#DCDCDC");
	   $checkbox = 'checked';
	});

	/* if checkbox is unselected */
	$("#checkbox").change(function() {
        if(!$(this).is(':checked')) {
        	$checkbox = 'unchecked';
        }
        console.log("checkbox is: ", $checkbox);
    }); 

	/* row is selected */
	$(document).on('click', 'tr', function(e) {
		/* highlight row */
		var isFirstSelected = $(this).closest('thead').index() == 0;
		var rows = $('tr');
    	var row = $(this);
   		if (e.metaKey && !isFirstSelected) {
       		row.addClass('highlight');
    	} else if (!isFirstSelected){
       		rows.removeClass('highlight');
       		row.addClass('highlight');
    	} 
    
    /* set fields */
	if (!isFirstSelected) {
		$reminderID = $(this).closest('tr').find('td:eq(0)').text();
		$userId = $(this).closest('tr').find('td:eq(1)').text();
		var date = $(this).closest('tr').find('td:eq(2)').text();
		var type = $(this).closest('tr').find('td:eq(3)').text();
		var subject = $(this).closest('tr').find('td:eq(4)').text();
		var reminder = $(this).closest('tr').find('td:eq(5)').text();
		var receiver = $(this).closest('tr').find('td:eq(6)').text();
		$status = $(this).closest('tr').find('td:eq(7)').text();
		$id= $(this).attr('id');
		
		$('#date').val(date);
		$('#subject').val(subject);
		$('#reminder').val(reminder);
		$('#type').val(type);
		$('#receiver').val(receiver);
		$('select[name=type]').val(type);
		$('.selectpicker').selectpicker('refresh');

		/* enable checkbox */
		$('#checkbox').removeAttr("disabled");

		/* delete error message */
		$("#login-form").find('label').remove();
		
		/*check if skype contact is added*/
		if (type == 'Skype') {
			isSkypeContactAdded(receiver);
		}
		
		$link = "./update";
		console.log("selected reminder id:", $reminderID);
		}
	});

	/* reset button is clicked */
	$("#reset").click(function () {
		console.log("reset clicked");
		$button = 'reset';
	}); 

	/* submit button is clicked */
	$("#submit").click(function () {
		console.log("submit clicked");
		$button = 'submit';

		/* delete reminder */
		if ($checkbox == 'checked') {
			$data = { "id" : $reminderID };
			$.confirm({
        		text: "Are you sure that you want to delete this reminder?",
            	confirm: function(button) {
            		$link = "./delete";
            		callAjax($data, $link);
            		$("#" + $id).remove(); 
            		resetAll();
            	},
            	cancel: function(button) {
                	return;
            	}
        	});
	    	return false;
		/* update or create */
		} else if(!isEmpty($('#subject').val()) && !isEmpty($('#reminder').val())) {
			$data = {
				"id": $reminderID,
				"userId" : $userId, 
				"date" : $('#date').val(),
				"subject": $('#subject').val(),
				"reminder" : $('#reminder').val(),
				"delivery" : $('#type').val(),
				"receiver": $('#receiver').val(),
				"status" : $status
			};
			if ($data != null){
				$link = './update';
			} else {
				$link = './create';
			}
			callAjax($data, $link);
		}
 	}); 
 
	function callAjax(data, link) {
		console.log("calling ajax");
		console.log("data: ", data);
		console.log("link: ", link);
		$.ajax({
			type : "POST",
			contentType : "application/json",
			data : JSON.stringify(data),
			dataType : 'json',
			timeout : 100000,
			url : link,
			beforeSend : function(xhr) {
			  xhr.setRequestHeader($header, $token);
			  xhr.setRequestHeader("Accept", "application/json");
			  xhr.setRequestHeader("Content-Type", "application/json");
			},
			success : function(data) {
				console.log("success: ", data);
				if (data['result'] != null) {
					drawTable(data['result']);
					$firstResult = data['firstResult'];
					$maxResult = data['maxResult'];
					$isNextPageExists = data['isNextPageExists'];
					$rows = $maxResult;
					handleButtons($firstResult, $maxResult, $isNextPageExists);
				}
			},
			error : function(e) {
				console.log("ERROR: ", e);
			},
		});
		return false;
	}
	
	/*next/previous buttons*/
	function handleButtons($firstResult, $maxResult, $isNextPageExists) {
		console.log("firstResult: ", $firstResult);
		console.log("maxResult: ", $maxResult);
		console.log("isNextPageExists: ", $isNextPageExists);
		if ($firstResult <= $initialResult && $maxResult <= $initialResult && !$isNextPageExists){
			$('#back').addClass("not-active");
			$('#next').addClass("not-active");
		} else if (!$isNextPageExists){
			$('#next').addClass("not-active");
			$('#back').removeClass("not-active");
		} else if ($firstResult <= $initialResult) {
			$('#back').addClass("not-active");
			$('#next').removeClass("not-active");
		} else {
			$('#back').removeClass("not-active");
			$('#next').removeClass("not-active");
		}
		document.getElementById("next").blur();
		document.getElementById("back").blur();
	}
	
	/*next button clicked*/
	$('#next').on('click', function(){
		data = { "firstResult": $firstResult, "maxResult": $maxResult };
		callAjax(data, "./messages");
	   });
	
	/*back button clicked*/
	$('#back').on('click', function(){
		data = { "firstResult": $firstResult - $maxResult - $initialResult, "maxResult": $initialResult };
		callAjax(data, "./messages/");
	   });
	
	function resetAll() {
		$("#checkbox" ).prop( "checked", false);
		$checkbox = "unchecked";
		$('#reminder').attr("disabled", false);
		$('#subject').attr("disabled", false);
		$('#type').attr("disabled", false);
		$('#checkbox').attr("disabled", true);
		$('#date').removeAttr("disabled");
		$('.form_datetime').selectpicker({ format: 'M dd, yyyy hh:ii:ss'}).datetimepicker("setDate", new Date());
		$('#subject').val("");
		$('#reminder').val("");
		$('select[name=type]').val("Email");
		$('.selectpicker').selectpicker('refresh');
		setReceiver();
		$reminderID = null;
		$userId = null;
		$status = null;
		$id = null;
		
		$rows -= 1;
		if($rows == 0 && $firstResult > $initialResult) {
			$('#back').click();
		}
	};
	
	function init() {
		$data = {"firstResult" : $firstResult, "maxResult" : $initialResult};
		$link = "./messages";
	}
	
	function drawTable(data) {
		$("#listTable").html($list_table_header);
		console.log("Building table size: ", data.length);
		for ( var i = 0; i < data.length; i++) {
			drawRow(data[i], i);
			console.log("Building row: ", data[i]);
		}
		console.log("End of Building table: ", data);
	}

	function drawRow(rowData, index) {
		var row = $("<tr id=" + index + "/>");
		$("#listTable").append(row);
		console.log("Row index: ", index);
		$.each(rowData, function(index, value) {
			if (index == 'id' || index == 'userId' || index == 'message') {
				row.append($("<td style=\"display:none;\">" + value	+ "</td>"));
			} else {
				row.append($("<td>" + value + "</td>"));
			}
		});
	}
	
	function isEmpty(str) {
	    return (!str || 0 === str.length);
	}
});

function isSkypeContactAdded(skypeName) {
	$header = $("meta[name='_csrf_header']").attr("content");
	$token = $("meta[name='_csrf']").attr("content");
	console.log("skypeName", skypeName);
	$.ajax({
		type : "POST",
		contentType : "application/json",
		data : JSON.stringify({"skypeName" : skypeName}), 
		dataType : 'json',
		timeout : 100000,
		url : 'skype/isSkypeContactAdded',
		beforeSend : function(xhr) {
		  xhr.setRequestHeader($header, $token);
		},
        success : function(data) {
        	if (data['result'].length != 0) {
        		$("#addContactMessage").addClass('error');
          	  	$("#addContactMessage").text(data['result']);
          	  	disableSaveButton();
          	} else {
				hideMessage();
          	}
          	console.log("return ", data['result']);
        }
    });
}	

function hideMessage() {
	$("#addContactMessage").removeClass('error');
  	$("#addContactMessage").html($("#addContactMessage").children());
  	enableSaveButton();
}

function enableSaveButton() {
	$('#submit').attr("disabled", false);
}

function disableSaveButton() {
	$('#submit').attr("disabled", true);
}