package cz.zcu.kiv.pia.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cz.zcu.kiv.pia.web.auth.AuthenticationService;

/**
 * Date: 26.11.15
 *
 * @author Jakub Danek
 */
public class AuthenticationGuard implements Filter {
	
	private static final String ERROR_ATRIBUTE = "err";

    private AuthenticationService authService;

    public AuthenticationGuard(AuthenticationService authService) {
        this.authService = authService;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        boolean allowed = authService.isLoggedIn(req.getSession());

        if(allowed) {
            chain.doFilter(request, response);
        } else {
        	req.setAttribute(ERROR_ATRIBUTE, "Je vyžadováno pøihlášení.");
        	 req.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(req, res);
        }
    }

    @Override
    public void destroy() {

    }
}
