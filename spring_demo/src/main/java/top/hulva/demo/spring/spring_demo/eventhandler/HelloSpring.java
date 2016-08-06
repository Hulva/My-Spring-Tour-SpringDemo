package top.hulva.demo.spring.spring_demo.eventhandler;

public class HelloSpring {
	private String message;

	public void getMessage() {
		System.out.println("Your message: " + this.message);
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
