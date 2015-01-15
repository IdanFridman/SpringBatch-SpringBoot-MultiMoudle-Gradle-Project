package com.play.notification_processor_service.common;

import com.play.notification_processor_service.common.model.NotificationJobEntity;
import com.play.notification_processor_service.common.model.NotificationTaskEntity;
import com.play.notification_processor_service.common.repository.NotificationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.Date;

/**
 * Created by idan on 1/14/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Application.class)
public class NotificationRepositoryTest {


    @Inject
    NotificationRepository notificationRepository;


    @Test
    public void NotificationRepositoryTest()
    {
        NotificationJobEntity notificationJobEntity=new NotificationJobEntity();
        notificationJobEntity.setCreatedDate(new Date());
        notificationJobEntity.setMessageBody("hello youu");
        notificationJobEntity.setSegmentId("3a33a");

          NotificationTaskEntity notificationTaskEntity=new NotificationTaskEntity();
          notificationTaskEntity.setCreatedDate(new Date());
          notificationTaskEntity.setRefId("23333");
          notificationTaskEntity.setStatus("fail");

          notificationTaskEntity.setNotificationJobEntity(notificationJobEntity);
          notificationJobEntity.getNotificationTaskEntities().add(notificationTaskEntity);
        notificationRepository.save(notificationJobEntity);


    }
}
