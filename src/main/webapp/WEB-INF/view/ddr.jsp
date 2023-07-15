<%@ 
page 
language="java" 
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"
import ="jakarta.servlet.http.*,
	 java.util.Calendar,
	 java.util.List,
	 java.util.*,
	 com.teamwrkr.app.dto.*,
	 com.teamwrkr.app.manager.*,
	 com.teamwrkr.app.service.*,
	 com.teamwrkr.app.util.*,
	 java.text.ParseException,
	 java.text.SimpleDateFormat,
	 java.text.DecimalFormat"
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Teamwrkr - Dashboard</title>
<link href="/tw1/img/favicon.ico" rel="shortcut icon" type="image/x-icon" />
<link rel="stylesheet" href="/tw1/css/twddr.css">
<link rel="stylesheet" href="/tw1/css/landing.css">
<link rel="stylesheet" href="/tw1/css/menubar.css">
<script src="/tw1/js/ddr1.js"></script>
<script src="/tw1/js/ddr2.js"></script>
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
	photoSrc = "/tw1/img/upload-photo-default-130x130-b.png";
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


%>
<%@include file="menubar.jsp"%>
<%@include file="hoverhelp.jsp"%>

<div class="main" style="margin-top: 0px;">

<div class="photobg">
<a class="topright1" 
onmouseover="show('hh-tooltip-edit-pi'); show('hh-tooltip-edit-pi-body');"
onmouseout="hide('hh-tooltip-edit-pi'); hide('hh-tooltip-edit-pi-body');"
onclick="openPopup('ddrPop1'); prepopulateForm('ddrPopForm1');">EDIT</a>
<div class="photo1"></div>
<div class="photo2">
<img id="ddrPhoto" 
onclick="openPopup('ddrPop9');"
src="<%=photoSrc %>" /></div>
<div class="name">
<span id="ddrfn"><%=profile.getFirstName() %></span>&nbsp;
<span id="ddrln"><%=profile.getLastName() %></span>
</div>
<div class="location">
<span id="ddrcity"><%=profile.getCity() %></span>,&nbsp;
<span id="ddrsp"><%=profile.getState() %></span>&nbsp;&nbsp;
<span id="ddrcountry"><%=profile.getCountry() %></span>
</div>
</div>




<div class="job">
<table class="centeredtable3">
<tr>
<td rowspan="3" style="width: 90px;">
<img id="ddrJobIcon" src="/tw1/img/twiconx48-1.png" />
</td>
<td style="font-size: 22px; font-weight: bold; text-align: left;">
<span id="ddrCategory1"><%=profile.getCategory1() %></span>
</td>
</tr>
<tr>
<td style="font-size: 18px; font-weight: bold; color: rgba(255, 211, 22, 0.75); text-align: left;">
<span id="ddrCategory2"><%=profile.getCategory2() %></span>
</td>
</tr>
</table>
</div>

<%
if (profile.getNiche() == null) {
	profile.setNiche("");
}
%>


<div class="level">
<div class="subhdr1">ABOUT ME</div>
<a class="topright1" 
onmouseover="show('hh-tooltip-niche'); show('hh-tooltip-niche-body');"
onmouseout="hide('hh-tooltip-niche'); hide('hh-tooltip-niche-body');"
onclick="openPopup('ddrPop12'); prepopulateForm('ddrPopForm12');">EDIT</a>
<div class="niche">
<div id="ddrniche"><%=profile.getNiche() %></div>
</div>
</div>



<div class="command">
<div class="subhdr1">COMMAND CENTER</div>
<button class="button2" 
id="addwrkbutton" 
onclick="openPopup('ddrPop5');"
onmouseover="show('hh-tooltip-wrk'); show('hh-tooltip-wrk-body');"
onmouseout="hide('hh-tooltip-wrk'); hide('hh-tooltip-wrk-body');">ADD TO YOUR WORK HISTORY</button><br/><br/>
<button 
class="button2" 
id="testimonialbutton"
onclick="openPopup('ddrPop15');"
onmouseover="show('hh-tooltip-test'); show('hh-tooltip-test-body');"
onmouseout="hide('hh-tooltip-test'); hide('hh-tooltip-test-body');"
disabled>REQUEST TESTIMONIAL</button><br/><br/>
<button class="button2" 
id="videobutton" 
onclick="openPopup('ddrPop14');"
disabled>CREATE PROMOTIONAL VIDEO</button><br/>
</div>


<div class="connect">
<div class="subhdr3">SOCIAL LINKS</div>
<div class="iconbg">
<form>

<%
if (profile.getUrl4() != null) {
%>
<img id="ddrurl4" class="imgroundedge1" 
style="height: 32px; width: 32px; opacity: 80%; margin: 0px 5px; cursor: pointer;"
onclick="openTab('ddrurl4a');"
src="/tw1/img/linkedinx64-3.png" />
<input type="hidden" id="ddrurl4a" value="<%=url4 %>" />
<%
}
if (profile.getUrl3() != null) {
%>
<img id="ddrurl3" class="imgroundedge1" 
style="height: 32px; width: 32px; opacity: 80%;  margin: 0px 5px; cursor: pointer;"
onclick="openTab('ddrurl3a');"
src="/tw1/img/instagramx64.png" />
<input type="hidden" id="ddrurl3a" value="<%=url3 %>" />
<%
}
if (profile.getUrl5() != null) {
%>
<img id="ddrurl5" 
class="imgroundedge1" 
style="height: 32px; width: 32px; opacity: 80%;  margin: 0px 5px; cursor: pointer;"
onclick="openTab('ddrurl5a');"
src="/tw1/img/tik-tok.png" />
<input type="hidden" id="ddrurl5a" value="<%=url5 %>" />
<%
}
if (profile.getUrl2() != null) {
%>
<img id="ddrurl2" class="imgroundedge1"
style="height: 32px; width: 32px; opacity: 80%;  margin: 0px 5px; cursor: pointer;"
onclick="openTab('ddrurl2a');"
src="/tw1/img/twitter.png" />
<input type="hidden" id="ddrurl2a" value="<%=url2 %>" />
<%
}
if (profile.getUrl6() != null) {
%>
<img id="ddrurl6" class="imgroundedge1"
style="height: 32px; width: 32px; opacity: 80%;  margin: 0px 5px; cursor: pointer;"
onclick="openTab('ddrurl6a');"
src="/tw1/img/icons8-canva-app-64.png" />
<input type="hidden" id="ddrurl6a" value="<%=url6 %>" />
<%
}
if (profile.getUrl1() != null) {
%>
<img id="ddrurl1" class="imgroundedge1" 
style="height: 32px; width: 32px; opacity: 80%;  margin: 0px 5px; cursor: pointer;"
onclick="openTab('ddrurl1a');"
src="/tw1/img/facebook.png" />
<input type="hidden" id="ddrurl1a" value="<%=url1 %>" />
<%
}
%>


</form>
</div>
<button class="bottom" 
onclick="openPopup('ddrPop3'); prepopulateUrlForm('ddrPopForm3');"
onmouseover="show('hh-tooltip-social'); show('hh-tooltip-social-body');"
onmouseout="hide('hh-tooltip-social'); hide('hh-tooltip-social-body');">CONNECT</button>
</div>

<%
Availability av1 = new Availability();
av1.setTwid(twid);
Availability av = (Availability) DataService.selectOne(av1);
int[] avmx = av.getMatrix();
int daysAvail = av.getDaysAvailable();
int daysNotAvail = 28 - daysAvail;

//System.out.println("daysAvail=" + daysAvail);
//System.out.println("daysNotAvail=" + daysNotAvail);

float pctY = (float) daysAvail/28;
float pctN = (float) daysNotAvail/28;


float py = (float) pctY * 100;
float pn = (float) pctN * 100;

//System.out.println("pctY=" + py);
//System.out.println("pctN=" + pn);

Formatter formatterYes = new Formatter();
formatterYes.format("%.0f", py);
String pctYes = formatterYes.toString();

Formatter formatterNo= new Formatter();
formatterNo.format("%.0f", pn);
String pctNo = formatterNo.toString();

int dividend = 100;
int divisor = 5;
int quotient = dividend / divisor;
//System.out.println("quotient=" + quotient);
%>


<div class="avail">
<div class="subhdr3">AVAILABILITY</div>

