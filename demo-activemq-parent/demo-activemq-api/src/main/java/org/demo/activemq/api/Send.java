package org.demo.activemq.api;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * Hello world!
 *
 */
public class Send 
{
	public static void main( String[] args ) throws JMSException
	{
		// 第一步：建立ConnectionFactory 工厂对象
		System.out.println("start...");
		ConnectionFactory connectionFactory=new ActiveMQConnectionFactory(
				ActiveMQConnectionFactory.DEFAULT_USER,
				ActiveMQConnectionFactory.DEFAULT_PASSWORD,
				"tcp://localhost:61616"
				);
		//第二步：通过connectionFactory 工厂对象创建Connection 链接，默认是关闭的需要start方法开启链接
		Connection connection=connectionFactory.createConnection();
		connection.start();
		//第三步：通过connection对象创建session 会话，用于消息的发送接收 ，1参数为是否启用事物，2参数为消息接收方式
		//Session.AUTO_ACKNOWLEDGE,Session.CLIENT_ACKNOWLEDGE
		//Session session=connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
		//使用事物的方式创建session将Boolean.FALSE 改成 Boolean.TRUE
//		Session session=connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
//		使用手工签收方式 CLIENT_ACKNOWLEDGE
		Session session=connection.createSession(Boolean.TRUE, Session.CLIENT_ACKNOWLEDGE);
		//第四步：通过session对象创建Destination对象 指定队列名称创建目标对象
		Destination destination=session.createQueue("queue1");
		//第五步：通过session对象创建消息的发送和接收对象MessageConsumer ,
		MessageProducer messageConsumer=session.createProducer(destination);
		//第六步：设置消息的持久化特性  NON_PERSISTENT不持久化，PERSISTENT持久化
		messageConsumer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		//第七步：创建消息 TextMessage
	
		//第八步：发送
		for (int i = 0; i < 10; i++) {
			TextMessage textMessage=session.createTextMessage(); 
	    	textMessage.setText("我是消息"+i);
			messageConsumer.send(textMessage);
			
			messageConsumer.send(textMessage, //消息
					DeliveryMode.NON_PERSISTENT,//不持久化
					1,  //优先级（0-9 ， 0-4 普通消费， 5-9 加急消费 默认4）
					1000*10 //过期时间
					);
			System.out.println("发送了的消息是："+textMessage.getText());
		}
		//使用事物方式提交
		session.commit();
		
		if(connection!=null){
			connection.close();
		}
	}
}
