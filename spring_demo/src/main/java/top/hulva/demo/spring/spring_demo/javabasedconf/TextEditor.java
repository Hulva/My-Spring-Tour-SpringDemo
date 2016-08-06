package top.hulva.demo.spring.spring_demo.javabasedconf;

public class TextEditor {
	private SpellChecker spellChecker;
	
	public TextEditor(SpellChecker spellChecker) {
		System.out.println("Inside TextEditor constructor.");
		this.spellChecker = spellChecker;
	}
	
	public void spellChecker() {
		spellChecker.checkSpelling();
	}
}
