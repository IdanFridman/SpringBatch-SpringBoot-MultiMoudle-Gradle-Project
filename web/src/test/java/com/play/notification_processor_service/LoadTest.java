package com.play.notification_processor_service;

import com.goebl.david.Webb;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xorg on 4/25/15.
 */
public class LoadTest {

    ExecutorService executerService = Executors.newFixedThreadPool(30);

    @Test
    public void parallelJobTest() {
                    executeTest();

    }

    private void executeTest() {
        Webb webb = Webb.create();
        webb.post("http://localhost:8080/batch/processFileJob")
                .param("jobId", "1")
                .param("taskId", "1")
                .param("pushMessage", "test").
                ensureSuccess().
                asVoid();
    }
}
