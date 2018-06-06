package com.adoph.framework.test.rocketmq;


import com.adoph.framework.util.StringUtils;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * MessageListenerOrderly
 *
 * @author Adoph
 * @version v1.0
 * @date 2018/3/30
 */
public class Consumer2 {

    public static void main(String[] args) throws MQClientException {

        /*
         * Instantiate with specified consumer group name.
         */
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("order_consumer");
        consumer.setNamesrvAddr("10.28.14.184:9876");

        /*
         * Specify where to start in case the specified consumer group is a brand new one.
         */
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);

        String[] tags = new String[]{"TagA", "TagB", "TagC", "TagD", "TagE"};

        /*
         * Subscribe one more more topics to consume.
         */
        consumer.subscribe("TopicTestjjj", StringUtils.join(tags, "||"));

        /*
         *  Register callback to execute on arrival of messages fetched from brokers.
         */
        consumer.registerMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {
                System.out.println(String.format("tags:%s,content:%s", list.get(0).getTags(), new String(list.get(0).getBody())));
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });

        /*
         *  Launch the consumer instance.
         */
        consumer.start();
        System.out.printf("Consumer Started.%n");
    }

}