package cz.zcu.kiv.pia.jpa;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import cz.zcu.kiv.pia.dao.UserDao;
import cz.zcu.kiv.pia.domain.User;


public class UserDaoJPQL extends UserDaoJpa implements UserDao
{
    public UserDaoJPQL(EntityManager em) {
        super(em);
    }

	public User findByUsername(String username) 
	{
		TypedQuery<User> q = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
		q.setParameter("username", username);
		try {
			return q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
