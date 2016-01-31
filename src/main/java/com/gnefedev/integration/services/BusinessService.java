package com.gnefedev.integration.services;

import com.gnefedev.integration.persistence.LoggedMessage;
import org.springframework.stereotype.Component;

/**
 * Created by gerakln on 31.01.16.
 */
@Component("businessService")
public class BusinessService {
    public LoggedMessage doSomething(LoggedMessage message) {
//        throw new RuntimeException();
        message.setMessage(message.getMessage() + " I'm George");
        return message;
    }
}
