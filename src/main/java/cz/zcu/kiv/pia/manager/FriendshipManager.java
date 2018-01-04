package cz.zcu.kiv.pia.manager;

import java.util.List;

import cz.zcu.kiv.pia.domain.Friendship;

public interface FriendshipManager {

	void createFriendship(Friendship fr);
	
	List<Friendship> findAll();
	
	List<Friendship> findUnapproved(Long id);
	
	List<Friendship> findApproved(Long id);
	
	boolean areInvolved(Long id1, Long id2);
	
	void deleteFriendship(Long id);
	
	void approveFriendship(Long id);
	
	
}
