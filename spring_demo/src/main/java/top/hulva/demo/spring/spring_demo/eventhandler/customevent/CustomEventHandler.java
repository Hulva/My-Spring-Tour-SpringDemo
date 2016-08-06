package top.hulva.demo.spring.spring_demo.eventhandler.customevent;

import org.springframework.context.ApplicationListener;

public class CustomEventHandler implements ApplicationListener<CustomEvent>{

	public void onApplicationEvent(CustomEvent arg0) {
		System.out.println(arg0.toString());
	}

}
