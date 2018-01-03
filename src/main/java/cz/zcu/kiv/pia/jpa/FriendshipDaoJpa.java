package cz.zcu.kiv.pia.jpa;

import javax.persistence.EntityManager;

import cz.zcu.kiv.pia.dao.FriendshipDao;
import cz.zcu.kiv.pia.domain.Friendship;

public abstract class FriendshipDaoJpa extends GenericDaoJpa<Friendship, Long> implements FriendshipDao {

	public FriendshipDaoJpa(EntityManager em) {
		super(em, Friendship.class);
	}
	
	
	@Override
	public Friendship createFriendship(Friendship fr) {
		entityManager.persist(fr);
		return fr;
	}
	

}
