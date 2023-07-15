package com.teamwrkr.app.dto;

import java.util.Date;

public class Testimonial {
	
	
	private int testId;
	private int twid1;
	private int reviewerTwid;
	private String subject;
	private String desc;
	private int stars;
	private String reviewerFirst;
	private String reviewerLast;
	private String reviewerEmail;
	private String reviewerCompany;
	private Date startDate;
	private Date endDate;
	private Date enteredDate;
	
	
	public Testimonial() { }
	
	
	public Testimonial(Testimonial testimonial) {
		
		this.testId = testimonial.getTestId();
		this.twid1 = testimonial.getTwid1();
		this.reviewerTwid = testimonial.getReviewerTwid();
		this.subject = testimonial.getSubject();
		this.desc = testimonial.getDesc();
		this.stars = testimonial.getStars();
		this.reviewerFirst = testimonial.getReviewerFirst();
		this.reviewerLast = testimonial.getReviewerLast();
		this.reviewerEmail = testimonial.getReviewerEmail();
		this.reviewerCompany = testimonial.getReviewerCompany();
		this.startDate = testimonial.getStartDate();
		this.endDate = testimonial.getEndDate();
		this.enteredDate = testimonial.getEnteredDate();
		
	}
	
	
	public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId  = testId;
    }
    
    public int getTwid1() {
        return twid1;
    }

    public void setTwid1(int twid1) {
        this.twid1  = twid1;
    }
    
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject  = subject;
    }
    
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc  = desc;
    }
    
    public int getReviewerTwid() {
        return reviewerTwid;
    }

    public void setReviewerTwid(int reviewerTwid) {
        this.reviewerTwid  = reviewerTwid;
    }
    
    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars  = stars;
    }
    
    public String getReviewerFirst() {
        return reviewerFirst;
    }

    public void setReviewerFirst(String reviewerFirst) {
        this.reviewerFirst  = reviewerFirst;
    }
    
    public String getReviewerLast() {
        return reviewerLast;
    }

    public void setReviewerLast(String reviewerLast) {
        this.reviewerLast  = reviewerLast;
    }
    
    public String getReviewerEmail() {
        return reviewerEmail;
    }

    public void setReviewerEmail(String reviewerEmail) {
        this.reviewerEmail  = reviewerEmail;
    }
    
    public String getReviewerCompany() {
        return reviewerCompany;
    }

    public void setReviewerCompany(String reviewerCompany) {
        this.reviewerCompany  = reviewerCompany;
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

    public Date getEnteredDate() {
        return enteredDate;
    }

    public void setEnteredDate(Date enteredDate) {
        this.enteredDate = enteredDate;
    }
    
}
