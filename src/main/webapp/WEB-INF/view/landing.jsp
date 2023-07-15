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
	 com.teamwrkr.app.service.*,
	 com.teamwrkr.app.util.WebUtils,
	 com.teamwrkr.app.manager.*,
	 java.text.ParseException,
	 java.text.SimpleDateFormat,
	 java.text.DecimalFormat"
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome to Teamwrkr</title>
<link href="/tw1/img/favicon.ico" rel="shortcut icon" type="image/x-icon" />
<link rel="stylesheet" href="/tw1/css/twddr2.css">
<link rel="stylesheet" href="/tw1/css/landing.css">
<link rel="stylesheet" href="/tw1/css/menubar.css">
<link rel="stylesheet" href="/tw1/css/twwin.css">
<script src="/tw1/js/rolo.js"></script>
<script src="/tw1/js/opps.js"></script>
<script src="/tw1/js/video.js"></script>
<script src="/tw1/js/mb.js"></script>
<script src="/tw1/js/forms.js"></script>
<style>

#tipframe {
	position: absolute;
	display: none;
	z-index: 201;
	top: 80px;
	left: 0px;
	height: 716px;
	width: 716px;
	border-radius: 30px;
	border: 0px;
}

</style>
</head>
<body>

<%
System.out.println("$^$^$^$^$^$^$^$^$^$^$^$^$^$^$^/===[landing.jsp]===\\^$^$^$^$^$^$^$^$^$^$^$^$^$^$^$");

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
String photoSrc = "";
if (profile.getPhotoImg() != null) {
	avatarBgPhoto = true;
	photoSrc = "/tw1/img/" + profile.getPhotoImg();
}
 
/*
 * Populate the rolodex.
 */
Profile roloCurrentProfile = (Profile) session.getAttribute("rolodexProfile");
Profile roloNextProfile = (Profile) session.getAttribute("rolodexNextProfile");
Profile roloPrevProfile = (Profile) session.getAttribute("rolodexPrevProfile");
String rolodexIndex = (String) session.getAttribute("rolodexIndex");
int roloIndex = Integer.parseInt(rolodexIndex);
//System.out.println("roloCurrentProfile.getTwid()=" + roloCurrentProfile.getTwid());

Scores s1 = new Scores();
s1.setTwid(roloCurrentProfile.getTwid());
Scores roloCurrentScores = (Scores) DataService.selectOne(s1);

Points pts1 = new Points();
pts1.setTwid(roloCurrentProfile.getTwid());
Points roloCurrentPoints = (Points) DataService.selectOne(pts1);
int roloUserPts = roloCurrentPoints.getMisc();

//int roloUserTotalPts = ScoreService.calculateOverallPoints(roloCurrentProfile.getTwid(), roloCurrentScores);
int roloUserTotalPts = s1.getScoreOverall();

Scores s2 = new Scores();
s2.setTwid(roloNextProfile.getTwid());
Scores roloNextScores = (Scores) DataService.selectOne(s2);
//int roloNextUserTotalPts = ScoreService.calculateOverallPoints(roloNextProfile.getTwid(), roloNextScores);
int roloNextUserTotalPts = s2.getScoreOverall();

Scores s3 = new Scores();
s3.setTwid(roloPrevProfile.getTwid());
Scores roloPrevScores = (Scores) DataService.selectOne(s3);
//int roloPrevUserTotalPts = ScoreService.calculateOverallPoints(roloPrevtProfile.getTwid(), roloPrevScores);
int roloPrevUserTotalPts = s3.getScoreOverall();


String[] hardSkill = new String[10];
int[] hardSkillScore = new int[10];
Map<String, Integer> hardSkillsMap = ScoreService.getHardSkillScoresMapDesc(roloCurrentScores);
int i = 0;
for (Map.Entry<String, Integer> entry : hardSkillsMap.entrySet()) {
	hardSkill[i] = entry.getKey();
	hardSkillScore[i] = entry.getValue();
	//System.out.println("skill map get " + hardSkill[i] + " " + hardSkillScore[i]);
	i++;
}

