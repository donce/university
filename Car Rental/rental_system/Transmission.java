package rental_system;

public enum Transmission {
	MANUAL("Manual"),
	AUTOMATIC("Automatic");
	
	private String title;
	
	Transmission(String title) {
		this.title = title;
	}
	
	public String toString() {
		return title;
	}
};
