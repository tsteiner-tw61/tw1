/**
 * 
 */
function setSkillScore(elname, val) {
	
	var score = document.getElementsByName(elname)[0];
	score.value = val;
	
}

function highlightScore(elname, idx) {
	
	var score = document.getElementsByName(elname);
	for (var i = 0; i < score.length; i++) {
		if (i === idx) {
			score[i].style = 'background: rgba(0, 255, 0, 1);';
		}
		else {
			score[i].style = 'background: rgba(0, 255, 0, 0.15);';
		}
	}
	
}


function highlightOverallScore(elname, idx) {
	
	var score = document.getElementsByName(elname);
	for (var i = 0; i < score.length; i++) {
		if (i === idx) {
			score[i].style = 'background: rgba(255, 0, 0, 1);';
		}
		else {
			score[i].style = 'background: rgba(255, 0, 0, 0.15);';
		}
	}
	
}
