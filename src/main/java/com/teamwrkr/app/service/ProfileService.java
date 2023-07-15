package com.teamwrkr.app.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


/**
 * This class contains methods related to the Profile DTO.
 * 
 * @author 		Ted E. Steiner
 * @date		03.01.2023
 */
public class ProfileService {
	
	
	
	public ProfileService() { }
	
	
	
	/**
	 * Bootstrap the twid primary key value of the Profile
	 * object so we can use it to create corresponding records
	 * in other DB tables, such as Points, Scores, Availability,
	 * CalInfo, and Status, where the tables' primary keys
	 * correspond to the Profile PK.
	 * 
	 * @param email
	 * @return int twid
	 */
	public static int getTwid(String email) {
		
		int twid = 0;
		
		String query = "SELECT twid FROM profile WHERE email = '" + email + "'";
		
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
		    	twid = rs.getInt("twid");
		    }
		    else {
		    	System.out.println("ERROR: NO twid FOUND!");
		    }
			conn.close();
		} 
		catch (NamingException e1) {
			e1.printStackTrace();
		}
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		
		return twid;
		
	}
	
	
	
	/**
	 * Check if a profile exists, as identified by a
	 * unique email address. To be used at time of user
	 * registration.
	 * 
	 * @param email
	 * @return boolean
	 */
	public static boolean profileExists(String email) {
		
		boolean exists = false;
		
		/*
		 * Generate SQL.
		 */
		String query = "SELECT email FROM profile WHERE email = '" + email + "'";
		
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
				System.out.println("profileExists(true)");
				exists = true;
			}
			else {
				System.out.println("profileExists(false)");
			}
			conn.close();
		} 
		catch (NamingException e1) {
			e1.printStackTrace();
		}
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		
		return exists;
		
	}
	

}

