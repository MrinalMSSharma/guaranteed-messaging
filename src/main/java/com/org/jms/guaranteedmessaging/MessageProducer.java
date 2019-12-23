package com.org.jms.guaranteedmessaging;

import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

public class MessageProducer {

	public static void main(String[] args) throws NamingException, JMSException {

		InitialContext initialContext = new InitialContext();
		Queue requestQueue = (Queue) initialContext.lookup("queue/myQueue");
		try (ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
				JMSContext context = connectionFactory.createContext(JMSContext.AUTO_ACKNOWLEDGE);
				//JMSContext context = connectionFactory.createContext(JMSContext.SESSION_TRANSACTED)
				) {
			JMSProducer producer = context.createProducer();
			producer.send(requestQueue, "Message 1");
//			context.rollback();
//			context.commit();
		}
	}
}