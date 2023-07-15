package com.teamwrkr.app.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.teamwrkr.app.dto.*;
import com.teamwrkr.app.manager.IntelligentJobMatchManager;
import com.teamwrkr.app.manager.OpportunityManager;
import com.teamwrkr.app.manager.PointsManager;
import com.teamwrkr.app.manager.RolodexManager;
import com.teamwrkr.app.service.*;
import com.teamwrkr.app.util.*;
import com.teamwrkr.app.web.*;



/**
 * Navigation controller servlet for Teamwrkr.com.
 * 
 * @author Ted E. Steiner
 * @date 2.1.2023
 *
 */
public class SiteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	
    public SiteServlet() {
        super();
    }


    
    
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		System.out.println("servlet init");
		
	}

	
	
	public void destroy() {
		
	}

	

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * Test console output for doGet() call.
		 * Comment out in production.
		 */
		System.out.print("SiteServlet.doGet()--case ");
		
		/*
		 * Get query parameter (navigation option).
		 */
		String query = request.getParameter("query");
		int q = Integer.parseInt(query);
		System.out.println(q);
		
		/*
		 * Basic session tracking.
		 * Attributes for profile wizard.
		 */
		HttpSession session = request.getSession();
	
		String target = "";
		
		switch(q) {
		
		
			/*
			 *  ----- START REGISTRATION WIZARD (START NOW)-----
			 */
			case 1:
				
				/*
				 * Session tracking for registration wizard.
				 * Currently disabled.
				 */
				if (session.getAttribute("sessionId") == null) {
					Random c1rnd = new Random();
			        int c1number = c1rnd.nextInt(999999999);
			        String c1rnd9 = String.format("%09d", c1number);
					session.setAttribute("sessionId", c1rnd9);
					System.out.println("session.setAttribute(sessionId) = " + c1rnd9);
				}

				target = "/WEB-INF/view/question.jsp?cat1=null&cat2=null&cat3=null&seq=1&ans=0";
				
				break;
			
			
			/*
			 * ----- WIZARD QUESTIONS -----
			 */	
			case 2:
				
				String c2cat1 = (String) request.getParameter("cat1");
				String c2cat2 = (String) request.getParameter("cat2");
				String c2cat3 = (String) request.getParameter("cat3");
				String c2seq = (String) request.getParameter("seq");
				String c2ans = (String) request.getParameter("ans");
				
				WizardKey wizardKey = WizardKeyService.selectWizardKey(c2cat1, c2cat2, c2cat3, 
																		Integer.parseInt(c2seq), 
																		Integer.parseInt(c2ans));
				
				if (wizardKey.getNewsequence() > 0) {
					String c2qs = "?";
					c2qs += "cat1=" + wizardKey.getNewcategory1() + "&";
					c2qs += "cat2=" + wizardKey.getNewcategory2() + "&";
					c2qs += "cat3=" + wizardKey.getNewcategory3() + "&";
					c2qs += "seq=" + wizardKey.getNewsequence();
					target = "/WEB-INF/view/question.jsp" + c2qs;
				}
				else {
					String c2qs = "?";
					c2qs += "cat1=" + wizardKey.getNewcategory1() + "&";
					c2qs += "cat2=" + wizardKey.getNewcategory2() + "&";
					target = "/WEB-INF/view/register.jsp" + c2qs;
				}
				
				break;
			
			
			/*
			 * ----- PROCESS REGISTRATION FORM -----	
			 */
			case 3:
				
				String c3email = request.getParameter("email");
				String c3pwd = request.getParameter("pwd");
				String c3encPwd = LoginService.encrypt(c3pwd);
				String c3cat1 = request.getParameter("cat1");
				String c3cat2 = request.getParameter("cat2");
	
				Profile c3profile = new Profile();
				c3profile.setEmail(c3email);
				c3profile.setEncPwd(c3encPwd);
				c3profile.setCategory1(c3cat1);
				c3profile.setCategory2(c3cat2);
			
				if (ProfileService.profileExists(c3email)) {
					target = "/index.jsp?reg=fail";
				}
				else {
	
					/*
					 * Create all the objects associated with
					 * a new registered user.
					 * 
					 * Profile - tracks all DDR information
					 * Points - tracks all system points
					 * Scores - tracks all survey scores
					 * Skills - tracks best 10 measurable skills and scores
					 * Status - tracks user settings
					 * Calendar (CalInfo) - indicates which dates Avaliablity refers to
					 * Availability - tracks user's availability to work
					 * 
					 */
					
					DataService.create(c3profile);
					int c3twid = ProfileService.getTwid(c3email);
					
					Profile c3profile1 = new Profile();
					c3profile1.setTwid(c3twid);
					Profile c3profile2 = (Profile) DataService.selectOne(c3profile1);
					
					StatusService.createStatus(c3profile2);
					
					Points c3points = new Points();
					c3points.setTwid(c3twid);
					DataService.create(c3points, false);
					
					Scores c3scores = new Scores();
					c3scores.setTwid(c3twid);
					DataService.create(c3scores, false);
					SkillService.populateScoreWithHardskills(c3profile2);
					
					CalInfo c3calInfo = new CalInfo();
					c3calInfo.setTwid(c3twid);
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.DAY_OF_WEEK, -(cal.get(Calendar.DAY_OF_WEEK)-1)); 
					c3calInfo.setStartYear(cal.get(Calendar.YEAR));
					c3calInfo.setStartMonth(cal.get(Calendar.MONTH)+1);
					c3calInfo.setStartDate(cal.get(Calendar.DATE));
					DataService.create(c3calInfo, false);
					
	            	Availability c3av = new Availability(true);
	            	c3av.setTwid(c3twid);
	            	DataService.create(c3av, false);
	            	
	    	        PointsManager.addPoints(c3twid, 1);
	    	        
	                target = "/WEB-INF/view/pi.jsp";
	
				}
		
				break;
			
			
			/*
			 * ----- LOGIN -----
			 */		
			case 4:
				
				String c4email = request.getParameter("email");
				String c4pwd = request.getParameter("pwd");
				boolean c4loginGood = LoginService.loginSuccessful(c4email, c4pwd);
				
				if (c4loginGood) {
	
					if (session.getAttribute("sessionId") == null) {
						Random c4rnd = new Random();
				        int c4number = c4rnd.nextInt(999999999);
				        String c4rnd9 = String.format("%09d", c4number);
						session.setAttribute("sessionId", c4rnd9);
						System.out.println("session.setAttribute(sessionId) = " + c4rnd9);
					}
					
					int c4twid = ProfileService.getTwid(c4email);
					session.setAttribute("twid", c4twid);
					System.out.println("session.setAttribute(twid) = " + c4twid);
					
					
					
					/*
					 * Load the rolodex, if not already loaded.
					 */
					if (session.getAttribute("rolodexLoaded") == null) {
						
						/*
						 * Log transaction in Tx DB table.
						 */
						SiteConstants sc4 = new SiteConstants();
						Tx c4tx = new Tx();
						c4tx.setTwid(c4twid);
						c4tx.setType(sc4.TX_TYPE_LOGIN);
						Date today = Calendar.getInstance().getTime();
						c4tx.setCreateDate(today);
						DataService.create(c4tx);
						
						int c4totalLoginPts = PointsManager.calculateLoginPoints(c4twid);
						
						Points c4reference = new Points();
						c4reference.setTwid(c4twid);
						Points c4update = (Points) DataService.selectOne(c4reference);
						c4update.setLogins(c4totalLoginPts);
						Points c4update2 = new Points(c4update);
						DataService.update(c4update2, c4reference);
						
						
						
						List<Profile> c4rolodexProfiles = RolodexManager.getAllRolodexProfiles();
						Profile c4RolodexProfile = RolodexManager.getInitialRolodexProfile(c4rolodexProfiles);
						int c4rolodexIndex = RolodexManager.getInitialRolodexIndex(c4rolodexProfiles);
						Profile c4RolodexProfileNext = 
								RolodexManager.getRolodexProfile(c4rolodexProfiles, c4rolodexIndex + 1);
						Profile c4RolodexProfilePrevious = 
								RolodexManager.getRolodexProfile(c4rolodexProfiles, c4rolodexIndex - 1);
						
						session.setAttribute("rolodex", c4rolodexProfiles);
						if (c4rolodexProfiles != null) {
							System.out.println("rolodexProfiles.size=" + c4rolodexProfiles.size());
							System.out.println("----- session.setAttribute(rolodex) -----");
							for (Profile c4rp : c4rolodexProfiles) {
								//System.out.println(c4rp.getTwid() + ": " + c4rp.getEmail());
							}
							System.out.println("-----------------------------------------");
						}
						else {
							System.out.println("session.setAttribute(rolodex) = null");
						}
						
						session.setAttribute("rolodexProfile", c4RolodexProfile);		
						if (c4RolodexProfile != null) {
							System.out.println("----- session.setAttribute(rolodexProfile) -----");
							System.out.println(c4RolodexProfile.getTwid() + ": " + c4RolodexProfile.getEmail());
							System.out.println("------------------------------------------------");
						}
						else {
							System.out.println("session.setAttribute(rolodexProfile) = null");
						}
						
						session.setAttribute("rolodexIndex", Integer.toString(c4rolodexIndex));
						System.out.println("session.setAttribute(rolodexIndex) = " + c4rolodexIndex);
						
						
						session.setAttribute("rolodexNextProfile", c4RolodexProfileNext);
						if (c4RolodexProfileNext != null) {
							System.out.println("----- session.setAttribute(rolodexNextProfile) -----");
							System.out.println(c4RolodexProfileNext.getTwid() + ": " + c4RolodexProfileNext.getEmail());
							System.out.println("----------------------------------------------------");
						}
						else {
							System.out.println("session.setAttribute(rolodexNextProfile) = null");
						}
						
						session.setAttribute("rolodexPrevProfile", c4RolodexProfilePrevious);
						if (c4RolodexProfilePrevious != null) {
							System.out.println("----- session.setAttribute(rolodexPrevProfile) -----");
							System.out.println(c4RolodexProfilePrevious.getTwid() + ": " + c4RolodexProfilePrevious.getEmail());
							System.out.println("----------------------------------------------------");
						}
						else {
							System.out.println("session.setAttribute(rolodexPrevProfile) = null");
						}
						
						session.setAttribute("rolodexLoaded", true);
						System.out.println("session.setAttribute(rolodexLoaded) = true");
						
						
					}
					else {
						boolean loaded = (boolean) session.getAttribute("rolodexLoaded");
						System.out.println("loaded=" + loaded);
					}
	
					
					/*
					 * Load the featured opportunities slider.
					 */
					if (session.getAttribute("oppSliderLoaded") == null) {
						
						List<Mission> c4allMissions = OpportunityManager.getAllMissions();
						session.setAttribute("missions", c4allMissions);
						if (c4allMissions != null) {
							System.out.println("missions.size=" + c4allMissions.size());
							System.out.println("----- session.setAttribute(missions) -----");
							for (Mission c4ms : c4allMissions) {
								//System.out.println(c4ms.getTwid1() + ": " + c4ms.getTitle());
							}
							System.out.println("-----------------------------------------");
						}
						else {
							System.out.println("session.setAttribute(missions) = null");
						}
						List<Mission> c4sliderOpps = OpportunityManager.getOpportunities(c4allMissions);
						session.setAttribute("sliderOpps", c4sliderOpps);
						if (c4sliderOpps != null) {
							System.out.println("sliderOpps.size=" + c4sliderOpps.size());
							System.out.println("----- session.setAttribute(sliderOpps) -----");
							for (Mission c4ms : c4sliderOpps) {
								//System.out.println(c4ms.getTwid1() + ": " + c4ms.getTitle());
							}
							System.out.println("-----------------------------------------");
						}
						else {
							System.out.println("session.setAttribute(sliderOpps) = null");
						}
						int c4sliderBlock = 1;
						session.setAttribute("sliderBlock", c4sliderBlock);
						System.out.println("session.setAttribute(sliderBlock) = " + c4sliderBlock);
						
						session.setAttribute("oppSliderLoaded", true);
						System.out.println("session.setAttribute(oppSliderLoaded) = true");
						
					}
					
					
	
					target = "/WEB-INF/view/landing.jsp";
					
				}
				else {
					System.out.println("----- LOGIN FAILED -----");				
					target = "/index.jsp?login=fail";
				}
				
				break;
			
		
			/*
			 * ----- PROCESS PERSONAL INFORMATION FORM -----
			 * ----- IF SUCCESSFUL, GO TO LANDING PAGE -----
			 */
			case 5:
				
				int c5twid = 0;
				
				String c5tw = request.getParameter("twid");
				String c5email = request.getParameter("email");
				String c5first = request.getParameter("fn");
				String c5last = request.getParameter("ln");
				String c5city = request.getParameter("city");
				String c5state = request.getParameter("sp");
				String c5country = request.getParameter("country");
				
				Profile c5Profile = new Profile();
				c5Profile.setTwid(Integer.parseInt(c5tw));
				c5Profile.setEmail(c5email);
				c5Profile.setFirstName(c5first);
				c5Profile.setLastName(c5last);
				c5Profile.setCity(c5city);
				c5Profile.setState(c5state);
				c5Profile.setCountry(c5country);
				c5Profile.setCreateDate(Calendar.getInstance().getTime());
				
				boolean initializedUser = false;
				Profile c5Profile2 = new Profile();
				c5Profile2.setTwid(Integer.parseInt(c5tw));
				Profile checkedProfile = (Profile) DataService.selectOne(c5Profile2);
				if (checkedProfile.getCreateDate() != null) {
					initializedUser = true;
					StatusService.updateStatusKey(checkedProfile.getTwid(), "wv", "1");
				}
				
				if (!initializedUser) {
					
					Profile c5Profile3 = new Profile(c5Profile);
					DataService.update(c5Profile3, c5Profile2);
					
					c5twid = ProfileService.getTwid(c5email);
					session.setAttribute("twid", c5twid);
					System.out.println("session.setAttribute(twid) = " + c5twid);
					
					if (session.getAttribute("rolodexLoaded") == null) {
						
						List<Profile> c5rolodexProfiles = RolodexManager.getAllRolodexProfiles();
						session.setAttribute("rolodex", c5rolodexProfiles);
						Profile c5RolodexProfile = RolodexManager.getInitialRolodexProfile(c5rolodexProfiles);
						session.setAttribute("rolodexProfile", c5RolodexProfile);
						int c5rolodexIndex = RolodexManager.getInitialRolodexIndex(c5rolodexProfiles);
						session.setAttribute("rolodexIndex", Integer.toString(c5rolodexIndex));
						int c5rolodexNextIndex = c5rolodexIndex + 1;
						Profile c5RolodexProfileNext = RolodexManager.getRolodexProfile(c5rolodexProfiles, 
								c5rolodexNextIndex);
						session.setAttribute("rolodexNextProfile", c5RolodexProfileNext);
						int c5rolodexPreviousIndex = c5rolodexIndex - 1;
						Profile c5RolodexProfilePrevious = RolodexManager.getRolodexProfile(c5rolodexProfiles, 
								c5rolodexPreviousIndex);
						session.setAttribute("rolodexPrevProfile", c5RolodexProfilePrevious);
						
						if (c5rolodexProfiles != null) {
							System.out.println("rolodexProfiles.size=" + c5rolodexProfiles.size());
							System.out.println("----- session.setAttribute(rolodex) -----");
							for (Profile c5rp : c5rolodexProfiles) {
								//System.out.println(c5rp.getTwid() + ": " + c5rp.getEmail());
							}
							System.out.println("-----------------------------------------");
						}
						else {
							System.out.println("session.setAttribute(rolodex) = null");
						}
						
						session.setAttribute("rolodexProfile", c5RolodexProfile);		
						if (c5RolodexProfile != null) {
							System.out.println("----- session.setAttribute(rolodexProfile) -----");
							System.out.println(c5RolodexProfile.getTwid() + ": " + c5RolodexProfile.getEmail());
							System.out.println("------------------------------------------------");
						}
						else {
							System.out.println("session.setAttribute(rolodexProfile) = null");
						}
						
						session.setAttribute("rolodexIndex", Integer.toString(c5rolodexIndex));
						System.out.println("session.setAttribute(rolodexProfile) = " + c5rolodexIndex);
						
						session.setAttribute("rolodexNextProfile", c5RolodexProfileNext);
						if (c5RolodexProfileNext != null) {
							System.out.println("----- session.setAttribute(rolodexNextProfile) -----");
							System.out.println(c5RolodexProfileNext.getTwid() + ": " + c5RolodexProfileNext.getEmail());
							System.out.println("----------------------------------------------------");
						}
						else {
							System.out.println("session.setAttribute(rolodexNextProfile) = null");
						}
						
						session.setAttribute("rolodexPrevProfile", c5RolodexProfilePrevious);
						if (c5RolodexProfilePrevious != null) {
							System.out.println("----- session.setAttribute(rolodexPrevProfile) -----");
							System.out.println(c5RolodexProfilePrevious.getTwid() + ": " + c5RolodexProfilePrevious.getEmail());
							System.out.println("----------------------------------------------------");
						}
						else {
							System.out.println("session.setAttribute(rolodexPrevProfile) = null");
						}
						
						session.setAttribute("rolodexLoaded", true);
						System.out.println("session.setAttribute(rolodexLoaded) = true");
						
					}
					
					/*
					 * Load the featured opportunities slider.
					 */
					if (session.getAttribute("oppSliderLoaded") == null) {
						
						List<Mission> c5allMissions = OpportunityManager.getAllMissions();
						session.setAttribute("missions", c5allMissions);
						if (c5allMissions != null) {
							System.out.println("missions.size=" + c5allMissions.size());
							System.out.println("----- session.setAttribute(missions) -----");
							for (Mission c5ms : c5allMissions) {
								//System.out.println(c5ms.getTwid1() + ": " + c5ms.getTitle());
							}
							System.out.println("-----------------------------------------");
						}
						else {
							System.out.println("session.setAttribute(missions) = null");
						}
						List<Mission> c5sliderOpps = OpportunityManager.getOpportunities(c5allMissions);
						session.setAttribute("sliderOpps", c5sliderOpps);
						if (c5sliderOpps != null) {
							System.out.println("sliderOpps.size=" + c5sliderOpps.size());
							System.out.println("----- session.setAttribute(sliderOpps) -----");
							for (Mission c5ms : c5sliderOpps) {
								System.out.println(c5ms.getTwid1() + ": " + c5ms.getTitle());
							}
							System.out.println("-----------------------------------------");
						}
						else {
							System.out.println("session.setAttribute(sliderOpps) = null");
						}
						int c5sliderBlock = 1;
						session.setAttribute("sliderBlock", c5sliderBlock);
						System.out.println("session.setAttribute(sliderBlock) = " + c5sliderBlock);
						
						session.setAttribute("oppSliderLoaded", true);
						System.out.println("session.setAttribute(oppSliderLoaded) = true");
						
					}
					
					
					
					target = "/WEB-INF/view/landing.jsp?twid=" + c5twid;
					
				}
				else {
					target = "/WEB-INF/view/landing.jsp";
				}

				
				break;
		
				
			/*
			 * ----- LANDING PAGE -----
			 */
			case 6:
				
				int c6twid = (int) session.getAttribute("twid");
				StatusService.updateStatusKey(c6twid, "wv", "1");
				target = "/WEB-INF/view/landing.jsp";
				
				break;
		
				
			/*
			 * ----- CRM -----
			 */
			case 7:
				
				int c7twid = (int) session.getAttribute("twid");
				target = "/WEB-INF/view/crm.jsp?twid=" + c7twid;
				
				break;
			
				
			/*
			 * -----CHAT -----
			 */
			case 8:
				
				int c8twid = (int) session.getAttribute("twid");
				target = "/WEB-INF/view/chat.jsp?twid=" + c8twid;
				
				break;
			
				
			/*
			 * ----- VIEW PROFILE ----- 
			 */
			case 9:
				
				String c9twid = request.getParameter("twid");
				target = "/WEB-INF/view/profile-ddr.jsp?twid=" + c9twid;
				
				break;
			
				
			/*
			 * ----- DDR -----
			 */
			case 10:
				
				int c10twid = (int) session.getAttribute("twid");
				target = "/WEB-INF/view/ddr.jsp?twid=" + c10twid;
				
				break;
			
				
			/*
			 * ----- VISITOR (JUST LOOKING) -----
			 */
			case 11:
				
				//System.out.println("case 11 - ri=" + (String) session.getAttribute("rolodexIndex"));
				//int c11twid = (int) session.getAttribute("twid");
				target = "/WEB-INF/view/verifyemail.jsp";
				
				break;
		
				
			/*
			 * ----- PROCESS EMAIL CONFIRMATION FORM -----
			 */
			case 12:
				
				int c12twid = 0;
				
				String c12email = request.getParameter("email");
				
				if (!ProfileService.profileExists(c12email)) {
					target = "/index.jsp?reg=fail";
				}
				else {
					
					/*
					 * Send verification email.
					 */
					String c12emSubject = "Teamwrkr Verification Code";
					EmailService c12ems = new EmailService();
					String c12vcode = c12ems.sendEmailDevelopment(c12email, c12emSubject);
					
					/*
					 * If verification email was successful, then vcode
					 * will be a 6-digit number. If not, it will be an 
					 * empty string.
					 */
					if (c12vcode.length() == 6) {
						
						/*
						 * Create all the objects associated with
						 * a new registered user.
						 * 
						 * Profile - tracks all DDR information
						 * Points - tracks all system points
						 * Scores - tracks all survey scores
						 * Status - tracks user settings
						 * Calendar (CalInfo) - indicates which dates Avaliablity refers to
						 * Availability - tracks user's availability to work
						 * 
						 */
						c12twid = ProfileService.getTwid(c12email);
						StatusService.updateStatusKey(c12twid, "vc", c12vcode);
	
						
					}
					
					target = "/WEB-INF/view/confirmemail.jsp?twid="  + c12twid + "&email=" + c12email;
					
				}
				
				break;
				
				
			/*
			 * ----- POST JOB -----
			 */
			case 13:
				
				target = "/WEB-INF/view/post1.jsp";
				
				break;	
			
				
			/*
			 * ----- START JOB POST WIZARD -----
			 */
			case 14:
				
				int c14twid = (int) session.getAttribute("twid");

				Profile c14Profile2 = new Profile();
				c14Profile2.setTwid(c14twid);
				Profile c14Profile = (Profile) DataService.selectOne(c14Profile2);
				
				String c14title = request.getParameter("title");
				String c14desc = request.getParameter("desc");
				String c14cat1 = request.getParameter("cat1");
				String c14cat2 = request.getParameter("cat2");
				String c14start = request.getParameter("start");
				String c14rate = request.getParameter("rate");
				String c14comp = request.getParameter("comp");
				String c14skillA1 = request.getParameter("softskill1");
				String c14skillA2 = request.getParameter("softskill2");
				String c14skillA3 = request.getParameter("softskill3");
				String c14skillB1 = request.getParameter("hardskill1");
				String c14skillB2 = request.getParameter("hardskill2");
				String c14skillB3 = request.getParameter("hardskill3");
				
				String c14q0 = "twid=" + c14twid;
				String c14q1 = ""; String c14q2 = ""; String c14q3 = ""; String c14q4 = "";	String c14q5 = ""; 
				String c14q6 = ""; String c14q7 = ""; String c14q8 = ""; String c14q9 = ""; String c14q10 = ""; 
				String c14q11 = ""; String c14q12 = ""; String c14q13 = "";
				
				//System.out.println("c14twid = " + c14twid);
				if (c14title != null) {
					//System.out.println("c14title = " + c14title);
				 c14q1 = "&title=" + URLEncoder.encode(c14title, StandardCharsets.UTF_8);
				}
				if (c14desc != null) {
					//System.out.println("c14desc = " + c14desc);
				 c14q2 = "&desc=" + URLEncoder.encode(c14desc, StandardCharsets.UTF_8);
				}
				if (c14cat1 != null) {
					//System.out.println("c14cat1 = " + c14cat1);
					 c14q3 = "&cat1=" + URLEncoder.encode(c14cat1, StandardCharsets.UTF_8);
				}
				if (c14cat2 != null) {
					//System.out.println("c14cat2 = " + c14cat2);
					 c14q4 = "&cat2=" + URLEncoder.encode(c14cat2, StandardCharsets.UTF_8);
				}
				if (c14start != null) {
					//System.out.println("c14start = " + c14start);
					 c14q5 = "&start=" + URLEncoder.encode(c14start, StandardCharsets.UTF_8);
				}
				if (c14rate != null) {
					//System.out.println("c14rate = " + c14rate);
					 c14q6 = "&rate=" + URLEncoder.encode(c14rate, StandardCharsets.UTF_8);
				}
				if (c14comp != null) {
					//System.out.println("c14comp = " + c14comp);
					 c14q7 = "&comp=" + URLEncoder.encode(c14comp, StandardCharsets.UTF_8);
				}
				if (c14skillA1 != null) {
					//System.out.println("c14skillA1 = " + c14skillA1);
					 c14q8 = "&skillA1=" + URLEncoder.encode(c14skillA1, StandardCharsets.UTF_8);
				}
				if (c14skillA2 != null) {
					//System.out.println("c14skillA2 = " + c14skillA2);
					 c14q9 = "&skillA2=" + URLEncoder.encode(c14skillA2, StandardCharsets.UTF_8);
				}
				if (c14skillA3 != null) {
					//System.out.println("c14skillA3 = " + c14skillA3);
					 c14q10 = "&skillA3=" + URLEncoder.encode(c14skillA3, StandardCharsets.UTF_8);
				}
				if (c14skillB1 != null) {
					//System.out.println("c14skillB1 = " + c14skillB1);
					 c14q11 = "&skillB1=" + URLEncoder.encode(c14skillB1, StandardCharsets.UTF_8);
				}
				if (c14skillB2 != null) {
					//System.out.println("c14skillB2 = " + c14skillB2);
					 c14q12 = "&skillB2=" + URLEncoder.encode(c14skillB2, StandardCharsets.UTF_8);
				}
				if (c14skillB3 != null) {
					//System.out.println("c14skillB3 = " + c14skillB3);
					 c14q13 = "&skillB3=" + URLEncoder.encode(c14skillB3, StandardCharsets.UTF_8);
				}
				
				String c14remember = request.getParameter("remember");
				
				
				
				/*
				 * Do database here.
				 */
				
				
				
				
				target = "/WEB-INF/view/post2.jsp?" +  c14q0 +  c14q1 +  
														c14q2 +  c14q3 +  
														c14q4 +  c14q5 +  
														c14q6 +  c14q7 +  
														c14q8 +  c14q9 +  
														c14q10 +  c14q11 +  
														c14q12 +  c14q13;
				break;
			
			
			/*
			 * ----- PROCESS JOB POST WIZARD SUBMIT -----
			 */
			case 15:
					
				int c15twid = (int) session.getAttribute("twid");
				
				Profile c15Profile2 = new Profile();
				c15Profile2.setTwid(c15twid);
				Profile c15Profile = (Profile) DataService.selectOne(c15Profile2);
				
				String c15title = request.getParameter("title");
				String c15desc = request.getParameter("desc");
				String c15cat1 = request.getParameter("cat1");
				String c15cat2 = request.getParameter("cat2");
				String c15start = request.getParameter("start");
				String c15rate = request.getParameter("rate");
				String c15comp = request.getParameter("comp");
				String c15skillA1 = request.getParameter("softskill1");
				String c15skillA2 = request.getParameter("softskill2");
				String c15skillA3 = request.getParameter("softskill3");
				String c15skillB1 = request.getParameter("hardskill1");
				String c15skillB2 = request.getParameter("hardskill2");
				String c15skillB3 = request.getParameter("hardskill3");
				
				String c15q0 = "twid=" + URLEncoder.encode(Integer.toString(c15twid), StandardCharsets.UTF_8);
				String c15q1 = ""; String c15q2 = ""; String c15q3 = ""; String c15q4 = "";	String c15q5 = ""; 
				String c15q6 = ""; String c15q7 = ""; String c15q8 = ""; String c15q9 = ""; String c15q10 = ""; 
				String c15q11 = ""; String c15q12 = ""; String c15q13 = "";
				
				//System.out.println("c15twid = " + c15twid);
				if (c15title != null) {
					//System.out.println("c15title = " + c15title);
					c15q1 = "&title=" + URLEncoder.encode(c15title, StandardCharsets.UTF_8);
				}
				if (c15desc != null) {
					//System.out.println("c15desc = " + c15desc);
					c15q2 = "&desc=" + URLEncoder.encode(c15desc, StandardCharsets.UTF_8);
				}
				if (c15cat1 != null) {
					//System.out.println("c15cat1 = " + c15cat1);
					c15q3 = "&cat1=" + URLEncoder.encode(c15cat1, StandardCharsets.UTF_8);
				}
				if (c15cat2 != null) {
					//System.out.println("c15cat2 = " + c15cat2);
					c15q4 = "&cat2=" + URLEncoder.encode(c15cat2, StandardCharsets.UTF_8);
				}
				if (c15start != null) {
					//System.out.println("c15start = " + c15start);
					c15q5 = "&start=" + URLEncoder.encode(c15start, StandardCharsets.UTF_8);
				}
				if (c15rate != null) {
					//System.out.println("c15rate = " + c15rate);
					c15q6 = "&rate=" + URLEncoder.encode(c15rate, StandardCharsets.UTF_8);
				}
				if (c15comp != null) {
					//System.out.println("c15comp = " + c15comp);
					c15q7 = "&comp=" + URLEncoder.encode(c15comp, StandardCharsets.UTF_8);
				}
				if (c15skillA1 != null) {
					//System.out.println("c15skillA1 = " + c15skillA1);
					c15q8 = "&skillA1=" + URLEncoder.encode(c15skillA1, StandardCharsets.UTF_8);
				}
				if (c15skillA2 != null) {
					//System.out.println("c15skillA2 = " + c15skillA2);
					c15q9 = "&skillA2=" + URLEncoder.encode(c15skillA2, StandardCharsets.UTF_8);
				}
				if (c15skillA3 != null) {
					//System.out.println("c15skillA3 = " + c15skillA3);
					c15q10 = "&skillA3=" + URLEncoder.encode(c15skillA3, StandardCharsets.UTF_8);
				}
				if (c15skillB1 != null) {
					//System.out.println("c15skillB1 = " + c15skillB1);
					c15q11 = "&skillB1=" + URLEncoder.encode(c15skillB1, StandardCharsets.UTF_8);
				}
				if (c15skillB2 != null) {
					//System.out.println("c15skillB2 = " + c15skillB2);
					c15q12 = "&skillB2=" + URLEncoder.encode(c15skillB2, StandardCharsets.UTF_8);
				}
				if (c15skillB3 != null) {
					//System.out.println("c15skillB3 = " + c15skillB3);
					c15q13 = "&skillB3=" + URLEncoder.encode(c15skillB3, StandardCharsets.UTF_8);
				}
				
				String c15submit = request.getParameter("next");
				//System.out.println("submit=" + c15submit);
				
				
				String c15next = request.getParameter("next");
				//System.out.println("next=" + c15next);
				String c15back = request.getParameter("back");
				//System.out.println("back=" + c15back);
				
				String c15jump2 = "";
				if (c15next != null) {
					c15jump2 = c15next;
				}
				if (c15back != null) {
					c15jump2 = c15back;
				}
				
				target = "/WEB-INF/view/" + c15jump2 + "?" + c15q0 + c15q1 + 
														c15q2 + c15q3 + 
														c15q4 + c15q5 + 
														c15q6 + c15q7 + 
														c15q8 + c15q9 + 
														c15q10 + c15q11 + 
														c15q12 + c15q13;
				break;
		
			
			/*
			 * ----- SUBMIT JOB POST -----
			 */
			case 16:
				Mission c16mission = new Mission();
				int c16twid = (int) session.getAttribute("twid");
				
				Profile c16Profile2 = new Profile();
				c16Profile2.setTwid(c16twid);
				Profile c16Profile = (Profile) DataService.selectOne(c16Profile2);
				
				String c16title = request.getParameter("title");
				String c16desc = request.getParameter("desc");
				String c16cat1 = request.getParameter("cat1");
				String c16cat2 = request.getParameter("cat2");
				String c16start = request.getParameter("start");
				String c16rate = request.getParameter("rate");
				String c16comp = request.getParameter("comp");
				String c16skillA1 = request.getParameter("softskill1");
				String c16skillA2 = request.getParameter("softskill2");
				String c16skillA3 = request.getParameter("softskill3");
				String c16skillB1 = request.getParameter("hardskill1");
				String c16skillB2 = request.getParameter("hardskill2");
				String c16skillB3 = request.getParameter("hardskill3");
				
				String c16q0 = "twid=" + URLEncoder.encode(Integer.toString(c16twid), StandardCharsets.UTF_8);
				String c16q1 = ""; String c16q2 = ""; String c16q3 = ""; String c16q4 = "";	String c16q5 = ""; 
				String c16q6 = ""; String c16q7 = ""; String c16q8 = ""; String c16q9 = ""; String c16q10 = ""; 
				String c16q11 = ""; String c16q12 = ""; String c16q13 = "";
				
				//System.out.println("c16twid = " + c16twid);
				if (c16title != null) {
					//System.out.println("c16title = " + c16title);
					c16q1 = "&title=" + URLEncoder.encode(c16title, StandardCharsets.UTF_8);
				}
				if (c16desc != null) {
					//System.out.println("c16desc = " + c16desc);
					c16q2 = "&desc=" + URLEncoder.encode(c16desc, StandardCharsets.UTF_8);
				}
				if (c16cat1 != null) {
					//System.out.println("c16cat1 = " + c16cat1);
					c16q3 = "&cat1=" + URLEncoder.encode(c16cat1, StandardCharsets.UTF_8);
				}
				if (c16cat2 != null) {
					//System.out.println("c16cat2 = " + c16cat2);
					c16q4 = "&cat2=" + URLEncoder.encode(c16cat2, StandardCharsets.UTF_8);
				}
				if (c16start != null) {
					//System.out.println("c16start = " + c16start);
					c16q5 = "&start=" + URLEncoder.encode(c16start, StandardCharsets.UTF_8);
				}
				if (c16rate != null) {
					//System.out.println("c16rate = " + c16rate);
					c16q6 = "&rate=" + URLEncoder.encode(c16rate, StandardCharsets.UTF_8);
				}
				if (c16comp != null) {
					//System.out.println("c16comp = " + c16comp);
					c16q7 = "&comp=" + URLEncoder.encode(c16comp, StandardCharsets.UTF_8);
				}
				if (c16skillA1 != null) {
					//System.out.println("c16skillA1 = " + c16skillA1);
					c16q8 = "&skillA1=" + URLEncoder.encode(c16skillA1, StandardCharsets.UTF_8);
				}
				if (c16skillA2 != null) {
					//System.out.println("c16skillA2 = " + c16skillA2);
					c16q9 = "&skillA2=" + URLEncoder.encode(c16skillA2, StandardCharsets.UTF_8);
				}
				if (c16skillA3 != null) {
					//System.out.println("c16skillA3 = " + c16skillA3);
					c16q10 = "&skillA3=" + URLEncoder.encode(c16skillA3, StandardCharsets.UTF_8);
				}
				if (c16skillB1 != null) {
					//System.out.println("c16skillB1 = " + c16skillB1);
					c16q11 = "&skillB1=" + URLEncoder.encode(c16skillB1, StandardCharsets.UTF_8);
				}
				if (c16skillB2 != null) {
					//System.out.println("c16skillB2 = " + c16skillB2);
					c16q12 = "&skillB2=" + URLEncoder.encode(c16skillB2, StandardCharsets.UTF_8);
				}
				if (c16skillB3 != null) {
					//System.out.println("c16skillB3 = " + c16skillB3);
					c16q13 = "&skillB3=" + URLEncoder.encode(c16skillB3, StandardCharsets.UTF_8);
				}
				
				String c16submit = request.getParameter("next");
				//System.out.println("submit=" + c16submit);
				
				
				String c16next = request.getParameter("next");
				//System.out.println("next=" + c16next);
				String c16back = request.getParameter("back");
				//System.out.println("back=" + c16back);
				
				String c16jump2 = "";
				if (c16next != null) {
					c16jump2 = c16next;
				}
				if (c16back != null) {
					c16jump2 = c16back;
				}
				
				target = "/WEB-INF/view/" + c16jump2 + "?" + c16q0 + c16q1 + 
														c16q2 + c16q3 + 
														c16q4 + c16q5 + 
														c16q6 + c16q7 + 
														c16q8 + c16q9 + 
														c16q10 + c16q11 + 
														c16q12 + c16q13;

				break;
				
				
			/*
			 * ----- PROCESS EMAIL CONFIRMATION CODE FORM -----
			 */
			case 17:
				
				String c17twid = request.getParameter("twid");
				String c17email = request.getParameter("email");
				String c17receivedCode = request.getParameter("received");
				String c17code = request.getParameter("code");
				
				boolean c17received = true;
				boolean c17codeMatched = false;
				
				/*
				 * Did not receive confirmation code.
				 */
				if (c17receivedCode != null) {
					//System.out.println("c17receivedCode = " + c17receivedCode);
					c17received = false;
				}
				
				/*
				 * Resend confirmation code email.
				 */
				if (!c17received) {
					
					String c17emSubject = "Teamwrkr Verification Code";
					EmailService c17ems = new EmailService();
					String c17vcode = c17ems.sendEmailDevelopment(c17email, c17emSubject);
					StatusService.updateStatusKey(Integer.parseInt(c17twid), "vcode", c17vcode);
					target = "/WEB-INF/view/confirmemail.jsp?twid="  + c17twid + "&email=" + c17email;

				}
				else {
					
					/*
					 * User entered a verification code in the form.
					 */
					if (c17code != null) {
						String c17codeToMatch = StatusService.getStatusKeyValue(Integer.parseInt(c17twid), "vcode");
						if (c17code.equals(c17codeToMatch)) {
							c17codeMatched = true;
						}
					}
					
					if (c17codeMatched) {
						
						session.setAttribute("twid", Integer.parseInt(c17twid));
						System.out.println("session.setAttribute(twid) = " + c17twid);
						
						List<Profile> c17rolodexProfiles = RolodexManager.getAllRolodexProfiles();
						session.setAttribute("rolodex", c17rolodexProfiles);
						Profile c17RolodexProfile = RolodexManager.getInitialRolodexProfile(c17rolodexProfiles);
						session.setAttribute("rolodexProfile", c17RolodexProfile);
						int c17rolodexIndex = RolodexManager.getInitialRolodexIndex(c17rolodexProfiles);
						session.setAttribute("rolodexIndex", Integer.toString(c17rolodexIndex));
						int c17rolodexNextIndex = c17rolodexIndex + 1;
						Profile c17RolodexProfileNext = RolodexManager.getRolodexProfile(c17rolodexProfiles, 
																						c17rolodexNextIndex);
						session.setAttribute("rolodexNextProfile", c17RolodexProfileNext);
						int c17rolodexPreviousIndex = c17rolodexIndex - 1;
						Profile c17RolodexProfilePrevious = RolodexManager.getRolodexProfile(c17rolodexProfiles, 
																						c17rolodexPreviousIndex);
						session.setAttribute("rolodexPrevProfile", c17RolodexProfilePrevious);
						
						
						if (c17rolodexProfiles != null) {
							System.out.println("rolodexProfiles.size=" + c17rolodexProfiles.size());
							System.out.println("----- session.setAttribute(rolodex) -----");
							for (Profile c17rp : c17rolodexProfiles) {
								//System.out.println(c17rp.getTwid() + ": " + c17rp.getEmail());
							}
							System.out.println("-----------------------------------------");
						}
						else {
							System.out.println("session.setAttribute(rolodex) = null");
						}
						
						session.setAttribute("rolodexProfile", c17RolodexProfile);		
						if (c17RolodexProfile != null) {
							System.out.println("----- session.setAttribute(rolodexProfile) -----");
							
							System.out.println("------------------------------------------------");
						}
						else {
							System.out.println("session.setAttribute(rolodexProfile) = null");
						}
						
						session.setAttribute("rolodexIndex", Integer.toString(c17rolodexIndex));
						System.out.println("session.setAttribute(rolodexProfile) = " + c17rolodexIndex);
						
						session.setAttribute("rolodexNextProfile", c17RolodexProfileNext);
						if (c17RolodexProfileNext != null) {
							System.out.println("----- session.setAttribute(rolodexNextProfile) -----");
							System.out.println(c17RolodexProfileNext.getTwid() + ": " + c17RolodexProfileNext.getEmail());
							System.out.println("----------------------------------------------------");
						}
						else {
							System.out.println("session.setAttribute(rolodexNextProfile) = null");
						}
						
						session.setAttribute("rolodexPrevProfile", c17RolodexProfilePrevious);
						if (c17RolodexProfilePrevious != null) {
							System.out.println("----- session.setAttribute(rolodexPrevProfile) -----");
							System.out.println(c17RolodexProfilePrevious.getTwid() + ": " + c17RolodexProfilePrevious.getEmail());
							System.out.println("----------------------------------------------------");
						}
						else {
							System.out.println("session.setAttribute(rolodexPrevProfile) = null");
						}
						
						target = "/WEB-INF/view/chgpwd.jsp?twid=" + c17twid;
						
					}
					else {
						
						target = "/WEB-INF/view/confirmemail.jsp?twid="  + c17twid + "&email=" + c17email + "&match=false";
						
					}
							
				}
				
				break;
			
			/*
			 * ----- DISPLAY CHANGE PASSWORD FORM (USER LOGGED IN) -----
			 */
			case 18:
				
				int c18twid = (int) session.getAttribute("twid");
				
				target = "/WEB-INF/view/chgpwd.jsp?twid="  + c18twid;
				
				break;
				
			/*
			 * ----- PROCESS CHANGE PASSWORD FORM (USER LOGGED IN) -----
			 */
			case 19:
				
				int c19twid = (int) session.getAttribute("twid");
				
				Profile c19profile = new Profile();
				c19profile.setTwid(c19twid);
				Profile c19profile0 = (Profile) DataService.selectOne(c19profile);
				String c19userEmail = c19profile0.getEmail();

				String c19pwd = request.getParameter("pwd");
				String c19encPwd = LoginService.encrypt(c19pwd);
				
				Profile c19profile1 = new Profile();
				c19profile1.setTwid(c19twid);
				c19profile1.setEncPwd(c19encPwd);
				
				Profile c19profile2 = new Profile();
				c19profile2.setTwid(c19twid);

				DataService.update(c19profile1, c19profile2);

				break;	
				
			/*
			 * ----- LOGOUT -----
			 */
			case 22:
				
				session.invalidate();
				
				target = "logout.jsp";
				
				break;	
				
			/*
			 * ----- NOTIFICATIONS -----
			 */
			case 24:
				
				int c24twid = (int) session.getAttribute("twid");
				
				
				
				target = "/WEB-INF/view/notifications.jsp?twid="  + c24twid;
				
				break;	
				
				
			/*
			 * ----- SUBMIT JOB POST -----
			 */	
			case 26:
				
				int c26twid = (int) session.getAttribute("twid");
				
				SiteConstants c26sc = new SiteConstants();
				
				Profile c26Profile2 = new Profile();
				c26Profile2.setTwid(c26twid);
				Profile c26Profile = (Profile) DataService.selectOne(c26Profile2);
				
				String c26fn = c26Profile.getFirstName();
				String c26ln = c26Profile.getLastName();
				String c26mgr = c26fn + " " + c26ln;
				String c26email = c26Profile.getEmail();
				
				String c26title = request.getParameter("title");
				String c26desc = request.getParameter("desc");
				String c26cat1 = request.getParameter("cat1");
				String c26cat2 = request.getParameter("cat2");
				String c26start = request.getParameter("start");
				String c26rate = request.getParameter("rate");
				String c26comp = request.getParameter("comp");
				String c26skillA1 = request.getParameter("softskill1");
				String c26skillA2 = request.getParameter("softskill2");
				String c26skillA3 = request.getParameter("softskill3");
				String c26skillB1 = request.getParameter("hardskill1");
				String c26skillB2 = request.getParameter("hardskill2");
				String c26skillB3 = request.getParameter("hardskill3");
				
				String c26submit = request.getParameter("next");
				//System.out.println("submit=" + c26submit);
				String c26next = request.getParameter("next");
				//System.out.println("next=" + c26next);
				String c26back = request.getParameter("back");
				//System.out.println("back=" + c26back);
				
				/*
				 * Add job to database.
				 */
				if (c26submit != null) {
					
					Mission c26mission = new Mission();
					c26mission.setTwid1(c26twid);
					c26mission.setMissionType(c26sc.MISSION_TYPE_JOB_POST);
					c26mission.setTitle(c26title);
					c26mission.setDesc(c26desc);
					c26mission.setCategory1(c26cat1);
					c26mission.setCategory2(c26cat2);
					c26mission.setManager(c26mgr);
					c26mission.setMgrEmail(c26email);
					c26mission.setStartDate(SqlUtils.getDate3(c26start));
					c26mission.setRate(Integer.parseInt(c26rate));
					c26mission.setType(Integer.parseInt(c26comp));
					c26mission.setSoftskill1(c26skillA1);
					c26mission.setSoftskill2(c26skillA2);
					c26mission.setSoftskill3(c26skillA3);
					c26mission.setHardskill1(c26skillB1);
					c26mission.setHardskill2(c26skillB2);
					c26mission.setHardskill3(c26skillB3);
						
					Calendar c26calendar = Calendar.getInstance();
					Date c26now = c26calendar.getTime();
					c26mission.setPostedDate(c26now);
						
					c26calendar.add(Calendar.DATE, 7);
					Date c26exp = c26calendar.getTime();
					c26mission.setExpiresDate(c26exp);
						
					DataService.create(c26mission);
					
					Mission c26missionTemp = new Mission();
					String c26qs = "twid1 = " + c26twid;
					Object[] c26missions = DataService.selectMany(c26missionTemp, c26qs);
					int c26mId = 0;
					for (Object c26miss : c26missions) {
						Mission c26m = (Mission) c26miss;
						if (c26m.getMissionId() > c26mId) {
							c26mId = c26m.getMissionId();
						}
					}
					
					
					/*
					 * ----- INTELLIGENT JOB MATCHING -----
					 */
					IntelligentJobMatchManager.match(3, c26mId);
						
					/*
					 * Send email to Teamwrkr account with summary of job posting.
					 * User needs advice on selecting rate.
					 */
					if (c26comp.equals("2")) {
						
					}
					
				}
				
				String c26q0 = "twid=" + URLEncoder.encode(Integer.toString(c26twid), StandardCharsets.UTF_8);
				String c26q1 = ""; String c26q2 = ""; String c26q3 = ""; String c26q4 = "";	String c26q5 = ""; 
				String c26q6 = ""; String c26q7 = ""; String c26q8 = ""; String c26q9 = ""; String c26q10 = ""; 
				String c26q11 = ""; String c26q12 = ""; String c26q13 = "";
				
				//System.out.println("c26twid = " + c26twid);
				if (c26title != null) {
					//System.out.println("c26title = " + c26title);
					c26q1 = "&title=" + URLEncoder.encode(c26title, StandardCharsets.UTF_8);
				}
				if (c26desc != null) {
					//System.out.println("c26desc = " + c26desc);
					c26q2 = "&desc=" + URLEncoder.encode(c26desc, StandardCharsets.UTF_8);
				}
				if (c26cat1 != null) {
					//System.out.println("c26cat1 = " + c26cat1);
					c26q3 = "&cat1=" + URLEncoder.encode(c26cat1, StandardCharsets.UTF_8);
				}
				if (c26cat2 != null) {
					//System.out.println("c26cat2 = " + c26cat2);
					c26q4 = "&cat2=" + URLEncoder.encode(c26cat2, StandardCharsets.UTF_8);
				}
				if (c26start != null) {
					//System.out.println("c26start = " + c26start);
					c26q5 = "&start=" + URLEncoder.encode(c26start, StandardCharsets.UTF_8);
				}
				if (c26rate != null) {
					//System.out.println("c26rate = " + c26rate);
					c26q6 = "&rate=" + URLEncoder.encode(c26rate, StandardCharsets.UTF_8);
				}
				if (c26comp != null) {
					//System.out.println("c26comp = " + c26comp);
					c26q7 = "&comp=" + URLEncoder.encode(c26comp, StandardCharsets.UTF_8);
				}
				if (c26skillA1 != null) {
					//System.out.println("c26skillA1 = " + c26skillA1);
					c26q8 = "&skillA1=" + URLEncoder.encode(c26skillA1, StandardCharsets.UTF_8);
				}
				if (c26skillA2 != null) {
					//System.out.println("c26skillA2 = " + c26skillA2);
					c26q9 = "&skillA2=" + URLEncoder.encode(c26skillA2, StandardCharsets.UTF_8);
				}
				if (c26skillA3 != null) {
					//System.out.println("c26skillA3 = " + c26skillA3);
					c26q10 = "&skillA3=" + URLEncoder.encode(c26skillA3, StandardCharsets.UTF_8);
				}
				if (c26skillB1 != null) {
					//System.out.println("c26skillB1 = " + c26skillB1);
					c26q11 = "&skillB1=" + URLEncoder.encode(c26skillB1, StandardCharsets.UTF_8);
				}
				if (c26skillB2 != null) {
					//System.out.println("c26skillB2 = " + c26skillB2);
					c26q12 = "&skillB2=" + URLEncoder.encode(c26skillB2, StandardCharsets.UTF_8);
				}
				if (c26skillB3 != null) {
					//System.out.println("c26skillB3 = " + c26skillB3);
					c26q13 = "&skillB3=" + URLEncoder.encode(c26skillB3, StandardCharsets.UTF_8);
				}
				
				String c26jump2 = "";
				if (c26next != null) {
					c26jump2 = c26next;
				}
				if (c26back != null) {
					c26jump2 = c26back;
				}
				
				
				String targetPath = "";
				if (!c26jump2.equals("post.jsp")) {
					targetPath = "/WEB-INF/view/";
				}
				
				target = targetPath + c26jump2 + "?" + c26q0 + c26q1 + 
														c26q2 + c26q3 + 
														c26q4 + c26q5 + 
														c26q6 + c26q7 + 
														c26q8 + c26q9 + 
														c26q10 + c26q11 + 
														c26q12 + c26q13;
				
				break;
				
			default:
				break;
				
			
		
		}
		
		
		RequestDispatcher rd = request.getRequestDispatcher(target);
		try {
			System.out.println("Dispatching HttpRequest to target = " + target);
			rd.forward(request, response);
		}
		catch (Exception e) {
			System.out.println("Error dispatching HttpRequest to target = " + target);
			e.printStackTrace();
		}
		
	}


	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	
	protected void doHead(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	
	protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	
	protected void doTrace(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	
}

