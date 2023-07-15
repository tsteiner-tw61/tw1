package com.teamwrkr.app.manager;

import java.util.ArrayList;
import java.util.List;

import com.teamwrkr.app.dto.Profile;
import com.teamwrkr.app.dto.Scores;
import com.teamwrkr.app.service.DataService;


public class RolodexManager {
	
	public RolodexManager() { }
	
	
	
	/**
	 * Returns an alphabetical list of all user profiles in the entire TW
	 * system at the time of query.
	 * 
	 * While this will be useful initially, it will likely become obsolete
	 * as the number of users increases to a certain critical mass.
	 * 
	 * @return
	 */
	public static List<Profile> getAllRolodexProfiles() {
		
		List<Profile> allProfiles = new ArrayList<Profile>();
		
		Profile p = new Profile();
		Object[] cachedProfiles = DataService.selectMany(p, " twid >= 0 AND photoImg IS NOT NULL ORDER BY lastName ASC, firstName ASC");

		System.out.println("####################  ROLODEX USERS BEGIN  ####################");

		int pfCt = 1;
		for (Object cachedProfile : cachedProfiles) {
			
			
			
			Profile profileToDisplay = (Profile) cachedProfile;
			
			Scores s1 = new Scores();
			s1.setTwid(profileToDisplay.getTwid());
			
			//Scores s2 = (Scores) DataService.selectOne(s1, 1);
			Scores s2 = (Scores) DataService.selectOne(s1);
			if (s2 == null) {continue;}
			
			String profileLn = profileToDisplay.getLastName();
			String profileFn = profileToDisplay.getFirstName();
			String profileEmail = profileToDisplay.getEmail();
			
			allProfiles.add(profileToDisplay);
			
			System.out.println(pfCt + ": " + profileLn + ", " + profileFn + " - " + profileEmail);
			
			pfCt++;
			
		}
		

		System.out.println("####################  ROLODEX USERS END  ####################");
		
		return allProfiles;
		
	}
	
	
	public static List<Profile> getFilteredRolodexProfiles(String filter) {
		
		List<Profile> allProfiles = new ArrayList<Profile>();
		
		Profile p = new Profile();
		Object[] cachedProfiles = DataService.selectMany(p, " twid >= 0 AND category2 = '" + filter + "' ORDER BY lastName ASC, firstName ASC");

		System.out.println("####################  ROLODEX USERS BEGIN  ####################");

		int pfCt = 1;
		for (Object cachedProfile : cachedProfiles) {
			
			
			
			Profile profileToDisplay = (Profile) cachedProfile;
			
			Scores s1 = new Scores();
			s1.setTwid(profileToDisplay.getTwid());
			
			Scores s2 = (Scores) DataService.selectOne(s1);
			if (s2 == null) {continue;}
			
			String profileLn = profileToDisplay.getLastName();
			String profileFn = profileToDisplay.getFirstName();
			String profileEmail = profileToDisplay.getEmail();
			
			allProfiles.add(profileToDisplay);
			
			System.out.println(pfCt + ": " + profileLn + ", " + profileFn + " - " + profileEmail);
			
			pfCt++;
			
		}
		

		System.out.println("####################  ROLODEX USERS END  ####################");
		
		return allProfiles;
		
	}
	
	
	/**
	 * Return the Profile closest to the middle of the total list of rolodex
	 * profiles for the current user.
	 * 
	 * @param profiles
	 * @return
	 */
	public static Profile getInitialRolodexProfile(List<Profile> profiles) {
		
		int index = Math.floorDiv(profiles.size(), 2);
		
		return profiles.get(index);
		
	}
	
	public static Profile getInitialRolodexProfile2(List<Profile> profiles) {
		
		return profiles.get(22);
		
	}
	
	public static int getInitialRolodexIndex(List<Profile> profiles) {
		
		return Math.floorDiv(profiles.size(), 2);
		
	}
	
	public static Profile getRolodexProfile(List<Profile> profiles, int n) {
		
		if (n >= profiles.size()) {
			return profiles.get(profiles.size() - 1);
		}
		if (n < 0) {
			return profiles.get(0);
		}
		
		return profiles.get(n);
		
	}
	
	
	
	

}
