package com.play.notification_processor_service.batch.tasklet;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import java.util.Random;
import java.util.UUID;

/**
 * Created by xorg on 4/25/15.
 */
public class Tasklet1 implements Tasklet,StepExecutionListener {
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {


        //generate hashId
        String hashId= UUID.randomUUID().toString();


        chunkContext.getStepContext().getStepExecution().getExecutionContext().put("hashId",hashId);
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
