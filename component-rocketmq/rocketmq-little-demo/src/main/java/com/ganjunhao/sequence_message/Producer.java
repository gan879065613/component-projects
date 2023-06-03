package com.ganjunhao.sequence_message;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.List;

/**
 * @author ganjunhao
 * @date 2023/6/2 15:04
 */
public class Producer {
    public static void main(String[] args) throws Exception {
        try {
            DefaultMQProducer producer = new DefaultMQProducer("sequence_message_producer_group");
            // 设置NameServer地址
            producer.setNamesrvAddr("172.25.226.199:9876");
            producer.start();
            String[] tags = new String[]{"sequence_message_tagA", "sequence_message_tagB", "sequence_message_tagC",
                    "sequence_message_tagD", "sequence_message_tagE"};
            for (int i = 0; i < 100; i++) {
                int orderId = i % 10;
                Message msg =
                        new Message("sequence_message_topic", tags[i % tags.length], "KEY" + i,
                                ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
                SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                    @Override
                    public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                        Integer id = (Integer) arg;
                        int index = id % mqs.size();
                        return mqs.get(index);
                    }
                }, orderId);
                System.out.printf("%s%n", sendResult);
            }
            producer.shutdown();
        } catch (MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
