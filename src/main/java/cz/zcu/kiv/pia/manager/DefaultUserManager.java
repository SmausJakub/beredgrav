package cz.zcu.kiv.pia.manager;


import cz.zcu.kiv.pia.dao.UserDao;
import cz.zcu.kiv.pia.domain.User;
import cz.zcu.kiv.pia.domain.UserValidationException;
import cz.zcu.kiv.pia.utils.Encoder;

public class DefaultUserManager implements UserManager {

	 private UserDao userDao;
	    private Encoder encoder;

	    public DefaultUserManager(UserDao userDao, Encoder encoder) {
	        this.userDao = userDao;
	        this.encoder = encoder;
	    }

	    @Override
	    public boolean authenticate(String username, String password) {
	        User u = userDao.findByUsername(username);
	        System.out.println(u);
	        return u != null && encoder.validate(password, u.getPassword());
	    }

	    @Override
	    public void register(User newUser) throws UserValidationException {
	        newUser.validate();

	        User existinCheck = userDao.findByUsername(newUser.getUsername());
	        if(existinCheck != null) {
	            throw new UserValidationException("Vámi zadané pøihlašovací jméno je již zabrané, vyberte prosím jiné.");
	        }

	        newUser.setPassword(encoder.encode(newUser.getPassword()));

	        userDao.startTransaction();
	        try {
	            userDao.save(newUser);
	        } catch (Exception e) {
	            userDao.rollbackTransaction();
	        }
	        userDao.commitTransaction();
	    }
}