<table id="calswitchtable">
<tr>
<td>
<%
if (av.getDaysAvailable() == 28) {
%>
<span style="color: rgba(255, 0, 0, 0.7);"></span>
<label class="switch">
<input type="checkbox" id="ddrAvailYes" onclick="setAvailability(); processCalendarForm('ddrPopForm6');" checked>
<span class="slider round"></span>
</label>
<%
}
else {
%>
<span style="color: rgba(255, 0, 0, 0.7);"></span>
<label class="switch">
<input type="checkbox" id="ddrAvailYes" onclick="setAvailability(); processCalendarForm('ddrPopForm6');">
<span class="slider round"></span>
</label>
<%
}
%>
<span>YES</span>
</td>
</tr>
</table>
<div class="calbg" style="display: none;">
<div class="verttext">NEXT 28d</div>
</div>
<div class="caldatebox" style="display: none;">
<a class="topright1" style="display: none;"  onclick="openPopup('ddrPop6'); displayAvailability();">SEE CALENDAR</a>
<div class="calstatsbg4"><span id="ddrDaysAvail"><%=daysAvail %></span>/28 <span style="font-size: 13px; color: rgba(222, 222, 222, 0.9);">days available (<span id="ddrPctAvail"><%=pctYes %></span>%)</span></div>
<div class="calstatsbg5"><span id="ddrDaysNotAvail"><%=daysNotAvail %></span>/28 <span style="font-size: 13px; color: rgba(222, 222, 222, 0.9);">days unavailable (<span id="ddrPctNotAvail"><%=pctNo %></span>%)</span></div>
</div>
<button class="bottom" onclick="openPopup('ddrPop6'); displayAvailability();">SEE CALENDAR</button>
</div>

<%
List<String> ssList = SkillService.getSoftSkills();
List<String> hardSkills = SkillService.getHardSkills(profile.getCategory1(), profile.getCategory2());
List<String> remainingHardSkills = new ArrayList<String>();

Scores scores = new Scores();
scores.setTwid(twid);

if (DataService.selectOne(scores) != null) {
	scores = (Scores) DataService.selectOne(scores);
}

List<String> dhsx = new ArrayList<String>();
List<Integer> dhpx = new ArrayList<Integer>();
//String[] dhs = {"","","","",""};
String[] dhs = {"","","","","","","","","",""};
Integer[] dhp = {0,0,0,0,0,0,0,0,0,0};
int idxx = 0;

Map<String,Integer> hsMap = ScoreService.getMeasurableSkillScores(scores, profile);
for (Map.Entry<String,Integer> entry : hsMap.entrySet()) { 
    dhs[idxx] = entry.getKey();
    dhp[idxx] = entry.getValue();
	System.out.println("dhs.key = " + entry.getKey() + ", dhp.val = " + entry.getValue());
    idxx++;
}

boolean allowHardSkillsEdits = true;

%>

<div class="skills">
<div class="skillshdr">SKILLS</div>
<div class="specificskillsbg">
<div class="subhdr2">MEASURABLE SKILLS</div>
<a class="topright1" 
onmouseover="show('hh-tooltip-skills'); show('hh-tooltip-skills-body');"
onmouseout="hide('hh-tooltip-skills'); hide('hh-tooltip-skills-body');"
onclick="openPopup('ddrPop2');">EDIT</a>
<div class="skills1">

<%
if (dhs[0].length() > 0) {
	String s0style = "";
	if (profile.getUserSkill1().equals(dhs[0]) || profile.getUserSkill2().equals(dhs[0]) || profile.getUserSkill3().equals(dhs[0])) {
		s0style = "style=\"font-weight: 700; color: rgba(0, 255, 0, 1);\"";
	}
%>
<div class="skillbartopm1">
<div id="ddrSkill1a" class="skillname" <%=s0style %>><span id="ddrhard1"><%=dhs[0] %></span></div>
<div class="skilllevelbg1"></div>
<div class="skilllevelfg1" style="width: <%=ScoreService.calcSkillWidth(dhp[0]) %>px;"></div>
<div class="skillpct2" <%=s0style %>><%=dhp[0] %></div>
</div>
<%
}
if (dhs[1].length() > 0) {
	String s1style = "";
	if (profile.getUserSkill1().equals(dhs[1]) || profile.getUserSkill2().equals(dhs[1]) || profile.getUserSkill3().equals(dhs[1])) {
		s1style = "style=\"font-weight: 700; color: rgba(0, 255, 0, 1);\"";
	}
%>
<div class="skillbarmidm2">
<div class="skillname" <%=s1style %>><span id="ddrhard2"><%=dhs[1] %></span></div>
<div class="skilllevelbg1"></div>
<div class="skilllevelfg1" style="width: <%=ScoreService.calcSkillWidth(dhp[1]) %>px;"></div>
<div class="skillpct2" <%=s1style %>><%=dhp[1] %></div>
</div>
<%
}
if (dhs[2].length() > 0) {
	String s2style = "";
	if (profile.getUserSkill1().equals(dhs[2]) || profile.getUserSkill2().equals(dhs[2]) || profile.getUserSkill3().equals(dhs[2])) {
		s2style = "style=\"font-weight: 700; color: rgba(0, 255, 0, 1);\"";
	}
%>
<div class="skillbarlowm3">
<div class="skillname" <%=s2style %>><span id="ddrhard3"><%=dhs[2] %></span></div>
<div class="skilllevelbg1"></div>
<div class="skilllevelfg1" style="width: <%=ScoreService.calcSkillWidth(dhp[2]) %>px;"></div>
<div class="skillpct2" <%=s2style %>><%=dhp[2] %></div>
</div>
<%
}
if (dhs[3].length() > 0) {
	String s3style = "";
	if (profile.getUserSkill1().equals(dhs[3]) || profile.getUserSkill2().equals(dhs[3]) || profile.getUserSkill3().equals(dhs[3])) {
		s3style = "style=\"font-weight: 700; color: rgba(0, 255, 0, 1);\"";
	}
%>
<div class="skillbarlowerm4">
<div class="skillname" <%=s3style %>><span id="ddrhard4"><%=dhs[3] %></span></div>
<div class="skilllevelbg1"></div>
<div class="skilllevelfg1" style="width: <%=ScoreService.calcSkillWidth(dhp[3]) %>px;"></div>
<div class="skillpct2" <%=s3style %>><%=dhp[3] %></div>
</div>
<%
}
if (dhs[4].length() > 0) {
	String s4style = "";
	if (profile.getUserSkill1().equals(dhs[4]) || profile.getUserSkill2().equals(dhs[4]) || profile.getUserSkill3().equals(dhs[4])) {
		s4style = "style=\"font-weight: 700; color: rgba(0, 255, 0, 1);\"";
	}
%>
<div class="skillbarlowestm5">
<div class="skillname" <%=s4style %>><span id="ddrhard5"><%=dhs[4] %></span></div>
<div class="skilllevelbg1"></div>
<div class="skilllevelfg1" style="width: <%=ScoreService.calcSkillWidth(dhp[4]) %>px;"></div>
<div class="skillpct2" <%=s4style %>><%=dhp[4] %></div>
</div>
<%
}
if (dhs[5].length() > 0) {
	String s5style = "";
	if (profile.getUserSkill1().equals(dhs[5]) || profile.getUserSkill2().equals(dhs[5]) || profile.getUserSkill3().equals(dhs[5])) {
		s5style = "style=\"font-weight: 700; color: rgba(0, 255, 0, 1);\"";
	}
%>
<div class="skillbarm6">
<div class="skillname" <%=s5style %>><span id="ddrhard6"><%=dhs[5] %></span></div>
<div class="skilllevelbg1"></div>
<div class="skilllevelfg1" style="width: <%=ScoreService.calcSkillWidth(dhp[5]) %>px;"></div>
<div class="skillpct2" <%=s5style %>><%=dhp[5] %></div>
</div>
<%
}
if (dhs[6].length() > 0) {
	String s6style = "";
	if (profile.getUserSkill1().equals(dhs[6]) || profile.getUserSkill2().equals(dhs[6]) || profile.getUserSkill3().equals(dhs[6])) {
		s6style = "style=\"font-weight: 700; color: rgba(0, 255, 0, 1);\"";
	}
%>
<div class="skillbarm7">
<div class="skillname" <%=s6style %>><span id="ddrhard7"><%=dhs[6] %></span></div>
<div class="skilllevelbg1"></div>
<div class="skilllevelfg1" style="width: <%=ScoreService.calcSkillWidth(dhp[6]) %>px;"></div>
<div class="skillpct2" <%=s6style %>><%=dhp[6] %></div>
</div>
<%
}
if (dhs[7].length() > 0) {
	String s7style = "";
	if (profile.getUserSkill1().equals(dhs[7]) || profile.getUserSkill2().equals(dhs[7]) || profile.getUserSkill3().equals(dhs[7])) {
		s7style = "style=\"font-weight: 700; color: rgba(0, 255, 0, 1);\"";
	}
%>
<div class="skillbarm8">
<div class="skillname" <%=s7style %>><span id="ddrhard8"><%=dhs[7] %></span></div>
<div class="skilllevelbg1"></div>
<div class="skilllevelfg1" style="width: <%=ScoreService.calcSkillWidth(dhp[7]) %>px;"></div>
<div class="skillpct2" <%=s7style %>><%=dhp[7] %></div>
</div>
<%
}
if (dhs[8].length() > 0) {
	String s8style = "";
	if (profile.getUserSkill1().equals(dhs[8]) || profile.getUserSkill2().equals(dhs[8]) || profile.getUserSkill3().equals(dhs[8])) {
		s8style = "style=\"font-weight: 700; color: rgba(0, 255, 0, 1);\"";
	}
%>
<div class="skillbarm9">
<div class="skillname" <%=s8style %>><span id="ddrhard9"><%=dhs[8] %></span></div>
<div class="skilllevelbg1"></div>
<div class="skilllevelfg1" style="width: <%=ScoreService.calcSkillWidth(dhp[8]) %>px;"></div>
<div class="skillpct2" <%=s8style %>><%=dhp[8] %></div>
</div>
<%
}
if (dhs[9].length() > 0) {
	String s9style = "";
	if (profile.getUserSkill1().equals(dhs[9]) || profile.getUserSkill2().equals(dhs[9]) || profile.getUserSkill3().equals(dhs[9])) {
		s9style = "style=\"font-weight: 700; color: rgba(0, 255, 0, 1);\"";
	}
%>
<div class="skillbarm10">
<div class="skillname" <%=s9style %>><span id="ddrhard10"><%=dhs[9] %></span></div>
<div class="skilllevelbg1"></div>
<div class="skilllevelfg1" style="width: <%=ScoreService.calcSkillWidth(dhp[9]) %>px;"></div>
<div class="skillpct2" <%=s9style %>><%=dhp[9] %></div>
</div>
<%
}
%>

