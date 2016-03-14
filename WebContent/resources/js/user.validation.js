$(document).ready(
			function() {
				$.validator.addMethod("mandatory",function(value,element) {
					return ($button == 'submit' && value.length == 0 ) ? false: true;
				}, "This field is required.");
				$("#phone").mask("+99 (999) 999-9999");
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
								username : {
									required : true,
									username : true,
									maxlength : 30,
									minlength : 3,
									noSpace : true
								},
								email : {
									required : true,
									email : true,
									maxlength : 50,
									minlength : 3,
								},
								skype : {
									maxlength : 30,
									minlength : 3,
									noSpace : true,
								},
								password: {
									required : true,
									password : true,
									maxlength : 20,
									minlength : 3,
									noSpace : true
								},
								confirmPassword : {
									equalTo: "#password"
								},
								topic : {
									required : true,
									maxlength : 50,
								},
								message : {
									required : true,
									maxlength : 150,
								},
								subject : {
									mandatory : true,
									maxlength : 50,
								},
								reminder : {
									mandatory : true,
									maxlength : 150,
								},
								date: {
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


