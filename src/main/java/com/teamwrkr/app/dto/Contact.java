package com.teamwrkr.app.dto;

import java.util.Date;

/**
 * This class represents a single contact entity.
 * 
 * @author Ted E. Steiner
 * @date 5.28.2023
 *
 */
public class Contact {
	
	private int contactId;
	private int missionId;
	private int type;
	private int status;
	private int stage;
	private int twid1;
	private int twid2;
	private String email;
	private String firstName;
	private String lastName;
	private String company;
	private String address;
	private String city;
	private String state;
	private String country;
	private String zip;
	private Date dob;
	private String category1;
	private String category2;
	private String notes;
	private String phone1;
	private String phone2;
	private Date createdDate;
	
	
	public Contact() { }
	
	
	public Contact(Contact contact) {
		
		this.contactId = contact.getContactId();
		this.missionId = contact.getMissionId();
		this.type = contact.getType();
		this.status = contact.getStatus();
		this.stage = contact.getStage();
		this.twid1 = contact.getTwid1();
		this.twid2 = contact.getTwid2();
		this.email = contact.getEmail();
		this.firstName = contact.getFirstName();
		this.lastName = contact.getLastName();
		this.company = contact.getCompany();
		this.address = contact.getAddress();
		this.city = contact.getCity();
		this.state = contact.getState();
		this.country = contact.getCountry();
		this.zip = contact.getZip();
		this.dob = contact.getDob();
		this.category1 = contact.getCategory1();
		this.category2 = contact.getCategory2();
		this.notes = contact.getNotes();
		this.phone1 = contact.getPhone1();
		this.phone2 = contact.getPhone2();
		this.createdDate = contact.getCreatedDate();

	}
	
	
	public int getContactId() {
        return contactId;
    }
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }
    
    public int getMissionId() {
        return missionId;
    }
    public void setMissionId(int missionId) {
        this.missionId = missionId;
    }

    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    
    public int getStage() {
        return stage;
    }
    public void setStage(int stage) {
        this.stage = stage;
    }
    
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
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
    
    
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
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
    
    
    public String getZip() {
        return zip;
    }
    public void setZip(String zip) {
        this.zip = zip;
    }
    
    
    public Date getDob() {
        return dob;
    }
    public void setDob(Date dob) {
        this.dob = dob;
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
    
    
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    
    public String getPhone1() {
        return phone1;
    }
    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }
    
    
    public String getPhone2() {
        return phone2;
    }
    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }
    
    
    public Date getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    
    
    /**
     * Convenience method to identify whether a
     * Contact is internal or external to the
     * Teamwrkr community.
     * 
     * @return boolean
     */
    public boolean isExternal() {
    	if (this.twid2 == 0) {
    		return true;
    	}
    	return false;
    }    
    
    
    public String translateStageCode(int code) {
    	
    	String text = null;
    	
    	
    	if (code == 1) {return "ENGAGED";}
    	if (code == 2) {return "PARTNERED";}
    	if (code == 3) {return "DELIVERING";}
    	if (code == 4) {return "NURTURING";}
    	
    	return text;
    	
    }
    
}
