package com.teamwrkr.app.dto;


/**
 * This class specifies the dates for the
 * Availability class.
 * 
 * @author Ted E. Steiner
 * @date 3.15.2023
 *
 */
public class CalInfo {
	
	//private int calId;
	private int twid;
	private int startYear;
	private int startMonth;
	private int startDate;

	
	public CalInfo() { }	
	
	
	
	public CalInfo(CalInfo calInfo) {
		
		this.twid = calInfo.getTwid();
		this.startYear = calInfo.getStartYear();
		this.startMonth = calInfo.getStartMonth();
		this.startDate = calInfo.getStartDate();
		
	}
	
	
	
	public CalInfo(int twid) {
		
		this.twid = twid;
		
	}

	
	
	public CalInfo(int startYear, int startMonth, int startDate) {
		
		this.startYear = startYear;
		this.startMonth = startMonth;
		this.startDate = startDate;
		
	}
	
	
	
	//public int getCalId() {
        //return calId;
    //}
    //public void setCalId(int calId) {
        //this.calId = calId;
    //}
	
    
	public int getTwid() {
        return twid;
    }
    public void setTwid(int twid) {
        this.twid = twid;
    }
    
    
    public int getStartYear() {
        return startYear;
    }
    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }
    
    
    public int getStartMonth() {
        return startMonth;
    }
    public void setStartMonth(int startMonth) {
        this.startMonth = startMonth;
    }
    
    
    public int getStartDate() {
        return startDate;
    }
    public void setStartDate(int startDate) {
        this.startDate = startDate;
    }
	
	
	
}

