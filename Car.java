
class Car {
	enum Transmission {MANUAL, AUTOMATIC};
	enum WheelSide {LEFT, RIGHT};
	
	private String title;
	private String color;
	private int seats;
	private WheelSide wheelSide;
	private Transmission transmission;
	private int price;
	
	private final int wheels = 4;
	

	public Car() {
		this("Golf", "black", 5, WheelSide.LEFT, Transmission.MANUAL, 5000);
	}
	
	public Car(String title, String color, int seats, WheelSide wheelSide, Transmission transmission, int price) {
		setTitle(title);
		setColor(color);
		setSeats(seats);
		setWheelSide(wheelSide);
		setTransmission(transmission);
		setPrice(price);
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	public int getSeats() {
		return seats;
	}
	public void setSeats(int seats) {
		this.seats = seats;
	}
	
	public WheelSide getWheelSide() {
		return wheelSide;
	}
	public void setWheelSide(WheelSide wheelSide) {
		this.wheelSide = wheelSide;
	}
	
	public Transmission getTransmission() {
		return transmission;
	}
	public void setTransmission(Transmission transmission) {
		this.transmission = transmission;
	}
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getPriceString() {
		return "$" + String.format("%.2f", (float)price / 100);
	}

	public int getWheels() {
		return wheels;
	}
	
	public void println() {
		System.out.println(String.format(
				"\"%s\", color: %s, seats: %d, wheel on: %s, transmission: %s, price: %s per day.",
				title, color, seats, (wheelSide == WheelSide.LEFT ? "left" : "right"),
				(transmission == Transmission.AUTOMATIC ? "automatic" : "manual"), getPriceString()
		));
	}
}