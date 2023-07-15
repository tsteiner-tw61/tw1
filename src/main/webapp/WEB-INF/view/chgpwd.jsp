<%@ 
page 
language="java" 
contentType="text/html; charset=ISO-8859-1" 
pageEncoding="ISO-8859-1"
import ="jakarta.servlet.http.HttpSession,
		 jakarta.servlet.http.HttpServlet,
		 jakarta.servlet.http.HttpServletRequest,
		 jakarta.servlet.http.HttpServletResponse,
		 java.util.*,
		 com.teamwrkr.app.dto.*,
		 com.teamwrkr.app.service.*"
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Teamwrkr - Change Password</title>
<link href="/tw1/img/favicon.ico" rel="shortcut icon" type="image/x-icon" />
<link rel="stylesheet" href="/tw1/css/twemv.css">
<script src="/tw1/js/forms.js"></script>
</head>
<body>

<%
int twid = 0;

try {
	String tw = (String) request.getParameter("twid");
	twid = Integer.parseInt(tw);
}
catch (Exception e) {
	twid = (int) session.getAttribute("twid");
}

Profile p1 = new Profile();
p1.setTwid(twid);
Profile profile = (Profile) DataService.selectOne(p1);
String email = profile.getEmail();
%>

<div id="indexdiv1" >
<header>
<figure>
 <img style="border-radius: 8px; width: 400px;" src="/tw1/img/twlogoxpgrayscale1.png"></img>
</figure>
</header>
</div>

<div>

<table class="chg-pwd-table">
<tr>
<td>

<form name="chgpwd" action="SiteServlet" method="get" onsubmit="return validateChangePasswordForm();" >
<input type="hidden" name="query" value="19" />


<h3>Change Your Password</h3>
<p class="instructions">This form will change the password for the account which you are currently logged in under.
If the password change is successful, you will automatically be logged out of the application and be required to log
back in to continue.</p>
<hr/>
<br/>
<br/>
<p>
<b>Email Address</b><br/>
<b><%=email %></b><br/>
</p>
<br/>

<p>
<b>*New Password</b><br/>
<input type="password" name="pwd" size="40" maxlength="16" required /><br/>
<span class='inputDescription'>8-16 characters. Must include at least one uppercase, one digit and one special character.</span>
</p>
<br/>

<button type="submit" name="submit">Submit</button>

</form>

<div id="msg"></div>
<br/>

<hr/>
<footer>* required field</footer>


</td>
</tr>
</table>

</div>

</body>
</html>
