package com.gnefedev.integration.services;

import com.gnefedev.integration.models.LoggedMessage;
import com.gnefedev.integration.models.ResultMessage;
import org.springframework.stereotype.Component;

/**
 * Created by gerakln on 31.01.16.
 */
@Component("businessService")
public class BusinessService {
    public ResultMessage doSomething(LoggedMessage loggedMessage) {
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setMessage(loggedMessage.getMessage());
        resultMessage.setName("George");
        return resultMessage;
    }
}
