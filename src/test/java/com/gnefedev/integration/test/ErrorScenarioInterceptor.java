package com.gnefedev.integration.test;

import com.gnefedev.integration.models.LoggedMessage;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Created by gerakln on 31.01.16.
 */
@Aspect
@Component
public class ErrorScenarioInterceptor {
    public static final String MESSAGE_FOR_ERROR = "Message for error";
    public static final String EXCEPTION_MESSAGE = "Generated exception";

    public volatile static boolean pullNotSucceed = false;

    public static void setPullNotSucceed(boolean pullNotSucceed) {
        ErrorScenarioInterceptor.pullNotSucceed = pullNotSucceed;
    }

    @Before(value = "execution(* com.gnefedev.integration.services.BusinessService.doSomething(..)) && args(loggedMessage)")
    public void checkForError(LoggedMessage loggedMessage) {
        if (loggedMessage.getMessage().equals(MESSAGE_FOR_ERROR)) {
            throw new RuntimeException(EXCEPTION_MESSAGE);
        }
    }
    @Around(value = "execution(* com.gnefedev.integration.services.StorageService.getNotSucceed(..)))")
    public Object getIds(ProceedingJoinPoint joinPoint) throws Throwable {
        if (pullNotSucceed) {
            return joinPoint.proceed(joinPoint.getArgs());
        } else {
            return new ArrayList<Integer>();
        }
    }
}
