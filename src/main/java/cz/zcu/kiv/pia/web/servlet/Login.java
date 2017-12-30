package cz.zcu.kiv.pia.web.servlet;



import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cz.zcu.kiv.pia.web.auth.AuthenticationService;


/**
 * Servlet handling user login requests
 *
 * Date: 26.11.15
 *
 * @author Jakub Danek
 */
public class Login extends HttpServlet {


	private static final long serialVersionUID = 1L;
	private static final String USERNAME_PARAMETER = "username";
    private static final String PASSWORD_PARAMETER = "password";

    private static final String ERR_ATTRIBUTE = "err";

    private AuthenticationService authService;

    public Login(AuthenticationService authService) {
        this.authService = authService;
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter(USERNAME_PARAMETER);
        String password = req.getParameter(PASSWORD_PARAMETER);

        boolean authenticated = authService.authenticate(req.getSession(), username, password);
        if(authenticated) {
        	req.getRequestDispatcher("WEB-INF/pages/welcome.jsp").forward(req, resp);
        	
        } else {
            req.setAttribute(ERR_ATTRIBUTE, "Špatnì zadané pøihlašovací údaje!");
            req.setAttribute(USERNAME_PARAMETER, username);
            req.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(req, resp);
        }
    }
}
