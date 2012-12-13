package rental_system;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Order implements Cloneable, Serializable {
	private Car car;
	private int days;
	private int price;
	private Customer customer;
	private Date date;

	public Order(Car car, int days, Customer customer) {
		setDate(new Date());
		setCar(car);
		setDays(days);
		setPrice(car.getPrice() * days);
		setCustomer(customer);
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		Locale locale = Locale.getDefault();
		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
				DateFormat.MEDIUM, locale);
		Calendar c = Calendar.getInstance();
		c.setTime(getDate());
		c.add(Calendar.DAY_OF_MONTH, days);
		return String.format(
				"Order \"%s\" by %s, %d days for $%.2f [from %s to %s]",
				getCar().getTitle(), getCustomer(), getDays(),
				(float) getPrice() / 100, df.format(getDate()),
				df.format(c.getTime()));
	}

	public void println() {
		System.out.println(toString());
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Order))
			return false;
		Order a = (Order) o;
		return (getCar().equals(a.getCar())) && (getDays() == a.getDays())
				&& (getPrice() == a.getPrice())
				&& (getCustomer().equals(a.getCustomer()))
				&& (getDate().equals(a.getDate()));
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Order o = (Order) super.clone();
		o.setCustomer(customer.clone());
		o.setCar((Car) car.clone());
		o.setDate((Date) date.clone());
		return o;
	}
}