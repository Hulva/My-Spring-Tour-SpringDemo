package top.hulva.demo.spring.spring_demo1;

public class HelloSpring {
	private String message;

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
