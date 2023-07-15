/**
 * Javascript for the DDR.
 * 
 * @author Ted E. Steiner
 * @date 3.11.2032
 */


/*
 * This actually opens the div in which the form is
 * displayed, not the form itself.
 */
function openPopup(id) {
  document.getElementById(id).style.display = "block";
}



/*
 * This actually closes the div in which the form is
 * displayed, not the form itself.
 */
function closePopup(id) {
  document.getElementById(id).style.display = "none";
}

/*
 * Display the contextual hover help.
 */
function openTooltip(id) {
	document.getElementById(id).style.display = "block";
}

/*
 * Hide the contextual hover help.
 */
function closeTooltip(id) {
	document.getElementById(id).style.display = "none";
}


/*
 * Toggle calendar entries.
 */
function toggleAvailability(id1, id2) {
	
	var table = document.getElementById('minicalendar');
	var td = document.getElementById(id1);
	//alert (td.style.background);
	if (td.style.background === 'rgb(0, 187, 0)') {
		td.style.background = 'rgb(255, 0, 0)';
	}
	else {
		td.style.background = 'rgb(0, 187, 0)';
	}
	
	var inp = document.getElementById(id2);
	//alert(inp.value);
	if (inp.value === '1') {
		inp.value = '0';
	}
	else {
		inp.value = '1';
	}
	
}


function displayAvailability() {
	
	for (var i = 1; i <= 28; i++) {
		var tdid = 'ddrCalDate' + i;
		var inpid = 'ddrCalDay' + i;
		var thetd = document.getElementById(tdid);
		var theinp = document.getElementById(inpid);
		if (theinp.value === '0') {
			thetd.style.background = 'rgb(255, 0, 0)';
		}
		if (theinp.value === '1') {
			thetd.style.background = 'rgb(0, 187, 0)';
		}
	}
	
}







function setAvailability() {
	
	//alert("hi");
	
	var yes = document.getElementById('ddrAvailYes');
	
	var days = 0; 
	
	for (var i = 1; i <= 28; i++) {
		
		var id1 = 'ddrCalDate' + i;
		var id2 = 'ddrCalDay' + i;
		
		var td = document.getElementById(id1);
		var inp = document.getElementById(id2);
		
		if (yes.checked === true) {
			//alert("true");
			td.style.background = 'rgb(0, 187, 0)';
			inp.value = '1';
			days++;
		}
		else {
			//alert("false");
			td.style.background = 'rgb(255, 0, 0)';
			inp.value = '0';
		}
	}
	
	document.getElementById('ddrDaysAvail').innerText = days;
	document.getElementById('ddrDaysNotAvail').innerText = 28 - days;
	
	var pctYes = (days/28) * 100;
	var pctNo = ((28-days)/28) * 100;
	
	document.getElementById('ddrPctAvail').innerText = pctYes.toFixed(0);
	document.getElementById('ddrPctNotAvail').innerText = pctNo.toFixed(0);
	
	
}

function limit5a(j) {
	

	var sum = 0;
	
	for (var i = 0; i < document.formdash5.ckb.length; i++) {

		if (document.formdash2.ckb[i].checked) {
			sum = sum + 1;
			assFuckMyAss3a(j);
		}
		//document.getElementById("msg").innerHTML="Sum :"+ sum;

		if (sum > 3) {
			sum = sum - 1;
			document.formdash2.ckb[j].checked = false;
			assFuckMyAss3a(j);
			return false;
		}
		//document.getElementById("msg").innerHTML="Sum :"+ sum;
	}
}


function limit3a(j) {
	

	var sum = 0;
	
	for (var i = 0; i < document.formdash2.ckb.length; i++) {

		if (document.formdash2.ckb[i].checked) {
			sum = sum + 1;
			assFuckMyAss3a(j);
		}
		//document.getElementById("msg").innerHTML="Sum :"+ sum;

		if (sum > 3) {
			sum = sum - 1;
			document.formdash2.ckb[i].checked = false;
			assUnfuckMyAss3a(j);
			return false;
		}
		//document.getElementById("msg").innerHTML="Sum :"+ sum;
	}
}



function assFuckMyAss3a(j) {
	
	var id = 'hard' + j;
	var hidden = document.getElementById(id);
	
	hidden.value = document.formdash2.ckb[j-1].value;
	//alert('cbk[' + (j-1) + ']');
	//alert('hidden.value=' + hidden.value);
	
}



