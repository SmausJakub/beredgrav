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
	public List<Friendship> findUnapproved(Long id) {
		return frDao.findUnapprovedById(id);
	}

	@Override
	public boolean areInvolved(Long id1, Long id2) {
		Friendship fr = frDao.findFriendshipsAnyApproveByIds(id1, id2);
		if (fr != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void deleteFriendship(Long id) {
		System.out.println("delete friendship " + id);
		
		frDao.startTransaction();
		
		try {

			frDao.delete(id);	
		} catch (Exception e) {
			frDao.rollbackTransaction();
		}
		
		frDao.commitTransaction();
		
		
	}

	@Override
	public void approveFriendship(Long id) {
		Friendship fr = frDao.findOne(id);
		
		if (fr == null) {
			return;
		}
		
		fr.setApproved(1);
		
		frDao.startTransaction();
		
		try {
			frDao.save(fr);
		} catch (Exception e) {
			frDao.rollbackTransaction();
		}
		
		frDao.commitTransaction();
		
	}

	@Override
	public List<Friendship> findApproved(Long id) {
		return frDao.findApprovedById(id);
	}

	

}
