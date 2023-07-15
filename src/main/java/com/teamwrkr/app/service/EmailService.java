package com.teamwrkr.app.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.teamwrkr.app.web.Email;

public class EmailService {
	
	private String rnd6;
	
	
	public EmailService() { }
	
	/*
	 * Generate random 6-digit verification code.
	 */
    public static String getRandom() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%06d", number);
    }

    /*
     * Send email to the new user.
     */
    public static boolean sendEmail(String email, Email em) {
    	
        boolean test = false;

        String toEmail = email;
        //String fromEmail = "ted006188@yahoo.com";
        //String password = "vxmawoanyvpcfcuz";
        //String smtpHostYahoo = "smtp.mail.yahoo.com";
        //String smtpPortYahoo = "587";
        //String smtpAuthYahoo = "true";
        //String smtpStartTlsEnableYahoo = "true";
        
        String fromEmailTw = "reviews@teamwrkr.com";
        String passwordTw = "zfjqdrykdzrygzzt";
        String smtpHostTw = "smtp.office365.com";
        String smtpPortTw = "587";
        String smtpAuthTw = "true";
        String smtpStartTlsEnableTw = "true";


        try {

    	    /*
       	     * Host email SMTP server details.
   	     */
            Properties pr = new Properties();
            pr.setProperty("mail.smtp.host", smtpHostTw);
            pr.setProperty("mail.smtp.port", smtpPortTw);
            pr.setProperty("mail.smtp.auth", smtpAuthTw);
            pr.setProperty("mail.smtp.starttls.enable", smtpStartTlsEnableTw);
            pr.put("mail.smtp.socketFactory.port", "587");
            pr.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

 
            /*
             * Get session to authenticate the host
             * email address and password.
             */
            Session session = Session.getInstance(pr, new Authenticator() {

                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmailTw, passwordTw);
                }

            });


            /*
             * Set email message details.
             */
            Message mess = new MimeMessage(session);

            /*
             * Set from email address.
             */
            mess.setFrom(new InternetAddress(fromEmailTw));
            
            /*
             * Set destination email address.
             */
            mess.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
    		
            /*
             * Set email subject.
             */
            mess.setSubject("A Teamwrkr Requests Your Review");
            
            /*
             * Set email content to be HTML.
             */
            mess.setContent(em.getHTML(), "text/html");

            System.out.println("Sending email to " + email);

            /*
             * Send the message.
             */
            Transport.send(mess);
            
            test = true;
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return test;
    }
    
    
    
    /**
     * Send email to the Teamwrkr survey recipient.
     * 
     * TODO test this and use for both development and production
     * 
     * @param email
     * @param subject
     * @return (success) 6-digit code
     * @return (failure) empty string
     */
    public String sendEmailDevelopment(String email, String subject) {
    	
    	boolean success = true;
    	
        String toEmail = email;
        
        String fromEmailTw = "reviews@teamwrkr.com";
        String passwordTw = "zfjqdrykdzrygzzt";
        String smtpHostTw = "smtp.office365.com";
        String smtpPortTw = "587";
        String smtpAuthTw = "true";
        String smtpStartTlsEnableTw = "true";


        try {

    	    /*
       	     * Host email SMTP server details.
       	     */
            Properties pr = new Properties();
            pr.setProperty("mail.smtp.host", smtpHostTw);
            pr.setProperty("mail.smtp.port", smtpPortTw);
            pr.setProperty("mail.smtp.auth", smtpAuthTw);
            pr.setProperty("mail.smtp.starttls.enable", smtpStartTlsEnableTw);
            pr.put("mail.smtp.socketFactory.port", "587");
            pr.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

 
            /*
             * Get session to authenticate the host
             * email address and password.
             */
            Session session = Session.getInstance(pr, new Authenticator() {

                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmailTw, passwordTw);
                }

            });

            Message mess = new MimeMessage(session);
            mess.setFrom(new InternetAddress(fromEmailTw));
            mess.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            mess.setSubject(subject);
            mess.setContent(getHTML(), "text/html");
            System.out.println("Sending email to " + email);
            Transport.send(mess);
            
            
        } 
        catch (Exception e) {
        	success = false;
            e.printStackTrace();
        }
        
        if (success) {
        	return this.rnd6;
        }

        return "";
        
    }
	
    
    /**
     * Iterate through the steps involved to read the static HTML
     * email content files and insert any necessary dynamic content.
     * 
     * @return HTML
     */
    public String getHTML() {
    	
    	String code = ""; 
    	String HTML = "";
    	
    	try {
    		code = readHTML();
    		HTML = insertVerificationCode(code);
    	}
    	catch (FileNotFoundException e1) {
    		e1.printStackTrace();
    	}
    	catch (IOException e2) {
    		e2.printStackTrace();
    	}
    	catch (Exception e3) {
    		e3.printStackTrace();
    	}
 
    	return HTML;
    	
    }
    
    
    /**
     * Iterate through the steps involved to read the static HTML
     * email content files and insert any necessary dynamic content.
     * 
     * @param emno - int specifies which email content file to read
     * @return HTML
     */
    public String getHTMLFor(int emno) {
    	
    	
    	
    	String HTML = "";
    	
    	try {
    		
    		HTML = readHTML();
    		
    		/*
    		 * Survey
    		 */
    		if (emno == 1) {
    			HTML = insertName("fn", "ln", HTML);
    			HTML = insertSenderEmail("from@from.com", HTML);
    			HTML = insertSurveyId(1, HTML);
    		}
    		
    		/*
    		 * Email Verification Code
    		 */
    		if (emno == 2) {
    			HTML = insertVerificationCode(HTML);
    		}
    		
    		/*
    		 * TODO add here as necessary
    		 */
    		
    	}
    	catch (FileNotFoundException e1) {
    		e1.printStackTrace();
    	}
    	catch (IOException e2) {
    		e2.printStackTrace();
    	}
    	catch (Exception e3) {
    		e3.printStackTrace();
    	}
    	
    	return HTML;
    	
    }
    
    
    /**
     * Read the HTML email content from the static HTML files.
     * 
     * @return HTML
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static String readHTML() throws FileNotFoundException, IOException {
    	
    	String HTML = "";

    	try {
    		File file = new File("C:\\Users\\ted00\\eclipse-workspace\\tw1\\src\\main\\webapp\\emailVerificationCode.html");
    		
    		
    		//File file = new File("/usr/local/tomcat10/webapps/tw1/html/emailVerificationCode.html");
    		
        	BufferedReader br = new BufferedReader(new FileReader(file));

        	StringBuilder sb = new StringBuilder();
        	
        	String st = "";
        	while ((st = br.readLine()) != null) {
        		sb.append(st + "\n");
        	}
        	HTML = sb.toString();
    	}
    	catch (FileNotFoundException e1) {
    		e1.printStackTrace();
    	}
    	catch (IOException e2) {
    		e2.printStackTrace();
    	}
    	catch (Exception e3) {
    		e3.printStackTrace();
    	}
 
    	return HTML;
    	
    }
    
    
    /**
     * Generate the random siz-digit email verification code
     * and insert it into the HTML email page to be sent.
     * 
     * @param HTML
     * @return HTML
     */
    private String insertVerificationCode(String HTML) {
    	
    	this.rnd6 = getRandom();
    	
    	return HTML.replaceAll("VERIFICATION-CODE", this.rnd6);
    	
    }
    
    
    /**
     * Pass in the survey id and insert it into the HTML email
     * page to be sent.
     * 
     * @param sid
     * @param HTML
     * @return HTML
     */
    private String insertSurveyId(int sid, String HTML) {
    	
    	return HTML.replaceAll("SURVEY-ID", Integer.toString(sid));
    	
    }
    
    
    /**
     * Pass in the first and last name of the Teamwrkr being reviewed
     * and insert it into the HTML email page to be sent.
     * 
     * @param fn
     * @param ln
     * @param HTML
     * @return HTML
     */
    private String insertName(String fn, String ln, String HTML) {
    	
    	String out = HTML.replaceAll("FIRST-NAME", fn);
    	out =  out.replaceAll("LAST-NAME", ln);
    	return out;
    	
    }
      
    
    /**
     * Pass in the email address of the Teamwrkr being reviewed and
     * insert it into the HTML email page to be sent.
     * 
     * @param email
     * @param HTML
     * @return HTML
     */
    private String insertSenderEmail(String email, String HTML) {
    	
    	return HTML.replaceAll("SENDER-EMAIL", email);
    	
    }
                
      
    /*
     * Access methods
     */
    public String getRnd6() {
        return rnd6;
    }
    public void setRnd6(String rnd6) {
        this.rnd6 = rnd6;
    }
    	
    	
       
        

	

}

