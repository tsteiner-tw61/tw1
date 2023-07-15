package com.teamwrkr.app.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * SQL utilities for constructing SQL queries from
 * Java object values.
 * 
 * @author Ted E. Steiner
 * @date 2.28.2023
 *
 */
public class SqlUtils {
	
	
	/**
	 * Wrap a value in a single quote for use in an 
	 * SQL query string.
	 * 
	 * @param str
	 * @return
	 */
	public static String wrapSingleQuote(String str) {
		
		return "'" + str + "'";
		
	}
	
	
	/**
	 * Wrap a value in a single quote for use in an
	 * SQL query string. For use in SQL UPDATE statements.
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static String wrapSingleQuoteOrNull(String str1, String str2) {
		
		if ((str1 == null || str1.isEmpty()) &&
			(str2 == null || str2.isEmpty())) {
			return null;
		}
		
		if (str1 == null) {str1 = "";}
		
		if (!str1.equals(str2)) {
			if (str2 == null) {
				return null;
			}
			str2 = wrapSingleQuote(str2);
			
		}
		else {
			return null;
		}
		
		return str2;
		
	}
	
	
	/**
	 * Wrap a value in a single quote for use in an
	 * SQL query string unless the value is null or 
	 * empty.
	 * 
	 * @param str
	 * @return
	 */
	public static String wrapSingleQuoteOrNull(String str) {
		
		if (str == null || str.isEmpty()) {
			return null;
		}
		str = wrapSingleQuote(str);
		
		return str;
		
	}
	
	
	/**
	 * Wrap a value in a single backtick for use in an
	 * SQL query statement.
	 * 
	 * @param str
	 * @return
	 */
	public static String wrapBackTick(String str) {
		
		return "[" + str + "]";
		
	}
	
	
	/**
	 * Wrap a value in a single backtick for use in an 
	 * SQL query string unless the value is null or empty.
	 * 
	 * @param str
	 * @return
	 */
	public static String wrapBackTickOrNull(String str) {
		
		if (str == null || str.isEmpty()) {
			return null;
		}
		str = wrapBackTick(str);
		
		return str;
		
	}
	
	
	/**
	 * Return a name value pair string.
	 * 
	 * @param name
	 * @param value
	 * @return
	 */
	public static String nameValuePair(String name, String value) {
		
		return name + " = " + value;
		
	}
	
	
	/**
	 * Convert a null or empty string value into an 
	 * 'IS NULL' statement for use in an SQL query.
	 * 
	 * @param str
	 * @return
	 */
	public static String convertNullEmptyToSql(String str) {
		
		if (str == "null" || str.isEmpty()) {
			return "IS NULL";
		}
		return wrapSingleQuote(str);
	}
	
	
	/**
	 * Convert a java Date into an SQL DATE statement.
	 * 
	 * @param date
	 * @return
	 */
	public static String convertToDate(Date date) {
		
		if (date == null) {
			return null;
		}
		
		String sql = "'";
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		sql += formatter.format(date) + "'";
		
		return sql;
		
	}
	
	
	/**
	 * Return today's date in a format for use in an 
	 * SQL query,
	 * 
	 * @return
	 */
	public static String sqlDateStamp() {
		
		String sql = "'";
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		sql += formatter.format(date) + "'";
		
		//System.out.println("x=" + formatter.format(date));

		return sql;
		
	}
	
	/**
	 * Turn MM-dd-yyyy into a Date object.
	 * 
	 * @param formattedDate
	 * @return
	 */
	public static Date getDate1(String formattedDate) {
		
		Calendar cal = Calendar.getInstance();
		String mm = formattedDate.substring(0, 2);
		String dd = formattedDate.substring(3, 5);
		String yyyy = formattedDate.substring(6);
		
		cal.set(Integer.parseInt(yyyy),
				Integer.parseInt(mm) - 1,
				Integer.parseInt(dd));
		
		return cal.getTime();
		
	}
	
	
	/**
	 * Turn MM-yyyy into a Date object.
	 * 
	 * @param formattedDate
	 * @return
	 */
	public static Date getDate2(String formattedDate) {
		
		Calendar cal = Calendar.getInstance();
		String mm = formattedDate.substring(0, 2);
		String yyyy = formattedDate.substring(3);
		cal.set(Calendar.YEAR, Integer.parseInt(yyyy));
		cal.set(Calendar.MONTH, Integer.parseInt(mm) - 1);
		
		return cal.getTime();
		
	}
	
	
	/**
	 * Create a Date object from a formatted date string
	 * of the pattern yyyy-mm-dd.
	 * 
	 * @param formattedDate
	 * @return
	 */
	public static Date getDate3(String formattedDate) {
		
		Calendar cal = Calendar.getInstance();
		String yyyy = formattedDate.substring(0, 4);
		String mm = formattedDate.substring(5, 7);
		String dd = formattedDate.substring(8);
		
		cal.set(Integer.parseInt(yyyy),
				Integer.parseInt(mm) - 1,
				Integer.parseInt(dd));
		
		return cal.getTime();
		
	}
	
