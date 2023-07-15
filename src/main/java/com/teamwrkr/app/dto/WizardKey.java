package com.teamwrkr.app.dto;

/**
 * POJO class for WizardKey DB entry.
 * 
 * WizardKey table entries will be the determining
 * information for whether a new category should be
 * assigned for a given profile wizard sequence and
 * maps the wizard to the next WizardItem in the 
 * sequence given the previous WizardAction.
 * 
 * If no WizardKey exists for a given combination
 * of categories, sequence within, and answer
 * provided, then the default action will be to
 * remain in the current category hierarchy and
 * move to the next sequence WizardItem within
 * that category combination.
 * 
 * TODO convert to JPA framework when implemented
 * 
 * @author Ted E. Steiner
 * @date 2.17.2023
 *
 */
public class WizardKey {
	
	private String currentcategory1;
	private String currentcategory2;
	private String currentcategory3;
	private int currentsequence;
	private int answer;
	private String newcategory1;
	private String newcategory2;
	private String newcategory3;
	private int newsequence;
	
	public WizardKey() { }
	
	
	public String getCurrentcategory1() {
		return this.currentcategory1;
	}
	
	public void setCurrentcategory1(String currentcategory1) {
		this.currentcategory1 = currentcategory1;
	}
	
	public String getCurrentcategory2() {
		return this.currentcategory2;
	}
	
	public void setCurrentcategory2(String currentcategory2) {
		this.currentcategory2 = currentcategory2;
	}
	
	public String getCurrentcategory3() {
		return this.currentcategory3;
	}
	
	public void setCurrentcategory3(String currentcategory3) {
		this.currentcategory3 = currentcategory3;
	}
	
	public int getCurrentsequence() {
		return this.currentsequence;
	}
	
	public void setCurrentsequence(int currentsequence) {
		this.currentsequence = currentsequence;
	}
	
	public int getAnswer() {
		return this.answer;
	}
	
	public void setAnswer(int answer) {
		this.answer = answer;
	}
	
	public String getNewcategory1() {
		return this.newcategory1;
	}
	
	public void setNewcategory1(String newcategory1) {
		this.newcategory1 = newcategory1;
	}
	
	public String getNewcategory2() {
		return this.newcategory2;
	}
	
	public void setNewcategory2(String newcategory2) {
		this.newcategory2 = newcategory2;
	}
	
	public String getNewcategory3() {
		return this.newcategory3;
	}
	
	public void setNewcategory3(String newcategory3) {
		this.newcategory3 = newcategory3;
	}
	
	public int getNewsequence() {
		return this.newsequence;
	}
	
	public void setNewsequence(int newsequence) {
		this.newsequence = newsequence;
	}

}


