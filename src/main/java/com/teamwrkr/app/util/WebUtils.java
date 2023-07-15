package com.teamwrkr.app.util;

import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Map.Entry;

public class WebUtils {
	
	
	public WebUtils() { }
	
	/*
	 * Generate random 4-digit verification code.
	 * 
	 * TODO make this getRandom(n) where n is
	 * number of digit random.
	 */
    public static String getRandom4() {
        Random rnd = new Random();
        int number = rnd.nextInt(9999);
        return String.format("%04d", number);
    }
    
    public static String getRandom3() {
        Random rnd = new Random();
        int number = rnd.nextInt(999);
        return String.format("%03d", number);
    }
    
    public static String getRandom2() {
        Random rnd = new Random();
        int number = rnd.nextInt(99);
        return String.format("%02d", number);
    }
    
    public static String getRandom5() {
        Random rnd = new Random();
        int number = rnd.nextInt(99999);
        return String.format("%05d", number);
    }
    
    public static String getRandom6() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%06d", number);
    }
    
    public static int getRandom1() {
        Random rnd = new Random();
        int number = rnd.nextInt(9);
        return number;
    }
    
    public static int getRandomBetween(int min, int max) {
    	return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
    
    public static void writeToFile(String fileName, String stringToWrite) throws IOException {
    	BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
    	writer.write(stringToWrite);
    	writer.close();
    }
    
    
    public static String getFirstLetter(String str) {
    	
    	String letter1 = "";
    	
    	if (str.length() > 0) {
    		String s = str.substring(0, 1);
    		letter1 = s.toUpperCase();
    	}
    	else {
    		return letter1;
    	}
    	
    	return letter1;
    	
    }
    
    public static String getNextLetter(String str) {
    	
    	String letter1 = "";
    	
    	if (str.length() > 0) {
    		char ch = str.charAt(0);
    		int uc1 = (int) ch;
    		int uc2 = uc1 + 1;
    		letter1 = Character.toString((char)uc2);
    	}
    	else {
    		return letter1;
    	}
    	
    	return letter1;
    	
    }
    
    public static String getPrevLetter(String str) {
    	
    	String letter1 = "";
    	
    	if (str.length() > 0) {
    		char ch = str.charAt(0);
    		int uc1 = (int) ch;
    		int uc2 = uc1 - 1;
    		letter1 = Character.toString((char)uc2);
    	}
    	else {
    		return letter1;
    	}
    	
    	return letter1;
    	
    }
    
    /**
     * Convenience method to tell an empty value.
     * 
     * @param str
     * @return
     */
    public static boolean isReallyEmpty(String str) {
	   
	   if (str != null && !str.isEmpty()) {
		   return false;
	   }
	   
	   return true;
	   
    }
    
    
    public static boolean isNotEmpty(String str) {
 	   
 	   if (str != null && !str.isEmpty()) {
 		   return true;
 	   }
 	   
 	   return false;
 	   
     }
    
    
    public static <String, Integer extends Comparable<? super Integer>> Map<String, Integer> sortByValue(Map<String, Integer> map) {
        
		List<Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(Entry.comparingByValue());

        Map<String, Integer> result = new LinkedHashMap<>();
        for (Entry<String, Integer> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }
    
    
    public static <String, Integer extends Comparable<? super Integer>> Map<String, Integer> sortByValueDesc(Map<String, Integer> map) {
        
		List<Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(Entry.comparingByValue(Comparator.reverseOrder()));

        Map<String, Integer> result = new LinkedHashMap<>();
        for (Entry<String, Integer> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }
    
    
 
    
    
    
    
    public void readFile() throws FileNotFoundException {
    	
        Scanner scan = new Scanner(new File("C:\\Users\\ted00\\tw-test-db-profiles-1.txt"));
        ArrayList<String> firstNames = new ArrayList<String>();
        ArrayList<String> lastNames = new ArrayList<String>();
        ArrayList<String> emails = new ArrayList<String>();
        ArrayList<String> cities = new ArrayList<String>();
        ArrayList<String> states = new ArrayList<String>();

        while (scan.hasNext()) {
        	
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
            
        }
        
        System.out.println(firstNames);
        System.out.println(lastNames);
        System.out.println(emails);
        System.out.println(cities);
        System.out.println(states);
        
        scan.close();
        
    }
    
    //public static getMeasurableSkillFromIndex
    
}

