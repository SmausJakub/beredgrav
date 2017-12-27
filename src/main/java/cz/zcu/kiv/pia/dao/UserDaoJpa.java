package cz.zcu.kiv.pia.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import cz.zcu.kiv.pia.domain.User;

public class UserDaoJpa extends GenericDaoJpa<User> implements UserDao {
	
	  public UserDaoJpa(EntityManager em) {
	        super(em, User.class);
	    }

	    @Override
	    public User findByUsername(String username) {
	        TypedQuery<User> q = em.createQuery("SELECT u FROM User u WHERE u.username = :uname", User.class);
	        q.setParameter("uname", username);
	        try {
	            return q.getSingleResult();
	        } catch (NoResultException e) {
	            //no result found, ensuring the behaviour described by interface specification
	            //see javadoc of the findByUsername method.
	            return null;
	        }
	    }
}
