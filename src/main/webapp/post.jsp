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
	cat2 = "Art/Illustration";
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
</head>
<body>


<table>
<tr>
<td>

<form name="posthelp" action="SiteServlet" method="get">
<input type="hidden" name="query" value="15" />

<h3>Post A Job</h3>
<h4>Detailed Information</h4>
<p class="instructions">Please provide the following information regarding your available role. The more details you provide,
the better Teamwrkr Intelligent Job Matching can present you with the most desirable candidates.</p>


<p id="post-p1">
<b>*Job Title</b><br/>
<input type="text" id="post-title" name="title" size="40" maxlength="96" value="<%=title %>" required>
</p>

<p id="post-p2">
<b>*Description</b><br/>
<textarea id="post-desc"  name="desc" maxlength="128" placeholder="128 char. max." required><%=desc %></textarea>
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
<input type="date" id="post-start" name="start" value="<%=start %>" min="<%=today %>" required>
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
$<input type="number" id="post-rate" name="rate" placeholder="USD" min="10" max="99999" value="<%=rate %>" required>
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

<button type="submit" name="next" value="post7a.jsp">Submit</button>
<button type="button" name="close" onclick="parent.hideIframe('postframe')">Close</button>

</form>


</td>
</tr>
</table>


</body>
</html>
