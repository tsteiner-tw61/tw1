package com.teamwrkr.app.dto;

import java.util.Date;


/**
 * This class represents a reference sent out to a
 * former employer for review via a Teamwrkr survey.
 * 
 * @author Ted E. Steiner
 * @date 2.22.2023
 *
 */
public class Mission {
	
	private int missionId;
	private int twid1;
	private int twid2;
	private int missionType;
	private String title;
	private String desc;
	private String category1;
	private String category2;
	private String hardskill1;
	private String hardskill2;
	private String hardskill3;
	private String softskill1;
	private String softskill2;
	private String softskill3;
	private String manager;
	private String mgrEmail;
	private Date startDate;
	private Date endDate;
	private int type;
	private int rate;
	private int length;
	private Date enteredDate;
	private Date referenceDate;
	private Date postedDate;
	private Date matchedDate;
	private Date acceptedDate;
	private Date confirmedDate;
	private Date completedDate;
	private Date verifiedDate;
	private Date paidDate;
	private Date feedbackDate1;
	private Date feedbackDate2;
	private Date expiresDate;
	
	
	
	
	public Mission() { }
	
	
	
	public Mission(Mission mission) {
		
		this.missionId = mission.getMissionId();
		this.twid1 = mission.getTwid1();
		this.twid2 = mission.getTwid2();
		this.missionType = mission.getMissionType();
		this.title = mission.getTitle();
		this.desc = mission.getDesc();
		this.category1 = mission.getCategory1();
		this.category2 = mission.getCategory2();
		this.hardskill1 = mission.getHardskill1();
		this.hardskill2 = mission.getHardskill2();
		this.hardskill3 = mission.getHardskill3();
		this.softskill1 = mission.getSoftskill1();
		this.softskill2 = mission.getSoftskill2();
		this.softskill3 = mission.getSoftskill3();
		this.manager = mission.getManager();
		this.mgrEmail = mission.getMgrEmail();
		this.startDate = mission.getStartDate();
		this.endDate = mission.getEndDate();
		this.type = mission.getType();
		this.rate = mission.getRate();
		this.length = mission.getLength();
		this.enteredDate = mission.getEnteredDate();
		this.referenceDate = mission.getReferenceDate();
		this.postedDate = mission.getPostedDate();
		this.matchedDate = mission.getMatchedDate();
		this.acceptedDate = mission.getAcceptedDate();
		this.confirmedDate = mission.getConfirmedDate();
		this.verifiedDate = mission.getVerifiedDate();
		this.completedDate = mission.getCompletedDate();
		this.paidDate = mission.getPaidDate();
		this.feedbackDate1 = mission.getFeedbackDate1();
		this.feedbackDate2 = mission.getFeedbackDate2();
		this.expiresDate = mission.getExpiresDate();
	}
	
	
	/**
	 * This allows a simple way to populate the
	 * mission stats in the dashboard.
	 */
	public int getStatus() {
		
		int status = 0;
		
		/*
		 * Processing for WRK experience entries.
		 *
		if (this.enteredDate != null) {
			status++;
			if (this.referenceDate != null) {status++;}
		}
		else {
			if (this.postedDate != null) {status = 3;}
			else {return 0;}
		}
		*/
		
		if (this.enteredDate != null) {status = 1;}
		if (this.referenceDate != null) {status = 2;}
		if (this.postedDate != null) {status = 3;}	
		
	
		/*
		 * Processing for new job postings.
		 */
		if (this.acceptedDate != null) {status++;}
		if (this.confirmedDate != null) {status++;}
		if (this.completedDate != null) {status++;}
		if (this.verifiedDate != null) {status++;}
		if (this.paidDate != null) {status++;}
		if (this.feedbackDate1 != null) {status = 10;}
		if (this.feedbackDate2 != null) {status = 20;}
		if (this.expiresDate != null) {status = 100;}
		
		return status;
		
	}
	
