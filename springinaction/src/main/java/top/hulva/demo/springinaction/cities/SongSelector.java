package top.hulva.demo.springinaction.cities;

public class SongSelector {
	private StringBuffer song = new StringBuffer("Hello World,"
			+ "World Peace,"
			+ "I been hope,"
			+ "Halo");

	public String getSong(Double i) {
		return song.toString().split(",")[i.intValue()];
	}

	public void setSong(String song) {
		this.song = this.song.append(","+song);
	}
}
