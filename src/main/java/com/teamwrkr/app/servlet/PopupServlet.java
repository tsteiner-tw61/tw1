package com.teamwrkr.app.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;

import com.teamwrkr.app.dto.*;
import com.teamwrkr.app.manager.PointsManager;
import com.teamwrkr.app.service.*;
import com.teamwrkr.app.util.*;
import com.teamwrkr.app.web.*;


/**
 * Servlet implementation class PopupServlet
 */
public class PopupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public PopupServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/*
		 * Test console output for doGet() call.
		 * Comment out in production.
		 */
		System.out.println("call PopupServlet.doGet()");
		
		
		String query = request.getParameter("query");
		int q = Integer.parseInt(query);
		System.out.println("query=" + q);
		
		String target = "";
		
		switch(q) {
		
		case 1:
			
			System.out.println("target: " + target);
			break;
			
		default:
			break;
		
		}
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * Test console output for doPost() call.
		 * Comment out in production.
		 */
		System.out.println("call PopupServlet.doPost()");
		
		SiteConstants sc = new SiteConstants();
		
		
		
		String query = request.getParameter("query");
		int q = Integer.parseInt(query);
		System.out.println("query=" + q);
		
		String target = "";
		
		switch(q) {
		
		/*
		 * Edit name and location popup handler.
		 */
		case 1:
			
			String twid = request.getParameter("twid");
			int c1tw = Integer.parseInt(twid);
			
			String fn = request.getParameter("fn");
			String ln = request.getParameter("ln");
			String city = request.getParameter("city");
			String sp = request.getParameter("sp");
			String country = request.getParameter("country");
			
			Profile c1profile = new Profile();
			c1profile.setTwid(c1tw);
			c1profile.setFirstName(fn);
			c1profile.setLastName(ln);
			c1profile.setCity(city);
			c1profile.setState(sp);
			c1profile.setCountry(country);
			
			Profile c1profile2 = new Profile();
			c1profile2.setTwid(c1tw);
			DataService.update(c1profile, c1profile2);
			
			break;
			
		/*
		 * Edit measurable skills popup handler.
		 */			
		case 2:
			
			String c2twid = request.getParameter("twid");
			int c2tw = Integer.parseInt(c2twid);
			
			String boxes2 = request.getParameter("boxes2");
			int bx2 = Integer.parseInt(boxes2);
			
			int hsi = 0;
			String[] hardSkills = new String[5];
			String[] hard = new String[bx2];
			for (int i = 0; i < bx2; i++) {
				hard[i] = request.getParameter("hard" + (i+1));
				if (hard[i] != null && !hard[i].isEmpty()) {
					hardSkills[hsi] = hard[i];
					//System.out.println("hardSkills[" + hsi + "]=" + hardSkills[hsi]);
					hsi++;
				}
			}
			
			Profile c2profile = new Profile();
			c2profile.setTwid(c2tw);
			
			if (WebUtils.isNotEmpty(hardSkills[0])) {
				c2profile.setUserSkill1(hardSkills[0]);
			}
			if (WebUtils.isNotEmpty(hardSkills[1])) {
				c2profile.setUserSkill2(hardSkills[1]);
			}
			if (WebUtils.isNotEmpty(hardSkills[2])) {
				c2profile.setUserSkill3(hardSkills[2]);
			}
			
			Profile c2profile2 = new Profile();
			c2profile2.setTwid(c2tw);
			DataService.update(c2profile, c2profile2);
			
			PointsManager.addPoints(c2tw, sc.ACTION_POINTS_PROFILE_SKILLS);
			
			
			break;
			
			
		/*
		 * Edit social media URL popup form.
		 */			
		case 3:
			
			String c3twid = request.getParameter("twid");
			int c3tw = Integer.parseInt(c3twid);
			
			String url1 = request.getParameter("url1");
			String url2 = request.getParameter("url2");
			String url3 = request.getParameter("url3");
			String url4 = request.getParameter("url4");
			String url5 = request.getParameter("url5");
			String url6 = request.getParameter("url6");
			
			
			Profile c3profile = new Profile();
			c3profile.setTwid(c3tw);
			c3profile.setUrl1(url1);
			c3profile.setUrl2(url2);
			c3profile.setUrl3(url3);
			c3profile.setUrl4(url4);
			c3profile.setUrl5(url5);
			c3profile.setUrl6(url6);

			Profile c3profile2 = new Profile();
			c3profile2.setTwid(c3tw);
			DataService.update(c3profile, c3profile2);
			
			PointsManager.addPoints(c3tw, sc.ACTION_POINTS_PROFILE_URL);
			
			break;
					
			/*
			 * Process New Job Post popup.
			 */
			case 5:
				
				String c5twid = request.getParameter("twid");
				int c5tw = Integer.parseInt(c5twid);
					
				String enteredJobTitle = request.getParameter("jobtitleexp");
				String enteredJobMgrEmail = request.getParameter("mgremailexp");
				String enteredJobStartDate = request.getParameter("startexp");
				String enteredJobEndDate = request.getParameter("endexp");
				String enteredJobDescription = request.getParameter("descexp");
				String userEmail = request.getParameter("userEmail");
				
				String enteredSoftSkills = request.getParameter("softskillsexp");
				String[] enteredJobSoftSkills = enteredSoftSkills.split(",");
				
				String enteredHardSkills = request.getParameter("hardskillsexp");
				String[] enteredJobHardSkills = enteredHardSkills.split(",");
				
				SiteConstants sc5 = new SiteConstants();
				
				/*
				 * Note here that the Mission is not being created
				 * until much further along in the code.
				 */
				Mission c5mission = new Mission();
				c5mission.setTwid1(c5tw);
				c5mission.setTitle(enteredJobTitle);
				c5mission.setDesc(enteredJobDescription);
				c5mission.setStartDate(SqlUtils.getDate1(enteredJobStartDate));
				c5mission.setEndDate(SqlUtils.getDate1(enteredJobEndDate));
				c5mission.setMgrEmail(enteredJobMgrEmail);
				c5mission.setMissionType(sc5.MISSION_TYPE_WORK_EXP);
				c5mission.setEnteredDate(Calendar.getInstance().getTime());
				
				/*
				 * Note here that the Survey is not being created
				 * until much further along in the code.
				 */
				Survey c5survey = new Survey();
				c5survey.setTwid(c5tw);
				c5survey.setTitle(enteredJobTitle);
				c5survey.setDesc(enteredJobDescription);
				c5survey.setMgrEmail(enteredJobMgrEmail);
				c5survey.setUserEmail(userEmail);
				c5survey.setStartDate(SqlUtils.getDate1(enteredJobStartDate));
				c5survey.setEndDate(SqlUtils.getDate1(enteredJobEndDate));
				
				/*
				 * Process soft skills. Only take 2.
				 */
				List<String> selectedSoftSkills = new ArrayList<String>();
				for (int i = 0; i < enteredJobSoftSkills.length; i++) {
					if (i == 0) {
						c5mission.setSoftskill1(enteredJobSoftSkills[i]);
						c5survey.setSoftskill1(enteredJobSoftSkills[i]);
						if (enteredJobSoftSkills[i] != null) {
							selectedSoftSkills.add(enteredJobSoftSkills[i]);
						}
					}
					if (i == 1) {
						c5mission.setSoftskill2(enteredJobSoftSkills[i]);
						c5survey.setSoftskill2(enteredJobSoftSkills[i]);
						if (enteredJobSoftSkills[i] != null) {
							selectedSoftSkills.add(enteredJobSoftSkills[i]);
						}
					}
				}
				
				/*
				 * Process hard skills. Only take 2.
				 */
				List<String> selectedHardSkills = new ArrayList<String>();
				for (int i = 0; i < enteredJobHardSkills.length; i++) {
					if (i == 0) {
						c5mission.setHardskill1(enteredJobHardSkills[i]);
						c5survey.setHardskill1(enteredJobHardSkills[i]);
						if (enteredJobHardSkills[i] != null) {
							selectedHardSkills.add(enteredJobHardSkills[i]);
						}
					}
					if (i == 1) {
						c5mission.setHardskill2(enteredJobHardSkills[i]);
						c5survey.setHardskill2(enteredJobHardSkills[i]);
						if (enteredJobHardSkills[i] != null) {
							selectedHardSkills.add(enteredJobHardSkills[i]);
						}
					}
					
				}
				
				/*
				 * We need to get the Profile to determine the
				 * hard skills that are available to be selected
				 * to randomly add to the survey.
				 */
				Profile c5profile = new Profile();
				c5profile.setTwid(c5tw);
				Profile c5p = (Profile) DataService.selectOne(c5profile);
				
				/*
				 * Get the list of measurable skills for the category matrix 
				 * of the user profile.
				 * TODO might not need this
				 */
				//List<String> hSkills = SkillService.getHardSkills(c5p.getCategory1(), c5p.getCategory2());

				/*
				 * Get a random skill from each category.
				 */
				String c5randomHardSkill = SkillService.getRandomSkill(Arrays.asList(enteredJobHardSkills), c5p, "hard");
				String c5randomSoftSkill = SkillService.getRandomSkill(Arrays.asList(enteredJobSoftSkills), c5p, "soft");
				
				/*
				 * Add the random hard skills to the survey content,
				 * unless there are no more available applicable 
				 * skills to add.
				 */
				if (c5randomHardSkill != null) {
					selectedHardSkills.add(c5randomHardSkill);
					c5survey.setHardskill3(c5randomHardSkill);
				}
				
				/*
				 * Add the random soft skills to the survey content.
				 * Since the randomSoftSkill will never equal null
				 * we can omit the null check.
				 */
				selectedSoftSkills.add(c5randomSoftSkill);
				c5survey.setSoftskill3(c5randomSoftSkill);
				
				/*
				 * Create the Mission set up above.
				 */
				DataService.create(c5mission);
				
				/*
				 * Find the surveyId of the survey just created.
				 * Get all surveyIds for the twid and take the 
				 * highest.
				 */
				String c5qs = " twid1 = " + c5twid;
				Mission c5mission2 = new Mission();
				Object[] missions = DataService.selectMany(c5mission2, c5qs);
				int c5missionId = 0;
				for (Object mission : missions) {
					Mission ms1 = (Mission) mission;
					if (ms1.getMissionId() > c5missionId) {
						c5missionId = ms1.getMissionId();
					}
				}
				
				/*
				 * Once the missionId has been found, then we
				 * can use this in creating the surveyId and finding
				 * the surveyId of the record, once created.
				 */
				c5survey.setMissionId(c5missionId);
				DataService.create(c5survey);
				
				/*
				 * Create a new Survey object equal to the old
				 * one to send out via Email.
				 */
				Survey c5survey2 = new Survey();
				c5survey2.setMissionId(c5missionId);
				Survey c5survey3 = (Survey) DataService.selectOne(c5survey2, 2);
				
				Email c5email = new Email(c5survey3, c5p);
				
				//EmailService.sendEmail(c5survey.getMgrEmail(), c5email);

				boolean emailSent = false;
				
				emailSent = EmailService.sendEmail(c5survey.getMgrEmail(), c5email);
				PointsManager.addPoints(c5tw, sc.ACTION_POINTS_PROFILE_REVIEW_SEND);
				/*
				if (!emailSent) {
					Mission c51mission = new Mission();
					c51mission.setMissionId(c5missionId);
					c51mission.setPostedDate(Calendar.getInstance().getTime());
					Mission c52mission = new Mission();
					c52mission.setMissionId(c5missionId);
					DataService.update(c51mission, c52mission);
				}
				*/
				break;				
			
		/*
		 * Edit my calendar popup handler.	
		 */
		case 6:
			
			String c6twid = request.getParameter("twid");
			int c6tw = Integer.parseInt(c6twid);
			
			String[] calday = new String[28];
			for (int i = 0; i < 28; i++) {
				calday[i] = request.getParameter("calday" + (i+1));
				//System.out.println("calday[" + i + "]=" + calday[i]);
			}
			
			// TODO convert this to DataService.selectOne
			Availability c6av1 = new Availability();
			c6av1.setTwid(c6tw);
			Availability c6av = (Availability) DataService.selectOne(c6av1);
			//Availability av = AvailabilityService.selectAvailability(c6tw);
			
			if (calday[0].equals("0")) {c6av.setSun1(false);}else {c6av.setSun1(true);}
			if (calday[1].equals("0")) {c6av.setMon1(false);}else {c6av.setMon1(true);}
			if (calday[2].equals("0")) {c6av.setTue1(false);}else {c6av.setTue1(true);}
			if (calday[3].equals("0")) {c6av.setWed1(false);}else {c6av.setWed1(true);}
			if (calday[4].equals("0")) {c6av.setThu1(false);}else {c6av.setThu1(true);}
			if (calday[5].equals("0")) {c6av.setFri1(false);}else {c6av.setFri1(true);}
			if (calday[6].equals("0")) {c6av.setSat1(false);}else {c6av.setSat1(true);}
			
			if (calday[7].equals("0")) {c6av.setSun2(false);}else {c6av.setSun2(true);}
			if (calday[8].equals("0")) {c6av.setMon2(false);}else {c6av.setMon2(true);}
			if (calday[9].equals("0")) {c6av.setTue2(false);}else {c6av.setTue2(true);}
			if (calday[10].equals("0")) {c6av.setWed2(false);}else {c6av.setWed2(true);}
			if (calday[11].equals("0")) {c6av.setThu2(false);}else {c6av.setThu2(true);}
			if (calday[12].equals("0")) {c6av.setFri2(false);}else {c6av.setFri2(true);}
			if (calday[13].equals("0")) {c6av.setSat2(false);}else {c6av.setSat2(true);}
			
			if (calday[14].equals("0")) {c6av.setSun3(false);}else {c6av.setSun3(true);}
			if (calday[15].equals("0")) {c6av.setMon3(false);}else {c6av.setMon3(true);}
			if (calday[16].equals("0")) {c6av.setTue3(false);}else {c6av.setTue3(true);}
			if (calday[17].equals("0")) {c6av.setWed3(false);}else {c6av.setWed3(true);}
			if (calday[18].equals("0")) {c6av.setThu3(false);}else {c6av.setThu3(true);}
			if (calday[19].equals("0")) {c6av.setFri3(false);}else {c6av.setFri3(true);}
			if (calday[20].equals("0")) {c6av.setSat3(false);}else {c6av.setSat3(true);}
			
			if (calday[21].equals("0")) {c6av.setSun4(false);}else {c6av.setSun4(true);}
			if (calday[22].equals("0")) {c6av.setMon4(false);}else {c6av.setMon4(true);}
			if (calday[23].equals("0")) {c6av.setTue4(false);}else {c6av.setTue4(true);}
			if (calday[24].equals("0")) {c6av.setWed4(false);}else {c6av.setWed4(true);}
			if (calday[25].equals("0")) {c6av.setThu4(false);}else {c6av.setThu4(true);}
			if (calday[26].equals("0")) {c6av.setFri4(false);}else {c6av.setFri4(true);}
			if (calday[27].equals("0")) {c6av.setSat4(false);}else {c6av.setSat4(true);}
			
			// TODO convert this to DataService.update
			Availability c6av2 = new Availability();
			c6av2.setTwid(c6tw);
			Availability c6av3 = new Availability(c6av);
			DataService.update(c6av3, c6av2);
			//AvailabilityService.updateAvailability(av);
			
			break;	
			
			
		/*
		 * Edit niche popup form handler.	
		 */
		case 12:

			String c12twid = request.getParameter("twid");
			int c12tw = Integer.parseInt(c12twid);
			
			String niche = request.getParameter("niche");
			
			Profile c12profile = new Profile();
			c12profile.setTwid(c12tw);
			c12profile.setNiche(niche);
			
			Profile c12profile2 = new Profile();
			c12profile2.setTwid(c12tw);
			DataService.update(c12profile, c12profile2);
			
			PointsManager.addPoints(c12tw, sc.ACTION_POINTS_PROFILE_NICHE);
			
			break;
			
			
		case 120:
			
			System.out.println("accepting notification index = ***************************************************************************************************************");
			
			String accept = request.getParameter("accept");
			
			if (accept != null && !accept.isEmpty()) {
				System.out.println("accept = " + accept);
			}
			else {
				System.out.println("accept IS NULL");
			}
			
			break;
			
		default:
			
			break;
		
		}
		
	}

}

