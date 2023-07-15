package com.teamwrkr.app.dto;

/**
 * POJO class for Wizard Page content.
 * 
 * Each WizardItem object represents a single 
 * page in the introductory teamwrkr wizard
 * which also serves as the defacto registration
 * process.
 * 
 * TODO modify to entity class when JPA working
 * 
 * @param id
 * @param category1 - primary category
 * @param category2 - secondary category
 * @param category3 - tertiary category
 * @param sequence - order of appearance within category structure
 * @param question - question
 * @param text - for marketing pages only
 * @param answer1..10 - multiple choice answers for questions
 * 
 * @author Ted E. Steiner
 * @date 2.17.2023
 *
 */
public class WizardItem {
	
	private int id;
	private String category1;
	private String category2;
	private String category3;
	private int sequence;
	private String question;
	private String text;
	private String answer1;
	private String answer2;
	private String answer3;
	private String answer4;
	private String answer5;
	private String answer6;
	private String answer7;
	private String answer8;
	private String answer9;
	private String answer10;
	private String img;
	
	
	public WizardItem() { }
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getCategory1() {
		return this.category1;
	}
	
	public void setCategory1(String category1) {
		this.category1 = category1;
	}
	
	public String getCategory2() {
		return this.category2;
	}
	
	public void setCategory2(String category2) {
		this.category2 = category2;
	}
	
	public String getCategory3() {
		return this.category3;
	}
	
	public void setCategory3(String category3) {
		this.category3 = category3;
	}
	
	public int getSequence() {
		return this.sequence;
	}
	
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	
	public String getQuestion() {
		return this.question;
	}
	
	public void setQuestion(String question) {
		this.question = question;
	}
	
	public String getText() {
		return this.text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	
	public String getAnswer1() {
		return this.answer1;
	}
	
	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}
	
	public String getAnswer2() {
		return this.answer2;
	}
	
	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}
	
	public String getAnswer3() {
		return this.answer3;
	}
	
	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}

	public String getAnswer4() {
		return this.answer4;
	}
	
	public void setAnswer4(String answer4) {
		this.answer4 = answer4;
	}
	
	public String getAnswer5() {
		return this.answer5;
	}
	
	public void setAnswer5(String answer5) {
		this.answer5 = answer5;
	}
	
	public String getAnswer6() {
		return this.answer6;
	}
	
	public void setAnswer6(String answer6) {
		this.answer6 = answer6;
	}
	
	public String getAnswer7() {
		return this.answer7;
	}
	
	public void setAnswer7(String answer7) {
		this.answer7 = answer7;
	}
	
	public String getAnswer8() {
		return this.answer8;
	}
	
	public void setAnswer8(String answer8) {
		this.answer8 = answer8;
	}
	
	public String getAnswer9() {
		return this.answer9;
	}
	
	public void setAnswer9(String answer9) {
		this.answer9 = answer9;
	}

	public String getAnswer10() {
		return this.answer10;
	}
	
	public void setAnswer10(String answer10) {
		this.answer10 = answer10;
	}
	
	public String getImg() {
		return this.img;
	}
	
	public void setImg(String img) {
		this.img = img;
	}

}

