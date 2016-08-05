/**
 * 
 */
package top.hulva.demo.spring.spring_demo.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Profile {
	@Autowired
	@Qualifier("student1")
	private Student student;
	
	public Profile() {
		System.out.println("Inside Profile Constructor.");
	}
	
	public void printName() {
		System.out.println("Name: " + this.student.getName());
	}
	
	public void printAge() {
		System.out.println("Age: " + this.student.getAge());
	}
}
