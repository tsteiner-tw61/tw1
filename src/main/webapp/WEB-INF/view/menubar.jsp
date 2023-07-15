<div class="menubar">

<table id="mbar-table">
<tr>
<td class="mbar-left-td">
<img class="mb-tw-icon" height="42" width="42" src="/tw1/img/twiconx48-1.png" />
<a 
onmouseover="show('mb-tooltip-home'); show('mb-tooltip-home-body');"
onmouseout="hide('mb-tooltip-home'); hide('mb-tooltip-home-body');"
href="SiteServlet?query=6">
<img height="54" width="54" class="menuicon" src="/tw1/img/twicons/hm-2x-bg0-fill0-aa77d6.png" />
</a>
<a 
onmouseover="show('mb-tooltip-profile'); show('mb-tooltip-profile-body');"
onmouseout="hide('mb-tooltip-profile'); hide('mb-tooltip-profile-body');"
href="SiteServlet?query=10"><img height="54" width="54" class="menuicon" src="/tw1/img/twicons/pro-2x-bg0-fill0-aa77d6.png" /></a>
<a 
onmouseover="show('mb-tooltip-wrk'); show('mb-tooltip-wrk-body');"
onmouseout="hide('mb-tooltip-wrk'); hide('mb-tooltip-wrk-body');"
href="SiteServlet?query=7"><img height="54" width="54" class="menuicon" src="/tw1/img/twicons/wrk-2x-bg0-fill0-aa77d6.png" /></a>
<a 
onmouseover="show('mb-tooltip-msg'); show('mb-tooltip-msg-body');"
onmouseout="hide('mb-tooltip-msg'); hide('mb-tooltip-msg-body');"
href="SiteServlet?query=8"><img height="54" width="54" class="menuicon" style="margin-right: 0px;" src="/tw1/img/twicons/msg-2x-bg0-fill0-aa77d6.png" />
</a>
<span style="color: rgba(249, 89, 66, 1); margin: 0px 22px 0px 0px; padding: 0px;"></span>
<a 
onmouseover="show('mb-tooltip-not'); show('mb-tooltip-not-body');"
onmouseout="hide('mb-tooltip-not'); hide('mb-tooltip-not-body');"
href="SiteServlet?query=24"><img height="54" width="54" class="menuicon" style="margin-right: 0px;" src="/tw1/img/twicons/not-2x-bg0-fill0-aa77d6.png" /></a>
<span style="color: rgba(249, 89, 66, 1); margin: 0px 22px 0px 0px; padding: 0px;"><%=notifications %></span>
</td>
<td class="mbar-center-td">
<button 
onmouseover="show('mb-tooltip-postjob'); show('mb-tooltip-postjob-body');"
onmouseout="hide('mb-tooltip-postjob'); hide('mb-tooltip-postjob-body');"
class="mb-wrk-button" 
onclick="showIframe('postframe');">POST A JOB</button>
</td>
<td class="mbar-right-td">


<input 
onmouseover="show('mb-tooltip-search'); show('mb-tooltip-search-body');"
onmouseout="hide('mb-tooltip-search'); hide('mb-tooltip-search-body');"
class="mbar-search-input" 
style="color: white;" 
type="text" 
placeholder="Search for a Teamwrkr" />
<span class="mb-pts"><%=pts %></span>
<%
if (!avatarBgPhoto) {
%>
<button 
onmouseover="show('mb-tooltip-avatar'); show('mb-tooltip-avatar-body');"
onmouseout="hide('mb-tooltip-avatar'); hide('mb-tooltip-avatar-body');"
class="mb-avatar" 
style="margin-left: 10px;" 
onclick="toggleDropdownMenu();">
<%=avatarInitials %>
</button>
<%
}
else {
%>

<img 
class="mb-avatar-img"
onmouseover="show('mb-tooltip-avatar'); show('mb-tooltip-avatar-body');"
onmouseout="hide('mb-tooltip-avatar'); hide('mb-tooltip-avatar-body');"
	style="margin-left: 10px;" 
onclick="toggleDropdownMenu();"
src="<%=photoSrc %>" />
<%
}
%>



<div id="mb-account-dropdown">
<a href="SiteServlet?query=18" style="padding: 0px 15px;" onclick="hideDropdownMenu();">Change Password</a><br/><br/>
<a href="SiteServlet?query=22" style="padding: 0px 15px;" onclick="hideDropdownMenu();">Logout</a>
</div>

</td>
</tr>
</table>

</div>
