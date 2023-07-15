package com.teamwrkr.app.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.teamwrkr.app.dto.Profile;
import com.teamwrkr.app.dto.Scores;
import com.teamwrkr.app.dto.Survey;
import com.teamwrkr.app.util.WebUtils;

/**
 * This class calculates all the ratings for the
 * skill score ratings in the DDR.
 * 
 * @author Ted E. Steiner
 * @date 3.17.2023
 *
 */
public class ScoreService {
	
	private Map<String,Integer> hardSkillSurveyScores;
	private Map<String,Integer> softSkillSurveyScores;
	private Map<String,Integer> hardSkillSurveyCounts;
	private Map<String,Integer> softSkillSurveyCounts;
	private Map<String,Double> hardSkillSurveyAggScores;
	private Map<String,Double> softSkillSurveyAggScores;
	
	
	public ScoreService() { }
	
	
	/**
	 * Method to calculate the overall score average for
	 * a given user's missions.
	 * 
	 * @param id
	 * @return
	 */
	public static float calculateOverallScore(int id) {
		
		float aggScore = 0;
		
		int total = 0;
		int surveysTotal = 0;
		Survey survey = new Survey();
		Object[] surveys = DataService.selectMany(survey, "twid = " + id + " AND completeDate IS NOT NULL");
		
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
			return aggScore;
		}
		
		
		for (int i = 0; i < surveysToProcess; i++) {
			Survey sv = (Survey) surveys[i];
			int score = sv.getScoreoverall();
			//System.out.println("score[" + i + "]=" + score);
			total = total + score;
			if (score > 0) {
				surveysTotal = surveysTotal + 1;
			}
			//System.out.println("total[" + i + "]=" + surveysTotal);
		}
		//System.out.println("surveysTotal=" + surveysTotal);
		if (surveysTotal != 0) {
			//System.out.println("surveysTotal+++++++++++++++++=" + surveysTotal);
			//aggScore = (((float) total)/surveysTotal);
			aggScore = (float) total;
			//System.out.println("aggscv+++++++++++++++++=" + aggScore);
		}
		