</div>
</div>
<div class="personalskillsbg">
<div class="subhdr2">INTERPERSONAL SKILLS</div>
<div class="skills1">
<div class="skillbartop">
<div class="skillname"><span id="ddrsoft1"><%=ssList.get(0) %></span></div>
<div class="skilllevelbg2"></div>
<div class="skilllevelfg2" style="width: <%=ScoreService.calcSkillWidth(scores.getScores1()) %>px;"></div>
<div class="skillpct"><%=scores.getScores1() %></div>
</div>
<div class="skillbarmid">
<div class="skillname"><span id="ddrsoft2"><%=ssList.get(1) %></span></div>
<div class="skilllevelbg2"></div>
<div class="skilllevelfg2" style="width: <%=ScoreService.calcSkillWidth(scores.getScores2()) %>px;"></div>
<div class="skillpct"><%=scores.getScores2() %></div>
</div>
<div class="skillbarlow">
<div class="skillname"><span id="ddrsoft3"><%=ssList.get(2) %></span></div>
<div class="skilllevelbg2"></div>
<div class="skilllevelfg2" style="width: <%=ScoreService.calcSkillWidth(scores.getScores3()) %>px;"></div>
<div class="skillpct"><%=scores.getScores3() %></div>
</div>
<div class="skillbarlower">
<div class="skillname"><span id="ddrsoft4"><%=ssList.get(3) %></span></div>
<div class="skilllevelbg2"></div>
<div class="skilllevelfg2" style="width: <%=ScoreService.calcSkillWidth(scores.getScores4()) %>px;"></div>
<div class="skillpct"><%=scores.getScores4() %></div>
</div>
<div class="skillbarlowest">
<div class="skillname"><span id="ddrsoft5"><%=ssList.get(4) %></span></div>
<div class="skilllevelbg2"></div>
<div class="skilllevelfg2" style="width: <%=ScoreService.calcSkillWidth(scores.getScores5()) %>px;"></div>
<div class="skillpct"><%=scores.getScores5() %></div>
</div>
<div class="skillbarlowesta">
<div class="skillname"><span id="ddrsoft6"><%=ssList.get(5) %></span></div>
<div class="skilllevelbg2"></div>
<div class="skilllevelfg2" style="width: <%=ScoreService.calcSkillWidth(scores.getScores6()) %>px;"></div>
<div class="skillpct"><%=scores.getScores6() %></div>
</div>
<div class="skillbarlowestb">
<div class="skillname"><span id="ddrsoft7"><%=ssList.get(6) %></span></div>
<div class="skilllevelbg2"></div>
<div class="skilllevelfg2" style="width: <%=ScoreService.calcSkillWidth(scores.getScores7()) %>px;"></div>
<div class="skillpct"><%=scores.getScores7() %></div>
</div>
<div class="skillbarlowestc">
<div class="skillname"><span id="ddrsoft8"><%=ssList.get(7) %></span></div>
<div class="skilllevelbg2"></div>
<div class="skilllevelfg2" style="width: <%=ScoreService.calcSkillWidth(scores.getScores8()) %>px;"></div>
<div class="skillpct"><%=scores.getScores8() %></div>
</div>
<div class="skillbarlowestd">
<div class="skillname"><span id="ddrsoft9"><%=ssList.get(8) %></span></div>
<div class="skilllevelbg2"></div>
<div class="skilllevelfg2" style="width: <%=ScoreService.calcSkillWidth(scores.getScores9()) %>px;"></div>
<div class="skillpct"><%=scores.getScores9() %></div>
</div>
<div class="skillbarloweste">
<div class="skillname"><span id="ddrsoft10"><%=ssList.get(9) %></span></div>
<div class="skilllevelbg2"></div>
<div class="skilllevelfg2" style="width: <%=ScoreService.calcSkillWidth(scores.getScores10()) %>px;"></div>
<div class="skillpct"><%=scores.getScores10() %></div>
</div>
</div>
</div>
</div>

<%
SiteConstants sc = new SiteConstants();
Mission mission = new Mission();
Object[] missions = DataService.selectMany(mission, "twid1 = " + twid);

List<Mission> vmiss = new ArrayList<Mission>();
int msct = 0;
int reviewsSent = 0;
for (Object msobj : missions) {
	Mission mssn = (Mission) msobj;
	if (mssn.getMissionType() == sc.MISSION_TYPE_WORK_EXP) {
		reviewsSent = reviewsSent + 1;
		vmiss.add(mssn);
	}
}
Mission[] visMissions = new Mission[vmiss.size()];
for (Mission vms : vmiss) {
	visMissions[msct] = vms;
	msct++;
}

int missionsDone = MissionService.missionsComplete(twid);
int overallScore = ScoreService.calculateOverallPoints(twid, scores);
%>

<div class="missions">
<div class="missionhdr">YOUR WRK JOURNEY</div>
<div class="missionratingbg">
<div class="subhdr2">WRK STATS</div>
<div class="missionratingbox">
<table class="centeredtable1">
<tr>
<td rowspan="2" style="width: 50px;">
<div class="circlepurplebig"><img src="/tw1/img/lightning-74-32.png" style="height: 24px; vertical-align: middle;"></img></div>
</td>
<td style="font-size: 18px; font-weight: bold; text-align: left;"><%=overallScore %></td>
</tr>
<tr>
<td style="font-size: 12px; color: gray; text-align: left; font-weight: bold;">Total Review Points</td>
</tr>
</table>
</div>
</div>
<div class="missionscompletebg">

<div class="missionscompletebox">
<table class="centeredtable1">
<tr>
<td rowspan="2" style="width: 50px;">
<div class="circlegreenbig">&check;</div>
</td>
<td style="font-size: 18px; font-weight: bold; text-align: left;"><%=missionsDone %>/<%=reviewsSent %></td>
</tr>
<tr>
<td style="font-size: 12px; color: gray; text-align: left; font-weight: bold;">Reviews Completed</td>
</tr>
</table>
</div>
</div>
<div class="missionhistorybg">
<div class="subhdr2">WRK HISTORY</div>
<a class="topright1" 
onclick="openPopup('ddrPop7');">SEE ALL</a>

<div id="missionhistorydiv">
<%
/*
 * Most recent first.
 */
