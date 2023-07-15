package com.teamwrkr.app.web;

import com.teamwrkr.app.dto.*;

public class Email {
	
	private String p1;
	private String p2;
	private String p3;
	private String p4;
	private String p5;
	private String p6;
	private String p7;
	private String greeting;
	private String salutation;
	private String surveyUrl;
	private String twSupportEmail;
	private String senderEmail;
	private String receiverEmail;
	private String mgrFirstName;
	


	public Email(Survey survey, Profile profile) {

		/*
		 * Production environment.
		 */
		//this.surveyUrl = "http://app.teamwrkr.com:8080/tw1/feedback.jsp?sid=" + survey.getSurveyId();
		
		/*
		 * Development environment.
		 */
		this.surveyUrl = "http://localhost:8080/tw1/feedback.jsp?sid=" + survey.getSurveyId();
		
		this.twSupportEmail = "help@teamwrkr.com";
		this.senderEmail = survey.getUserEmail();
		this.p1 = "<p>I have created a Dynamic Digital Profile with Teamwrkr - a platform that helps freelancers like me work with people like you!  </p>";
		this.p2 = "<p>I am asking that you help me by providing a review of the work I have done for you. This will be a short and confidential web-based review. </p>";
		this.p3 = "<p>You should know that I won&apos;t see your review. Teamwrkr aggregates reviews and your individual responses will be kept confidential. They also enhance confidentiality by making sure no reporting is done unless at least 3 people provide reviews of my work.</p>";
		this.p4 = "<p>Please note that I am asking you to respond as an individual, not as a representative of any company or organization.</p>";
		this.p5 = "<p>The process is quick and easy with only a few " +
                        				"questions. Please click here to complete the review: <a href='" +
                                        this.surveyUrl + "'>Performance Review Survey for " + profile.getFirstName() + " " + profile.getLastName() + "</a>" +
                                        "</p>";
		this.p6 = "<p>Please contact Teamwrkr by e-mail at " +
                                        this.twSupportEmail +
                                        " if you need any technical assistance.</p>";
		this.p7 = "<p>If you have any questions, please contact me at " +
                                        this.senderEmail +
                                        ".</p>";
		this.salutation = "<p>Thanks!</p>";


        }

	
	
	public String getHTML() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(this.p1);
		sb.append(this.p2);
		sb.append(this.p3);
		sb.append(this.p4);
		sb.append(this.p5);
		sb.append(this.p6);
		sb.append(this.p7);
		sb.append(this.salutation);
		
		
		return sb.toString();
		
	}



}

