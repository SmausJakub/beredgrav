package cz.zcu.kiv.pia.jpa;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import cz.zcu.kiv.pia.domain.Role;


public class RoleDaoJPQL extends RoleDaoJpa
{
	public RoleDaoJPQL(EntityManager em)
	{
		super(em);
	}
	
	@Override
	public Set<Role> findByUser(String username) 
	{
		Query q = entityManager.createQuery("SELECT u.roles FROM User u WHERE u.username = :username");
		q.setParameter("username", username);
		
		return new HashSet<>(q.getResultList());
	}
}
