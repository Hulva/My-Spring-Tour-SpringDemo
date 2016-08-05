package top.hulva.demo.spring.spring_demo.beanpostprocessor;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

	public static void main(String[] args) {
		
		AbstractApplicationContext abstractApplicationContext = new ClassPathXmlApplicationContext("Beans.xml");
		
		HelloSpring obj = (HelloSpring) abstractApplicationContext.getBean("helloSpring1");
		obj.getMessage();
		abstractApplicationContext.registerShutdownHook();
	}
}
