package rental_system;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Rental system of cars. System has cars and registered customers.
 * Customer can rent a car for some number of days.
 * 
 * @author Donatas Kučinskas <donce.lt@gmail.com>
 *
 */
public class RentalSystem implements Serializable {
	protected List<Car> cars;
	private List<Car> orderedCars;
	private List<Customer> customers;
	private List<Order> orders;
	
	private Map<Customer, Set<Order>> customerOrders = new HashMap<Customer, Set<Order>>();
	private Map<String, Car> carIds = new HashMap<>();
	
	/**
	 * Create empty rental system.
	 */
	public RentalSystem() {
		cars = new ArrayList<Car>();
		orderedCars = new ArrayList<Car>();
		orders = new ArrayList<Order>();
		customers = new ArrayList<Customer>();
	}

	/**
	 * Add car to the system.
	 * 
	 * @param car car to add
	 */
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
	
	/**
	 * Remove car from the system.
	 * 
	 * @param car car to remove
	 */
	public void remove(Car car) {
		if (car == null)
			throw new IllegalArgumentException();
		if (!cars.remove(car) && !orderedCars.remove(car))
			throw new IllegalArgumentException("Car being removed does not exist.");
		carIds.remove(car.getId());
	}
	
	/**
	 * Get car by it's id.
	 * 
	 * @param id id of car the key whose associated value is to be returned
	 * @return Car with specified id, null if no such car in found
	 */
	public Car getCar(String id) {
		return carIds.get(id);
	}
	
	/**
	 * Get all cars in the system
	 * 
	 * @return list of cars in the system
	 */

	public List<Car> getCars() {
		return getCars(new FilterData());
	}
	
	/**
	 * Get all cars in the system whose match the given filter
	 * 
	 * @param filterData filter to be used for choosing cars
	 * @return list of cars in the system matching the given filter
	 */
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
	
	/**
	 * Add customer to the system.
	 * 
	 * @param customer customer to add
	 * @throws InvalidFormDataException if customer information is invalid
	 */
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
	
	/**
	 * Remove customer from the system.
	 * 
	 * @param customer
	 */
	public void remove(Customer customer) {
		if (customer == null || !customers.contains(customer) || !customerOrders.containsKey(customer))
			throw new IllegalArgumentException();
		customers.remove(customer);
		customerOrders.remove(customer);
	}

	/**
	 * Get customer by it's first name and last name.
	 * 
	 * @param firstName Customer first name
	 * @param lastName Customer last name
	 * @return Customer with specified names if it exists in the system, otherwise null
	 */
	public Customer getCustomer(String firstName, String lastName) {
		for (Customer c : customers)
			if (c.getFirstName().equals(firstName) && c.getLastName().equals(lastName))
				return c;
		return null;
	}

	/**
	 * Get all customers in the system
	 * @return list of customers in the system
	 */
	public List<Customer> getCustomers() {
		return customers;
	}

	/**
	 * Order car for customer.
	 * 
	 * @param car car to order
	 * @param days amount of days to order
	 * @param customer customer who orders
	 */
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
	
	/**
	 * Finish order made earlier.
	 * 
	 * @param order order to finish
	 */
	public void finish(Order order) {
		if (order == null)
			throw new IllegalArgumentException();
		orderedCars.remove(order.getCar());
		getCars().add(order.getCar());
		getOrders().remove(order);
	}

	/**
	 * Get all orders in the system
	 * @return list of orders in the system
	 */
	public List<Order> getOrders() {
		return orders;
	}

	/**
	 * Get orders made by some customer.
	 * 
	 * @param customer customer which orders to get
	 * @return list of orders made by customer
	 */
	public Set<Order> getOrders(Customer customer) {
		if (customer == null)
			throw new IllegalArgumentException();
		return customerOrders.get(customer);
	}
	
	/**
	 * Generate some predefined customers.
	 */
	public void generateCustomers() {
		String[] firstNames = {"Donatas", "Marius", "Rytis", "Vidmantas", "Karolis"};
		String[] lastNames = {"Pavardenis", "Pavardžius", "Pavardienius", "Pavadžius", "Pavardenis"};
		for (int i = 0; i < Math.min(firstNames.length, lastNames.length); ++i)
			try {
				add(new Customer(firstNames[i], lastNames[i], "Lithuania", "Vilnius", "Naugarduko", 10, 2));
			}
			catch (InvalidFormDataException e) {}
	}

	/**
	 * Print information about system to output.
	 */
	public void println() {
		System.out.println("All cars:");
		for (int i = 0; i < getCars().size(); ++i) {
			getCars().get(i).println();
		}
	}
	
}