package com.play.notification_processor_service.common.services;

import com.play.notification_processor_service.common.dto.ProcessFileRequestDTO;
import com.play.notification_processor_service.common.dto.StatusResponse;

/**
 * Created by idan on 1/15/15.
 */
public interface NotificationJobService {

    public StatusResponse startProcessFileJobExecution(ProcessFileRequestDTO processFileRequestDTO);

}