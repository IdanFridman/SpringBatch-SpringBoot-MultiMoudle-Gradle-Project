package com.play.notification_processor_service.batch.jobs;

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
        //  return this.jobs.get("processFileJob").start(processSnidFileStep()).next(logJobStatusToDBTasklet()).build();//.next(pushToKafkaStep()).build();
        return this.jobs.get("processFileJob").start(logJobStatusToDBTasklet()).build();//.next(pushToKafkaStep()).build();

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
                .reader(reader(OVERRIDDEN_BY_EXPRESSION))
                .processor(processor(OVERRIDDEN_BY_EXPRESSION, OVERRIDDEN_BY_EXPRESSION, OVERRIDDEN_BY_EXPRESSION, OVERRIDDEN_BY_EXPRESSION))
                .writer(writer())
                        //    .listener(logProcessListener())
                        //     .faultTolerant()
                        //   .skipLimit(10) //default is set to 0
                        //     .skip(MySQLIntegrityConstraintViolationException.class)
                .build();
    }

    @Bean
    public ItemWriter writer() {
        return new KafkaWriter();
    }

    @Bean
    @Scope(value = "step", proxyMode = ScopedProxyMode.INTERFACES)
    public ItemStreamReader<PushItemDTO> reader(@Value("#{jobParameters[filePath]}") String filePath) {
        FlatFileItemReader<PushItemDTO> itemReader = new FlatFileItemReader<PushItemDTO>();
        itemReader.setLineMapper(lineMapper());
        itemReader.setLinesToSkip(1);
        itemReader.setResource(new FileSystemResource(filePath));
        return itemReader;
    }

   /* @Bean
    public ItemReader<PushItemDTO> reader() {
        FlatFileItemReader<PushItemDTO> reader = new FlatFileItemReader<PushItemDTO>();
        // reader.setLinesToSkip(1);//first line is title definition
        reader.setResource(new ClassPathResource("snids.csv"));
        reader.setLineMapper(lineMapper());
        return reader;
    }*/

    @Bean
    @Scope(value = "step", proxyMode = ScopedProxyMode.INTERFACES)
    public ItemProcessor<PushItemDTO, PushItemDTO> processor(@Value("#{jobParameters[pushMessage]}") String pushMessage,
                                                             @Value("#{jobParameters[jobId]}") String jobId,
                                                             @Value("#{jobParameters[taskId]}") String taskId,
                                                             @Value("#{jobParameters[refId]}") String refId)
    {
        return new PushItemProcessor(pushMessage,jobId,taskId,refId);
    }

    @Bean
    public LineMapper<PushItemDTO> lineMapper() {
        DefaultLineMapper<PushItemDTO> lineMapper = new DefaultLineMapper<PushItemDTO>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(true);
        lineTokenizer.setNames(new String[]{"environment_id", "user_id", "sn_type_id", "game_type_id", "platform_id", "user_sn_id", "udid", "email", "user_first_name", "user_last_name", "ip_country", "invoke_source", "affiliate", "installation_ts", "user_level", "user_balance_coins", "user_experience", "last_login_ts", "is_paying", "count_payments", "first_transaction_ts", "last_transaction_ts", "first_transaction_amount", "last_transaction_amount", "sum_success_tran_amount", "sum_net_amount", "tier_id", "created_ts", "updated_ts", "test_group"
        });
        BeanWrapperFieldSetMapper<PushItemDTO> fieldSetMapper = new BeanWrapperFieldSetMapper<PushItemDTO>();
        fieldSetMapper.setTargetType(PushItemDTO.class);


        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(new PushItemFieldSetMapper());


        return lineMapper;
    }

}
