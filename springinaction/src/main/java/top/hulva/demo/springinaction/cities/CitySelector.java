package top.hulva.demo.springinaction.cities;

public class CitySelector {
	private String city;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public void chooseCity() {
		System.out.println(this.city);
	}
	
}
