package com.teamwrkr.app.manager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.teamwrkr.app.dto.*;
import com.teamwrkr.app.dto.Scores;
import com.teamwrkr.app.service.DataService;
import com.teamwrkr.app.util.SiteConstants;


public class OpportunityManager {
	
	public OpportunityManager() { }
	
	
	

	/**
	 * Returns an alphabetical list of all user missions in the entire TW
	 * system at the time of query.
	 * 
	 * While this will be useful initially, it will likely become obsolete
	 * as the number of users increases to a certain critical mass.
	 * 
	 * @return
	 */
	public static List<Mission> getAllMissions() {
		
		List<Mission> allMissions = new ArrayList<Mission>();
		
		Mission m = new Mission();
		Object[] cachedMissions = DataService.selectMany(m, " missionId >= 0");

		System.out.println("####################  OPPORTUNITIES BEGIN  ####################");

		int msCt = 1;
		for (Object cachedMission : cachedMissions) {
			Mission missionToDisplay = (Mission) cachedMission;
			allMissions.add(missionToDisplay);
			msCt++;
		}

		System.out.println("####################  OPPORTUNITIES END  ####################");
		
		return allMissions;
		
	}
	
	
	// filter out comptype = 2 (idk)
	// filter out expiredDate < today
	// filter by postedDate not NULL (if NULL, this is a reference, not an opportunity)
	// order by newest to oldest
	
	public static List<Mission> getOpportunities(List<Mission> allOpps) {
		
		SiteConstants sc = new SiteConstants();
		
		Date today = Calendar.getInstance().getTime();
		
		List<Mission> opps = new ArrayList<Mission>();
		
		for (Mission opp : allOpps) {
			
			/*
			 * Selected IDK for compensation type.
			 */
			if (opp.getType() == 2) {continue;}
			/*
			 * Listing expired.
			 */
			if (opp.getExpiresDate() != null && opp.getExpiresDate().before(today)) {continue;}
			/*
			 * Listing is a reference request, not a job posting.
			 * These checks are likely redundant but one can never
			 * be too safe.
			 */
			if (opp.getMissionType() == sc.MISSION_TYPE_WORK_EXP) {continue;}
			if (opp.getPostedDate() == null) {continue;}
			
			opps.add(opp);
			
			
		}
		
		return opps;
		
	}
	
	
	public static List<Mission> getFilteredOpportunities(String filter) {
		
		List<Mission> allMissions = new ArrayList<Mission>();
		
		Mission p = new Mission();
		Object[] cachedMissions = DataService.selectMany(p, " missionId >= 0 AND hardskill1 = '" + filter + "'");

		System.out.println("####################  OPPORTUNITIES BEGIN  ####################");

		int msCt = 1;
		for (Object cachedMission : cachedMissions) {
			Mission missionToDisplay = (Mission) cachedMission;
			allMissions.add(missionToDisplay);
			msCt++;
		}
		

		System.out.println("####################  OPPORTUNITIES END  ####################");
		
		return allMissions;
		
	}
	
	
	/**
	 * Return the Mission closest to the middle of the total list of rolodex
	 * missions for the current user.
	 * 
	 * @param missions
	 * @return
	 */
	public static Mission getInitialOpportunity(List<Mission> missions) {
		
		int index = Math.floorDiv(missions.size(), 2);
		
		return missions.get(index);
		
	}
	
	public static Mission getInitialOpportunity2(List<Mission> missions) {
		
		return missions.get(22);
		
	}
	
	public static int getInitialOpportunityIndex(List<Mission> missions) {
		
		return Math.floorDiv(missions.size(), 2);
		
	}
	
	public static Mission getOpportunity(List<Mission> missions, int n) {
		
		if (n >= missions.size()) {
			return missions.get(missions.size() - 1);
		}
		if (n < 0) {
			return missions.get(0);
		}
		
		return missions.get(n);
		
	}
	
	/*
	 * Grabs the next n (7 for now) opportunities to display on
	 * the Opportunities slider.
	 */
	public static List<Mission> getOpportunityBlock(List<Mission> missions, int n, int size) {
		
		List<Mission> missionsBlock = new ArrayList<Mission>();
		
		if (n >= missions.size() - size) {
			for (int i = size; i > 0; i--) {
				missionsBlock.add(missions.get(missions.size() - i));
			}
			return missionsBlock;
		}
		
		if (n < 0) {
			for (int i = 0; i < size; i++) {
				missionsBlock.add(missions.get(i));
			}
			return missionsBlock;
		}
		
		for (int i = n; i < n + size; i++) {
			missionsBlock.add(missions.get(i));
		}
		
		return missionsBlock;
		
	}
	

}
