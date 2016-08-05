package top.hulva.demo.spring.spring_demo.beandefinitioninheritance;

public class HelloSpring {
	private String message;
	private String message1;
	
	public void getMessage1() {
		System.out.println("Here's Your Message1: " + this.message1);
	}

	public void setMessage1(String message1) {
		this.message1 = message1;
	}

	public void getMessage() {
		System.out.println("Here's Your Message: " + this.message);
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public void init() {
		System.out.println("Bean is going through init.");
	}
	
	public void destroy() {
		System.out.println("Bean will destroy now.");
	}

}
