package top.hulva.demo.spring.spring_demo.hellospring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
    	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("Beans.xml");
    	HelloSpring obj = (HelloSpring) applicationContext.getBean("helloSpring");
    	obj.getMessage();
    	
    	HelloSpring obj1 = (HelloSpring) applicationContext.getBean("helloSpring");
    	obj1.setMessage("Drop the beat!");
    	obj1.getMessage();
    	
    	System.out.println(obj);
    	System.out.println(obj1);
    }
}
