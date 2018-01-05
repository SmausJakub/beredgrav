package cz.zcu.kiv.pia.manager;

import java.util.List;

import cz.zcu.kiv.pia.domain.Status;
import cz.zcu.kiv.pia.domain.StatusValidationException;
import cz.zcu.kiv.pia.domain.User;

public interface StatusManager {

	void publishStatus(Status status);
	
	List<Status> findAllStatuses();
	
	List<Status> findByIds(List<Long> ids);
	
	Status findStatusById(Long id);
	
	void likeStatus(User user, String id) throws StatusValidationException;
	
	void hateStatus(User user, String id) throws StatusValidationException;
	
}
