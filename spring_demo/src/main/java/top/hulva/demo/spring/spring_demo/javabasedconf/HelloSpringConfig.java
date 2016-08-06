package top.hulva.demo.spring.spring_demo.javabasedconf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloSpringConfig {

	@Bean
	public HelloSpring helloSpring() {
		return new HelloSpring();
	}
}

// 这段代码等同于xml文件中的这段配置：
/*
 * <beans>
 *    <bean id="helloWorld" class="com.tutorialspoint.HelloWorld" />
 * </beans>
 */
