package com.play.notification_processor_service.web.controller;

import com.goebl.david.Webb;
import com.play.notification_processor_service.common.dto.ProcessFileRequestDTO;
import com.play.notification_processor_service.common.dto.StatusResponse;
import com.play.notification_processor_service.common.services.NotificationJobService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * Created by idan on 1/15/15.
 */
@RestController
@RequestMapping("/batch")
class JobController {

    @Inject
    NotificationJobService NotificationJobServiceImpl;

    @RequestMapping(value = "/processFileJob", method = RequestMethod.POST)
    @ResponseBody
    public StatusResponse processFileJob(ProcessFileRequestDTO processFileRequestDTO) {
        return NotificationJobServiceImpl.startProcessFileJobExecution(processFileRequestDTO);


    }
}
