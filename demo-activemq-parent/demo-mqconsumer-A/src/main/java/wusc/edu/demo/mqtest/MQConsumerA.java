/**
 *  .
 */
package wusc.edu.demo.mqtest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @描述: ActiveMQ测试启动类  .
 *   
 * @创建时间: 2015-3-17,上午2:25:20 .
 * @版本号: V1.0 .
 */
public class MQConsumerA {
	private static final Log log = LogFactory.getLog(MQConsumerA.class);

	public static void main(String[] args) {
//		try {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
			context.start();
//		} catch (Exception e) {
//			log.error("==>MQ context start error:", e);
//			System.exit(0);
//		}
	}
}
