package com.ganjunhao.normal_message;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @author ganjunhao
 * @date 2023/6/2 14:19
 */
public class SyncProducer {
    public static void main(String[] args) throws Exception {
        // 初始化一个producer并设置Producer group name
        DefaultMQProducer defaultMQProducer = new DefaultMQProducer("normal_sync_message_producer_group");
        // 设置NameServer地址
        defaultMQProducer.setNamesrvAddr("172.25.226.199:9876");
        // 启动producer
        defaultMQProducer.start();
        for (int i = 0; i < 10; i++) {
            // 创建一条消息，并指定topic、tag、body等信息，tag可以理解成标签，对消息进行再归类，RocketMQ可以在消费端对tag进行过滤
            Message msg = new Message("normal_sync_topic" /* Topic */,
                    "normal_sync_tag" /* Tag */,
                    ("Hello normal_sync_rocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );   //（3）
            // 利用producer进行发送，并同步等待发送结果
            SendResult sendResult = defaultMQProducer.send(msg);   //（4）
            System.out.printf("%s%n", sendResult);
            /*
            SendResult [sendStatus=SEND_OK, msgId=7F000001647018B4AAC20850EC970000, offsetMsgId=AC19E2C700002A9F0000000000009B51,
             messageQueue=MessageQueue [topic=normal_sync_topic, brokerName=broker-a, queueId=1], queueOffset=0]
            * */
        }
        // 一旦producer不再使用，关闭producer
        defaultMQProducer.shutdown();
    }
}