	public String getStatusText() {
		
		String text = "";
		
		/*
		 * Processing for WRK experience entries.
		 *
		if (this.enteredDate != null) {
			text = "Entered";
			if (this.referenceDate != null) {text = "Verified";}
		}
		else {
			if (this.postedDate != null) {text = "Posted";}
			else {return "";}
		}
		*/
		
		if (this.enteredDate != null) {text = "Entered on";}
		if (this.referenceDate != null) {text = "Verified on";}
		if (this.postedDate != null) {text = "Rejected (bad email address) on";}	
	
		/*
		 * Processing for new job postings.
		 */
		if (this.acceptedDate != null) {text = "Accepted";}
		if (this.confirmedDate != null) {text = "Confirmed";}
		if (this.completedDate != null) {text = "Completed";}
		if (this.verifiedDate != null) {text = "Verified Completed";}
		if (this.paidDate != null) {text = "Paid";}
		if (this.feedbackDate1 != null) {text = "Last feedback";}
		if (this.feedbackDate2 != null) {text = "Last feedback";}
		if (this.expiresDate != null) {text = "Expired";}
		
		return text;
		
	}
	
	public int getMissionId() {
        return missionId;
    }

    public void setMissionId(int missionId) {
        this.missionId = missionId;
    }
    
    
	
	public int getTwid1() {
        return twid1;
    }

    public void setTwid1(int twid1) {
        this.twid1 = twid1;
    }
    
    public int getTwid2() {
        return twid2;
    }

    public void setTwid2(int twid2) {
        this.twid2 = twid2;
    }
    
    public int getMissionType() {
        return missionType;
    }

    public void setMissionType(int missionType) {
        this.missionType = missionType;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    public String getCategory1() {
        return category1;
    }

    public void setCategory1(String category1) {
        this.category1 = category1;
    }
    
    public String getCategory2() {
        return category2;
    }

    public void setCategory2(String category2) {
        this.category2 = category2;
    }
    
    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }
    
    public String getMgrEmail() {
        return mgrEmail;
    }

    public void setMgrEmail(String mgrEmail) {
        this.mgrEmail = mgrEmail;
    }
    
    public String getHardskill1() {
        return hardskill1;
    }

    public void setHardskill1(String hardskill1) {
        this.hardskill1 = hardskill1;
    }
    
    public String getHardskill2() {
        return hardskill2;
    }

    public void setHardskill2(String hardskill2) {
        this.hardskill2 = hardskill2;
    }
    
    public String getHardskill3() {
        return hardskill3;
    }

    public void setHardskill3(String hardskill3) {
        this.hardskill3 = hardskill3;
    }
    
    public String getSoftskill1() {
        return softskill1;
    }

    public void setSoftskill1(String softskill1) {
        this.softskill1 = softskill1;
    }
    
    public String getSoftskill2() {
        return softskill2;
    }

    public void setSoftskill2(String softskill2) {
        this.softskill2 = softskill2;
    }
    
    public String getSoftskill3() {
        return softskill3;
    }

    public void setSoftskill3(String softskill3) {
        this.softskill3 = softskill3;
    }
    
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
    
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    
    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
    
    public Date getEnteredDate() {
        return enteredDate;
    }

    public void setEnteredDate(Date enteredDate) {
        this.enteredDate = enteredDate;
    }
    
    public Date getReferenceDate() {
        return referenceDate;
    }

    public void setReferenceDate(Date referenceDate) {
        this.referenceDate = referenceDate;
    }
    
    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }
    
    public Date getMatchedDate() {
        return matchedDate;
    }

    public void setMatchedDate(Date matchedDate) {
        this.matchedDate = matchedDate;
    }
    
    public Date getAcceptedDate() {
        return acceptedDate;
    }

    public void setAcceptedDate(Date acceptedDate) {
        this.acceptedDate = acceptedDate;
    }
    
    public Date getConfirmedDate() {
        return confirmedDate;
    }

    public void setConfirmedDate(Date confirmedDate) {
        this.confirmedDate = confirmedDate;
    }
    
    public Date getVerifiedDate() {
        return verifiedDate;
    }

    public void setVerifiedDate(Date verifiedDate) {
        this.verifiedDate = verifiedDate;
    }
    
    public Date getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(Date completedDate) {
        this.completedDate = completedDate;
    }
    
    public Date getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(Date paidDate) {
        this.paidDate = paidDate;
    }
    
    public Date getFeedbackDate1() {
        return feedbackDate1;
    }

    public void setFeedbackDate1(Date feedbackDate1) {
        this.feedbackDate1 = feedbackDate1;
    }
    
    public Date getFeedbackDate2() {
        return feedbackDate2;
    }

    public void setFeedbackDate2(Date feedbackDate2) {
        this.feedbackDate2 = feedbackDate2;
    }
    
    
    
    public Date getExpiresDate() {
        return expiresDate;
    }

    public void setExpiresDate(Date expiresDate) {
        this.expiresDate = expiresDate;
    }

}

