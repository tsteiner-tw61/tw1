package com.teamwrkr.app.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.teamwrkr.app.dto.Profile;
import com.teamwrkr.app.dto.Scores;

public class SkillService {
	
	
	public SkillService() { }
	
	
	/**
	 * Select all available soft skills.
	 * 
	 * @return
	 */
	public static List<String> getSoftSkills() {
		
		List<String> softSkills = new ArrayList<String>();
		
		/*
		 * Generate SQL.
		 */
		String query = "SELECT name FROM softskill WHERE category1 IS NULL";
		
		System.out.println(query);
		
		/*
		 * Execute query.
		 */
		Context initCtx;
		try {
			initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource)  envCtx.lookup("jdbc/teamwrkrDB");
			Connection conn = ds.getConnection();
		    Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		    ResultSet rs = stmt.executeQuery(query);
		    while (rs.next()) {
		    	softSkills.add(rs.getString("name"));
		    }
			conn.close();
		} 
		catch (NamingException e1) {
			e1.printStackTrace();
		}
		catch (SQLException e2) {
			e2.printStackTrace();
		}

		return softSkills;
		
	}
	
	
	
	/**
	 * Select hard (measurable) skills, by category mix.
	 * 
	 * @param type
	 * @param category1
	 * @param category2
	 * @return
	 */
	public static List<String> getHardSkills( String category1, String category2) {
		
		List<String> skills = new ArrayList<String>();
		
		/*
		 * Generate SQL.
		 */
		String query = "SELECT name FROM hardskill WHERE ";
		query+= "category1 = '" + category1 + "' AND ";
		query+= "category2 = '" + category2 + "'";
		query+= " ORDER BY name ASC";
		
		System.out.println(query);
		
		/*
		 * Execute query.
		 */
		Context initCtx;
		try {
			initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource)  envCtx.lookup("jdbc/teamwrkrDB");
			Connection conn = ds.getConnection();
		    Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		    ResultSet rs = stmt.executeQuery(query);
		    while (rs.next()) {
		    	skills.add(rs.getString("name"));
		    }
			conn.close();
		} 
		catch (NamingException e1) {
			e1.printStackTrace();
		}
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		
		
		return skills;
		
	}
	
	
	
	/**
	 * Method to select a random skill from a list of skills
	 * that excludes any skill that has already been selected
	 * from the same category.
	 * 
	 * @param selected
	 * @param profile
	 * @param type
	 * @return
	 */
	public static String getRandomSkill(List<String> selectedList, Profile profile, String type) {
		
		String skill = "";
		
		/*
		 * Get the total possible list of skills in
		 * both categories, interpersonal and measurable,
		 * for the given user Profile.
		 */
		List<String> skillList = new ArrayList<String>();
		if (type.equals("hard") ) {
			skillList = SkillService.getHardSkills(profile.getCategory1(), profile.getCategory2());
		}
		if (type.equals("soft") ) {
			skillList = SkillService.getSoftSkills();
		}
		
		/*
		 * Compare the selected lists of skills to the
		 * total list and remove any skills that have
		 * already been chosen by the user when submitting
		 * the reference request.
		 */
		List<String> filteredList = new ArrayList<String>();
		for (String sk : skillList) {
			//System.out.println("skillList=" + sk);
		    if (selectedList.contains(sk)) {
		        continue;
		    }
		    else {
		    	//System.out.println("filteredList.add=" + sk);
		        filteredList.add(sk);
		    }
		}
		
		/*
		 * If the remaining skill list is greater
		 * than one item, then select a random integer
		 * between 1 and the number of items in the
		 * remaining list. If no items remain, then
		 * do nothing.
		 */
		if (filteredList.size() > 1) {
			
			Random ran = new Random();
			int x = ran.nextInt(filteredList.size());
			skill = filteredList.get(x);
			//System.out.println("result = " + skill);
			
		}
		else if (filteredList.size() == 1) {
			skill = filteredList.get(0);
			//System.out.println("filteredList.get(0)=" + skill);
		}
		
		//System.out.println("random skill = " + skill);
		
		return skill;
		
	}

	
	/**
	 * Update the Scores object for the Profile with the 
	 * hardskill names.
	 * 
	 * @param profile
	 */
	public static void populateScoreWithHardskills(Profile profile) {
		
		List<String> skills = SkillService.getHardSkills(profile.getCategory1(), profile.getCategory2());
		
		/*
		 * ----- POPULATE UPDATE DTO -----
		 */
		Scores scores = new Scores();
		scores.setTwid(profile.getTwid());
		int index = 0;
		for (String skill : skills) {
			if (index == 0) {scores.setHardskill1(skill);}
			if (index == 1) {scores.setHardskill2(skill);}
			if (index == 2) {scores.setHardskill3(skill);}
			if (index == 3) {scores.setHardskill4(skill);}
			if (index == 4) {scores.setHardskill5(skill);}
			if (index == 5) {scores.setHardskill6(skill);}
			if (index == 6) {scores.setHardskill7(skill);}
			if (index == 7) {scores.setHardskill8(skill);}
			if (index == 8) {scores.setHardskill9(skill);}
			if (index == 9) {scores.setHardskill10(skill);}
			index++;
		}
		Scores scoresToUpdate = new Scores(scores);
		
		/*
		 * ----- CREATE DUMMY DTO -----
		 */
		Scores scoresObject = new Scores();
		scoresObject.setTwid(profile.getTwid());
		
		/*
		 * ----- EXECUTE UPDATE QUERY -----
		 */
		DataService.update(scoresToUpdate, scoresObject);
		
	}
	
	

}

