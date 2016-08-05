package top.hulva.demo.spring.spring_demo.beandefinitioninheritance.beandefinitiontemplate;

public class HelloIndia {
	private String message;
	private String message1;
	private String message2;
	
	public void getMessage() {
		System.out.println("India Message: " + this.message);
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void getMessage1() {
		System.out.println("India Message1: " + this.message1);
	}
	public void setMessage1(String message1) {
		this.message1 = message1;
	}
	public void getMessage2() {
		System.out.println("India Message2: " + this.message2);
	}
	public void setMessage2(String message2) {
		this.message2 = message2;
	}
	
}
