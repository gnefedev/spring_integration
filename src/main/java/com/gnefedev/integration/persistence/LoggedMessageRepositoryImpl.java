package com.gnefedev.integration.persistence;

import com.gnefedev.integration.models.LoggedMessage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;

/**
 * Created by gerakln on 31.01.16.
 */
@Repository
public class LoggedMessageRepositoryImpl implements FindWithLock {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public LoggedMessage getWithLock(int id) {
        return entityManager.find(LoggedMessage.class, id, LockModeType.PESSIMISTIC_WRITE);
    }
}
