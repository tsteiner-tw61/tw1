package com.teamwrkr.app.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.teamwrkr.app.dto.WizardKey;
import com.teamwrkr.app.util.SqlUtils;

/**
 * Service defining classes responsible for getting
 * informaton about profile wizard display sequence.
 * 
 * TODO update this to the JPA structure when implemented
 * 
 * @author Ted E. Steiner
 * @date 2.18.2023
 *
 */
public class WizardKeyService {
	
	
	public WizardKeyService() { }

	/**
	 * Try to get a WizardKey with the current hierarchy.
	 * If none exists, move sequence forward by one and
	 * try again. Exit and return null WizardKey if still
	 * none exists.
	 * 
	 * @param category1
	 * @param category2
	 * @param category3
	 * @param sequence
	 * @param answer
	 * @return
	 */
	public static WizardKey findWizardKey(String category1, String category2, String category3, int sequence, int answer) {
		
		WizardKey wizardKey = new WizardKey();
		
		wizardKey = selectWizardKey(category1, category2, category3, sequence, answer);
		if (wizardKey == null) {
			wizardKey = selectWizardKey(category1, category2, category3, sequence+1, answer);
		}
		
		return wizardKey;
		
	}
	
	
	/**
	 * Method to select a single WizardKey item
	 * from the DB to determine the next step
	 * in the wizard profile process.
	 * 
	 * @param category1
	 * @param category2
	 * @param category3
	 * @param sequence
	 * @param answer
	 * @return
	 */
	public static WizardKey selectWizardKey(String category1, String category2, String category3, int sequence, int answer) {
		
		WizardKey wizardKey = new WizardKey();
		
		/*
		 * Generate SQL.
		 */
		String query = "SELECT * FROM wizardkey WHERE ";
		String sqlCat1 = "currentcategory1 = '" + category1 + "' AND ";
		String sqlCat2 = "currentcategory2 = '" + category2 + "' AND ";
		String sqlCat3 = "currentcategory3 = '" + category3 + "' AND ";
		if (category1 == null || category1.equals("null")) {
			sqlCat1 = "currentcategory1 IS NULL AND ";
		}
		if (category2 == null || category2.equals("null")) {
			sqlCat2 = "currentcategory2 IS NULL AND ";
		}
		if (category3 == null || category3.equals("null")) {
			sqlCat3 = "currentcategory3 IS NULL AND ";
		}
		String sqlSeq = "currentsequence = " + sequence + " AND ";
		String sqlAns = "answer = " + answer;
		query += sqlCat1;
		query += sqlCat2;
		query += sqlCat3;
		query += sqlSeq;
		query += sqlAns;
		
		System.out.println(query);
		
		/*
		 * Execute query.				
		 */
		Context initCtx;
		try {
			initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource)  envCtx.lookup("jdbc/teamwrkrDB");
			Connection conn = ds.getConnection();
		    Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		    ResultSet rs = stmt.executeQuery(query);
		    if (rs.first()) {
		    	wizardKey.setCurrentcategory1(rs.getString("currentcategory1"));
		    	wizardKey.setCurrentcategory2(rs.getString("currentcategory2"));
		    	wizardKey.setCurrentcategory3(rs.getString("currentcategory3"));
		    	wizardKey.setCurrentsequence(rs.getInt("currentsequence"));
		    	wizardKey.setAnswer(rs.getInt("answer"));
		    	wizardKey.setNewcategory1(rs.getString("newcategory1"));
		    	wizardKey.setNewcategory2(rs.getString("newcategory2"));
		    	wizardKey.setNewcategory3(rs.getString("newcategory3"));
		    	wizardKey.setNewsequence(rs.getInt("newsequence"));
		    }
		    else {
		    	wizardKey = null;
		    }
			conn.close();
		} 
		catch (NamingException e1) {
			e1.printStackTrace();
		}
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		
		return wizardKey;
		
		
	}
	
	

}

