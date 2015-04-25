package com.play.notification_processor_service.common.dto;

/**
 * Created by Ext_IdanF on 11/01/2015.
 */
public class ProcessFileRequestDTO {

    String jobId;
    String taskId;
    String pushMessage;

    public String getPushMessage() {
        return pushMessage;
    }

    public void setPushMessage(String pushMessage) {
        this.pushMessage = pushMessage;
    }


    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    @Override
    public String toString() {
        return "ProcessFileDTO{" +
                ", jobId='" + jobId + '\'' +
                ", taskId='" + taskId + '\'' +
                ", pushMessage='" + pushMessage + '\'' +
                '}';
    }
}