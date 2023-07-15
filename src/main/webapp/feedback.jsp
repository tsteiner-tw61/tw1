<%@ 
page 
language="java" 
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"
import ="jakarta.servlet.http.HttpSession,
		 jakarta.servlet.http.HttpServlet,
		 jakarta.servlet.http.HttpServletRequest,
		 jakarta.servlet.http.HttpServletResponse,
		 java.text.SimpleDateFormat,
		 java.text.DecimalFormat,
		 java.util.*,
		 com.teamwrkr.app.dto.*,
		 com.teamwrkr.app.service.DataService"

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Survey</title>
<link href="/tw1/img/favicon.ico" rel="shortcut icon" type="image/x-icon" />
<link rel="stylesheet" href="/tw1/css/twfb.css">
<script src="/tw1/js/feedback.js"></script>
</head>
<body>

<%
String sid = (String) request.getParameter("sid");

Survey surveyRequested = new Survey();
surveyRequested.setSurveyId(Integer.parseInt(sid));
Survey survey = (Survey) DataService.selectOne(surveyRequested);

int tw = survey.getTwid();

SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy");
Date date1 = survey.getStartDate();
Date date2 = survey.getEndDate();

String startDate = formatter.format(date1);
String endDate = formatter.format(date2);

Profile profileRequested = new Profile();
profileRequested.setTwid(tw);
Profile profile = (Profile) DataService.selectOne(profileRequested);

boolean surveyExpired = false;
if (survey.getCompleteDate() != null) {
	surveyExpired = true;
}

