package cz.zcu.kiv.pia.dao;

import java.util.List;

import cz.zcu.kiv.pia.domain.Friendship;

public interface FriendshipDao extends GenericDao<Friendship, Long> {

	
	Friendship createFriendship(Friendship fr);
	
	List<Friendship> findAll();
	
	List<Friendship> findByUserId(Long id);
	
	Friendship findFriendshipByIds(Long id1, Long id2);
	
}