function limit5x(j) {

	var sum = 0;
	
	for (var i = 0; i < document.formdash5.opt.length; i++) {

		if (document.formdash5.opt[i].selected) {
			sum = sum + 1;
			assFuckMyAss5x(j);
		}
		//document.getElementById("msg").innerHTML="Sum :"+ sum;

		if (sum > 1) {
			sum = sum - 1;
			document.formdash5.opt[j].selected = false;
			assUnfuckMyAss5x(j);
			return false;
		}
		//document.getElementById("msg").innerHTML="Sum :"+ sum;
	}
}
function assFuckMyAss5x(j) {
	
	var id = 'hd' + j;
	var hidden = document.getElementById(id);
	
	hidden.value = document.formdash5.opt[j-1].value;
	//alert('cbk[' + (j-1) + ']');
	//alert('hidden.value=' + hidden.value);
	
}
function assUnfuckMyAss5x(j) {
	
	var id = 'hd' + j;
	var hidden = document.getElementById(id);
	
	hidden.value = "";
	//alert('hidden.value=' + hidden.value);
	
}

function limit5z(j) {
	
	var total = 0;

	for(var i = 0; i < document.formdash5.opt.length; i++) {
	
	if(document.formdash5.opt[i].selected)
		total = total + 1;
 	}
	
	if (total > 2) {
		document.formdash5.opt[i].selected = false ;
		return false;
	}

}


function assUnfuckMyAss3a(j) {
	
	var id = 'hard' + j;
	var hidden = document.getElementById(id);
	
	hidden.value = "";
	//alert('hidden.value=' + hidden.value);
	
}


function limit3b(j) {
	

	var sum = 0;
	
	for (var i = 0; i < document.formdash11.ckb.length; i++) {

		if (document.formdash11.ckb[i].checked) {
			sum = sum + 1;
			assFuckMyAss3b(j);
		}
		//document.getElementById("msg").innerHTML="Sum :"+ sum;

		if (sum > 3) {
			sum = sum - 1;
			document.formdash11.ckb[i].checked = false;
			assUnfuckMyAss3b(j);
			return false;
		}
		
		
		//document.getElementById("msg").innerHTML="Sum :"+ sum;
	}
}



function assFuckMyAss3b(j) {
	
	var id = 'soft' + j;
	var hidden = document.getElementById(id);
	
	hidden.value = document.formdash11.ckb[j-1].value;
	//alert('cbk[' + (j-1) + ']');
	//alert('hidden.value=' + hidden.value);
	
}


function assUnfuckMyAss3b(j) {
	
	var id = 'soft' + j;
	var hidden = document.getElementById(id);
	
	hidden.value = "";
	//alert('hidden.value=' + hidden.value);
	
}

function openTab(id) {
	var url = document.getElementById(id).value;
	//alert(url);
    window.open(url, "_blank");
}


function limitSelections() {

	var errormsg = document.getElementById('ddrPop5Msg');
	
	let invalidSelects = 0;
	let collection1 = document.getElementById('ddrHardskillsexp').selectedOptions;
	let coll1Length = collection1.length;
	if (collection1.length > 2) {
		for (let j = 0; j < coll1Length; j++) {
			collection1[0].selected = false;
		}
		invalidSelects++;
		errormsg.innerHTML = "Please select only 2 measurable skills.";
	}
	let collection2 = document.getElementById('ddrSoftskillsexp').selectedOptions;
	let coll2Length = collection2.length;
	if (collection2.length > 2) {
		for (let j = 0; j < coll2Length; j++) {
			collection2[0].selected = false;
		}
		invalidSelects++;
		errormsg.innerHTML = "Please select only 2 interpersonal skills.";
	}
    
}


/**
 * Clears and hides the select state dropdown
 * whenever a country other than USA is selected
 * in the select country dropdown.
 */
function toggleSelectState() {
	
	let countrySelect = document.getElementById('ddr-country');
	let stateSelect = document.getElementById('ddr-sp');
	
	if (countrySelect.value === 'USA') {
		stateSelect.value = "";
		stateSelect.selectedIndex = 0;
		stateSelect.style.display = 'inline';
	}
	else {
		stateSelect.value = '';
		stateSelect.selectedIndex = 0;
		stateSelect.style.display = 'none';
	}
	
}


function setSelectedNotTwid(twid) {
	
	var stwid = document.getElementById('selectedTwid');
	
	var pframe = document.getElementById('profileframe');
	
	var srctext = '/tw1/SiteServlet?query=9&twid=' + twid;
	
	pframe.src = srctext;
	
}
