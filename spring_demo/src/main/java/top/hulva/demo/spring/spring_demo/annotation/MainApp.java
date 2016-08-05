package top.hulva.demo.spring.spring_demo.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

	public static void main(String[] args) {
		//ApplicationContext context = new ClassPathXmlApplicationContext("BeansWithAnnotation.xml");
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("BeansWithAnnotation.xml");
		
		/*Student student = (Student) context.getBean("student");

		System.out.println(student.getName());
		System.out.println(student.getAge());*/
		
		/*TextEditor te = (TextEditor) context.getBean("textEditor");
		te.spellCheck();*/
		
		/*Profile profile = (Profile) context.getBean("profile");
		profile.printAge();
		profile.printName();*/
		
		HelloSpring obj = (HelloSpring) context.getBean("helloSpring");
		obj.getMessage();
		context.registerShutdownHook();
	}

}
