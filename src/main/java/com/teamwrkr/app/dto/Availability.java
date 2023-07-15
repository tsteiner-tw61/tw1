package com.teamwrkr.app.dto;


/**
 * This class indicates a user's availablity for
 * a 28 day period, determined by the user's 
 * CalInfo object, which specifies the dates
 * attached to the values in this class.
 * 
 * @author 	Ted E. Steiner
 * @date 	3.15.2023
 *
 */
public class Availability {
	
	private int twid;
	private boolean sun1;
	private boolean mon1;
	private boolean tue1;
	private boolean wed1;
	private boolean thu1;
	private boolean fri1;
	private boolean sat1;
	private boolean sun2;
	private boolean mon2;
	private boolean tue2;
	private boolean wed2;
	private boolean thu2;
	private boolean fri2;
	private boolean sat2;
	private boolean sun3;
	private boolean mon3;
	private boolean tue3;
	private boolean wed3;
	private boolean thu3;
	private boolean fri3;
	private boolean sat3;
	private boolean sun4;
	private boolean mon4;
	private boolean tue4;
	private boolean wed4;
	private boolean thu4;
	private boolean fri4;
	private boolean sat4;
	
	
	public Availability() { }
	
	
	public Availability(Availability av) {
		
		this.twid = av.getTwid();
		this.sun1 = av.getSun1();
		this.mon1 = av.getMon1();
		this.tue1 = av.getTue1();
		this.wed1 = av.getWed1();
		this.thu1 = av.getThu1();
		this.fri1 = av.getFri1();
		this.sat1 = av.getSat1();
		this.sun2 = av.getSun2();
		this.mon2 = av.getMon2();
		this.tue2 = av.getTue2();
		this.wed2 = av.getWed2();
		this.thu2 = av.getThu2();
		this.fri2 = av.getFri2();
		this.sat2 = av.getSat2();
		this.sun3 = av.getSun3();
		this.mon3 = av.getMon3();
		this.tue3 = av.getTue3();
		this.wed3 = av.getWed3();
		this.thu3 = av.getThu3();
		this.fri3 = av.getFri3();
		this.sat3 = av.getSat3();
		this.sun4 = av.getSun4();
		this.mon4 = av.getMon4();
		this.tue4 = av.getTue4();
		this.wed4 = av.getWed4();
		this.thu4 = av.getThu4();
		this.fri4 = av.getFri4();
		this.sat4 = av.getSat4();
		
	}
	
	
	/**
	 * By default, the constructor sets total
	 * availability either true or false.
	 */
	public Availability(boolean available) {
		
		this.sun1 = available;
		this.mon1 = available;
		this.tue1 = available;
		this.wed1 = available;
		this.thu1 = available;
		this.fri1 = available;
		this.sat1 = available;
		this.sun2 = available;
		this.mon2 = available;
		this.tue2 = available;
		this.wed2 = available;
		this.thu2 = available;
		this.fri2 = available;
		this.sat2 = available;
		this.sun3 = available;
		this.mon3 = available;
		this.tue3 = available;
		this.wed3 = available;
		this.thu3 = available;
		this.fri3 = available;
		this.sat3 = available;
		this.sun4 = available;
		this.mon4 = available;
		this.tue4 = available;
		this.wed4 = available;
		this.thu4 = available;
		this.fri4 = available;
		this.sat4 = available;
		
	}
	
	
	/**
	 * Return the availability as an array matrix.
	 * 
	 * TODO check if this is being used anywhere in 
	 * the codebase.
	 * 
	 * @return
	 */
	public int[] getMatrix() {
		
		int[] matrix = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
		
		if (this.sun1 == false) {matrix[0] = 0;}
		if (this.mon1 == false) {matrix[1] = 0;}
		if (this.tue1 == false) {matrix[2] = 0;}
		if (this.wed1 == false) {matrix[3] = 0;}
		if (this.thu1 == false) {matrix[4] = 0;}
		if (this.fri1 == false) {matrix[5] = 0;}
		if (this.sat1 == false) {matrix[6] = 0;}
		
		if (this.sun2 == false) {matrix[7] = 0;}
		if (this.mon2 == false) {matrix[8] = 0;}
		if (this.tue2 == false) {matrix[9] = 0;}
		if (this.wed2 == false) {matrix[10] = 0;}
		if (this.thu2 == false) {matrix[11] = 0;}
		if (this.fri2 == false) {matrix[12] = 0;}
		if (this.sat2 == false) {matrix[13] = 0;}
		
		if (this.sun3 == false) {matrix[14] = 0;}
		if (this.mon3 == false) {matrix[15] = 0;}
		if (this.tue3 == false) {matrix[16] = 0;}
		if (this.wed3 == false) {matrix[17] = 0;}
		if (this.thu3 == false) {matrix[18] = 0;}
		if (this.fri3 == false) {matrix[19] = 0;}
		if (this.sat3 == false) {matrix[20] = 0;}
		
		if (this.sun4 == false) {matrix[21] = 0;}
		if (this.mon4 == false) {matrix[22] = 0;}
		if (this.tue4 == false) {matrix[23] = 0;}
		if (this.wed4 == false) {matrix[24] = 0;}
		if (this.thu4 == false) {matrix[25] = 0;}
		if (this.fri4 == false) {matrix[26] = 0;}
		if (this.sat4 == false) {matrix[27] = 0;}
		
		return matrix;
	}
	
	
	public int getTwid() {
        return twid;
    }

    public void setTwid(int twid) {
        this.twid = twid;
    }
	
	public boolean getSun1() {
        return sun1;
    }

