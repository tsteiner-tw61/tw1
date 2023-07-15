/**
 * Gets subspecialties lists associated with specialties.
 * To be used for dynamic form inputs once these specialties
 * are added to the onboarding wizard.
 * 
 * NOTE: currently (6.22.2023) only Marketing is available
 */
function getSubcategories() {
	
	var mktgSubs = ['Art/Illustration', 'Branding', 'Copywriting/Editing', 'Graphic Design', 'Video Editing', 'UX/UI Design'];
	var svcsSubs = ['Virtual Assistant', 'Coaching', 'Consulting'];
	var legalSubs = ['Attorney', 'Paralegal'];
	var acctSubs = ['Bookkeeping', 'Finance/Investments', 'Tax Accountant'];
	var hlthSubs = ['Counseling/Mental Health', 'Nurse', 'Doctor', 'Fitness'];
	
	var cat1 = document.getElementById('post-cat1');
	var cat2 = document.getElementById('post-cat2');
	
	console.log("cat2.length=" + cat2.length);
	var len = cat2.length;
	for (var i = 0; i < len; i++) {
		console.log("option[" + i + "]=" );
		cat2.remove(0);
	}
	
	
	if (cat1.value === 'Marketing') {
		for (var i = 0; i < mktgSubs.length; i++) {
			var opt = document.createElement('option');
    		opt.value = mktgSubs[i];
    		opt.innerHTML = mktgSubs[i];
    		cat2.appendChild(opt);
		}
	}
	if (cat1.value === 'Services') {
		for (var i = 0; i < svcsSubs.length; i++) {
			var opt = document.createElement('option');
    		opt.value = svcsSubs[i];
    		opt.innerHTML = svcsSubs[i];
    		cat2.appendChild(opt);
		}
	}
	if (cat1.value === 'Legal') {
		var j = 0;
		for (var i = 0; i < legalSubs.length; i++) {
			var opt = document.createElement('option');
    		opt.value = j;
    		opt.innerHTML = legalSubs[i];
    		cat2.appendChild(opt);
    		j++;
		}
	}
	if (cat1.value === 'Accounting/Finance') {
		for (var i = 0; i < acctSubs.length; i++) {
			var opt = document.createElement('option');
    		opt.value = acctSubs[i];
    		opt.innerHTML = acctSubs[i];
    		cat2.appendChild(opt);
		}
	}
	if (cat1.value === 'Healthcare') {
		for (var i = 0; i < hlthSubs.length; i++) {
			var opt = document.createElement('option');
    		opt.value = acctSubs[i];
    		opt.innerHTML = hlthSubs[i];
    		cat2.appendChild(opt);
		}
	}
}


/**
 * Populate the hidden input that allow the Post A Job
 * form to submit both the selected index and the actual
 * text associated with the selection to the servlet.
 */
function saveCat2TextValue() {
	
	var cat2 = document.getElementById('post-cat2');
	var cat2text  = document.getElementById('post-cat2-text');
	
	var mktgSubs = ['Art/Illustration', 'Branding', 'Copywriting/Editing', 'Graphic Design', 'Video Editing', 'UX/UI Design'];
	
	for (var i = 0; i < mktgSubs.length; i++) {
		if (cat2.selectedIndex === i) {
			cat2text.innerText = mktgSubs[i];
		}
	}
	
}


/**
 * Populates the first measurable skill <select>
 * in the full Post A Job form. Does not apply to the
 * Post A Job wizard, as that is populated via JSP
 * code.
 */
