package top.hulva.demo.springinaction.take1;

public class Sonnet29 implements Poem {
	private static String[] LIINES = {
		"Hello, it's me",
		"Do what you wanna do",
		"Shake it, Shake it",
		"Trouble Maker"
	};
	
	public Sonnet29() {
	}
	
	public void recite() {
		for (int i = 0; i < LIINES.length; i++) {
			System.out.println(LIINES[i]);
		}
	}

}
