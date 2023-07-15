/**
 * 
 */

var fetchPath = 'http://localhost:8080/tw1/';

/*
 * Photo upload.
 */
async function uploadFile() {
    let formData = new FormData(); 
    formData.append("file", ajaxfile.files[0]);
    await fetch(fetchPath + 'FileUploadServlet', {
      method: "POST", 
      body: formData
    }).then(response => {
		location.reload();
	}); 
}



/*
 * Process popup form to the PopupServlet.
 */
async function processForm(id) {
	var myForm = document.getElementById(id);
	var params = new URLSearchParams();
	params.append("form", id)
    
    //Extract Each Element Value
    for (var i = 0; i < myForm.elements.length; i++) {
    	if (myForm.elements[i].type !== 'button') {
			if (myForm.elements[i].type === 'select-multiple') {
				let collection = myForm.elements[i].selectedOptions;
    			let output = "";
    			for (let j = 0; j < collection.length; j++) {
      				if (output === "") {
        				output = "";
      				}
      				output += collection[j].label;
      				if (j < collection.length - 1) {
        				output += ",";
      				} 
    			}
				
				//alert("selectedoptions " + output);
				params.append(myForm.elements[i].name, output);
			}
			else {
				params.append(myForm.elements[i].name, myForm.elements[i].value);
			}

		}
    }
    let response = await fetch(fetchPath + 'PopupServlet', {
      method: "POST", 
      body: params
    }); 
   for (var i = 0; i < myForm.elements.length; i++) {
    	if ((myForm.elements[i].type !== 'button')  && 
    		(myForm.elements[i].type !== 'hidden') && 
    		(myForm.elements[i].type !== 'select-multiple')) {
			var elementId = 'ddr' + myForm.elements[i].name;
			document.getElementById(elementId).innerText = myForm.elements[i].value;
		}
    }
}



async function processForm2(id) {
	//alert("in processForm2()");
	var myForm = document.getElementById(id);
	var params = new URLSearchParams();
	params.append("form", id)
    
    //Extract Each Element Value
    for (var i = 0; i < myForm.elements.length; i++) {
    	if (myForm.elements[i].type !== 'button') {
			if (myForm.elements[i].type === 'select-multiple') {
				let collection = myForm.elements[i].selectedOptions;
    			let output = "";
    			for (let j = 0; j < collection.length; j++) {
      				if (output === "") {
        				output = "";
      				}
      				output += collection[j].label;
      				if (j < collection.length - 1) {
        				output += ",";
      				} 
    			}
				
				//alert("selectedoptions " + output);
				params.append(myForm.elements[i].name, output);
			}
			else {
				params.append(myForm.elements[i].name, myForm.elements[i].value);
			}

		}
    }
    let response = await fetch(fetchPath + 'PopupServlet', {
      method: "POST", 
      body: params
    }).then(response => {
		//alert('response received');	
		location.reload();
		//alert('location reloaded');
	}); 
 
}


async function processUrlForm(id) {
	var myForm = document.getElementById(id);
	var params = new URLSearchParams();
	params.append("form", id)
    
    //Extract Each Element Value
    for (var i = 0; i < myForm.elements.length; i++) {
    	if (myForm.elements[i].type !== 'button') {
			params.append(myForm.elements[i].name, myForm.elements[i].value);
		}
    }
    let response = await fetch(fetchPath + 'PopupServlet', {
      method: "POST", 
      body: params
    }); 
    for (var i = 0; i < myForm.elements.length; i++) {
    	if ((myForm.elements[i].type !== 'button') && (myForm.elements[i].type !== 'hidden')) {
			var elementId = 'ddr' + myForm.elements[i].name + 'a';
			document.getElementById(elementId).value = myForm.elements[i].value;
		}
    }
	location.reload();
	
}



async function processUrlForm2(id) {
	var myForm = document.getElementById(id);
	var params = new URLSearchParams();
	params.append("form", id)
    
    //Extract Each Element Value
    for (var i = 0; i < myForm.elements.length; i++) {
    	if (myForm.elements[i].type !== 'button') {
			params.append(myForm.elements[i].name, myForm.elements[i].value);
		}
    }
    let response = await fetch(fetchPath + 'PopupServlet', {
      method: "POST", 
      body: params
    }).then(response => {
		location.reload();
	}); 
	
}



function check3(id) {
	
	var sum = 0;
	
	for (var i = 0; i < document.formdash2.ckb.length; i++) {

		if (document.formdash2.ckb[i].checked) {
			sum = sum + 1;
		}
	}
	//alert(sum);
	if (sum < 3) {
		return false;
	}
	document.getElementById(id).style.display = "none";
	processCheckboxForm2('ddrPopForm2'); 
	closePopup('ddrPop2'); 
	//location.reload();
	
	
}



/*
 * Process popup form to the PopupServlet.
 * For the multiple checkbox select form.
 */
