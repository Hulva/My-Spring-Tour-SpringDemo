package top.hulva.demo.spring.spring_demo.beanpostprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

// 在 init-method 执行前后可以在以下两个方法内进行一些业务操作
public class InitHelloSpring implements BeanPostProcessor{

	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		System.out.println("BeforeInitialization: " + beanName);
		return bean;
	}

	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		System.out.println("AfterInitialization: " + beanName);
		return bean;
	}

}
