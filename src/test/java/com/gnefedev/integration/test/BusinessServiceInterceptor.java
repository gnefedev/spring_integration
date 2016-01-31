package com.gnefedev.integration.test;

import com.gnefedev.integration.models.LoggedMessage;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Created by gerakln on 31.01.16.
 */
@Aspect
@Component
public class BusinessServiceInterceptor {
    public static final String MESSAGE_FOR_ERROR = "Message for error";
    public static final String EXCEPTION_MESSAGE = "Generated exception";

    @Before(value = "execution(* com.gnefedev.integration.services.BusinessService.doSomething(..)) && args(loggedMessage)")
    public void checkForError(LoggedMessage loggedMessage) {
        if (loggedMessage.getMessage().equals(MESSAGE_FOR_ERROR)) {
            throw new RuntimeException(EXCEPTION_MESSAGE);
        }
    }
}
