package com.play.notification_processor_service.common.repository;

import com.play.notification_processor_service.common.model.NotificationJobEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by idan on 1/14/15.
 */
public interface NotificationRepository extends CrudRepository<NotificationJobEntity, Long> {


}