    public void setSun1(boolean sun1) {
        this.sun1 = sun1;
    }
    
    public boolean getMon1() {
        return mon1;
    }

    public void setMon1(boolean mon1) {
        this.mon1 = mon1;
    }
    
    public boolean getTue1() {
        return tue1;
    }

    public void setTue1(boolean tue1) {
        this.tue1 = tue1;
    }
    
    public boolean getWed1() {
        return wed1;
    }

    public void setWed1(boolean wed1) {
        this.wed1 = wed1;
    }
    
    public boolean getThu1() {
        return thu1;
    }

    public void setThu1(boolean thu1) {
        this.thu1 = thu1;
    }
    
    public boolean getFri1() {
        return fri1;
    }

    public void setFri1(boolean fri1) {
        this.fri1 = fri1;
    }
    
    public boolean getSat1() {
        return sat1;
    }

    public void setSat1(boolean sat1) {
        this.sat1 = sat1;
    }
    
    public boolean getSun2() {
        return sun2;
    }

    public void setSun2(boolean sun2) {
        this.sun2 = sun2;
    }
    
    public boolean getMon2() {
        return mon2;
    }

    public void setMon2(boolean mon2) {
        this.mon2 = mon2;
    }
    
    public boolean getTue2() {
        return tue2;
    }

    public void setTue2(boolean tue2) {
        this.tue2 = tue2;
    }
    
    public boolean getWed2() {
        return wed2;
    }

    public void setWed2(boolean wed2) {
        this.wed2 = wed2;
    }
    
    public boolean getThu2() {
        return thu2;
    }

    public void setThu2(boolean thu2) {
        this.thu2 = thu2;
    }
    
    public boolean getFri2() {
        return fri2;
    }

    public void setFri2(boolean fri2) {
        this.fri2 = fri2;
    }
    
    public boolean getSat2() {
        return sat2;
    }

    public void setSat2(boolean sat2) {
        this.sat2 = sat2;
    }
    
    public boolean getSun3() {
        return sun3;
    }

    public void setSun3(boolean sun3) {
        this.sun3 = sun3;
    }
    
    public boolean getMon3() {
        return mon3;
    }

    public void setMon3(boolean mon3) {
        this.mon3 = mon3;
    }
    
    public boolean getTue3() {
        return tue3;
    }

    public void setTue3(boolean tue3) {
        this.tue3 = tue3;
    }
    
    public boolean getWed3() {
        return wed3;
    }

    public void setWed3(boolean wed3) {
        this.wed3 = wed3;
    }
    
    public boolean getThu3() {
        return thu3;
    }

    public void setThu3(boolean thu3) {
        this.thu3 = thu3;
    }
    
    public boolean getFri3() {
        return fri3;
    }

    public void setFri3(boolean fri3) {
        this.fri3 = fri3;
    }
    
    public boolean getSat3() {
        return sat3;
    }

    public void setSat3(boolean sat3) {
        this.sat3 = sat3;
    }
    
    public boolean getSun4() {
        return sun4;
    }

    public void setSun4(boolean sun4) {
        this.sun4 = sun4;
    }
    
    public boolean getMon4() {
        return mon4;
    }

    public void setMon4(boolean mon4) {
        this.mon4 = mon4;
    }
    
    public boolean getTue4() {
        return tue4;
    }

    public void setTue4(boolean tue4) {
        this.tue4 = tue4;
    }
    
    public boolean getWed4() {
        return wed4;
    }

    public void setWed4(boolean wed4) {
        this.wed4 = wed4;
    }
    
    public boolean getThu4() {
        return thu4;
    }

    public void setThu4(boolean thu4) {
        this.thu4 = thu4;
    }
    
    public boolean getFri4() {
        return fri4;
    }

    public void setFri4(boolean fri4) {
        this.fri4 = fri4;
    }
    
    public boolean getSat4() {
        return sat4;
    }

    public void setSat4(boolean sat4) {
        this.sat4 = sat4;
    }
    
 
    /**
     * Convenience function to get the number of
     * days the user marked as available to display
     * on the dashboard view.
     * 
     * @return int days
     */
    public int getDaysAvailable() {
    	
    	int days = 0;
    	
    	if (this.sun1 == true) {days++;}
		if (this.mon1 == true) {days++;}
		if (this.tue1 == true) {days++;}
		if (this.wed1 == true) {days++;}
		if (this.thu1 == true) {days++;}
		if (this.fri1 == true) {days++;}
		if (this.sat1 == true) {days++;}
		
		if (this.sun2 == true) {days++;}
		if (this.mon2 == true) {days++;}
		if (this.tue2 == true) {days++;}
		if (this.wed2 == true) {days++;}
		if (this.thu2 == true) {days++;}
		if (this.fri2 == true) {days++;}
		if (this.sat2 == true) {days++;}
		
		if (this.sun3 == true) {days++;}
		if (this.mon3 == true) {days++;}
		if (this.tue3 == true) {days++;}
		if (this.wed3 == true) {days++;}
		if (this.thu3 == true) {days++;}
		if (this.fri3 == true) {days++;}
		if (this.sat3 == true) {days++;}
		
		if (this.sun4 == true) {days++;}
		if (this.mon4 == true) {days++;}
		if (this.tue4 == true) {days++;}
		if (this.wed4 == true) {days++;}
		if (this.thu4 == true) {days++;}
		if (this.fri4 == true) {days++;}
		if (this.sat4 == true) {days++;}
		
		//System.out.println("daysYes = " + days);
		
		float pct = (float) 1/28;
		
		//System.out.println("pct = " + Float.valueOf(pct));
		
		return days;
    	
    }
	
	

}

