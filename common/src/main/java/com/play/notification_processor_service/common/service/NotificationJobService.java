package com.play.notification_processor_service.common.service;/*
package com.play.notification_processor_service.core.services;

import javax.inject.Inject;
import javax.inject.Named;

*/
/**
 * Created by idan on 1/15/15.
 *//*

@Named
public class NotificationJobService {

    @Inject
    private JobLauncher jobLauncher;

    @Inject
    private Job processFileJob;

    public NotificationJobService() {
    }

    public String startProcessFileJobExecution(ProcessFileRequestDTO processFileRequestDTO) {
        //TODO: genereate refId
        String filePath= processFileRequestDTO.getFilePath();
        String jobId= processFileRequestDTO.getJobId();
        String taskId= processFileRequestDTO.getTaskId();
        String pushMessage= processFileRequestDTO.getPushMessage();
        String refId = "abc111";
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
*/
