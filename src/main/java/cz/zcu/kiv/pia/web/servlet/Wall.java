package cz.zcu.kiv.pia.web.servlet;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cz.zcu.kiv.pia.domain.Friendship;
import cz.zcu.kiv.pia.domain.Status;
import cz.zcu.kiv.pia.domain.User;
import cz.zcu.kiv.pia.manager.FriendshipManager;
import cz.zcu.kiv.pia.manager.StatusManager;
import cz.zcu.kiv.pia.manager.UserManager;

/**
 * Servlet implementation class Wall
 */

public class Wall extends HttpServlet {
	private static final long serialVersionUID = 1L;

       
	private StatusManager statusManager;
	private FriendshipManager frManager;
	private UserManager userManager;
	
	private static final String STATUS_TEXT_PARAMETER = "text";
	private static final String STATUS_PARAMETER = "statusList";
	private static final String FRIENDSHIP_PARAMETER = "friendshipList";
	
	private static final String USER = "user";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Wall(UserManager userManager, StatusManager statusManager, FriendshipManager frManager) {
    	this.statusManager = statusManager;
    	this.userManager = userManager;
    	this.frManager = frManager;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Status> statusList = statusManager.findAllStatuses();
		Collections.sort(statusList);
		request.setAttribute(STATUS_PARAMETER, statusList);
		
		String username = (String) request.getSession().getAttribute(USER);
		
		User loggedUser = userManager.findUserByUsername(username);
		
		List<Friendship> friendshipList = frManager.findInvolvedFriendships(loggedUser.getId());
		
		request.setAttribute(FRIENDSHIP_PARAMETER, friendshipList);
		request.getRequestDispatcher("WEB-INF/pages/wall.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String text = request.getParameter(STATUS_TEXT_PARAMETER);
		
		if (text == null) {
			doGet(request, response);
			return;
		}
		
		if (text.isEmpty()) {
			return;
		}
		
		String username = (String) request.getSession().getAttribute(USER);
		
		User user = userManager.findUserByUsername(username);
		
		statusManager.publishStatus(new Status(user, text, new Date()));
		
		doGet(request, response);
		
	}

}
