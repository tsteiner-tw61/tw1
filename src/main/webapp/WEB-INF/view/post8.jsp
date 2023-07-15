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
<body>

<table class="verifyemail-table">
<tr>
<td>

<h3>Post A Job</h3>
<h4>Process Complete</h4>
<p class="instructions">Your posting has been entered into the Teamwrkr system. It will be available and displayed in the
application after a brief validation process. Thank you for posting with Teamwrkr!</p>

<button type="button" name="close" onclick="parent.hideIframe('postframe')">Close</button>

</body>
</html>
