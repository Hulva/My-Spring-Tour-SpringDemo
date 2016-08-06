package top.hulva.demo.spring.spring_demo.javabasedconf;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		
//		context.register(HelloSpringConfig.class, TextEditorConfig.class);
		context.register(TextEditorConfig.class); // 在TextEditorConfig中@Import(HelloSpringConfig.class)
		   										// 这样便能从TextEditorConfig中加载HelloSpringConfig
		context.refresh();
		
		HelloSpring obj = context.getBean(HelloSpring.class);
		obj.setMessage("Hey, there!");
		obj.getMessage();
		
		TextEditor editor = context.getBean(TextEditor.class);
		
		editor.spellChecker();
	}

}
