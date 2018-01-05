package cz.zcu.kiv.pia.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import cz.zcu.kiv.pia.ApplicationContext;
import cz.zcu.kiv.pia.web.filter.AuthenticationGuard;
import cz.zcu.kiv.pia.web.servlet.Friend;
import cz.zcu.kiv.pia.web.servlet.FriendApprove;
import cz.zcu.kiv.pia.web.servlet.FriendDelete;
import cz.zcu.kiv.pia.web.servlet.Friends;
import cz.zcu.kiv.pia.web.servlet.Hate;
import cz.zcu.kiv.pia.web.servlet.Like;
import cz.zcu.kiv.pia.web.servlet.Login;
import cz.zcu.kiv.pia.web.servlet.Logout;
import cz.zcu.kiv.pia.web.servlet.Profile;
import cz.zcu.kiv.pia.web.servlet.Register;
import cz.zcu.kiv.pia.web.servlet.Upload;
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

        // assign servlets with managers and add mappings
        sce.getServletContext().addServlet("login", new Login(ctx.getAuthenticationService())).addMapping("/login");
        sce.getServletContext().addServlet("logout", new Logout(ctx.getAuthenticationService())).addMapping("/logout");
        sce.getServletContext().addServlet("register", new Register(ctx.getUserManager())).addMapping("/register");
        sce.getServletContext().addServlet("wall", new Wall(ctx.getUserManager(), ctx.getStatusManager(), ctx.getFrManager())).addMapping("/wall");
        sce.getServletContext().addServlet("profile", new Profile(ctx.getUserManager(), ctx.getFrManager())).addMapping("/profile");
        sce.getServletContext().addServlet("users", new Users(ctx.getUserManager())).addMapping("/users");
        sce.getServletContext().addServlet("friend", new Friend(ctx.getUserManager(), ctx.getFrManager())).addMapping("/friend");
        sce.getServletContext().addServlet("friendDelete", new FriendDelete(ctx.getFrManager())).addMapping("/friendDelete");
        sce.getServletContext().addServlet("friendApprove", new FriendApprove(ctx.getFrManager())).addMapping("/friendApprove");
        sce.getServletContext().addServlet("friends", new Friends(ctx.getUserManager(), ctx.getFrManager())).addMapping("/friends");
        sce.getServletContext().addServlet("upload", new Upload(ctx.getUserManager())).addMapping("/upload");
        sce.getServletContext().addServlet("like", new Like(ctx.getUserManager(), ctx.getStatusManager())).addMapping("/like");
        sce.getServletContext().addServlet("hate", new Hate(ctx.getUserManager(), ctx.getStatusManager())).addMapping("/hate");
        
        //  users need to be logged in to view these
        sce.getServletContext().addFilter("authFilter", new AuthenticationGuard(ctx.getAuthenticationService())).addMappingForUrlPatterns(null, false, "/wall/*", "/welcome/*", "/friend/*", "/friendDelete/*", "/friendApprove/*", "/friends/*", "/upload/*", "/hate/*", "/like/*");
        
        
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ctx.destroy();
    }
}
