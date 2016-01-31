package com.gnefedev.integration.services;

import com.gnefedev.integration.models.LoggedMessage;
import com.gnefedev.integration.persistence.LoggedMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.support.ErrorMessage;
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

    public void markAsError(ErrorMessage errorMessage) {
        MessagingException error = (MessagingException) errorMessage.getPayload();
        LoggedMessage loggedMessage = (LoggedMessage) error.getFailedMessage().getPayload();
        loggedMessage = loggedMessageRepository.findOne(loggedMessage.getId());
        loggedMessage.setError(getCause(error).getMessage());
        loggedMessage.setSuccess(false);
        loggedMessageRepository.save(loggedMessage);
    }

    private Throwable getCause(MessagingException errorMessage) {
        if(errorMessage.getCause() instanceof MessagingException) {
            return getCause((MessagingException) errorMessage.getCause());
        } else {
            return errorMessage.getCause();
        }
    }
}
