package com.teamwrkr.app.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import com.teamwrkr.app.dto.Profile;
import com.teamwrkr.app.manager.RolodexManager;

/**
 * Servlet implementation class RolodexServlet
 */
public class RolodexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RolodexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		/*
		 * Test console output for doPost() call.
		 * Comment out in production.
		 */
		System.out.println("call RolodexServlet.doPost()");
		
		HttpSession session = request.getSession();
		
		
		/*
		 * Get query parameter (navigation option).
		 */
		String query = request.getParameter("rolo-query");
		int q = Integer.parseInt(query);
		System.out.println("rolo-query=" + q);
		
		String target = "";
		
		switch(q) {
			
			case 1:
				
				/*
				 * Get direction.
				 */
				String direction = request.getParameter("rolo-direction");
				System.out.println("rolo-direction = " + direction);
				
				
				/*
				 * Get existing rolodex information.
				 */
				List<Profile> rolodexProfiles = (List<Profile>) session.getAttribute("rolodex");
				String rlIdx = (String) session.getAttribute("rolodexIndex");
				int rolodexIndex = Integer.parseInt(rlIdx);
				
				/*
				 * Update rolodex information.
				 */
				int newIndex = rolodexIndex;
				
				
				if (direction.equals("fwd")) {
					if (rolodexIndex < (rolodexProfiles.size() - 1)) {
						newIndex = rolodexIndex + 1;
					}
				}
				if (direction.equals("back")) {
					if (rolodexIndex > 0) {
						newIndex = rolodexIndex - 1;
					}
				}
				session.setAttribute("rolodexIndex", Integer.toString(newIndex));
				Profile newProfile = RolodexManager.getRolodexProfile(rolodexProfiles, newIndex);
				session.setAttribute("rolodexProfile", newProfile);
				Profile newNextProfile = RolodexManager.getRolodexProfile(rolodexProfiles, newIndex + 1);
				session.setAttribute("rolodexNextProfile", newNextProfile);
				Profile newPrevProfile = RolodexManager.getRolodexProfile(rolodexProfiles, newIndex - 1);
				session.setAttribute("rolodexPrevProfile", newPrevProfile);
				
				break;
				
				
			case 2:
				
				String filter = request.getParameter("rolo-filter");
				List<Profile> rolodexProfiles2 = RolodexManager.getFilteredRolodexProfiles(filter);
				session.setAttribute("rolodex", rolodexProfiles2);
				
				break;
				
			default:
				break;
		
		}
		
		
		
		
		
		
	}

}
