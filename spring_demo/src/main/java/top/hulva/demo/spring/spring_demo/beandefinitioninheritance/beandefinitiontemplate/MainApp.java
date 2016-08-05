package top.hulva.demo.spring.spring_demo.beandefinitioninheritance.beandefinitiontemplate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

// 定义继承自 beanTemplate
public class MainApp {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		
		HelloIndia obj2 = (HelloIndia) context.getBean("helloIndia-fromTemplate");
		obj2.getMessage();
		obj2.getMessage1();
		obj2.getMessage2();
	}

}
