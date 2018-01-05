package cz.zcu.kiv.pia.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cz.zcu.kiv.pia.domain.FriendshipValidationException;
import cz.zcu.kiv.pia.manager.FriendshipManager;

/**
 * Servlet implementation class FriendApprove
 */
public class FriendApprove extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private FriendshipManager frManager;
	
	private static final String SUCCESS_ATTRIBUTE = "suc";
	private static final String ERROR_ATTRIBUTE = "err";
	
	public static final String FR_ID = "id";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FriendApprove(FriendshipManager frManager) {
       this.frManager = frManager;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// approval of a friend request - the target agrees
		
		String id = request.getParameter(FR_ID);

		// try to approve it
		try {
			frManager.approveFriendship(id);
		} catch (FriendshipValidationException e) {
			request.setAttribute(ERROR_ATTRIBUTE, e.getMessage());
			request.getRequestDispatcher("WEB-INF/pages/welcome.jsp").forward(request, response);
			return;
		}
		
		request.setAttribute(SUCCESS_ATTRIBUTE, "Žádost pøijata!");
		request.getRequestDispatcher("WEB-INF/pages/welcome.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
