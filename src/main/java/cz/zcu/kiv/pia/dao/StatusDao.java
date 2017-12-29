package cz.zcu.kiv.pia.dao;

import java.util.List;

import cz.zcu.kiv.pia.domain.Status;

public interface StatusDao {
	
	Status createStatus(Status status);
	
	List<Status> findByUsername(String username);

}
