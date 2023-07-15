package com.teamwrkr.app.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.IOException;

import com.teamwrkr.app.dto.Profile;
import com.teamwrkr.app.manager.PointsManager;
import com.teamwrkr.app.service.DataService;
import com.teamwrkr.app.util.SiteConstants;


/**
 * Servlet implementation class FileUploadServlet
 */

@WebServlet(name = "FileUploadServlet", urlPatterns = { "/FileuploadServlet" })
@MultipartConfig(
  fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
  maxFileSize = 1024 * 1024 * 10,      // 10 MB
  maxRequestSize = 1024 * 1024 * 10   // 10 MB
)
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public FileUploadServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/* 
		 * Receive file uploaded to the Servlet from the HTML5 form 
		 */
		System.out.println("FileUploadServlet.doPost()");
		
		SiteConstants sc = new SiteConstants();
		
		HttpSession session = request.getSession();
	    int twid = (int) session.getAttribute("twid");
	    System.out.println("session.getAttribute(twid) = " + twid);
		
	    Part filePart = request.getPart("file");
	    String fileName = filePart.getSubmittedFileName();
	    
		/*
		 * Save the file to the filesystem.
		 */
	    for (Part part : request.getParts()) {
	    	
		    /*
		     * Production environment.
		     *
			part.write("/usr/local/tomcat10/webapps/tw1/data/" + fileName);
			 */
	    	
	    	/*
	    	 * Development environment.
	    	 */
	    	part.write("C:\\Users\\ted00\\eclipse-workspace\\tw1\\src\\main\\webapp\\img\\" + fileName);
	    	//System.out.println("filename=" + fileName);

	    }
	    
	    Profile profile = new Profile();
	    profile.setTwid(twid);
	    profile.setPhotoImg(fileName);
	    Profile profile2 = new Profile();
	    profile2.setTwid(twid);
	    DataService.update(profile, profile2);
	    
	    PointsManager.addPoints(twid, sc.ACTION_POINTS_PROFILE_PHOTO);
	    
	}

}

