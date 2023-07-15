package com.teamwrkr.app.manager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.teamwrkr.app.dto.Contact;
import com.teamwrkr.app.dto.Mission;
import com.teamwrkr.app.dto.Notification;
import com.teamwrkr.app.dto.Profile;
import com.teamwrkr.app.service.DataService;
import com.teamwrkr.app.util.SiteConstants;

public class ContactManager {
	
	public ContactManager() { }
	
	
	
	public static List<Contact> getAllContacts() {
		
		List<Contact> all = new ArrayList<Contact>();
		Contact c0 = new Contact();
		String qs = "twid2 > 0";
		
		Object[] contacts = DataService.selectMany(c0, qs);
		
		for (Object c : contacts) { 
			Contact contact = (Contact) c;
			all.add(contact);
		}
		
		return all;
		
	}
	
	
	/**
	 * Note here that each user gets a Contract record created
	 * in the DB regardless of the method used to initiate engagement.
	 * @param twid
	 * @return
	 */
	public static List<Contact> getAllContacts(int twid) {
		
		List<Contact> contactList = new ArrayList<Contact>();
		
		Contact c0 = new Contact();
		c0.setTwid2(twid);
		
		String qs = "twid2 = " + twid;
		
		Object[] contacts = DataService.selectMany(c0, qs);
		
		for (Object c : contacts) { 
			Contact contact = (Contact) c;
			contactList.add(contact);
		}
			
		return contactList;
			
	}
	
	
	
	//public static List<Contact> getAllActiveContacts(int twid) {
		
		//SiteConstants sc = new SiteConstants();
		
		//List<Contact> activeContacts = new ArrayList<Contact>();
		
		//List<Contact> allContacts = getAllContacts(twid);
		
		//for (Contact contact : allContacts) {
			//if (contact.getType() == sc.CONTACT_TYPE_ACTIVE) {
				//activeContacts.add(contact);
			//}
		//}
		
		//return activeContacts;
		
		
	//}
	
	
	
	public static void addFreelancerContact(Notification notification) {
		
		SiteConstants sc = new SiteConstants();
		
		/*
		 * Get the mission attached to the notification.
		 */
		Mission m0 = new Mission();
		m0.setMissionId(notification.getMissionId());
		Mission mission = (Mission) DataService.selectOne(m0);
		
		/*
		 * Get the job poster's profile.
		 */
		//Profile p0 = new Profile();
		//p0.setTwid(mission.getTwid1());
		
		
		/*
		 * Create the freelance contact.
		 * This is the contact that is displayed in the
		 * freelancer's account, with the contact info
		 * for the poster of the job who's associated
		 * notification the freelancer accepted.
		 */
		Contact c0 = new Contact();
		c0.setMissionId(notification.getMissionId());
		c0.setStage(sc.CONTACT_STAGE_ENGAGE);
		c0.setEmail(mission.getMgrEmail());
		c0.setTwid1(notification.getTwid1());
		c0.setTwid2(mission.getTwid1());
		c0.setFirstName(mission.getManager());
		c0.setCreatedDate(Calendar.getInstance().getTime());
		
		DataService.create(c0);
		
	}
	

}
