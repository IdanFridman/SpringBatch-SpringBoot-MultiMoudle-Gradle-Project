package com.play.notification_processor_service.batch.jobs;

import com.play.notification_processor_service.common.dto.ProcessFileRequestDTO;
import com.play.notification_processor_service.common.dto.StatusResponse;
import com.play.notification_processor_service.common.services.NotificationJobService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.scheduling.annotation.Async;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by idan on 1/15/15.
 */
@Named
public class NotificationJobServiceImpl implements NotificationJobService {

    @Inject
    private JobLauncher jobLauncher;

    @Inject
    private Job processFileJob;

    ExecutorService executorService= Executors.newFixedThreadPool(30);

    @Override
    @Async
    public StatusResponse startProcessFileJobExecution(final ProcessFileRequestDTO processFileRequestDTO) {

        for (int i=0;i<10;i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    execute(processFileRequestDTO);
                }
            });
        }
     return null;
    }

    private StatusResponse execute(ProcessFileRequestDTO processFileRequestDTO) {
        //TODO: genereate refId from insights service
        String refId = "abc111";
        String filePath= "PLACE_HOLDER";
        String jobId= processFileRequestDTO.getJobId();
        String taskId= processFileRequestDTO.getTaskId();
        String pushMessage= processFileRequestDTO.getPushMessage();
        try {
            JobParameters jobParameters = new JobParametersBuilder().addString("refId", refId).addString("jobId", jobId).addString("pushMessage",pushMessage).
                    addString("taskId", taskId).addString("filePath", filePath).addDate("date", new Date()).toJobParameters();
            jobLauncher.run(processFileJob, jobParameters);
            return new StatusResponse(true);
        } catch (JobInstanceAlreadyCompleteException ex) {
            return new StatusResponse(false, "This job has been completed already!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
