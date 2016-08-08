package top.hulva.demo.springinaction.take1;

public class Stage {
	private Stage() {}
	
	// initialization on demand holder 这是一种创建单例类的技术
	private static class StageSingletonHolder {
		static Stage instance = new Stage();
	}
	
	public static Stage getInstance() {
		return StageSingletonHolder.instance;
	}
	
}