int rowcount = 1;
for (int ms = (visMissions.length-1); ms >= 0; ms--) {

	Date date = Calendar.getInstance().getTime();
	
	/*
	 * Alter the date displayed to correspond to the
	 * context of the type of mission that was submitted.
	 */
	System.out.println("mission " + ms);
	int missionStatus = MissionService.getMissionStatus(visMissions[ms]);
	if (missionStatus == sc.MISSION_WORK_EXP_STATUS_SENT) {date = visMissions[ms].getEnteredDate();}
	if (missionStatus == sc.MISSION_WORK_EXP_STATUS_VERIFIED) {date = visMissions[ms].getReferenceDate();}
	//if (missionStatus == sc.MISSION_JOB_POST_STATUS_POSTED) {date = visMissions[ms].getPostedDate();}
	String actionDate = date.toString();
	
	String statusText = MissionService.getJobStatusMessage(visMissions[ms]);
	
	String rowStyle = "";
	if (rowcount % 2 == 1) {rowStyle = "background: rgba(222, 222, 222, 0.08);"	;}
%>
<div class="missionhistoryrow" style="<%=rowStyle %>">
<div class="missionhistoryrow-col1" >
<%
	if (missionStatus == sc.MISSION_WORK_EXP_STATUS_SENT) {
%>
<div class="circleyellow">
<img src="/tw1/img/clock-221-32.png" style="height: 20px; padding-bottom: 1px;  vertical-align: middle; "></img>
</div>
<%
	}
	if (missionStatus == sc.MISSION_WORK_EXP_STATUS_VERIFIED) {
%>
<div class="circlegreen">&check;</div>
<%
	}
%>


</div>
<%
String missionType = "";
if (visMissions[ms].getMissionType() == sc.MISSION_TYPE_WORK_EXP) {
	missionType = "REFERENCE";
}

String missionAction = MissionService.getActionMessage(visMissions[ms]);

String from = "";
if (visMissions[ms].getManager() != null) {
	from = visMissions[ms].getManager();
}

if (missionStatus == sc.MISSION_JOB_POST_STATUS_MATCHED) {
	
}

String missionStatusSummary = MissionService.getJobStatusMessage(visMissions[ms]);


%>
<!-- mission type and action verb  for the row -->
<div class="missionhistoryrow-col2" >
<%=visMissions[ms].getTitle() %>  
</div>
<!-- point of contact -->
<div class="missionhistoryrow-col3">
STATUS: <%=missionStatusSummary %>
</div>
<!-- link to details -->
<div class="missionhistoryrow-col4">
</div>
<!-- date for the row -->
<div class="missionhistoryrow-col5">
<%=missionAction %>&nbsp;<%=actionDate %>
</div>

</div>
<%
	rowcount++;
}
%>

</div>

</div>

</div>

</div>


<!-- TOOLTIPS -->


<%@include file="mbhelp.jsp"%>


<!-- POPUP WINDOWS -->

