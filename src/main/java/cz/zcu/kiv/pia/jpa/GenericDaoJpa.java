package cz.zcu.kiv.pia.jpa;

import java.io.Serializable;

import javax.persistence.EntityManager;

import cz.zcu.kiv.pia.dao.GenericDao;
import cz.zcu.kiv.pia.domain.IEntity;


/**
 * JPA implementation of the GenericDao interface
 *
 * Date: 26.9.15
 *
 * @author Jakub Danek
 */
public class GenericDaoJpa<E extends IEntity<PK>, PK extends Serializable> implements GenericDao<E, PK> {

    protected EntityManager entityManager;
    protected Class<E> persistedClass;

    /**
     *
     * @param em entity manager
     * @param persistedClass entity type to be persisted by this instance
     */
    public GenericDaoJpa(EntityManager em, Class<E> persistedClass) {
        this.entityManager = em;
        this.persistedClass = persistedClass;
    }

    @Override
    public E save(E instance) {
        if(instance.getPK() == null) {
            entityManager.persist(instance);
            return instance;
        } else {
            return entityManager.merge(instance);
        }

    }

    @Override
    public E findOne(PK id) {
        return entityManager.find(persistedClass, id);

    }

    @Override
    public void delete(PK id) {
        E en = entityManager.find(persistedClass, id);
        if(en != null) {
            entityManager.remove(en);
        }

    }
    

    @Override
    public void startTransaction() {
        entityManager.getTransaction().begin();
    }

    @Override
    public void commitTransaction() {
    	entityManager.getTransaction().commit();
    }

    @Override
    public void rollbackTransaction() {
    	entityManager.getTransaction().rollback();
    }

}
