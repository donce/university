package orders;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Order {
	private String customer;
	private int days;
	private String car;

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getCustomer() {
		return customer;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public int getDays() {
		return days;
	}

	public void setCar(String car) {
		this.car = car;
	}

	public String getCar() {
		return car;
	}
}
