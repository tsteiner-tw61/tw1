/**
 * opps.js
 * 
 * Javascript functions related to the featured
 * opportunities slider.
 * 
 * @author 	Ted E. Steiner
 * @date 	06.29.2023
 */

/**
 * URL path to the Java servlet code.
 */
var fetchPath = 'http://localhost:8080/tw1/';



/**
 * Update the rolodex by incrementing or decrementing
 * the user profile index to be displayed.
 */
async function updateOppSlider(id, direction, query) {
	
	var myForm = document.getElementById(id);
	
	var params = new URLSearchParams();
	params.append("form", id);
	
    //Extract Each Element Value
    for (var i = 0; i < myForm.elements.length; i++) {
		if (myForm.elements[i].name === 'opps-direction') {
			myForm.elements[i].value = direction;
		}
		if (myForm.elements[i].name === 'opps-query') {
			myForm.elements[i].value = query;
		}
		params.append(myForm.elements[i].name, myForm.elements[i].value);
    }
    let response = await fetch(fetchPath + 'OpportunityServlet', {
        method: "POST", 
        body: params
      }).then(response => {
          location.reload();
      }); 
   
}



/**
 * Start a running timer for an individual job posting.
 * 
 * NOTE: currently deprecated (6.29.2023)
 */
function startTime() {
    var today = new Date();
    var h = today.getHours();
    var m = today.getMinutes();
    var s = today.getSeconds();
    m = formatTime(m);
    s = formatTime(s);
    document.getElementById('opps-timer-1').innerHTML =
    h + ":" + m + ":" + s;
    var t = setTimeout(startTime, 500);
}

/**
 * Format a component of the clock to show leading zero.
 * 
 * NOTE: currently deprecated (6.29.2023)
 */
function formatTime(i) {
    if (i < 10) {i = "0" + i};  // add zero in front of numbers < 10
    return i;
}