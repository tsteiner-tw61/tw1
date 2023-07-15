package com.teamwrkr.app.manager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import com.teamwrkr.app.dto.Mission;
import com.teamwrkr.app.dto.Notification;
import com.teamwrkr.app.dto.Profile;
import com.teamwrkr.app.dto.Scores;
import com.teamwrkr.app.service.DataService;
import com.teamwrkr.app.service.ScoreService;
import com.teamwrkr.app.util.SiteConstants;
import com.teamwrkr.app.util.SqlUtils;
import com.teamwrkr.app.util.WebUtils;

/**
 * This class contains all functions related to 
 * the Intelligent Job Matching system.
 * 
 * @author 	Ted E. Steiner
 * @date	06.29.2023
 *
 */
public class IntelligentJobMatchManager {
	
	
	public IntelligentJobMatchManager() { }
	
	
	
	public static List<Profile> match(int noToMatch, int jobId) {
		
		List<Profile> matchedProfiles = new ArrayList<Profile>();
		List<Profile> cachedProfiles = new ArrayList<Profile>();
		List<Scores> cachedScores = new ArrayList<Scores>();
		
		Mission mission = new Mission();
		mission.setMissionId(jobId);
		Mission job = (Mission) DataService.selectOne(mission);
		
		
		/*
		 * Get qualifying information.
		 */
		Date start = job.getStartDate();
		int rate = job.getRate();
		
		Profile p = new Profile();
		String qs1 = "";
				
		/*
		 * Main category search.
		 */
		String cat1 = job.getCategory1();
		qs1 = "category1 = '" + cat1 + "'";
		Object[] result1 = DataService.selectMany(p, qs1);
		if (result1.length == noToMatch) {
			for (Object result : result1) {
				Profile p1 = (Profile) result;
				matchedProfiles.add(p1);
			}
			return matchedProfiles;
		}
		if (result1.length < noToMatch) {
			// alternate algorithm
		}
		
		/*
		 * Subcategory search.
		 */
		String cat2 = job.getCategory2();
		qs1 += " AND category2 = '" + cat2 + "'";
		Object[] result2 = DataService.selectMany(p, qs1);
		if (result2.length == noToMatch) {
			for (Object result : result2) {
				Profile p2 = (Profile) result;
				matchedProfiles.add(p2);
			}
			return matchedProfiles;
		}
		if (result2.length < noToMatch) {
			// alternate algorithm
		}
		
		/*
		 * If an abundance of matches still exists...
		 */
		String ms1 = job.getHardskill1();
		String ms2 = job.getHardskill2();
		String ms3 = job.getHardskill3();
		String ips1 = job.getSoftskill1();
		String ips2 = job.getSoftskill2();
		String ips3 = job.getSoftskill3();
		
		Map<String, Integer> rankingMap = new HashMap<String, Integer>();
		
		for (Object result : result2) {
			
			Profile p2 = (Profile) result;
			int twid = p2.getTwid();
			
			Scores sc = new Scores();
			sc.setTwid(twid);
			Scores sc2 = (Scores) DataService.selectOne(sc);
			
			int rankTotalPts = 0;
			boolean exactMatch1st = false;
			boolean exactMatch2nd = false;
			boolean exactMatch3rd = false;
			boolean top3Match1st = false;
			boolean top3Match2nd = false;
			boolean top3Match3rd = false;
			
			Map<String, Integer> orderedMeasurableScores = ScoreService.getHardSkillScoresMapDesc(sc2);
			int mspos = 1;
			for (Map.Entry<String,Integer> entry : orderedMeasurableScores.entrySet()) { 
	            System.out.println(twid + "--" + entry.getKey() + "--" + entry.getValue());
	            
	            /*
	             * Measurable skill rank matching.
	             */
	            if (mspos == 1) {
	            	if (ms1.equals(entry.getKey())) {
	            		rankTotalPts = rankTotalPts + 30;
	            		exactMatch1st = true;
	            	}
	            	if (ms2.equals(entry.getKey())) {
	            		rankTotalPts = rankTotalPts + 27;
	            		top3Match1st = true;
	            	}
	            	if (ms3.equals(entry.getKey())) {
	            		rankTotalPts = rankTotalPts + 24;
	            		top3Match1st = true;
	            	}
	            }
	            if (mspos == 2) {
	            	if (ms1.equals(entry.getKey())) {
	            		rankTotalPts = rankTotalPts + 22;
	            		top3Match2nd = true;
	            	}
	            	if (ms2.equals(entry.getKey())) {
	            		rankTotalPts = rankTotalPts + 20;
	            		exactMatch2nd = true;
	            	}
	            	if (ms3.equals(entry.getKey())) {
	            		rankTotalPts = rankTotalPts + 18;
	            		top3Match2nd = true;
	            	}
	            }
	            if (mspos == 3) {
	            	if (ms1.equals(entry.getKey())) {
	            		rankTotalPts = rankTotalPts + 12;
	            		top3Match3rd = true;
	            	}
	            	if (ms2.equals(entry.getKey())) {
	            		rankTotalPts = rankTotalPts + 11;
	            		top3Match3rd = true;
	            	}
	            	if (ms3.equals(entry.getKey())) {
	            		rankTotalPts = rankTotalPts + 10;
	            		exactMatch3rd = true;
	            	}
	            }
	            
	            mspos++;
			}
			
			/*
			 * Evaluate match bonuses.
			 */
			boolean perfectMatch = false;
			boolean top3Match = false;
			boolean fuzzyMatch = false;
			if (exactMatch1st && exactMatch2nd && exactMatch3rd) {
				perfectMatch = true;
			}
			else if ((exactMatch1st || top3Match1st) &&
					(exactMatch2nd || top3Match2nd) &&
					(exactMatch3rd || top3Match3rd)) {
				top3Match = true;
			}
			else if (((exactMatch1st || top3Match1st) && (exactMatch2nd || top3Match2nd)) ||
					((exactMatch1st || top3Match1st) && (exactMatch3rd || top3Match3rd)) || 
					((exactMatch2nd || top3Match2nd) && (exactMatch3rd || top3Match3rd))) {
				fuzzyMatch = true;
			}
			if (perfectMatch) {rankTotalPts = rankTotalPts + 20;}
			if (top3Match) {rankTotalPts = rankTotalPts + 10;}
			if (fuzzyMatch) {rankTotalPts = rankTotalPts + 5;}
			
			
			boolean exactMatchB1st = false;
			boolean exactMatchB2nd = false;
			boolean exactMatchB3rd = false;
			boolean top3MatchB1st = false;
			boolean top3MatchB2nd = false;
			boolean top3MatchB3rd = false;
			
			
			int ipspos = 1;
			Map<String, Integer> orderedInterpersonalScores = ScoreService.getSoftSkillScoresMapDesc(sc2);
			for (Map.Entry<String,Integer> entry : orderedInterpersonalScores.entrySet()) { 
	            System.out.println(twid + "--" + entry.getKey() + "--" + entry.getValue());
	            
	            /*
	             * Interpersonal skill rank matching.
	             */
	            if (ipspos == 1) {
	            	if (ips1.equals(entry.getKey())) {
	            		rankTotalPts = rankTotalPts + 30;
	            		exactMatchB1st = true;
	            	}
	            	if (ips2.equals(entry.getKey())) {
	            		rankTotalPts = rankTotalPts + 27;
	            		top3MatchB1st = true;
	            	}
	            	if (ips3.equals(entry.getKey())) {
	            		rankTotalPts = rankTotalPts + 24;
	            		top3MatchB1st = true;
	            	}
	            }
	            if (ipspos == 2) {
	            	if (ips1.equals(entry.getKey())) {
	            		rankTotalPts = rankTotalPts + 22;
	            		top3MatchB2nd = true;
	            	}
	            	if (ips2.equals(entry.getKey())) {
	            		rankTotalPts = rankTotalPts + 20;
	            		exactMatchB2nd = true;
	            	}
	            	if (ips3.equals(entry.getKey())) {
	            		rankTotalPts = rankTotalPts + 18;
	            		top3MatchB2nd = true;
	            	}
	            }
	            if (ipspos == 3) {
	            	if (ips1.equals(entry.getKey())) {
	            		rankTotalPts = rankTotalPts + 12;
	            		top3MatchB3rd = true;
	            	}
	            	if (ips2.equals(entry.getKey())) {
	            		rankTotalPts = rankTotalPts + 11;
	            		top3MatchB3rd = true;
	            	}
	            	if (ips3.equals(entry.getKey())) {
	            		rankTotalPts = rankTotalPts + 10;
	            		exactMatchB3rd = true;
	            	}
	            }

	            ipspos++;
	            
			}
			
			/*
			 * Evaluate match bonuses.
			 */
			boolean perfectMatchB = false;
			boolean top3MatchB = false;
			boolean fuzzyMatchB = false;
			if (exactMatchB1st && exactMatchB2nd && exactMatchB3rd) {
				perfectMatchB = true;
			}
			else if ((exactMatchB1st || top3MatchB1st) &&
					(exactMatchB2nd || top3MatchB2nd) &&
					(exactMatchB3rd || top3MatchB3rd)) {
				top3MatchB = true;
			}
			else if (((exactMatchB1st || top3MatchB1st) && (exactMatchB2nd || top3MatchB2nd)) ||
					((exactMatchB1st || top3MatchB1st) && (exactMatchB3rd || top3MatchB3rd)) || 
					((exactMatchB2nd || top3MatchB2nd) && (exactMatchB3rd || top3MatchB3rd))) {
				fuzzyMatchB = true;
			}
			if (perfectMatchB) {rankTotalPts = rankTotalPts + 20;}
			if (top3MatchB) {rankTotalPts = rankTotalPts + 10;}
			if (fuzzyMatchB) {rankTotalPts = rankTotalPts + 5;}
			
			rankingMap.put(Integer.toString(twid), rankTotalPts);
			cachedScores.add(sc2);
			
		}
		
		
		/*
		 * Display the ranking map to the console.
		 */
		Map<String, Integer> outputRankMap = WebUtils.sortByValueDesc(rankingMap);
		int rank = 1;
		for (Map.Entry<String,Integer> entry : outputRankMap.entrySet()) { 
            System.out.println(rank + "--" + entry.getKey() + "--" + entry.getValue());    
            if (rank <= noToMatch) {
            	Profile pRank = new Profile();
            	pRank.setTwid(Integer.parseInt(entry.getKey()));
            	Profile pMatch = (Profile) DataService.selectOne(pRank);
            	matchedProfiles.add(pMatch);
            }
            rank++;
        }
		
		
		String qs2 = "";
		
		if (matchedProfiles.size() > 0) {
			//Mission msCurrentRecord = new Mission();
			//msCurrentRecord.setMissionId(jobId);
			//Mission msFieldUpdates = new Mission();
			//msFieldUpdates.setMatchedDate(Calendar.getInstance().getTime());
			//DataService.update(msFieldUpdates, msCurrentRecord);
			
			
			
			/*
			 * Used by DataService.update to selectOne and
			 * get the current values of the record as stored
			 * in the database.
			 */
			Mission reference = new Mission();
			reference.setMissionId(jobId);

			/*
			 * A second local object which contains all of the
			 * current values of the record as stored in the
			 * database.
			 */
			Mission update = (Mission) DataService.selectOne(reference);

			/*
			 * Now, update only the object fields that you wish
			 * to update, leaving all other fields the same.
			 */
			update.setMatchedDate(Calendar.getInstance().getTime());

			/*
			 * Create an updated object.
			 */
			Mission update2 = new Mission(update);
			/*
			 * Execute the update using update
			 */
			DataService.update(update2, reference);

			/*
			 * Be sure to get a local object copy that 
			 * reflects the updated values resulting from the
			 * previous update statement above.
			 */
			Mission updated = (Mission) DataService.selectOne(reference);

						


			
			
		}
		
		notifyMatchedProfiles(matchedProfiles, job);
		
		
		/*
		 * Revise search if too few candidates
		 * result from the original search
		 * algorithm.
		 * 
		 * 1. most total TW points
		 * 2. most successfully completed jobs
		 * 3. highest survey average overall score out of 5.0
		 */
		
		
		
		return matchedProfiles;
		
	}
	
	
	
	
	public static List<Profile> match(int noToMatch, int ordinal, int jobId) {
		
		List<Profile> matchedProfiles = new ArrayList<Profile>();
		List<Profile> cachedProfiles = new ArrayList<Profile>();
		List<Scores> cachedScores = new ArrayList<Scores>();
		
		Mission mission = new Mission();
		mission.setMissionId(jobId);
		Mission job = (Mission) DataService.selectOne(mission);
		
		
		/*
		 * Get qualifying information.
		 */
		Date start = job.getStartDate();
		int rate = job.getRate();
		
		Profile p = new Profile();
		String qs1 = "";
				
		/*
		 * Main category search.
		 */
		String cat1 = job.getCategory1();
		qs1 = "category1 = '" + cat1 + "'";
		Object[] result1 = DataService.selectMany(p, qs1);
		
		int r1Count = 1;
		if (result1.length == noToMatch) {
			for (Object result : result1) {
				Profile p1 = (Profile) result;
				if (r1Count == ordinal) {
					matchedProfiles.add(p1);
				}
				r1Count++;
			}
			return matchedProfiles;
		}
		
		
		if (result1.length < noToMatch) {
			// alternate algorithm
		}
		
		/*
		 * Subcategory search.
		 */
		String cat2 = job.getCategory2();
		qs1 += " AND category2 = '" + cat2 + "'";
		Object[] result2 = DataService.selectMany(p, qs1);
		
		
		int r2Count = 1;
		if (result2.length == noToMatch) {
			for (Object result : result2) {
				Profile p2 = (Profile) result;
				if (r2Count == ordinal) {
					matchedProfiles.add(p2);
				}
				r2Count++;
			}
			return matchedProfiles;
		}
		
		
		
		
		if (result2.length < noToMatch) {
			// alternate algorithm
		}
		
		/*
		 * If an abundance of matches still exists...
		 */
		String ms1 = job.getHardskill1();
		String ms2 = job.getHardskill2();
		String ms3 = job.getHardskill3();
		String ips1 = job.getSoftskill1();
		String ips2 = job.getSoftskill2();
		String ips3 = job.getSoftskill3();
		
		Map<String, Integer> rankingMap = new HashMap<String, Integer>();
		
		for (Object result : result2) {
			
			Profile p2 = (Profile) result;
			int twid = p2.getTwid();
			
			Scores sc = new Scores();
			sc.setTwid(twid);
			Scores sc2 = (Scores) DataService.selectOne(sc);
			
			int rankTotalPts = 0;
			boolean exactMatch1st = false;
			boolean exactMatch2nd = false;
			boolean exactMatch3rd = false;
			boolean top3Match1st = false;
			boolean top3Match2nd = false;
			boolean top3Match3rd = false;
			
			Map<String, Integer> orderedMeasurableScores = ScoreService.getHardSkillScoresMapDesc(sc2);
			int mspos = 1;
			for (Map.Entry<String,Integer> entry : orderedMeasurableScores.entrySet()) { 
	            System.out.println(twid + "--" + entry.getKey() + "--" + entry.getValue());
	            
	            /*
	             * Measurable skill rank matching.
	             */
	            if (mspos == 1) {
	            	if (ms1.equals(entry.getKey())) {
	            		rankTotalPts = rankTotalPts + 30;
	            		exactMatch1st = true;
	            	}
	            	if (ms2.equals(entry.getKey())) {
	            		rankTotalPts = rankTotalPts + 27;
	            		top3Match1st = true;
	            	}
	            	if (ms3.equals(entry.getKey())) {
	            		rankTotalPts = rankTotalPts + 24;
	            		top3Match1st = true;
	            	}
	            }
	            if (mspos == 2) {
	            	if (ms1.equals(entry.getKey())) {
	            		rankTotalPts = rankTotalPts + 22;
	            		top3Match2nd = true;
	            	}
	            	if (ms2.equals(entry.getKey())) {
	            		rankTotalPts = rankTotalPts + 20;
	            		exactMatch2nd = true;
	            	}
	            	if (ms3.equals(entry.getKey())) {
	            		rankTotalPts = rankTotalPts + 18;
	            		top3Match2nd = true;
	            	}
	            }
	            if (mspos == 3) {
	            	if (ms1.equals(entry.getKey())) {
	            		rankTotalPts = rankTotalPts + 12;
	            		top3Match3rd = true;
	            	}
	            	if (ms2.equals(entry.getKey())) {
	            		rankTotalPts = rankTotalPts + 11;
	            		top3Match3rd = true;
	            	}
	            	if (ms3.equals(entry.getKey())) {
	            		rankTotalPts = rankTotalPts + 10;
	            		exactMatch3rd = true;
	            	}
	            }
	            
	            mspos++;
			}
			
			/*
			 * Evaluate match bonuses.
			 */
			boolean perfectMatch = false;
			boolean top3Match = false;
			boolean fuzzyMatch = false;
			if (exactMatch1st && exactMatch2nd && exactMatch3rd) {
				perfectMatch = true;
			}
			else if ((exactMatch1st || top3Match1st) &&
					(exactMatch2nd || top3Match2nd) &&
					(exactMatch3rd || top3Match3rd)) {
				top3Match = true;
			}
			else if (((exactMatch1st || top3Match1st) && (exactMatch2nd || top3Match2nd)) ||
					((exactMatch1st || top3Match1st) && (exactMatch3rd || top3Match3rd)) || 
					((exactMatch2nd || top3Match2nd) && (exactMatch3rd || top3Match3rd))) {
				fuzzyMatch = true;
			}
			if (perfectMatch) {rankTotalPts = rankTotalPts + 20;}
			if (top3Match) {rankTotalPts = rankTotalPts + 10;}
			if (fuzzyMatch) {rankTotalPts = rankTotalPts + 5;}
			
			
			boolean exactMatchB1st = false;
			boolean exactMatchB2nd = false;
			boolean exactMatchB3rd = false;
			boolean top3MatchB1st = false;
			boolean top3MatchB2nd = false;
			boolean top3MatchB3rd = false;
			
			
			int ipspos = 1;
			Map<String, Integer> orderedInterpersonalScores = ScoreService.getSoftSkillScoresMapDesc(sc2);
			for (Map.Entry<String,Integer> entry : orderedInterpersonalScores.entrySet()) { 
	            System.out.println(twid + "--" + entry.getKey() + "--" + entry.getValue());
	            
	            /*
	             * Interpersonal skill rank matching.
	             */
	            if (ipspos == 1) {
	            	if (ips1.equals(entry.getKey())) {
	            		rankTotalPts = rankTotalPts + 30;
	            		exactMatchB1st = true;
	            	}
	            	if (ips2.equals(entry.getKey())) {
	            		rankTotalPts = rankTotalPts + 27;
	            		top3MatchB1st = true;
	            	}
	            	if (ips3.equals(entry.getKey())) {
	            		rankTotalPts = rankTotalPts + 24;
	            		top3MatchB1st = true;
	            	}
	            }
	            if (ipspos == 2) {
	            	if (ips1.equals(entry.getKey())) {
	            		rankTotalPts = rankTotalPts + 22;
	            		top3MatchB2nd = true;
	            	}
	            	if (ips2.equals(entry.getKey())) {
	            		rankTotalPts = rankTotalPts + 20;
	            		exactMatchB2nd = true;
	            	}
	            	if (ips3.equals(entry.getKey())) {
	            		rankTotalPts = rankTotalPts + 18;
	            		top3MatchB2nd = true;
	            	}
	            }
	            if (ipspos == 3) {
	            	if (ips1.equals(entry.getKey())) {
	            		rankTotalPts = rankTotalPts + 12;
	            		top3MatchB3rd = true;
	            	}
	            	if (ips2.equals(entry.getKey())) {
	            		rankTotalPts = rankTotalPts + 11;
	            		top3MatchB3rd = true;
	            	}
	            	if (ips3.equals(entry.getKey())) {
	            		rankTotalPts = rankTotalPts + 10;
	            		exactMatchB3rd = true;
	            	}
	            }

	            ipspos++;
	            
			}
			
			/*
			 * Evaluate match bonuses.
			 */
			boolean perfectMatchB = false;
			boolean top3MatchB = false;
			boolean fuzzyMatchB = false;
			if (exactMatchB1st && exactMatchB2nd && exactMatchB3rd) {
				perfectMatchB = true;
			}
			else if ((exactMatchB1st || top3MatchB1st) &&
					(exactMatchB2nd || top3MatchB2nd) &&
					(exactMatchB3rd || top3MatchB3rd)) {
				top3MatchB = true;
			}
			else if (((exactMatchB1st || top3MatchB1st) && (exactMatchB2nd || top3MatchB2nd)) ||
					((exactMatchB1st || top3MatchB1st) && (exactMatchB3rd || top3MatchB3rd)) || 
					((exactMatchB2nd || top3MatchB2nd) && (exactMatchB3rd || top3MatchB3rd))) {
				fuzzyMatchB = true;
			}
			if (perfectMatchB) {rankTotalPts = rankTotalPts + 20;}
			if (top3MatchB) {rankTotalPts = rankTotalPts + 10;}
			if (fuzzyMatchB) {rankTotalPts = rankTotalPts + 5;}
			
			rankingMap.put(Integer.toString(twid), rankTotalPts);
			cachedScores.add(sc2);
			
		}
		
		
		/*
		 * Display the ranking map to the console.
		 */
		Map<String, Integer> outputRankMap = WebUtils.sortByValueDesc(rankingMap);
		int rank = 1;
		for (Map.Entry<String,Integer> entry : outputRankMap.entrySet()) { 
            System.out.println(rank + "--" + entry.getKey() + "--" + entry.getValue());    
            if (rank <= noToMatch) {
            	Profile pRank = new Profile();
            	pRank.setTwid(Integer.parseInt(entry.getKey()));
            	Profile pMatch = (Profile) DataService.selectOne(pRank);
            	
            	if (rank == ordinal) {
            		matchedProfiles.add(pMatch);
            	}
            	
            	
            }
            rank++;
        }
		
		
		String qs2 = "";
		
		if (matchedProfiles.size() > 0) {
			//Mission msCurrentRecord = new Mission();
			//msCurrentRecord.setMissionId(jobId);
			//Mission msFieldUpdates = new Mission();
			//msFieldUpdates.setMatchedDate(Calendar.getInstance().getTime());
			//DataService.update(msFieldUpdates, msCurrentRecord);
			
			
			
			/*
			 * Used by DataService.update to selectOne and
			 * get the current values of the record as stored
			 * in the database.
			 */
			Mission reference = new Mission();
			reference.setMissionId(jobId);

			/*
			 * A second local object which contains all of the
			 * current values of the record as stored in the
			 * database.
			 */
			Mission update = (Mission) DataService.selectOne(reference);

			/*
			 * Now, update only the object fields that you wish
			 * to update, leaving all other fields the same.
			 */
			update.setMatchedDate(Calendar.getInstance().getTime());

			/*
			 * Create an updated object.
			 */
			Mission update2 = new Mission(update);
			/*
			 * Execute the update using update
			 */
			DataService.update(update2, reference);

			/*
			 * Be sure to get a local object copy that 
			 * reflects the updated values resulting from the
			 * previous update statement above.
			 */
			Mission updated = (Mission) DataService.selectOne(reference);
			
			
			
		}
		
		notifyMatchedProfiles(matchedProfiles, job);
		
		
		/*
		 * Revise search if too few candidates
		 * result from the original search
		 * algorithm.
		 * 
		 * 1. most total TW points
		 * 2. most successfully completed jobs
		 * 3. highest survey average overall score out of 5.0
		 */
		
		
		
		return matchedProfiles;
		
	}
	
	
	
	
	public static void notifyMatchedProfiles(List<Profile> profiles, Mission mission) {
		
		SiteConstants sc = new SiteConstants();
		
		for (Profile profile : profiles) {
			Notification notification = new Notification();
			notification.setTwid1(mission.getTwid1());
			notification.setTwid2(profile.getTwid());
			notification.setType(sc.NOTIFICATION_TYPE_JOB);
			notification.setMissionId(mission.getMissionId());
			notification.setSubject("Congratulations! You have been matched to an opportunity.");
			Date createdDate = Calendar.getInstance().getTime();
			notification.setCreatedDate(createdDate);
			notification.setCreatedTime(SqlUtils.convertTimeToInt(createdDate, false));
			notification.setDaysValid(1);
			DataService.create(notification);
		}
		
		
	}
	
	
	
	public static List<Notification> getNotificationsFor(Profile profile) {
		
		List<Notification> notifications = new ArrayList<Notification>();
		
		Notification n = new Notification();
		String qs = "twid1 = " + profile.getTwid();
		Object[] results = DataService.selectMany(n, qs);
		
		for (Object result : results) {
			Notification n1 = (Notification) result;
			notifications.add(n1);
		}
		
		return notifications;
		
	}

}
