package com.ganjunhao;

import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
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

    @Test
    public void sendMessageInTransaction() throws Exception {
        String topic = "MyBootTopic";
        String msg = "hello transaction spring boot rocketmq";
        String[] tags = new String[]{"TagA", "TagB", "TagC",
                "TagD", "TagE"};
        for (int i = 0; i < 10; i++) {
            //注意该message为org.springframework.messaging.Message
            Message<String> message = MessageBuilder.withPayload(msg).build();
            //topic和tag整合在⼀起，以":"隔开
            String destination = topic + ":" + tags[i % tags.length];
            //第⼀个destination为消息要发到的⽬的地，第⼆个destination为消协携带的业务数据
            TransactionSendResult sendResult = template.sendMessageInTransaction(destination, message, destination);
            System.out.println("已发送事务消息:" + sendResult);
            Thread.sleep(1000);
        }
    }

}
