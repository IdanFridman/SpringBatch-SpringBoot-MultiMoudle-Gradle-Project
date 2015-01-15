package com.play.notification_processor_service.batch.writer;

import com.play.notification_processor_service.common.dto.PushItemDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

/**
 * Created by Ext_IdanF on 11/01/2015.
 */
public class KafkaWriter implements ItemWriter<PushItemDTO> {
    public static final String KAFKA_TOPIC_NAME = "push_notification";
    private static final Logger logger = LoggerFactory.getLogger(KafkaWriter.class);

    // @Autowired
    //KafkaProducer kafkaProducer;

    @Override
    public void write(List<? extends PushItemDTO> items) throws Exception {
        for(PushItemDTO item : items)
        {
            logger.debug("Writing to kafka="+item);
            sendMessageToKafka(items.get(0));
        }
    }
    public void sendMessageToKafka(PushItemDTO pushItemDTO) {
        //   kafkaProducer.send(KAFKA_TOPIC_NAME, pushItemDTO);
    }
}