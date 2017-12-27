package cz.zcu.kiv.pia.jpa;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import cz.zcu.kiv.pia.domain.Role;
import cz.zcu.kiv.pia.domain.User;

public class RoleDaoCriteria extends RoleDaoJpa
{
	public RoleDaoCriteria(EntityManager em)
	{
		super(em);
	}
	
	public Set<Role> findByUser(String username) 
	{
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Role> criteria = cb.createQuery(Role.class);
		Root<User> root = criteria.from(User.class);
		
		//select roles
		criteria.select(root.get("roles"));
		
		//where user has username
		Predicate byUsername = cb.equal(root.get("username"), username);
		
		criteria.where(byUsername);
		Query q = entityManager.createQuery(criteria);
		return new HashSet<>(q.getResultList());
	}
}
