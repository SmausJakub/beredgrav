package cz.zcu.kiv.pia.manager;

import java.util.List;

import cz.zcu.kiv.pia.domain.Status;
import cz.zcu.kiv.pia.domain.User;
import cz.zcu.kiv.pia.domain.UserValidationException;

public interface UserManager {

    /**
     * Method for authentication of user's credentials.
     *
     * @param username provided login
     * @param password provided password
     * @return true if username and password are a match, false otherwise
     */
    boolean authenticate(String username, String password);

    /**
     * Method for registering a new user.
     * @param newUser instance with new user data, expected not-null value
     * @throws UserValidationException if the new user data instance is not in valid state,
     *                                 e.g. required fields are missing
     */
    void register(User newUser) throws UserValidationException;
    
    /**
     * finds a user by his username. Null if not found
     * @param username users username
     * @return user
     */
    User findUserByUsername(String username);
    
    /**
     * finds all registered users in the database
     * @return list of registered users
     */
    List<User> findAllRegisteredUsers();
    
    /**
     * updates user with given params. If they are null, upgrade is not done
     * @param username users username
     * @param password users password
     * @param email users email
     * @param gender gender of user
     */
	void updateUser(String username, String password, String email, String gender);
	
	/**
	 * updates the users avatar
	 * @param username users username
	 * @param avatarUrl the avatar
	 */
	void updateAvatar(String username, String avatarUrl);
	
	/**
	 * removes a like
	 * @param user
	 * @param status
	 */
	void removeLike(User user, Status status);
	
	/**
	 * removes a hate
	 * @param user
	 * @param status
	 */
	void removeHate(User user, Status status);
	
	/**
	 * adds a like
	 * @param user
	 * @param status
	 */
	void addLike(User user, Status status);
	
	/**
	 * adds a hate
	 * @param user
	 * @param status
	 */
	void addHate(User user, Status status);
    
    
}
