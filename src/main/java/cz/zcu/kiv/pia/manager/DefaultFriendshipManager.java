package cz.zcu.kiv.pia.manager;

import java.util.List;

import cz.zcu.kiv.pia.dao.FriendshipDao;
import cz.zcu.kiv.pia.domain.Friendship;

public class DefaultFriendshipManager implements FriendshipManager {
	
	private FriendshipDao frDao;
	
	public DefaultFriendshipManager(FriendshipDao frDao) {
		this.frDao = frDao;
	}

	@Override
	public void createFriendship(Friendship fr) {
		
		frDao.startTransaction();
		
		try {
			frDao.createFriendship(fr);
		} catch (Exception e) {
			frDao.rollbackTransaction();
		}
		
		frDao.commitTransaction();
		
	}

	@Override
	public List<Friendship> findAll() {
		return frDao.findAll();
	}

	@Override
	public List<Friendship> findInvolvedFriendships(Long id) {
		return frDao.findByUserId(id);
	}

	@Override
	public boolean areInvolved(Long id1, Long id2) {
		Friendship fr = frDao.findFriendshipByIds(id1, id2);
		if (fr != null) {
			return true;
		} else {
			return false;
		}
	}

	

}
