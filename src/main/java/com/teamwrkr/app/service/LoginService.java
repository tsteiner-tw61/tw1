package com.teamwrkr.app.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class LoginService {
	
	
	
	public LoginService() { } 
	
	
	
	public static String encrypt(String plaintext) {
		
		Base64.Encoder encoder = Base64.getEncoder(); 
		String hash = encoder.encodeToString(plaintext.getBytes());  
		//System.out.println("encoded = " + hash);

		return hash;
		
	}
	

	public static String decode(String base64text){
		
		Base64.Decoder decoder = Base64.getDecoder();  
        String dStr = new String(decoder.decode(base64text));  
        //System.out.println("Decoded string: " + dStr);  
		
		return dStr;
	}
	
	
	
	public static boolean loginSuccessful(String email, String pwd) {
		
		
		boolean success = false;

		String encPwd = encrypt(pwd);
		
		/*
		 * Generate SQL.
		 */
		String query = "SELECT email, encpwd FROM profile WHERE email = '" + email + "'";
		System.out.println("LoginService - SQL QUERY - [" + query + "]");  
		
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
				String dbEncPwd = rs.getString("encpwd");
				//System.out.println("dbEncPwd=" + dbEncPwd);
				if (encPwd.equals(dbEncPwd)) {
					success = true;	
					System.out.println("LoginService - loginSuccessful(true)");
				}
				else {
					success = false;
					System.out.println("LoginService - loginFailedNoMatch(" + encPwd + "," + dbEncPwd + ")");
				}
			}
			else {
				System.out.println("LoginService - loginSuccessful(false)");
			}
			conn.close();
		} 
		catch (NamingException e1) {
			e1.printStackTrace();
		}
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		
		
		return success;
		
	}
	
	

}

