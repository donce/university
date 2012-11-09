package rental_system;

public enum WheelSide {
	LEFT("Left"),
	RIGHT("Right");

	private String title;
	
	WheelSide(String title) {
		this.title = title;
	}

	public String toString() {
		return title;
	}
};
