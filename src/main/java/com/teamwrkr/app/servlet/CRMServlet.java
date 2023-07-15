package com.teamwrkr.app.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.teamwrkr.app.dto.Notification;
import com.teamwrkr.app.manager.NotificationManager;
import com.teamwrkr.app.service.DataService;

/**
 * Servlet implementation class CRMServlet
 */
public class CRMServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CRMServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("accepting notification index = ***************************************************************************************************************");
		String accept = request.getParameter("accept");
		System.out.println("accept = " + accept);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//String accept = request.getParameter("accept");
		
		HttpSession session = request.getSession();
		
		System.out.println("CRM accepting notification index = ***************************************************************************************************************");
		String accept = request.getParameter("accept");
		String reject = request.getParameter("reject");
		String view = request.getParameter("view");
		
		
		if (accept != null && !accept.isEmpty()) {
			System.out.println("accept = " + accept);
			Notification notification = new Notification();
			notification.setNotId(Integer.parseInt(accept));			
			Notification n0 = (Notification) DataService.selectOne(notification);
			NotificationManager.acceptNotification(n0);
		}
		else {
			System.out.println("accept IS NULL");
		}
		if (reject != null && !reject.isEmpty()) {
			System.out.println("reject = " + reject);
			Notification notification = new Notification();
			notification.setNotId(Integer.parseInt(accept));			
			Notification n0 = (Notification) DataService.selectOne(notification);
			NotificationManager.rejectNotification(n0);
		}
		else {
			System.out.println("reject IS NULL");
		}
		if (view != null && !view.isEmpty()) {
			System.out.println("view = " + view);
			session.setAttribute("currentNotificationIndex", view);
			System.out.println("setAttribute(currentNotificationIndex)=" + view);
		}
		else {
			System.out.println("view IS NULL");
		}
		
		
		
		
		
		
	}

}
