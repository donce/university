package rental_system;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Car implements Comparable<Car>, Cloneable, Serializable {
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
	@XmlAttribute
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getColor() {
		return color;
	}
	@XmlAttribute
	public void setColor(String color) {
		this.color = color;
	}
	
	public int getSeats() {
		return seats;
	}
	@XmlAttribute
	public void setSeats(int seats) {
		this.seats = seats;
	}
	
	public WheelSide getWheelSide() {
		return wheelSide;
	}
	@XmlAttribute(name="wheel_side")
	public void setWheelSide(WheelSide wheelSide) {
		this.wheelSide = wheelSide;
	}
	
	public Transmission getTransmission() {
		return transmission;
	}
	@XmlAttribute
	public void setTransmission(Transmission transmission) {
		this.transmission = transmission;
	}
	
	public int getPrice() {
		return price;
	}
	@XmlAttribute
	public void setPrice(int price) {
		this.price = price;
	}

	public int getWheels() {
		return wheels;
	}
	
	@Override
	public String toString() {
		return String.format(
				"\"%s\", color: %s, seats: %d, wheel on: %s, transmission: %s, price: %s per day.",
				getTitle(), getColor(), getSeats(), getWheelSide().toString(),
				getTransmission().toString(), Money.toString(getPrice())
		);

	}
	
	public void println() {
		System.out.println(toString());
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Car))
			return false;
		Car c = (Car)o;
		return (getTitle().equals(c.getTitle())) && (getColor().equals(c.getColor())) &&
				(getSeats() == c.getSeats()) && (getWheelSide().equals(c.getWheelSide())) &&
				(getTransmission().equals(c.getTransmission())) && (getPrice() == c.getPrice());
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public int compareTo(Car car) {
		return getTitle().compareTo(car.getTitle());
	}
	
}
