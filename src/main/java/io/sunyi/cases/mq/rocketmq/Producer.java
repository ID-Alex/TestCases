package io.sunyi.cases.mq.rocketmq;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.client.producer.SendStatus;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageQueue;
import com.alibaba.rocketmq.remoting.exception.RemotingException;

import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
 * @author sunyi
 *         Created on 15/10/24
 */
public class Producer {


	public static void main(String args[]) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
		DefaultMQProducer mqProducer = new DefaultMQProducer("GROUP_PRODUCER");
		mqProducer.setNamesrvAddr("192.168.1.42:9876");
		mqProducer.setInstanceName("PRODUCER_INSTANCE");

		mqProducer.start();

		Message msg = new Message("TOPIC_1", "A", String.valueOf(System.currentTimeMillis()), "Test Message".getBytes(Charset.forName("UTF-8")));

		SendResult sendResult = mqProducer.send(msg, 1000);


		String msgId = sendResult.getMsgId();
		System.out.println(msgId);

		MessageQueue messageQueue = sendResult.getMessageQueue();
		System.out.println(messageQueue);

		SendStatus sendStatus = sendResult.getSendStatus();
		System.out.println(sendStatus);

		TimeUnit.MILLISECONDS.sleep(100);
		mqProducer.shutdown();
	}

}