<!-- **********************************************  1   ********************************************** -->
<!-- ******************************************** EDIT PI ********************************************* -->
<!-- ************************************************************************************************** -->
<!-- Edit Name, Location -->
<div id="ddrPop1">
<form id="ddrPopForm1" name="formdash1" class="popup">  
<input type="hidden" name="query" value="1" />
<input type="hidden" name="twid" value="<%=twid %>" />
<h6 class="formheader">Edit Personal Information</h6>
<hr/>
<b>First Name</b><br/>
<input type="text" class="forminput1" value="" name="fn"><br/>
<b>Last Name</b><br/>
<input type="text" class="forminput1" value="" name="ln"><br/>
<b>City</b><br/>
<input type="text" class="forminput1" value="" name="city"><br/>
<b>State/Province</b><br/>
<select 
class="formselect1" 
id="ddr-sp"
name="sp">
<option value="">Select state</option>
<option value="AL">Alabama</option>
<option value="AK">Alaska</option>
<option value="AZ">Arizona</option>
<option value="AR">Arkansas</option>
<option value="CA">California</option>
<option value="CO">Colorado</option>
<option value="CT">Connecticut</option>
<option value="DE">Delaware</option>
<option value="DC">District Of Columbia</option>
<option value="FL">Florida</option>
<option value="GA">Georgia</option>
<option value="HI">Hawaii</option>
<option value="ID">Idaho</option>
<option value="IL">Illinois</option>
<option value="IN">Indiana</option>
<option value="IA">Iowa</option>
<option value="KS">Kansas</option>
<option value="KY">Kentucky</option>
<option value="LA">Louisiana</option>
<option value="ME">Maine</option>
<option value="MD">Maryland</option>
<option value="MA">Massachusetts</option>
<option value="MI">Michigan</option>
<option value="MN">Minnesota</option>
<option value="MS">Mississippi</option>
<option value="MO">Missouri</option>
<option value="MT">Montana</option>
<option value="NE">Nebraska</option>
<option value="NV">Nevada</option>
<option value="NH">New Hampshire</option>
<option value="NJ">New Jersey</option>
<option value="NM">New Mexico</option>
<option value="NY">New York</option>
<option value="NC">North Carolina</option>
<option value="ND">North Dakota</option>
<option value="OH">Ohio</option>
<option value="OK">Oklahoma</option>
<option value="OR">Oregon</option>
<option value="PA">Pennsylvania</option>
<option value="RI">Rhode Island</option>
<option value="SC">South Carolina</option>
<option value="SD">South Dakota</option>
<option value="TN">Tennessee</option>
<option value="TX">Texas</option>
<option value="UT">Utah</option>
<option value="VT">Vermont</option>
<option value="VA">Virginia</option>
<option value="WA">Washington</option>
<option value="WV">West Virginia</option>
<option value="WI">Wisconsin</option>
<option value="WY">Wyoming</option>
</select><br/>
<b>Country</b><br/>
<select 
class="formselect1" 
id="ddr-country"
onchange="toggleSelectState();"
name="country">
<option value="">Select country</option>
<option value="AF">Afghanistan</option>
<option value="AX">Aland Islands</option>
<option value="AL">Albania</option>
<option value="DZ">Algeria</option>
<option value="AS">American Samoa</option>
<option value="AD">Andorra</option>
<option value="AO">Angola</option>
<option value="AI">Anguilla</option>
<option value="AQ">Antarctica</option>
<option value="AG">Antigua and Barbuda</option>
<option value="AR">Argentina</option>
<option value="AM">Armenia</option>
<option value="AW">Aruba</option>
<option value="AU">Australia</option>
<option value="AT">Austria</option>
<option value="AZ">Azerbaijan</option>
<option value="BS">Bahamas</option>
<option value="BH">Bahrain</option>
<option value="BD">Bangladesh</option>
<option value="BB">Barbados</option>
<option value="BY">Belarus</option>
<option value="BE">Belgium</option>
<option value="BZ">Belize</option>
<option value="BJ">Benin</option>
<option value="BM">Bermuda</option>
<option value="BT">Bhutan</option>
<option value="BO">Bolivia</option>
<option value="BQ">Bonaire, Sint Eustatius and Saba</option>
<option value="BA">Bosnia and Herzegovina</option>
<option value="BW">Botswana</option>
<option value="BV">Bouvet Island</option>
<option value="BR">Brazil</option>
<option value="IO">British Indian Ocean Territory</option>
<option value="BN">Brunei Darussalam</option>
<option value="BG">Bulgaria</option>
<option value="BF">Burkina Faso</option>
<option value="BI">Burundi</option>
<option value="KH">Cambodia</option>
<option value="CM">Cameroon</option>
<option value="CA">Canada</option>
<option value="CV">Cape Verde</option>
<option value="KY">Cayman Islands</option>
<option value="CF">Central African Republic</option>
<option value="TD">Chad</option>
<option value="CL">Chile</option>
<option value="CN">China</option>
<option value="CX">Christmas Island</option>
<option value="CC">Cocos (Keeling) Islands</option>
<option value="CO">Colombia</option>
<option value="KM">Comoros</option>
<option value="CG">Congo</option>
<option value="CD">Congo, Democratic Republic of the Congo</option>
<option value="CK">Cook Islands</option>
<option value="CR">Costa Rica</option>
<option value="CI">Cote D'Ivoire</option>
<option value="HR">Croatia</option>
<option value="CU">Cuba</option>
<option value="CW">Curacao</option>
<option value="CY">Cyprus</option>
<option value="CZ">Czech Republic</option>
<option value="DK">Denmark</option>
<option value="DJ">Djibouti</option>
<option value="DM">Dominica</option>
<option value="DO">Dominican Republic</option>
<option value="EC">Ecuador</option>
<option value="EG">Egypt</option>
<option value="SV">El Salvador</option>
<option value="GQ">Equatorial Guinea</option>
<option value="ER">Eritrea</option>
<option value="EE">Estonia</option>
<option value="ET">Ethiopia</option>
<option value="FK">Falkland Islands (Malvinas)</option>
<option value="FO">Faroe Islands</option>
<option value="FJ">Fiji</option>
<option value="FI">Finland</option>
<option value="FR">France</option>
<option value="GF">French Guiana</option>
<option value="PF">French Polynesia</option>
<option value="TF">French Southern Territories</option>
<option value="GA">Gabon</option>
<option value="GM">Gambia</option>
<option value="GE">Georgia</option>
<option value="DE">Germany</option>
<option value="GH">Ghana</option>
<option value="GI">Gibraltar</option>
<option value="GR">Greece</option>
<option value="GL">Greenland</option>
<option value="GD">Grenada</option>
<option value="GP">Guadeloupe</option>
<option value="GU">Guam</option>
<option value="GT">Guatemala</option>
<option value="GG">Guernsey</option>
<option value="GN">Guinea</option>
<option value="GW">Guinea-Bissau</option>
<option value="GY">Guyana</option>
<option value="HT">Haiti</option>
<option value="HM">Heard Island and Mcdonald Islands</option>
<option value="VA">Holy See (Vatican City State)</option>
<option value="HN">Honduras</option>
<option value="HK">Hong Kong</option>
<option value="HU">Hungary</option>
<option value="IS">Iceland</option>
<option value="IN">India</option>
<option value="ID">Indonesia</option>
<option value="IR">Iran, Islamic Republic of</option>
<option value="IQ">Iraq</option>
<option value="IE">Ireland</option>
<option value="IM">Isle of Man</option>
<option value="IL">Israel</option>
<option value="IT">Italy</option>
<option value="JM">Jamaica</option>
<option value="JP">Japan</option>
<option value="JE">Jersey</option>
<option value="JO">Jordan</option>
<option value="KZ">Kazakhstan</option>
<option value="KE">Kenya</option>
<option value="KI">Kiribati</option>
<option value="KP">Korea, Democratic People's Republic of</option>
<option value="KR">Korea, Republic of</option>
<option value="XK">Kosovo</option>
<option value="KW">Kuwait</option>
<option value="KG">Kyrgyzstan</option>
<option value="LA">Lao People's Democratic Republic</option>
<option value="LV">Latvia</option>
<option value="LB">Lebanon</option>
<option value="LS">Lesotho</option>
<option value="LR">Liberia</option>
<option value="LY">Libyan Arab Jamahiriya</option>
<option value="LI">Liechtenstein</option>
<option value="LT">Lithuania</option>
<option value="LU">Luxembourg</option>
<option value="MO">Macao</option>
<option value="MK">Macedonia, the Former Yugoslav Republic of</option>
<option value="MG">Madagascar</option>
<option value="MW">Malawi</option>
<option value="MY">Malaysia</option>
<option value="MV">Maldives</option>
<option value="ML">Mali</option>
<option value="MT">Malta</option>
<option value="MH">Marshall Islands</option>
<option value="MQ">Martinique</option>
<option value="MR">Mauritania</option>
<option value="MU">Mauritius</option>
<option value="YT">Mayotte</option>
<option value="MX">Mexico</option>
<option value="FM">Micronesia, Federated States of</option>
<option value="MD">Moldova, Republic of</option>
<option value="MC">Monaco</option>
<option value="MN">Mongolia</option>
<option value="ME">Montenegro</option>
<option value="MS">Montserrat</option>
<option value="MA">Morocco</option>
<option value="MZ">Mozambique</option>
<option value="MM">Myanmar</option>
<option value="NA">Namibia</option>
<option value="NR">Nauru</option>
<option value="NP">Nepal</option>
<option value="NL">Netherlands</option>
<option value="AN">Netherlands Antilles</option>
<option value="NC">New Caledonia</option>
<option value="NZ">New Zealand</option>
<option value="NI">Nicaragua</option>
<option value="NE">Niger</option>
<option value="NG">Nigeria</option>
<option value="NU">Niue</option>
<option value="NF">Norfolk Island</option>
<option value="MP">Northern Mariana Islands</option>
<option value="NO">Norway</option>
<option value="OM">Oman</option>
<option value="PK">Pakistan</option>
<option value="PW">Palau</option>
<option value="PS">Palestinian Territory, Occupied</option>
<option value="PA">Panama</option>
<option value="PG">Papua New Guinea</option>
<option value="PY">Paraguay</option>
<option value="PE">Peru</option>
<option value="PH">Philippines</option>
<option value="PN">Pitcairn</option>
<option value="PL">Poland</option>
<option value="PT">Portugal</option>
<option value="PR">Puerto Rico</option>
<option value="QA">Qatar</option>
<option value="RE">Reunion</option>
<option value="RO">Romania</option>
<option value="RU">Russian Federation</option>
<option value="RW">Rwanda</option>
<option value="BL">Saint Barthelemy</option>
<option value="SH">Saint Helena</option>
<option value="KN">Saint Kitts and Nevis</option>
<option value="LC">Saint Lucia</option>
<option value="MF">Saint Martin</option>
<option value="PM">Saint Pierre and Miquelon</option>
<option value="VC">Saint Vincent and the Grenadines</option>
<option value="WS">Samoa</option>
<option value="SM">San Marino</option>
<option value="ST">Sao Tome and Principe</option>
<option value="SA">Saudi Arabia</option>
<option value="SN">Senegal</option>
<option value="RS">Serbia</option>
<option value="CS">Serbia and Montenegro</option>
<option value="SC">Seychelles</option>
<option value="SL">Sierra Leone</option>
<option value="SG">Singapore</option>
<option value="SX">Sint Maarten</option>
<option value="SK">Slovakia</option>
<option value="SI">Slovenia</option>
<option value="SB">Solomon Islands</option>
<option value="SO">Somalia</option>
<option value="ZA">South Africa</option>
<option value="GS">South Georgia and the South Sandwich Islands</option>
<option value="SS">South Sudan</option>
<option value="ES">Spain</option>
<option value="LK">Sri Lanka</option>
<option value="SD">Sudan</option>
<option value="SR">Suriname</option>
<option value="SJ">Svalbard and Jan Mayen</option>
<option value="SZ">Swaziland</option>
<option value="SE">Sweden</option>
<option value="CH">Switzerland</option>
<option value="SY">Syrian Arab Republic</option>
<option value="TW">Taiwan, Province of China</option>
<option value="TJ">Tajikistan</option>
<option value="TZ">Tanzania, United Republic of</option>
<option value="TH">Thailand</option>
<option value="TL">Timor-Leste</option>
<option value="TG">Togo</option>
<option value="TK">Tokelau</option>
<option value="TO">Tonga</option>
<option value="TT">Trinidad and Tobago</option>
<option value="TN">Tunisia</option>
<option value="TR">Turkey</option>
<option value="TM">Turkmenistan</option>
<option value="TC">Turks and Caicos Islands</option>
<option value="TV">Tuvalu</option>
<option value="UG">Uganda</option>
<option value="UA">Ukraine</option>
<option value="AE">United Arab Emirates</option>
<option value="GB">United Kingdom</option>
<option value="USA" selected>United States</option>
<option value="UM">United States Minor Outlying Islands</option>
<option value="UY">Uruguay</option>
<option value="UZ">Uzbekistan</option>
<option value="VU">Vanuatu</option>
<option value="VE">Venezuela</option>
<option value="VN">Viet Nam</option>
<option value="VG">Virgin Islands, British</option>
<option value="VI">Virgin Islands, U.s.</option>
<option value="WF">Wallis and Futuna</option>
<option value="EH">Western Sahara</option>
<option value="YE">Yemen</option>
<option value="ZM">Zambia</option>
<option value="ZW">Zimbabwe</option>
</select><br/>
<button type="button" class="popupsubmit" onclick="processForm2('ddrPopForm1'); closePopup('ddrPop1');">Submit</button>
<button type="button" class="popupclose" onclick="closePopup('ddrPop1')">Close</button>
</form>
</div>
<!-- ************************************************************************************************** -->
<!-- ************************************************************************************************** -->
<!-- ************************************************************************************************** -->




<!-- *********************************************  12   ********************************************** -->
<!-- ****************************************** EDIT NICHE ******************************************** -->
<!-- ************************************************************************************************** -->
<!-- Edit Niche -->
<div id="ddrPop12">
<form id="ddrPopForm12" name="formdash12" class="popup">
<input type="hidden" name="query" value="12" />
<input type="hidden" name="twid" value="<%=twid %>" />
<h6 class="formheader">About Me</h6>
<hr/>
<p class="popupInst">
Tell us more about what makes you special or unique. What makes you stand out from the crowd!! This could be your
superpower or niche.
</p>
<hr/>
<br/>
<textarea class="formtextarea1" maxlength="512" name="niche" ></textarea><br/>

<button type="button" class="popupsubmit" onclick="processForm2('ddrPopForm12'); closePopup('ddrPop12');">Submit</button>
<button type="button" class="popupclose" onclick="closePopup('ddrPop12')">Close</button>
</form>
</div>
<!-- ************************************************************************************************** -->
<!-- ************************************************************************************************** -->
<!-- ************************************************************************************************** -->




