package top.hulva.demo.spring.spring_demo.eventhandler;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;

public class CStartEventHandler implements ApplicationListener<ContextStartedEvent>{

	public void onApplicationEvent(ContextStartedEvent arg0) {
		System.out.println("ContextStartedEvent Received...");
	}
	
}
