package cz.zcu.kiv.pia.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import cz.zcu.kiv.pia.domain.User;


public class UserDaoCriteria extends UserDaoJpa {
	public UserDaoCriteria(EntityManager em) {
		super(em);
	}

	public User findByUsername(String username) 
	{
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> criteria = cb.createQuery(User.class);
		Root<User> root = criteria.from(User.class);
		Predicate byUsername = cb.equal(root.get("username"), username);
		criteria.where(byUsername);
		TypedQuery<User> q = entityManager.createQuery(criteria);

		try {
			return q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public List<User> findAll() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> criteria = cb.createQuery(User.class);
		Root<User> root = criteria.from(User.class);
		criteria.select(root);
		TypedQuery<User> q = entityManager.createQuery(criteria);
		
		try {
			return q.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
}
