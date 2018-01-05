package cz.zcu.kiv.pia.web.servlet;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cz.zcu.kiv.pia.domain.Friendship;
import cz.zcu.kiv.pia.domain.User;
import cz.zcu.kiv.pia.manager.FriendshipManager;
import cz.zcu.kiv.pia.manager.UserManager;

/**
 * Servlet implementation class Profile
 */
@MultipartConfig
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String USER_PARAMETER = "username";
	 private static final String USER = "user";   
	 
	 public static final String PROFILE_MODE = "profileMode";
	 
	 public static final String PROFILE_USERNAME = "profileUser";
	 public static final String PROFILE_EMAIL = "profileEmail";
	 public static final String PROFILE_GENDER = "profileGender";
	 public static final String PROFILE_AVATAR = "profileAvatar";
	 public static final String PROFILE_AGE = "profileAge";
	 public static final String PROFILE_REG = "profileReg";
	 public static final String PROFILE_ID = "profileId";
	 
	 public static final String EDIT_EMAIL = "editEmail";
	 public static final String EDIT_GENDER = "editGender";
	 public static final String EDIT_PASSWORD = "editPassword";
	 public static final String EDIT_AVATAR = "editAvatar";
	 
	 public static final String PASSWORD = "password";
	 public static final String EMAIL = "email";
	 public static final String GENDER = "sex";
	 public static final String FILE = "file";
	 
	 private static final String ERROR_ATRIBUTE = "err";
	 private static final String SUCCESS_ATTRIBUTE = "suc";
	 
	 public static final String AVATAR_DIR = "/img/avatars/";
	
	
	private UserManager userManager;
	private FriendshipManager frManager;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profile(UserManager userManager, FriendshipManager frManager) {
    	this.userManager = userManager;
    	this.frManager = frManager;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// profile get 
		
		String viewUsername = request.getParameter(USER_PARAMETER);
		User viewedUser = userManager.findUserByUsername(viewUsername);
		if (viewedUser == null) {
			errorDispatch("Zadaný uživatel nebyl nalezen!", request, response);
			return;
		}
		
		String logged = (String) request.getSession().getAttribute(USER);
		User loggedUser = userManager.findUserByUsername(logged);
		
		if (viewedUser.getUsername().equals(logged)) {
			// profile edit
			
			request.setAttribute(PROFILE_MODE, 0);
			if (loggedUser.getEmail() != null) request.setAttribute(EDIT_EMAIL, loggedUser.getEmail());
			if (loggedUser.getGender() != null) request.setAttribute(EDIT_GENDER, loggedUser.getGender());
			request.setAttribute(EDIT_AVATAR, loggedUser.getAvatar());
			
			request.getRequestDispatcher("WEB-INF/pages/profile.jsp").forward(request, response);
			
			
		} else {
			// profile viewer
			
			// need to know if the requester is a friend with the viewer
			request.setAttribute(PROFILE_MODE, 1);
			
			
			if (loggedUser != null) {
			
				if (frManager.areInvolved(loggedUser.getId(), viewedUser.getId()) != null) {
					// they are involved
					// lest find out how - are they friends ?

					Friendship fr = frManager.areFriends(loggedUser.getId(), viewedUser.getId());
					if (fr != null) {
						// friends - show the friendship cancel button
						request.setAttribute(PROFILE_ID, fr.getId());
						request.setAttribute(PROFILE_MODE, 2);
					}
					
					
				} else {
					// show the request to become friends button
					request.setAttribute(PROFILE_MODE, 3);
				}
			}
			
			
			
			
			
			request.setAttribute(PROFILE_USERNAME, viewedUser.getUsername());
			if (viewedUser.getDateOfBirth() != null) request.setAttribute(PROFILE_AGE, getAge(viewedUser.getDateOfBirth()));
			else request.setAttribute(PROFILE_AGE, "Neuvedeno");
			if (viewedUser.getEmail() != null) request.setAttribute(PROFILE_EMAIL, viewedUser.getEmail());
			else request.setAttribute(PROFILE_EMAIL, "Neuvedeno");
			request.setAttribute(PROFILE_AVATAR, viewedUser.getAvatar());
			if (viewedUser.getGender() != null) request.setAttribute(PROFILE_GENDER, viewedUser.getGender().equalsIgnoreCase("male") ? "Muž" : "Žena");
			else request.setAttribute(PROFILE_GENDER, "Neuvedeno");
			request.setAttribute(PROFILE_REG, viewedUser.getDateOfRegistration());
			
			
			request.getRequestDispatcher("WEB-INF/pages/profile.jsp").forward(request, response);
		
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter(EMAIL);
		String gender = request.getParameter(GENDER);
		String password = request.getParameter(PASSWORD);
		
		String username = (String) request.getSession().getAttribute(USER);
		
		// let user manager handle the update
		userManager.updateUser(username, password, email, gender);
		
		request.setAttribute(USER_PARAMETER, request.getSession().getAttribute(USER));
		
			request.setAttribute(SUCCESS_ATTRIBUTE, "Zmìny úspìšnì uloženy");
		  request.getRequestDispatcher("WEB-INF/pages/welcome.jsp").forward(request, response);
		
		
	}
	
	/**
	 * gets age from a date of birth
	 * @param dateOfBirth the date of birth
	 * @return age
	 */
	private int getAge(Date dateOfBirth) {
	    Calendar today = Calendar.getInstance();
	    Calendar birthDate = Calendar.getInstance();
	    birthDate.setTime(dateOfBirth);
	    
	    int todayYear = today.get(Calendar.YEAR);
	    int birthDateYear = birthDate.get(Calendar.YEAR);
	    int todayDayOfYear = today.get(Calendar.DAY_OF_YEAR);
	    int birthDateDayOfYear = birthDate.get(Calendar.DAY_OF_YEAR);
	    int todayMonth = today.get(Calendar.MONTH);
	    int birthDateMonth = birthDate.get(Calendar.MONTH);
	    int todayDayOfMonth = today.get(Calendar.DAY_OF_MONTH);
	    int birthDateDayOfMonth = birthDate.get(Calendar.DAY_OF_MONTH);
	    int age = todayYear - birthDateYear;

	    // If birth date is greater than todays date (after 2 days adjustment of leap year) then decrement age one year
	    if ((birthDateDayOfYear - todayDayOfYear > 3) || (birthDateMonth > todayMonth)){
	        age--;
	    
	    // If birth date and todays date are of same month and birth day of month is greater than todays day of month then decrement age
	    } else if ((birthDateMonth == todayMonth) && (birthDateDayOfMonth > todayDayOfMonth)){
	        age--;
	    }
	    return age;
	}
	
	 private void errorDispatch(String err, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 req.setAttribute(ERROR_ATRIBUTE, err);	        
	        req.getRequestDispatcher("WEB-INF/pages/profile.jsp").forward(req, resp);
	    }

}
