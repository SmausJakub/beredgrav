package cz.zcu.kiv.pia.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cz.zcu.kiv.pia.domain.StatusValidationException;
import cz.zcu.kiv.pia.domain.User;
import cz.zcu.kiv.pia.manager.StatusManager;
import cz.zcu.kiv.pia.manager.UserManager;

/**
 * Servlet implementation class Hate
 */

public class Hate extends HttpServlet {
private static final long serialVersionUID = 1L;
	
	private static final String STATUS_ID = "id";
	
	private static final String USER = "user";
	
	 private static final String ERROR_ATRIBUTE = "err";
	 private static final String SUCCESS_ATTRIBUTE = "suc";
	
	
	private StatusManager statusManager;
	private UserManager userManager;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Hate(UserManager userManager, StatusManager statusManager) {
    	this.statusManager = statusManager;
    	this.userManager = userManager;
    }	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// hate status
		
	String statusId = (String) request.getParameter(STATUS_ID);
		
		String username = (String) request.getSession().getAttribute(USER);
		
		User loggedUser = userManager.findUserByUsername(username);
		
		try {
			statusManager.hateStatus(loggedUser, statusId);
		} catch (StatusValidationException e) {
			errorDispatch(e.getMessage(), request, response);
			return;
		}
		
		request.setAttribute(SUCCESS_ATTRIBUTE, "Provedeno!");
		request.getRequestDispatcher("WEB-INF/pages/welcome.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	 private void errorDispatch(String err, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 req.setAttribute(ERROR_ATRIBUTE, err);	        
	        req.getRequestDispatcher("WEB-INF/pages/welcome.jsp").forward(req, resp);
	    }


}
