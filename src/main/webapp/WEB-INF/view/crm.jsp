<%@ 
page 
language="java" 
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"
import ="jakarta.servlet.http.*,
	 java.util.*,
	 com.teamwrkr.app.dto.*,
	 com.teamwrkr.app.manager.*,
	 com.teamwrkr.app.util.*,
	 com.teamwrkr.app.service.*"
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Teamwrkr - Contacts</title>
<link href="/tw1/img/favicon.ico" rel="shortcut icon" type="image/x-icon" />
<link rel="stylesheet" href="/tw1/css/twddr2.css">
<link rel="stylesheet" href="/tw1/css/landing.css">
<link rel="stylesheet" href="/tw1/css/twcrm.css">
<link rel="stylesheet" href="/tw1/css/menubar.css">
<script defer src="https://widget-js.cometchat.io/v3/cometchatwidget.js"></script>
<script src="/tw1/js/crm.js"></script>
<script src="/tw1/js/chat.js"></script>
<script src="/tw1/js/mb.js"></script>
<script src="/tw1/js/forms.js"></script>
<script src="/tw1/js/ddr1.js"></script>
<script src="/tw1/js/ddr2.js"></script>

</head>
<body>
<%
System.out.println("$^$^$^$^$^$^$^$^$^$^$^$^$^$^$^/=====[ddr.jsp]=====\\^$^$^$^$^$^$^$^$^$^$^$^$^$^$^$");

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
 * Set the user's measurable skills to blank to avoid
 * the dreaded NullPointerException on page refresh.
 */
if (profile.getUserSkill1() == null) {profile.setUserSkill1("");}
if (profile.getUserSkill2() == null) {profile.setUserSkill2("");}
if (profile.getUserSkill3() == null) {profile.setUserSkill3("");}

/*
 * Establish the default image source for the user avatar.
 * If the user has upload their avatar, display it instead.
 */
String photoSrc = "";

if (profile.getPhotoImg() != null) {
	photoSrc = "/tw1/img/" + profile.getPhotoImg();
}
else {
	photoSrc = "/tw1/img/upload-photo-default-130x130-a.png";
}

/*
 * Set the avatar background photo, if applicable.
 */
boolean avatarBgPhoto = false; 
if (profile.getPhotoImg() != null) {
	avatarBgPhoto = true;
}

 /*
  * Get default social media URLs.
  */
String url1 = (profile.getUrl1() == null) ? "" : profile.getUrl1();
String url2 = (profile.getUrl2() == null) ? "" : profile.getUrl2();
String url3 = (profile.getUrl3() == null) ? "" : profile.getUrl3();
String url4 = (profile.getUrl4() == null) ? "" : profile.getUrl4();
String url5 = (profile.getUrl5() == null) ? "" : profile.getUrl5();
String url6 = (profile.getUrl6() == null) ? "" : profile.getUrl6();

/*
 * Null check the state field of the user Profile as
 * it is not required for user's residing outside the USA.
 */
if (profile.getState() == null) {
	profile.setState("  ");
}

/*
 * Count users notifications here.
 */
int notices = 0;

List<Notification> notificationList = NotificationManager.getAllActiveNotificatonsFor(twid);


String notifications = "";
if (notices > 0) {
	notifications = Integer.toString(notices);
}

/*
 * Count users messages here.
 */
int chats = 0;

List<Message> messageList = MessageManager.getAllUnreadMessagesFor(twid);

String messages = "";
if (chats > 0) {
	messages = Integer.toString(chats);
}


/*
 * Count users notifications here.
 */
int cts = 0;

//List<Contact> contactList = ContactManager.getAllContacts(twid);
List<Contact> contactList = ContactManager.getAllContacts();

String contacts = "";
if (cts > 0) {
	contacts = Integer.toString(cts);
}


%>
<%@include file="menubar.jsp"%> 


<h3>TEAMWRKR CONTACTS</h3>

<div id="crm-div">

<div id="crm-div-inner">

