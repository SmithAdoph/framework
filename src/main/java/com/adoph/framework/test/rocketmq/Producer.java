package com.adoph.framework.test.rocketmq;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Producer:
 * 1.Sync 同步，一般用于比较重要的消息通知
 * 2.Async 异步，一般用于对响应时间特别敏感的业务场景
 * 3.Oneway 单项传输，中等可靠性传输，如：日志记录
 *
 * @author Adoph
 * @version v1.0
 * @date 2018/3/30
 */
public class Producer {

    public static void main(String[] args) {
        try {
//            normalSend();
//            orderSend();
//            syncMsg();
//            asyncMsg();
//            scheduleMsg();
//            batchMsg();
            clusterTest();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 集群测试
     *
     * @throws Exception
     */
    private static void clusterTest() throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("tdd_cluster_producer");
        producer.setNamesrvAddr("10.28.14.184:9876;10.28.14.74:9876");//名称服务器地址和端口
        producer.start();
        Message msg = new Message("cluster_topic", "tagA", "TEST CONTENT".getBytes());
        producer.send(msg);
    }

    /**
     * 批量发送消息
     *
     * @throws Exception
     */
    private static void batchMsg() throws Exception {
        // Instantiate a producer to send scheduled messages
        DefaultMQProducer producer = new DefaultMQProducer("tdd_schedule_producer");
        producer.setNamesrvAddr("10.28.14.184:9876");//名称服务器地址和端口
        // Launch producer
        producer.start();
        String topic = "BatchTest";
        List<Message> messages = new ArrayList<>();
        messages.add(new Message(topic, "TagA", "OrderID001", "Hello world 0".getBytes()));
        messages.add(new Message(topic, "TagA", "OrderID002", "Hello world 1".getBytes()));
        messages.add(new Message(topic, "TagA", "OrderID003", "Hello world 2".getBytes()));
        producer.send(messages);
    }

    /**
     * 消息发送后，存储一段时间再推送给消费者
     *
     * @throws Exception
     */
    private static void scheduleMsg() throws Exception {
        // Instantiate a producer to send scheduled messages
        DefaultMQProducer producer = new DefaultMQProducer("tdd_schedule_producer");
        producer.setNamesrvAddr("10.28.14.184:9876");//名称服务器地址和端口
        // Launch producer
        producer.start();
        int totalMessagesToSend = 5;
        for (int i = 0; i < totalMessagesToSend; i++) {
            Message message = new Message("TestTopic", ("Hello scheduled message " + i).getBytes());
            // This message will be delivered to consumer 10 seconds later.
            message.setDelayTimeLevel(3);
            // Send the message
            producer.send(message);
        }

        // Shutdown producer after use.
        producer.shutdown();
    }

    /**
     * 同步消息
     *
     * @throws MQClientException
     * @throws UnsupportedEncodingException
     * @throws RemotingException
     * @throws InterruptedException
     * @throws MQBrokerException
     */
    private static void syncMsg() throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
        //Instantiate with a producer group name.
        DefaultMQProducer producer = new
                DefaultMQProducer("tdd_sync_pro");
        producer.setNamesrvAddr("10.28.14.184:9876");//名称服务器地址和端口
        //Launch the instance.
        producer.start();
        for (int i = 0; i < 2; i++) {
            //Create a message instance, specifying topic, tag and message body.
            Message msg = new Message("TopicBroadcastTest" /* Topic */,
                    "TagA" /* Tag */,
                    ("同步消息 " +
                            i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            //Call send message to deliver message to one of brokers.
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n", sendResult);
        }
        //Shut down once the producer instance is not longer in use.
        producer.shutdown();
    }

    /**
     * 异步消息
     *
     * @throws Exception
     */
    private static void asyncMsg() throws Exception {
        //Instantiate with a producer group name.
        DefaultMQProducer producer = new DefaultMQProducer("tdd_async_producer");
        producer.setNamesrvAddr("10.28.14.184:9876");//名称服务器地址和端口
        //Launch the instance.
        producer.start();
        producer.setRetryTimesWhenSendAsyncFailed(0);
        for (int i = 0; i < 1; i++) {
            final int index = i;
            //Create a message instance, specifying topic, tag and message body.
            Message msg = new Message("TopicAsyncTest",
                    "TagA",
                    "OrderID188",
                    "Hello world".getBytes(RemotingHelper.DEFAULT_CHARSET));
            producer.send(msg, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.printf("%-10d OK %s %n", index,
                            sendResult.getMsgId());
                }

                @Override
                public void onException(Throwable e) {
                    System.out.printf("%-10d Exception %s %n", index, e);
                    e.printStackTrace();
                }
            });
        }
        //Shut down once the producer instance is not longer in use.
        producer.shutdown();
    }

    /**
     * 单向传输
     *
     * @throws Exception
     */
    private static void oneWayMsg() throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("ExampleProducerGroup");
        producer.setNamesrvAddr("10.28.14.184:9876");//名称服务器地址和端口
        //Launch the instance.
        producer.start();
        for (int i = 0; i < 5; i++) {
            Message msg = new Message("TopicTest" /* Topic */,
                    "TagA" /* Tag */,
                    ("Hello RocketMQ " +
                            i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            producer.sendOneway(msg);
        }
        producer.shutdown();
    }

    private static void orderSend() throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
        //Instantiate with a producer group name.
        DefaultMQProducer producer = new
                DefaultMQProducer("tdd_sync_producer");
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
