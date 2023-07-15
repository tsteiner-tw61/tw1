package com.teamwrkr.app.service;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.teamwrkr.app.dto.ActionPoints;
import com.teamwrkr.app.dto.Availability;
import com.teamwrkr.app.dto.CalInfo;
import com.teamwrkr.app.dto.Contact;
import com.teamwrkr.app.dto.Mission;
import com.teamwrkr.app.dto.Notification;
import com.teamwrkr.app.dto.Points;
import com.teamwrkr.app.dto.Profile;
import com.teamwrkr.app.dto.Scores;
import com.teamwrkr.app.dto.Status;
import com.teamwrkr.app.dto.Survey;
import com.teamwrkr.app.dto.Tx;
import com.teamwrkr.app.util.SqlUtils;

/**
 * Class for all actions that interact with the
 * application DataSource. Designed to work with 
 * generic DTOs.
 * 
 * @author Ted E. Steiner
 * @date 3.19.2023
 *
 */
public class DataService {
	
	
	public DataService() { }
	
	
	/**
	 * Get the database table name from the DTO
	 * class name.
	 * 
	 * @param c
	 * @return String
	 */
	public static String getTableName(Class c) {
		
		String tableName = "";
		
		if (c.getSimpleName().equals("CalInfo")) {
			tableName = "calendar";
			return tableName;
		}
		if (c.getSimpleName().equals("ActionPoints")) {
			tableName = "actionpoints";
			return tableName;
		}
		
		/*
		 * For most DB tables, the naming convention is to
		 * be the same as the DTO class name with the first
		 * letter lowercase.
		 * 
		 * Exceptions appear above this code block.
		 */
		String ch1 = c.getSimpleName().substring(0,1).toLowerCase();
		String chs = c.getSimpleName().substring(1);
		
		tableName = ch1 + chs;
		
		return tableName;
		
	}
	
	
	
	/**
	 * Select a record from the DataSource.
	 * 
	 * @param obj - object with id provided
	 * @return
	 */
	public static Object selectOne(Object obj) {
		
		try {
			
			Class c = obj.getClass();
			Field[] fields = c.getDeclaredFields();
			
			/*
			 * Generate SQL.
			 */
			String query = "SELECT * FROM " + getTableName(c) + " WHERE ";

			fields[0].setAccessible(true);
			
			/*
			 * Generate more SQL.
			 */
			query += fields[0].getName() + " = " + (int) fields[0].getInt(obj);
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
			    	
			    	for(int i = 0; i < fields.length; i++) {
						fields[i].setAccessible(true);
						
						if (fields[i].getType().getTypeName().equals("java.lang.String")) {
							String value = rs.getString(fields[i].getName());
							fields[i].set(obj, value);
						}
						if (fields[i].getType().getTypeName().equals("java.util.Date")) {
							Date value = rs.getDate(fields[i].getName());
							fields[i].set(obj, value);
						}
						
						if (fields[i].getType().getTypeName().equals("int")) {
							int value = rs.getInt(fields[i].getName());
							fields[i].set(obj, value);
						}
						
						if (fields[i].getType().getTypeName().equals("long")) {
							long value = rs.getLong(fields[i].getName());
							fields[i].set(obj, value);
						}
						
						if (fields[i].getType().getTypeName().equals("boolean")) {
							boolean value = rs.getBoolean(fields[i].getName());
							fields[i].set(obj, value);
						}
						
					}
			    	
			    }
			    else {
			    	obj = null;
			    }
				conn.close();
				
			} 
			catch (NamingException e1) {
				e1.printStackTrace();
			}
			catch (SQLException e2) {
				e2.printStackTrace();
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return obj;
		
	}
	
	
	
