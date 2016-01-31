package com.gnefedev.integration.persistence;

import com.gnefedev.integration.models.LoggedMessage;

/**
 * Created by gerakln on 31.01.16.
 */
public interface FindWithLock {
    public LoggedMessage getWithLock(int id);
}
