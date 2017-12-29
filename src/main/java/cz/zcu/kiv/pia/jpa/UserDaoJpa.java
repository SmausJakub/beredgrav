package cz.zcu.kiv.pia.jpa;

import javax.persistence.EntityManager;

import cz.zcu.kiv.pia.dao.UserDao;
import cz.zcu.kiv.pia.domain.User;


/**
 * JPA implementation of the UserDao interface
 *
 * Date: 26.9.15
 *
 * @author Jakub Danek
 */
public abstract class UserDaoJpa extends GenericDaoJpa<User, Long> implements UserDao {

    /**
     *
     * @param em entity manager to be used by this dao
     */
    public UserDaoJpa(EntityManager em) {
        super(em, User.class);
    }

    @Override
    public User create(User user) {
        entityManager.persist(user);
        return user;
    }
 
}
