package com.teamwrkr.app.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import com.teamwrkr.app.dto.Mission;
import com.teamwrkr.app.dto.Profile;
import com.teamwrkr.app.manager.OpportunityManager;
import com.teamwrkr.app.manager.RolodexManager;

/**
 * Servlet implementation class OpportunityServlet
 */
public class OpportunityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OpportunityServlet() {
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
		System.out.println("call OpportunityServlet.doPost()");
		
		HttpSession session = request.getSession();
		
		
		/*
		 * Get query parameter (navigation option).
		 */
		String query = request.getParameter("opps-query");
		int q = Integer.parseInt(query);
		System.out.println("opps-query=" + q);
		
		String target = "";
		
		switch(q) {
			
		
			/*
			 * ----- OPPORTUNITY SLIDER CONTROL BUTTONS -----
			 */
			case 1:
				
				/*
				 * Get direction.
				 */
				String direction = request.getParameter("opps-direction");
				System.out.println("opps-direction = " + direction);
				
				
				/*
				 * Get existing oppsdex information.
				 */
				List<Mission> sliderOpps = (List<Mission>) session.getAttribute("sliderOpps");
				int sliderBlock = (int) session.getAttribute("sliderBlock");
				
				/*
				 * Update rolodex information.
				 */
				int newBlock = sliderBlock;
				int maxSliderIndex = (sliderBlock * 7) - 1;
				
				
				if (direction.equals("fwd")) {
					if (maxSliderIndex < (sliderOpps.size() - 1)) {
						newBlock = sliderBlock + 1;
					}
				}
				if (direction.equals("back")) {
					if (sliderBlock > 0) {
						newBlock = sliderBlock - 1;
					}
				}
				session.setAttribute("sliderBlock", newBlock);
				System.out.println("session.setAttribute(sliderBlock) = " + newBlock);
				
				break;
				
				
			/*
			 * ----- FILTERED OPPORTUNITY SEARCH -----
			 * 
			 * NOTE: currently not used.
			 */
			case 2:
				
				String filter = request.getParameter("opps-filter");
				List<Mission> filteredOppSlider = OpportunityManager.getFilteredOpportunities(filter);
				session.setAttribute("oppsSlider", filteredOppSlider);
				
				break;
				
			default:
				break;
		
		}
		
	}

}
