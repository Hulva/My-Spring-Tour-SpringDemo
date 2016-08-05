package top.hulva.demo.spring.spring_demo.autowire;

import top.hulva.demo.spring.spring_demo.dependencyinjection.construtorbased.SpellChecker;

public class TextEditor {
	private SpellChecker spellChecker;
	private String name;
	
	public TextEditor() {}
	
	public TextEditor(SpellChecker _spellChecker, String _name) {
		this.name = _name;
		this.spellChecker = _spellChecker;
	}
	
	public SpellChecker getSpellChecker() {
		return spellChecker;
	}
	public void setSpellChecker(SpellChecker _spellChecker) {
		this.spellChecker = _spellChecker;
	}
	public String getName() {
		return name;
	}
	public void setName(String _name) {
		this.name = _name;
	}
	
	public void spellCheck() {
		spellChecker.checkSpelling();
	}
}
