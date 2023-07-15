/**
 * 
 */

 
function closeVideo() {
	
	var welcomeVideo = document.getElementById('welcome-video-container');
	
	welcomeVideo.style.display = 'none';
	
}


function accessCamera() {
	
	var player = document.getElementById('player');

  var handleSuccess = function (stream) {
    player.srcObject = stream;
  };

  navigator.mediaDevices
    .getUserMedia({audio: true, video: true})
    .then(handleSuccess);
	
	
}


