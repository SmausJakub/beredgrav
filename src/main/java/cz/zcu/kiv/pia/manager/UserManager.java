package cz.zcu.kiv.pia.manager;

import java.util.List;

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
    
    User findUserByUsername(String username);
    
    List<User> findAllRegisteredUsers();
    
    
}
