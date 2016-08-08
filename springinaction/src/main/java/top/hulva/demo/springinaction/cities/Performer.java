package top.hulva.demo.springinaction.cities;

public class Performer {
	private Instrument instrument;

	public Instrument getInstrument() {
		return instrument;
	}

	public void setInstrument(Instrument instrument) {
		this.instrument = instrument;
	}
	
	public void perform() {
		instrument.play();
	}
}
