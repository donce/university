package rental_system;

import java.util.ArrayList;
import java.util.List;

public class RentalSystem {
	private List<Car> cars;
	private List<Car> orderedCars;
	private List<Order> orders;
	private List<Customer> customers;
	
	public RentalSystem() {
		cars = new ArrayList<Car>();
		orderedCars = new ArrayList<Car>();
		orders = new ArrayList<Order>();
		customers = new ArrayList<Customer>();
	}
	
	public void add(Car car) {
		cars.add(car);
	}
	public void remove(Car car) {
		if (!cars.remove(car) && !orderedCars.remove(car))
			throw new IllegalArgumentException("Car being removed does not exist.");
	}
	public List<Car> getCars() {
		return cars;
	}
	
	public void add(Customer customer) {
		customers.add(customer);
	}
	public void remove(Customer customer) {
		customers.remove(customer);
	}
	public List<Customer> getCustomers() {
		return customers;
	}
	

	public List<Car> getCars(FilterData filterData) {
		List<Car> list = new ArrayList<Car>();
		for (int i = 0; i < cars.size(); ++i) {
			Car car = cars.get(i);
			if ((filterData.getMinSeats() == -1 || filterData.getMinSeats() <= car.getSeats()) && (filterData.getWheelSide() == null || filterData.getWheelSide() == car.getWheelSide())
				&& (filterData.getTransmission() == null || filterData.getTransmission() == car.getTransmission())
				&& (filterData.getMaxPrice() == -1 || filterData.getMaxPrice() >= car.getPrice()))
				list.add(car);
		}
		return list;
	}
	
	public List<Order> getOrders() {
		return orders;
	}
	
	public void order(Car car, int days, Customer customer) {
		int index = cars.indexOf(car);
		if (index == -1)
			throw new IllegalArgumentException("Car is not available.");
		cars.remove(index);
		orderedCars.add(car);
		Order order = new Order(car, days, customer);
		orders.add(order);
	}
	
	public void giveBack(Order order) {
		orderedCars.remove(order.getCar());
		cars.add(order.getCar());
		orders.remove(order);
	}
	
	public void println() {
		System.out.println("All cars:");
		for (int i = 0; i < cars.size(); ++i) {
			cars.get(i).println();
		}
	}
}