function populateMeasurableSkillsSelect1() {
	
	console.log("in function");
	
	var artIllSubs = ['Constructive Drawing', 'Realistic Drawing'];
	var brandingSubs = ['Brand Management', 'Brand Positioning', 'Brand Strategy', 'Competitive Analysis'];
	var copyEditSubs = ['Editing', 'Originality', 'Research', 'SEO', 'Social Media', 'Technical'];
	var graphicDesignSubs = ['Branding', 'Design Principles', 'Ideation Skills', 'Technology', 'Typography'];
	var videoEditSubs = ['Organization', 'Self-Motivation', 'Technical'];
	var uxuiSubs = ['Creativity and Design', 'Design Software', 'Industry Knowledge', 'Software Development Knowledge', 'Technical Skills', 'User Research', 'Visual Design'];
	
	var category = document.getElementById('post-cat2');
	var hskillSelect1 = document.getElementById('post-hskill1');
	
	var len = hskillSelect1.length;
	
	for (var i = 1; i < len; i++) {
		console.log("remove option[" + i + "]=" );
		hskillSelect1.remove(1);
	}
	
	
	if (category.selectedIndex === 0) {
		for (var i = 0; i < artIllSubs.length; i++) {
			var opt = document.createElement('option');
    		opt.value = artIllSubs[i];
    		opt.innerHTML = artIllSubs[i];
    		hskillSelect1.appendChild(opt);
		}
	}
	if (category.selectedIndex === 1) {
		for (var i = 0; i < brandingSubs.length; i++) {
			var opt = document.createElement('option');
    		opt.value = brandingSubs[i];
    		opt.innerHTML = brandingSubs[i];
    		hskillSelect1.appendChild(opt);
		}
	}
	if (category.selectedIndex === 2) {
		for (var i = 0; i < copyEditSubs.length; i++) {
			var opt = document.createElement('option');
    		opt.value = copyEditSubs[i];
    		opt.innerHTML = copyEditSubs[i];
    		hskillSelect1.appendChild(opt);
		}
	}
	if (category.selectedIndex === 3) {
		for (var i = 0; i < graphicDesignSubs.length; i++) {
			var opt = document.createElement('option');
    		opt.value = graphicDesignSubs[i];
    		opt.innerHTML = graphicDesignSubs[i];
    		hskillSelect1.appendChild(opt);
		}
	}
	if (category.selectedIndex === 4) {
		for (var i = 0; i < videoEditSubs.length; i++) {
			var opt = document.createElement('option');
    		opt.value = videoEditSubs[i];
    		opt.innerHTML = videoEditSubs[i];
    		hskillSelect1.appendChild(opt);
		}
	}
	if (category.selectedIndex === 5) {
		for (var i = 0; i < uxuiSubs.length; i++) {
			var opt = document.createElement('option');
    		opt.value = uxuiSubs[i];
    		opt.innerHTML = uxuiSubs[i];
    		hskillSelect1.appendChild(opt);
		}
	}
	
	
}



/**
 * Manages the measurable skills <select> items
 * dynamically, removing any selected choice from 
 * subsequent <select> elements.
 */
function manageMeasurableSkillsSelects(id) {
	
	var hskillSelect1 = document.getElementById('post-hskill1');
	var hskillSelect2 = document.getElementById('post-hskill2');
	var hskillSelect3 = document.getElementById('post-hskill3');
	
	var currentSelect = document.getElementById(id);
	var len = currentSelect.length;
	
	if (id === 'post-hskill1') {
		var len2 = hskillSelect2.length;
		for (var i = 0; i < len2; i++) {
			//console.log("remove option[" + i + "]=" );
			hskillSelect2.remove(0);
		}
		var len3 = hskillSelect3.length;
		for (var i = 0; i < len3; i++) {
			//console.log("remove option[" + i + "]=" );
			hskillSelect3.remove(0);
		}
		var j = 0;
		for (var i = 0; i < len; i++) {
			if (i !== currentSelect.selectedIndex) {
				var opt = document.createElement('option');
    			opt.value = currentSelect.options[i].value;
    			opt.innerText = currentSelect.options[i].innerText;
    			//console.log("create option " + opt.innerText );
    			hskillSelect2.appendChild(opt);
			}
		}
		if (currentSelect.selectedIndex === 0) {
			hskillSelect2.disabled = true;
			hskillSelect3.disabled = true;
		}
		else {
			hskillSelect2.disabled = false;
		}
		
	}
		
	if (id === 'post-hskill2') {
		var len3 = hskillSelect3.length;
		for (var i = 0; i < len3; i++) {
			//console.log("remove option[" + i + "]=" );
			hskillSelect3.remove(0);
		}
		for (var i = 0; i < len; i++) {
			if (i !== currentSelect.selectedIndex) {
				var opt = document.createElement('option');
    			opt.value = currentSelect.options[i].value;
    			opt.innerText = currentSelect.options[i].innerText;
    			//console.log("create option[" + j + "]=" );
    			hskillSelect3.appendChild(opt);
			}
		}
		if (currentSelect.selectedIndex === 0) {
			hskillSelect3.disabled = true;
		}
		else {
			hskillSelect3.disabled = false;
		}
		
	}

}



/**
 * Manages the interpersonal skills <select> items
 * dynamically, removing any selected choice from 
 * subsequent <select> elements.
 */
