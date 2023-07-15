/**
 * 
 */

function toggleDropdownMenu() {
	var dropdownMenu = document.getElementById('mb-account-dropdown');
	if (dropdownMenu.style.display === 'none') {
		showDropdownMenu();
	}
	else {
		hideDropdownMenu();
	}
}

function showDropdownMenu() { 
	var dropdownMenu = document.getElementById('mb-account-dropdown');
	dropdownMenu.style.display = 'block';
}

function hideDropdownMenu() { 
	var dropdownMenu = document.getElementById('mb-account-dropdown');
	dropdownMenu.style.display = 'none';
}

function show(id) { 
	var tooltip = document.getElementById(id);
	tooltip.style.display = 'block';
}

function hide(id) { 
	var tooltip = document.getElementById(id);
	tooltip.style.display = 'none';
}