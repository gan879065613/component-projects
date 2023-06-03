package com.ganjunhao.listener;

import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.support.RocketMQUtil;
import org.springframework.messaging.Message;
import org.springframework.messaging.converter.StringMessageConverter;

/**
 * @author ganjunhao
 * @date 2023/6/3 15:41
 */
@RocketMQTransactionListener(rocketMQTemplateBeanName = "rocketMQTemplate")
public class TransactionListener implements RocketMQLocalTransactionListener {
    @Override
    public RocketMQLocalTransactionState
    executeLocalTransaction(Message msg, Object arg) {
        //获得业务参数中的数据
        String destination = (String) arg;
        //使⽤RocketMQUtil将spring的message转换成rocketmq的message
        org.apache.rocketmq.common.message.Message message =
                RocketMQUtil.convertToRocketMessage(new StringMessageConverter(), "utf-8", destination, msg);
        //获得消息中的业务数据tags
        String tags = message.getTags();
        if (StringUtils.contains(tags, "TagA")) {
            //提交本地事务
            System.out.println("TagA提交本地事务");
            return RocketMQLocalTransactionState.COMMIT;
        } else if (StringUtils.contains(tags, "TagB")) {
            //回滚
            System.out.println("TagB回滚本地事务");
            return RocketMQLocalTransactionState.ROLLBACK;
        } else {
            //中间状态
            System.out.println("中间状态");
            return RocketMQLocalTransactionState.UNKNOWN;
        }
    }

    @Override
    public RocketMQLocalTransactionState
    checkLocalTransaction(Message msg) {
        return null;
    }
}
