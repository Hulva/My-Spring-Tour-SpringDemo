package top.hulva.demo.spring.spring_demo.beandefinitioninheritance;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

// bean 的定义继承
public class MainApp {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		
		HelloSpring obj1 = (HelloSpring) context.getBean("helloSpring2");
		obj1.getMessage();
		obj1.getMessage1();
		
		HelloIndia obj2 = (HelloIndia) context.getBean("helloIndia");
		obj2.getMessage(); // 这里打印的内容应该是和 obj1.getMessage() 打印的内容是一样的
		obj2.getMessage1();
		obj2.getMessage2();
	}

}
