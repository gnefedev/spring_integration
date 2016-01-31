package com.gnefedev.integration.services;

import com.gnefedev.integration.models.LoggedMessage;
import com.gnefedev.integration.persistence.LoggedMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
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

    public Message markAsSuccess (Message message, @Header(name = "loggedMessageId", required = true) int id) {
        LoggedMessage loggedMessage = loggedMessageRepository.findOne(id);
        loggedMessage.setSuccess(true);
        loggedMessageRepository.save(loggedMessage);
        return message;
    }
}
