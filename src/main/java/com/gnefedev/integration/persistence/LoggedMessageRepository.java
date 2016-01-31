package com.gnefedev.integration.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by gerakln on 31.01.16.
 */
@Repository
public interface LoggedMessageRepository extends CrudRepository<LoggedMessage, Integer> {
}