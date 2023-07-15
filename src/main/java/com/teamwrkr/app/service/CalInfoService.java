package com.teamwrkr.app.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.teamwrkr.app.dto.CalInfo;
import com.teamwrkr.app.util.SqlUtils;


/**
 * This class sets the dates for the 28 day calendar
 * associated with a Teamwrkr's Availablity matrix.
 * 
 * @author 		Ted E. Steiner
 * @date		03.01.2023
 */
public class CalInfoService {
	
	
	
	public CalInfoService() { }
	
	
	
	/**
	 * Save the dates of the most recent calendar
	 * for the current user.
	 * 
	 * @param twid
	 */
	public static void saveCalInfo(int twid) {
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_WEEK, -(cal.get(Calendar.DAY_OF_WEEK)-1)); 
		
		/*
		 * Generate SQL.
		 */
		String query = "INSERT INTO calendar ";
		
		String[] sql = new String[4];
		sql[0] = SqlUtils.wrapBackTickOrNull("twid");
		sql[1] = SqlUtils.wrapBackTickOrNull("startYear");
		sql[2] = SqlUtils.wrapBackTickOrNull("startMonth");
		sql[3] = SqlUtils.wrapBackTickOrNull("startDate");
	
		String[] val = new String[4];
		val[0] = Integer.toString(twid);;
		val[1] = Integer.toString(cal.get(Calendar.YEAR));
		val[2] = Integer.toString(cal.get(Calendar.MONTH)+1);
		val[3] = Integer.toString(cal.get(Calendar.DATE));
		
		/*
		 * Create COLUMS, VALUES sql.
		 */
		String sqlColumns = "(";
		String sqlValues = " VALUES (";
		 for (int i = 0; i < 4; i++) {
			 if (i < 3 && val[i] != null) {
				sqlColumns += sql[i] + ", ";
				sqlValues += val[i] + ", ";
			}
			if (i == 3) {
				sqlColumns += sql[i];
				sqlValues += val[i];
			}
		}
		sqlColumns += ")";
		sqlValues += ")";
		
		query += sqlColumns + sqlValues;
		
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
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.executeUpdate();
			conn.close();
		} 
		catch (NamingException e1) {
			e1.printStackTrace();
		}
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		
	}
	
	
	
	/**
	 * Select the calendar table for the appropriate
	 * twid, which determines the start date for the
	 * user's calendar.
	 * 
	 * @param twid
	 * @return
	 */
	public static CalInfo selectCalInfo(int twid) {
		
		CalInfo calInfo = new CalInfo();
		
		/*
		 * Generate SQL.
		 */
		String query = "SELECT * FROM calendar WHERE ";
		String sql1 = "twid = " + twid;
		
		query += sql1;
		
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
		    	calInfo.setTwid(rs.getInt("twid"));
		    	calInfo.setStartYear(rs.getInt("startYear"));
		    	calInfo.setStartMonth(rs.getInt("startMonth"));
		    	calInfo.setStartDate(rs.getInt("startDate"));
		    }
		    else {
		    	calInfo = null;
		    }
			conn.close();
		} 
		catch (NamingException e1) {
			e1.printStackTrace();
		}
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		
		return calInfo;
		
	}
	
	

}

