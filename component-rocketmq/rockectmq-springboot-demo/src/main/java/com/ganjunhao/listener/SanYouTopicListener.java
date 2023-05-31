package com.ganjunhao.listener;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author ganjunhao
 * @date 2023/5/31 11:37
 */
@Component
@RocketMQMessageListener(consumerGroup = "sanyouConsumer", topic = "sanyouSpringbootTopic")
public class SanYouTopicListener implements RocketMQListener<String> {

    @Override
    public void onMessage(String msg) {
        System.out.println("处理消息:" + msg);
    }

}
