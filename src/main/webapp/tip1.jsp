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
<title>Teamwrkr - Helpful Hints</title>
<link href="/tw1/img/favicon.ico" rel="shortcut icon" type="image/x-icon" />
<link rel="stylesheet" href="/tw1/css/twtip.css">
<script src="/tw1/js/forms.js"></script>
</head>
<body>

<table>
<tr>
<td>

<form name="tips" action="tip2.jsp" method="get">

<h3>Welcome To Teamwrkr!</h3>
<h4>Home/Landing/Start Page</h4>
<p class="instructions">Every time you login to the application,
you will automatically be brought to this page. From here you can check out items most interesting and useful to freelancers aiming
to build their business quickly and easily.</p>
<br/>

<br/>


<p>
<br/>
<a 
class="verifyemail-link"
href="http://localhost:8080/tw1/tip1a.jsp">
Community Rolodex
</a>
<br/>

<a 
class="verifyemail-link"
href="http://localhost:8080/tw1/tip1b.jsp">
Opportunities Slider
</a>
<br/>
</p>

<button type="submit" name="prev">Previous</button>
<button type="submit" name="next">Next</button>
<button type="submit" name="close">Close</button>
<p>
<input type="checkbox" name="disable" />&nbsp;
<b>Do not show this window again</b>
</p>
</form>

<hr/>
<footer>Tip #1 of 10</footer>


</td>
</tr>
</table>


</body>
</html>
