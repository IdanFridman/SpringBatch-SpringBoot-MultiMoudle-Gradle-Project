package com.play.notification_processor_service.batch.tasklet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

/**
 * Created by xorg on 4/25/15.
 */
public class Tasklet2 implements Tasklet, StepExecutionListener {
    private static final Logger logger = LoggerFactory.getLogger(Tasklet2.class);

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        String hashId = (String) chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().get("hashId");
        String filePath = hashId + ".csv";
        logger.info("Tasklet2: filePath=" + filePath + ", hashId=" + hashId);
        chunkContext.getStepContext().getStepExecution().getExecutionContext().put("hashId", hashId);
        chunkContext.getStepContext().getStepExecution().getExecutionContext().put("filePath",filePath);


        return RepeatStatus.FINISHED;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {

    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }
}
