package cz.zcu.kiv.pia.manager;

import java.util.List;

import cz.zcu.kiv.pia.dao.StatusDao;
import cz.zcu.kiv.pia.domain.Status;

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

}
