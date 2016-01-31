package com.gnefedev.integration.test;

import com.gnefedev.integration.config.AppConfig;
import com.gnefedev.integration.models.LoggedMessage;
import com.gnefedev.integration.persistence.LoggedMessageRepository;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.Destination;
import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;

/**
 * Created by gerakln on 30.01.16.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, TestConfig.class})
public class ScenarioWithError {
    @Qualifier("queueIn")
    @Autowired
    private Destination queueIn;

    @Qualifier("queueOut")
    @Autowired
    private Destination queueOut;


    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private LoggedMessageRepository loggedMessageRepository;

    @Test
    public void test1SendMessage() {
        jmsTemplate.convertAndSend(queueIn, BusinessServiceInterceptor.MESSAGE_FOR_ERROR);
    }

    @Test
    public void test2CheckStoredMessage() {
        LoggedMessage loggedMessage = getLoggedMessage();
        assertEquals(BusinessServiceInterceptor.MESSAGE_FOR_ERROR, loggedMessage.getMessage());
    }

    @Transactional
    @Test
    public void test3ReceiveMessage() {
        jmsTemplate.setReceiveTimeout(1000);

        String resultMessage = (String) jmsTemplate.receiveAndConvert(queueOut);
        assertEquals(null, resultMessage);

        jmsTemplate.setReceiveTimeout(0);

        LoggedMessage loggedMessage = getLoggedMessage();
        assertEquals(false, loggedMessage.isSuccess());
        assertEquals(BusinessServiceInterceptor.EXCEPTION_MESSAGE, loggedMessage.getError());
    }

    private LoggedMessage getLoggedMessage() {
        return loggedMessageRepository.findByMessage(BusinessServiceInterceptor.MESSAGE_FOR_ERROR);
    }
}
