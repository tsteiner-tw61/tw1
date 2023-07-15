package com.teamwrkr.app.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.teamwrkr.app.dto.*;
import com.teamwrkr.app.service.*;
import com.teamwrkr.app.util.*;

/**
 * Servlet implementation class SurveyServlet
 */
public class SurveyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public SurveyServlet() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("call SurveyServlet.doPost()");
		
		/*
		 * Process form inputs.
		 */
		String sid = request.getParameter("sid");
		String tw = request.getParameter("tw"); 
		String skillh1name = request.getParameter("skillh1name");
		String skillh1score = request.getParameter("skillh1score");
		String skillh2name = request.getParameter("skillh2name");
		String skillh2score = request.getParameter("skillh2score");
		String skillh3name = request.getParameter("skillh3name");
		String skillh3score = request.getParameter("skillh3score");
		String skills1name = request.getParameter("skills1name");
		String skills1score = request.getParameter("skills1score");
		String skills2name = request.getParameter("skills2name");
		String skills2score = request.getParameter("skills2score");
		String skills3name = request.getParameter("skills3name");
		String skills3score = request.getParameter("skills3score");
		String overallscore = request.getParameter("overallscore");
		String surveycomments = request.getParameter("surveycomments");
		
		System.out.println("skillh1name=" + skillh1name);
		System.out.println("skillh2name=" + skillh2name);
		System.out.println("skillh3name=" + skillh3name);
		System.out.println("skills1name=" + skills1name);
		System.out.println("skills2name=" + skills2name);
		System.out.println("skills3name=" + skills3name);
		System.out.println("skillh1score=" + skillh1score);
		System.out.println("skillh2score=" + skillh2score);
		System.out.println("skillh3score=" + skillh3score);
		System.out.println("skills1score=" + skills1score);
		System.out.println("skills2score=" + skills2score);
		System.out.println("skills3score=" + skills3score);
		System.out.println("overallscore=" + overallscore);
		
		/*
		 * Get the incoming survey from the database.
		 */
		Survey sv1 = new Survey();
		sv1.setSurveyId(Integer.parseInt(sid));
		Survey s2 = (Survey) DataService.selectOne(sv1);
		
		/*
		 * Modify the incoming survey with the survey scores
		 * from the survey form just submitted.
		 * 
		 * Note: Due to the way the DataService.update()
		 * method works, you must create a new Survey object
		 * and copy the values of the Survey object that
		 * you intend to update for the method to function
		 * properly.
		 */
		Survey incomingSurvey1 = new Survey();
		incomingSurvey1.setSurveyId(Integer.parseInt(sid));
		incomingSurvey1.setTwid(s2.getTwid());
		incomingSurvey1.setMissionId(s2.getMissionId());
		incomingSurvey1.setHardskill1(skillh1name);
		incomingSurvey1.setHardskill2(skillh2name);
		incomingSurvey1.setHardskill3(skillh3name);
		incomingSurvey1.setScoreh1(Integer.parseInt(skillh1score));
		incomingSurvey1.setScoreh2(Integer.parseInt(skillh2score));
		incomingSurvey1.setScoreh3(Integer.parseInt(skillh3score));
		incomingSurvey1.setSoftskill1(skills1name);
		incomingSurvey1.setSoftskill2(skills2name);
		incomingSurvey1.setSoftskill3(skills3name);
		incomingSurvey1.setScores1(Integer.parseInt(skills1score));
		incomingSurvey1.setScores2(Integer.parseInt(skills2score));
		incomingSurvey1.setScores3(Integer.parseInt(skills3score));
		incomingSurvey1.setScoreoverall(Integer.parseInt(overallscore));
		incomingSurvey1.setComments(surveycomments);
		incomingSurvey1.setCompleteDate(Calendar.getInstance().getTime());
		
		/*
		 * Update the survey values in the database.
		 */
		DataService.update(incomingSurvey1, sv1);
		
		/*
		 * Update the related mission status to verified.
		 */
		
		Mission m1 = new Mission();
		m1.setMissionId(s2.getMissionId());
		Mission m2 = (Mission) DataService.selectOne(m1);
		Mission m3 = new Mission(m2);
		System.out.println("m3.getMissionId() = " + m3.getMissionId());
		m3.setReferenceDate(Calendar.getInstance().getTime());
		DataService.update(m3, m1);
		
		/*
		 * Get the profile for skills data.
		 */
		Profile p = new Profile();
		p.setTwid(Integer.parseInt(tw));
		Profile profile = (Profile) DataService.selectOne(p);
		
		/*
		 * First, get the user-selected hard skills.
		 */
		List<String> selectedHardSkills = new ArrayList<String>();
		if (WebUtils.isNotEmpty(profile.getUserSkill1())) {
			selectedHardSkills.add(profile.getUserSkill1());
		}
		if (WebUtils.isNotEmpty(profile.getUserSkill2())) {
			selectedHardSkills.add(profile.getUserSkill2());
		}
		if (WebUtils.isNotEmpty(profile.getUserSkill3())) {
			selectedHardSkills.add(profile.getUserSkill3());
		}
		
		/*
		 * Next get all the possible hard skills for 
		 * this users category matrix.
		 */
		List<String> allHardSkills = SkillService.getHardSkills(profile.getCategory1(), profile.getCategory2());
		List<String> allSoftSkills = SkillService.getSoftSkills();
		
		/*
		 * Compare the list of user-selected hard skills
		 * to the overall list of hard skills for that 
		 * user's category matrix. Make a new list with
		 * the remaining selectable hard skills for that
		 * user.
		 */
		
		
		
		
		
		/*
		 * Make a map with all the hard skills for the user
		 * profile and set the values to 0, and another for
		 * the survey counts.
		 */
		System.out.println("SURVEY-SURVEY-SURVEY-SURVEY-SURVEY-SURVEY-SURVEY-SURVEY-SURVEY-SURVEY-SURVEY-SURVEY-SURVEY-");
		Map<String,Integer> allHardSkillsSurveyScores = new HashMap<String,Integer>();
	    for (String hs : allHardSkills) {
	    	allHardSkillsSurveyScores.put(hs, 0);
	    }
	    Map<String,Integer> allHardSkillsSurveyCounts = new HashMap<String,Integer>();
	    for (String hs : allHardSkills) {
	    	allHardSkillsSurveyCounts.put(hs, 0);
	    }
	    Map<String,Double> allHardSkillsAggregateScores = new HashMap<String,Double>();
	    for (String hs : allHardSkills) {
	    	allHardSkillsAggregateScores.put(hs, 0.0);
	    }
	    
	    /*
		 * Make a map with all the soft skills for the user
		 * profile and set the values to 0, and another for
		 * the survey counts.
		 */
		Map<String,Integer> allSoftSkillsSurveyScores = new HashMap<String,Integer>();
	    for (String ss : allSoftSkills) {
	    	allSoftSkillsSurveyScores.put(ss, 0);
	    }
	    Map<String,Integer> allSoftSkillsSurveyCounts = new HashMap<String,Integer>();
	    for (String ss : allSoftSkills) {
	    	allSoftSkillsSurveyCounts.put(ss, 0);
	    }
	    Map<String,Double> allSoftSkillsAggregateScores = new HashMap<String,Double>();
	    for (String ss : allSoftSkills) {
	    	allSoftSkillsAggregateScores.put(ss, 0.0);
	    }
	    
	    /*
		 * Select all the surveys for the given user.
		 */
		Survey survey = new Survey();
		Object[] surveys = DataService.selectMany(survey, "twid = " + tw + " AND completeDate IS NOT NULL");
		
		/*
		 * Manage survey update blocks here.
		 * Fetch the first 3, then every 2
		 * thereafter.
		 */
		int surveysFetched = surveys.length;
		int surveysToProcess = surveys.length;
		if (surveysFetched >= 3) {
			if (surveysFetched % 2 == 0 && surveysFetched != 1) {
				surveysToProcess = surveysFetched - 1;
			}
		}
		else {
			surveysToProcess = 0;
		}
		
		
		for (int i = 0; i < surveysToProcess; i++) {
			Survey sv = (Survey) surveys[i];
			
			
			/*
			 * Overall scores.
			 */
			
			
			
			/*
			 * Hard skills
			 */
			if (WebUtils.isNotEmpty(sv.getHardskill1())) {
				String hskill = sv.getHardskill1();
				int hsc = sv.getScores1();
				if (hsc > 0) {
					int score1 = allHardSkillsSurveyScores.get(hskill);
					int score2 = score1 + hsc;
					allHardSkillsSurveyScores.put(hskill, score2);
					int count1 = allHardSkillsSurveyCounts.get(hskill);
					int count2 = count1 + 1;
					System.out.println("allHardSkillsSurveyCounts put " + hskill + " " + count2);
					allHardSkillsSurveyCounts.put(hskill, count2);
				}
			}
			if (WebUtils.isNotEmpty(sv.getHardskill2())) {
				String hskill = sv.getHardskill2();
				int hsc = sv.getScores2();
				if (hsc > 0) {
					int score1 = allHardSkillsSurveyScores.get(hskill);
					int score2 = score1 + hsc;
					allHardSkillsSurveyScores.put(hskill, score2);
					int count1 = allHardSkillsSurveyCounts.get(hskill);
					int count2 = count1 + 1;
					System.out.println("allHardSkillsSurveyCounts put " + hskill + " " + count2);
					allHardSkillsSurveyCounts.put(hskill, count2);
				}
			}
			if (WebUtils.isNotEmpty(sv.getHardskill3())) {
				String hskill = sv.getHardskill3();
				int hsc = sv.getScores3();
				if (hsc > 0) {
					int score1 = allHardSkillsSurveyScores.get(hskill);
					int score2 = score1 + hsc;
					allHardSkillsSurveyScores.put(hskill, score2);
					int count1 = allHardSkillsSurveyCounts.get(hskill);
					int count2 = count1 + 1;
					System.out.println("allHardSkillsSurveyCounts put " + hskill + " " + count2);
					allHardSkillsSurveyCounts.put(hskill, count2);
				}
			}
			if (WebUtils.isNotEmpty(sv.getHardskill4())) {
				String hskill = sv.getHardskill4();
				int hsc = sv.getScores4();
				if (hsc > 0) {
					int score1 = allHardSkillsSurveyScores.get(hskill);
					int score2 = score1 + hsc;
					allHardSkillsSurveyScores.put(hskill, score2);
					int count1 = allHardSkillsSurveyCounts.get(hskill);
					int count2 = count1 + 1;
					System.out.println("allHardSkillsSurveyCounts put " + hskill + " " + count2);
					allHardSkillsSurveyCounts.put(hskill, count2);
				}
			}
			if (WebUtils.isNotEmpty(sv.getHardskill5())) {
				String hskill = sv.getHardskill5();
				int hsc = sv.getScores5();
				if (hsc > 0) {
					int score1 = allHardSkillsSurveyScores.get(hskill);
					int score2 = score1 + hsc;
					allHardSkillsSurveyScores.put(hskill, score2);
					int count1 = allHardSkillsSurveyCounts.get(hskill);
					int count2 = count1 + 1;
					System.out.println("allHardSkillsSurveyCounts put " + hskill + " " + count2);
					allHardSkillsSurveyCounts.put(hskill, count2);
				}
			}
			
			
			/*
			 * Soft skills
			 */
			if (WebUtils.isNotEmpty(sv.getSoftskill1())) {
				String sskill = sv.getSoftskill1();
				int ssc = sv.getScores1();
				if (ssc > 0) {
					int score1 = allSoftSkillsSurveyScores.get(sskill);
					int score2 = score1 + ssc;
					allSoftSkillsSurveyScores.put(sskill, score2);
					int count1 = allSoftSkillsSurveyCounts.get(sskill);
					int count2 = count1 + 1;
					System.out.println("allSoftSkillsSurveyCounts put " + sskill + " " + count2);
					allSoftSkillsSurveyCounts.put(sskill, count2);
				}
			}
			if (WebUtils.isNotEmpty(sv.getSoftskill2())) {
				String sskill = sv.getSoftskill2();
				int ssc = sv.getScores2();
				if (ssc > 0) {
					int score1 = allSoftSkillsSurveyScores.get(sskill);
					int score2 = score1 + ssc;
					allSoftSkillsSurveyScores.put(sskill, score2);
					int count1 = allSoftSkillsSurveyCounts.get(sskill);
					int count2 = count1 + 1;
					System.out.println("allSoftSkillsSurveyCounts put " + sskill + " " + count2);
					allSoftSkillsSurveyCounts.put(sskill, count2);
				}
			}
			if (WebUtils.isNotEmpty(sv.getSoftskill3())) {
				String sskill = sv.getSoftskill3();
				int ssc = sv.getScores3();
				if (ssc > 0) {
					int score1 = allSoftSkillsSurveyScores.get(sskill);
					int score2 = score1 + ssc;
					allSoftSkillsSurveyScores.put(sskill, score2);
					int count1 = allSoftSkillsSurveyCounts.get(sskill);
					int count2 = count1 + 1;
					System.out.println("allSoftSkillsSurveyCounts put " + sskill + " " + count2);
					allSoftSkillsSurveyCounts.put(sskill, count2);
				}
			}
			if (WebUtils.isNotEmpty(sv.getSoftskill4())) {
				String sskill = sv.getSoftskill4();
				int ssc = sv.getScores4();
				if (ssc > 0) {
					int score1 = allSoftSkillsSurveyScores.get(sskill);
					int score2 = score1 + ssc;
					allSoftSkillsSurveyScores.put(sskill, score2);
					int count1 = allSoftSkillsSurveyCounts.get(sskill);
					int count2 = count1 + 1;
					System.out.println("allSoftSkillsSurveyCounts put " + sskill + " " + count2);
					allSoftSkillsSurveyCounts.put(sskill, count2);
				}
			}
			if (WebUtils.isNotEmpty(sv.getSoftskill5())) {
				String sskill = sv.getSoftskill5();
				int ssc = sv.getScores5();
				if (ssc > 0) {
					int score1 = allSoftSkillsSurveyScores.get(sskill);
					int score2 = score1 + ssc;
					allSoftSkillsSurveyScores.put(sskill, score2);
					int count1 = allSoftSkillsSurveyCounts.get(sskill);
					int count2 = count1 + 1;
					System.out.println("allSoftSkillsSurveyCounts put " + sskill + " " + count2);
					allSoftSkillsSurveyCounts.put(sskill, count2);
				}
			}
			
		}
		
		/*
		 * Create an object to save the scores.
		 * This has already been created I just need to update it.
		 */
		//Scores scores = new Scores();
		//scores.setTwid(Integer.parseInt(tw));
		//int scoreId =
		//System.out.println(scoreId);
		//scores.setScoreId(Integer.parseInt(tw));
		//if (DataService.selectOne(scores) == null) {
			//DataService.create(scores);
		//}
		
		Scores scores = new Scores();
		scores.setTwid(Integer.parseInt(tw));


		
		/*
		 * Get the map values.
		 */
		List<Integer> hssScores = new ArrayList<Integer>();
		for (Map.Entry<String,Integer> entry : allHardSkillsSurveyScores.entrySet()) { 
            System.out.println("allHardSkillsSurveyScores.key = " + entry.getKey() + ", allHardSkillsSurveyScores.val = " + entry.getValue());
            hssScores.add(entry.getValue());
		}
		List<Integer> hssCounts = new ArrayList<Integer>();
		for (Map.Entry<String,Integer> entry : allHardSkillsSurveyCounts.entrySet()) {
			System.out.println("allHardSkillsSurveyCounts.key = " + entry.getKey() + ", allHardSkillsSurveyCounts.val = " + entry.getValue());
			hssCounts.add(entry.getValue());
		}
		List<Integer> sssScores = new ArrayList<Integer>();
		for (Map.Entry<String,Integer> entry : allSoftSkillsSurveyScores.entrySet()) { 
            System.out.println("allSoftSkillsSurveyScores.key = " + entry.getKey() + ", allSoftSkillsSurveyScores.val = " + entry.getValue());
            sssScores.add(entry.getValue());
		}
		
		/*
		 * Set the skills for the scores object.
		 */
		scores.setSoftskill1(allSoftSkills.get(0));
		scores.setSoftskill2(allSoftSkills.get(1));
		scores.setSoftskill3(allSoftSkills.get(2));
		scores.setSoftskill4(allSoftSkills.get(3));
		scores.setSoftskill5(allSoftSkills.get(4));
		scores.setSoftskill6(allSoftSkills.get(5));
		scores.setSoftskill7(allSoftSkills.get(6));
		scores.setSoftskill8(allSoftSkills.get(7));
		scores.setSoftskill9(allSoftSkills.get(8));
		scores.setSoftskill10(allSoftSkills.get(9));
		
		int hsIndex = 0;
		if (hsIndex < allHardSkills.size()) {
			scores.setHardskill1(allHardSkills.get(hsIndex));
			hsIndex++;
		}
		if (hsIndex < allHardSkills.size()) {
			scores.setHardskill2(allHardSkills.get(hsIndex));
			hsIndex++;
		}
		if (hsIndex < allHardSkills.size()) {
			scores.setHardskill3(allHardSkills.get(hsIndex));
			hsIndex++;
		}
		if (hsIndex < allHardSkills.size()) {
			scores.setHardskill4(allHardSkills.get(hsIndex));
			hsIndex++;
		}
		if (hsIndex < allHardSkills.size()) {
			scores.setHardskill5(allHardSkills.get(hsIndex));
			hsIndex++;
		}
		if (hsIndex < allHardSkills.size()) {
			scores.setHardskill6(allHardSkills.get(hsIndex));
			hsIndex++;
		}
		if (hsIndex < allHardSkills.size()) {
			scores.setHardskill7(allHardSkills.get(hsIndex));
			hsIndex++;
		}
		if (hsIndex < allHardSkills.size()) {
			scores.setHardskill8(allHardSkills.get(hsIndex));
			hsIndex++;
		}
		if (hsIndex < allHardSkills.size()) {
			scores.setHardskill9(allHardSkills.get(hsIndex));
			hsIndex++;
		}
		if (hsIndex < allHardSkills.size()) {
			scores.setHardskill10(allHardSkills.get(hsIndex));
		}
		
		/*
		 * Set the scores for the skills in
		 * the scores object.
		 */
		scores.setScores1(allSoftSkillsSurveyScores.get(allSoftSkills.get(0)));
		scores.setScores2(allSoftSkillsSurveyScores.get(allSoftSkills.get(1)));
		scores.setScores3(allSoftSkillsSurveyScores.get(allSoftSkills.get(2)));
		scores.setScores4(allSoftSkillsSurveyScores.get(allSoftSkills.get(3)));
		scores.setScores5(allSoftSkillsSurveyScores.get(allSoftSkills.get(4)));
		scores.setScores6(allSoftSkillsSurveyScores.get(allSoftSkills.get(5)));
		scores.setScores7(allSoftSkillsSurveyScores.get(allSoftSkills.get(6)));
		scores.setScores8(allSoftSkillsSurveyScores.get(allSoftSkills.get(7)));
		scores.setScores9(allSoftSkillsSurveyScores.get(allSoftSkills.get(8)));
		scores.setScores10(allSoftSkillsSurveyScores.get(allSoftSkills.get(9)));
		
		int hssIndex = 0;
		if (hssIndex < allHardSkills.size()) {
			scores.setScoreh1(allHardSkillsSurveyScores.get(allHardSkills.get(hssIndex)));
			hssIndex++;
		}
		if (hssIndex < allHardSkills.size()) {
			scores.setScoreh2(allHardSkillsSurveyScores.get(allHardSkills.get(hssIndex)));
			hssIndex++;
		}
		if (hssIndex < allHardSkills.size()) {
			scores.setScoreh3(allHardSkillsSurveyScores.get(allHardSkills.get(hssIndex)));
			hssIndex++;
		}
		if (hssIndex < allHardSkills.size()) {
			scores.setScoreh4(allHardSkillsSurveyScores.get(allHardSkills.get(hssIndex)));
			hssIndex++;
		}
		if (hssIndex < allHardSkills.size()) {
			scores.setScoreh5(allHardSkillsSurveyScores.get(allHardSkills.get(hssIndex)));
			hssIndex++;
		}
		if (hssIndex < allHardSkills.size()) {
			scores.setScoreh6(allHardSkillsSurveyScores.get(allHardSkills.get(hssIndex)));
			hssIndex++;
		}
		if (hssIndex < allHardSkills.size()) {
			scores.setScoreh7(allHardSkillsSurveyScores.get(allHardSkills.get(hssIndex)));
			hssIndex++;
		}
		if (hssIndex < allHardSkills.size()) {
			scores.setScoreh8(allHardSkillsSurveyScores.get(allHardSkills.get(hssIndex)));
			hssIndex++;
		}
		if (hssIndex < allHardSkills.size()) {
			scores.setScoreh9(allHardSkillsSurveyScores.get(allHardSkills.get(hssIndex)));
			hssIndex++;
		}
		if (hssIndex < allHardSkills.size()) {
			scores.setScoreh10(allHardSkillsSurveyScores.get(allHardSkills.get(hssIndex)));
		}
		
		
		/*
		 * Set the counts for the skills in
		 * the scores object.
		 */
		scores.setCounts1(allSoftSkillsSurveyCounts.get(allSoftSkills.get(0)));
		scores.setCounts2(allSoftSkillsSurveyCounts.get(allSoftSkills.get(1)));
		scores.setCounts3(allSoftSkillsSurveyCounts.get(allSoftSkills.get(2)));
		scores.setCounts4(allSoftSkillsSurveyCounts.get(allSoftSkills.get(3)));
		scores.setCounts5(allSoftSkillsSurveyCounts.get(allSoftSkills.get(4)));
		scores.setCounts6(allSoftSkillsSurveyCounts.get(allSoftSkills.get(5)));
		scores.setCounts7(allSoftSkillsSurveyCounts.get(allSoftSkills.get(6)));
		scores.setCounts8(allSoftSkillsSurveyCounts.get(allSoftSkills.get(7)));
		scores.setCounts9(allSoftSkillsSurveyCounts.get(allSoftSkills.get(8)));
		scores.setCounts10(allSoftSkillsSurveyCounts.get(allSoftSkills.get(9)));
		
		
		int hscIndex = 0;
		if (hscIndex < allHardSkills.size()) {
			scores.setCounth1(allHardSkillsSurveyCounts.get(allHardSkills.get(hscIndex)));
			hscIndex++;
		}
		if (hscIndex < allHardSkills.size()) {
			scores.setCounth2(allHardSkillsSurveyCounts.get(allHardSkills.get(hscIndex)));
			hscIndex++;
		}
		if (hscIndex < allHardSkills.size()) {
			scores.setCounth3(allHardSkillsSurveyCounts.get(allHardSkills.get(hscIndex)));
			hscIndex++;
		}
		if (hscIndex < allHardSkills.size()) {
			scores.setCounth4(allHardSkillsSurveyCounts.get(allHardSkills.get(hscIndex)));
			hscIndex++;
		}
		if (hscIndex < allHardSkills.size()) {
			scores.setCounth5(allHardSkillsSurveyCounts.get(allHardSkills.get(hscIndex)));
			hscIndex++;
		}
		if (hscIndex < allHardSkills.size()) {
			scores.setCounth6(allHardSkillsSurveyCounts.get(allHardSkills.get(hscIndex)));
			hscIndex++;
		}
		if (hscIndex < allHardSkills.size()) {
			scores.setCounth7(allHardSkillsSurveyCounts.get(allHardSkills.get(hscIndex)));
			hscIndex++;
		}
		if (hscIndex < allHardSkills.size()) {
			scores.setCounth8(allHardSkillsSurveyCounts.get(allHardSkills.get(hscIndex)));
			hscIndex++;
		}
		if (hscIndex < allHardSkills.size()) {
			scores.setCounth9(allHardSkillsSurveyCounts.get(allHardSkills.get(hscIndex)));
			hscIndex++;
		}
		if (hscIndex < allHardSkills.size()) {
			scores.setCounth10(allHardSkillsSurveyCounts.get(allHardSkills.get(hscIndex)));
		}
		
		//Survey sv1 = new Survey();
		//sv1.setSurveyId(Integer.parseInt(sid));
		//Survey s2 = (Survey) DataService.selectOne(sv1);
		
		Scores scores2 = new Scores();
		//scores2.setScoreId(Integer.parseInt(tw));
		scores2.setTwid(Integer.parseInt(tw));
		Scores scores3 = new Scores(scores);
		DataService.update(scores3, scores2);
		
		
		List<Integer> sssCounts = new ArrayList<Integer>();
		for (Map.Entry<String,Integer> entry : allSoftSkillsSurveyCounts.entrySet()) {
			System.out.println("allSoftSkillsSurveyCounts.key = " + entry.getKey() + ", allSoftSkillsSurveyCounts.val = " + entry.getValue());
			sssCounts.add(entry.getValue());
		}
		
		
		
		/*
		 * Calculate the aggregate scores.
		 */
		List<Double> hssAggregates = new ArrayList<Double>();
		for (int i = 0; i < hssScores.size(); i++) {
			int score = hssScores.get(i);
			int count = hssCounts.get(i);
			double agg = 0.0;
			if (count != 0) {
				agg = ((double) score)/count;
			}
			hssAggregates.add(agg);
		}
		List<Double> sssAggregates = new ArrayList<Double>();
		for (int i = 0; i < sssScores.size(); i++) {
			int score = sssScores.get(i);
			int count = sssCounts.get(i);
			double agg = 0.0;
			if (count != 0) {
				agg = ((double) score)/count;
			}
			sssAggregates.add(agg);
		}
		
		
		/*
		 * For each skill, put an aggregate score,
		 * calculated just immediately above into the
		 * Map.
		 */
		int hsaIndex = 0;
	    for (String hs : allHardSkills) {
	    	Double db = hssAggregates.get(hsaIndex);
	    	allHardSkillsAggregateScores.put(hs, db);
	    	hsaIndex++;
	    }
	    int ssaIndex = 0;
	    for (String ss : allSoftSkills) {
	    	Double db = sssAggregates.get(ssaIndex);
	    	allSoftSkillsAggregateScores.put(ss, db);
	    	ssaIndex++;
	    }
		
		/*
		 * Print the aggreate score maps to the console.
		 */
		
		for (Map.Entry<String, Double> entry : allSoftSkillsAggregateScores.entrySet()) {
		    System.out.println("allSoftSkillsAggregateScores.key = " + entry.getKey() + ", allSoftSkillsAggregateScores.val = " + entry.getValue());
		}
		for (Map.Entry<String,Double> entry : allHardSkillsAggregateScores.entrySet()) { 
		    System.out.println("allHardSkillsAggregateScores.key = " + entry.getKey() + ", allHardSkillsAggregateScores.val = " + entry.getValue());
		}
		
		/*
		 * Process selected scores.
		 */
		//for (String selected : selectedHardSkills) {
			//if (allHardSkillsAggregateScores.containsKey(selected)) {
				//selectableScores.add(allHardSkillsAggregateScores.get(selectable));
			//}
		//}
		
		/*
		 * Process selectable hard skills.
		 *
		List<Double> selectableScores = new ArrayList<Double>();
		for (String selectable : selectableHardSkills) {
			if (allHardSkillsAggregateScores.containsKey(selectable)) {
				selectableScores.add(allHardSkillsAggregateScores.get(selectable));
			}
		}
		Collections.sort(selectableScores);
		Collections.reverse(selectableScores);
		for (Double scr : selectableScores) {
			System.out.println("sorted list = " + scr);
		}
		*/
		
		
		
		
		
		
		/*
		 * Update the ScoreService field maps.
		 */
		ScoreService ss1 = new ScoreService();
		ss1.setHardSkillSurveyAggScores(allHardSkillsAggregateScores);
		ss1.setSoftSkillSurveyAggScores(allSoftSkillsAggregateScores);
		ss1.setHardSkillSurveyScores(allHardSkillsSurveyScores);
		ss1.setSoftSkillSurveyScores(allSoftSkillsSurveyScores);
		ss1.setHardSkillSurveyCouunts(allHardSkillsSurveyCounts);
		ss1.setSoftSkillSurveyCounts(allSoftSkillsSurveyCounts);
		
		/*
		 * Save the scores to the appropriate database 
		 * records.
		 */
		
		List<String> remainingHardSkills = new ArrayList<String>();
		

		
		
		String target = "/thankyou.jsp";
		
		RequestDispatcher rd = request.getRequestDispatcher(target);
		try {
			rd.forward(request, response);
		}
		catch (Exception e) {
			System.out.println("Error dispatching to target = " + target);
		}
		
	}

}