<!-- *********************************************   2   ********************************************** -->
<!-- ****************************************** HARD SKILLS ******************************************* -->
<!-- ************************************************************************************************** -->
<%
if (allowHardSkillsEdits) {
%>
<!-- Select/Edit Specific (Hard) Skills -->
<div id="ddrPop2">
<form id="ddrPopForm2" name="formdash2" class="popup">
<input type="hidden" name="query" value="2" />
<input type="hidden" name="twid" value="<%=twid %>" />
<h6 class="formheader">Measurable Skills</h6>
<hr/>
<p class="popupInst">
The list below contains the specific skills that Teamwrkr believes are most important given the focus and subspecialty 
(e.g. Marketing/Branding) that you identified as during the registration process. 
Please select the top 3 measurable skills you believe you possess, allowing those you have worked or will work with
to more favorably rate you on your top-notch skill set.
</p>
<hr/>
<br/>
<%
int k = 1;
//List<String> hardSkills = SkillService.getHardSkills(profile.getCategory1(), profile.getCategory2());
for (String skill : hardSkills) {
%>
<input type="checkbox" name="ckb" value="<%=skill %>" onclick="limit3a(<%=k %>);" /><%=skill %><br/>
<input type="hidden" name="hard<%=k %>" id="hard<%=k %>" value="" />
<%
k++;
}
%>
<input type="hidden" name="boxes2" value="<%=hardSkills.size() %>" />
<button type="button" class="popupsubmit" onclick="check3('ddrPop2'); ">Submit</button>
<button type="button" class="popupclose" onclick="closePopup('ddrPop2')">Close</button>
</form>
</div>
<%
}
%>
<!-- ************************************************************************************************** -->
<!-- ************************************************************************************************** -->
<!-- ************************************************************************************************** -->




<!-- *********************************************   3   ********************************************** -->
<!-- ****************************************** SOCIAL MEDIA ****************************************** -->
<!-- ************************************************************************************************** -->
<!-- Provide URL links to social media -->
<div id="ddrPop3">
<form id="ddrPopForm3" name="formdash3" class="popup">
<input type="hidden" name="query" value="3" />
<input type="hidden" name="twid" value="<%=twid %>" />
<h6 class="formheader">Links To Social Media</h6>
<hr/>
<p class="popupInst">
Paste your profile homepages below.
</p>
<hr/>
<b>LinkedIn Link URL</b><br/>
<input type="text" class="forminput1" name="url4" value="<%=url4 %>"><br/>
<b>Instagram Link URL</b><br/>
<input type="text" class="forminput1" name="url3" value="<%=url3 %>"><br/>
<b>TikTok Link URL</b><br/>
<input type="text" class="forminput1" name="url5" value="<%=url5 %>"><br/>
<b>Twitter Link URL</b><br/>
<input type="text" class="forminput1" name="url2" value="<%=url2 %>"><br/>
<b>Canva Link URL</b><br/>
<input type="text" class="forminput1" name="url6" value="<%=url6 %>"><br/>
<b>Facebook Link URL</b><br/>
<input type="text" class="forminput1" name="url1" value="<%=url1 %>"><br/>
<button type="button" class="popupsubmit" onclick="processUrlForm2('ddrPopForm3'); closePopup('ddrPop3');">Submit</button>
<button type="button" class="popupclose" onclick="closePopup('ddrPop3')">Close</button>
</form>
</div>
<!-- ************************************************************************************************** -->
<!-- ************************************************************************************************** -->
<!-- ************************************************************************************************** -->

<!-- *********************************************  4  ************************************************ -->
<!-- ******************************************* FIND WRK ********************************************* -->
<!-- ************************************************************************************************** -->

<div id="ddrPop4">
<form id="ddrPopForm" name="formdash4" class="popup">
<input type="hidden" name="query" value="4" />
<input type="hidden" name="twid" value="<%=twid %>" />
<h6 class="formheader">Find WRK</h6>
<hr/>
<p class="popupInst">
Fill out the form below to help the Teamwrkr intelligent job matching system to better select new opportunities
to show you each time you visit our app. Be honest, and as specific as possible, to clarify the process
of finding new freelance roles to build your brand and business.
</p>
<hr/>
<table>
<tr>
<td class="popcolpad" style="vertical-align: top;">
<b>Minimum Rate*</b><br/>
<input type="text" class="forminput1" name="rate"><br/>
</td>
<td class="popcolpad" style="vertical-align: top;">
<b>Preferred Assignment Type*</b><br/>
<select class="formselect1" id="post-comp" name="comp">
<option value="0">per hour</option>
<option value="1">per project</option>
</select><br/>
</td>
</tr>
</table>
<div id="ddrPop4Msg" style="height: 13px; width: 505px; text-align: center; line-height: 23px; font-size: 13px; font-weight: bold; color: rgba(111, 0, 255, 1); vertical-align: middle: margin-top: 5px;"></div>
<button type="button" class="popupsubmit" onclick="processForm2('ddrPopForm4'); closePopup('ddrPop4');">Submit</button>
<button type="button" class="popupclose" onclick="closePopup('ddrPop4')">Close</button>
</form>
</div>
<!-- ************************************************************************************************** -->
<!-- ************************************************************************************************** -->
<!-- ************************************************************************************************** -->


<!-- *********************************************  5  ************************************************ -->
<!-- ******************************************* YOUR WRK ********************************************* -->
<!-- ************************************************************************************************** -->
<div id="ddrPop5">
<form id="ddrPopForm5" name="formdash5" class="popup">
<input type="hidden" name="query" value="5" />
<input type="hidden" name="twid" value="<%=twid %>" />
<h6 class="formheader">WRK Experience</h6>
<hr/>
<p class="popupInst">
Build your profile by getting reviews and testimonials. An email will be sent to the address you provide.
A sample of that email can be viewed <a style="color: blue;" "onmouseover="" onmouseout="" onclick="openPopup('ddrPop13');">here.</a>
</p>
<hr/>
<table>
<tr>
<td class="popcolpad" style="vertical-align: top;">
<b>Job title*</b><br/>
<input type="text" class="forminput1" name="jobtitleexp"><br/>
<b>Start Date*</b> (MM-DD-YYYY)<br/>
<input type="text" class="forminput1" name="startexp"><br/>
<b>End Date*</b> (MM-DD-YYYY)<br/>
<input type="text" class="forminput1" name="endexp"><br/>
<b>Contact Email*</b><br/>
<input type="text" class="forminput1" name="mgremailexp"><br/>
<b>Confirm Contact Email*</b><br/>
<input type="text" class="forminput1" name="mgremailexp2"><br/>
<b>Description*</b> (128 char. max)<br/>
<textarea class="formtextarea2" name="descexp" maxlength="128"></textarea><br/>
</td>
<td class="popcolpad" style="vertical-align: top;">

<b>Choose two measurable skills you wish to be rated on*</b><br/>
<select class="formselect1" style="height: 80px;" name="hardskillsexp" id="ddrHardskillsexp" onclick="limitSelections();" multiple>
<%
int s = 1;
List<String> hrdSkills = SkillService.getHardSkills(profile.getCategory1(), profile.getCategory2());
for (String skill : hrdSkills) {
%>
<option value="<%=skill %>"><%=skill %></option>
<%
s++;
}
%>
</select>
<input type="hidden" name="boxes5" value="<%=hrdSkills.size() %>" />
<input type="hidden" name="userEmail" value="<%=profile.getEmail() %>" />
<br/><br/>
<b>Choose two interpersonal skills you wish to be rated on*</b><br/>
<select class="formselect1" style="height: 80px;" name="softskillsexp" id="ddrSoftskillsexp" onclick="limitSelections();" multiple>
<%
int u = 1;
List<String> sSkills = SkillService.getSoftSkills();
for (String skill : sSkills) {
%>
<option value="<%=skill %>"><%=skill %></option>
<%
u++;
}
%>
</select>
</td>
</tr>
</table>
<div id="ddrPop5Msg" style="height: 13px; width: 505px; text-align: center; line-height: 23px; font-size: 13px; font-weight: bold; color: rgba(111, 0, 255, 1); vertical-align: middle: margin-top: 5px;"></div>
<button type="button" class="popupsubmit" onclick="if(validateAddWrkForm()){ processForm2('ddrPopForm5'); closePopup('ddrPop5');}">Submit</button>
<button type="button" class="popupclose" onclick="closePopup('ddrPop5')">Close</button>
</form>
</div>
<!-- ************************************************************************************************** -->
<!-- ************************************************************************************************** -->
<!-- ************************************************************************************************** -->


