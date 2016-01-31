package com.gnefedev.integration.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

/**
 * Created by gerakln on 31.01.16.
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.gnefedev.integration.services")
@Import({JmsConfig.class, IntegrationConf.class, JpaConfig.class})
public class AppConfig {
    @Bean(name = "xmlMarshaller")
    public Marshaller jaxb() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setPackagesToScan("com.gnefedev.integration.persistence");
        return marshaller;
    }
}