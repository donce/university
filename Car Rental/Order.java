import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


class Order {
	private Car car;
	private int days;
	private int price;
	private String customer;
	private Date date;
 	
	public Order(Car car, int days, String customer) {
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

	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public void println() {
		Locale locale = Locale.getDefault();
		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, locale);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, days);
		System.out.println(String.format("Order \"%s\" by %s, %d days for $%.2f [from %s to %s]",
		car.getTitle(), customer, days, (float)price / 100, df.format(date), df.format(c.getTime())));
		
	}
}