	/**
	 * Converts the time of day portion of any Date
	 * object to an integer representation of the
	 * military 24h form.
	 * 
	 * @param date
	 * @return
	 */
	public static int convertTimeToInt(Date date, boolean seconds) {
		
		int time = 0;
		
		Calendar cal = Calendar.getInstance();
		
		int h = date.getHours();
		int m = date.getMinutes();
		int s = date.getSeconds();
		
		String timeString = String.format("%02d", h) + 
							String.format("%02d", m) +
							String.format("%02d", s);
		
		if (!seconds) {
			timeString = timeString.substring(0,4);
		}
		
		return Integer.parseInt(timeString);
		
	}
	
	
	
	public static String convertToString(Date date) {
		
		String dateString = "";
		
		Calendar cal = Calendar.getInstance();
		
		int y = date.getYear() + 1900;
		int m = date.getMonth() + 1;
		int d = date.getDate();
		
		dateString = String.format("%04d", y) + "-" +
							String.format("%02d", m) + "-" +
							String.format("%02d", d);

		
		return dateString;
		
	}
	
	
	public static int millisecondsInDays(int days) {
		
		int millis = 0;
		
		SiteConstants sc = new SiteConstants();
		
		millis = days * sc.MILLIS_PER_SECOND *
						sc.SECONDS_IN_MINUTE *
						sc.MINUTES_IN_HOUR *
						sc.HOURS_IN_DAY;
		
		return millis;
		
	}
	
	
	/**
	 * Return the number of days between the two dates provided.
	 * Dates are stripped of any extraneous time information that
	 * they may contain at the time they are passed into this 
	 * method.
	 * 
	 * Positive return value means second date is after first date.
	 * Negative return value means second date is before first date.
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int daysBetweenDates(Date date1, Date date2) {
		
		int days = 0;
		
		SiteConstants sc = new SiteConstants();
		
		/*
		 * Strip the time information from the dates.
		 */
		Date d1 = new Date(date1.getYear(), date1.getMonth(), date1.getDate());
		long ms1 = d1.getTime();
		Date d2 = new Date(date2.getYear(), date2.getMonth(), date2.getDate());
		long ms2 = d2.getTime();
		
		long diff = ms2 - ms1;
		
		System.out.println("diff = " + diff);
		
		//int df = ((int) ms2 - (int) ms1);
		
		
		long divisor =  sc.MILLIS_PER_SECOND *
						sc.SECONDS_IN_MINUTE *
						sc.MINUTES_IN_HOUR *
						sc.HOURS_IN_DAY;
		
		//int div = (int) divisor;
		
		days = (int) Math.floorDiv(diff, divisor);
		
		System.out.println("days = " + days);
		
		return days;
		
	}
	
	
	public static int millisecondsBetweenTimes(int hhmm1, int hhmm2) {
		
		SiteConstants sc = new SiteConstants();
		
		int millis = 0;
		
		int h1 = Math.floorDiv(hhmm1, 100);
		int m1 = hhmm1 % 100;
		
		int h2 = Math.floorDiv(hhmm2, 100);
		int m2 = hhmm2 % 100;
		
		if (hhmm1 == hhmm2) {return 0;}
		
		if (hhmm1 < hhmm2) {
			
			int h3 = h2 - h1;
			int m3 = m2 - m1;
			
			if (m1 > m2) {
				h3 = h3 - 1;
				m3 = m1 - m2;
			}
			
			millis = (h3 * sc.MINUTES_IN_HOUR * sc.SECONDS_IN_MINUTE * sc.MILLIS_PER_SECOND) +
					 (m3 * sc.SECONDS_IN_MINUTE * sc.MILLIS_PER_SECOND);
		}
		else {
			
			int h4 = 0;
			int h4a = 24 - h1;
			int h4b = h2;
			h4 = h4a + h4b;
			
			int m4 = m2 - m1;
			
			if (m1 > m2) {
				h4 = h4 - 1;
				m4 = m1 - m2;
			}
			
			millis = (h4 * sc.MINUTES_IN_HOUR * sc.SECONDS_IN_MINUTE * sc.MILLIS_PER_SECOND) +
					 (m4 * sc.SECONDS_IN_MINUTE * sc.MILLIS_PER_SECOND);
			
		}
				
		return millis;
		
	}
	

	
	
	
	
	//public static String getDateAndTimeText(String formattedDate) {
		
		//String dateTime = "";
		
		//Calendar cal = Calendar.getInstance().
		
		
		//return dateTime;
		
	//}
	
	

}