String[] softSkill = new String[10];
int[] softSkillScore = new int[10];
int j = 9;
Map<String, Integer> softSkillsMap = ScoreService.getSoftSkillScoresMap(roloCurrentScores);
for (Map.Entry<String, Integer> entry : softSkillsMap.entrySet()) {
	softSkill[j] = entry.getKey();
	softSkillScore[j] = entry.getValue();
	j--;
}

String roloNextLetter = WebUtils.getNextLetter(roloNextProfile.getLastName());
String roloPrevLetter = WebUtils.getFirstLetter(roloPrevProfile.getLastName());


/*
 * Populate the job slider.
 */
List<Mission> sliderOpps = (List<Mission>) session.getAttribute("sliderOpps");
int sliderBlock = (int) session.getAttribute("sliderBlock");
int sliderBeginIndex = (sliderBlock*7) - 7;
int sliderEndIndex = sliderBeginIndex + 7;

String[] oppTitle = new String[7];
String[] oppDesc = new String[7];
String[] oppCat1 = new String[7];
String[] oppCat2 = new String[7];
Date[] oppStartDate = new Date[7];
Date[] oppPostedDate = new Date[7];
int[] oppRate = new int[7];
String[] oppCompType = new String[7];
String[] oppSoftSkill1 = new String[7];
String[] oppSoftSkill2 = new String[7];
String[] oppSoftSkill3 = new String[7];
String[] oppHardSkill1 = new String[7];
String[] oppHardSkill2 = new String[7];
String[] oppHardSkill3 = new String[7];
String[] oppManager = new String[7];
String[] oppProfileAvatar = new String[7];

int sliderDisplayIndex = 0;
List<Mission> sliderOpps2Display = new ArrayList<Mission>();
for (int n = sliderBeginIndex; n < sliderEndIndex; n++) {
	if (n < sliderOpps.size() && n >= 0) {
		Mission opp2Display = sliderOpps.get(n);
		if (opp2Display != null) {
			oppTitle[sliderDisplayIndex] = opp2Display.getTitle();
			oppDesc[sliderDisplayIndex] = opp2Display.getDesc();
			oppCat1[sliderDisplayIndex] = opp2Display.getCategory1();
			oppCat2[sliderDisplayIndex] = opp2Display.getCategory2();
			oppManager[sliderDisplayIndex] = opp2Display.getManager();
			oppStartDate[sliderDisplayIndex] = opp2Display.getStartDate();
			oppPostedDate[sliderDisplayIndex] = opp2Display.getPostedDate();
			oppRate[sliderDisplayIndex] = opp2Display.getRate();
			int type = opp2Display.getType();
			if (type == 0) {
				oppCompType[sliderDisplayIndex] = "per hour";
			}
			if (type == 1) {
				oppCompType[sliderDisplayIndex] = "upon completion";
			}
			if (opp2Display.getSoftskill1() != null) {
				oppSoftSkill1[sliderDisplayIndex] = opp2Display.getSoftskill1();
			}
			if (opp2Display.getSoftskill2() != null) {
				oppSoftSkill2[sliderDisplayIndex] = opp2Display.getSoftskill2();
			}
			if (opp2Display.getSoftskill3() != null) {
				oppSoftSkill3[sliderDisplayIndex] = opp2Display.getSoftskill3();
			}
			if (opp2Display.getHardskill1() != null) {
				oppHardSkill1[sliderDisplayIndex] = opp2Display.getHardskill1();
			}
			if (opp2Display.getHardskill2() != null) {
				oppHardSkill2[sliderDisplayIndex] = opp2Display.getHardskill2();
			}
			if (opp2Display.getHardskill3() != null) {
				oppHardSkill3[sliderDisplayIndex] = opp2Display.getHardskill3();
			}
			
			/*
			 * Get profile of job poster to get any additional
			 * information to be displayed in the opp slider.
			 * In this case, the profile photo.
			 */
			Profile oppPf = new Profile();
			oppPf.setTwid(opp2Display.getTwid1());
			Profile p2 = (Profile) DataService.selectOne(oppPf);
			if (p2 != null && p2.getPhotoImg() != null) {
				oppProfileAvatar[sliderDisplayIndex] = p2.getPhotoImg();
			}
			else {
				oppProfileAvatar[sliderDisplayIndex] = "logo-on-black-001.png";
			}
			
			
			sliderDisplayIndex++;
		}
	}
}

