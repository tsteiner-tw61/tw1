package com.teamwrkr.app.dto;

/**
 * This class contains all scores resulting from 
 * returned Teamwrkr surveys.
 * 
 * @author Ted E. Steiner
 * @date 3.30.2023
 *
 */
public class Scores {
	
	private int twid;
	private String hardskill1;
	private String hardskill2;
	private String hardskill3;
	private String hardskill4;
	private String hardskill5;
	private String hardskill6;
	private String hardskill7;
	private String hardskill8;
	private String hardskill9;
	private String hardskill10;
	private String softskill1;
	private String softskill2;
	private String softskill3;
	private String softskill4;
	private String softskill5;
	private String softskill6;
	private String softskill7;
	private String softskill8;
	private String softskill9;
	private String softskill10;
	private int scoreh1;
	private int scoreh2;
	private int scoreh3;
	private int scoreh4;
	private int scoreh5;
	private int scoreh6;
	private int scoreh7;
	private int scoreh8;
	private int scoreh9;
	private int scoreh10;
	private int scores1;
	private int scores2;
	private int scores3;
	private int scores4;
	private int scores5;
	private int scores6;
	private int scores7;
	private int scores8;
	private int scores9;
	private int scores10;
	private int counth1;
	private int counth2;
	private int counth3;
	private int counth4;
	private int counth5;
	private int counth6;
	private int counth7;
	private int counth8;
	private int counth9;
	private int counth10;
	private int counts1;
	private int counts2;
	private int counts3;
	private int counts4;
	private int counts5;
	private int counts6;
	private int counts7;
	private int counts8;
	private int counts9;
	private int counts10;
	private int scoreOverall;
	private int countOverall;
	
	
	
	/**
	 * This class is a convenience class to 
	 * quickly display all user scores onto 
	 * the dashboard, aka, the DDR.
	 */
	public Scores() { }
	
	
	public Scores(Scores scores) {
		
		this.twid = scores.getTwid();
		this.scoreh1 = scores.getScoreh1();
		this.scoreh2 = scores.getScoreh2();
		this.scoreh3 = scores.getScoreh3();
		this.scoreh4 = scores.getScoreh4();
		this.scoreh5 = scores.getScoreh5();
		this.scoreh6 = scores.getScoreh6();
		this.scoreh7 = scores.getScoreh7();
		this.scoreh8 = scores.getScoreh8();
		this.scoreh9 = scores.getScoreh9();
		this.scoreh10 = scores.getScoreh10();
		this.scores1 = scores.getScores1();
		this.scores2 = scores.getScores2();
		this.scores3 = scores.getScores3();
		this.scores4 = scores.getScores4();
		this.scores5 = scores.getScores5();
		this.scores6 = scores.getScores6();
		this.scores7 = scores.getScores7();
		this.scores8 = scores.getScores8();
		this.scores9 = scores.getScores9();
		this.scores10 = scores.getScores10();
		this.scoreOverall = scores.getScoreOverall();
		this.countOverall = scores.getCountOverall();
		this.hardskill1 = scores.getHardskill1();
		this.hardskill2 = scores.getHardskill2();
		this.hardskill3 = scores.getHardskill3();
		this.hardskill4 = scores.getHardskill4();
		this.hardskill5 = scores.getHardskill5();
		this.hardskill6 = scores.getHardskill6();
		this.hardskill7 = scores.getHardskill7();
		this.hardskill8 = scores.getHardskill8();
		this.hardskill9 = scores.getHardskill9();
		this.hardskill10 = scores.getHardskill10();
		this.softskill1 = scores.getSoftskill1();
		this.softskill2 = scores.getSoftskill2();
		this.softskill3 = scores.getSoftskill3();
		this.softskill4 = scores.getSoftskill4();
		this.softskill5 = scores.getSoftskill5();
		this.softskill6 = scores.getSoftskill6();
		this.softskill7 = scores.getSoftskill7();
		this.softskill8 = scores.getSoftskill8();
		this.softskill9 = scores.getSoftskill9();
		this.softskill10 = scores.getSoftskill10();
		this.counth1 = scores.getCounth1();
		this.counth2 = scores.getCounth2();
		this.counth3 = scores.getCounth3();
		this.counth4 = scores.getCounth4();
		this.counth5 = scores.getCounth5();
		this.counth6 = scores.getCounth6();
		this.counth7 = scores.getCounth7();
		this.counth8 = scores.getCounth8();
		this.counth9 = scores.getCounth9();
		this.counth10 = scores.getCounth10();
		this.counts1 = scores.getCounts1();
		this.counts2 = scores.getCounts2();
		this.counts3 = scores.getCounts3();
		this.counts4 = scores.getCounts4();
		this.counts5 = scores.getCounts5();
		this.counts6 = scores.getCounts6();
		this.counts7 = scores.getCounts7();
		this.counts8 = scores.getCounts8();
		this.counts9 = scores.getCounts9();
		this.counts10 = scores.getCounts10();
		
	}
	
	
	public int getTwid() {
        return twid;
    }

