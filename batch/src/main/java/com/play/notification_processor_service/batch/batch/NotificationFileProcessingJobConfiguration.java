package com.play.notification_processor_service.batch.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Configuration;

import javax.inject.Inject;


/**
 * Created by idan on 1/15/15.
 */

@Configuration
@EnableBatchProcessing
public class NotificationFileProcessingJobConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(NotificationFileProcessingJobConfiguration.class);

    @Inject
    private StepBuilderFactory steps;

    private static final String OVERRIDDEN_BY_EXPRESSION = null;


    @Inject
    private JobBuilderFactory jobs;

    @Inject
    private StepBuilderFactory stepBuilderFactory;


/*
    private Step logJobStatusToDBTasklet() {
        return this.steps.get("step7").tasklet(logJobStatusToDBTasklet).build();
    }*/


}