Date today = Calendar.getInstance().getTime();

//List<Profile> sliderOppPosterProfiles = new ArrayList<Profile>();
//for (int n = 0; n < 7; n++) {
	//Profile profile = new Profile();
	//profile.setTwid(sliderOpps.get(n).getTwid2());
	//Profile p = (Profile) DataService.selectOne(profile);
	//sliderOppPosterProfiles.add(p);
//}


/*
 * Count notifications here.
 */
int notifications = 0;
String notices = "";
if (notifications > 0) {
	notices = Integer.toString(notifications);
}
		 
%>
<%@include file="menubar.jsp"%>
<!-- ^ PAGE CONTAINER DIV (1) -->
<div class="main">




<div id="rolodexdiv">
<h3>TEAMWRKR COMMUNITY MEMBERS</h3>
<table id="rolodexmain">

<tr>
<td class="rolodex1"><button class="rolotab2" onclick="updateRolodex('ldg2-rolodex-hidden', 'back', '1');" style="height: 45px; width: 45px; margin: 8px;">&lt;</button></td>
<td class="rolodex2">
<div class="rolodiv2" style="line-height: 285px; font-weight: bold;">
<%=roloPrevLetter %>
</div>
</td>
<td class="rolodex3">
<div class="rolodiv3">


<div class="rolodiv3content" style="background: url(/tw1/img/rolo-bg-<%=WebUtils.getRandomBetween(1, 28) %>.png);">

<div class="rolodiv3NameDiv">
<span id="ldg2-fn-prev"><%=roloPrevProfile.getFirstName() %></span>
<span id="ldg2-ln-prev"><%=roloPrevProfile.getLastName() %></span>
</div>
<div class="rolodiv3LocDiv">
<span id="ldg2-city-prev"><%=roloPrevProfile.getCity() %>,</span>
<span id="ldg2-state-prev"><%=roloPrevProfile.getState() %></span>
<span id="ldg2-country-prev"><%=roloPrevProfile.getCountry() %></span>
</div>

<div class="rolodiv3MarqueeDiv">

<div class="rolodiv3PhotoDiv">

<div class="rolodiv3PhotoInnerDiv">
<img id="rolodiv3PhotoImg" src="/tw1/img/<%=roloPrevProfile.getPhotoImg() %>" />
<div class="verttextleft">
<span id="ldg2-totpts"><%=String.format("%,d", roloPrevUserTotalPts) %></span>
</div>
</div>
</div>
</div>
<div class="rolodiv3Cat1Div">
<span id="ldg2-cat1-prev"><%=roloPrevProfile.getCategory1() %></span>
</div>
<div class="rolodiv3Cat2Div">
<span id="ldg2-cat2-prev"><%=roloPrevProfile.getCategory2() %></span>
</div>

</div>
</div>

</td>

<td class="rolodex4">
<table id="roloTnTable">
<tr>
<td class="roloTn1">

<div 
class="roloTn1nameDiv"
>
<span 
id="ldg2-fn" 
style="cursor: pointer;"
onclick="showIframe2('profileframe');"><%=roloCurrentProfile.getFirstName() %></span>
<span 
id="ldg2-ln" 
style="cursor: pointer;"
onclick="showIframe2('profileframe');"><%=roloCurrentProfile.getLastName() %></span>
</div>
<div class="roloTn1locDiv">
<span id="ldg2-city"><%=roloCurrentProfile.getCity() %>,</span>
<span id="ldg2-state"><%=roloCurrentProfile.getState() %></span>
<span id="ldg2-country"><%=roloCurrentProfile.getCountry() %></span>
</div>
<div class="roloTn1marqueeDiv">
<div class="roloTn1PtsDiv">
<span id="ldg2-totpts"><%=String.format("%,d", roloUserPts) %></span>
</div>
<div class="roloTn1PhotoDiv">
<div class="roloTn1PhotoInnerDiv">
<img id="roloTn1PhotoImg" src="/tw1/img/<%=roloCurrentProfile.getPhotoImg() %>" /></div>
</div>

