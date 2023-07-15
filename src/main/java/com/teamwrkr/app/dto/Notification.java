package com.teamwrkr.app.dto;

import java.util.Date;
import java.sql.Time;


/**
 * This class represents a single Notification.
 * It can be of either a job or system type for
 * Revision 1.0.
 * 
 * @author 		Ted E. Steiner
 * @date		06.26.2023
 */
public class Notification {
	
	
	private int notId;
	private int missionId;
	private int twid1;
	private int twid2;
	private int type;
	private int status;
	private String subject;
	private String message;
	private int createdTime;
	private Date createdDate;
	private int daysValid; 
	
	
	
	public Notification() { } 
	
	
	
	public Notification(Notification notification) {
		
		this.notId = notification.getNotId();
		this.missionId = notification.getMissionId();
		this.twid1 = notification.getTwid1();
		this.twid2 = notification.getTwid2();
		this.type = notification.getType();
		this.status = notification.getStatus();
		this.subject = notification.getSubject();
		this.message = notification.getMessage();
		this.createdTime = notification.getCreatedTime();
		this.createdDate = notification.getCreatedDate();		
		this.daysValid = notification.getDaysValid();
		
	}
	
	
	
	public int getNotId() {
        return notId;
    }

    public void setNotId(int notId) {
        this.notId = notId;
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
	
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    
    public int getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(int createdTime) {
        this.createdTime = createdTime;
    }
    
    public int getDaysValid() {
        return daysValid;
    }

    public void setDaysValid(int daysValid) {
        this.daysValid = daysValid;
    }
    
    

}
