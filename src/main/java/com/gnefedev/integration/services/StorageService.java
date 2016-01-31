package com.gnefedev.integration.services;

import com.gnefedev.integration.models.LoggedMessage;
import com.gnefedev.integration.persistence.LoggedMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.support.ErrorMessage;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gerakln on 31.01.16.
 */
@Component("storageService")
@Transactional
public class StorageService {

    @Autowired
    private LoggedMessageRepository loggedMessageRepository;

    public int storeInDB(String message){
        LoggedMessage loggedMessage = new LoggedMessage();
        loggedMessage.setMessage(message);
        loggedMessageRepository.save(loggedMessage);
        return loggedMessage.getId();
    }

    public List<Integer> getNotSucceed() {
        List<LoggedMessage> loggedMessages = loggedMessageRepository.findBySuccessAndTryCountLessThan(false, 5);
        List<Integer> idList = new ArrayList<Integer>();
        for(LoggedMessage loggedMessage: loggedMessages) {
            idList.add(loggedMessage.getId());
        }
        return idList;
    }

    public LoggedMessage findAndLockMessage(int loggedMessageId) {
        return loggedMessageRepository.getWithLock(loggedMessageId);
    }

    public Message markAsSuccessAndUpdate(Message message, @Header(name = "loggedMessage", required = true) LoggedMessage loggedMessage) {
        loggedMessage.setSuccess(true);
        loggedMessageRepository.save(loggedMessage);
        return message;
    }

    public void markAsError(ErrorMessage errorMessage) {
        MessagingException error = (MessagingException) errorMessage.getPayload();
        LoggedMessage loggedMessage = (LoggedMessage) error.getFailedMessage().getPayload();
        loggedMessage = loggedMessageRepository.getWithLock(loggedMessage.getId());
        loggedMessage.setError(getCause(error).getMessage());
        loggedMessage.setSuccess(false);
        loggedMessage.incrementTry();
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
