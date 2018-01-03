package cz.zcu.kiv.pia.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import cz.zcu.kiv.pia.domain.Friendship;

public class FriendshipDaoCriteria extends FriendshipDaoJpa {

	public FriendshipDaoCriteria(EntityManager em) {
		super(em);
	}

	@Override
	public List<Friendship> findAll() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Friendship> criteria = cb.createQuery(Friendship.class);
		Root<Friendship> root = criteria.from(Friendship.class);
		criteria.select(root);
		TypedQuery<Friendship> q = entityManager.createQuery(criteria);
		
		try {
			return q.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public List<Friendship> findByUserId(Long id) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Friendship> criteria = cb.createQuery(Friendship.class);
		Root<Friendship> root = criteria.from(Friendship.class);
		Predicate byInitiator = cb.equal(root.get("initiator"), id);
		Predicate byTarget = cb.equal(root.get("target"), id);
		Predicate notApproved = cb.equal(root.get("approved"), 0);
		Predicate both = cb.or(byInitiator, byTarget);
		Predicate allP = cb.and(both, notApproved);
		
		criteria.select(root).where(allP);
		
		TypedQuery<Friendship> q = entityManager.createQuery(criteria);
		
		try {
			return q.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Friendship findFriendshipByIds(Long id1, Long id2) {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Friendship> criteria = cb.createQuery(Friendship.class);
		Root<Friendship> root = criteria.from(Friendship.class);
		Predicate byInitiator = cb.equal(root.get("initiator"), id1);
		Predicate byTarget = cb.equal(root.get("target"), id2);
		Predicate involved = cb.and(byInitiator, byTarget);
		
		criteria.select(root).where(involved);
		
		TypedQuery<Friendship> q = entityManager.createQuery(criteria);
		
		try {
			return q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		
	}



}
