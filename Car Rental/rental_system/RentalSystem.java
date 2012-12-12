package rental_system;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;


public class RentalSystem implements Serializable {
	protected List<Car> cars;
	private List<Car> orderedCars;
	private List<Order> orders;
	private List<Customer> customers;
	
	private Map<Customer, Set<Order>> customerOrders = new HashMap<Customer, Set<Order>>();
	//private Set<String> carIds = new HashSet<String>();
	private Map<String, Car> carIds = new HashMap<>();
	
	public RentalSystem() {
		cars = new ArrayList<Car>();
		orderedCars = new ArrayList<Car>();
		orders = new ArrayList<Order>();
		customers = new ArrayList<Customer>();
	}
	
	public Customer getCustomer(String firstName, String lastName) {
		for (Customer c : customers)
			if (c.getFirstName().equals(firstName) && c.getLastName().equals(lastName))
				return c;
		return null;
	}
	
	public Car getCar(String id) {
		return carIds.get(id);
	}
	
	public void add(Car car) {
		if (car == null)
			throw new NullPointerException();
		if (carIds.containsKey(car.getId())) {
			System.out.println("contains");
			throw new IllegalArgumentException();
		}
		cars.add(car);
		carIds.put(car.getId(), car);
	}
	public void remove(Car car) {
		if (car == null)
			throw new IllegalArgumentException();
		if (!cars.remove(car) && !orderedCars.remove(car))
			throw new IllegalArgumentException("Car being removed does not exist.");
		carIds.remove(car.getId());
	}
	public List<Car> getCars() {
		return getCars(new FilterData());
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
	
	public void add(Customer customer) throws InvalidFormDataException {
		if (customer == null)
			throw new IllegalArgumentException();
		
		if (customer.getFirstName().isEmpty())
			throw new InvalidFormDataException("First name", "cannot be empty");
		if (customer.getLastName().isEmpty())
			throw new InvalidFormDataException("Last name", "cannot be empty");
		if (customer.getCountry().isEmpty())
			throw new InvalidFormDataException("Country", "cannot be empty");
		if (customer.getCity().isEmpty())
			throw new InvalidFormDataException("City", "cannot be empty");
		if (customer.getStreet().isEmpty())
			throw new InvalidFormDataException("Street", "cannot be empty");
		if (customer.getNumberA() <= 0)
			throw new InvalidFormDataException("Number", "cannot be negative or zero");
		if (customer.getNumberB() < 0)
			throw new InvalidFormDataException("Number2", "cannot be negative");
		
		customers.add(customer);
		customerOrders.put(customer, new HashSet<Order>());
	}
	public void remove(Customer customer) {
		if (customer == null || !customers.contains(customer) || !customerOrders.containsKey(customer))
			throw new IllegalArgumentException();
		customers.remove(customer);
		customerOrders.remove(customer);
	}
	public List<Customer> getCustomers() {
		return customers;
	}

	public void order(Car car, int days, Customer customer) {
		if (car == null || days <= 0 || customer == null)
			throw new IllegalArgumentException();
		int index = cars.indexOf(car);
		if (index == -1)
			throw new IllegalArgumentException("Car is not available.");
		cars.remove(index);
		orderedCars.add(car);
		Order order = new Order(car, days, customer);
		orders.add(order);
		customerOrders.get(customer).add(order);
	}
	public void giveBack(Order order) {
		if (order == null)
			throw new IllegalArgumentException();
		orderedCars.remove(order.getCar());
		getCars().add(order.getCar());
		getOrders().remove(order);
	}
	public List<Order> getOrders() {
		return orders;
	}
	public Set<Order> getOrders(Customer customer) {
		if (customer == null)
			throw new IllegalArgumentException();
		return customerOrders.get(customer);
	}
	
	public void println() {
		System.out.println("All cars:");
		for (int i = 0; i < getCars().size(); ++i) {
			getCars().get(i).println();
		}
	}

	public void generateCustomers() {
		String[] firstNames = {"Donatas", "Marius", "Rytis", "Vidmantas", "Karolis"};
		String[] lastNames = {"Pavardenis", "Pavardžius", "Pavardienius", "Pavadžius", "Pavardenis"};
		for (int i = 0; i < Math.min(firstNames.length, lastNames.length); ++i)
			try {
				add(new Customer(firstNames[i], lastNames[i], "Lithuania", "Vilnius", "Naugarduko", 10, 2));
			}
			catch (InvalidFormDataException e) {}
	}
	
	public void generateOrders() {
		Random rand = new Random(System.currentTimeMillis());
		for (int i = 0; i < 10; ++i) {
			order(getCars(new FilterData()).get(rand.nextInt(getCars(new FilterData()).size())),
					1+rand.nextInt(14),
					customers.get(rand.nextInt(customers.size())));
		}
		
	}
	
}