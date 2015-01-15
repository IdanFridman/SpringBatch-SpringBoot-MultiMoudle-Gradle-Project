package com.play.notification_processor_service.common.config;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ComponentScan("com.play.notification_processor_service")
@Configuration
@EnableJpaRepositories(basePackages = {"com.play.notification_processor_service.common.repository"})
@EntityScan({"com.play.notification_processor_service"})
@EnableAutoConfiguration
@EnableTransactionManagement
public class CommonConfiguration {


}