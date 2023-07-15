package com.teamwrkr.app.manager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.teamwrkr.app.dto.Mission;
import com.teamwrkr.app.dto.Notification;
import com.teamwrkr.app.service.DataService;
import com.teamwrkr.app.util.SiteConstants;
import com.teamwrkr.app.util.SqlUtils;

public class NotificationManager {
	
	public NotificationManager() { }
	
	// notifications arise when
	// ijm posted job
	//pjs
	
	// notificatons become inactive disabled when
	// when acknowledged
	// when expired+
	
	// how long should they be visible in any state
	// should we decide or let the users have a say
	
	
	public static List<Notification> getAllActiveNotificatonsFor(int twid) {
		
		List<Notification> notifications = new ArrayList<Notification>();
		
		
		return notifications;
		
	}
	
	
	/**
	 * Get every Notification in the entire system.
	 * @return
	 */
	public static List<Notification> getAllNotifications() {
		
		List<Notification> notifications = new ArrayList<Notification>();
		
		Notification no1 = new Notification();
		String qs = "twid1 > 0";
		
		Object[] nos = DataService.selectMany(no1, qs);
		for (Object no : nos) {
			Notification notification = (Notification) no;
			notifications.add(notification);
		}
		
		return notifications;
		
		
	}
	
	
	
	public static List<Notification> getDisplayedNotifications() {
		
		SiteConstants sc = new SiteConstants();
		
		List<Notification> notifications = new ArrayList<Notification>();
		
		Notification no1 = new Notification();
		String qs = "twid1 > 0";
		
		Object[] nos = DataService.selectMany(no1, qs);
		for (Object no : nos) {
			Notification notification = (Notification) no;
			
			
			if (isNotificationValid(notification, notification.getDaysValid())) {
				if (notification.getStatus() == sc.NOTIFICATION_STATUS_PENDING) {
					
				}
			}
			
			
			
			
			
			
			notifications.add(notification);
		}
		
		return notifications;
		
		
	}
	
	// deprecate
	public static boolean isNotificationValid(Notification n) {
		
		boolean valid = true;
		
		Date created = n.getCreatedDate();
		int time = n.getCreatedTime();
		int dValid = n.getDaysValid();
		
		Calendar cal = Calendar.getInstance();
		Date today = cal.getTime();
		
		int daysOld = today.compareTo(created);
		System.out.println("daysOld = " + daysOld);
		
		
		
		// get notification
		// get notification expire date
		// compare to now
		// if expired return false else return true
		
		return valid;
		
	}
	
	
	public static void expireNotification(Notification n) {
		
		// get notification
		// change status to expired
		
		
	}
	
	
	public static void acceptNotification(Notification n) {
		
		SiteConstants sc = new SiteConstants();

		if (n.getStatus() == sc.NOTIFICATION_STATUS_PENDING) {
			
			/*
			 * Used by DataService.update to selectOne and
			 * get the current values of the record as stored
			 * in the database.
			 */
			Notification reference = new Notification();
			reference.setNotId(n.getNotId());
			
			/*
			 * A second local object which contains all of the
			 * current values of the record as stored in the
			 * database.
			 */
			Notification update = (Notification) DataService.selectOne(reference);
			
			/*
			 * Now, update only the object fields that you wish
			 * to update, leaving all other fields the same.
			 */
			update.setStatus(sc.NOTIFICATION_STATUS_ACCEPTED);
			
			/*
			 * Create an updated object.
			 */
			Notification update2 = new Notification(update);

			/*
			 * Execute the update using update
			 */
			DataService.update(update2, reference);
			
			/*
			 * Be sure to get a local object copy that 
			 * reflects the updated values resulting from the
			 * previous update statement above.
			 */
			Notification updated = (Notification) DataService.selectOne(reference);
			
			/*
			 * 
			 */
			ContactManager.addFreelancerContact(updated);
			
		}

	}
	