    public void setTwid(int twid) {
        this.twid = twid;
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
    
    public String getHardskill6() {
        return hardskill6;
    }

    public void setHardskill6(String hardskill6) {
        this.hardskill6 = hardskill6;
    }
    
    public String getHardskill7() {
        return hardskill7;
    }

    public void setHardskill7(String hardskill7) {
        this.hardskill7 = hardskill7;
    }
    
    public String getHardskill8() {
        return hardskill8;
    }

    public void setHardskill8(String hardskill8) {
        this.hardskill8 = hardskill8;
    }
    
    public String getHardskill9() {
        return hardskill9;
    }

    public void setHardskill9(String hardskill9) {
        this.hardskill9 = hardskill9;
    }
    
    public String getHardskill10() {
        return hardskill10;
    }

    public void setHardskill10(String hardskill10) {
        this.hardskill10 = hardskill10;
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
    
    public String getSoftskill6() {
        return softskill6;
    }

    public void setSoftskill6(String softskill6) {
        this.softskill6 = softskill6;
    }
    
    public String getSoftskill7() {
        return softskill7;
    }

    public void setSoftskill7(String softskill7) {
        this.softskill7 = softskill7;
    }
    
    public String getSoftskill8() {
        return softskill8;
    }

    public void setSoftskill8(String softskill8) {
        this.softskill8 = softskill8;
    }
    
    public String getSoftskill9() {
        return softskill9;
    }

    public void setSoftskill9(String softskill9) {
        this.softskill9 = softskill9;
    }
    
    public String getSoftskill10() {
        return softskill10;
    }

    public void setSoftskill10(String softskill10) {
        this.softskill10 = softskill10;
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
    
    public int getScoreh6() {
        return scoreh6;
    }

    public void setScoreh6(int scoreh6) {
        this.scoreh6 = scoreh6;
    }
    
    public int getScoreh7() {
        return scoreh7;
    }

    public void setScoreh7(int scoreh7) {
        this.scoreh7 = scoreh7;
    }
    
    public int getScoreh8() {
        return scoreh8;
    }

    public void setScoreh8(int scoreh8) {
        this.scoreh8 = scoreh8;
    }
    
    public int getScoreh9() {
        return scoreh9;
    }

    public void setScoreh9(int scoreh9) {
        this.scoreh9 = scoreh9;
    }
    
    public int getScoreh10() {
        return scoreh10;
    }

    public void setScoreh10(int scoreh10) {
        this.scoreh10 = scoreh10;
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
    
    
    public int getScores6() {
        return scores6;
    }

    public void setScores6(int scores6) {
        this.scores6 = scores6;
    }
    
    public int getScores7() {
        return scores7;
    }

    public void setScores7(int scores7) {
        this.scores7 = scores7;
    }
    
    public int getScores8() {
        return scores8;
    }

    public void setScores8(int scores8) {
        this.scores8 = scores8;
    }
    
    public int getScores9() {
        return scores9;
    }

    public void setScores9(int scores9) {
        this.scores9 = scores9;
    }
    
    public int getScores10() {
        return scores10;
    }

    public void setScores10(int scores10) {
        this.scores10 = scores10;
    }
    
    public int getCounth1() {
        return counth1;
    }

    public void setCounth1(int counth1) {
        this.counth1 = counth1;
    }
    
    public int getCounth2() {
        return counth2;
    }

    public void setCounth2(int counth2) {
        this.counth2 = counth2;
    }
    
    public int getCounth3() {
        return counth3;
    }

    public void setCounth3(int counth3) {
        this.counth3 = counth3;
    }
    
    public int getCounth4() {
        return counth4;
    }

    public void setCounth4(int counth4) {
        this.counth4 = counth4;
    }
    
    public int getCounth5() {
        return counth5;
    }

    public void setCounth5(int counth5) {
        this.counth5 = counth5;
    }
    
    public int getCounth6() {
        return counth6;
    }

    public void setCounth6(int counth6) {
        this.counth6 = counth6;
    }
    
    public int getCounth7() {
        return counth7;
    }

    public void setCounth7(int counth7) {
        this.counth7 = counth7;
    }
    
    public int getCounth8() {
        return counth8;
    }

    public void setCounth8(int counth8) {
        this.counth8 = counth8;
    }
    
    public int getCounth9() {
        return counth9;
    }

    public void setCounth9(int counth9) {
        this.counth9 = counth9;
    }
    
    public int getCounth10() {
        return counth10;
    }

    public void setCounth10(int counth10) {
        this.counth10 = counth10;
    }
    
    
    
    public int getCounts1() {
        return counts1;
    }

    public void setCounts1(int counts1) {
        this.counts1 = counts1;
    }
    
    public int getCounts2() {
        return counts2;
    }

    public void setCounts2(int counts2) {
        this.counts2 = counts2;
    }
    
    public int getCounts3() {
        return counts3;
    }

    public void setCounts3(int counts3) {
        this.counts3 = counts3;
    }
    
    public int getCounts4() {
        return counts4;
    }

    public void setCounts4(int counts4) {
        this.counts4 = counts4;
    }
    
    public int getCounts5() {
        return counts5;
    }

    public void setCounts5(int counts5) {
        this.counts5 = counts5;
    }
    
    
    public int getCounts6() {
        return counts6;
    }

    public void setCounts6(int counts6) {
        this.counts6 = counts6;
    }
    
    public int getCounts7() {
        return counts7;
    }

    public void setCounts7(int counts7) {
        this.counts7 = counts7;
    }
    
    public int getCounts8() {
        return counts8;
    }

    public void setCounts8(int counts8) {
        this.counts8 = counts8;
    }
    
    public int getCounts9() {
        return counts9;
    }

    public void setCounts9(int counts9) {
        this.counts9 = counts9;
    }
    
    public int getCounts10() {
        return counts10;
    }

    public void setCounts10(int counts10) {
        this.counts10 = counts10;
    }
    
    
    public int getScoreOverall() {
        return scoreOverall;
    }

    public void setScoreOverall(int scoreOverall) {
        this.scoreOverall = scoreOverall;
    }
    
    public int getCountOverall() {
        return countOverall;
    }

    public void setCountOverall(int countOverall) {
        this.countOverall = countOverall;
    }

}

