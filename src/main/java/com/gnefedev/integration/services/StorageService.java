package com.gnefedev.integration.services;

import com.gnefedev.integration.persistence.LoggedMessage;
import com.gnefedev.integration.persistence.LoggedMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * Created by gerakln on 31.01.16.
 */
@Component("storageService")
@Transactional
public class StorageService {

    @Autowired
    private LoggedMessageRepository loggedMessageRepository;

    public LoggedMessage storeInDB(String message){
        LoggedMessage loggedMessage = new LoggedMessage();
        loggedMessage.setMessage(message);
        loggedMessageRepository.save(loggedMessage);
        return loggedMessage;
    }
}
