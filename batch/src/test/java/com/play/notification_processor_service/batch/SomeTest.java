package com.play.notification_processor_service.batch;

/**
 * Created by xorg on 1/22/15.
 */

import com.play.notification_processor_service.common.config.CommonConfiguration;
import org.junit.Test;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Configuration;

import javax.inject.Inject;

@Configuration
@SpringApplicationConfiguration(classes= CommonConfiguration.class)
public class SomeTest {


    @Test
    public void someTest()
    {
        System.out.println("srrrrometest");
    }
}
