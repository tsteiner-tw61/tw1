package com.teamwrkr.app.dto;

import java.util.Date;


/**
 * This class represents a Teamwrkr user.
 * It is the main class used for populating the
 * user dashboard, a.k.a. Dynamic Digital Resume
 * 
 * @author Ted E. Steiner
 * @date 3.14.2023
 *
 */
public class Profile {
	
	private int twid;
	private long sessionId;
	private String email;
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String state;
	private String country;
	private Date dob;
	private String photoImg;
	private String videoLoc;
	private String category1;
	private String category2;
	private String niche;
	private String userSkill1;
	private String userSkill2;
	private String userSkill3;
	private String url1;
	private String url2;
	private String url3;
	private String url4;
	private String url5;
	private String url6;
	private String encPwd;
	private Date createDate;
	
	
	public Profile() { }
	
	public Profile(Profile profile) {
		
		 this.twid = profile.getTwid();
		 this.sessionId = profile.getSessionId();
		 this.email = profile.getEmail();
		 this.firstName = profile.getFirstName();
		 this.lastName = profile.getLastName();
		 this.address = profile.getAddress();
		 this.city = profile.getCity();
		 this.state = profile.getState();
		 this.country = profile.getCountry();
		 this.dob = profile.getDob();
		 this.photoImg = profile.getPhotoImg();
		 this.videoLoc = profile.getVideoLoc();
		 this.category1 = profile.getCategory1();
		 this.category2 = profile.getCategory2();
		 this.niche = profile.getNiche();
		 this.userSkill1 = profile.getUserSkill1();
		 this.userSkill2 = profile.getUserSkill2();
		 this.userSkill3 = profile.getUserSkill3();
		 this.url1 = profile.getUrl1();
		 this.url2 = profile.getUrl2();
		 this.url3 = profile.getUrl3();
		 this.url4 = profile.getUrl4();
		 this.url5 = profile.getUrl5();
		 this.url6 = profile.getUrl6();
		 this.encPwd = profile.getEncPwd();
		 this.createDate = profile.getCreateDate();
		 
	}
	
	
	
	public int getTwid() {
        return twid;
    }

    public void setTwid(int twid) {
        this.twid = twid;
    }
    
    public long getSessionId() {
        return sessionId;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }
	
	public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
	
	public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
    
    public String getPhotoImg() {
        return photoImg;
    }

    public void setPhotoImg(String photoImg) {
        this.photoImg = photoImg;
    }
    
    public String getVideoLoc() {
        return videoLoc;
    }

    public void setVideoLoc(String videoLoc) {
        this.videoLoc = videoLoc;
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
    
    public String getNiche() {
        return niche;
    }

    public void setNiche(String niche) {
        this.niche = niche;
    }
    
    public String getUserSkill1() {
        return userSkill1;
    }

    public void setUserSkill1(String userSkill1) {
        this.userSkill1 = userSkill1;
    }
    
    public String getUserSkill2() {
        return userSkill2;
    }

    public void setUserSkill2(String userSkill2) {
        this.userSkill2 = userSkill2;
    }
    
    public String getUserSkill3() {
        return userSkill3;
    }

    public void setUserSkill3(String userSkill3) {
        this.userSkill3 = userSkill3;
    }
    
    public String getUrl1() {
        return url1;
    }

    public void setUrl1(String url1) {
        this.url1 = url1;
    }
    
    public String getUrl2() {
        return url2;
    }

    public void setUrl2(String url2) {
        this.url2 = url2;
    }
    
    public String getUrl3() {
        return url3;
    }

    public void setUrl3(String url3) {
        this.url3 = url3;
    }
    
    public String getUrl4() {
        return url4;
    }

    public void setUrl4(String url4) {
        this.url4 = url4;
    }
    
    public String getUrl5() {
        return url5;
    }

    public void setUrl5(String url5) {
        this.url5 = url5;
    }
    
    public String getUrl6() {
        return url6;
    }

    public void setUrl6(String url6) {
        this.url6 = url6;
    }
    
    public String getEncPwd() {
        return encPwd;
    }

    public void setEncPwd(String encPwd) {
        this.encPwd = encPwd;
    }
    
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
 
}

