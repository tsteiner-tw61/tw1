package com.teamwrkr.app.service;

import java.util.Calendar;
import java.util.Date;

import com.teamwrkr.app.dto.Mission;
import com.teamwrkr.app.util.SiteConstants;

public class MissionService {
	
	
	public MissionService() { }
	
	
	
	/**
	 * Return a preformed short message for display to describe
	 * in laymans terms the workflow status of the Mission.
	 * 
	 * @param mission
	 * @return String msg
	 */
	public static String getJobStatusMessage (Mission mission) {
		
		SiteConstants sc = new SiteConstants();
		String msg = "";
		
		/*
		 * Reference request.
		 */
		if (mission.getMissionType() == sc.MISSION_TYPE_WORK_EXP) {
			if (mission.getEnteredDate() != null) {
				if (mission.getReferenceDate() != null) {
					msg = "REFERENCE REQUEST RECEIVED";
				}
				else {
					msg = "REFERENCE REQUEST PENDING";
				}
			}
		}
		
		

		return msg;
		
	}
	
	
	
	/**
	 * Get the action verb for the mission history summary entry.
	 * 
	 * @param mission
	 * @return
	 */
	public static String getActionMessage(Mission mission) {
		
		SiteConstants sc = new SiteConstants();
		
		String msg = "";
		
		if (mission.getMissionType() == sc.MISSION_TYPE_WORK_EXP) {
			if (mission.getEnteredDate() != null) {
				if (mission.getReferenceDate() != null) {
					msg = "RECEIVED";
				}
				else {
					msg = "REQUESTED";
				}
			}
		}
		return msg;
	}
		/*
		if (mission.getMissionType() == sc.MISSION_TYPE_JOB_POST) {
			
			if (mission.getPostedDate() != null) {
				msg = "POSTED";
				
				if (mission.getAcceptedDate() != null) {
					msg = "IN NEGOTIATIONS";
				}
				if (mission.getConfirmedDate() != null) {
					msg = "FILLED";
				}
				if (mission.getCompletedDate() != null) {
					msg = "COMPLETED";
				}
				if (mission.getVerifiedDate() != null) {
					msg = "APPROVED";
				}
				if (mission.getPaidDate() != null) {
					msg = "PAID IN FULL";
					 provide link to feedback 
				}
				
				Date today = Calendar.getInstance().getTime();
				if (mission.getExpiresDate().compareTo(today) <= 0) {
					msg = "POSTING EXPIRED";
				}
				
				
			}*/

		//}
		
		//return msg;
		
	//}
	
	
	
	
	/**
	 * Returns the total number of Missions with completed reviews.
	 * Therefore, only Missions of the reference type are counted here.
	 * 
	 * @param twid
	 * @return int done
	 */
	public static int missionsComplete(int twid) {
		
		SiteConstants sc = new SiteConstants();
		
		int done = 0;
		
		Mission m1 = new Mission();
		Object[] missions = DataService.selectMany(m1, "twid1 = " + twid);
		for (Object missObj : missions) {
			Mission mission = (Mission) missObj;
			if (mission.getMissionType() == sc.MISSION_TYPE_WORK_EXP) {
				if (mission.getEnteredDate() != null) {
					if (mission.getReferenceDate() != null) {
						done = done + 1;
					}
				}
			}
			/*
			if (mission.getMissionType() == sc.MISSION_TYPE_JOB_POST) {
				
			}
			*/
		}
		
		return done;
		
	}
	
	
	
	/**
	 * Get the overall workflow status position for the mission
	 * history entry in question.
	 * 
	 * @param mission
	 * @return int status
	 */
	public static int getMissionStatus(Mission mission) {
		
		SiteConstants sc = new SiteConstants();
		
		int status = 0;
		
		/*
		 * Reference request.
		 */
		if (mission.getMissionType() == sc.MISSION_TYPE_WORK_EXP) {
			if (mission.getEnteredDate() != null) {
				if (mission.getReferenceDate() != null) {
					status = sc.MISSION_WORK_EXP_STATUS_VERIFIED;
				}
				else {
					status = sc.MISSION_WORK_EXP_STATUS_SENT;
				}
			}
		}
		
		/*
		 * Job posting.
		 */
		Date today = Calendar.getInstance().getTime();
		int jobPostWorkflowStep = 1;
		if (mission.getMissionType() == sc.MISSION_TYPE_JOB_POST) {
			
			if (mission.getPostedDate() != null) {
				status = sc.MISSION_JOB_POST_STATUS_POSTED;
				if (mission.getAcceptedDate() != null) {
					jobPostWorkflowStep = jobPostWorkflowStep + 1;
					status = sc.MISSION_JOB_POST_STATUS_ACCEPTED;
				}
				if (mission.getConfirmedDate() != null) {
					jobPostWorkflowStep = jobPostWorkflowStep + 1;
					status = sc.MISSION_JOB_POST_STATUS_CONFIRMED;
				}
				if (mission.getCompletedDate() != null) {
					jobPostWorkflowStep = jobPostWorkflowStep + 1;
					status = sc.MISSION_JOB_POST_STATUS_COMPLETED;
				}
				if (mission.getVerifiedDate() != null) {
					jobPostWorkflowStep = jobPostWorkflowStep + 1;
					status = sc.MISSION_JOB_POST_STATUS_VERIFIED;
				}
				if (mission.getPaidDate() != null) {
					jobPostWorkflowStep = jobPostWorkflowStep + 1;
					status = sc.MISSION_JOB_POST_STATUS_PAID;
					/* provide link to feedback */
				}
				
				if (mission.getExpiresDate().compareTo(today) <= 0 && jobPostWorkflowStep < 2) {
					status = sc.MISSION_JOB_POST_STATUS_EXPIRED;
				}
				
			}
			
		}
		
		return status;
		
	}
	
		

}
