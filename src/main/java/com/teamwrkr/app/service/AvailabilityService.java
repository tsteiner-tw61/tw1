package com.teamwrkr.app.service;

import com.teamwrkr.app.dto.Availability;


/**
 * THIS CLASS IS TO BE DEPRECATED.
 * @author ted00
 *
 */
public class AvailabilityService {
	
	
	
	public AvailabilityService() { }
	
	
	
	/**
	 * Calculate the number of days the user has opted to
	 * be viewed as available out of the next 28.
	 * 
	 * @return
	 */
	public static int getAvailableDays(int twid) {
		
		int availableDays = 0;
		
		Availability av = new Availability();
		av.setTwid(twid);
		
		Availability av1 = (Availability) DataService.selectOne(av);
		availableDays = av.getDaysAvailable();
		
		return availableDays;
	}
	

}

