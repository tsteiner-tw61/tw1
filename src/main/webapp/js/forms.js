/**
 * Validate email string format.
 */
function validateEmail(input) {

	//var validRegex = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
	var validRegex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

 	if (input.value.match(validRegex)) {
  		//alert("Valid email address!");
		return true;
	} 
	else {
		//alert("Invalid email address!");
		return false;
 	}
  
}
 
/**
 * Validate password string format.
 */
function validatePassword(input) { 
	 
	var decimal=  /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9])(?!.*\s).{8,24}$/;
	
	if (input.value.match(decimal)) { 
		//alert('Correct, try another...')
		return true;
	}
	else { 
		//alert('Wrong...!')
		return false;
	}
	
}


function validateLoginForm() {
	
	var msgDiv = document.getElementById("index-login-msg");
	
	if (!validateEmail(document.login.email)) {
		msgDiv.innerHTML = "Invalid email address format.";
		return false;
	}
	if (!validatePassword(document.login.pwd)) {
		msgDiv.innerHTML = "Invalid password format.";
		return false;
	}
	return true;
	
}



function validateRegistrationForm() {
	
	var msgDiv = document.getElementById("msg");
	
	if (!validateEmail(document.register.email)) {
		msgDiv.innerHTML = "Invalid email address format.";
		return false;
	}
	if (!validatePassword(document.register.pwd)) {
		msgDiv.innerHTML = "Invalid password format.";
		return false;
	}
	if (document.register.legal.checked === false) {
		msgDiv.innerHTML = "You must accept the Terms of Service to continue.";
		return false;
	}
	return true;
	
}


function validateChangePasswordForm() {
	
	var msgDiv = document.getElementById("msg");
	
	if (!validatePassword(document.chgpwd.pwd)) {
		msgDiv.innerHTML = "Invalid password format.";
		return false;
	}
	return true;
	
}


function validateEmailVerificationForm() {
	
	var msgDiv = document.getElementById("msg");
	
	if (!validateEmail(document.verifyemail.email)) {
		msgDiv.innerHTML = "Invalid email address format.";
		return false;
	}
	if (document.verifyemail.legal.checked === false) {
		msgDiv.innerHTML = "You must accept the Terms of Service to continue.";
		return false;
	}
	return true;
	
}


function validateEmailConfirmationForm() {
	
	var msgDiv = document.getElementById("msg");
	
	if (document.confirmemail.received.checked === true) {
		msgDiv.innerHTML = "Another verification code has been sent to your account.";
		return false;
	}
	else {
		if (document.confirmemail.code.value.length !== 6) {
			msgDiv.innerHTML = "Please enter your confirmation code or specify that you did not receive it.";
			return false;
		}
	}
	return true;
	
}

/**
 * Validate registration form.
 */
function validatePiForm() {
	
	var msgDiv = document.getElementById("msg");
	console.log("1-" + document.pi.country.value);
	console.log("2-" + document.pi.sp.value);
	if (document.pi.country.value === '  ') {
		msgDiv.innerHTML = "Please select your country";
		return false;
	}
	
	if (document.pi.country.value === 'USA') {
		if (document.pi.sp.value === '  ') {
			msgDiv.innerHTML = "Please select your state";
			return false;
		}	
	}
	
	return true;
	
}


/**
 * Validate survey form.
 */
function validateSurveyForm(id) {
	
	var myForm = document.getElementById(id);
	var errorDiv = document.getElementById("error");
	
	for (var i = 0; i < myForm.elements.length; i++) {
		
		if (myForm.elements[i].type === 'hidden') {
			if (myForm.elements[i].value === '0') {
				errorDiv.innerHTML = "Please complete the survey before submission. Thank you.";
				return false;
			}
		}
	
	}
	
	return true;
	
}

function validateAddWrkForm() {

	var dateRegex = /\d{2}\-\d{2}\-\d{4}/;
	
	var errormsg = document.getElementById('ddrPop5Msg');
	
	errormsg.innerHTML = "";

	if (document.formdash5.jobtitleexp.value.length === 0) {
		console.log("invalid name");
		errormsg.innerHTML = "Please enter a Job Title";
		return false;
	}
	if (!document.formdash5.startexp.value.match(dateRegex)) {
		console.log("invalid start date");
		errormsg.innerHTML = "Please enter a valid Start Date";
		return false;
	}
	if (!document.formdash5.endexp.value.match(dateRegex)) {
		console.log("invalid end date");
		errormsg.innerHTML = "Please enter a valid End Date";
		return false;
	}
	if (document.formdash5.mgremailexp.value !== document.formdash5.mgremailexp2.value) {
                console.log("emails do not match");
                errormsg.innerHTML = "Contact Email entries do not match";
                return false;
        }
	if (!validateEmail(document.formdash5.mgremailexp)) {
		console.log("invalid email address");
		errormsg.innerHTML = "Please enter valid Contact Emails";
		return false;
	}
	if (!validateEmail(document.formdash5.mgremailexp2)) {
		console.log("invalid email address");
		errormsg.innerHTML = "Please enter valid Contact Emails";
		return false;
	}
	if (document.formdash5.descexp.value.length === 0) {
		console.log("no description");
		errormsg.innerHTML = "Please enter a Job Description";
		return false;
	}
	let collection1 = document.getElementById('ddrHardskillsexp').selectedOptions;
	let coll1Length = collection1.length;
	if (collection1.length < 2) {
		console.log("too few hard skills selected");
		errormsg.innerHTML = "Please select 2 Measurable skills";
		return false;
	}
	let collection2 = document.getElementById('ddrSoftskillsexp').selectedOptions;
	let coll2Length = collection2.length;
	if (collection2.length < 2) {
		console.log("too few soft skills selected");
		errormsg.innerHTML = "Please select 2 Interpersonal skills";
		return false;
	}
	
	return true;
	
}

function showIframe(id) {
	var frame = document.getElementById(id);
	frame.src = '/tw1/post1.jsp';
	frame.style.display = 'inline';
}

function hideIframe(id) {
	var frame = document.getElementById(id);
	frame.style.display = 'none';
}


function showIframe2(id) {
	var frame = document.getElementById(id);
	frame.style.display = 'inline';
}

function hideIframe2(id) {
	var frame = document.getElementById(id);
	frame.style.display = 'none';
}

