package com.play.notification_processor_service.batch.processor;

import com.play.notification_processor_service.common.dto.PushItemDTO;
import org.springframework.batch.item.ItemProcessor;

/**
 * Created by Ext_IdanF on 11/01/2015.
 */
public class PushItemProcessor implements ItemProcessor<PushItemDTO, PushItemDTO> {
    private final String refId;
    private String pushMessage;
    private String jobId;
    private String taskId;

    public PushItemProcessor(String pushMessage, String jobId, String taskId,String refId) {
        this.pushMessage=pushMessage;
        this.jobId=jobId;
        this.taskId=taskId;
        this.refId=refId;


    }

    @Override
    public PushItemDTO process(PushItemDTO item) throws Exception {
        PushItemDTO enrichedPushItem = enrichPushItem(item);
        PushItemDTO pushItemDTO = validateSnidInterval(enrichedPushItem);

        return item;
    }

    private PushItemDTO enrichPushItem(PushItemDTO item) {
        item.setCreated_ts(System.currentTimeMillis());
        item.setMessageUId(java.util.UUID.randomUUID().toString());
        item.setMessageBody(pushMessage);
        item.setJobId(jobId);
        item.setRefId(refId);
        item.setTaskId(taskId);
        return item;
    }

    private PushItemDTO validateSnidInterval(PushItemDTO item) {
        //validate against couchbase;
        return item;
    }
}