package com.play.notification_processor_service.web.config;

import com.play.notification_processor_service.common.config.CommonConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by idan on 1/15/15.
 */
@ComponentScan("com.play.notification_processor_service.web")
@PropertySource("classpath:application.properties")
@Configuration
@Import({CommonConfiguration.class})
public class WebConfiguration {




}
