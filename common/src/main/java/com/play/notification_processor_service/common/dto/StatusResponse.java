package com.play.notification_processor_service.common.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ext_IdanF on 30/12/2014.
 */
public class StatusResponse {
    private Boolean success;
    private List<String> message;

    public StatusResponse() {
        this.message = new ArrayList<String>();
    }

    public StatusResponse(Boolean success) {
        super();
        this.success = success;
        this.message = new ArrayList<String>();
    }

    public StatusResponse(Boolean success, String message) {
        super();
        this.success = success;
        this.message = new ArrayList<String>();
        this.message.add(message);
    }

    public StatusResponse(Boolean success, List<String> message) {
        super();
        this.success = success;
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String mess : message) {
            sb.append(mess + ", ");
        }

        return "StatusResponse [success=" + success + ", message=" + sb.toString()
                + "]";
    }

}