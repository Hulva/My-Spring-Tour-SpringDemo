package top.hulva.demo.springinaction.cities;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/META-INF/city/Beans.xml");
		
		CitySelector cs = (CitySelector) applicationContext.getBean("citySelector");
		cs.chooseCity();
		
		Performer performer = (Performer) applicationContext.getBean("performer");
		performer.perform();
	}

}
