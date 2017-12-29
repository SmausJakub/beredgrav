package cz.zcu.kiv.pia.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import cz.zcu.kiv.pia.domain.Status;

public class StatusDaoCriteria extends StatusDaoJpa {

	public StatusDaoCriteria(EntityManager em) {
		super(em);
	}

	@Override
	public List<Status> findByUsername(String username) {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Status> criteria = cb.createQuery(Status.class);
		Root<Status> root = criteria.from(Status.class);
		Predicate byUsername = cb.equal(root.get("username"), username);
		criteria.where(byUsername);
		TypedQuery<Status> q = entityManager.createQuery(criteria);
		
		try {
			return q.getResultList();
		} catch (NoResultException e) {
			return null;
		}
		
	}

}
