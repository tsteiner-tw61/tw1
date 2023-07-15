package com.teamwrkr.app.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Servlet Filter implementation class SessionTimeoutFilter
 */
public class SessionTimeoutFilter extends HttpFilter implements Filter {
       
	
    /**
     * @see HttpFilter#HttpFilter()
     */
    public SessionTimeoutFilter() {
        super();
    }

    
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() { }

	
	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		System.out.println("doFilter");
		
		String logoutPage = "timeout.jsp";
		
		if ((request instanceof HttpServletRequest) && (response instanceof HttpServletResponse)) {
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			HttpServletResponse httpServletResponse = (HttpServletResponse) response;
			String timeoutUrl = /*httpServletRequest.getContextPath() + "/" +*/ logoutPage;
			System.out.println("timeoutUrl=" + timeoutUrl);
			if (!isValidSession(httpServletRequest)) {
				System.out.println("isNotValidSession");
				RequestDispatcher rd = request.getRequestDispatcher(timeoutUrl);
				try {
					System.out.println("Dispatching HttpRequest to target = " + timeoutUrl);
					rd.forward(request, response);
				}
				catch (Exception e) {
					System.out.println("Error dispatching HttpRequest to target = " + timeoutUrl);
					e.printStackTrace();
				}
				
			}
			chain.doFilter(request, response);
		}
		
	}

	
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException { }

	
	
	private boolean isValidSession(HttpServletRequest httpServletRequest) {
		
        return (httpServletRequest.getRequestedSessionId() != null) && httpServletRequest.isRequestedSessionIdValid();
        
    }
	
}
