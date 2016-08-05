package top.hulva.demo.spring.spring_demo;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class HelloSpring implements InitializingBean, DisposableBean {

	private String message;

	public void getMessage() {
		System.out.println("Your message: " + this.message);
	}

	public void setMessage(String message) {
		this.message = message;
	}

	// 与在Xml文件中的bean节点内定义 init-method 方法是一个效果
	public void afterPropertiesSet() throws Exception {
		this.message = "This is 'afterPropertiesSet' method.";
	}
	
	// 在Beans.xml 文件中bean节点中添加 init-method="init"
	public void init() {
		this.message = "This is 'afterPropertiesSet' method.";
	}

	// 与在Xml文件中的bean节点内定义 destroy-method 方法是一个效果
	public void destroy() throws Exception {
		System.out.println("Go dead!");
	}
	
	/*public void destroy() {
		System.out.println("Go dead!");
	}*/
}
