package com.gnefedev.integration.config;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by gerakln on 31.01.16.
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.gnefedev.integration.persistence")
@EntityScan(basePackages = "com.gnefedev.integration.persistence")
public class JpaConfig {

}
