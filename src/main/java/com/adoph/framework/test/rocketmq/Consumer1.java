package com.adoph.framework.test.rocketmq;


import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * MessageListenerConcurrently：
 * 消息消费无序
 *
 * @author Adoph
 * @version v1.0
 * @since 2018/3/30
 */
public class Consumer1 {

    public static void main(String[] args) {
//        指定唯一的分组名称初始化Consumer
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("tdd_consumer");
//        指定namesrv的地址和端口
        consumer.setNamesrvAddr("10.28.14.184:9876");

//        CONSUME_FROM_LAST_OFFSET 默认策略，从该队列最尾开始消费，即跳过历史消息
//        CONSUME_FROM_FIRST_OFFSET 从队列最开始开始消费，即历史消息（还储存在broker的）全部消费一遍
//        CONSUME_FROM_TIMESTAMP 从某个时间点开始消费，和setConsumeTimestamp()配合使用，默认是半个小时以前
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);

        try {
//            指定topic和tags,多个tag用"||"分开，如：tagA || tagB
            consumer.subscribe("TopicTest", "TagB");
        } catch (MQClientException e) {
            e.printStackTrace();
        }

//        注册消息接收监听MessageListenerConcurrently
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
//                ConsumeConcurrentlyStatus.CONSUME_SUCCESS 消息成功
//                ConsumeConcurrentlyStatus.RECONSUME_LATER 稍后重新消费
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        try {
//            启动consumer连接实例
            consumer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        System.out.printf("Consumer Started.%n");
    }

}
