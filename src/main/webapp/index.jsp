<%@ 
page 
language="java" 
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"
import ="jakarta.servlet.http.HttpSession,
		 jakarta.servlet.http.HttpServlet,
		 jakarta.servlet.http.HttpServletRequest,
		 jakarta.servlet.http.HttpServletResponse,
		 java.util.*"
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Teamwrkr - Welcome</title>
<link href="/tw1/img/favicon.ico" rel="shortcut icon" type="image/x-icon" />
<link rel="stylesheet" href="/tw1/css/tw.css">
<link rel="stylesheet" href="/tw1/css/twindex.css">
<script src="/tw1/js/forms.js"></script>
</head>
<body>
<%
String login = (String) request.getParameter("login");
String reg = (String) request.getParameter("reg");
String reload = (String) request.getParameter("reload");
%>
<div id="index-main-div">

<div id="index-content-div">

<div id="index-logo-div">
<figure>
<img style="width: 400px;" src="/tw1/img/twlogoxp1.png"></img>
</figure>
</div>

<div id="index-login-div">
<h6>Already a part of the Teamwrkr community? Login here.</h6>
<form name="login" action="SiteServlet" method="get" onsubmit="return validateLoginForm()" >
<input type="hidden" name="query" value="4" />
Email:&nbsp;
<input type="text" name="email" size="28" maxlength="96" required/>
<br/>
Password:&nbsp;
<input type="password" name="pwd" size="28" maxlength="16" required/>
<br/>
<a class="index-forgot-pwd" href="SiteServlet?query=11">Forgot Password?</a><br/>
<button class="index-login-button-gray" type="submit" name="submit">Login</button>
</form>
<div id="index-login-msg">
<%
if (login != null && login.equals("fail")) {
%>
<span>Login failed. Please try again.</span>
<%
}
%>
<%
if (reg != null && reg.equals("fail")) {
%>
<span>Account not found. Please try again.</span>
<%
}
%>
</div>
</div>

<div id="index-img-row1-div">
<img class="index-img-row" src="/tw1/img/people-row-8.png"></img>
</div>

<div id="index-reg-div">
<h2>Welcome to Teamwrkr</h2>
<h4>Please allow us to customize your experience by agreeing to answer a few quick questions.</h4>
<form name="home" action="SiteServlet" method="get" style="display: inline;" >
<input type="hidden" name="query" value="1" />
<button type="submit" name="submit" class="invertedbutton">I'M A FREELANCER</button>
&nbsp;&nbsp;
</form>
<form name="visitor" action="SiteServlet" method="get" style="display: inline;">
<input type="hidden" name="query" value="1" />
<button type="submit" name="submit" class="invertedbutton">I NEED FREELANCERS</button>
</form>
</div>

<div id="index-img-row2-div">
<img class="index-img-row" src="/tw1/img/people-row-6.png"></img>
</div>


</div>


<footer>Copyright © 2023</footer>
</div>

</body>
</html>