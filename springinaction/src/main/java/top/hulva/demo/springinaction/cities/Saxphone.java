package top.hulva.demo.springinaction.cities;

public class Saxphone implements Instrument {
	private String song;
	
	public String getSong() {
		return song;
	}

	public void setSong(String song) {
		this.song = song;
	}

	@Override
	public void play() {
		System.out.println("I'm playing "+  this.song +" by using saxphone...");
	}

}
