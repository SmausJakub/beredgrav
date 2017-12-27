package cz.zcu.kiv.pia.dao;

import cz.zcu.kiv.pia.domain.BaseObject;

public interface GenericDao <T extends BaseObject> {
	
		T save(T value);

	    T findOne(Long id);

	    void remove(T toRemove);

	    /*
	        Transaction handling, very crude, there are better ways to do this.
	     */

	    void startTransaction();

	    void commitTransaction();

	    void rollbackTransaction();

}