</div>
</div>
<div class="roloTn1cat1Div">
<span id="ldg2-cat1"><%=roloCurrentProfile.getCategory1() %></span>
</div>
<div class="roloTn1cat2Div">
<span id="ldg2-cat2"><%=roloCurrentProfile.getCategory2() %></span>
</div>

</td>
<td class="roloTn2">
<div class="roloTn2NicheDiv">ABOUT ME
<div class="roloTn2NicheInnerDiv">
<div id="ldg2-niche" style="width: 100%; height: 87px; margin: auto; padding-top: 8px;">
<%=roloCurrentProfile.getNiche() %>
</div>
</div>

</div>
<div class="roloTn2MidDiv"></div>
<div class="roloTn2SkillsDiv">SKILLS
<div class="roloTn2SkillsInnerDiv">


<table id="roloTn2SkillsTable">
<tr>
<td style="width: 50%; margin: auto; ">
<div class="roloTn2Skill1Div" style="margin-top: 8px;">
<div class="roloTn2SkillName1">
<span id="ldg2-ms1name"><%=hardSkill[0] %></span>
</div>
<div class="roloTn2SkillLevelfg1" style="width: <%=Math.floorDiv(hardSkillScore[0], 3) %>px;"></div>
<div class="roloTn2SkillLevelbg1"></div>
<div class="roloTn2SkillLevelPts1">
<span id="ldg2-ms1pts"><%=hardSkillScore[0]  %></span>
</div>
</div>

<div class="roloTn2Skill1Div">
<div class="roloTn2SkillName1">
<span id="ldg2-ms2name"><%=hardSkill[1] %></span>
</div>
<div class="roloTn2SkillLevelfg1" style="width: <%=Math.floorDiv(hardSkillScore[1], 3) %>px;"></div>
<div class="roloTn2SkillLevelbg1"></div>
<div class="roloTn2SkillLevelPts1">
<span id="ldg2-ms2pts"><%=hardSkillScore[1]  %></span>
</div>
</div>

<div class="roloTn2Skill1Div">
<div class="roloTn2SkillName1">
<span id="ldg2-ms3name"><%=hardSkill[2] %></span>
</div>
<div class="roloTn2SkillLevelfg1" style="width: <%=Math.floorDiv(hardSkillScore[2], 3) %>px;"></div>
<div class="roloTn2SkillLevelbg1"></div>
<div class="roloTn2SkillLevelPts1">
<span id="ldg2-ms3pts"><%=hardSkillScore[2]  %></span>
</div>
</div>
</td>
<td style="width: 50%; margin: auto;">

<div class="roloTn2Skill1Div" style="margin-top: 8px;">
<div class="roloTn2SkillName2">
<span id="ldg2-ips1name"><%=softSkill[0] %></span>
</div>
<div class="roloTn2SkillLevelfg2" style="width: <%=Math.floorDiv(softSkillScore[0], 3) %>px;"></div>
<div class="roloTn2SkillLevelbg2"></div>
<div class="roloTn2SkillLevelPts2">
<span id="ldg2-ips1pts"><%=softSkillScore[0]  %></span>
</div>
</div>

<div class="roloTn2Skill1Div">
<div class="roloTn2SkillName2">
<span id="ldg2-ips2name"><%=softSkill[1] %></span>
</div>
<div class="roloTn2SkillLevelfg2" style="width: <%=Math.floorDiv(softSkillScore[1], 3) %>px;"></div>
<div class="roloTn2SkillLevelbg2"></div>
<div class="roloTn2SkillLevelPts2">
<span id="ldg2-ips2pts"><%=softSkillScore[1]  %></span>
</div>
</div>

<div class="roloTn2Skill1Div">
<div class="roloTn2SkillName2">
<span id="ldg2-ips3name"><%=softSkill[2] %></span>
</div>
<div class="roloTn2SkillLevelfg2" style="width: <%=Math.floorDiv(softSkillScore[2], 3) %>px;"></div>
<div class="roloTn2SkillLevelbg2"></div>
<div class="roloTn2SkillLevelPts2">
<span id="ldg2-ips3pts"><%=softSkillScore[2]  %></span>
</div>
</div>
</td>
</tr>
</table>




</div>
</div>

</td>
</tr>
</table>

