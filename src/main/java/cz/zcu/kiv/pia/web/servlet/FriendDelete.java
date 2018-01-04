package cz.zcu.kiv.pia.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cz.zcu.kiv.pia.manager.FriendshipManager;

/**
 * Servlet implementation class FriendDelete
 */

public class FriendDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FriendshipManager frManager;
	
	public static final String FR_ID = "id";
	
	private static final String SUCCESS_ATTRIBUTE = "suc";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FriendDelete(FriendshipManager frManager) {
       this.frManager = frManager;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter(FR_ID);
		
		if (id == null) {
			return;
		}
		
		frManager.deleteFriendship(Long.parseLong(id));
		
		request.setAttribute(SUCCESS_ATTRIBUTE, "Žádost smazána!");
		request.getRequestDispatcher("WEB-INF/pages/welcome.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
