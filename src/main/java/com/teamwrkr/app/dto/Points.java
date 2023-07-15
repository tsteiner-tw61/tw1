package com.teamwrkr.app.dto;


/**
 * This class tracks all Teamwrkr points earned by
 * a given user Profile.
 * 
 * @author Ted E. Steiner
 * @date 5.5.2023
 *
 */
public class Points {

	//private int pointsId;
	private int twid;
	private int profilePhoto;
	private int profileNiche;
	private int profileSkills;
	private int profileURL;
	private int profileReview;
	private int profileVideo;
	private int profileEdit;
	private int profileView;
	private int logins;
	private int jobPosts;
	private int jobApplies;
	private int jobPays;
	private int jobDones;
	private int upvotes;
	private int feedback;
	private int surveys;
	private int onlineTime;
	private int referrals;
	private int contacts;
	private int bonus;
	private int special;
	private int availability;
	private int currency;
	private int contribution;
	private int chat;
	private int misc;
	
	
	public Points() { }
	
	
	public Points(Points points) {
		
		//this.pointsId = points.getPointsId();
		this.twid = points.getTwid();
		this.profilePhoto = points.getProfilePhoto();
		this.profileNiche = points.getProfileNiche();
		this.profileSkills = points.getProfileSkills();
		this.profileURL = points.getProfileURL();
		this.profileReview = points.getProfileReview();
		this.profileVideo = points.getProfileVideo();
		this.profileEdit = points.getProfileEdit();
		this.profileView = points.getProfileView();
		this.logins = points.getLogins();
		this.jobPosts = points.getJobPosts();
		this.jobApplies = points.getJobApplies();
		this.jobPays = points.getJobPays();
		this.jobDones = points.getJobDones();
		this.upvotes = points.getUpvotes();
		this.feedback = points.getFeedback();
		this.surveys = points.getSurveys();
		this.onlineTime = points.getOnlineTime();
		this.referrals = points.getReferrals();
		this.contacts = points.getContacts();
		this.bonus = points.getBonus();
		this.special = points.getSpecial();
		this.availability = points.getAvailability();
		this.currency = points.getCurrency();
		this.contribution = points.getContribution();
		this.chat = points.getChat();
		this.misc = points.getMisc();
		
	}
	
	//public int getPointsId() {
        //return pointsId;
    //}

    //public void setPointsId(int pointsId) {
        //this.pointsId  = pointsId;
    //}
    
    public int getTwid() {
        return twid;
    }

    public void setTwid(int twid) {
        this.twid  = twid;
    }

    public int getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(int profilePhoto) {
        this.profilePhoto  = profilePhoto;
    }
    
    public int getProfileNiche() {
        return profileNiche;
    }

    public void setProfileNiche(int profileNiche) {
        this.profileNiche  = profileNiche;
    }
    
    public int getProfileSkills() {
        return profileSkills;
    }

    public void setProfileSkills(int profileSkills) {
        this.profileSkills  = profileSkills;
    }
    
    public int getProfileURL() {
        return profileURL;
    }

    public void setProfileURL(int profileURL) {
        this.profileURL  = profileURL;
    }
    
    public int getProfileReview() {
        return profileReview;
    }

    public void setProfileReview(int profileReview) {
        this.profileReview  = profileReview;
    }
    
    public int getProfileVideo() {
        return profileVideo;
    }

    public void setProfileVideo(int profileVideo) {
        this.profileVideo  = profileVideo;
    }
    
    public int getProfileEdit() {
        return profileEdit;
    }

    public void setProfileEdit(int profileEdit) {
        this.profileEdit  = profileEdit;
    }
    
    public int getProfileView() {
        return profileView;
    }

    public void setProfileView(int profileView) {
        this.profileView  = profileView;
    }

    public int getLogins() {
        return logins;
    }

    public void setLogins(int logins) {
        this.logins  = logins;
    }
    
    public int getJobPosts() {
        return jobPosts;
    }

    public void setJobPosts(int jobPosts) {
        this.jobPosts  = jobPosts;
    }
    
    public int getJobApplies() {
        return jobApplies;
    }

    public void setJobApplies(int jobApplies) {
        this.jobApplies  = jobApplies;
    }
    
    public int getJobPays() {
        return jobPays;
    }

    public void setJobPays(int twid) {
        this.twid  = twid;
    }
    
    public int getJobDones() {
        return jobDones;
    }

    public void setJobDones(int jobDones) {
        this.jobDones  = jobDones;
    }
    
    public int getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes  = upvotes;
    }
    
    public int getFeedback() {
        return feedback;
    }

    public void setFeedback(int feedback) {
        this.feedback  = feedback;
    }
    
    public int getSurveys() {
        return surveys;
    }

    public void setSurveys(int surveys) {
        this.surveys  = surveys;
    }
    
    public int getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(int onlineTime) {
        this.onlineTime  = onlineTime;
    }
    
    public int getReferrals() {
        return referrals;
    }

    public void setReferrals(int referrals) {
        this.referrals  = referrals;
    }
    
    public int getContacts() {
        return contacts;
    }

    public void setContacts(int contacts) {
        this.contacts  = contacts;
    }
    
    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus  = bonus;
    }
    
    public int getSpecial() {
        return special;
    }

    public void setSpecial(int special) {
        this.special  = special;
    }
    
    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability  = availability;
    }
    
    public int getCurrency() {
        return currency;
    }

    public void setCurrency(int currency) {
        this.currency  = currency;
    }
    
    public int getContribution() {
        return contribution;
    }

    public void setContribution(int contribution) {
        this.contribution  = contribution;
    }
    
    public int getChat() {
        return chat;
    }

    public void setChat(int chat) {
        this.chat  = chat;
    }

    public int getMisc() {
        return misc;
    }

    public void setMisc(int misc) {
        this.misc  = misc;
    }
    
}
