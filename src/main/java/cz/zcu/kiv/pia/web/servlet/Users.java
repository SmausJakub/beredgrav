package cz.zcu.kiv.pia.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cz.zcu.kiv.pia.domain.User;
import cz.zcu.kiv.pia.manager.UserManager;

/**
 * Servlet implementation class Users
 */

public class Users extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String USER_LIST = "userList";
	
	private UserManager userManager;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Users(UserManager userManager) {
    	this.userManager = userManager;
    
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<User> userList = userManager.findAllRegisteredUsers();
		
		request.setAttribute(USER_LIST, userList);
		request.getRequestDispatcher("WEB-INF/pages/users.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
