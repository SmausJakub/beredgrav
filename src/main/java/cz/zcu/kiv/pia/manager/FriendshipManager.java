package cz.zcu.kiv.pia.manager;

import java.util.List;

import cz.zcu.kiv.pia.domain.Friendship;

public interface FriendshipManager {

	void createFriendship(Friendship fr);
	
	List<Friendship> findAll();
	
	List<Friendship> findInvolvedFriendships(Long id);
	
	boolean areInvolved(Long id1, Long id2);
	
}
