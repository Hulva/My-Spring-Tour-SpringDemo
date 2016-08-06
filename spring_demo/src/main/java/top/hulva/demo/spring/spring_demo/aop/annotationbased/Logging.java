package top.hulva.demo.spring.spring_demo.aop.annotationbased;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Logging {

	@Pointcut("execution(* top.hulva.demo.spring.spring_demo.aop.annotationbased.*.*(..))")
	private void selectAll(){}
	
	@Before("selectAll()")
	public void beforeAdvice() {
		System.out.println("Going to setup student profile.");
	}
	
	@After("selectAll()")
	public void afterAdvice() {
		System.out.println("Student profile has been setup.");
	}
	
	@AfterReturning(pointcut="selectAll()", returning="returnVal")
	public void afterReturningAdvice(Object returnVal) {
		System.out.println("Returning: " + returnVal.toString());
	}
	
	@AfterThrowing(pointcut="selectAll()", throwing="e")
	public void AfterThrowingAdvice(IllegalArgumentException e) {
		System.out.println("There has been an exception: " + e.toString());
	}
}
