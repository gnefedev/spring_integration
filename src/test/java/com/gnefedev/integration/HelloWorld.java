package com.gnefedev.integration;

import com.gnefedev.integration.config.AppConfig;
import com.gnefedev.integration.persistence.LoggedMessageRepository;
import org.junit.FixMethodOrder;
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

import static org.junit.Assert.*;

/**
 * Created by gerakln on 30.01.16.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class HelloWorld {
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
        jmsTemplate.convertAndSend(queueIn, "Hello World!!!");
    }

    @Test
    public void test2CheckStoredMessage() {
        String message = loggedMessageRepository.findAll().iterator().next().getMessage();
        assertEquals("Hello World!!!", message);
    }

    @Transactional
    @Test
    public void test3ReceiveMessage() {
        jmsTemplate.setReceiveTimeout(1000);

        String message = (String) jmsTemplate.receiveAndConvert(queueOut);
        assertEquals(
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
                        "<resultMessage>" +
                        "<message>Hello World!!!</message>" +
                        "<name>George</name>" +
                        "</resultMessage>",
                message);

        jmsTemplate.setReceiveTimeout(0);
    }
}
