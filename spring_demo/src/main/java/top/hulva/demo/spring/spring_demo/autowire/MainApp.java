package top.hulva.demo.spring.spring_demo.autowire;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		
		TextEditor te = (TextEditor) context.getBean("textEditor-autowire");
		
		te.spellCheck();
	}

}
