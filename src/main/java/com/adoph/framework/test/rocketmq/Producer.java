package com.adoph.framework.test.rocketmq;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Producer
 *
 * @author Adoph
 * @version v1.0
 * @since 2018/3/30
 */
public class Producer {

    public static void main(String[] args) throws Exception {
//        normalSend();
        orderSend();
    }

    private static void orderSend() throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
        //Instantiate with a producer group name.
        DefaultMQProducer producer = new
                DefaultMQProducer("tdd_producer");
        producer.setNamesrvAddr("10.28.14.184:9876");//名称服务器地址和端口
//        producer.setRetryTimesWhenSendFailed(3);//发送失败重试次数

        //Launch the instance.
        producer.start();
        String[] tags = new String[]{"TagA", "TagB", "TagC", "TagD", "TagE"};
        for (int i = 0; i < 20; i++) {
//            int orderId = i % 10;
            //Create a message instance, specifying topic, tag and message body.
            Message msg = new Message("TopicTestjjj", tags[i % tags.length], "KEY" + i,
                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            System.out.println(String.format("tags:%s,content:Hello RocketMQ %d", tags[i % tags.length], i));
            SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                    Integer id = (Integer) arg;
                    int index = id % mqs.size();
                    return mqs.get(index);
                }
            }, i);

//            System.out.printf("%s%n", sendResult);
        }
//        int i = 3;
//        Message msg = new Message("TopicTestjjj", tags[i], "KEY" + i,
//                ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
//        System.out.println(String.format("tags:%s,content:Hello RocketMQ %d", tags[i], i));
//        SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
//            @Override
//            public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
//                Integer id = (Integer) arg;
//                int index = id % mqs.size();
//                return mqs.get(index);
//            }
//        }, i);
//
//        int j = 0;
//        Message msg1 = new Message("TopicTestjjj", tags[j], "KEY" + j,
//                ("Hello RocketMQ " + j).getBytes(RemotingHelper.DEFAULT_CHARSET));
//        System.out.println(String.format("tags:%s,content:Hello RocketMQ %d", tags[j], j));
//        SendResult sendResult1 = producer.send(msg1, new MessageQueueSelector() {
//            @Override
//            public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
//                Integer id = (Integer) arg;
//                int index = id % mqs.size();
//                return mqs.get(index);
//            }
//        }, j);
        //server shutdown
        producer.shutdown();
    }

    /**
     * 无序发送消息
     *
     * @throws MQClientException
     * @throws UnsupportedEncodingException
     * @throws RemotingException
     * @throws InterruptedException
     * @throws MQBrokerException
     */
    private static void normalSend() throws MQClientException, UnsupportedEncodingException, RemotingException,
            InterruptedException, MQBrokerException {
        //Instantiate with a producer group name.
        DefaultMQProducer producer = new
                DefaultMQProducer("tdd_producer");
        producer.setNamesrvAddr("10.28.14.184:9876");//名称服务器地址和端口
//        producer.setRetryTimesWhenSendFailed(3);//发送失败重试次数

        //Launch the instance.
        producer.start();
        for (int i = 0; i < 5; i++) {
            //Create a message instance, specifying topic, tag and message body.
            Message msg = new Message("TopicTest" /* Topic */,
                    "TagA" /* Tag */,
                    ("ORDER " + i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            //Call send message to deliver message to one of brokers.
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n", sendResult);
        }
        //Shut down once the producer instance is not longer in use.
        producer.shutdown();
    }

}
