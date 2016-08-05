package top.hulva.demo.spring.spring_demo.dependencyinjection.setterbased;

import top.hulva.demo.spring.spring_demo.dependencyinjection.construtorbased.SpellChecker;

public class TextEditor {
	private SpellChecker spellChecker;

	public SpellChecker getSpellChecker() {
		return spellChecker;
	}

	public void setSpellChecker(SpellChecker spellChecker) {
		System.out.println("== inside setSpellChecker.");
		this.spellChecker = spellChecker;
	}
	
	public void spellCheck() {
		spellChecker.checkSpelling();
	}
	
	
}