	/**
	 * Select a record from the DataSource.
	 * 
	 * @param obj - object with id provided
	 * @param field - ordinal index number of the field on which the SQL query conditional is based
	 * 					if it is not 0. If based on 0, use selectOne(Object) above.
	 * @return Object
	 */
	public static Object selectOne(Object obj, int field) {
		
		try {
			
			Class c = obj.getClass();
			Field[] fields = c.getDeclaredFields();
			
			/*
			 * Generate SQL.
			 */
			String query = "SELECT * FROM " + getTableName(c) + " WHERE ";

			fields[field].setAccessible(true);
			
			/*
			 * Generate more SQL.
			 */
			query += fields[field].getName() + " = " + (int) fields[field].getInt(obj);
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
			    	
			    	for(int i = 0; i < fields.length; i++) {
						fields[i].setAccessible(true);
						
						if (fields[i].getType().getTypeName().equals("java.lang.String")) {
							String value = rs.getString(fields[i].getName());
							fields[i].set(obj, value);
						}
							
						if (fields[i].getType().getTypeName().equals("java.util.Date")) {
							Date value = rs.getDate(fields[i].getName());
							fields[i].set(obj, value);
						}
						
						if (fields[i].getType().getTypeName().equals("int")) {
							int value = rs.getInt(fields[i].getName());
							fields[i].set(obj, value);
						}
						
						if (fields[i].getType().getTypeName().equals("long")) {
							long value = rs.getLong(fields[i].getName());
							fields[i].set(obj, value);
						}
						
						if (fields[i].getType().getTypeName().equals("boolean")) {
							boolean value = rs.getBoolean(fields[i].getName());
							fields[i].set(obj, value);
						}

					}
			    	
			    }
			    else {
			    	obj = null;
			    }
				conn.close();
			} 
			catch (NamingException e1) {
				e1.printStackTrace();
			}
			catch (SQLException e2) {
				e2.printStackTrace();
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return obj;
		
	}
	
	
	
