package com.play.notification_processor_service.batch.jobs;

import com.play.notification_processor_service.common.dto.ProcessFileRequestDTO;
import com.play.notification_processor_service.common.dto.StatusResponse;
import com.play.notification_processor_service.common.services.NotificationJobService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;

import javax.inject.Inject;
import java.util.Date;

/**
 * Created by idan on 1/15/15.
 */
public class JobExecuter implements NotificationJobService {

    @Inject
    private JobLauncher jobLauncher;

    @Inject
    private Job processFileJob;

    @Override
    public StatusResponse startProcessFileJobExecution(ProcessFileRequestDTO processFileRequestDTO) {

        //TODO: genereate refId from insights service
        String refId = "abc111";
        String filePath= processFileRequestDTO.getFilePath();
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
