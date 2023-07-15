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

<%

String title = request.getParameter("title");
String desc = request.getParameter("desc");
String cat1 = request.getParameter("cat1");
String cat2 = request.getParameter("cat2");
String start = request.getParameter("start");
String rate = request.getParameter("rate");
String comp = request.getParameter("comp");
String skillA1 = request.getParameter("softskill1");
String skillA2 = request.getParameter("softskill2");
String skillA3 = request.getParameter("softskill3");
String skillB1 = request.getParameter("hardskill1");
String skillB2 = request.getParameter("hardskill2");
String skillB3 = request.getParameter("hardskill3");

if (title == null) {
	title = "";
}
if (desc == null) {
	desc = "";
}
if (cat1 == null) {
	cat1 = "";
}
if (cat2 == null) {
	cat2 = "";
}
if (start == null) {
	start = "";
}
if (rate == null) {
	rate = "";
}
if (comp == null) {
	comp = "0";
}
if (skillA1 == null) {
	skillA1 = "";
}
if (skillA2 == null) {
	skillA2 = "";
}
if (skillA3 == null) {
	skillA3 = "";
}
if (skillB1 == null) {
	skillB1 = "";
}
if (skillB2 == null) {
	skillB2 = "";
}
if (skillB3 == null) {
	skillB3 = "";
}

Calendar cal = Calendar.getInstance();
SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
String today = formatter.format(cal.getTime());

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Teamwrkr - Post A Job</title>
<link href="/tw1/img/favicon.ico" rel="shortcut icon" type="image/x-icon" />
<link rel="stylesheet" href="/tw1/css/twtip.css">
<script src="/tw1/js/forms.js"></script>
<script src="/tw1/js/jobpost.js"></script>
<style>

#post-p1,
#post-p2,
#post-p3,
#post-p4,
#post-p5,
#post-p6,
#post-p7,
#post-p8,
#post-p9 {
	display: none;
}

</style>
</head>
<body 
onload="setSelectedIndex('post-comp', '<%=comp %>'); 
setSelects('<%=cat2 %>','<%=skillA1 %>','<%=skillA2 %>','<%=skillA3 %>','<%=skillB1 %>','<%=skillB2 %>','<%=skillB3 %>'); 
enableSelectedSkills();">

<table class="verifyemail-table">
<tr>
<td>

<form name="posthelp" action="SiteServlet" method="get">
<input type="hidden" name="query" value="26" />

<h3>Post A Job</h3>
<h4>Step 6 - Review/Submit</h4>
<p class="instructions">Below are the details provided regarding the opportunity you wish to post on Teamwrkr.
If they are correct, click Submit. To edit, use the Next/Prev navigation buttons. You will notified of 
qualified candidates who best matched your requirements shortly. Thank you for using Teamwrkr.</p>

<p id="post-p1">
<b>*Job Title</b><br/>
<input type="text" id="post-title" name="title" size="40" maxlength="96" value="<%=title %>">
</p>

<p id="post-p2">
<b>*Description</b><br/>
<textarea id="post-desc"  name="desc" maxlength="128" placeholder="128 char. max."><%=desc %></textarea>
</p>

<p id="post-p3">
<b>*Focus</b><br/>
<select id="post-cat1" name="cat1">
<option value="Marketing">Marketing</option>
</select>
</p>

<p id="post-p4">
<b>*Specialty</b><br/>
<select 
id="post-cat2" 
name="cat2"
onchange="populateMeasurableSkillsSelect1();">
<option value="Art/Illustration">Art/Illustration</option>
<option value="Branding">Branding</option>
<option value="Copywriting/Editing">Copywriting/Editing</option>
<option value="Graphic Design">Graphic Design</option>
<option value="Video Editing">Video Editing</option>
<option value="UX/UI Design">UX/UI Design</option>
</select>
</p>

<p id="post-p5">
<label for="start">*Start Date</label>
<br/>
<input type="date" id="post-start" name="start" value="<%=start %>" min="<%=today %>">
</p>

<p id="post-p7">
<b>*Compensation Type</b><br/>
<select id="post-comp" name="comp" onchange="clearRateAmount();">
<option value="0">per hour</option>
<option value="1">upon completion</option>
<option value="2">i don't know</option>
</select>
</p>

<p id="post-p6">
<b>*Rate (USD)</b><br/>
$<input type="number" id="post-rate" name="rate" placeholder="USD" min="10" max="99999" value="<%=rate %>">
</p>

