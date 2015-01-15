package com.play.notification_processor_service.web.main;

import com.play.notification_processor_service.batch.config.BatchConfiguration;
import com.play.notification_processor_service.common.config.CommonConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by idan on 1/15/15.
 */
@ComponentScan("com.play.notification_processor_service.web")
@PropertySource("classpath:application.properties")
@Configuration
@Import({CommonConfiguration.class, BatchConfiguration.class})
@EnableJpaRepositories(basePackages = {"com.play.notification_processor_service.common.repository"})
@EntityScan({"com.play.notification_processor_service"})
@EnableAutoConfiguration
@EnableTransactionManagement
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.printf("Started the app");
    }
}
