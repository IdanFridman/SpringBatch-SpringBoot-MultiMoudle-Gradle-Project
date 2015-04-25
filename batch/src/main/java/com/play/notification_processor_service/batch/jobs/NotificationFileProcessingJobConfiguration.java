package com.play.notification_processor_service.batch.jobs;

import com.play.notification_processor_service.batch.tasklet.Tasklet1;
import com.play.notification_processor_service.batch.tasklet.Tasklet2;
import com.play.notification_processor_service.batch.writer.KafkaWriter;
import com.play.notification_processor_service.batch.tasklet.LogJobStatusToDBTasklet;
import com.play.notification_processor_service.batch.processor.PushItemProcessor;
import com.play.notification_processor_service.batch.utils.PushItemFieldSetMapper;
import com.play.notification_processor_service.common.dto.PushItemDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.listener.ExecutionContextPromotionListener;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.io.FileSystemResource;

import javax.inject.Inject;


/**
 * Created by idan on 1/15/15.
 */

@Configuration
@EnableBatchProcessing
public class NotificationFileProcessingJobConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(NotificationFileProcessingJobConfiguration.class);

    @Inject
    private StepBuilderFactory steps;

    private static final String OVERRIDDEN_BY_EXPRESSION = null;


    @Inject
    private JobBuilderFactory jobs;

    @Inject
    private StepBuilderFactory stepBuilderFactory;


    @Bean
    public Job job() throws Exception {
        return this.jobs.get("processFileJob").
                start(tasklet1Step())
                .next(tasklet2Step())
                .next(processSnidFileStep())
                .build();

    }


    private Step logJobStatusToDBTasklet() {
        return this.steps.get("step7").tasklet(tasklet()).build();
    }

    @Bean
    protected Tasklet tasklet() {
        return new LogJobStatusToDBTasklet();
    }

    @Bean
    public Step processSnidFileStep() {
        return stepBuilderFactory.get("processSnidFileStep")
                .<PushItemDTO, PushItemDTO>chunk(1) //important to be one in this case to commit after every line read
                .reader(reader(OVERRIDDEN_BY_EXPRESSION, OVERRIDDEN_BY_EXPRESSION))
                .processor(processor(OVERRIDDEN_BY_EXPRESSION, OVERRIDDEN_BY_EXPRESSION, OVERRIDDEN_BY_EXPRESSION, OVERRIDDEN_BY_EXPRESSION))
                .writer(writer())

                .build();
    }

    @Bean
    public ItemWriter writer() {
        return new KafkaWriter();
    }

    @Bean
    @StepScope
    public ItemStreamReader<PushItemDTO> reader(@Value("#{jobExecutionContext[filePath]}") String filePath,
                                                @Value("#{jobExecutionContext[hashId]}") String hashId) {
        logger.info("Reader: hashId=" + hashId + ",filePath=" + filePath);
        FlatFileItemReader<PushItemDTO> itemReader = new FlatFileItemReader<PushItemDTO>();
        itemReader.setLineMapper(lineMapper());
        itemReader.setLinesToSkip(1);
        itemReader.setStrict(false);
        itemReader.setResource(new FileSystemResource(filePath));
        return itemReader;
    }


    @Bean
    @StepScope
    public ItemProcessor<PushItemDTO, PushItemDTO> processor(@Value("#{jobParameters[pushMessage]}") String pushMessage,
                                                             @Value("#{jobParameters[jobId]}") String jobId,
                                                             @Value("#{jobParameters[taskId]}") String taskId,
                                                             @Value("#{jobParameters[refId]}") String refId) {
        return new PushItemProcessor(pushMessage, jobId, taskId, refId);
    }

    @Bean
    public LineMapper<PushItemDTO> lineMapper() {
        DefaultLineMapper<PushItemDTO> lineMapper = new DefaultLineMapper<PushItemDTO>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(true);
        lineTokenizer.setNames(new String[]{"user_id"
        });
        BeanWrapperFieldSetMapper<PushItemDTO> fieldSetMapper = new BeanWrapperFieldSetMapper<PushItemDTO>();
        fieldSetMapper.setTargetType(PushItemDTO.class);


        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(new PushItemFieldSetMapper());


        return lineMapper;
    }

    @Bean
    protected Tasklet tasklet1() {
        return new Tasklet1();
    }

    @Bean
    protected Tasklet tasklet2() {
        return new Tasklet2();
    }

    private Step tasklet1Step() {
        return this.steps.get("tasklet1Step").tasklet(tasklet1()).listener(executionContextPromotionListener()).build();
    }

    private Step tasklet2Step() {
        return this.steps.get("tasklet2Step").tasklet(tasklet2()).listener(executionContextPromotionListener()).build();
    }

    @Bean
    public ExecutionContextPromotionListener executionContextPromotionListener() {
        ExecutionContextPromotionListener executionContextPromotionListener = new ExecutionContextPromotionListener();
        executionContextPromotionListener.setKeys(new String[]{"filePath","hashId"});
        return executionContextPromotionListener;
    }


}
