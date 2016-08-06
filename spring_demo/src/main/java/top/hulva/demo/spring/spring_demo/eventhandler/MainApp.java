package top.hulva.demo.spring.spring_demo.eventhandler;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("Beans-eventCase.xml");
		
		// 启动一个开始事件
		context.start();
		
		HelloSpring obj = (HelloSpring) context.getBean("helloSpring");
		
		obj.getMessage();
		
		// 开启一个停止事件
		context.stop();
	}

}
