package cz.zcu.kiv.pia.jpa;

import java.util.ArrayList;
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
		criteria.select(root).where(byUsername);
		TypedQuery<Status> q = entityManager.createQuery(criteria);
		
		try {
			return q.getResultList();
		} catch (NoResultException e) {
			return null;
		}
		
	}

	@Override
	public List<Status> findAll() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Status> criteria = cb.createQuery(Status.class);
		Root<Status> root = criteria.from(Status.class);
		criteria.select(root);
		TypedQuery<Status> q = entityManager.createQuery(criteria);
		
		try {
			return q.getResultList();
		} catch (NoResultException e) {
			return null;
		}
		
		
	}

	@Override
	public List<Status> findByIds(List<Long> ids) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Status> criteria = cb.createQuery(Status.class);
		Root<Status> root = criteria.from(Status.class);
		
		List<Predicate> predicates = new ArrayList<>();
		for (Long id : ids) {
			predicates.add((cb.equal(root.get("owner"), id)));
		}
		criteria.select(root).where(cb.or(predicates.toArray(new Predicate[]{})));
		
		TypedQuery<Status> q = entityManager.createQuery(criteria);
		
		try {
			return q.getResultList();
		} catch (NoResultException e) {
			return null;
		}
		
	}

	

}
