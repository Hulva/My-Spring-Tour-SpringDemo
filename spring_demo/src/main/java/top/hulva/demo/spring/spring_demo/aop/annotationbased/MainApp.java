package top.hulva.demo.spring.spring_demo.aop.annotationbased;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("Beans-aopAspectj.xml");
		
		Student student = (Student) applicationContext.getBean("student");
		
		student.getName();
		student.getAge();
	}

}
