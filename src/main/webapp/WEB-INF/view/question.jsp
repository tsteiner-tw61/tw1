<%@ 
page 
language="java" 
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"
import ="jakarta.servlet.http.HttpSession,
		 jakarta.servlet.http.HttpServlet,
		 jakarta.servlet.http.HttpServletRequest,
		 jakarta.servlet.http.HttpServletResponse,
		 com.teamwrkr.app.dto.*,
		 com.teamwrkr.app.service.*"

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Teamwrkr - Customization</title>
<link href="/tw1/img/favicon.ico" rel="shortcut icon" type="image/x-icon" />
<link rel="stylesheet" href="/tw1/css/twemvq.css">
<script src="/tw1/js/forms.js"></script>
</head>
<body>

<%
String cat1 = (String) request.getParameter("cat1");
String cat2 = (String) request.getParameter("cat2");
String cat3 = (String) request.getParameter("cat3");
String seq = (String) request.getParameter("seq");

WizardItem wiz = WizardItemService.selectWizardItem(cat1, cat2, cat3, Integer.parseInt(seq));
boolean alpha = false;

String btype = "submit";
String bstyle = "opacity: 100%;";
if (wiz.getQuestion() != null) {
	if (wiz.getQuestion().equals("What is your business focus?")) {
		//alpha = true;
		btype = "button";
		bstyle = "opacity: 30%;";
	}
%>

<div id="indexdiv1" >
<header>
<figure>
 <img style="border-radius: 8px; width: 400px;" src="/tw1/img/twlogoxpgrayscale1.png"></img>
</figure>
</header>
</div>

<div>

<form name="wizard" action="SiteServlet" method="get" >
<input type="hidden" name="query" value="2" />
<input type="hidden" name="cat1" value="<%=cat1 %>" />
<input type="hidden" name="cat2" value="<%=cat2 %>" />
<input type="hidden" name="cat3" value="<%=cat3 %>" />
<input type="hidden" name="seq" value="<%=seq %>" />

<table class="verifyemail-table">
<tr>
<td>

<h3><%=wiz.getQuestion() %></h3>
<p class="instructions"></p>
<hr/>
<br/>
<br/>

<%
if (wiz.getAnswer1() != null && !wiz.getAnswer1().equals("Just starting")) {
%>
<button type="submit" name="ans" value="1"><%=wiz.getAnswer1() %></button>
<%
}
if (wiz.getAnswer2() != null) {
	if (!alpha) {
%>
<button type="<%=btype %>" style="<%=bstyle %>" name="ans" value="2"><%=wiz.getAnswer2() %></button>
<%
	}
}
if (wiz.getAnswer3() != null) {
	if (!alpha) {
%>
<button type="<%=btype %>" style="<%=bstyle %>" name="ans" value="3"><%=wiz.getAnswer3() %></button>
<%
	}
}
if (wiz.getAnswer4() != null) {
	if (!alpha) {
%>
<button type="<%=btype %>" style="<%=bstyle %>" name="ans" value="4"><%=wiz.getAnswer4() %></button>
<%
	}
}
if (wiz.getAnswer5() != null) {
	if (!alpha) {
%>
<button type="<%=btype %>" style="<%=bstyle %>" name="ans" value="5"><%=wiz.getAnswer5() %></button>
<%
	}
}
if (wiz.getAnswer6() != null) {
	if (!alpha) {
%>
<button type="<%=btype %>" style="<%=bstyle %>" name="ans" value="6"><%=wiz.getAnswer6() %></button>
<%
	}
}
if (wiz.getAnswer7() != null) {
	if (!alpha) {
%>
<button type="<%=btype %>" style="<%=bstyle %>" name="ans" value="7"><%=wiz.getAnswer7() %></button>
<%
	}
}
if (wiz.getAnswer8() != null) {
	if (!alpha) {
%>
<button type="<%=btype %>" style="<%=bstyle %>" name="ans" value="8"><%=wiz.getAnswer8() %></button>
<%
	}
}
if (wiz.getAnswer9() != null) {
	if (!alpha) {
%>
<button type="<%=btype %>" style="<%=bstyle %>" name="ans" value="9"><%=wiz.getAnswer9() %></button>
<%
	}
}
if (wiz.getAnswer10() != null) {
	if (!alpha) {
%>
<button type="<%=btype %>" style="<%=bstyle %>" name="ans" value="10"><%=wiz.getAnswer10() %></button>
<%
	}
}
%>
<br/>
<br/>
<br/>
<hr/>
<footer><br/></footer>
</td>
</tr>
</table>
</form>
<%
}
%>

</div>

</body>
</html>
