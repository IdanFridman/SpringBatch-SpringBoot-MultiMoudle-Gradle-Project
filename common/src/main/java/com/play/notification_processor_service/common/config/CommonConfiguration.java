package com.play.notification_processor_service.common.config;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.IOException;

@ComponentScan("com.play.notification_processor_service")
@Configuration
@EnableJpaRepositories(basePackages = {"com.play.notification_processor_service.common.repository"})
@EntityScan({"com.play.notification_processor_service"})
@EnableAutoConfiguration
@EnableTransactionManagement
public class CommonConfiguration {

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext context = SpringApplication.run(CommonConfiguration.class);
       /* TestRepository repository = context.getBean(TestRepository.class);
        Test test = new Test();
        test.setText("test");
        repository.save(test);
        repository.findAll().iterator().forEachRemaining(f -> System.out.println(f));
        context.close();*/
    }
}