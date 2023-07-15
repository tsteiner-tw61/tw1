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
<title>Verify Your Email</title>
<link href="/tw1/img/favicon.ico" rel="shortcut icon" type="image/x-icon" />
<link rel="stylesheet" href="/tw1/css/twemv.css">
<script src="/tw1/js/forms.js"></script>
</head>
<body>

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

<form name="verifyemail" action="SiteServlet" method="get" onsubmit="return validateEmailVerificationForm();" >
<input type="hidden" name="query" value="12" />

<h3>Verify Your Email Address</h3>
<p class="instructions">To change your password, please enter your email address below. A verification link
will be sent to the provided address.</p>
<hr/>
<br/>
<br/>
<p>
<b>* Email Address</b><br/>
<input type="text" name="email" size="40" maxlength="96" placeholder="Please enter a valid, active account" required /><br/>
</p>
<br/>
<p>
<input type="checkbox" name="legal" />&nbsp;
<b>* I agree with the Teamwrkr Terms of Service and Privacy Policy</b>
</p>
<p>
<br/>
<a 
class="verifyemail-link"
href="https://teamwrkr.com/terms/" 
target="popup" 
onclick="window.open('https://teamwrkr.com/terms/','popup','width=600,height=600'); return false;">
View Teamwrkr Terms of Service
</a>
<br/>
<a 
class="verifyemail-link"
href="https://teamwrkr.com/privacy/" 
target="popup" 
onclick="window.open('https://teamwrkr.com/privacy/','popup','width=600,height=600'); return false;">
View Teamwrkr Privacy Policy
</a>
</p>
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
