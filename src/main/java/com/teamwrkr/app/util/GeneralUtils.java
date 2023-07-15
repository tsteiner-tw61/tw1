package com.teamwrkr.app.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import com.teamwrkr.app.service.LoginService;

public class GeneralUtils {
	
	public GeneralUtils() { }
	
	public static String generateTestProfiles() {
		
		String outputStr = "";
		
		int twid = 10000;
		int sessionId = 100000000;
		Calendar calCreateDate = Calendar.getInstance();
		Calendar calDOB = Calendar.getInstance();
		String pwd = "Fuck0ff!";
		String encPwd = LoginService.encrypt(pwd);
		String country = "USA";
		
		String category1 = "Marketing";
		
		List<String> subcats = new ArrayList<String>();
		subcats.add("Art/Illustration");
		subcats.add("Branding");
		subcats.add("Copywriting/Editing");
		subcats.add("Graphic Design");
		subcats.add("Video Editing");
		subcats.add("UX/UI Design");
		
		List<String> niches = new ArrayList<String>();
		niches.add("blah blah blah blah blah blah blah blah blah blah blah blah ");
		niches.add("This site is the best.");
		niches.add("I HATE THIS FUCKING SITE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		niches.add("I love to talk about myselfie.");
		
		List<String> softSkills = new ArrayList<String>();
		softSkills.add("Time Management");
		softSkills.add("Communication");
		softSkills.add("Adaptability");
		softSkills.add("Problem Solving");
		softSkills.add("Teamwork");
		softSkills.add("Creativity");
		softSkills.add("Leadership");
		softSkills.add("Interpersonal Skills");
		softSkills.add("Work Ethic");
		softSkills.add("Attention To Detail");
		
		List<String> hardSkills = new ArrayList<String>();
		hardSkills.add("Specific Skill 1");
		hardSkills.add("Specific Skill 2");
		hardSkills.add("Specific Skill 3");
		hardSkills.add("Specific Skill 4");
		hardSkills.add("Specific Skill 5");
		hardSkills.add("Specific Skill 6");
		hardSkills.add("Specific Skill 7");
		hardSkills.add("Specific Skill 8");
		hardSkills.add("Specific Skill 9");
		hardSkills.add("Specific Skill 10");
		
		List<String> photoImgs = new ArrayList<String>();
		photoImgs.add("Halloween Ganley.jpg");
		photoImgs.add("garu owl 1.png");
		photoImgs.add("random-lady-face-1.jpg");
		
		
		try {
			
			Scanner scan = new Scanner(new File("C:\\Users\\ted00\\tw-test-db-profiles-2.txt"));
	        ArrayList<String> firstNames = new ArrayList<String>();
	        ArrayList<String> lastNames = new ArrayList<String>();
	        ArrayList<String> emails = new ArrayList<String>();
	        ArrayList<String> cities = new ArrayList<String>();
	        ArrayList<String> states = new ArrayList<String>();

	        int picIndex = 0;
	        int subcatIndex = 0;
	        int nichesIndex = 0;
	        int testEmailIndex = 1;
	        while (scan.hasNext()) {
	        	
	        	String query = "INSERT INTO [dbo].[profile] ([twid] ,[sessionId],[email],[firstName],[lastName],[city],"
	    				+ "[state],[country],[dob],[photoImg],[category1],[category2],[niche],[encPwd],[createDate])"
	    				+ " VALUES (";
	    		
	    		String query2 = "INSERT INTO [dbo].[scores] ([scoreId],[twid],[scoreh1],[scoreh2],[scoreh3],[scoreh4],[scoreh5],[scores1],"
	    				+ "[scores2],[scores3],[scores4],[scores5],[scoreOverall],[hardskill1],[hardskill2],[hardskill3],[hardskill4],"
	    				+ "[hardskill5],[softskill1],[softskill2],[softskill3],[softskill4],[softskill5],[scoreh6],[scoreh7],[scoreh8],"
	    				+ "[scoreh9],[scoreh10],[scores6],[scores7],[scores8],[scores9],[scores10],[countOverall],[hardskill6],"
	    				+ "[hardskill7],[hardskill8],[hardskill9],[hardskill10],[softskill6],[softskill7],[softskill8],[softskill9],"
	    				+ "[softskill10],[counth1],[counth2],[counth3],[counth4],[counth5],[counth6],[counth7],[counth8],[counth9],"
	    				+ "[counth10],[counts1],[counts2],[counts3],[counts4],[counts5],[counts6],[counts7],[counts8],[counts9],[counts10]) "
	    				+ "VALUES (";
	        	
	            String curLine = scan.nextLine();
	            String[] splitted = curLine.split("\t");
	            String fn = splitted[0].trim();
	            firstNames.add(fn);
	            String ln = splitted[1].trim();
	            lastNames.add(ln);
	            String em = splitted[2].trim();
	            emails.add(em);
	            String cy = splitted[3].trim();
	            cities.add(cy);
	            String st = splitted[4].trim();
	            states.add(st);
	            
	            query += twid + ", ";
	            query += sessionId + ", ";
	            //query += "'" + em + "', ";
	            query += "'test" + testEmailIndex + "@teamwrkr.com', ";
	            query += "'" + fn + "', ";
	            query += "'" + ln + "', ";
	            query += "'" + cy + "', ";
	            query += "'" + st + "', ";
	            query += "'" + country + "', ";
	            query += SqlUtils.convertToDate(calDOB.getTime())+ ", ";
	            query += "'" + photoImgs.get(picIndex % 3) + "', ";
	            query += "'" + category1 + "', ";
	            query += "'" + subcats.get(subcatIndex % 6) + "', ";
	            query += "'" + niches.get(nichesIndex % 4) + "', ";
	            query += "'" + encPwd + "', ";
	            query += SqlUtils.convertToDate(calCreateDate.getTime()) + ");\n";
	            query += "GO\n";
	            
	            query2 += twid + ", ";
	            query2 += twid + ", ";
	            for (int i = 0; i < 10; i++) {
	            	query2 += WebUtils.getRandom3() + ", ";
	            }
	            query2 += WebUtils.getRandom5() + ", ";
	            query2 += "'" + hardSkills.get(0) + "', ";
	            query2 += "'" + hardSkills.get(1) + "', ";
	            query2 += "'" + hardSkills.get(2) + "', ";
	            query2 += "'" + hardSkills.get(3) + "', ";
	            query2 += "'" + hardSkills.get(4) + "', ";
	            query2 += "'" + softSkills.get(0) + "', ";
	            query2 += "'" + softSkills.get(1) + "', ";
	            query2 += "'" + softSkills.get(2) + "', ";
	            query2 += "'" + softSkills.get(3) + "', ";
	            query2 += "'" + softSkills.get(4) + "', ";
	            for (int i = 0; i < 10; i++) {
	            	query2 += WebUtils.getRandom3() + ", ";
	            }
	            query2 += WebUtils.getRandom3() + ", ";
	            query2 += "'" + hardSkills.get(5) + "', ";
	            query2 += "'" + hardSkills.get(6) + "', ";
	            query2 += "'" + hardSkills.get(7) + "', ";
	            query2 += "'" + hardSkills.get(8) + "', ";
	            query2 += "'" + hardSkills.get(9) + "', ";
	            query2 += "'" + softSkills.get(5) + "', ";
	            query2 += "'" + softSkills.get(6) + "', ";
	            query2 += "'" + softSkills.get(7) + "', ";
	            query2 += "'" + softSkills.get(8) + "', ";
	            query2 += "'" + softSkills.get(9) + "', ";
	            for (int i = 0; i < 19; i++) {
	            	query2 += WebUtils.getRandom2() + ", ";
	            }
	            query2 += WebUtils.getRandom2() + ");\n";
	            query2 += "GO\n";
	            
	            twid++;
	            sessionId++;
	            picIndex++;
	            subcatIndex++;
	            nichesIndex++;
	            
	            System.out.println(query);
		        System.out.println(query2);
		        
		        outputStr += query + query2;
		        
		        query = "";
		        query2 = "";
		        
		        
	            
	        }
	        
	        
	        
	        scan.close();
	        
		}
		catch (FileNotFoundException e) {
			System.out.println("Exception " + e.getMessage());
			
		}
		
		
		return outputStr;
		
		
	}

}
