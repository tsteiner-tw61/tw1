package com.teamwrkr.app.dto;

/**
 * This is the DTO class for the actionpoints
 * DB table which contains the matrix of Teamwrkr
 * points awarded for each action a user takes from
 * within the application.
 * 
 * @author 		Ted E. Steiner
 * @date		05.15.2023
 */
public class ActionPoints {
	
	
	int actId;
	String action;
	int actionCode;
	int points;
	
	
	
	public ActionPoints() { } 
	
	
	
	public ActionPoints(ActionPoints actionPoints) {
		
		this.actId = actionPoints.getActId();
		this.action = actionPoints.getAction();
		this.actionCode = actionPoints.getActionCode();
		this.points = actionPoints.getPoints();
		
	}
	
	
	
	public int getActId() {
        return actId;
    }

    public void setActId(int actId) {
        this.actId = actId;
    }
    
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
    
    public int getActionCode() {
        return actionCode;
    }

    public void setActionCode(int actionCode) {
        this.actionCode = actionCode;
    }
    
    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

}
