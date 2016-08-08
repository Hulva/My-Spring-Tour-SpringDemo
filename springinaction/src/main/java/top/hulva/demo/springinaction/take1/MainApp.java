package top.hulva.demo.springinaction.take1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/take1/Beans.xml");
		
		Performer duke = (Performer) context.getBean("duke");
		duke.perform();
		
		Performer cary = (Performer) context.getBean("cary");
		cary.perform();
		
		Performer poeticDuke = (Performer) context.getBean("poeticDuke");
		poeticDuke.perform();
		
		Performer poeticCary = (Performer) context.getBean("poeticCary");
		poeticCary.perform();
		
	}

}
