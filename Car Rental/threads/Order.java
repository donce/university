package threads;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Order {
	private int customer;
	private int days;
	private int car;
	
	public int getCustomer() {
		return customer;
	}
	
	public int getDays() {
		return days;
	}
	
	public int getCar() {
		return car;
	}
}