		return aggScore;
		
	}



	public static int calculateOverallPoints(int id, Scores scores) {
		
		int total = 0;
		
		int surveysTotal = 0;
		Survey survey = new Survey();
		Object[] surveys = DataService.selectMany(survey, "twid = " + id + " AND completeDate IS NOT NULL");
		
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
			return total;
		}
		
		
		for (int i = 0; i < surveysToProcess; i++) {
			Survey sv = (Survey) surveys[i];
			int score = sv.getScoreoverall();
			//System.out.println("OVERALL POINTS - SURVEY[" + i + "] = " + score);
			total = total + score;
			if (score > 0) {
				surveysTotal = surveysTotal + 1;
			}
			//System.out.println("TOTAL POINTS AFTER SURVEY[" + i + "] = " + surveysTotal);
		}
		//System.out.println("TOTAL NUMBER OF SURVEYS PROCESSED = " + surveysTotal);

		int totalPts = 0;
		int h1 = scores.getScoreh1();
		int h2 = scores.getScoreh2();
		int h3 = scores.getScoreh3();
                int h4 = scores.getScoreh4();
		int h5 = scores.getScoreh5();
                int h6 = scores.getScoreh6();
		int h7 = scores.getScoreh7();
                int h8 = scores.getScoreh8();
		int h9 = scores.getScoreh9();
                int h10 = scores.getScoreh10();
		
		int s1 = scores.getScores1();
                int s2 = scores.getScores2();
                int s3 = scores.getScores3();
                int s4 = scores.getScores4();
                int s5 = scores.getScores5();
                int s6 = scores.getScores6();
                int s7 = scores.getScores7();
                int s8 = scores.getScores8();
                int s9 = scores.getScores9();
                int s10 = scores.getScores10();		

		int ov = (int) calculateOverallScore(id);
		//System.out.println("calculateOverallScore = " + ov);	
		
		totalPts = h1 + h2 + h3 + h4 + h5 + h6 + h7 + h8 + h9 + h10 + ov + s1 + s2 + s3 + s4 + s5 + s6 + s7 + s8 + s9 + s10;		

		return totalPts;
		
	}
	
	public static int calcBarWidth(int pct) {
		
		int n = 0;
		float nf = (float) 0.0;
		
		nf = ((float) pct) * (float) 2.7;
		
		return Math.round(nf);

	}

	public static int calcSkillWidth(int pts) {
		
		float w = (float) 0.0;
		
		if (pts < 100) {
			w = ((float) pts) * (float) 2.0;
		}
		if (pts >= 100 && pts < 400) {
			w = ((float) pts) * (float) 0.5;
		}
		if (pts >= 400 && pts < 1600) {
			w = ((float) pts) * (float) 0.125;
		}
		if (pts >= 1600) {
			w = ((float) pts) * (float) 0.01;
		}
		return Math.round(w);
		
	}
	
	/**
	 * Convenience method to get a single rating score.
	 * Returns 0 if no skill matches the available skills.
	 * 
	 * @param skill
	 * @return
	 */
	public double getRatingScore(String skill) {
		
		double score = 0.0;
		
		Map<String,Double> hardAggScores = this.hardSkillSurveyAggScores;
		
		for (Map.Entry<String, Double> entry : hardAggScores.entrySet()) {
			if (entry.getKey().equals(skill) ) {
				score = entry.getValue();
			}
		}
		
		Map<String,Double> softAggScores = this.softSkillSurveyAggScores;
		
		for (Map.Entry<String, Double> entry : softAggScores.entrySet()) {
			if (entry.getKey().equals(skill) ) {
				score = entry.getValue();
			}
		}
		
		return score;
		
	}
	
	
	/**
	 * Get rating percentage for a single skill.
	 * 
	 * @param skill
	 * @return
	 */
	public int getRatingPercentage(String skill) {
		
		int pct = 0;
		
		double ratingScore = getRatingScore(skill);
		
		ratingScore = ratingScore * 20;
		//System.out.println("ratingScorePct=" + ratingScore);
		
		return pct;
		
	}
	
	public List<String> getUserSkills(Profile profile, String type) {
		
		List<String> skills = new ArrayList<String>();
		
		if (profile.getUserSkill1() != null) {
			
		}
		
		skills.add(profile.getUserSkill1());
		
		return skills;
		
	}
	
	public static int calculatePct(int total, int count) {
		
		//System.out.println("calculatePct(" + total + "," + count + ")");
		
		int pct = 0;
		float pctf = (float) 0.0;
		
		if (total != 0 && count != 0) {
			pctf = (((float) total)/(count*5)) * 100;
		}
		else {
			return 0;
		}
		
		return Math.round(pctf);
		
		
	}
	
	
	
	/**
	 * Get a Map of the current measurable skills and their associated 
	 * point totals, in descending order.
	 * 
	 * @param scores
	 * @param profile
	 * @return Map<String,Integer> skillScoreMap
	 */
	public static Map<String,Integer> getMeasurableSkillScores(Scores scores, Profile profile) {
		
		Map<String,Integer> skillScoreMap = new HashMap<String,Integer>();
		
		if (scores == null) {
			return skillScoreMap;
		}
		
		if (scores.getHardskill1() != null) {
			skillScoreMap.put(scores.getHardskill1(), scores.getScoreh1());
		}
		if (scores.getHardskill2() != null) {
			skillScoreMap.put(scores.getHardskill2(), scores.getScoreh2());
		}
		if (scores.getHardskill3() != null) {
			skillScoreMap.put(scores.getHardskill3(), scores.getScoreh3());
		}
		if (scores.getHardskill4() != null) {
			skillScoreMap.put(scores.getHardskill4(), scores.getScoreh4());
		}
		if (scores.getHardskill5() != null) {
			skillScoreMap.put(scores.getHardskill5(), scores.getScoreh5());
		}
		if (scores.getHardskill6() != null) {
			skillScoreMap.put(scores.getHardskill6(), scores.getScoreh6());
		}
		if (scores.getHardskill7() != null) {
			skillScoreMap.put(scores.getHardskill7(), scores.getScoreh7());
		}
		if (scores.getHardskill8() != null) {
			skillScoreMap.put(scores.getHardskill8(), scores.getScoreh8());
		}
		if (scores.getHardskill9() != null) {
			skillScoreMap.put(scores.getHardskill9(), scores.getScoreh9());
		}
		if (scores.getHardskill10() != null) {
			skillScoreMap.put(scores.getHardskill10(), scores.getScoreh10());
		}
		
		Map<String,Integer> logMap = WebUtils.sortByValueDesc(skillScoreMap);
		
		for (Map.Entry<String,Integer> entry : logMap.entrySet()) { 
            System.out.println(entry.getKey() + "--" + entry.getValue());  
		}
		
		return logMap;
		
	}
	
	
	public static Map<String,Integer> calculatePct(Scores scores, Profile profile) {
		
		Map<String,Integer> displayedHardSkillPcts = new LinkedHashMap<String,Integer>();
		
		if (scores == null) {
			return displayedHardSkillPcts;
		}
		
		List<String> ssList = SkillService.getSoftSkills();
		List<String> hardSkills = SkillService.getHardSkills(profile.getCategory1(), profile.getCategory2());
		List<String> unselectedHardSkills = new ArrayList<String>();
		List<String> selectedHardSkills = new ArrayList<String>();
		List<String> displayedHardSkills = new ArrayList<String>();
		List<String> scoredHardSkills = new ArrayList<String>();
		int[] hardSkillMatrix = {-1,-1,-1,-1,-1};
	
		
		/*
		 * Check if the user has their skills selected.
		 * If not, do not calculate or display any skills.
		 */
		boolean calculate = true;
		if (profile.getUserSkill1() == null || profile.getUserSkill1().isEmpty()) {calculate = false;}
		if (profile.getUserSkill2() == null || profile.getUserSkill2().isEmpty()) {calculate = false;}
		if (profile.getUserSkill3() == null || profile.getUserSkill3().isEmpty()) {calculate = false;}		


		if (calculate) {

			/*
			 * Break the hard skills list into 2 lists,
			 * selected, and unselected.
			 */
			for (String hardSkill : hardSkills) {
				if (!hardSkill.equals(profile.getUserSkill1()) &&
					!hardSkill.equals(profile.getUserSkill2()) &&
					!hardSkill.equals(profile.getUserSkill3())) {
					unselectedHardSkills.add(hardSkill);
				}
				else {
					selectedHardSkills.add(hardSkill);
					displayedHardSkills.add(hardSkill);
				}
			}	


			/*
			 * Get the scored skills that match the unselected skills.
			 * Send the first 2 discovered to the displayed skills list.
			 *
			 * TODO get all the unselected skills and only display the best 2.
			 */
			int ct = 0;
			if (scores.getHardskill1() != null &&
				unselectedHardSkills.contains(scores.getHardskill1()) &&
				scores.getCounth1() > 0 && ct < 2) {
				displayedHardSkills.add(scores.getHardskill1());
				ct = ct + 1;
			}
			if (scores.getHardskill2() != null &&
				unselectedHardSkills.contains(scores.getHardskill2()) &&
				scores.getCounth2() > 0 && ct < 2) {
				displayedHardSkills.add(scores.getHardskill2());
				ct = ct + 1;
			}
			if (scores.getHardskill3() != null &&
				unselectedHardSkills.contains(scores.getHardskill3()) &&
				scores.getCounth3() > 0 && ct < 2) {
				displayedHardSkills.add(scores.getHardskill3());
				ct = ct + 1;
			}
			if (scores.getHardskill4() != null &&
				unselectedHardSkills.contains(scores.getHardskill4()) &&
				scores.getCounth4() > 0 && ct < 2) {
				displayedHardSkills.add(scores.getHardskill4());
				ct = ct + 1;
			}
			if (scores.getHardskill5() != null &&
				unselectedHardSkills.contains(scores.getHardskill5()) &&
				scores.getCounth5() > 0 && ct < 2) {
				displayedHardSkills.add(scores.getHardskill5());
				ct = ct + 1;
			}
			if (scores.getHardskill6() != null &&
				unselectedHardSkills.contains(scores.getHardskill6()) &&
				scores.getCounth6() > 0 && ct < 2) {
				displayedHardSkills.add(scores.getHardskill6());
				ct = ct + 1;
			}
			if (scores.getHardskill7() != null &&
				unselectedHardSkills.contains(scores.getHardskill7()) &&
				scores.getCounth7() > 0 && ct < 2) {
				displayedHardSkills.add(scores.getHardskill7());
				ct = ct + 1;
			}
			if (scores.getHardskill8() != null &&
				unselectedHardSkills.contains(scores.getHardskill8()) &&
				scores.getCounth8() > 0 && ct < 2) {
				displayedHardSkills.add(scores.getHardskill8());
				ct = ct + 1;
			}
			if (scores.getHardskill9() != null &&
				unselectedHardSkills.contains(scores.getHardskill9()) &&
				scores.getCounth9() > 0 && ct < 2) {
				displayedHardSkills.add(scores.getHardskill9());
				ct = ct + 1;
			}
			if (scores.getHardskill10() != null &&
				unselectedHardSkills.contains(scores.getHardskill10()) &&
				scores.getCounth10() > 0 && ct < 2) {
				displayedHardSkills.add(scores.getHardskill10());
				ct = ct + 1;
			}



	
			/*
			 * Put the scoredHardSkills in a list for logging purposes.
			 */
			if (scores.getHardskill1() != null) {scoredHardSkills.add(scores.getHardskill1());}
			if (scores.getHardskill2() != null) {scoredHardSkills.add(scores.getHardskill2());}
			if (scores.getHardskill3() != null) {scoredHardSkills.add(scores.getHardskill3());}
			if (scores.getHardskill4() != null) {scoredHardSkills.add(scores.getHardskill4());}
			if (scores.getHardskill5() != null) {scoredHardSkills.add(scores.getHardskill5());}
			if (scores.getHardskill6() != null) {scoredHardSkills.add(scores.getHardskill6());}
			if (scores.getHardskill7() != null) {scoredHardSkills.add(scores.getHardskill7());}
			if (scores.getHardskill8() != null) {scoredHardSkills.add(scores.getHardskill8());}
			if (scores.getHardskill9() != null) {scoredHardSkills.add(scores.getHardskill9());}
			if (scores.getHardskill10() != null) {scoredHardSkills.add(scores.getHardskill10());}
			

			
			/*
			 * Create a matrix for scored hard skills to display
			 */
			for (int i = 0; i < scoredHardSkills.size(); i++) {
			
				if (selectedHardSkills.size() > 0 && scoredHardSkills.get(i).equals(selectedHardSkills.get(0))) {
					hardSkillMatrix[0] = i;
				}
				if (selectedHardSkills.size() > 1 && scoredHardSkills.get(i).equals(selectedHardSkills.get(1))) {
					hardSkillMatrix[1] = i;
				}
				if (selectedHardSkills.size() > 2 && scoredHardSkills.get(i).equals(selectedHardSkills.get(2))) {
					hardSkillMatrix[2] = i;
				}
				if (unselectedHardSkills.size() > 0 && scoredHardSkills.get(i).equals(unselectedHardSkills.get(0))) {
					hardSkillMatrix[3] = i;
				}
				if (unselectedHardSkills.size() > 1 && scoredHardSkills.get(i).equals(unselectedHardSkills.get(1))) {
					hardSkillMatrix[4] = i;
				}
			}


	
		
			int[] displayedTotals = {0,0,0,0,0};
		
			for (int i = 0; i < hardSkillMatrix.length; i++) {
				if (hardSkillMatrix[i] == -1) {continue;}
				if (hardSkillMatrix[i] == 0) {
					displayedTotals[i] = scores.getScoreh1();
					//System.out.println("displayedTotals[" + i + "] = " + scores.getScoreh1());
				}
				if (hardSkillMatrix[i] == 1) {
					displayedTotals[i] = scores.getScoreh2();
					//System.out.println("displayedTotals[" + i + "] = " + scores.getScoreh2());
				}
				if (hardSkillMatrix[i] == 2) {
					displayedTotals[i] = scores.getScoreh3();
					//System.out.println("displayedTotals[" + i + "] = " + scores.getScoreh3());
				}
				if (hardSkillMatrix[i] == 3) {
					displayedTotals[i] = scores.getScoreh4();
					//System.out.println("displayedTotals[" + i + "] = " + scores.getScoreh4());
				}
				if (hardSkillMatrix[i] == 4) {
					displayedTotals[i] = scores.getScoreh5();
					//System.out.println("displayedTotals[" + i + "] = " + scores.getScoreh5());
				}
				if (hardSkillMatrix[i] == 5) {
					displayedTotals[i] = scores.getScoreh6();
					//System.out.println("displayedTotals[" + i + "] = " + scores.getScoreh6());
				}
				if (hardSkillMatrix[i] == 6) {
					displayedTotals[i] = scores.getScoreh7();
					//System.out.println("displayedTotals[" + i + "] = " + scores.getScoreh7());
				}
				if (hardSkillMatrix[i] == 7) {
					displayedTotals[i] = scores.getScoreh8();
					//System.out.println("displayedTotals[" + i + "] = " + scores.getScoreh8());
				}
				if (hardSkillMatrix[i] == 8) {
					displayedTotals[i] = scores.getScoreh9();
					//System.out.println("displayedTotals[" + i + "] = " + scores.getScoreh9());
				}
				if (hardSkillMatrix[i] == 9) {
					displayedTotals[i] = scores.getScoreh10();
					//System.out.println("displayedTotals[" + i + "] = " + scores.getScoreh10());
				}
	
			}
			

		
			int[] displayedCounts = {0,0,0,0,0};
			
			for (int i = 0; i < hardSkillMatrix.length; i++) {
				if (hardSkillMatrix[i] == -1) {continue;}
				if (hardSkillMatrix[i] == 0) {displayedCounts[i] = scores.getCounth1();}
				if (hardSkillMatrix[i] == 1) {displayedCounts[i] = scores.getCounth2();}
				if (hardSkillMatrix[i] == 2) {displayedCounts[i] = scores.getCounth3();}
				if (hardSkillMatrix[i] == 3) {displayedCounts[i] = scores.getCounth4();}
				if (hardSkillMatrix[i] == 4) {displayedCounts[i] = scores.getCounth5();}
				if (hardSkillMatrix[i] == 5) {displayedCounts[i] = scores.getCounth6();}
				if (hardSkillMatrix[i] == 6) {displayedCounts[i] = scores.getCounth7();}
				if (hardSkillMatrix[i] == 7) {displayedCounts[i] = scores.getCounth8();}
				if (hardSkillMatrix[i] == 8) {displayedCounts[i] = scores.getCounth9();}
				if (hardSkillMatrix[i] == 9) {displayedCounts[i] = scores.getCounth10();}
			}
		
		
		
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ SKILLS PROCESSING ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			
			System.out.println("");
			System.out.println("");
			System.out.println("");
                	System.out.println("");
	
			System.out.println("----------------");
			int index1 = 0;
			for (String ss : ssList) {
				System.out.println("softskill[" + index1 + "] = " + ss);
				index1++;
			}
			System.out.println("");
			System.out.println("");
			System.out.println("----------------");
		
			System.out.println("----------------");	
			int index2 = 0;
			for (String hs : hardSkills) {
				System.out.println("hardSkills[" + index2 + "] = " + hs);
				index2++;
			}
			System.out.println("");
			System.out.println("");
			System.out.println("----------------");

			System.out.println("----------------");		
			int index3 = 0;
			for (String uhs : unselectedHardSkills) {
				System.out.println("unselectedHardSkills[" + index3 + "] = " + uhs);
				index3++;
			}
			System.out.println("");
			System.out.println("");
			System.out.println("----------------");
	
			System.out.println("----------------");
			int index4 = 0;
			for (String shs : selectedHardSkills) {
				System.out.println("selectedHardSkills[" + index4 + "] = " + shs);
				index4++;
			}
			System.out.println("");
			System.out.println("");
			System.out.println("----------------");

			System.out.println("----------------");
			int index5 = 0;
			for (String dhs : displayedHardSkills) {
				System.out.println("displayedHardSkills[" + index5 + "] = " + dhs);
				index5++;
			}
			System.out.println("");
			System.out.println("");
			System.out.println("----------------");

			System.out.println("----------------");
			int index6 = 0;
			for (String schs : scoredHardSkills) {
				System.out.println("scoredHardSkills[" + index6 + "] = " + schs);
				index6++;
			}
			System.out.println("");
			System.out.println("");
			System.out.println("----------------");

			System.out.println("----------------");		
			for (int index7 = 0; index7 < hardSkillMatrix.length; index7++) {
				System.out.println("hardSkillMatrix[" + index7 + "] = " + hardSkillMatrix[index7]);
			}
			System.out.println("");
			System.out.println("");
			System.out.println("----------------");
		
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		
			/*
			 * Initialize map.
			 */
			int idx = 0;
			for (String hs : displayedHardSkills) {
			   	int calcPts = displayedTotals[idx];
			    	displayedHardSkillPcts.put(hs, calcPts);
			    	System.out.println("displayedHardSkillPts.put (" + hs + "," + calcPts + ")");
		    		idx++;
			}

		}
		
		return displayedHardSkillPcts;
		
	}
	
		
	public static Map<String, Integer> getHardSkillScoresMap(Scores scores) {
		
		Map<String, Integer> hardSkillsScoreMap = new HashMap<String, Integer>();
		
		String[] skillNames = {scores.getHardskill1(),
								scores.getHardskill2(),
								scores.getHardskill3(),
								scores.getHardskill4(),
								scores.getHardskill5(),
								scores.getHardskill6(),
								scores.getHardskill7(),
								scores.getHardskill8(),
								scores.getHardskill9(),
								scores.getHardskill10()};
		
		int[] skillValues = {scores.getScoreh1(),
								scores.getScoreh2(),
								scores.getScoreh3(),
								scores.getScoreh4(),
								scores.getScoreh5(),
								scores.getScoreh6(),
								scores.getScoreh7(),
								scores.getScoreh8(),
								scores.getScoreh9(),
								scores.getScoreh10()};
		
		for (int i = 0; i < skillNames.length; i++) {
			hardSkillsScoreMap.put(skillNames[i], Integer.valueOf(skillValues[i]));
			
		}
		
		return WebUtils.sortByValue(hardSkillsScoreMap);
		
	}
	
	
	public static Map<String, Integer> getHardSkillScoresMapDesc(Scores scores) {
		
		Map<String, Integer> hardSkillsScoreMap = new HashMap<String, Integer>();
		
		String[] skillNames = {scores.getHardskill1(),
								scores.getHardskill2(),
								scores.getHardskill3(),
								scores.getHardskill4(),
								scores.getHardskill5(),
								scores.getHardskill6(),
								scores.getHardskill7(),
								scores.getHardskill8(),
								scores.getHardskill9(),
								scores.getHardskill10()};
		
		int[] skillValues = {scores.getScoreh1(),
								scores.getScoreh2(),
								scores.getScoreh3(),
								scores.getScoreh4(),
								scores.getScoreh5(),
								scores.getScoreh6(),
								scores.getScoreh7(),
								scores.getScoreh8(),
								scores.getScoreh9(),
								scores.getScoreh10()};
		
		for (int i = 0; i < skillNames.length; i++) {
			hardSkillsScoreMap.put(skillNames[i], Integer.valueOf(skillValues[i]));
			
		}
		
		return WebUtils.sortByValueDesc(hardSkillsScoreMap);
		
	}
	
	public static Map<String, Integer> getSoftSkillScoresMap(Scores scores) {
		
		Map<String, Integer> softSkillsScoreMap = new HashMap<String, Integer>();
		
		String[] skillNames = {scores.getSoftskill1(),
								scores.getSoftskill2(),
								scores.getSoftskill3(),
								scores.getSoftskill4(),
								scores.getSoftskill5(),
								scores.getSoftskill6(),
								scores.getSoftskill7(),
								scores.getSoftskill8(),
								scores.getSoftskill9(),
								scores.getSoftskill10()};
		
		int[] skillValues = {scores.getScores1(),
								scores.getScores1(),
								scores.getScores2(),
								scores.getScores3(),
								scores.getScores4(),
								scores.getScores5(),
								scores.getScores6(),
								scores.getScores7(),
								scores.getScores8(),
								scores.getScores9(),
								scores.getScores10()};
		
		for (int i = 0; i < skillNames.length; i++) {
			softSkillsScoreMap.put(skillNames[i], Integer.valueOf(skillValues[i]));
		}
	
		return WebUtils.sortByValue(softSkillsScoreMap);
		
	}
	
	
	public static Map<String, Integer> getSoftSkillScoresMapDesc(Scores scores) {
		
		Map<String, Integer> softSkillsScoreMap = new HashMap<String, Integer>();
		
		String[] skillNames = {scores.getSoftskill1(),
								scores.getSoftskill2(),
								scores.getSoftskill3(),
								scores.getSoftskill4(),
								scores.getSoftskill5(),
								scores.getSoftskill6(),
								scores.getSoftskill7(),
								scores.getSoftskill8(),
								scores.getSoftskill9(),
								scores.getSoftskill10()};
		
		int[] skillValues = {scores.getScores1(),
								scores.getScores1(),
								scores.getScores2(),
								scores.getScores3(),
								scores.getScores4(),
								scores.getScores5(),
								scores.getScores6(),
								scores.getScores7(),
								scores.getScores8(),
								scores.getScores9(),
								scores.getScores10()};
		
		for (int i = 0; i < skillNames.length; i++) {
			softSkillsScoreMap.put(skillNames[i], Integer.valueOf(skillValues[i]));
		}
	
		return WebUtils.sortByValueDesc(softSkillsScoreMap);
		
	}
	
	
	public Map<String,Integer> getHardSkillSurveyScores() {
		return this.hardSkillSurveyScores;
	}
	
	public void setHardSkillSurveyScores(Map<String,Integer> hardSkillSurveyScores) {
		this.hardSkillSurveyScores = hardSkillSurveyScores;
	}
	
	public Map<String,Integer> getHardSkillSurveyCounts() {
		return this.hardSkillSurveyCounts;
	}
	
	public void setHardSkillSurveyCouunts(Map<String,Integer> hardSkillSurveyCounts) {
		this.hardSkillSurveyCounts = hardSkillSurveyCounts;
	}
	
	public Map<String,Integer> getSoftSkillSurveyScores() {
		return this.softSkillSurveyScores;
	}
	
	public void setSoftSkillSurveyScores(Map<String,Integer> softSkillSurveyScores) {
		this.softSkillSurveyScores = softSkillSurveyScores;
	}
	
	public Map<String,Integer> getSoftSkillSurveyCounts() {
		return this.softSkillSurveyCounts;
	}
	
	public void setSoftSkillSurveyCounts(Map<String,Integer> softSkillSurveyCounts) {
		this.softSkillSurveyCounts = softSkillSurveyCounts;
	}
	
	public Map<String,Double> getHardSkillSurveyAggScores() {
		return this.hardSkillSurveyAggScores;
	}
	
	public void setHardSkillSurveyAggScores(Map<String,Double> hardSkillSurveyAggScores) {
		this.hardSkillSurveyAggScores = hardSkillSurveyAggScores;
	}
	
	public Map<String,Double> getSoftSkillSurveyAggScores() {
		return this.softSkillSurveyAggScores;
	}
	
	public void setSoftSkillSurveyAggScores(Map<String,Double> softSkillSurveyAggScores) {
		this.softSkillSurveyAggScores = softSkillSurveyAggScores;
	}
	


}

