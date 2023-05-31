package com.ganjunhao.simpledemo;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @author ganjunhao
 * @date 2023/5/31 11:11
 */
public class Consumer {
    public static void main(String[] args) throws InterruptedException, MQClientException {

        // 通过push模式消费消息，指定消费者组
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("sanyouConsumer");

        // 指定NameServer的地址
        consumer.setNamesrvAddr("172.28.75.72:9876");

        // 订阅这个topic下的所有的消息
        consumer.subscribe("sanyouTopic", "*");

        // 注册一个消费的监听器，当有消息的时候，会回调这个监听器来消费消息
        consumer.registerMessageListener(new MessageListenerConcurrently() {

            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                            ConsumeConcurrentlyContext context) {
                for (MessageExt msg : msgs) {
                    System.out.printf("消费消息:%s", new String(msg.getBody()) + "\n");
                }

                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        // 启动消费者
        consumer.start();

        System.out.printf("Consumer Started.%n");
    }
}
