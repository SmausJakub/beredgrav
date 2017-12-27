package cz.zcu.kiv.pia.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import cz.zcu.kiv.pia.ApplicationContext;
import cz.zcu.kiv.pia.web.filter.AuthenticationGuard;
import cz.zcu.kiv.pia.web.servlet.Index;
import cz.zcu.kiv.pia.web.servlet.Login;
import cz.zcu.kiv.pia.web.servlet.Register;
import cz.zcu.kiv.pia.web.servlet.SecretServlet;

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
        sce.getServletContext().addServlet("register", new Register(ctx.getUserManager())).addMapping("/register");
        sce.getServletContext().addServlet("secret", new SecretServlet()).addMapping("/secret/vip");
        
        sce.getServletContext().addFilter("authFilter", new AuthenticationGuard(ctx.getAuthenticationService())).addMappingForUrlPatterns(null, false, "/secret/*");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ctx.destroy();
    }
}