</td>
<td class="rolodex5">
<div class="rolodiv5" >
<div class="rolodiv5content" style="background: url(/tw1/img/rolo-bg-<%=WebUtils.getRandomBetween(1, 28) %>.png);">

<div class="rolodiv5InnerContent">

<div class="rolodiv5NameDiv">
<span id="ldg2-fn-prev"><%=roloNextProfile.getFirstName() %></span>
<span id="ldg2-ln-prev"><%=roloNextProfile.getLastName() %></span>
</div>
<div class="rolodiv5LocDiv">
<span id="ldg2-city-prev"><%=roloNextProfile.getCity() %>,</span>
<span id="ldg2-state-prev"><%=roloNextProfile.getState() %></span>
<span id="ldg2-country-prev"><%=roloNextProfile.getCountry() %></span>
</div>

<div class="rolodiv5MarqueeDiv">

<div class="rolodiv5PhotoDiv">

<div class="rolodiv5PhotoInnerDiv">
<img id="rolodiv5PhotoImg" src="/tw1/img/<%=roloNextProfile.getPhotoImg() %>" />
<div class="verttextright">
<span id="ldg2-totpts"><%=String.format("%,d", roloNextUserTotalPts) %></span>
</div>
</div>
</div>
</div>
<div class="rolodiv5Cat1Div">
<span id="ldg2-cat1-prev"><%=roloNextProfile.getCategory1() %></span>
</div>
<div class="rolodiv5Cat2Div">
<span id="ldg2-cat2-prev"><%=roloNextProfile.getCategory2() %></span>
</div>

</div>



</div>
</div>
</td>
<td class="rolodex6">
<div class="rolodiv6" style="line-height: 285px; font-weight: bold;">
<%=roloNextLetter %>
</div>
</td>
<td class="rolodex7"><button onclick="updateRolodex('ldg2-rolodex-hidden', 'fwd', '1');" class="rolotab2" style="height: 45px; width: 45px; margin: 8px;">&gt;</button></td>
</tr>
</table>

</div>

<div id="rolotabdiv"> 
<button class="rolotab" >#ux/ui design</button>
<button class="rolotab" >#art/illustration</button>
<button class="rolotab" >#branding</button>
<button class="rolotab" >#copywriting/editing</button>
<button class="rolotab" >#graphic design</button>
</div>

</div>
<!-- $ (1) -->




<!-- ----------------------------------------------------------------------------------------------------- -->
<!-- ----------------------------------------------------------------------------------------------------- -->
<!-- ----------------------------------------------------------------------------------------------------- -->
<!-- ----------------------------------------------------------------------------------------------------- -->
<!-- ----------------------------------------------------------------------------------------------------- -->
<!-- ----------------------------------------------------------------------------------------------------- -->


<h3>FEATURED OPPORTUNITIES</h3>

<div class="ldg2-opps-nav">
<div class="ldg2-opps-left-div">
<button onclick="updateOppSlider('ldg2-oppslider-hidden', 'back', '1');" class="rolotab2" style="height: 35px; width: 35px; margin: 115px 0px;">&lt;</button>
</div>



<div class="ldg2-opps">
<table id="opps-tns">
<tr>

<%
if (oppTitle[0] != null) {
%>
<td class="opps-tn-td">
<div class="opps-tn-div">
<div class="opps-tn-div1"></div>
<div class="opps-tn-overlay">
<div class="opps-tn-overlay-div1">
<div class="opps-title"><%=oppTitle[0] %></div>
<div class="opps-cat1-div"><%=oppCat1[0] %></div>
<div class="opps-cat2-div"><%=oppCat2[0] %></div>
</div>
<div class="opps-tn-overlay-div2">
<div style="margin: 5px auto; height: 70px;">
<img 
style="border: 6px solid; border-style: double; border-color: rgba(111, 111, 111, 0.88);" 
src="/tw1/img/<%=oppProfileAvatar[0] %>" 
class="opps-avatar-img" />
</div>
<div class="opps-poster-div"></div>
<div class="opps-desc-div">posted by: <%=oppManager[0] %></div>

</div>
<div class="opps-tn-overlay-div3">
posted: <%=oppPostedDate[0].toString() %>
</div>
</div>
</div>
</td>
<%
}
%>



