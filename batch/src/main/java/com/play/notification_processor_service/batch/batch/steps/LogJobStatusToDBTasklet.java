package com.play.notification_processor_service.batch.batch.steps;

import com.play.notification_processor_service.common.model.NotificationJobEntity;
import com.play.notification_processor_service.common.model.NotificationTaskEntity;
import com.play.notification_processor_service.common.repository.NotificationRepository;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import javax.inject.Inject;
import java.util.Date;

/**
 * Created by idan on 1/15/15.
 */
public class LogJobStatusToDBTasklet implements Tasklet {

    @Inject
    NotificationRepository notificationRepository;


    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        saveToDB();
        return RepeatStatus.FINISHED;
    }

    public void saveToDB() {
        NotificationJobEntity notificationJobEntity=new NotificationJobEntity();
        notificationJobEntity.setCreatedDate(new Date());
        notificationJobEntity.setMessageBody("mishiu");
        notificationJobEntity.setSegmentId("33");

        NotificationTaskEntity notificationTaskEntity=new NotificationTaskEntity();
        notificationTaskEntity.setCreatedDate(new Date());
        notificationTaskEntity.setRefId("23333");
        notificationTaskEntity.setStatus("fail");

        notificationTaskEntity.setNotificationJobEntity(notificationJobEntity);
        notificationJobEntity.getNotificationTaskEntities().add(notificationTaskEntity);
        notificationRepository.save(notificationJobEntity);

    }
}
