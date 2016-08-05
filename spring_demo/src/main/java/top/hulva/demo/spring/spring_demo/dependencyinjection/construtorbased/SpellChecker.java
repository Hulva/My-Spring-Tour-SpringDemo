package top.hulva.demo.spring.spring_demo.dependencyinjection.construtorbased;

public class SpellChecker {
	public SpellChecker() {
		System.out.println("Inside SpellChecker Constructor.");
	}
	
	public void checkSpelling() {
		System.out.println("checkSpelling...");
	}
}
