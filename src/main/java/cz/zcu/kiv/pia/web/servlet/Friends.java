package cz.zcu.kiv.pia.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cz.zcu.kiv.pia.domain.Friendship;
import cz.zcu.kiv.pia.domain.User;
import cz.zcu.kiv.pia.manager.FriendshipManager;
import cz.zcu.kiv.pia.manager.UserManager;

/**
 * Servlet implementation class Friends
 */
public class Friends extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String USER = "user";
	public static final String FRIEND_PARAMETER = "friendList";
	
	private FriendshipManager frManager;
	private UserManager userManager;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Friends(UserManager userManager, FriendshipManager frManager) {
    	this.frManager = frManager;
    	this.userManager = userManager;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// servlet lists all friends of a user
		
		String username = (String) request.getSession().getAttribute(USER);
		User user = userManager.findUserByUsername(username);
				
		List<Friendship> friendshipList = frManager.findApproved(user.getId());
		
		// now to get users from the friendships - take them by id only if that id is not session user and it is not already in the list
		List<User> friendList = new ArrayList<>();
	
		for (Friendship fr : friendshipList) {
			
			if (!(fr.getInitiator().equals(user)) && !(friendList.contains(user))) {
				friendList.add(fr.getInitiator());
			} else if (!(fr.getTarget().equals(user)) && !(friendList.contains(user))) {
				friendList.add(fr.getTarget());
			}
			
		}
		
		request.setAttribute(FRIEND_PARAMETER, friendList);
		request.getRequestDispatcher("WEB-INF/pages/friends.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
