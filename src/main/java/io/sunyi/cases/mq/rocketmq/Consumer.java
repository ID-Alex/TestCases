package io.sunyi.cases.mq.rocketmq;

import com.alibaba.rocketmq.client.consumer.DefaultMQPullConsumer;
import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.MQConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListener;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.impl.consumer.PullMessageService;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.alibaba.rocketmq.common.protocol.heartbeat.MessageModel;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author sunyi
 *         Created on 15/10/26
 */
public class Consumer {

	public static Lock lock = new ReentrantLock();

	public static void main(String args[]) throws MQClientException, IOException {

		DefaultMQPushConsumer mqConsumer = new DefaultMQPushConsumer("GROUP_CONSUMER_1");
//		mqConsumer.setInstanceName("CONSUMER_INSTANCE");
		mqConsumer.setNamesrvAddr("192.168.1.42:9876");
		mqConsumer.setMessageModel(MessageModel.CLUSTERING);
		mqConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

		mqConsumer.subscribe("TOPIC_1", "*");

		mqConsumer.setMessageListener(new MessageListenerConcurrently() {

			                              public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
	          lock.lock();

	          try {

	              System.out.println("in consumeMessage");

	              if (msgs == null) {
	                  return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
	              }

	              for (MessageExt messageExt : msgs) {
		              String msgId = messageExt.getMsgId();
		              String topic = messageExt.getTopic();
	                  String tags = messageExt.getTags();
	                  String keys = messageExt.getKeys();
	                  String body = new String(messageExt.getBody(), Charset.forName("UTF-8"));

		              System.out.println("msgId: " + msgId);
		              System.out.println("topic: " + topic);
	                  System.out.println("tags: " + tags);
	                  System.out.println("keys: " + keys);
		              System.out.println("body: " + body);
		              System.out.println("----------------------------------");

	              }
	              return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
	          } finally {
	              lock.unlock();
	          }
	          }

	      }

		);


		mqConsumer.start();


		System.in.read();

	}


}
