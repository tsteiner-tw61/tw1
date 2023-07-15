package com.teamwrkr.app.dto;

import java.util.Date;

/**
 * This class represents a survey sent to a former
 * employer of a registered Teamwrkr user.
 * 
 * @author Ted E. Steiner
 * @date 4.1.2023
 *
 */
public class Survey {
	
	
	private int surveyId;
	private int twid;
	private int missionId;
	private String title;
	private String desc;
	private String userEmail;
	private String mgrEmail;
	private String hardskill1;
	private String hardskill2;
	private String hardskill3;
	private String hardskill4;
	private String hardskill5;
	private String softskill1;
	private String softskill2;
	private String softskill3;
	private String softskill4;
	private String softskill5;
	private int scoreh1;
	private int scoreh2;
	private int scoreh3;
	private int scoreh4;
	private int scoreh5;
	private int scores1;
	private int scores2;
	private int scores3;
	private int scores4;
	private int scores5;
	private int scoreoverall;
	private String comments;
	private Date startDate;
	private Date endDate;
	private Date completeDate;
	
	
	
	public Survey() { }
	
	public Survey(Survey survey) {
		
		this.surveyId = survey.getMissionId();
		this.twid = survey.getTwid();
		this.missionId = survey.getMissionId();
		this.title = survey.getTitle();
		this.desc = survey.getDesc();
		this.userEmail = survey.getUserEmail();
		this.mgrEmail = survey.getMgrEmail();
		this.scoreh1 = survey.getScoreh1();
		this.scoreh2 = survey.getScoreh2();
		this.scoreh3 = survey.getScoreh3();
		this.scoreh4 = survey.getScoreh4();
		this.scoreh5 = survey.getScoreh5();
		this.scores1 = survey.getScores1();
		this.scores2 = survey.getScores2();
		this.scores3 = survey.getScores3();
		this.scores4 = survey.getScores4();
		this.scores5 = survey.getScores5();
		this.scoreoverall = survey.getScoreoverall();
		this.hardskill1 = survey.getHardskill1();
		this.hardskill2 = survey.getHardskill2();
		this.hardskill3 = survey.getHardskill3();
		this.hardskill4 = survey.getHardskill4();
		this.hardskill5 = survey.getHardskill5();
		this.softskill1 = survey.getSoftskill1();
		this.softskill2 = survey.getSoftskill2();
		this.softskill3 = survey.getSoftskill3();
		this.softskill4 = survey.getSoftskill4();
		this.softskill5 = survey.getSoftskill5();
		this.startDate = survey.getStartDate();
		this.endDate = survey.getEndDate();
		this.comments = survey.getComments();
		this.completeDate = survey.getCompleteDate();
		
	}
	
	public int getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(int surveyId) {
        this.surveyId = surveyId;
    }
	
	public int getTwid() {
        return twid;
    }

    public void setTwid(int twid) {
        this.twid = twid;
    }
    
    public int getMissionId() {
        return missionId;
    }

    public void setMissionId(int missionId) {
        this.missionId = missionId;
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
    
    public String getMgrEmail() {
        return mgrEmail;
    }

    public void setMgrEmail(String mgrEmail) {
        this.mgrEmail = mgrEmail;
    }
    
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
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
    
    public String getHardskill4() {
        return hardskill4;
    }

    public void setHardskill4(String hardskill4) {
        this.hardskill4 = hardskill4;
    }
    
    public String getHardskill5() {
        return hardskill5;
    }

    public void setHardskill5(String hardskill5) {
        this.hardskill5 = hardskill5;
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
    
    public String getSoftskill4() {
        return softskill4;
    }

    public void setSoftskill4(String softskill4) {
        this.softskill4 = softskill4;
    }
    
    public String getSoftskill5() {
        return softskill5;
    }

    public void setSoftskill5(String softskill5) {
        this.softskill5 = softskill5;
    }
    
    public int getScoreh1() {
        return scoreh1;
    }

    public void setScoreh1(int scoreh1) {
        this.scoreh1 = scoreh1;
    }
    
    public int getScoreh2() {
        return scoreh2;
    }

    public void setScoreh2(int scoreh2) {
        this.scoreh2 = scoreh2;
    }
    
    public int getScoreh3() {
        return scoreh3;
    }

    public void setScoreh3(int scoreh3) {
        this.scoreh3 = scoreh3;
    }
    
    public int getScoreh4() {
        return scoreh4;
    }

    public void setScoreh4(int scoreh4) {
        this.scoreh4 = scoreh4;
    }
    
    public int getScoreh5() {
        return scoreh5;
    }

    public void setScoreh5(int scoreh5) {
        this.scoreh5 = scoreh5;
    }
    
    public int getScores1() {
        return scores1;
    }

    public void setScores1(int scores1) {
        this.scores1 = scores1;
    }
    
    public int getScores2() {
        return scores2;
    }

    public void setScores2(int scores2) {
        this.scores2 = scores2;
    }
    
    public int getScores3() {
        return scores3;
    }

    public void setScores3(int scores3) {
        this.scores3 = scores3;
    }
    
    public int getScores4() {
        return scores4;
    }

    public void setScores4(int scores4) {
        this.scores4 = scores4;
    }
    
    public int getScores5() {
        return scores5;
    }

    public void setScores5(int scores5) {
        this.scores5 = scores5;
    }
    
    public int getScoreoverall() {
        return scoreoverall;
    }

    public void setScoreoverall(int scoreoverall) {
        this.scoreoverall = scoreoverall;
    }
    
    public Date getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(Date completeDate) {
        this.completeDate = completeDate;
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
    
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

}

