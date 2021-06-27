function myReload() {
    document.getElementById("captcha").src = document.getElementById("captcha").src+ "?nocache=" + new Date().getTime();
};

$(document).ready(function(){
/* ---------------------------------------------- /*
* E-mail validation
/* ---------------------------------------------- */

function isValidEmailAddress(emailAddress) {
	var pattern = new RegExp(/^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i);
	return pattern.test(emailAddress);
};

/* ---------------------------------------------- /*
* Contact form ajax
/* ---------------------------------------------- */
$('#contact-form').submit(function(e) {
	e.preventDefault();
	var c_name = $('#c_name').val();
	var c_email = $('#c_email').val();
	var c_message = $('#c_message').val();
	var c_check = $('#check').val();
	var response = $('#contact-form .ajax-response');

	var formData = {
		'name'       : c_name,
		'email'      : c_email,
		'text'       : c_message,
		'captcha'    : c_check
	};
	if (c_name== '' || c_email == '' || c_message == '' || c_check == '' || !isValidEmailAddress(c_email)) {
		response.fadeIn(500);
		response.html('<i class="fa fa-warning"></i> Please fix the errors and try again.');
	}else {
		response.html("In Processing...").fadeIn("fast");
		$.ajax({
			type        : 'POST', // define the type of HTTP verb we want to use (POST for our form)
			url         : '/email', // the url where we want to POST
			data        : formData, // our data object
			cache       : false,
			dataType    : 'json', // what type of data do we expect back from the server
			encode      : true,
			success		: function(res){
						    var ret = $.parseJSON(JSON.stringify(res));
							if(ret.Result == "Fail"){
								response.html("Wrong Captcha").fadeIn(500);
							}else if (ret.Result == "Succeed"){
								response.html("Form Submitted Successfully").fadeIn(500);
								$("input[type=reset]").trigger("click");
								document.getElementById("refresh").click();
							}else {
								response.html("Failed to send mail, please mail me to qizhy@mail3.sysu.edu.cn").fadeIn(500);
								document.getElementById("refresh").click();
							}
					}
			});
	}
         return false;
	});
});