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
<title>Teamwrkr - Profile</title>
<link href="/tw1/img/favicon.ico" rel="shortcut icon" type="image/x-icon" />
<link rel="stylesheet" href="/tw1/css/twprof.css">
<script src="/tw1/js/ddr1.js"></script>
<script src="/tw1/js/ddr2.js"></script>
<script src="/tw1/js/mb.js"></script>
<script src="/tw1/js/forms.js"></script>
</head>
<body>

<%
System.out.println("$^$^$^$^$^$^$^$^$^$^$^$^$^$^$^/=====[profile.jsp]=====\\^$^$^$^$^$^$^$^$^$^$^$^$^$^$^$");

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
	photoSrc = "/tw1/img/photo-not-avail-130x130.png";
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

<div class="main" style="margin-top: 0px;">

<div class="photobg">




<div class="photo1"></div>
<div class="photo2">
<img id="ddrPhoto" 

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




<div class="niche">
<span id="ddrniche"><%=profile.getNiche() %></span>
</div>
</div>



<div class="command">
<div class="subhdr1">ADDITIONAL INFORMATION</div>



<%
Points points1 = new Points();
points1.setTwid(twid);
Points ptsTot = (Points) DataService.selectOne(points1);
int total = ptsTot.getMisc();
int loginPts = PointsManager.calculateLoginPoints(twid);
%>
<div class="profile-total-pts">
<span style="float: left; margin-left: 10px;">Teamwrkr Points:</span>
<span style="float: right; margin-right: 10px;"><%=total %></span> 
</div>

<div class="profile-tw-since">
<span style="float: left; margin-left: 10px;">Teamwrkr Since:</span>
<span style="float: right; margin-right: 10px;"><%=profile.getCreateDate().toString() %></span>
</div>

<div class="profile-logins">
<span style="float: left; margin-left: 10px;">Logins/Month:</span>
<span style="float: right; margin-right: 10px;"> <%=loginPts %></span>
</div>













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

<div class="bottom-div">Click the icons above to visit <%=profile.getFirstName() %>&apos;s social media links.</div>


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


















<%
if (av.getDaysAvailable() == 28) {
%>
<div class="profile-avail-to-work">AVAILABLE TO WORK</div>
<%
}
else {
%>
<div class="profile-unavail-to-work">UNAVAILABLE TO WORK</div>
<%
}
%>










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
<div class="missionhdr"><%=profile.getFirstName() %>&apos;s WRK JOURNEY</div>
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

<div id="closebutton">
<button 
type="button"
onclick="parent.hideIframe('profileframe')"
class="circleclose">x</button>
</div>



</div>

<%
System.out.println("$_$_$_$_$_$_$_$_$_$_$_$_$_$_$_\\=====[profile.jsp]=====/_$_$_$_$_$_$_$_$_$_$_$_$_$_$_$");
%>

</body>
</html>