<%
if (oppTitle[1] != null) {
%>
<td class="opps-tn-td">
<div class="opps-tn-div">
<div class="opps-tn-div1"></div>
<div class="opps-tn-overlay">
<div class="opps-tn-overlay-div1">
<div class="opps-title"><%=oppTitle[1] %></div>
<div class="opps-cat1-div"><%=oppCat1[1] %></div>
<div class="opps-cat2-div"><%=oppCat2[1] %></div>
</div>
<div class="opps-tn-overlay-div2">
<div style="margin: 5px auto; height: 70px;">
<img 
style="border: 6px solid; border-style: double; border-color: rgba(111, 111, 111, 0.88);" 
src="/tw1/img/<%=oppProfileAvatar[1] %>" 
class="opps-avatar-img" />
</div>
<div class="opps-poster-div"></div>
<div class="opps-desc-div">posted by: <%=oppManager[1] %></div>

</div>
<div class="opps-tn-overlay-div3">
posted: <%=oppPostedDate[1].toString() %>
</div>
</div>
</div>
</td>
<%
}
%>


<%
if (oppTitle[2] != null) {
%>
<td class="opps-tn-td">
<div class="opps-tn-div">
<div class="opps-tn-div1"></div>
<div class="opps-tn-overlay">
<div class="opps-tn-overlay-div1">
<div class="opps-title"><%=oppTitle[2] %></div>
<div class="opps-cat1-div"><%=oppCat1[2] %></div>
<div class="opps-cat2-div"><%=oppCat2[2] %></div>
</div>
<div class="opps-tn-overlay-div2">
<div style="margin: 5px auto; height: 70px;">
<img 
style="border: 6px solid; border-style: double; border-color: rgba(111, 111, 111, 0.88);" 
src="/tw1/img/<%=oppProfileAvatar[2] %>" 
class="opps-avatar-img" />
</div>
<div class="opps-poster-div"></div>
<div class="opps-desc-div">posted by: <%=oppManager[2] %></div>

</div>
<div class="opps-tn-overlay-div3">
posted: <%=oppPostedDate[2].toString() %>
</div>
</div>
</div>
</td>
<%
}
%>


<%
if (oppTitle[3] != null) {
%>
<td class="opps-tn-td">
<div class="opps-tn-div">
<div class="opps-tn-div1"></div>
<div class="opps-tn-overlay">
<div class="opps-tn-overlay-div1">
<div class="opps-title"><%=oppTitle[3] %></div>
<div class="opps-cat1-div"><%=oppCat1[3] %></div>
<div class="opps-cat2-div"><%=oppCat2[3] %></div>
</div>
<div class="opps-tn-overlay-div2">
<div style="margin: 5px auto; height: 70px;">
<img 
style="border: 6px solid; border-style: double; border-color: rgba(111, 111, 111, 0.88);" 
src="/tw1/img/<%=oppProfileAvatar[3] %>" 
class="opps-avatar-img" />
</div>
<div class="opps-poster-div"></div>
<div class="opps-desc-div">posted by: <%=oppManager[3] %></div>

</div>
<div class="opps-tn-overlay-div3">
posted: <%=oppPostedDate[3].toString() %>
</div>
</div>
</div>
</td>
<%
}
%>


<%
if (oppTitle[4] != null) {
%>
<td class="opps-tn-td">
<div class="opps-tn-div">
<div class="opps-tn-div1"></div>
<div class="opps-tn-overlay">
<div class="opps-tn-overlay-div1">
<div class="opps-title"><%=oppTitle[4] %></div>
<div class="opps-cat1-div"><%=oppCat1[4] %></div>
<div class="opps-cat2-div"><%=oppCat2[4] %></div>
</div>
<div class="opps-tn-overlay-div2">
<div style="margin: 5px auto; height: 70px;">
<img 
style="border: 6px solid; border-style: double; border-color: rgba(111, 111, 111, 0.88);" 
src="/tw1/img/<%=oppProfileAvatar[4] %>" 
class="opps-avatar-img" />
</div>
<div class="opps-poster-div"></div>
<div class="opps-desc-div">posted by: <%=oppManager[4] %></div>

</div>
<div class="opps-tn-overlay-div3">
posted: <%=oppPostedDate[4].toString() %>
</div>
</div>
</div>
</td>
<%
}
%>


