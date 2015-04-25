package com.play.notification_processor_service.batch.config;

import com.play.notification_processor_service.common.config.CommonConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Created by idan on 1/15/15.
 */
@ComponentScan("com.play.notification_processor_service.batch")
@Configuration
@Import({CommonConfiguration.class})
public class BatchConfiguration {
/*
    @Value("${database.driver}")
    private String databaseDriver;
    @Value("${database.url}")
    private String databaseUrl;
    @Value("${database.username}")
    private String databaseUsername;
    @Value("${database.password}")
    private String databasePassword;



    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(databaseDriver);
        dataSource.setUrl(databaseUrl);
        dataSource.setUsername(databaseUsername);
        dataSource.setPassword(databasePassword);
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }*/


}
