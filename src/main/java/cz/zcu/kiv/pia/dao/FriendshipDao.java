package cz.zcu.kiv.pia.dao;

import java.util.List;

import cz.zcu.kiv.pia.domain.Friendship;

public interface FriendshipDao extends GenericDao<Friendship, Long> {

	
	Friendship createFriendship(Friendship fr);
	
	List<Friendship> findAll();
	
	List<Friendship> findUnapprovedById(Long id);
	
	List<Friendship> findApprovedById(Long id);
	
	Friendship findApprovedByIds(Long id1, Long id2);
	
	Friendship findAnyByIds(Long id1, Long id2);
	
}
