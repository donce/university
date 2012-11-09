package rental_system;


public class FilterData {
	private int minSeats;
	private WheelSide wheelSide;
	private Transmission transmission;
	private int maxPrice;

	public FilterData() {
		this(-1, (WheelSide)null, (Transmission)null, -1);
	}

	public FilterData(int minSeats, WheelSide wheelSide, Transmission transmission, int maxPrice) {
		this.minSeats = minSeats;
		this.wheelSide = wheelSide;
		this.transmission = transmission;
		this.maxPrice = maxPrice;
	}
	
	public int getMinSeats() {
		return minSeats;
	}
	public WheelSide getWheelSide() {
		return wheelSide;
	}
	public Transmission getTransmission() {
		return transmission;
	}
	public int getMaxPrice() {
		return maxPrice;
	}
}