if (!surveyExpired) {
%>

<div style="padding-left: 40px;">

<div style="width: 600px; text-align: center; margin-top: 35px; margin-bottom: 35px;">
<figure style="padding: 0px; margin: 0px;">
 <img style="border-radius: 8px; width: 300px;" src="/tw1/img/twlogoxpmono1.png"></img>
</figure>
</div>

<p>Thank you for agreeing to review:</p>

<table>
<tr>
<td class="key">Name:
</td>
<td class="val"><%=profile.getFirstName() %> <%=profile.getLastName() %>
</td>
</tr>
<tr>
<td class="key">Job Title:
</td>
<td class="val"><%=survey.getTitle() %>
</td>
</tr>
<tr>
<td class="key">Job Description/Duties:
</td>
<td class="val"><%=survey.getDesc() %>
</td>
</tr>
<tr>
<td class="key">Start Date:
</td>
<td class="val"><%=startDate %>
</td>
</tr>
<tr>
<td class="key">End Date:
</td>
<td class="val"><%=endDate %>
</td>
</tr>
</table>

<form id="survey" name="surveyform" action="SurveyServlet" method="post">
<input type="hidden" name="sid" value="<%=sid %>" />
<input type="hidden" name="tw" value="<%=tw %>" />
<p>Please rate the above mentioned individual on the following scale:<br/>(1 = worst, 5 = best)</p>
<br/>
<table style="width: 700px;">
<tr>
<td style="width: 400px; background: rgba(255, 255, 255, 0.2);">
<%=survey.getHardskill1() %>
</td>
<td style="width: 300px;">
&nbsp;&nbsp;
<input type="button" class="star1" name="skillh1" value="1" onclick="setSkillScore('skillh1score', 1); highlightScore('skillh1', 0);" />
<input type="button" class="star2" name="skillh1" value="2" onclick="setSkillScore('skillh1score', 2); highlightScore('skillh1', 1);" />
<input type="button" class="star3" name="skillh1" value="3" onclick="setSkillScore('skillh1score', 3); highlightScore('skillh1', 2);" />
<input type="button" class="star4" name="skillh1" value="4" onclick="setSkillScore('skillh1score', 4); highlightScore('skillh1', 3);" />
<input type="button" class="star5" name="skillh1" value="5" onclick="setSkillScore('skillh1score', 5); highlightScore('skillh1', 4);" />
<input type="hidden" name="skillh1name" value="<%=survey.getHardskill1() %>" />
<input type="hidden" name="skillh1score" value="0" />
</td>
</tr>
<tr>
<td style="background: rgba(255, 255, 255, 0.1);">
<%=survey.getHardskill2() %>
</td>
<td>
&nbsp;&nbsp;
<input type="button" class="star1" name="skillh2" value="1" onclick="setSkillScore('skillh2score', 1); highlightScore('skillh2', 0);" />
<input type="button" class="star2" name="skillh2" value="2" onclick="setSkillScore('skillh2score', 2); highlightScore('skillh2', 1);" />
<input type="button" class="star3" name="skillh2" value="3" onclick="setSkillScore('skillh2score', 3); highlightScore('skillh2', 2);" />
<input type="button" class="star4" name="skillh2" value="4" onclick="setSkillScore('skillh2score', 4); highlightScore('skillh2', 3);" />
<input type="button" class="star5" name="skillh2" value="5" onclick="setSkillScore('skillh2score', 5); highlightScore('skillh2', 4);" />
<input type="hidden" name="skillh2name" value="<%=survey.getHardskill2() %>" />
<input type="hidden" name="skillh2score" value="0" />
</td>
</tr>
<tr>
<td style="background: rgba(255, 255, 255, 0.2);">
<%=survey.getHardskill3() %>
</td>
<td>
&nbsp;&nbsp;
<input type="button" class="star1" name="skillh3" value="1" onclick="setSkillScore('skillh3score', 1); highlightScore('skillh3', 0);" />
<input type="button" class="star2" name="skillh3" value="2" onclick="setSkillScore('skillh3score', 2); highlightScore('skillh3', 1);" />
<input type="button" class="star3" name="skillh3" value="3" onclick="setSkillScore('skillh3score', 3); highlightScore('skillh3', 2);" />
<input type="button" class="star4" name="skillh3" value="4" onclick="setSkillScore('skillh3score', 4); highlightScore('skillh3', 3);" />
<input type="button" class="star5" name="skillh3" value="5" onclick="setSkillScore('skillh3score', 5); highlightScore('skillh3', 4);" />
<input type="hidden" name="skillh3name" value="<%=survey.getHardskill3() %>" />
<input type="hidden" name="skillh3score" value="0" />
</td>
</tr>
<tr>
<td style="background: rgba(255, 255, 255, 0.1);">
<%=survey.getSoftskill1() %>
</td>
<td>
&nbsp;&nbsp;
<input type="button" class="star1" name="skills1" value="1" onclick="setSkillScore('skills1score', 1); highlightScore('skills1', 0);" />
<input type="button" class="star2" name="skills1" value="2" onclick="setSkillScore('skills1score', 2); highlightScore('skills1', 1);" />
<input type="button" class="star3" name="skills1" value="3" onclick="setSkillScore('skills1score', 3); highlightScore('skills1', 2);" />
<input type="button" class="star4" name="skills1" value="4" onclick="setSkillScore('skills1score', 4); highlightScore('skills1', 3);" />
<input type="button" class="star5" name="skills1" value="5" onclick="setSkillScore('skills1score', 5); highlightScore('skills1', 4);" />
<input type="hidden" name="skills1name" value="<%=survey.getSoftskill1() %>" />
<input type="hidden" name="skills1score" value="0" />
</td>
</tr>
<tr>
<td style="background: rgba(255, 255, 255, 0.2);">
<%=survey.getSoftskill2() %>
</td>
<td>
&nbsp;&nbsp;
<input type="button" class="star1" name="skills2" value="1" onclick="setSkillScore('skills2score', 1); highlightScore('skills2', 0);" />
<input type="button" class="star2" name="skills2" value="2" onclick="setSkillScore('skills2score', 2); highlightScore('skills2', 1);" />
<input type="button" class="star3" name="skills2" value="3" onclick="setSkillScore('skills2score', 3); highlightScore('skills2', 2);" />
<input type="button" class="star4" name="skills2" value="4" onclick="setSkillScore('skills2score', 4); highlightScore('skills2', 3);" />
<input type="button" class="star5" name="skills2" value="5" onclick="setSkillScore('skills2score', 5); highlightScore('skills2', 4);" />
<input type="hidden" name="skills2name" value="<%=survey.getSoftskill2() %>" />
<input type="hidden" name="skills2score" value="0" />
</td>
</tr>
<tr>
<td style="background: rgba(255, 255, 255, 0.1);">
<%=survey.getSoftskill3() %>
</td>
<td>
&nbsp;&nbsp;
<input type="button" class="star1" name="skills3" value="1" onclick="setSkillScore('skills3score', 1); highlightScore('skills3', 0);" />
<input type="button" class="star2" name="skills3" value="2" onclick="setSkillScore('skills3score', 2); highlightScore('skills3', 1);" />
<input type="button" class="star3" name="skills3" value="3" onclick="setSkillScore('skills3score', 3); highlightScore('skills3', 2);" />
<input type="button" class="star4" name="skills3" value="4" onclick="setSkillScore('skills3score', 4); highlightScore('skills3', 3);" />
<input type="button" class="star5" name="skills3" value="5" onclick="setSkillScore('skills3score', 5); highlightScore('skills3', 4);" />
<input type="hidden" name="skills3name" value="<%=survey.getSoftskill3() %>" />
<input type="hidden" name="skills3score" value="0" />
</td>
</tr>
<tr>
<td style="background: rgba(255, 255, 255, 0.2);">
Overall Rating:
</td>
<td>
&nbsp;&nbsp;
<input type="button" class="star1a" name="overall" value="1" onclick="setSkillScore('overallscore', 1); highlightOverallScore('overall', 0);" />
<input type="button" class="star2a" name="overall" value="2" onclick="setSkillScore('overallscore', 2); highlightOverallScore('overall', 1);" />
<input type="button" class="star3a" name="overall" value="3" onclick="setSkillScore('overallscore', 3); highlightOverallScore('overall', 2);" />
<input type="button" class="star4a" name="overall" value="4" onclick="setSkillScore('overallscore', 4); highlightOverallScore('overall', 3);" />
<input type="button" class="star5a" name="overall" value="5" onclick="setSkillScore('overallscore', 5); highlightOverallScore('overall', 4);" />
<input type="hidden" name="overallscore" value="0" />
</td>
</tr>
</table>
<br/><br/>
<p>Please provide comments (128 char. max.) that you feel relevant to add about<br/><%=profile.getFirstName() %> <%=profile.getLastName() %>.</p>
<textarea class="feedtextarea" id="surveycomments"></textarea>
<br/><br/>

<div style="width: 600px; text-align: center;">
<button type="submit" class="surveysubmit" onclick="validateSurveyForm('surveyForm');">Submit</button>
</form>
</div>

</div>

<div id="error" style="margin-top: 20px; margin-bottom: 20px; color: red;"></div>
<%
}
else {
%>
<h1>This survey is expired or has already been submitted. Thank you.</h1>
<%
}
%>
</body>
</html>
