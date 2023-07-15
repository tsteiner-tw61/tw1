package com.teamwrkr.app.util;

public class ContactUtils {
	
	
	public ContactUtils() { }
	
	
	public static String translateStage(int stageValue) {
		
		String contactStage = "ENGAGED";
		
		if (stageValue <= 1) {contactStage = "ENGAGED";}
		if (stageValue == 2) {contactStage = "PARTNERED";}
		if (stageValue == 3) {contactStage = "DELIVERING";}
		if (stageValue == 4) {contactStage = "NURTURING";}
		
		return contactStage;
		
	}

}
