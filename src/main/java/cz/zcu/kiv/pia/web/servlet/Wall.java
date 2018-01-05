package cz.zcu.kiv.pia.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
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
	
	private static final String STREAM_PARAMETER = "stream";
	private static final String STREAM = "streamValue";
	
	private static final String USER_AVATAR = "userAvatar";
	
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
		
		// wall do get does few things - load notifications, statuses
		
		String stream = request.getParameter(STREAM_PARAMETER);
		String username = (String) request.getSession().getAttribute(USER);
		
		User loggedUser = userManager.findUserByUsername(username);
		
		// notifications - find those unapproved friendships involving our logged user id
		List<Friendship> friendshipList = frManager.findUnapproved(loggedUser.getId());
		
		request.setAttribute(FRIENDSHIP_PARAMETER, friendshipList);
		
		
		// states - find approved frinedships friends states
		List<Friendship> statusFrindshipList = frManager.findApproved(loggedUser.getId());
		
		// ids of all users which we want their states
		List<Long> userIds = new ArrayList<>();
		userIds.add(loggedUser.getId());
		
		// load all ids
		for (Friendship fr : statusFrindshipList) {
			
			if (!userIds.contains(fr.getInitiator().getId())) {
				userIds.add(fr.getInitiator().getId());
			}
			if (!userIds.contains(fr.getTarget().getId())) {
				userIds.add(fr.getTarget().getId());
			}
			
		}

		// find the states
		List<Status> statusList = statusManager.findByIds(userIds);
		
		// it can be empty - then skip this
		if (!statusList.isEmpty()) {
			// it is not empty - sort it by date
			Collections.sort(statusList);
			
			int str = 0;
			// what is the value of stream ?
			if (stream == null) {
				// was not given - default 0
				str = 0;
			} else {
				try {
					// try to parse it
					str = Integer.parseInt(stream);
					
					// look if it is either 0,10,20,30 etc
					if (!(str == 0 || str % 10 == 0)) {
						// otherwise default
						str = 0;
					}
					
				} catch (Exception e) {
					// probably wasnt even number - default
					str = 0;
				}
			}
			

			request.setAttribute(STREAM, str);
			
			if (str == 0) {
				// first 10 states
				int endIndex = 10;
				// watch that we didnt get a bad number (greater than list size)
				if (statusList.size() < endIndex) {
					endIndex = statusList.size();
				}
				request.setAttribute(STATUS_PARAMETER, statusList.subList(0, endIndex));
			} else {
				// different states
				int startIndex = str;
				int endIndex = str+10;
				// check numbers so we do not envoke exception
				if (statusList.size() < startIndex) {
					while (statusList.size() < startIndex) {
						startIndex -= 10;
					}
				}
				if (statusList.size() < endIndex) {
					endIndex = statusList.size();
				}
				request.setAttribute(STATUS_PARAMETER, statusList.subList(startIndex, endIndex));
			}
		}
		
		request.setAttribute(USER_AVATAR, loggedUser.getAvatar());
		request.getRequestDispatcher("WEB-INF/pages/wall.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String text = request.getParameter(STATUS_TEXT_PARAMETER);
		
		// no text sent - just call get
		if (text == null) {
			doGet(request, response);
			return;
		}
		
		// user sent empty text - we dont bother with that
		if (text.trim().isEmpty()) {
			doGet(request, response);
			return;
		}
		
		// publish new status
		String username = (String) request.getSession().getAttribute(USER);
		
		User user = userManager.findUserByUsername(username);
		
		statusManager.publishStatus(new Status(user, text, new Date()));
		
		doGet(request, response);
		
	}

}
