package com.ganjunhao;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ganjunhao
 * @date 2023/5/31 11:38
 */
@SpringBootTest(classes = RocketMQApplication.class)
@RunWith(SpringRunner.class)
public class RocketMQTest {

    @Autowired
    private RocketMQTemplate template;

    @Test
    public void send() throws InterruptedException {
        template.convertAndSend("sanyouSpringbootTopic", "三友的springboot日记");
        Thread.sleep(60000);
    }

}
