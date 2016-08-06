package top.hulva.demo.spring.spring_demo.eventhandler;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStoppedEvent;

public class CStopEventHandler implements ApplicationListener<ContextStoppedEvent>{

	public void onApplicationEvent(ContextStoppedEvent arg0) {
		System.out.println("ContextStopedEvent Received...");
	}

}