<%
if (oppTitle[5] != null) {
%>
<td class="opps-tn-td">
<div class="opps-tn-div">
<div class="opps-tn-div1"></div>
<div class="opps-tn-overlay">
<div class="opps-tn-overlay-div1">
<div class="opps-title"><%=oppTitle[5] %></div>
<div class="opps-cat1-div"><%=oppCat1[5] %></div>
<div class="opps-cat2-div"><%=oppCat2[5] %></div>
</div>
<div class="opps-tn-overlay-div2">
<div style="margin: 5px auto; height: 70px;">
<img 
style="border: 6px solid; border-style: double; border-color: rgba(111, 111, 111, 0.88);" 
src="/tw1/img/<%=oppProfileAvatar[5] %>" 
class="opps-avatar-img" />
</div>
<div class="opps-poster-div"></div>
<div class="opps-desc-div">posted by: <%=oppManager[5] %></div>

</div>
<div class="opps-tn-overlay-div3">
posted: <%=oppPostedDate[5].toString() %>
</div>
</div>
</div>
</td>
<%
}
%>


<%
if (oppTitle[6] != null) {
%>
<td class="opps-tn-td">
<div class="opps-tn-div">
<div class="opps-tn-div1"></div>
<div class="opps-tn-overlay">
<div class="opps-tn-overlay-div1">
<div class="opps-title"><%=oppTitle[6] %></div>
<div class="opps-cat1-div"><%=oppCat1[6] %></div>
<div class="opps-cat2-div"><%=oppCat2[6] %></div>
</div>
<div class="opps-tn-overlay-div2">
<div style="margin: 5px auto; height: 70px;">
<img 
style="border: 6px solid; border-style: double; border-color: rgba(111, 111, 111, 0.88);" 
src="/tw1/img/<%=oppProfileAvatar[6] %>" 
class="opps-avatar-img" />
</div>
<div class="opps-poster-div"></div>
<div class="opps-desc-div">posted by: <%=oppManager[6] %></div>

</div>
<div class="opps-tn-overlay-div3">
posted: <%=oppPostedDate[6].toString() %>
</div>
</div>
</div>
</td>
<%
}
%>



</tr>
</table>
<div class="ldg2-opps-right-div">
<button onclick="updateOppSlider('ldg2-oppslider-hidden', 'fwd', '1');" class="rolotab2" style="height: 35px; width: 35px; margin: 115px 0px;">&gt;</button>
</div>
</div>


<iframe id="postframe" style="display: none;" src="/tw1/post1.jsp" scrolling="no"></iframe>

<%
if (StatusService.getStatusKeyValue(twid, "wv").equals("0")) {
%>
<div id="welcome-video-container">
<h5>Teamwrkr Tips</h5>
<video id="welcome-video" controls>
<source src="/tw1/video/welcome-001.mp4" type="video/mp4">
</video>
<br/>
<button type="button" id="welcome-video-close" onclick="closeVideo();">Close</button>
</div>
<%
}
%>

<iframe id="profileframe" style="display: none;" src="/tw1/SiteServlet?query=9&twid=<%=roloCurrentProfile.getTwid() %>" scrolling="no"></iframe>


</div>


<!--  -->
<form id="ldg2-rolodex-hidden" name="formRolodexHidden" style="display: none;">
<input type="hidden" name="rolo-index" value="<%=roloIndex %>" />
<input type="hidden" name="rolo-direction" value="" />
<input type="hidden" name="rolo-query" value="1" />
<input type="hidden" name="rolo-filter" value="" />
</form>

<form id="ldg2-oppslider-hidden" name="formOppsliderHidden" style="display: none;">
<input type="hidden" name="opps-block" value="<%=sliderBlock %>" />
<input type="hidden" name="opps-direction" value="" />
<input type="hidden" name="opps-query" value="1" />
<input type="hidden" name="opps-filter" value="" />
</form>

<%@include file="mbhelp.jsp"%>


<%
System.out.println("$_$_$_$_$_$_$_$_$_$_$_$_$_$_$_\\===[landing.jsp]===/_$_$_$_$_$_$_$_$_$_$_$_$_$_$_$");
%>



</body>
</html>