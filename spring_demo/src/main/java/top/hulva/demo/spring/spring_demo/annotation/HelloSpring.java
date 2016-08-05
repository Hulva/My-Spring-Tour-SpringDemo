package top.hulva.demo.spring.spring_demo.annotation;
import javax.annotation.*;

public class HelloSpring {
	private String message;

	public void getMessage() {
		System.out.println("Your Message: " + this.message);
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	@PostConstruct
	public void init() {
		System.out.println("Bean is going through init.");
	}
	
	@PreDestroy
	public void destroy() {
		System.out.println("Bean is going destroy.");
	}
}
