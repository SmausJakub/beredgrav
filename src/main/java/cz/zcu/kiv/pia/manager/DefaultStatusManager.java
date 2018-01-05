package cz.zcu.kiv.pia.manager;

import java.util.List;
import java.util.Set;

import cz.zcu.kiv.pia.dao.StatusDao;
import cz.zcu.kiv.pia.domain.Status;
import cz.zcu.kiv.pia.domain.StatusValidationException;
import cz.zcu.kiv.pia.domain.User;

public class DefaultStatusManager implements StatusManager {

	private StatusDao statusDao;
	
	public DefaultStatusManager(StatusDao statusDao) {
		this.statusDao = statusDao;
	}
	
	@Override
	public void publishStatus(Status status) {
		
		statusDao.startTransaction();
		
		try {
			statusDao.save(status);
		} catch (Exception e) {
			statusDao.rollbackTransaction();
		}
		
		statusDao.commitTransaction();
		
		
		
	}

	@Override
	public List<Status> findAllStatuses() {
		return statusDao.findAll();
	}

	@Override
	public List<Status> findByIds(List<Long> ids) {
		if (ids.isEmpty()) {
			return null;
		}
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
		
		if (status.getHates().contains(user)) {
			throw new StatusValidationException("Tento status jste již hatoval!");
		}
		
		// if he liked it already, remove the like?
		if (status.getLikes().contains(user)) {

			status.getLikes().remove(user);
			
			statusDao.startTransaction();
			
			try {
				statusDao.save(status);
			} catch (Exception e) {
				statusDao.rollbackTransaction();
			}
			
			statusDao.commitTransaction();
		} else {
			// now he simply likes it
			
			status.getLikes().add(user);
			
			statusDao.startTransaction();
			
			try {
				statusDao.save(status);
			} catch (Exception e) {
				statusDao.rollbackTransaction();
			}
			
			statusDao.commitTransaction();
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
		
		
		if (status.getLikes().contains(user)) {
			throw new StatusValidationException("Tento status jste již likoval!");
		}
		
				if (status.getHates().contains(user)) {

					status.getHates().remove(user);
					
					statusDao.startTransaction();
					
					try {
						statusDao.save(status);
					} catch (Exception e) {
						statusDao.rollbackTransaction();
					}
					
					statusDao.commitTransaction();
				} else {
					// now he simply hate it
					
					status.getHates().add(user);
					
					statusDao.startTransaction();
					
					try {
						statusDao.save(status);
					} catch (Exception e) {
						statusDao.rollbackTransaction();
					}
					
					statusDao.commitTransaction();
				}
		
		
	}

}
