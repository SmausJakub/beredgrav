package cz.zcu.kiv.pia.jpa;

import java.util.Set;

import javax.persistence.EntityManager;

import cz.zcu.kiv.pia.dao.RoleDao;
import cz.zcu.kiv.pia.domain.Role;


/**
 * JPA implementation of the RoleDao interface
 *
 * Date: 26.9.15
 *
 * @author Jakub Danek
 */
public class RoleDaoJpa extends GenericDaoJpa<Role, Long> implements RoleDao {

    /**
     *
     * @param em entity manager to be used by this instance
     */
    public RoleDaoJpa(EntityManager em) {
        super(em, Role.class);
    }

    @Override
    public Set<Role> findByUser(String username) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
