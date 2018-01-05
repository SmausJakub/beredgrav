package cz.zcu.kiv.pia.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cz.zcu.kiv.pia.domain.Friendship;
import cz.zcu.kiv.pia.domain.User;
import cz.zcu.kiv.pia.manager.FriendshipManager;
import cz.zcu.kiv.pia.manager.UserManager;

/**
 * Servlet implementation class Friend
 */
public class Friend extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserManager userManager;
	private FriendshipManager frManager;
	
	public static final String USER_PARAMETER = "username";
	 private static final String USER = "user";   
	 
	 private static final String ERROR_ATRIBUTE = "err";
	 private static final String SUCCESS_ATTRIBUTE = "suc";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Friend(UserManager userManager, FriendshipManager frManager) {
    	this.userManager = userManager;
    	this.frManager = frManager;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// make a friendship !
		
		String loggedUsername = (String) request.getSession().getAttribute(USER);
		User loggedUser = userManager.findUserByUsername(loggedUsername);
		
		String viewedUsername = (String) request.getParameter(USER_PARAMETER);
		User viewedUser = userManager.findUserByUsername(viewedUsername);
		
		// get both users who are involved
		
		// can be null if the id given is wrong
		if (viewedUser == null) {
			errorDispatch("Hledaný uživatel neexistuje!", request, response);
			return;
		}
		
		// lets see if they are already involved
		if (frManager.areInvolved(loggedUser.getId(), viewedUser.getId()) != null) {
			// they are already involved somehow - so error
			errorDispatch("Urèitý vztah byl již navázán!", request, response);
			return;
		}
		
		// they are not involved, so lets create a new friendship request
		frManager.createFriendship(new Friendship(loggedUser, viewedUser));
		
		request.setAttribute(SUCCESS_ATTRIBUTE, "Žádost o pøátelství odeslána.");
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
