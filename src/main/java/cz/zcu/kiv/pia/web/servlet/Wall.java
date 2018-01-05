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
		
		String stream = request.getParameter(STREAM_PARAMETER);
		String username = (String) request.getSession().getAttribute(USER);
		
		User loggedUser = userManager.findUserByUsername(username);
		
		// notifications
		List<Friendship> friendshipList = frManager.findUnapproved(loggedUser.getId());
		
		request.setAttribute(FRIENDSHIP_PARAMETER, friendshipList);
		
		
		// states
		List<Friendship> statusFrindshipList = frManager.findApproved(loggedUser.getId());
		List<Long> userIds = new ArrayList<>();
		userIds.add(loggedUser.getId());
		
		for (Friendship fr : statusFrindshipList) {
			
			if (!userIds.contains(fr.getInitiator().getId())) {
				userIds.add(fr.getInitiator().getId());
			}
			if (!userIds.contains(fr.getTarget().getId())) {
				userIds.add(fr.getTarget().getId());
			}
			
		}

		List<Status> statusList = statusManager.findByIds(userIds);
		
		if (!statusList.isEmpty()) {
			
			Collections.sort(statusList);
			
			int str = 0;
			// what is the value of stream ?
			if (stream == null) {
				str = 0;
			} else {
				try {
					str = Integer.parseInt(stream);
					
					if (!(str == 0 || str % 10 == 0)) {
						str = 0;
					}
					
				} catch (Exception e) {
					str = 0;
				}
			}
			

			request.setAttribute(STREAM, str);
			
			if (str == 0) {
				// first 10 states
				int endIndex = 10;
				if (statusList.size() < endIndex) {
					endIndex = statusList.size();
				}
				request.setAttribute(STATUS_PARAMETER, statusList.subList(0, endIndex));
			} else {
				int startIndex = str;
				int endIndex = str+10;
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
		
		if (text == null) {
			doGet(request, response);
			return;
		}
		
		if (text.trim().isEmpty()) {
			doGet(request, response);
			return;
		}
		
		String username = (String) request.getSession().getAttribute(USER);
		
		User user = userManager.findUserByUsername(username);
		
		statusManager.publishStatus(new Status(user, text, new Date()));
		
		doGet(request, response);
		
	}

}
