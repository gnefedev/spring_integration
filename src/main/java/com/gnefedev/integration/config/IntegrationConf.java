package com.gnefedev.integration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;

/**
 * Created by gerakln on 31.01.16.
 */
@Configuration
@ImportResource("classpath:/spring/integration.xml")
public class IntegrationConf {
//    @Bean
//    public MessageChannel inChannel() {
//        return new DirectChannel();
//    }
//    @Bean
//    public MessageChannel outChannel() {
//        return new DirectChannel();
//    }

}
