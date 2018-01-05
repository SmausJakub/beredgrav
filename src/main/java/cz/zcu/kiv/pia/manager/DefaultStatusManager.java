package cz.zcu.kiv.pia.manager;

import java.util.List;

import cz.zcu.kiv.pia.dao.StatusDao;
import cz.zcu.kiv.pia.domain.Status;
import cz.zcu.kiv.pia.domain.StatusValidationException;
import cz.zcu.kiv.pia.domain.User;

public class DefaultStatusManager implements StatusManager {

	private StatusDao statusDao;
	
	private UserManager userManager;
	
	public DefaultStatusManager(StatusDao statusDao, UserManager userManager) {
		this.statusDao = statusDao;
		this.userManager = userManager;
	}
	
	@Override
	public void publishStatus(Status status) {
		
		statusDao.startTransaction();
		
		try {
			statusDao.save(status);
			statusDao.commitTransaction();
		} catch (Exception e) {
			statusDao.rollbackTransaction();
		}
		
		
		
		
	}

	@Override
	public List<Status> findAllStatuses() {
		return statusDao.findAll();
	}

	@Override
	public List<Status> findByIds(List<Long> ids) {
		return statusDao.findByIds(ids);
	}

	@Override
	public Status findStatusById(Long id) {
		return statusDao.findOne(id);
	}

	@Override
	public void likeStatus(User user, String id) throws StatusValidationException {

		if (id == null) {
			throw new StatusValidationException("Nesprávné id!");
		}
		
		Long idL;
		
		try {
			idL = Long.parseLong(id);
		} catch (Exception e) {
			throw new StatusValidationException("Nesprávné id!");
		}
		
		Status status = this.findStatusById(idL);
		
		if (status == null) {
			throw new StatusValidationException("Status nenalezen!");
		}
		
		if (user.getHates().contains(status)) {
			throw new StatusValidationException("Tento status jste již hatoval!");
		}
		
		// if he liked it already, remove the like
		if (user.getLikes().contains(status)) {
			
			userManager.removeLike(user, status);
			
			status.getLikes().remove(user);
			
			statusDao.startTransaction();
			
			try {
				statusDao.save(status);
				statusDao.commitTransaction();
			} catch (Exception e) {
				statusDao.rollbackTransaction();
			}

		} else {
			// now he simply likes it
			
			userManager.addLike(user, status);
			
			status.getLikes().add(user);
			
			statusDao.startTransaction();
			
			try {
				statusDao.save(status);
				statusDao.commitTransaction();
			} catch (Exception e) {
				statusDao.rollbackTransaction();
			}
		}
		
		
	}

	@Override
	public void hateStatus(User user, String id) throws StatusValidationException {
		if (id == null) {
			throw new StatusValidationException("Nesprávné id!");
		}
		
		Long idL;
		
		try {
			idL = Long.parseLong(id);
		} catch (Exception e) {
			throw new StatusValidationException("Nesprávné id!");
		}
		
		Status status = this.findStatusById(idL);
		
		if (status == null) {
			throw new StatusValidationException("Status nenalezen!");
		}
		
		
		if (user.getLikes().contains(status)) {
			throw new StatusValidationException("Tento status jste již likoval!");
		}
		
				if (user.getHates().contains(status)) {
					
					userManager.removeHate(user, status);

					status.getHates().remove(user);
					
					statusDao.startTransaction();
					
					try {
						statusDao.save(status);
						statusDao.commitTransaction();
					} catch (Exception e) {
						statusDao.rollbackTransaction();
					}
					
				} else {
					// now he simply hates it
					
					userManager.addHate(user, status);
					
					status.getHates().add(user);
					
					statusDao.startTransaction();
					
					try {
						statusDao.save(status);
						statusDao.commitTransaction();
					} catch (Exception e) {
						statusDao.rollbackTransaction();
					}
					
				}
		
		
	}

}
