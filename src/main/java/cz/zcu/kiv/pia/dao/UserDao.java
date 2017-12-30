package cz.zcu.kiv.pia.dao;


import java.util.List;

import cz.zcu.kiv.pia.domain.User;

/**
 * DAO interface for the User entity
 *
 * Date: 26.9.15
 *
 * @author Jakub Danek
 */
public interface UserDao extends GenericDao<User, Long> {

    User create(User user);
    
    User findByUsername(String username);
    
    List<User> findAll();

}
