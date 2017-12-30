package cz.zcu.kiv.pia.manager;

import java.util.List;

import cz.zcu.kiv.pia.domain.Status;

public interface StatusManager {

	void publishStatus(Status status);
	
	List<Status> findAllStatuses();
	
}
