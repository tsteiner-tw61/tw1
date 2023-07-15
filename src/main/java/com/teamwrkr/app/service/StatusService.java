package com.teamwrkr.app.service;

import java.lang.reflect.Field;

import com.teamwrkr.app.dto.Profile;
import com.teamwrkr.app.dto.Status;

public class StatusService {
	
	
	public StatusService() { } 
	
	

	/**
	 * Creates an empty Status table object for
	 * a new user Profile.
	 * 
	 * @param profile
	 */
	public static void createStatus(Profile profile) {
		
		Status status = new Status();
		status.setTwid(profile.getTwid());
		status.setKey1("vc");
		status.setValue1("0");
		status.setKey2("wv");
		status.setValue2("0");
		DataService.create(status, false);
		
	}
	
	
	/**
	 * Select a Status table for a particular user Profile.
	 * 
	 * @param profile
	 * @return Status
	 */
	public static Status selectStatus(Profile profile) {
		
		Status status = new Status();
		status.setTwid(profile.getTwid());
		Status statusObj = (Status) DataService.selectOne(status);
		
		return statusObj;
		
	}
	
	
	/**
	 * Gets the status value for the given key (category).
	 * 
	 * @param twid
	 * @param category
	 * @return value
	 */
	public static String getStatusKeyValue(int twid, String category) {
		
		String value = "";
		
		Status status = new Status();
		status.setTwid(twid);
		Status s0 = (Status) DataService.selectOne(status);
		
		/*
		 * ----- 1. VC = VERIFICATION CODE -----
		 * nnnnnn:		verification code sent to user email address
		 * 0:			no verification code sent to user email address
		 */
		if (category.equals("vc")) {
			value = s0.getValue1();
		}
		
		/*
		 * ----- 2. WV = WELCOME VIDEO -----
		 * 1:			user has seen welcome video popup - hide
		 * 0:			user has not seen welcome video popup - show
		 */
		if (category.equals("wv")) {
			value = s0.getValue2();
		}
		
		return value;
		
	}
	
	
	/**
	 * This may be deprecated.
	 * 
	 * @param status
	 * @param value
	 * @return
	 */
	public static String findEmptyStatusKey(Status status, String value) {
		
		try {
			
			Class c = status.getClass();
			Field[] fields = c.getDeclaredFields();
			
			for (int i = 0; i < fields.length; i++) {
				fields[i].setAccessible(true);
			}
			
			for (int i = 0; i < fields.length; i++) {
				String fieldName = fields[i].getName();
				String fieldValue = (String) fields[i].get(status);
				if (fieldValue == null) {
					return fieldName;
				}
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	
	/**
	 * Adds a new status category, along with its value,
	 * to the existing Status table object for a given user
	 * Profile.
	 * 
	 * @param profile
	 * @param s0
	 * @param field
	 * @param category
	 * @param value
	 */
	public static void createStatusKey(int twid, String category, String value) {
		
		try {
			
			Status status = new Status();
			status.setTwid(twid);
			Status s0 = (Status) DataService.selectOne(status);
			
			
			Class c = s0.getClass();
			Field[] fields = c.getDeclaredFields();
			
			for (int i = 0; i < fields.length; i++) {
				fields[i].setAccessible(true);
			}
			
			for (int i = 1; i < fields.length; i++) {
				String fieldName = fields[i].getName();
				String fieldValue = (String) fields[i].get(s0);
				if (fieldValue == null || fieldValue.equals("null")) {
					fields[i].set(s0, category);
					fields[i+1].set(s0, value);
					break;
				}
			}
			
			
			Status s2 = new Status(s0);
			
			Status s1 = new Status();
			s1.setTwid(twid);
			
			DataService.update(s2, s1);
			
		}
		catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		
		
	}
	
	
	
	public static void updateStatusKey(int twid, String category, String value) {
		
		try {
			
			Status status = new Status();
			status.setTwid(twid);
			Status s0 = (Status) DataService.selectOne(status);
			
			
			Class c = s0.getClass();
			Field[] fields = c.getDeclaredFields();
			
			for (int i = 0; i < fields.length; i++) {
				fields[i].setAccessible(true);
			}
			
			for (int i = 1; i < fields.length; i++) {
				String fieldName = fields[i].getName();
				System.out.println("fn=" + fieldName);
				String fieldValue = (String) fields[i].get(s0);
				if (fieldValue == null || fieldValue.equals("null")) {
					break;
				}
				System.out.println("fv=" + fieldValue);
				
				if (fieldValue.equals(category)) {
					fields[i+1].set(s0, value);
				}
				
			}
			
			
			Status s2 = new Status(s0);
			
			Status s1 = new Status();
			s1.setTwid(twid);
			
			DataService.update(s2, s1);
			
		}
		catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		
		
	}
	
	
	/**
	 * The map of category keys in the Status table.
	 * Identifies which table field is to be updated
	 * when a given status item changes for a particular 
	 * user Profile.
	 * 
	 * @param category
	 * @return String
	 */
	private static String getKeyField(String category) {
		
		String fieldName = "";
		
		if (category.equals("vc")) {fieldName = "key1";}
		if (category.equals("wv")) {fieldName = "key2";}
		
		return fieldName;
		
	}
	
	
	/**
	 * Identifies the corresponding value field for the
	 * given key category field, which is necessary info
	 * to update the database table.
	 * 
	 * @param category
	 * @return String
	 */
	private static String getValueField(String category) {
		
		String fieldName = "";
		
		if (category.equals("vc")) {fieldName = "value1";}
		if (category.equals("wv")) {fieldName = "value2";}
		
		return fieldName;
		
	}
	
	
	

}