async function processCheckboxForm(id) {
	var myForm = document.getElementById(id);
	var params = new URLSearchParams();
	params.append("form", id)
    
    //Extract Each Element Value
    for (var i = 0; i < myForm.elements.length; i++) {
    	if (myForm.elements[i].type === 'hidden') {
			alert(myForm.elements[i].value);
			params.append(myForm.elements[i].name, myForm.elements[i].value);
		}
    }
    let response = await fetch(fetchPath + 'PopupServlet', {
      method: "POST", 
      body: params
    }).then(alert("checkboxFormProcessed")); 
    var skillCount = 1;
    for (var i = 0; i < myForm.elements.length; i++) {
    	if (myForm.elements[i].type === 'hidden' && 
    		myForm.elements[i].name !== 'query' && 
    		myForm.elements[i].name !== 'boxes2' &&
    		myForm.elements[i].name !== 'boxes11') {
			var elementName = myForm.elements[i].name;
			//alert(elementName);
			if (myForm.elements[i].value !== "") {
				elementName = elementName.substring(0,4) + skillCount;
				var elementId = 'ddr' + elementName;
				//alert('elementid = ' + elementId);
				skillCount++;
				//alert(elementId);
				//alert(elementName);
				document.getElementById(elementId).innerText = myForm.elements[i].value;
				
			}
			
		}
    }
	
	
}

async function processCheckboxForm2(id) {
	var myForm = document.getElementById(id);
	var params = new URLSearchParams();
	params.append("form", id)
    
    //Extract Each Element Value
    for (var i = 0; i < myForm.elements.length; i++) {
    	if (myForm.elements[i].type === 'hidden') {
			//alert(myForm.elements[i].value);
			params.append(myForm.elements[i].name, myForm.elements[i].value);
		}
    }
    let response = await fetch(fetchPath + 'PopupServlet', {
      method: "POST", 
      body: params
    }).then(response => {
		location.reload();
	}); 
	
	
}




async function processCalendarForm(id) {
	var myForm = document.getElementById(id);
	var params = new URLSearchParams();
	params.append("form", id)
    
    //Extract Each Element Value
    for (var i = 0; i < myForm.elements.length; i++) {
    	if (myForm.elements[i].type === 'hidden') {
			params.append(myForm.elements[i].name, myForm.elements[i].value);
		}
    }
    let response = await fetch(fetchPath + 'PopupServlet', {
      method: "POST", 
      body: params
    }); 
    for (var i = 0; i < myForm.elements.length; i++) {
    	if (myForm.elements[i].type !== 'button') {
			var elementId = 'ddr' + myForm.elements[i].name;
			document.getElementById(elementId).innerText = myForm.elements[i].value;
		}
    }
	
}

function daysAvailable() {
	
	var days = 0;
	
	for (var i = 1; i <= 28; i++) {
		var tdid = 'ddrCalDate' + i;
		var inpid = 'ddrCalDay' + i;
		var theinp = document.getElementById(inpid);
		
		if (theinp.value === '1') {
			days++;
		}
	}
	
	document.getElementById('ddrDaysAvail').innerText = days;
	document.getElementById('ddrDaysNotAvail').innerText = 28 - days;
	
	var pctYes = (days/28) * 100;
	var pctNo = ((28-days)/28) * 100;
	
	document.getElementById('ddrPctAvail').innerText = pctYes.toFixed(0);
	document.getElementById('ddrPctNotAvail').innerText = pctNo.toFixed(0);
	
}

async function processCalendarForm2(id) {
	var myForm = document.getElementById(id);
	var params = new URLSearchParams();
	params.append("form", id)
    
    //Extract Each Element Value
    for (var i = 0; i < myForm.elements.length; i++) {
    	if (myForm.elements[i].type === 'hidden') {
			params.append(myForm.elements[i].name, myForm.elements[i].value);
		}
    }
    let response = await fetch(fetchPath + 'PopupServlet', {
      method: "POST", 
      body: params
    }).then(response => {
		daysAvailable();
		location.reload();
	});
	
}



function prepopulateForm(id) {
	var myForm = document.getElementById(id);
	for (var i = 0; i < myForm.elements.length; i++) {
    	if ((myForm.elements[i].type !== 'button') && (myForm.elements[i].type !== 'hidden')) {
			var elementId = 'ddr' + myForm.elements[i].name;
			myForm.elements[i].value = document.getElementById(elementId).innerText;
		}
    }
}


function prepopulateUrlForm(id) {
	var myForm = document.getElementById(id);
	for (var i = 0; i < myForm.elements.length; i++) {
    	if ((myForm.elements[i].type !== 'button') && (myForm.elements[i].type !== 'hidden')) {
			var elementId = 'ddr' + myForm.elements[i].name +'a';
			if (myForm.elements[i].value !== null) {
				myForm.elements[i].value = document.getElementById(elementId).value;
			}
			
		}
    }
}


async function acceptNotification(id, index) {
	//alert("in processForm2()");
	var myForm = document.getElementById(id);
	var params = new URLSearchParams();
	params.append("form", id);
	params.append("accept", index);
    
    let response = await fetch(fetchPath + 'CRMServlet', {
      method: "POST", 
      body: params
    }).then(response => {
		//alert('response received');	
		location.reload();
		//alert('location reloaded');
	}); 
 
}


async function rejectNotification(id, index) {
	//alert("in processForm2()");
	var myForm = document.getElementById(id);
	var params = new URLSearchParams();
	params.append("form", id);
	params.append("reject", index);
    
    let response = await fetch(fetchPath + 'CRMServlet', {
      method: "POST", 
      body: params
    }).then(response => {
		//alert('response received');	
		location.reload();
		//alert('location reloaded');
	}); 
 
}


async function viewNotification(id, index) {
	//alert("in processForm2()");
	var myForm = document.getElementById(id);
	var params = new URLSearchParams();
	params.append("form", id);
	params.append("view", index);
    
    let response = await fetch(fetchPath + 'CRMServlet', {
      method: "POST", 
      body: params
    }).then(response => {
		//alert('response received');	
		location.reload();
		//alert('location reloaded');
	}); 
 
}





