<%@ 
page 
language="java" 
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"
import ="jakarta.servlet.http.*,
	 java.util.*,
	 com.teamwrkr.app.dto.*,
	 com.teamwrkr.app.manager.*,
	 com.teamwrkr.app.service.*"
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Teamwrkr - Chat</title>
<link href="/tw1/img/favicon.ico" rel="shortcut icon" type="image/x-icon" />
<link rel="stylesheet" href="/tw1/css/landing.css">
<link rel="stylesheet" href="/tw1/css/menubar.css">
<link rel="stylesheet" href="/tw1/css/twchat.css">
<script defer src="https://widget-js.cometchat.io/v3/cometchatwidget.js"></script>
<script src="/tw1/js/mb.js"></script>
<script src="/tw1/js/forms.js"></script>
</head>
<body>

<%
int twid = 0;

/*
 * Get the user's teamwrkr id (twid).
 */
try {
	String tw = (String) request.getParameter("twid");
	twid = Integer.parseInt(tw);
}
catch (Exception e) {
	twid = (int) session.getAttribute("twid");
}
 
/*
 * Grab the user's profile from the database.
 * Helps with auto data refreshing.
 */
Profile profile = new Profile();
profile.setTwid(twid);
Profile p = (Profile) DataService.selectOne(profile);

/*
 * Get user points.
 */
int pts = PointsManager.getTotalPoints(p);

/*
 * For avatar initials generation and to suppress 
 * display of the word null on the DDR when something
 * goes wrong with fetching the user's name from the
 * database.
 */
boolean displayAvatarInitials = false;
String avatarInitials = "TW";
String fn = "";
String ln = "";
if (p != null && p.getFirstName() != null && p.getFirstName().length() > 0) {
	fn = p.getFirstName().substring(0,1).toUpperCase();
	displayAvatarInitials = true;
}
if (p != null && p.getLastName() != null && p.getLastName().length() > 0) {
	ln = p.getLastName().substring(0,1).toUpperCase();
	displayAvatarInitials = true;
}
if (displayAvatarInitials) {
	avatarInitials = fn + ln;
}

/*
 * Set the avatar background photo, if applicable.
 */
boolean avatarBgPhoto = false; 
String photoSrc = "/tw1/img/";
String defaultAvatarImg = "logo-on-black-001.png";
if (profile.getPhotoImg() != null) {
	avatarBgPhoto = true;
	photoSrc += profile.getPhotoImg();
}
else {
	photoSrc += defaultAvatarImg;
}

/*
 * Count notifications here.
 */
int notifications = 0;
String notices = "";
if (notifications > 0) {
	notices = Integer.toString(notifications);
}

/*
 * CometChat exclusively.
 */
String ccUserId = "tw" + twid;
String ccUserName = p.getLastName().toLowerCase() + p.getFirstName().toLowerCase() + twid;
String avatarUrl = "http://localhost:8080" + photoSrc; 
%>
<%@include file="menubar.jsp"%>
<!-- ---------------------- CHAT WIDGET IN EMBEDDED LAYOUT ---------------------- -->
<!-- --- PASTED DIRECTLY FROM https://www.cometchat.com/docs/chat-widgets/web --- -->

<div id="cometchat"></div>
	<script>
	window.addEventListener('DOMContentLoaded', (event) => {
		
		
		
		CometChatWidget.init({
			"appID": "2391170c64e58438",
			"appRegion": "us",
			"authKey": "46c651201bc5170ad2a331fcefb8bc26f9bc2377"
		}).then(response => {
			console.log("Initialization completed successfully");
			
			
				/* Create new user. */
				const user = new CometChatWidget.CometChat.User('<%=ccUserId %>');
				user.setName('<%=ccUserName %>');
				user.setAvatar('<%=avatarUrl %>');
				//user.setLink(profileLink);
				CometChatWidget.createOrUpdateUser(user).then((user) => {
				
				
			
			
							//You can now call login function.
							CometChatWidget.login({
								"uid": "<%=ccUserId %>"
							}).then((user) => {
								CometChatWidget.launch({
									"widgetID": "7fe6fea5-7f26-4e29-a8ee-7531520a0475",
									"target": "#cometchat",
									"roundedCorners": "true",
									"height": "800px",
									"width": "1200px",
									"defaultID": '<%=ccUserId %>', //default UID (user) or GUID (group) to show,
									"defaultType": 'user' //user or group
								});//ccw.launch
							}, error => {
								console.log("User login failed with error:", error);
								//Check the reason for error and take appropriate action.
							});//cometchatwidgetlogin.then resp
			
				
				});//createorupdateuser.then
			
			
		}, error => {
			console.log("Initialization failed with error:", error);
			//Check the reason for error and take appropriate action.
		});//cometchatwidgetinit.then resp
		
		
		
		
		
	}); //addeventlistener
	</script>
<form id="ddrIframeForm1" name="formIframe1">
<iframe id="postframe" style="display: none;" src="/tw1/post1.jsp" scrolling="no"></iframe>
</form>

<%@include file="mbhelp.jsp"%>

</body>
</html>