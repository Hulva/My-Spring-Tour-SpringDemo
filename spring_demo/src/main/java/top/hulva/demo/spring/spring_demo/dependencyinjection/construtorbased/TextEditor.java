package top.hulva.demo.spring.spring_demo.dependencyinjection.construtorbased;

public class TextEditor {
	private SpellChecker spellChecker;
	
	public TextEditor(SpellChecker _spellChecker) {
		System.out.println("Inside TextEditor Constructor.");
		this.spellChecker = _spellChecker;
	}
	
	public void spellCheck() {
		spellChecker.checkSpelling();
	}
}
