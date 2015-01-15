package com.play.notification_processor_service.batch;

import org.junit.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by idan on 1/15/15.
 */
@Configuration
public class LogJobStatusToDBTaskletTest extends AbstractJobLauncher {

    @Autowired
    protected Job processFileJob;




    @Bean
    public JobLauncherTestUtils jobLauncherTestUtils() {
        JobLauncherTestUtils jobLauncherTestUtils = new JobLauncherTestUtils();
        jobLauncherTestUtils.setJob(processFileJob);
        return jobLauncherTestUtils;
    }


    @Test
    public void startBatch() throws Exception {
        JobParameters params = new JobParametersBuilder (super.jobLauncherTestUtils.getUniqueJobParameters()).toJobParameters();
        jobLauncher.run(processFileJob, params);
    }
}