<!-- ********************************************  15  ************************************************ -->
<!-- ************************************* REQUEST TESTIMONIAL **************************************** -->
<!-- ************************************************************************************************** -->
<div id="ddrPop15">
<form id="ddrPopForm15" name="formdash15" class="popup">
<input type="hidden" name="query" value="15" />
<input type="hidden" name="twid" value="<%=twid %>" />
<h6 class="formheader">Request Testimonial</h6>
<hr/>
<p class="popupInst">
Build your profile by getting reviews and testimonials. A notification will be sent to the Teamwrkr you specify simply
asking if they would write you a testimonial based on the title and description you provide when filling out this form.
</p>
<hr/>
<table>
<tr>
<td class="popcolpad" style="vertical-align: top;">
<b>Job title*</b><br/>
<input type="text" class="forminput1" name="jobtitletest"><br/>
<b>Start Date*</b> (MM-DD-YYYY)<br/>
<input type="text" class="forminput1" name="starttest"><br/>
<b>End Date*</b> (MM-DD-YYYY)<br/>
<input type="text" class="forminput1" name="endtest"><br/>
<b>Contact Email*</b><br/>
<input type="text" class="forminput1" name="mgremailtest"><br/>
<b>Confirm Contact Email*</b><br/>
<input type="text" class="forminput1" name="mgremailtest2"><br/>
<b>Description*</b> (256 char. max)<br/>
<textarea class="formtextarea2" name="desctest" maxlength="256"></textarea><br/>
</td>

</tr>
</table>
<div id="ddrPop15Msg" style="height: 13px; width: 505px; text-align: center; line-height: 23px; font-size: 13px; font-weight: bold; color: rgba(111, 0, 255, 1); vertical-align: middle: margin-top: 5px;"></div>
<button type="button" class="popupsubmit" onclick="if(validateAddWrkForm()){ processForm2('ddrPopForm15'); closePopup('ddrPop15');}">Submit</button>
<button type="button" class="popupclose" onclick="closePopup('ddrPop15')">Close</button>
</form>
</div>
<!-- ************************************************************************************************** -->
<!-- ************************************************************************************************** -->
<!-- ************************************************************************************************** -->






<!-- ************************************************ 13 ************************************************ -->
<!-- *************************************** SAMPLE SURVEY EMAIL **************************************** -->
<!-- **************************************************************************************************** -->

<div id="ddrPop13">
<form id="ddrPopForm13" name="formdash13" class="popup">
<input type="hidden" name="query" value="13" />
<input type="hidden" name="twid" value="<%=twid %>" />
<h6 class="formheader">Sample Email</h6>
<hr/>
<p class="popupInst">
Below is a sample of the email that will be sent to any potential references.
</p>
<hr/>
<div style="background: rgba(200, 200, 188, 1); font-weight: bold; font-style: italic;">
<p>
I have created a Dynamic Digital Profile with Teamwrkr - 
a platform that helps freelancers like me work with people like you!  
</p>
<p>
I am asking that you help me by providing a review of the work I have done for you. 
This will be a short and confidential web-based review.
</p>
<p>
You should know that I won&apos;t see your review.
Teamwrkr aggregates reviews and your individual responses will be kept confidential. 
They also enhance confidentiality by making sure no reporting is done unless at least 3 people provide reviews of my work.
</p>
<p>Please note that I am asking you to respond as an individual, 
not as a representative of any company or organization.</p>
<p>
The process is quick and easy with only a few questions. 
Please click here to complete the review: 
<span style="color: blue; text-decoration: underline;">Performance Review Survey for <%=profile.getFirstName() %> <%=profile.getLastName() %></span>
</p>
<p>Please contact Teamwrkr by e-mail at help@teamwrkr.com if you need any technical assistance.</p>
<p>If you have any questions, please contact me at <%=profile.getEmail() %></p>
<p>Thanks!</p>
</div>
<button type="button" class="popupclose1" onclick="closePopup('ddrPop13')">Close</button>
</form>
</div>
<!-- **************************************************************************************************** -->
<!-- **************************************************************************************************** -->
<!-- **************************************************************************************************** -->









<%

CalInfo ci = CalInfoService.selectCalInfo(twid);

int[] calDates = new int[28];
try {
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	Calendar cal = Calendar.getInstance();
	DecimalFormat df = new DecimalFormat("00");
	String startCalendar = ci.getStartYear() + "-" + df.format(ci.getStartMonth()) + "-" + df.format(ci.getStartDate());
	cal.setTime(formatter.parse(startCalendar));
	calDates[0] = cal.get(Calendar.DATE);
	for (int i = 1; i < 28; i++) {
		cal.add(Calendar.DATE, 1);
		calDates[i] = cal.get(Calendar.DATE);
	}
}
catch (ParseException e) {
	System.out.println("ParseException: " + e.getMessage());
}


%>

<!-- ************************************************ 6 *********************************************** -->
<!-- ******************************************* MY CALENDAR ****************************************** -->
<!-- ************************************************************************************************** -->
<div id="ddrPop6">
<form id="ddrPopForm6" name="formdash6" class="popup">
<input type="hidden" name="query" value="6" />
<input type="hidden" name="twid" value="<%=twid %>" />
<h6 class="formheader">My Calendar</h6>
<hr/>
<p class="popupInst">
This is a simple calendar to indicate your general availability over the next 4 weeks. Green is &quot;available&quot;
and red is &quot;unavailable&quot;. Click on a date to change your availability.
</p>
<hr/>
<table id="minicalendar">
<tr>
<th class="tdinnerborder1 calth">N</th>
<th class="tdinnerborder1 calth">M</th>
<th class="tdinnerborder1 calth">T</th>
<th class="tdinnerborder1 calth">W</th>
<th class="tdinnerborder1 calth">R</th>
<th class="tdinnerborder1 calth">F</th>
<th class="tdinnerborder1 calth">S</th>
</tr>
<tr>
<td id="ddrCalDate1" class="tdinnerborder calheadrow" ><%=calDates[0] %>
<input type="hidden" id="ddrCalDay1" name="calday1" value="<%=avmx[0] %>" />
</td>
<td id="ddrCalDate2" class="tdinnerborder calheadrow" ><%=calDates[1] %>
<input type="hidden" id="ddrCalDay2"  name="calday2" value="<%=avmx[1] %>" />
</td>
<td id="ddrCalDate3" class="tdinnerborder calheadrow" ><%=calDates[2] %>
<input type="hidden" id="ddrCalDay3"  name="calday3" value="<%=avmx[2] %>" />
</td>
<td id="ddrCalDate4" class="tdinnerborder calheadrow" ><%=calDates[3] %>
<input type="hidden" id="ddrCalDay4"  name="calday4" value="<%=avmx[3] %>" />
</td>
<td id="ddrCalDate5" class="tdinnerborder calheadrow" ><%=calDates[4] %>
<input type="hidden" id="ddrCalDay5"  name="calday5" value="<%=avmx[4] %>" />
</td>
<td id="ddrCalDate6" class="tdinnerborder calheadrow" ><%=calDates[5] %>
<input type="hidden" id="ddrCalDay6"  name="calday6" value="<%=avmx[5] %>" />
</td>
<td id="ddrCalDate7" class="tdinnerborder calheadrow" ><%=calDates[6] %>
<input type="hidden" id="ddrCalDay7"  name="calday7" value="<%=avmx[6] %>" />
</td>
</tr>
<tr>
<td id="ddrCalDate8" class="tdinnerborder" ><%=calDates[7] %>
<input type="hidden" id="ddrCalDay8"  name="calday8" value="<%=avmx[7] %>" />
</td>
<td id="ddrCalDate9" class="tdinnerborder" ><%=calDates[8] %>
<input type="hidden" id="ddrCalDay9"  name="calday9" value="<%=avmx[8] %>" />
</td>
<td id="ddrCalDate10" class="tdinnerborder" ><%=calDates[9] %>
<input type="hidden" id="ddrCalDay10"  name="calday10" value="<%=avmx[9] %>" />
</td>
<td id="ddrCalDate11" class="tdinnerborder" ><%=calDates[10] %>
<input type="hidden" id="ddrCalDay11"  name="calday11" value="<%=avmx[10] %>" />
</td>
<td id="ddrCalDate12" class="tdinnerborder" ><%=calDates[11] %>
<input type="hidden" id="ddrCalDay12"  name="calday12" value="<%=avmx[11] %>" />
</td>
<td id="ddrCalDate13" class="tdinnerborder" ><%=calDates[12] %>
<input type="hidden" id="ddrCalDay13"  name="calday13" value="<%=avmx[12] %>" />
</td>
<td id="ddrCalDate14" class="tdinnerborder" ><%=calDates[13] %>
<input type="hidden" id="ddrCalDay14"  name="calday14" value="<%=avmx[13] %>" />
</td>
</tr>
<tr>
<td id="ddrCalDate15" class="tdinnerborder" ><%=calDates[14] %>
<input type="hidden" id="ddrCalDay15"  name="calday15" value="<%=avmx[14] %>" />
</td>
<td id="ddrCalDate16" class="tdinnerborder" ><%=calDates[15] %>
<input type="hidden" id="ddrCalDay16"  name="calday16" value="<%=avmx[15] %>" />
</td>
<td id="ddrCalDate17" class="tdinnerborder" ><%=calDates[16] %>
<input type="hidden" id="ddrCalDay17"  name="calday17" value="<%=avmx[16] %>" />
</td>
<td id="ddrCalDate18" class="tdinnerborder" ><%=calDates[17] %>
<input type="hidden" id="ddrCalDay18"  name="calday18" value="<%=avmx[17] %>" />
</td>
<td id="ddrCalDate19" class="tdinnerborder" ><%=calDates[18] %>
<input type="hidden" id="ddrCalDay19"  name="calday19" value="<%=avmx[18] %>" />
</td>
<td id="ddrCalDate20" class="tdinnerborder" ><%=calDates[19] %>
<input type="hidden" id="ddrCalDay20"  name="calday20" value="<%=avmx[19] %>" />
</td>
<td id="ddrCalDate21" class="tdinnerborder" ><%=calDates[20] %>
<input type="hidden" id="ddrCalDay21"  name="calday21" value="<%=avmx[20] %>" />
</td>
</tr>
<tr>
<td id="ddrCalDate22" class="tdinnerborder" ><%=calDates[21] %>
<input type="hidden" id="ddrCalDay22"  name="calday22" value="<%=avmx[21] %>" />
</td>
<td id="ddrCalDate23" class="tdinnerborder" ><%=calDates[22] %>
<input type="hidden" id="ddrCalDay23"  name="calday23" value="<%=avmx[22] %>" />
</td>
<td id="ddrCalDate24" class="tdinnerborder" ><%=calDates[23] %>
<input type="hidden" id="ddrCalDay24"  name="calday24" value="<%=avmx[23] %>" />
</td>
<td id="ddrCalDate25" class="tdinnerborder" ><%=calDates[24] %>
<input type="hidden" id="ddrCalDay25"  name="calday25" value="<%=avmx[24] %>" />
</td>
<td id="ddrCalDate26" class="tdinnerborder" ><%=calDates[25] %>
<input type="hidden" id="ddrCalDay26"  name="calday26" value="<%=avmx[25] %>" />
</td>
<td id="ddrCalDate27" class="tdinnerborder" ><%=calDates[26] %>
<input type="hidden" id="ddrCalDay27"  name="calday27" value="<%=avmx[26] %>" />
</td>
<td id="ddrCalDate28" class="tdinnerborder" ><%=calDates[27] %>
<input type="hidden" id="ddrCalDay28"  name="calday28" value="<%=avmx[27] %>" />
</td>
</tr>
</table>
<button type="button" class="popupsubmit" onclick="processCalendarForm2('ddrPopForm6'); closePopup('ddrPop6');">Submit</button>
<button type="button" class="popupclose" onclick="closePopup('ddrPop6')">Close</button>
</form>
</div>
<!-- *************************************************************************************************** -->
<!-- *************************************************************************************************** -->
<!-- *************************************************************************************************** -->





