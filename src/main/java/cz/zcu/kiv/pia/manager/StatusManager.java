package cz.zcu.kiv.pia.manager;

import java.util.List;

import cz.zcu.kiv.pia.domain.Status;
import cz.zcu.kiv.pia.domain.StatusValidationException;
import cz.zcu.kiv.pia.domain.User;

public interface StatusManager {

	/**
	 * creates a new status
	 * @param status new status
	 */
	void publishStatus(Status status);
	
	/**
	 * gets all statuses from database
	 * @return list of all statuses
	 */
	List<Status> findAllStatuses();
	
	/**
	 * finds a status where the owner is one of the ids
	 * @param ids owner ids
	 * @return list of statuses done by the users ids
	 */
	List<Status> findByIds(List<Long> ids);
	
	/**
	 * finds a single status by its id
	 * @param id id of status
	 * @return status
	 */
	Status findStatusById(Long id);
	
	/**
	 * likes a status. Can throw error when provided id is wrong. If the user liked status already, the like is removed. If he hated it already, error is thrown
	 * @param user the user
	 * @param id id of a status
	 * @throws StatusValidationException
	 */
	void likeStatus(User user, String id) throws StatusValidationException;
	
	/**
	 * hates a status. Can throw error when provided id is wrong. If the user hated status already, the hate is removed. If he liked it already, error is thrown
	 * @param user the user
	 * @param id id of a status
	 * @throws StatusValidationException
	 */
	void hateStatus(User user, String id) throws StatusValidationException;
	
}