<div id="crm-div-th">
<div id="crm-div-th-inner">
<div class="crm-th-row">
<div class="crm-table-stage">STAGE</div>
<div class="crm-table-status">STATUS</div>
<div class="crm-table-contact-name">CONTACT NAME</div>
<div class="crm-table-contact-profile">CONTACT INFORMATION</div>
<div class="crm-table-notes">YOUR NOTES</div>
<div class="crm-table-actions">OPTIONS TO ENGAGE NOW</div>
</div>
</div>
</div>


<div id="crm-div-td">

<div id="crm-div-td-inner">
<%
for (int i = 0; i < contactList.size(); i++) {
	
	Contact contact = (Contact) contactList.get(i);
	
	String stage = ContactUtils.translateStage(contact.getStage());
	int missionId = contact.getMissionId();
	//Mission mission = new Mission();
	//mission.setMissionId(missionId);
	//Mission mission0 = (Mission) DataService.selectOne(mission);
	
	String status = "PENDING";
	String name = "";
	Profile pro = new Profile();
	pro.setTwid(contact.getTwid1());
	Profile pro1 = (Profile) DataService.selectOne(pro);
	name = pro1.getFirstName() + " " + pro1.getLastName();
	String contactInfo = "";
	String notes = "";
%>
<div class="crm-td-row">
<div class="crm-table-stage"><%=stage %></div>
<div class="crm-table-status"><%=status %></div>
<div class="crm-table-contact-name"><%=name %></div>
<div class="crm-table-contact-profile" onclick="showIframe2('profileframe');"><%=name %></div>
<div class="crm-table-notes"><%=notes %></div>
<div class="crm-table-actions">
<button 
id="crm-button-msg-<%=i %>" 
class="crm-action-button"
type="button"
onclick="openChatWindow();">
Chat
</button>
<button 
id="crm-button-email-<%=i %>" 
class="crm-action-button"
onclick="openPopup('crm-email-pop');">
Email
</button>
<button 
id="crm-button-call-<%=i %>" 
class="crm-action-button" 
disabled>
Call
</button>
<button 
id="crm-button-sms-<%=i %>" 
class="crm-action-button" 
disabled>
SMS
</button>
</div>
</div>
<%
}
%>

</div>


</div>


</div>


</div>



<form id="ddrIframeForm1" name="formIframe1">
<iframe id="postframe" style="display: none;" src="/tw1/post1.jsp" scrolling="no"></iframe>
<iframe id="profileframe" style="display: none;" src="/tw1/SiteServlet?query=9&twid=<%=twid %>" scrolling="no"></iframe>
</form>


<div id="crm-email-pop">
<form id="crmPopFormEmail" name="formcrm1" class="popup">
<input type="hidden" name="query" value="20" />
<input type="hidden" name="twid" value="<%=twid %>" />
<input type="hidden" id="crm-email-to" name="email" value="" />
<h6 class="formheader">Email Contact</h6>
<hr/>
<p class="popupInst">
This form will draft an email for you to send to a contact who has identified you as a match for a job opening.
It will create a template which will be previewed below. In addition, you may add up to 256 characters of free
text to communicate anything that you feel the template is lacking.
</p>
<hr/>
<div style="background: rgba(200, 200, 188, 1); font-weight: bold; font-style: italic;">
<p>
Thank you for your interest in potentially retaining me to fill your open position.
</p>
<p>
I have received and accepted the notification that was automatically sent to me by the Teamwrkr Intelligent 
Job Matching system. This indicates that I am amenable to further discussing the opportunity with the intent
of determining if it is a good fit for both you and I.
</p>
<p>
As a result of my accepting the notification, our contact information was added to each others' Teamwrkr Contact list.
The list entry has been added to facilitate inter-system communication between us in the hopes that we can 
agree on the terms of the opportunity and I can provide excellent work for you.
</p>
<p>
The notification summarized the details of the position, but I still have the following comments/questions/concerns:
</p>
<p>
Your questions will appear here.
</p>
</div>
<textarea class="formtextarea3" maxlength="256" name="commentsQuestionsConcerns" ></textarea><br/>
<button type="button" class="popupsubmit" onclick="processForm2('crmPopFormEmail'); closePopup('crm-email-pop');">Send</button>
<button type="button" class="popupclose" onclick="closePopup('crm-email-pop')">Cancel/Close</button>
</form>
</div>





<%@include file="mbhelp.jsp"%>


</body>
</html>