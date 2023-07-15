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
		 com.teamwrkr.app.dto.WizardItem,
		 com.teamwrkr.app.service.WizardItemService"
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Confirm Your Email</title>
<link href="/tw1/img/favicon.ico" rel="shortcut icon" type="image/x-icon" />
<link rel="stylesheet" href="/tw1/css/twemv.css">
<script src="/tw1/js/forms.js"></script>
</head>
<body>

<%
String twid = request.getParameter("twid");
String email = request.getParameter("email");
String match = request.getParameter("match");
%>

<div id="indexdiv1" >
<header>
<figure>
 <img style="border-radius: 8px; width: 400px;" src="/tw1/img/twlogoxpgrayscale1.png"></img>
</figure>
</header>
</div>

<div>

<table class="verifyemail-table">
<tr>
<td>

<form name="confirmemail" action="SiteServlet" method="get" onsubmit="return validateEmailConfirmationForm();" >
<input type="hidden" name="query" value="17" />
<input type="hidden" name="twid" value="<%=twid %>" />
<input type="hidden" name="email" value="<%=email %>" />

<h3>Confirm Email Verification</h3>
<p class="instructions">If you have received an account verification email from Teamwrkr, please enter the code provided in the 
appropriate field below. If you have not received it, please check your spam folder if you have not already. 
Otherwise, indicate that you definitely have not received it, and Teamwrkr will send you another one.</p>
<hr/>
<br/>
<br/>
<p>
<b>* Enter your Teamwrkr verification code</b><br/>
<input type="text" name="code" size="40" maxlength="6" placeholder="Please enter the 6-digit code" /><br/>
</p>
<p>
<input style="margin: 15px auto;" type="checkbox" name="received" value="no"/>&nbsp;
<b>I did not receive the verification email</b>
</p>
<p>
<button type="submit" name="submit">Submit</button>

</form>
<div id="msg"></div>
<br/>
<%
if (match != null && match.equals("false")) {
%>
<div id="msg2">
The verification code entered did not match the one in our records. Please try again.
</div>
<br/>
<%
}
%>


<hr/>
<footer>* required field</footer>


</td>
</tr>
</table>

</div>

</body>
</html>