<p id="post-p8">
<%
List<String> sftSkills = SkillService.getSoftSkills();
%>
<b>** Top Interpersonal Skills Needed</b><br/>
<span style="font-size: 11px; line-height: 11px; height: 11px; font-weight: 600;">in order of importance&nbsp;&nbsp; **(1 required/3 desired)</span><br/>
1:&nbsp; 
<select 
name="softskill1" 
id="post-sskill1"
onchange="manageInterpersonalSkillsSelects('post-sskill1');">
<option value="0">Select one</option>
<%
for (String skill : sftSkills) {
%>
<option value="<%=skill %>"><%=skill %></option>
<%
}
%>
</select>
<br/>
2: 
<select 
name="softskill2" 
id="post-sskill2"
onchange="manageInterpersonalSkillsSelects('post-sskill2');" 
disabled>
<option value="0">Select one</option>
<%
for (String skill : sftSkills) {
%>
<option value="<%=skill %>"><%=skill %></option>
<%
}
%>
</select>
<br/>
3: 
<select 
name="softskill3" 
id="post-sskill3"
disabled>
<option value="0">Select one</option>
<%
for (String skill : sftSkills) {
%>
<option value="<%=skill %>"><%=skill %></option>
<%
}
%>
</select>
</p>

<p id="post-p9">
<%
List<String> hrdSkills = SkillService.getHardSkills("Marketing", cat2);
%>
<b>Top 3 Measurable Skills Needed*</b><br/>
<span style="font-size: 11px; line-height: 11px; height: 11px; font-weight: 600;">in order of importance&nbsp;&nbsp; **(1 required/3 desired)</span><br/>
1:&nbsp; 
<select 
name="hardskill1" 
id="post-hskill1" 
onchange="manageMeasurableSkillsSelects('post-hskill1');">
<option value="0">Select one</option>
<%
for (String skill : hrdSkills) {
%>
<option value="<%=skill %>"><%=skill %></option>
<%
}
%>
</select>
<br/>
2: 
<select
name="hardskill2" 
id="post-hskill2"
onchange="manageMeasurableSkillsSelects('post-hskill2');"
disabled>
<option value="0">Select one</option>
<%
for (String skill : hrdSkills) {
%>
<option value="<%=skill %>"><%=skill %></option>
<%
}
%>
</select>
<br/>
3: 
<select 
name="hardskill3" 
id="post-hskill3"
disabled>
<option value="0">Select one</option>
<%
for (String skill : hrdSkills) {
%>
<option value="<%=skill %>"><%=skill %></option>
<%
}
%>
</select>
</p>

<%
String compText = "";
if (comp.equals("0")) {
	compText = "hourly";
}
if (comp.equals("1")) {
	compText = "upon completion";
}
if (comp.equals("2")) {
	compText = "unknown";
}
%>

<table class="summaryTable">
<tr>
<th style="width: 50%;">Item</th>
<th style="width: 50%;">Value</th>
</tr>
<tr>
<td class="summaryTableTd1">Title</td>
<td class="summaryTableTd2"><%=title %></td>
</tr>
<tr>
<td class="summaryTableTd1">Description</td>
<td class="summaryTableTd2"><%=desc %></td>
</tr>
<tr>
<td class="summaryTableTd1">Focus</td>
<td class="summaryTableTd2"><%=cat1 %></td>
</tr>
<tr>
<td class="summaryTableTd1">Specialty</td>
<td class="summaryTableTd2"><%=cat2 %></td>
</tr>
<tr>
<td class="summaryTableTd1">Start Date</td>
<td class="summaryTableTd2"><%=start %></td>
</tr>
<tr>
<td class="summaryTableTd1">Rate (USD)</td>
<td class="summaryTableTd2">$<%=rate %></td>
</tr>
<tr>
<td class="summaryTableTd1">Compensation Type</td>
<td class="summaryTableTd2"><%=compText %></td>
</tr>
<tr>
<td class="summaryTableTd1">Desired Interpersonal Skills</td>
<td class="summaryTableTd2">
<%=skillA1 %><br>
<%=skillA2 %><br>
<%=skillA3 %>
</td>
</tr>
<tr>
<td class="summaryTableTd1">Desired Measurable Skills</td>
<td class="summaryTableTd2">
<%=skillB1 %><br>
<%=skillB2 %><br>
<%=skillB3 %>
</td>
</tr>
</table>


<button type="submit" name="back" value="post6.jsp">Back</button>
&nbsp;&nbsp;&nbsp;
<button type="button" name="close" onclick="parent.hideIframe('postframe')">Close</button>
&nbsp;&nbsp;&nbsp;
<button type="submit" name="next" value="post8.jsp">Submit</button>


</form>

<footer>
<table class="progress">
<tr>
<td class="progresstd" style="width: 50px; opacity: 75%;"></td>
<td class="progresstd" style="width: 50px; opacity: 75%;"></td>
<td class="progresstd" style="width: 50px; opacity: 75%;"></td>
<td class="progresstd" style="width: 50px; opacity: 75%;"></td>
<td class="progresstd" style="width: 50px; opacity: 75%;"></td>
<td class="progresstd" style="width: 50px; opacity: 75%;"></td>
</tr>
</table>
Step #6 of 6
</footer>


</td>
</tr>
</table>


</body>
</html>
