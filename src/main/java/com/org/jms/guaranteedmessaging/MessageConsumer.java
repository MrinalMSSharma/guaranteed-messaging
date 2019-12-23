package com.org.jms.guaranteedmessaging;

import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

public class MessageConsumer {

	public static void main(String[] args) throws NamingException, JMSException {

		InitialContext initialContext = new InitialContext();
		Queue requestQueue = (Queue) initialContext.lookup("queue/myQueue");
		try (ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
				JMSContext context = connectionFactory.createContext(JMSContext.CLIENT_ACKNOWLEDGE);
//				JMSContext context = connectionFactory.createContext(JMSContext.SESSION_TRANSACTED)
				) {
			JMSConsumer consumer = context.createConsumer(requestQueue);
			TextMessage message = (TextMessage) consumer.receive();
			
			System.out.println(message.getText());
//			message.acknowledge();
		}
	}
}