	/**
	 * Select one or more records from the DataSource.
	 * 
	 * @param obj - object with id provided
	 * @param qs - query string after WHERE, free text
	 * @return
	 */
	public static Object[] selectMany(Object obj, String qs) {
		
		List<Object> objs = new ArrayList<Object>();
		
		try {
			Class c = obj.getClass();
			Field[] fields = c.getDeclaredFields();

			/*
			 * Generate SQL.
			 */
			String query = "SELECT * FROM " + getTableName(c) + " WHERE ";

			fields[0].setAccessible(true);

			/*
			 * Generate more SQL.
			 */
			query += qs;
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
			    Calendar cal1 = Calendar.getInstance();
			    long ts1 = cal1.getTimeInMillis();
			    ResultSet rs = stmt.executeQuery(query);
			    Calendar cal2 = Calendar.getInstance();
			    long ts2 = cal2.getTimeInMillis();
			    int j = 0;
			    while (rs.next()) {
			    	
			    	Object obj2 = obj;
					Class c2 = obj2.getClass();
					Field[] fields2 = c2.getDeclaredFields();
			    	
			    	for(int i = 0; i < fields2.length; i++) {
						fields2[i].setAccessible(true);
						
						if (fields2[i].getType().getTypeName().equals("java.lang.String")) {
							String value = rs.getString(fields2[i].getName());
							fields2[i].set(obj2, value);
						}
							
						if (fields2[i].getType().getTypeName().equals("java.util.Date")) {
							Date value = rs.getDate(fields2[i].getName());
							fields2[i].set(obj2, value);
						}
						
						if (fields2[i].getType().getTypeName().equals("int")) {
							int value = rs.getInt(fields2[i].getName());
							fields2[i].set(obj2, value);
						}
						
						if (fields2[i].getType().getTypeName().equals("long")) {
							long value = rs.getLong(fields2[i].getName());
							fields2[i].set(obj2, value);
						}
						
						if (fields2[i].getType().getTypeName().equals("boolean")) {
							boolean value = rs.getBoolean(fields2[i].getName());
							fields2[i].set(obj2, value);
						}
						
					}
			    	
			    	if (obj.getClass().getSimpleName().equals("Mission")) {
			    		Mission mm = (Mission) obj2;
				    	Mission mmm = new Mission(mm);
				    	objs.add(mmm);
			    	}
			    	if (obj.getClass().getSimpleName().equals("Notification")) {
			    		Notification not = (Notification) obj2;
			    		Notification not2 = new Notification(not);
				    	objs.add(not2);
			    	}
			    	if (obj.getClass().getSimpleName().equals("Contact")) {
			    		Contact ct = (Contact) obj2;
			    		Contact ct2 = new Contact(ct);
				    	objs.add(ct2);
			    	}
			    	if (obj.getClass().getSimpleName().equals("Survey")) {
			    		Survey sv = (Survey) obj2;
			    		Survey sv2 = new Survey(sv);
			    		objs.add(sv2);
			    	}
			    	if (obj.getClass().getSimpleName().equals("Scores")) {
			    		Scores sc = (Scores) obj2;
			    		Scores sc2 = new Scores(sc);
			    		objs.add(sc2);
			    	}
			    	if (obj.getClass().getSimpleName().equals("Profile")) {
			    		Profile pf = (Profile) obj2;
			    		Profile pf2 = new Profile(pf);
			    		objs.add(pf2);
			    	}
			    	if (obj.getClass().getSimpleName().equals("Status")) {
			    		Status st = (Status) obj2;
			    		Status st2 = new Status(st);
			    		objs.add(st2);
			    	}
			    	if (obj.getClass().getSimpleName().equals("Points")) {
			    		Points pts = (Points) obj2;
			    		Points pts2 = new Points(pts);
			    		objs.add(pts2);
			    	}
			    	if (obj.getClass().getSimpleName().equals("ActionPoints")) {
			    		ActionPoints apts = (ActionPoints) obj2;
			    		ActionPoints apts2 = new ActionPoints(apts);
			    		objs.add(apts2);
			    	}
			    	if (obj.getClass().getSimpleName().equals("Tx")) {
			    		Tx tx = (Tx) obj2;
			    		Tx tx2 = new Tx(tx);
			    		objs.add(tx2);
			    	}
			    	if (obj.getClass().getSimpleName().equals("Availability")) {
			    		Availability availability = (Availability) obj2;
			    		Availability availability2 = new Availability(availability);
			    		objs.add(availability2);
			    	}
			    	if (obj.getClass().getSimpleName().equals("CalInfo")) {
			    		CalInfo ci = (CalInfo) obj2;
			    		CalInfo ci2 = new CalInfo(ci);
			    		objs.add(ci2);
			    	}
			    	
			    	j++;
			    	
			    }
			    
			    Calendar cal3 = Calendar.getInstance();
		    	long ts3 = cal3.getTimeInMillis();
		    	
		    	System.out.println("query(db) time = " + (ts2-ts1) + " ms");
		    	System.out.println("query(rs=" + j + ") time = " + (ts3-ts2) + " ms");
			    
				conn.close();
			} 
			catch (NamingException e1) {
				e1.printStackTrace();
			}
			catch (SQLException e2) {
				e2.printStackTrace();
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return objs.toArray();
		
	}
	
	
	/**
	 * Create a database record based on a DTO
	 * object.
	 * 
	 * @param obj
	 */
	public static void create(Object obj) {
		
		
		try {
			
			Class c = obj.getClass();
			Field[] fields = c.getDeclaredFields();
			
			/*
			 * Generate SQL.
			 */
			String query = "INSERT INTO ";
			query += getTableName(c) + " ";
			
			String[] sql = new String[fields.length];
			String[] val = new String[fields.length];
			
			for(int i = 0; i < fields.length; i++) {
				fields[i].setAccessible(true);
				sql[i] = SqlUtils.wrapBackTickOrNull(fields[i].getName());
				
				if (fields[i].getType().getTypeName().equals("java.lang.String")) {
					String value = (String) fields[i].get(obj);
					val[i] = SqlUtils.wrapSingleQuoteOrNull(value);
				}
					
				if (fields[i].getType().getTypeName().equals("java.util.Date")) {
					Date value = (Date) fields[i].get(obj);	
					val[i] = SqlUtils.convertToDate(value);
				}
				
				if (fields[i].getType().getTypeName().equals("int")) {
					int value = (int) fields[i].get(obj);	
					val[i] = Integer.toString(value);
				}
				
				if (fields[i].getType().getTypeName().equals("long")) {
					long value = (long) fields[i].get(obj);	
					val[i] = Long.toString(value);
				}
				
				if (fields[i].getType().getTypeName().equals("boolean")) {
					boolean value = (boolean) fields[i].get(obj);	
					val[i] = SqlUtils.wrapSingleQuote(Boolean.toString(value));
				}
				
			}
			
			/*
			 * Create COLUMS, VALUES sql.
			 */
			String sqlColumns = "(";
			String sqlValues = " VALUES (";
			int cols = 0;
			for (int i = 1; i < fields.length; i++) {
				
				if (val[i] != null) {
					if (cols == 0) {
						sqlColumns += sql[i];
						sqlValues += val[i];
					}
					if (cols >= 1) {
						sqlColumns += ", " + sql[i];
						sqlValues += ", " + val[i];
					}
					cols = cols + 1;
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
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	/**
	 * Create object. Alternate. Not sure what this was for.
	 * 
	 * @param obj
	 * @param pk
	 */
	public static void create(Object obj, boolean pk) {
		
		try {
			
			Class c = obj.getClass();
			Field[] fields = c.getDeclaredFields();
			
			/*
			 * Generate SQL.
			 */
			String query = "INSERT INTO ";
			query += getTableName(c) + " ";
			
			String[] sql = new String[fields.length];
			String[] val = new String[fields.length];
			
			for (int i = 0; i < fields.length; i++) {
				fields[i].setAccessible(true);
				sql[i] = SqlUtils.wrapBackTickOrNull(fields[i].getName());
				
				if (fields[i].getType().getTypeName().equals("java.lang.String")) {
					String value = (String) fields[i].get(obj);
					val[i] = SqlUtils.wrapSingleQuoteOrNull(value);
				}
					
				if (fields[i].getType().getTypeName().equals("java.util.Date")) {
					Date value = (Date) fields[i].get(obj);	
					val[i] = SqlUtils.convertToDate(value);
				}
				
				if (fields[i].getType().getTypeName().equals("int")) {
					int value = (int) fields[i].get(obj);	
					val[i] = Integer.toString(value);
				}
				
				if (fields[i].getType().getTypeName().equals("long")) {
					long value = (long) fields[i].get(obj);	
					val[i] = Long.toString(value);
				}
				
				if (fields[i].getType().getTypeName().equals("boolean")) {
					boolean value = (boolean) fields[i].get(obj);	
					val[i] = SqlUtils.wrapSingleQuote(Boolean.toString(value));
				}
				
			}
			
			
			/*
			 * Create COLUMS, VALUES sql.
			 */
			String sqlColumns = "(";
			String sqlValues = " VALUES (";
			int cols = 0;
			
			for (int i = 0; i < fields.length; i++) {
				if (val[i] != null) {
					if (cols == 0) {
						sqlColumns += sql[i];
						sqlValues += val[i];
					}
					if (cols >= 1) {
						sqlColumns += ", " + sql[i];
						sqlValues += ", " + val[i];
					}
					cols = cols + 1;
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
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	/**
	 * Update a database record for a given DTO.
	 * 
	 * @param obj
	 * @param obj2
	 */
	public static void update(Object obj, Object obj2) {
		
		
		try {
			
			Class c = obj.getClass();
			Field[] fields = c.getDeclaredFields();
			
			/*
			 * Get existing object.
			 * Set up an array to compare the update object with.
			 */
			Object obj3 = selectOne(obj2);
			Class c3 = obj3.getClass();
			Field[] fields3 = c3.getDeclaredFields();
			
			String[] existing = new String[fields3.length];
			
			
			
			for(int i = 0; i < fields3.length; i++) {
				fields3[i].setAccessible(true);
				
				if (fields3[i].getType().getTypeName().equals("java.lang.String")) {
					String value = (String) fields3[i].get(obj3);
					existing[i] = value;
				}
					
				if (fields3[i].getType().getTypeName().equals("java.util.Date")) {
					Date value = (Date) fields3[i].get(obj3);	
					existing[i] = SqlUtils.convertToDate(value);
				}
				
				if (fields3[i].getType().getTypeName().equals("int")) {
					int value = (int) fields3[i].get(obj3);	
					existing[i] = Integer.toString(value);
				}
				
				if (fields3[i].getType().getTypeName().equals("long")) {
					long value = (long) fields3[i].get(obj3);	
					existing[i] = Long.toString(value);
				}
				
				if (fields3[i].getType().getTypeName().equals("boolean")) {
					boolean value = (boolean) fields3[i].get(obj3);	
					existing[i] = Boolean.toString(value);
				}
				
			}
			
			System.out.println("CURRENT OBJECT");
			for (int idx = 0; idx < existing.length; idx++) {
				System.out.println(fields3[idx].getName() + "=" + existing[idx]);
			}
			
			/*
			 * Generate SQL.
			 */
			String query = "UPDATE ";
			query += getTableName(c) + " SET ";
			
			String[] sql = new String[fields.length];
			String[] val = new String[fields.length];
			
			//String[] values = new String[fields.length];
			
			
			for(int i = 0; i < fields.length; i++) {
				fields[i].setAccessible(true);
				
				System.out.println("fieldn=" + fields[i].getName());
				
				sql[i] = SqlUtils.wrapBackTickOrNull(fields[i].getName());
				
				if (fields[i].getType().getTypeName().equals("java.lang.String")) {
					String value = (String) fields[i].get(obj);
					//values[i] = value;
					val[i] = SqlUtils.wrapSingleQuoteOrNull(existing[i], value);
				}
					
				if (fields[i].getType().getTypeName().equals("java.util.Date")) {
					Date value = (Date) fields[i].get(obj);	
					//values[i] = value.toString();
					val[i] = SqlUtils.convertToDate(value);
				}
				
				if (fields[i].getType().getTypeName().equals("int")) {
					int value = (int) fields[i].get(obj);	
					//values[i] = Integer.toString(value);
					val[i] = Integer.toString(value);
				}
				
				if (fields[i].getType().getTypeName().equals("long")) {
					long value = (long) fields[i].get(obj);	
					//values[i] = Long.toString(value);
					val[i] = Long.toString(value);
				}
				
				if (fields[i].getType().getTypeName().equals("boolean")) {
					boolean value = (boolean) fields[i].get(obj);	
					val[i] = SqlUtils.wrapSingleQuote(Boolean.toString(value));
				}
				
			}
			
			System.out.println("NEW OBJECT");
			for (int idx = 0; idx < existing.length; idx++) {
				if (val[idx] != null) {
					System.out.println(fields[idx].getName() + "=" + val[idx]);
				}
				else {
					System.out.println(fields[idx].getName() + " IS NULL");
				}
				
			}
			
			
			/*
			 * Create VALUES to SET
			 */
			String sqlSet = "";
			for (int i = 1; i < fields.length; i++) {
				if (val[i] != null) {
					sqlSet += sql[i] + " = ";
					sqlSet += val[i] + ", ";
				}
			}
			
			/*
			 * Do an empty sqlSet check here.
			 */
			if (sqlSet.length() >= 2) {
				sqlSet = sqlSet.substring(0,sqlSet.length()-2);
			}
			
			query += sqlSet;
			
			if (sqlSet.isBlank()) {return;}
			
			query += " WHERE " + fields[0].getName() + " = ";
			query += (int) fields[0].get(obj);
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
		catch (Exception e) {
			e.printStackTrace();		
		}
		
	}

	
	
}

