package cz.zcu.kiv.pia;


import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import cz.zcu.kiv.pia.dao.RoleDao;
import cz.zcu.kiv.pia.dao.UserDao;
import cz.zcu.kiv.pia.jpa.RoleDaoCriteria;
import cz.zcu.kiv.pia.jpa.UserDaoCriteria;
import cz.zcu.kiv.pia.manager.DefaultUserManager;
import cz.zcu.kiv.pia.manager.UserManager;
import cz.zcu.kiv.pia.utils.Encoder;
import cz.zcu.kiv.pia.utils.PasswordHashEncoder;
import cz.zcu.kiv.pia.web.auth.AuthenticationService;


/**
 * Application context holds references to all parts of the application,
 * manages their creation and provides them wherever needed.
 *
 * TODO currently are all instances held by the context hard-coded.
 * TODO the whole mechanism could be made dynamic using String ids to identify each held instance
 *
 * Date: 26.11.15
 *
 * @author Jakub Danek
 */
public class ApplicationContext {

    //persistence
    private EntityManager em;
    private UserDao userDao;
    private RoleDao roleDao;

    //business
    private UserManager userManager;
    private Encoder encoder;

    //web
    private AuthenticationService authenticationService;

    public ApplicationContext() {
        //TODO persistence unit name should be taken from a property file, not hard-coded!
        em = Persistence.createEntityManagerFactory("dbs").createEntityManager();
        userDao = new UserDaoCriteria(em);
        roleDao = new RoleDaoCriteria(em);
        encoder = new PasswordHashEncoder();
        userManager = new DefaultUserManager(userDao, encoder);
        authenticationService = new AuthenticationService(userManager);
    }

    public void destroy() {
        em.close();
    }

    public EntityManager getEm() {
        return em;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public Encoder getEncoder() {
        return encoder;
    }

    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }
}
