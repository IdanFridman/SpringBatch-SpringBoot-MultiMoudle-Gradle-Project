package com.play.notification_processor_service.common.dto;


/**
 * Created by Ext_IdanF on 09/12/2014.
 */
public class PushItemDTO{ //implements IBaseMQItem {
    private String messageUId;
    private String snid;
    private long created_ts;
    private String messageBody;
    private String jobId;
    private String taskId;
    private String refId;
    private long send_ts;

    public PushItemDTO() {
    }

    public PushItemDTO(String messageUId, String snid, long created_ts, String messageBody, String jobId, String taskId, String refId) {
        this.messageUId = messageUId;
        this.snid = snid;
        this.created_ts = created_ts;
        this.messageBody = messageBody;
        this.jobId = jobId;
        this.taskId = taskId;
        this.refId = refId;
    }


    public String getMessageUId() {
        return messageUId;
    }

    public void setMessageUId(String messageUId) {
        this.messageUId = messageUId;
    }

    public String getSnid() {
        return snid;
    }

    public void setSnid(String snid) {
        this.snid = snid;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }


    public long getCreated_ts() {
        return created_ts;
    }

    public void setCreated_ts(long created_ts) {
        this.created_ts = created_ts;
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

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public long getSend_ts() {
        return send_ts;
    }

    public void setSend_ts(long send_ts) {
        this.send_ts = send_ts;
    }


    @Override
    public String toString() {
        return "PushMessage{" +
                "messageUId='" + messageUId + '\'' +
                ", snid='" + snid + '\'' +
                ", created_ts=" + created_ts +
                ", messageBody='" + messageBody + '\'' +
                ", jobId='" + jobId + '\'' +
                ", taskId='" + taskId + '\'' +
                ", refId='" + refId + '\'' +
                ", send_ts=" + send_ts +
                '}';
    }
}