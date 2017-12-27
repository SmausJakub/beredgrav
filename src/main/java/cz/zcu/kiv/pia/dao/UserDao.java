package cz.zcu.kiv.pia.dao;

import cz.zcu.kiv.pia.domain.User;

public interface UserDao extends GenericDao<User> {
	
    /**
    *
    * @param username the requested username
    * @return user with the given username or null
    */
   User findByUsername(String username);

}
