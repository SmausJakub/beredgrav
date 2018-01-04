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
	public List<Friendship> findUnapprovedById(Long id) {
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
	public List<Friendship> findApprovedById(Long id) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Friendship> criteria = cb.createQuery(Friendship.class);
		Root<Friendship> root = criteria.from(Friendship.class);
		Predicate byInitiator = cb.equal(root.get("initiator"), id);
		Predicate byTarget = cb.equal(root.get("target"), id);
		Predicate approved = cb.equal(root.get("approved"), 1);
		Predicate both = cb.or(byInitiator, byTarget);
		Predicate allP = cb.and(both, approved);
		
		criteria.select(root).where(allP);
		
		TypedQuery<Friendship> q = entityManager.createQuery(criteria);
		
		try {
			return q.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}


	@Override
	public Friendship findApprovedByIds(Long id1, Long id2) {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Friendship> criteria = cb.createQuery(Friendship.class);
		Root<Friendship> root = criteria.from(Friendship.class);
		Predicate byInitiator1 = cb.equal(root.get("initiator"), id1);
		Predicate byInitiator2 = cb.equal(root.get("initiator"), id2);
		Predicate byTarget1 = cb.equal(root.get("target"), id1);
		Predicate byTarget2 = cb.equal(root.get("target"), id2);
		
		Predicate approved = cb.equal(root.get("approved"), 1);
		
		Predicate byInitiatorOr = cb.or(byInitiator1, byInitiator2);
		Predicate byTargetOr = cb.or(byTarget1, byTarget2);
		Predicate involved = cb.and(byInitiatorOr, byTargetOr);
		Predicate involvedAndApproved = cb.and(involved, approved);
		
		criteria.select(root).where(involvedAndApproved);
		
		TypedQuery<Friendship> q = entityManager.createQuery(criteria);
		
		
		try {
			return q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	
	@Override
	public Friendship findAnyByIds(Long id1, Long id2) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Friendship> criteria = cb.createQuery(Friendship.class);
		Root<Friendship> root = criteria.from(Friendship.class);
		Predicate byInitiator1 = cb.equal(root.get("initiator"), id1);
		Predicate byInitiator2 = cb.equal(root.get("initiator"), id2);
		Predicate byTarget1 = cb.equal(root.get("target"), id1);
		Predicate byTarget2 = cb.equal(root.get("target"), id2);
		
		Predicate byInitiatorOr = cb.or(byInitiator1, byInitiator2);
		Predicate byTargetOr = cb.or(byTarget1, byTarget2);
		Predicate involved = cb.and(byInitiatorOr, byTargetOr);
		
		criteria.select(root).where(involved);
		
		TypedQuery<Friendship> q = entityManager.createQuery(criteria);
		
		
		try {
			return q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	


}
