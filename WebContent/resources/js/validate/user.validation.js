$(document).ready(
			function() {
				$("#req_phone").mask("+99 (999) 999-9999");
				$.validator.addMethod("username", function(value, element) {
			        	return this.optional(element) || /^[a-z0-9\_]+$/i.test(value);
			    }, "Username must contain only letters, numbers, or underscore.");
				$.validator.addMethod("password",function(value,element) {
						return this.optional(element) || /^[A-Za-z0-9!@#$%^&*()_]{6,15}$/i.test(value); 
				},"Passwords are 6-16 characters");
				$.validator.addMethod("digits",function(value,element) {
						return this.optional(element) || /^[0-9\-\+]+$/i.test(value);
				}, "Please enter numbers.");
				$.validator.addMethod("noSpace", function(value, element) { 
					 	return value.indexOf(" ") < 0; 
				}, "No space please");
				$('#login-form').validate(
						{
							rules : {
								req_email : {
									required : true,
									email : true,
									maxlength : 30,
									minlength : 3,
								},
								req_name : {
									required : true,
									username : true,
									maxlength : 30,
									minlength : 3,
									noSpace : true
								},
								req_psw : {
									required : true,
									password : true,
									maxlength : 30,
									minlength : 3,
									noSpace : true
								},
								req_confirmPsw : {
									equalTo: "#req_psw"
								},
								req_skype : {
									maxlength : 30,
									minlength : 3,
									noSpace : true,
								},
								subject : {
									required : true,
									maxlength : 150,
								},
								message : {
									required : true,
									maxlength : 150,
								},
								reminderName : {
									maxlength : 50,
									required : true,
								},
								reminderText : {
									maxlength : 150,
									required : true,
								},
								reminderType: {
									maxlength : 150,
								},
								reminderDate: {
									required : true,
								}
							},
							highlight : function(element) {
								$(element).closest('.control-group')
										.removeClass('success').addClass(
												'error');
							}  
						});
			}); // end document.ready


