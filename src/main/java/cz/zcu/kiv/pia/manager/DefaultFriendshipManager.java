package cz.zcu.kiv.pia.manager;

import java.util.List;

import cz.zcu.kiv.pia.dao.FriendshipDao;
import cz.zcu.kiv.pia.domain.Friendship;
import cz.zcu.kiv.pia.domain.FriendshipValidationException;

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
	public Friendship areInvolved(Long id1, Long id2) {
		return frDao.findAnyByIds(id1, id2);
	}
	
	@Override
	public Friendship areFriends(Long id1, Long id2) {
		return frDao.findApprovedByIds(id1, id2);
	}

	@Override
	public void deleteFriendship(String id) throws FriendshipValidationException {
		
		if (id == null) {
			throw new FriendshipValidationException("Nesprávné id!");
		}
		
		Long idL;
		
		try {
			idL = Long.parseLong(id);
		} catch (Exception e) {
			throw new FriendshipValidationException("Nesprávné id!");
		}
		
		frDao.startTransaction();
		
		try {

			frDao.delete(idL);	
		} catch (Exception e) {
			frDao.rollbackTransaction();
		}
		
		frDao.commitTransaction();
		
		
	}

	@Override
	public void approveFriendship(String id) throws FriendshipValidationException {
		if (id == null) {
			throw new FriendshipValidationException("Nesprávné id!");
		}
		
		Long idL;
		
		try {
			idL = Long.parseLong(id);
		} catch (Exception e) {
			throw new FriendshipValidationException("Nesprávné id!");
		}
		
		Friendship fr = frDao.findOne(idL);
		
		if (fr == null) {
			throw new FriendshipValidationException("Zadané id nebylo nalezeno!");
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
