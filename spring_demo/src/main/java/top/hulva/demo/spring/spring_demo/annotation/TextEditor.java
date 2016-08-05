/**
 * 
 */
package top.hulva.demo.spring.spring_demo.annotation;

import org.springframework.beans.factory.annotation.Autowired;

import top.hulva.demo.spring.spring_demo.dependencyinjection.construtorbased.SpellChecker;

public class TextEditor {
	@Autowired
	private SpellChecker spellChecker;

	public SpellChecker getSpellChecker() {
		return spellChecker;
	}

	/*@Autowired or @Resource(name= "spellChecker")
	public void setSpellChecker(SpellChecker spellChecker) {
		this.spellChecker = spellChecker;
	}*/
	
	public void spellCheck(){
		spellChecker.checkSpelling();
	}
}