function manageInterpersonalSkillsSelects(id) {
	
	var sskillSelect1 = document.getElementById('post-sskill1');
	var sskillSelect2 = document.getElementById('post-sskill2');
	var sskillSelect3 = document.getElementById('post-sskill3');
	
	var currentSelect = document.getElementById(id);
	var len = currentSelect.length;
	console.log('currentSelect.selectedIndex=' + currentSelect.selectedIndex);
	
	if (id === 'post-sskill1') {
		var len2 = sskillSelect2.length;
		for (var i = 0; i < len2; i++) {
			//console.log("remove option[" + i + "]=" );
			sskillSelect2.remove(0);
		}
		var len3 = sskillSelect3.length;
		for (var i = 0; i < len3; i++) {
			//console.log("remove option[" + i + "]=" );
			sskillSelect3.remove(0);
		}
		for (var i = 0; i < len; i++) {
			if (i !== currentSelect.selectedIndex) {
				var opt = document.createElement('option');
    			opt.value = currentSelect.options[i].value;
    			opt.innerText = currentSelect.options[i].innerText;
    			//console.log("create option[" + i + "]=" );
    			sskillSelect2.appendChild(opt);
			}
		}
		if (currentSelect.selectedIndex === 0) {
			//console.log("enabling??a");
			sskillSelect2.disabled = true;
			sskillSelect3.disabled = true;
		}
		else {
			//console.log("enabling??b");
			sskillSelect2.disabled = false;
		}
		
	}
		
	if (id === 'post-sskill2') {
		var len3 = sskillSelect3.length;
		for (var i = 0; i < len3; i++) {
			//console.log("remove option[" + j + "]=" );
			sskillSelect3.remove(0);
		}
		for (var i = 0; i < len; i++) {
			if (i !== currentSelect.selectedIndex) {
				var opt = document.createElement('option');
    			opt.value = currentSelect.options[i].value;
    			opt.innerText = currentSelect.options[i].innerText;
    			//console.log("create option[" + j + "]=" );
    			sskillSelect3.appendChild(opt);
			}
		}
		if (currentSelect.selectedIndex === 0) {
			sskillSelect3.disabled = true;
		}
		else {
			sskillSelect3.disabled = false;
		}
		
	}

}



/**
 * Clears the rate amount input on the Post A Job
 * form when it contextually does not make sense for 
 * it to appear given the option selected for the
 * Compensation Type input.
 */
function clearRateAmount() {
	
	var rate = document.getElementById('post-rate');
	var rateSection = document.getElementById('post-p6');
	var comp = document.getElementById('post-comp');
	
	if (comp.selectedIndex === 2) {
		rate.removeAttribute("required");
		console.log("javascript");
		rate.value = '';
		rate.style.display = 'none';
		rateSection.style.display = 'none';
	}
	else {
		rateSection.style.display = 'block';
		rate.style.display = 'inline';
		rate.setAttribute("required");
	}

}



/**
 * Toggles skills <select> dropdown inputs between enabled
 * and disabled, depending upon currently saved user inputs
 * during the Post A Job wizard process.
 */
function enableSelectedSkills() {
	
	var sskillSelect1 = document.getElementById('post-sskill1');
	var sskillSelect2 = document.getElementById('post-sskill2');
	var sskillSelect3 = document.getElementById('post-sskill3');
	
	var hskillSelect1 = document.getElementById('post-hskill1');
	var hskillSelect2 = document.getElementById('post-hskill2');
	var hskillSelect3 = document.getElementById('post-hskill3');
	
	console.log('sskillSelect1 = ' + sskillSelect1.selectedIndex);
	console.log('sskillSelect2 = ' + sskillSelect2.selectedIndex);
	console.log('sskillSelect3 = ' + sskillSelect3.selectedIndex);
	
	console.log('hskillSelect1 = ' + hskillSelect1.selectedIndex);
	console.log('hskillSelect2 = ' + hskillSelect2.selectedIndex);
	console.log('hskillSelect3 = ' + hskillSelect3.selectedIndex);
	
	if (sskillSelect2.selectedIndex > 0) {
		sskillSelect2.disabled = false;
	}
	if (sskillSelect3.selectedIndex > 0) {
		sskillSelect3.disabled = false;
	}
	if (hskillSelect2.selectedIndex > 0) {
		hskillSelect2.disabled = false;
	}
	if (hskillSelect3.selectedIndex > 0) {
		hskillSelect3.disabled = false;
	}
	
}



/**
 * Set the selectedIndex for skills on form load.
 */
function setSelectedIndex(id, comp) {
	
	var compSelect = document.getElementById(id);
	compSelect.selectedIndex = comp;

}



function setSelects(cat2, skilla1, skilla2, skilla3, skillb1, skillb2, skillb3) {

	setSelectedIndexByValue('post-cat2', cat2);
	setSelectedIndexByValue('post-sskill1', skilla1);
	setSelectedIndexByValue('post-sskill2', skilla2);
	setSelectedIndexByValue('post-sskill3', skilla3);
	setSelectedIndexByValue('post-hskill1', skillb1);
	setSelectedIndexByValue('post-hskill2', skillb2);
	setSelectedIndexByValue('post-hskill3', skillb3);

}



/**
 * Uses the text value of a select item to choose
 * the correct selectedIndex for the item.
 */
function setSelectedIndexByValue(id, value) {

	var selectItem = document.getElementById(id);
	console.log(id);
	var len = selectItem.length;

	for (var i = 0; i < len; i++) {
		if (selectItem.options[i].value === value) {
			selectItem.selectedIndex = i;
		}
	}

}