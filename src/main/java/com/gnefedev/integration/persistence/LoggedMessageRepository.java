package com.gnefedev.integration.persistence;

import com.gnefedev.integration.models.LoggedMessage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by gerakln on 31.01.16.
 */
@Repository
public interface LoggedMessageRepository extends CrudRepository<LoggedMessage, Integer>, FindWithLock {
    LoggedMessage findByMessage(String message);

    List<LoggedMessage> findBySuccessAndTryCountLessThan(boolean success, int tryCount);
}
