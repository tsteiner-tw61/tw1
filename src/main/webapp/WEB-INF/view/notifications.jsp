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
<title>Teamwrkr - Notifications</title>
<link href="/tw1/img/favicon.ico" rel="shortcut icon" type="image/x-icon" />
<link rel="stylesheet" href="/tw1/css/twddr2.css">
<link rel="stylesheet" href="/tw1/css/landing.css">
<link rel="stylesheet" href="/tw1/css/twnot.css">
<link rel="stylesheet" href="/tw1/css/menubar.css">
<script src="/tw1/js/ddr1.js"></script>
<script src="/tw1/js/ddr2.js"></script>
<script src="/tw1/js/crm.js"></script>
<script src="/tw1/js/mb.js"></script>
<script src="/tw1/js/forms.js"></script>
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

List<Contact> contactList = ContactManager.getAllContacts(twid);

String contacts = "";
if (cts > 0) {
	contacts = Integer.toString(cts);
}


SiteConstants sc = new SiteConstants();


List<Notification> ijmNotices = IntelligentJobMatchManager.getNotificationsFor(p);

%>
<%@include file="menubar.jsp"%> 
<h3>TEAMWRKR NOTIFICATIONS</h3>

<div id="notices-div">



<div id="not-div-inner">
<div id="not-div-th">
<div id="not-div-th-inner">
<div class="not-th-row">
<div class="not-table-received">RECEIVED</div>
<div class="not-table-from">FROM</div>
<div class="not-table-subject">SUBJECT</div>
<div class="not-table-actions">ACTIONS</div>
<div class="not-table-expired">EXPIRES AT</div>
</div>
</div>
</div>



<div id="not-div-td">

<div id="not-div-td-inner">
<form id="notificationsForm1" name="notifications1">
<input type="hidden" name="query" value="24" />
<input type="hidden" name="twid" value="<%=twid %>" />
<%
//for (int i = 0; i < ijmNotices.size(); i++) {
	//Notification notification = ijmNotices.get(i);
	//String from = "PHIL@TEAMWRKR";
	//if (notification.getType() == sc.NOTIFICATION_TYPE_JOB && notification.getMissionId() != 0) {
	//Mission m0 = new Mission();
	//	m0.setMissionId(notification.getMissionId());
	//	Mission mission1 = (Mission) DataService.selectOne(m0);
		
		
//}
	
int current = 0;
try {
	
	String testing = (String) session.getAttribute("currentNotificationIndex");
	System.out.println("testing=" + testing);
	current = Integer.parseInt(testing);
	//current = (int) session.getAttribute("currentNotificationIndex");
}
catch (Exception e) {
	System.out.println("getAttribute(currentNotificationIndex) IS NULL");
}


List<String> nMessages = new ArrayList<String>();	
	
List<Notification> nList = NotificationManager.getAllNotifications();

String msgHtml = "";
if (nList.size() > 0) {
	 msgHtml = nList.get(current).getMessage();
}

int nindex = 0;
for (int i = 0; i < nList.size(); i++) {
	Notification notification = nList.get(i);
	
	if (notification.getStatus() == sc.NOTIFICATION_STATUS_PENDING) {
	
		String from = "TEAMWRKR SYSTEM";
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(notification.getCreatedDate());
		cal.add(Calendar.DATE, notification.getDaysValid());
		String expiresDate = SqlUtils.convertToString(cal.getTime());
		
		if (notification.getType() == sc.NOTIFICATION_TYPE_JOB && notification.getMissionId() != 0) {
			Mission m0 = new Mission();
			m0.setMissionId(notification.getMissionId());
			Mission mission1 = (Mission) DataService.selectOne(m0);
			from = mission1.getManager();
			
			String htmltable = NotificationManager.createMissionHTMLTable(mission1);
			
			boolean disabled = false;
			String fromOnclickAttribute = "showIframe2('profileframe');";
			
			nMessages.add(htmltable);
		
%>
<div id="not-td-row-<%=nindex %>" class="not-td-row">
<div class="not-table-received"><%=notification.getCreatedDate() %> @ <%=notification.getCreatedTime() %> EST</div>
<div class="not-table-from" onclick="setSelectedNotTwid('<%=notification.getTwid1() %>'); <%=fromOnclickAttribute %>"><span style="cursor: pointer;"><%=from %></span></div>
<div class="not-table-subject"><%=notification.getSubject() %></div>
<div class="not-table-actions">
<button 
id="not-button-view-<%=nindex %>" 
class="not-action-button"
type="button"
onclick="viewNotification('notificationsForm1','<%=nindex %>');">
View
</button>
<button 
id="not-button-accept-<%=nindex %>" 
name="notAccept<%=nindex %>" 
class="not-action-button" 
type="button" 
onclick="acceptNotification('notificationsForm1','<%=notification.getNotId() %>');">
Accept
</button>
<button 
id="not-button-reject-<%=nindex %>" 
class="not-action-button" 
type="button"
onclick="rejectNotification('notificationsForm1','<%=notification.getNotId() %>');">
Reject
</button>
</div>
<div class="not-table-expired"><%=expiresDate %> @ <%=notification.getCreatedTime() %> EST</div>
</div>
<%
			nindex++;
		}
	}
}
%>
</form>
</div>
</div>
</div>
</div>


<%

String noticeMessage = "";
Notification nm1 = new Notification();
if (nMessages.size() > 0) { 
	noticeMessage = nMessages.get(current);
	nm1 = (Notification) nList.get(current);
}





%>

<div id="notice-div">
<div class="notice-div-inner">
<div class="notice-text">
<h4><%=nm1.getSubject() %></h4>
<p class="notice-div-head-p">
You are receiving this notification because you have been identified as the best available match for a job posted by another member
of the Teamwrkr community. Please review the details below, and if you wish to find out more about this position, please click the 
Accept button above. Otherwise, please select the Reject button, so another member of the community can be presented with the opportunity 
to fill this role. If you take no action, the offer will expire 24 hours after it was originally presented to you.
</p>
<%=noticeMessage %>
<p class="notice-div-foot-p">
*By clicking Accept, you are not committing to filling the role, only to engaging in further communication with the community member 
who posted the role on our site. If you accept, the notification above will be deleted, and the job posters contact information will 
be added to your Teamwrkr contacts, which can be viewed by click the WRK tab in the main menu. From that screen, you will be provided 
with several ways to directly contact him/her to discuss the position further.
</p>
</div>

</div>
</div>

<form id="ddrIframeForm1" name="formIframe1">
<input type="hidden" id="selectedTwid" name="pickedTwid" value="" />
<iframe id="postframe" style="display: none;" src="/tw1/post1.jsp" scrolling="no"></iframe>
<iframe id="profileframe" style="display: none;" src="/tw1/SiteServlet?query=9&twid=<%=nm1.getTwid1() %>" scrolling="no"></iframe>
</form>



<%@include file="mbhelp.jsp"%>


</body>
</html>
