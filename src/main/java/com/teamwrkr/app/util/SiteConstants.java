package com.teamwrkr.app.util;

/**
 * Class for constants that need description for
 * code clarity.
 * 
 * @author Ted E. Steiner
 * @date 3.18.2023
 *
 */
public class SiteConstants {
	
	
	public final int MISSION_TYPE_WORK_EXP = 1;
	public final int MISSION_TYPE_JOB_POST = 2;
	
	/*
	 * ----- NOT BEING USED IN REV 1.0 -----
	 */
	public final int MISSION_TYPE_DESIRED = 3;
	
	public final int MISSION_WORK_EXP_STATUS_SENT = 100;
	public final int MISSION_WORK_EXP_STATUS_VERIFIED = 101;
	
	public final int MISSION_JOB_POST_STATUS_POSTED = 200;
	public final int MISSION_JOB_POST_STATUS_MATCHED = 201;
	public final int MISSION_JOB_POST_STATUS_ACCEPTED = 202;
	public final int MISSION_JOB_POST_STATUS_CONFIRMED = 203;
	public final int MISSION_JOB_POST_STATUS_COMPLETED = 204;
	public final int MISSION_JOB_POST_STATUS_VERIFIED = 205;
	public final int MISSION_JOB_POST_STATUS_PAID = 206;
	public final int MISSION_JOB_POST_STATUS_FEEDBACK_X1 = 207;
	public final int MISSION_JOB_POST_STATUS_FEEDBACK_X2 = 208;
	public final int MISSION_JOB_POST_STATUS_EXPIRED = 299;
	
	public final int MISSION_STATUS_ERROR = 0;
	public final int MISSION_STATUS_ENTERED = 1;
	public final int MISSION_STATUS_VERIFIED = 2;
	public final int MISSION_STATUS_POSTED = 3;
	public final int MISSION_STATUS_ACCEPTED = 4;
	public final int MISSION_STATUS_CONFIRMED = 5;
	public final int MISSION_STATUS_COMPLETED = 6;
	public final int MISSION_STATUS_PAID = 7;
	public final int MISSION_STATUS_TWFB = 10;
	public final int MISSION_STATUS_MGRFB = 20;
	public final int MISSION_STATUS_2FB = 30;
	public final int MISSION_STATUS_EXPIRED = 100;
	
	public final String URL_DEFAULT_FACEBOOK = "http://www.facebook.com";
	public final String URL_DEFAULT_TWITTER = "http://www.twitter.com";
	public final String URL_DEFAULT_INSTAGRAM = "http://www.instagram.com";
	public final String URL_DEFAULT_LINKEDIN = "http://www.linkedin.com";
	public final String URL_DEFAULT_INDEED = "http://www.indeed.com";
	public final String URL_DEFAULT_CANVA = "http://www.canva.com";
	
	public final int POINTS_TYPE_LOGIN = 1;
	public final int POINTS_TYPE_PROFILE = 2;
	public final int POINTS_TYPE_JOBS = 3;
	public final int POINTS_TYPE_CHAT = 4;
	
	public final int ACTION_POINTS_REGISTER = 1;
	public final int ACTION_POINTS_PROFILE_PHOTO = 2;
	public final int ACTION_POINTS_PROFILE_NICHE = 3;
	public final int ACTION_POINTS_PROFILE_URL = 4;
	public final int ACTION_POINTS_PROFILE_SKILLS = 5;
	public final int ACTION_POINTS_PROFILE_REVIEW_SEND = 6;
	public final int ACTION_POINTS_PROFILE_REVIEW_RECEIVE = 7;
	public final int ACTION_POINTS_PROFILE_LOGIN_X1 = 8;
	public final int ACTION_POINTS_PROFILE_LOGIN_X3 = 9;
	public final int ACTION_POINTS_PROFILE_LOGIN_X5 = 10;
	public final int ACTION_POINTS_PROFILE_LOGIN_X7 = 11;
	
	public final int NOTIFICATION_TYPE_JOB = 1;
	public final int NOTIFICATION_TYPE_SYSTEM = 2;

	public final int TX_TYPE_LOGIN = 1;
	public final int TX_TYPE_LOGOUT = 2;
	public final int TX_TYPE_POINTS = 3;
	
	public final int CONTACT_TYPE_FREELANCER = 1;
	public final int CONTACT_TYPE_POSTER = 2;
	
	public final int CONTACT_STAGE_ENGAGE = 1;
	public final int CONTACT_STAGE_PARTNER = 2;
	public final int CONTACT_STAGE_DELIVER = 3;
	public final int CONTACT_STAGE_NURTURE = 4;
	
	public final int NOTIFICATION_STATUS_PENDING = 0;
	public final int NOTIFICATION_STATUS_ACCEPTED = 1;
	public final int NOTIFICATION_STATUS_REJECTED = 2;
	public final int NOTIFICATION_STATUS_EXPIRED = 3;
	
	public final int MILLIS_PER_SECOND = 1000;
	public final int SECONDS_IN_MINUTE = 60;
	public final int MINUTES_IN_HOUR = 60;
	public final int HOURS_IN_DAY = 24;
	
	
	
	public SiteConstants() { }

}