<!-- ********************************************** 7 ************************************************** -->
<!-- ***************************************** SEE ALL JOBS ******************************************** -->
<!-- *************************************************************************************************** -->
<div id="ddrPop7">
<form id="ddrPopForm7" name="formdash7" class="popup">
<input type="hidden" name="query" value="7" />
<input type="hidden" name="twid" value="<%=twid %>" />
<h6 class="formheader">My WRK History</h6>
<table>
<tr>
<td class="wrkhsta" style="width: 30px;">No.</td>
<td class="wrkhsta" style="width: 120px;">Title</td>
<td class="wrkhsta" style="width: 90px;">Entered</td>
<td class="wrkhsta" style="width: 90px;">Verified</td>
<td class="wrkhsta" style="width: 150px;">Status</td>
</tr>
<%
Mission m1 = new Mission();
Object[] m2 = DataService.selectMany(m1, "twid1 = " + twid);
Mission[] m2s = new Mission[m2.length];
for (int i = 0; i < m2.length; i++) {
	if (m2[i].getClass().getSimpleName().equals("Mission")) {
		Mission m5 = (Mission) m2[i];
		m2s[i] = m5;
		String m5Date1 = "";
		String m5Date2 = "";
		SimpleDateFormat sdfFormatter1 = new SimpleDateFormat("MM.dd.yyyy");
		if (m5.getEnteredDate() != null) {
			Date m5EnteredDate = m5.getEnteredDate();
			m5Date1 = sdfFormatter1.format(m5EnteredDate);
			String m5Verified = "NO";
			Date m5VerifiedDate = new Date();
			if (m5.getReferenceDate() != null) {
				m5VerifiedDate = m5.getReferenceDate();
				m5Verified = sdfFormatter1.format(m5VerifiedDate);
			}
		}
		else {
			if (m5.getPostedDate() != null) {
				Date m5PostedDate = m5.getPostedDate();
				m5Date1 = sdfFormatter1.format(m5PostedDate);
				String m5Verified = "NO";
				Date m5VerifiedDate = new Date();
				if (m5.getReferenceDate() != null) {
					m5VerifiedDate = m5.getReferenceDate();
					m5Verified = sdfFormatter1.format(m5VerifiedDate);
				}
			}
			
		}
		
%>
<tr>
<td class="wrkhst"><%=i+1 %></td>
<td class="wrkhst"><%=m5.getTitle() %></td>
<td class="wrkhst"></td>
<td class="wrkhst"></td>
<td class="wrkhst"></td>
</tr>
<%
	}
	
}
%>
</table>
<button type="button" class="popupclose1" onclick="closePopup('ddrPop7')">Close</button>
</form>
</div>
<!-- *************************************************************************************************** -->
<!-- *************************************************************************************************** -->
<!-- *************************************************************************************************** -->





<!-- ************************************************ 9 ************************************************ -->
<!-- ***************************************** UPLOAD A PHOTO ****************************************** -->
<!-- *************************************************************************************************** -->

<div id="ddrPop9">
<form id="ddrPopForm9" name="formdash9" class="popup">
<h6 class="formheader">Upload A Photo</h6>
<hr/>
<input type="hidden" id="ddrtwid" name="twid" value="<%=twid %>" />
<input type="file" class="formfile1" id="ajaxfile" style="font-size: 12px; font-family: 'Poppins'; padding: 5px; padding-top: 15px; vertical-align: middle;"/><br/>
<button type="button" class="popupsubmit" onclick="uploadFile(); closePopup('ddrPop9');">Submit</button>
<button type="button" class="popupclose" onclick="closePopup('ddrPop9')">Close</button>
</form>
</div>
<!-- *************************************************************************************************** -->
<!-- *************************************************************************************************** -->
<!-- *************************************************************************************************** -->


<!-- *********************************************** 14 ************************************************ -->
<!-- ***************************************** UPLOAD A VIDEO ****************************************** -->
<!-- *************************************************************************************************** -->

<div id="ddrPop14">
<form id="ddrPopForm14" name="formdash14" class="popup">
<input type="hidden" name="query" value="13" />
<input type="hidden" name="twid" value="<%=twid %>" />
<h6 class="formheader">Upload Video</h6>
<hr/>
<p class="popupInst">
Upload a video (30 seconds or less) letting people know a little more about you. Press the play button at the bottom
left of the video player to begin recording.
</p>
<hr/>
<video id="player" controls></video>

<button type="button" class="popupsubmit" onclick="uploadFile(); closePopup('ddrPop14');">Submit</button>
<button type="button" class="popupclose" onclick="closePopup('ddrPop14')">Close</button>
</form>
</div>
<!-- *************************************************************************************************** -->
<!-- *************************************************************************************************** -->
<!-- *************************************************************************************************** -->




<!-- ************************************************ 1 ************************************************ -->
<!-- *****************************************   POST A JOB   ****************************************** -->
<!-- *************************************************************************************************** -->
<form id="ddrIframeForm1" name="formIframe1">
<iframe id="postframe" style="display: none;" src="/tw1/post1.jsp" scrolling="no"></iframe>
</form>
<!-- *************************************************************************************************** -->
<!-- *************************************************************************************************** -->
<!-- *************************************************************************************************** -->






<%
System.out.println("$_$_$_$_$_$_$_$_$_$_$_$_$_$_$_\\=====[ddr.jsp]=====/_$_$_$_$_$_$_$_$_$_$_$_$_$_$_$");
%>

</body>
</html>
