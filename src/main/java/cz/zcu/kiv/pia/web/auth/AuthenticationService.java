package cz.zcu.kiv.pia.web.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cz.zcu.kiv.pia.manager.UserManager;



/**
 * Wrapper around HttpSession providing authentication functionality.
 *
 * Date: 26.11.15
 *
 * @author Jakub Danek
 */
public class AuthenticationService {

    private static final String USER = "user";

    private UserManager userManager;

    public AuthenticationService(UserManager userManager) {
        this.userManager = userManager;
    }

    /**
     * Signs in the user, if username and password match
     *
     * @param session session associated with the request
     * @param username provided username
     * @param password provided password
     * @return true if success, false otherwise
     */
    public boolean authenticate(HttpSession session, String username, String password) {
        boolean authenticated = userManager.authenticate(username, password);

        if(authenticated) {
            session.setAttribute(USER, username);
            return true;
        }
        return false;
    }
    
    public void invalidate(HttpServletRequest request) {
    	
    	HttpSession session = request.getSession(false);
    	
    	if (session != null) {
    		session.invalidate();
    	}
    }
    

    /**
     *
     * @param session session associated with the request
     * @return true if there is a user currently logged in within this session.
     */
    public boolean isLoggedIn(HttpSession session) {
        return session.getAttribute(USER) != null;
    }
}
