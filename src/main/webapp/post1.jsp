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
<title>Teamwrkr - Post A Job</title>
<link href="/tw1/img/favicon.ico" rel="shortcut icon" type="image/x-icon" />
<link rel="stylesheet" href="/tw1/css/twtip.css">
<script src="/tw1/js/forms.js"></script>
</head>
<body>




<table>
<tr>
<td>

<form name="posthelp" action="SiteServlet" method="get">

<h3>Post A Job</h3>
<h4>Welcome</h4>
<p class="instructions" style="margin-bottom: 36px;">Teamwrkr, the premier commmunity for freelance professionals, welcomes any member to post opportunities
on our application. However, a few simple guidelines apply to what can be posted and how. If you would like more details, we can
walk you through the process, or you can skip this and go directly to the full Post A Job form.</p>


<table class="options">
<tr>
<td class="optionstd">
<figure>
<a href="/tw1/SiteServlet?query=14">
<img height="128" width="128" src="/tw1/img/tutorial1.png" />
</a>
<figcaption>Post Job Tutorial</figcaption>
</figure>
</td>
<td class="optionstd">
<figure>
<a href="/tw1/post.jsp">
<img height="128" width="128" src="/tw1/img/gui_form_icon_1576441.png"  />
</a>
<figcaption>Post Job Form</figcaption>
</figure>
</td>
</tr>

</table>


<footer>
<!-- <input type="checkbox" id="post-remember" name="remember" />Remember my decision<br/> -->
<button type="button" name="close" onclick="parent.hideIframe('postframe')" >Close</button>
</footer>
</form>

</td>
</tr>
</table>


</body>
</html>
