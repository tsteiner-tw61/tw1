/**
 * rolo.js
 * 
 * Javascript functions related to the user profile rolodex.
 * 
 * @author 	Ted E. Steiner
 * @date 	06.01.2023
 */

/**
 * URL path to the Java servlet code.
 */
var fetchPath = 'http://localhost:8080/tw1/';



/**
 * Update the rolodex by incrementing or decrementing
 * the user profile index to be displayed.
 */
async function updateRolodex(id, direction, query) {
	
	var myForm = document.getElementById(id);
	
	var params = new URLSearchParams();
	params.append("form", id)
    //Extract Each Element Value
    for (var i = 0; i < myForm.elements.length; i++) {
		
		if (myForm.elements[i].name === 'rolo-direction') {
			myForm.elements[i].value = direction;
		}
		if (myForm.elements[i].name === 'rolo-query') {
			myForm.elements[i].value = query;
		}
		params.append(myForm.elements[i].name, myForm.elements[i].value);
    }
    let response = await fetch(fetchPath + 'RolodexServlet', {
        method: "POST", 
        body: params
      }).then(response => {
          location.reload();
      }); 
   
}



/**
 * Filter rolodex content with hashtag subspecialty search
 * buttons, appearing directly below the rolodex.
 */
async function filterRolodex(id, filter, query) {
	
	var myForm = document.getElementById(id);
	
	var params = new URLSearchParams();
	params.append("form", id)
	
    //Extract Each Element Value
    for (var i = 0; i < myForm.elements.length; i++) {
		if (myForm.elements[i].name === 'rolo-filter') {
			myForm.elements[i].value = filter;
		}
		if (myForm.elements[i].name === 'rolo-query') {
			myForm.elements[i].value = query;
		}
		params.append(myForm.elements[i].name, myForm.elements[i].value);
    }
    let response = await fetch(fetchPath + 'RolodexServlet', {
        method: "POST", 
        body: params
      }).then(response => {
          location.reload();
      }); 
   
}