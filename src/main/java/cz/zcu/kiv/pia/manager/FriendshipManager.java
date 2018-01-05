package cz.zcu.kiv.pia.manager;

import java.util.List;

import cz.zcu.kiv.pia.domain.Friendship;
import cz.zcu.kiv.pia.domain.FriendshipValidationException;

public interface FriendshipManager {

	/**
	 * creates a new friendship
	 * @param fr friendship
	 * @throws FriendshipValidationException 
	 */
	void createFriendship(Friendship fr);
	
	/**
	 * finds all friendships in the database
	 * @return list of all friendships
	 */
	List<Friendship> findAll();
	
	/**
	 * finds all unapproved friendships where user with given id is involved
	 * @param id  id of a user
	 * @return list of friendships
	 */
	List<Friendship> findUnapproved(Long id);
	
	/**
	 * finds all approved friendships where user with given id is involved
	 * @param id id of a user
	 * @return list of friendships
	 */
	List<Friendship> findApproved(Long id);
	
	/**
	 * finds out if users are friends (approved friendship). Returns their friendship class if they are, null otherwise
	 * @param id1 user1 id
	 * @param id2 user2 id
	 * @return friendship or null
	 */
	Friendship areFriends(Long id1, Long id2);
	
	/**
	 * finds out if users are involved (unapproved or approved friendship). Returns their friendship class if they are, null otherwise
	 * @param id1 user1 id
	 * @param id2 user2 id
	 * @return friendship or null
	 */
	Friendship areInvolved(Long id1, Long id2);
	
	/**
	 * deletes a friendship of given id. Can throw error if id is wrong
	 * @param id id of a friendship
	 * @throws FriendshipValidationException
	 */
	void deleteFriendship(String id) throws FriendshipValidationException;
	
	/**
	 * approved a friendship of given id. Can throw error if id is wrong
	 * @param id id of a friendship
	 * @throws FriendshipValidationException
	 */
	void approveFriendship(String id) throws FriendshipValidationException;
	
	
}
