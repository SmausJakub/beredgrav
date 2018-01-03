package cz.zcu.kiv.pia.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import cz.zcu.kiv.pia.ApplicationContext;
import cz.zcu.kiv.pia.web.filter.AuthenticationGuard;
import cz.zcu.kiv.pia.web.servlet.Login;
import cz.zcu.kiv.pia.web.servlet.Logout;
import cz.zcu.kiv.pia.web.servlet.Profile;
import cz.zcu.kiv.pia.web.servlet.Register;
import cz.zcu.kiv.pia.web.servlet.Users;
import cz.zcu.kiv.pia.web.servlet.Wall;

/**
 * Application startup listener. Handles registration of servlets
 * and injection of their dependencies.
 *
 * Date: 20.11.15
 *
 * @author Jakub Danek
 */
@WebListener
public class ApplicationStartListener implements ServletContextListener {

    private ApplicationContext ctx;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ctx = new ApplicationContext();

        sce.getServletContext().addServlet("login", new Login(ctx.getAuthenticationService())).addMapping("/login");
        sce.getServletContext().addServlet("logout", new Logout(ctx.getAuthenticationService())).addMapping("/logout");
        sce.getServletContext().addServlet("register", new Register(ctx.getUserManager())).addMapping("/register");
        sce.getServletContext().addServlet("wall", new Wall(ctx.getUserManager(), ctx.getStatusManager(), ctx.getFrManager())).addMapping("/wall");
        sce.getServletContext().addServlet("profile", new Profile(ctx.getUserManager(), ctx.getFrManager())).addMapping("/profile");
        sce.getServletContext().addServlet("users", new Users(ctx.getUserManager())).addMapping("/users");
        
        sce.getServletContext().addFilter("authFilter", new AuthenticationGuard(ctx.getAuthenticationService())).addMappingForUrlPatterns(null, false, "/wall/*", "/profile/*", "/welcome/*");
        
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ctx.destroy();
    }
}
