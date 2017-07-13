package net.etongbao.vasp.ac.quartz;

import java.io.FileNotFoundException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 启动定时调度
 * @author Fu Wei
 *
 */
public class StartQuartz {

	public static void main(String[] args) throws FileNotFoundException {

		new ClassPathXmlApplicationContext("classpath:quartz-context.xml");
	}
}
