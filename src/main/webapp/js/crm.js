/**
 * 
 */
var fetchPath = 'http://localhost:8080/tw1/';


function selectCrmTab(id) {
	
	const tabs = ['crm-tab-1', 'crm-tab-2', 'crm-tab-3', 'crm-tab-4', 'crm-tab-5'];
	const tabcontents = ['crm-tab-contents-1', 'crm-tab-contents-2', 'crm-tab-contents-3', 
							'crm-tab-contents-4', 'crm-tab-contents-5'];
	const tabdescriptions = ['Teamwrkrs in the Marketing/Branding space',
								'contacts explicitly bookmarked by you',
								'contacts manually entered or imported by you',
								'important messages from Teamwrkr',
								'contacts you are actively engaged with at this time']
	for (var i = 0; i < 5; i++) {
		var tab = document.getElementById(tabs[i]);
		var tabcontent = document.getElementById(tabcontents[i]);
		var tabdesc = document.getElementById('crm-tab-description');
		if (tabs[i] === id) {
			tab.style.background = 'orange';
			tab.style.color = 'black';
			tab.style.fontWeight = 'bold';
			tabcontent.style.display = 'block';
			tabcontent.style.zIndex = '5';
			tabdesc.innerText = tabdescriptions[i];;
			
		}
		else {
			tab.style.background = 'rgb(111, 0, 255)';
			tab.style.color = 'white';
			tab.style.fontWeight = 'normal';
			tabcontent.style.display = 'none';
			tabcontent.style.zIndex = '2';
		}
		
	}
	var currentTab = document.getElementById(id);
	
	
}



