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
<title>Create Teamwrkr Account</title>
<link href="/tw1/img/favicon.ico" rel="shortcut icon" type="image/x-icon" />
<link rel="stylesheet" href="/tw1/css/twemv.css">
<script src="/tw1/js/forms.js"></script>
</head>
<body>

<%
/*
 * Pass along the category information from the
 * wizard question sequence.
 */
String cat1 = (String) request.getParameter("cat1");
String cat2 = (String) request.getParameter("cat2");
String ans = (String) request.getParameter("ans");
String seq = (String) request.getParameter("seq");
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

<form name="register" action="SiteServlet" method="get" onsubmit="return validateRegistrationForm();" >
<input type="hidden" name="query" value="3" />
<input type="hidden" name="cat1" value="<%=cat1 %>" />
<input type="hidden" name="cat2" value="<%=cat2 %>" />
<input type="hidden" name="ans" value="<%=ans %>" />
<input type="hidden" name="seq" value="<%=seq %>" />

<h3>Create Your Account</h3>
<p class="instructions">Please enter the following fields to create your Teamwrkr account. 
Your email will be your username the next time you visit. If you do not
enter a valid email account on this form, you may still be able to access the application, but you will not receive 
information regarding new freelance opportunites as it is standard Teamwrkr 
practice to notify you via email of such occurrences. Thank you for joining Teamwrkr!</p>
<hr/>
<br/>
<br/>
<p>
<b>* Email Address</b><br/>
<input type="text" name="email" size="40" maxlength="96" placeholder="Please enter a valid, active account" required /><br/>
</p>
<br/>

<p>
<b>* Password</b><br/>
<input type="password" name="pwd" size="40" maxlength="16" required /><br/>
<span class='inputDescription'>8-16 characters. Must include at least one uppercase, one digit and one special character.</span>
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
