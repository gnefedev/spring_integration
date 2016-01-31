package com.gnefedev.integration.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration;
import org.springframework.boot.autoconfigure.test.ImportAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;

/**
 * Created by gerakln on 30.01.16.
 */
@Configuration
public class JmsConfig {
    @Bean(name = "connectionFactory")
    public ConnectionFactory connectionFactory() {
        return new ActiveMQConnectionFactory("vm://localhost?broker.persistent=false");
    }

    @Bean(name = "queueIn")
    public Destination inQueue () {
        return new ActiveMQQueue("test_in");
    }

    @Bean(name = "queueOut")
    public Destination outQueue () {
        return new ActiveMQQueue("test_out");
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        return new JmsTemplate(connectionFactory());
    }

}
