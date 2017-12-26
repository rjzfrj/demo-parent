package org.demo.activemq.api;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * Hello world!
 *
 */
public class Receiver 
{
    public static void main( String[] args ) throws JMSException
    {
    	// 第一步：建立ConnectionFactory 工厂对象
    	ConnectionFactory connectionFactory=new ActiveMQConnectionFactory(
    			ActiveMQConnectionFactory.DEFAULT_USER,
    			ActiveMQConnectionFactory.DEFAULT_PASSWORD,
    			"tcp://localhost:61616"
    			);
    	//第二步：通过connectionFactory 工厂对象创建Connection 链接，默认是关闭的需要start方法开启链接
    	Connection connection=connectionFactory.createConnection();
    	connection.start();
    	//第三步：通过connection对象创建session 会话，用于消息的发送接收 ，1参数为是否启用事物，2参数为消息接收方式
    	//Session.AUTO_ACKNOWLEDGE （自动方式签收）,Session.CLIENT_ACKNOWLEDGE
//    	Session session=connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
    	//签收改用CLIENT_ACKNOWLEDGE 方式签收
    	Session session=connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
    	//第四步：通过session对象创建Destination对象 指定队列名称创建目标对象
    	Destination destination=session.createQueue("queue1");
    	//第五步：通过session对象创建消息的发送和接收对象MessageConsumer ,
    	MessageConsumer messageConsumer=session.createConsumer(destination);
    	
    	
    	while (true) {
    		TextMessage textMessage=(TextMessage) messageConsumer.receive();//接收消息
    		textMessage.acknowledge(); // 如果使用CLIENT_ACKNOWLEDGE方式签收必须手动 回执下
    		if (textMessage!=null) {
				System.out.println("收到的消息是："+textMessage);
			}else{
				break;
			}
		}
    	if(connection!=null){
    		connection.close();
    	}
    }
}
