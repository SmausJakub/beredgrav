package cz.zcu.kiv.pia.jpa;

import javax.persistence.EntityManager;

import cz.zcu.kiv.pia.dao.StatusDao;
import cz.zcu.kiv.pia.domain.Status;

public abstract class StatusDaoJpa extends GenericDaoJpa<Status, Long> implements StatusDao {

	public StatusDaoJpa(EntityManager em) {
		super(em, Status.class);
	}

	@Override
	public Status createStatus(Status status) {
		entityManager.persist(status);
		return status;
	}
}
