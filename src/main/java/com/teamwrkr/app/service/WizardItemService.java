package com.teamwrkr.app.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.teamwrkr.app.dto.WizardItem;
import com.teamwrkr.app.util.SqlUtils;

/**
 * Service defining classes responsible for getting
 * content objects to be used in the profile wizard
 * display sequence.
 * 
 * TODO update this to the JPA structure when implemented
 * 
 * @author Ted E. Steiner
 * @date 2.17.2023
 *
 */
public class WizardItemService {

	
	public WizardItemService() { }
	
	/**
	 * WizardItem sequence is determined within the current
	 * category hierarchy the user has compiled with their
	 * answers to previous questions.
	 * 
	 * @param category1
	 * @param category2
	 * @param category3
	 * @param sequence
	 * @return WizardItem
	 */
	public static WizardItem selectWizardItem(String category1, String category2, String category3, int sequence) {
		
		WizardItem wizardItem = new WizardItem();
		
		/*
		 * Generate SQL.
		 */
		String query = "SELECT * FROM wizarditem WHERE ";
		String sqlCat1 = "category1 = '" + category1 + "' AND ";
		String sqlCat2 = "category2 = '" + category2 + "' AND ";
		String sqlCat3 = "category3 = '" + category3 + "' AND ";
		if (category1 == null || category1.equals("null")) {
			sqlCat1 = "category1 IS NULL AND ";
		}
		if (category2 == null || category2.equals("null")) {
			sqlCat2 = "category2 IS NULL AND ";
		}
		if (category3 == null || category3.equals("null")) {
			sqlCat3 = "category3 IS NULL AND ";
		}
		
		String sqlSeq = "sequence = " + sequence;
		query += sqlCat1;
		query += sqlCat2;
		query += sqlCat3;
		query += sqlSeq;
		
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
		    	wizardItem.setId(rs.getInt("id"));
			    wizardItem.setCategory1(rs.getString("category1"));
			    wizardItem.setCategory2(rs.getString("category2"));
			    wizardItem.setCategory3(rs.getString("category3"));
			    wizardItem.setSequence(rs.getInt("sequence"));
			    wizardItem.setQuestion(rs.getString("question"));
			    wizardItem.setText(rs.getString("text"));      
			    wizardItem.setAnswer1(rs.getString("answer1"));			        
			    wizardItem.setAnswer2(rs.getString("answer2"));			        
			    wizardItem.setAnswer3(rs.getString("answer3"));		        
			    wizardItem.setAnswer4(rs.getString("answer4"));			        
			    wizardItem.setAnswer5(rs.getString("answer5"));		        
			    wizardItem.setAnswer6(rs.getString("answer6"));		        
			    wizardItem.setAnswer7(rs.getString("answer7"));		        
			    wizardItem.setAnswer8(rs.getString("answer8"));			        
			    wizardItem.setAnswer9(rs.getString("answer9"));			        
			    wizardItem.setAnswer10(rs.getString("answer10"));			        
			    wizardItem.setImg(rs.getString("img"));
		    }
		    else {
		    	wizardItem = null;
		    }
			conn.close();
		} 
		catch (NamingException e1) {
			e1.printStackTrace();
		}
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		
		return wizardItem;
		
	}

}