	public static void rejectNotification(Notification n) {
		
		SiteConstants sc = new SiteConstants();

		if (n.getStatus() == sc.NOTIFICATION_STATUS_PENDING) {
			Notification notCurrentRecord = new Notification();
			notCurrentRecord.setNotId(n.getNotId());
			Notification notFieldUpdates = new Notification();
			notFieldUpdates.setNotId(n.getNotId());
			notFieldUpdates.setStatus(sc.NOTIFICATION_STATUS_REJECTED);
			DataService.update(notFieldUpdates, notCurrentRecord);
		}
		
	}
	
	
	/**
	 * Determine the validity of a Notification, which can mean
	 * several things depending on the context of the criteria
	 * against which it is measured.
	 * 
	 * @param n
	 * @return
	 */
	public static boolean isNotificationValid(Notification n, int days) {
		
		boolean visible = true;
		
		Calendar cal = Calendar.getInstance();
		Date today = cal.getTime();
		int now = (today.getHours() * 100) + (today.getMinutes());
		
		Date dateCreated = n.getCreatedDate();
		int timeCreated = n.getCreatedTime();
		
		int daysElapsed = SqlUtils.daysBetweenDates(dateCreated, today);
		
		if (now < timeCreated) {
			daysElapsed = daysElapsed - 1;
		}
		
		if (daysElapsed >= days) {
			visible = false;
		}
		
		return visible;
	}
	
	
	public static boolean isNotificationEnabled(Notification n) {
		
		boolean enabled = true;
		
		SiteConstants sc = new SiteConstants();
				
		boolean valid = isNotificationValid(n, n.getDaysValid());
		
		
		
		return enabled;
		
	}
	
	
	public static boolean isNotificationVisible(Notification n) {
		
		boolean visible = isNotificationValid(n, 5);
		
		return visible;
		
	}
	
	
	
	
	public static void validateNotification(Notification n) {
		
		SiteConstants sc = new SiteConstants();
		
		/*
		 * Check if the notification has gone stale.
		 * If so, update the status so that it can be 
		 * processed correctly the next time it is accessed
		 * by the application code.
		 */
		boolean valid = isNotificationValid(n, n.getDaysValid());
		if (!valid) {
			if (n.getStatus() != sc.NOTIFICATION_STATUS_EXPIRED) {
				Notification notCurrentRecord = new Notification();
				notCurrentRecord.setNotId(n.getNotId());
				Notification notFieldUpdates = new Notification();
				notFieldUpdates.setStatus(sc.NOTIFICATION_STATUS_EXPIRED);
				DataService.update(notFieldUpdates, notCurrentRecord);
				
			}
		}
		
	}
	
	
	/**
	 * Takes a Mission object and creates an HTML table which
	 * will likely be used in the Notification view in the
	 * window where the actual body of the Notification message
	 * is read.
	 * 
	 * @param m
	 * @return
	 */
	public static String createMissionHTMLTable(Mission m) {
		
		String HTML = "";
		
		String compText = "";
		if (m.getType() == 0) {
			compText = "hourly";
		}
		if (m.getType() == 1) {
			compText = "upon completion";
		}
		if (m.getType() == 2) {
			compText = "unknown";
		}
		
		String skillIp1 = "";
		String skillIp2 = "";
		String skillIp3 = "";
		
		if (m.getSoftskill1() != null && !m.getSoftskill1().isBlank()) {
			skillIp1 = m.getSoftskill1();
		}
		if (m.getSoftskill2() != null && !m.getSoftskill2().isBlank()) {
			skillIp2 = m.getSoftskill2();
		}
		if (m.getSoftskill3() != null && !m.getSoftskill3().isBlank()) {
			skillIp3 = m.getSoftskill3();
		}
		
		String skillM1 = "";
		String skillM2 = "";
		String skillM3 = "";
		
		if (m.getHardskill1() != null && !m.getHardskill1().isBlank()) {
			skillM1 = m.getHardskill1();
		}
		if (m.getHardskill2() != null && !m.getHardskill2().isBlank()) {
			skillM2 = m.getHardskill2();
		}
		if (m.getHardskill3() != null && !m.getHardskill3().isBlank()) {
			skillM3 = m.getHardskill3();
		}
		
		
		String table = "<table class=\"notice-ms-table\">\r\n"
		+ "<caption>Opportunity Details</caption>\r\n"
		+ "<tr>\r\n"
		+ "<th style=\"width: 50%; color: #9f5cd2;\">Item</th>\r\n"
		+ "<th style=\"width: 50%; color: #cc96f6;\">Value</th>\r\n"
		+ "</tr>\r\n"
		+ "<tr>\r\n"
		+ "<td class=\"notice-ms-table-td1\">Title</td>\r\n"
		+ "<td class=\"notice-ms-table-td2\">" + m.getTitle() + "</td>\r\n"
		+ "</tr>\r\n"
		+ "<tr>\r\n"
		+ "<td class=\"notice-ms-table-td1\">Description</td>\r\n"
		+ "<td class=\"notice-ms-table-td2\">" + m.getDesc() + "</td>\r\n"
		+ "</tr>\r\n"
		+ "<tr>\r\n"
		+ "<td class=\"notice-ms-table-td1\">Focus</td>\r\n"
		+ "<td class=\"notice-ms-table-td2\">" + m.getCategory1() + "</td>\r\n"
		+ "</tr>\r\n"
		+ "<tr>\r\n"
		+ "<td class=\"notice-ms-table-td1\">Specialty</td>\r\n"
		+ "<td class=\"notice-ms-table-td2\">" + m.getCategory2() + "</td>\r\n"
		+ "</tr>\r\n"
		+ "<tr>\r\n"
		+ "<td class=\"notice-ms-table-td1\">Start Date</td>\r\n"
		+ "<td class=\"notice-ms-table-td2\">" + m.getStartDate() + "</td>\r\n"
		+ "</tr>\r\n"
		+ "<tr>\r\n"
		+ "<td class=\"notice-ms-table-td1\">Rate (USD)</td>\r\n"
		+ "<td class=\"notice-ms-table-td2\">$" + m.getRate() + "</td>\r\n"
		+ "</tr>\r\n"
		+ "<tr>\r\n"
		+ "<td class=\"notice-ms-table-td1\">Compensation Type</td>\r\n"
		+ "<td class=\"notice-ms-table-td2\">" + compText + "</td>\r\n"
		+ "</tr>\r\n"
		+ "<tr>\r\n"
		+ "<td class=\"notice-ms-table-td1\">Desired Interpersonal Skills</td>\r\n"
		+ "<td class=\"notice-ms-table-td2\">\r\n"
		+ skillIp1 + "<br>\r\n"
		+ skillIp2 + "<br>\r\n"
		+ skillIp3 + "\r\n"
		+ "</td>\r\n"
		+ "</tr>\r\n"
		+ "<tr>\r\n"
		+ "<td class=\"notice-ms-table-td1\">Desired Measurable Skills</td>\r\n"
		+ "<td class=\"notice-ms-table-td2\">\r\n"
		+ skillM1 + "<br>\r\n"
		+ skillM2 + "<br>\r\n"
		+ skillM3 + "\r\n"
		+ "</td>\r\n"
		+ "</tr>\r\n"
		+ "</table>";
		
		
		return table;
		
		
		
	}
	
	
	/**
	 * Creates a templated message for a job match generated via the 
	 * Intelligent Job Matching system.
	 * 
	 * @param subject
	 * @param mission
	 * @return
	 */
	public static String createMessageHTML(String subject, Mission mission) {
		
		String HTML = "";
		
		String header = "<h3></h3>";
		
		String textInstructions = "You are receiving this notification because you have been identified as the best available" +
									"match for a job posted by another member of the Teamwrkr community. Please review the details " +
									"below, and if you wish to find out more about this position, please click the Accept button above. " +
									"Otherwise, please select the Reject button, so another member of the community can be presented with " +
									"the opportunity to fill this role. If you take no action, the offer will expire 24 hours after it was " +
									"originally presented to you.";
		
		String textInstructions2 = "By clicking Accept, you are not committing to filling the role, only to engaging in further " +
									"communication with the community member who posted the role on our site. If you accept, the " +
									"notification above will be deleted, and the job posters contact information will be added to your " +
									"Teamwrkr contacts, which can be viewed by click the WRK tab in the main menu. From that screen, you " +
									"will be provided with several ways to directly contact him/her to discuss the position further.";
		
		String instructions = "<div class=\"notice-inst\"></div>";
		
		String body = "<div class=\"notice-body\"></div>";
		
		String footer = "<div class=\"notice-footer\"></div>";
		
		
		
		return HTML;
		
	}
	


}
