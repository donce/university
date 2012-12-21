package system_statistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import rental_system.*;

/**
 * Rental system with additional ability to collect statistics about cars and
 * orders.
 * 
 * @author Donatas Kučinskas <donce.lt@gmail.com>
 * 
 */
public class RentalSystem2 extends RentalSystem {
	/**
	 * Number of specific car title count in the system
	 */
	private Map<String, Integer> carTitles = new HashMap<String, Integer>();
	/**
	 * List of orders time duration
	 */
	private List<Integer> orderedDays;

	/**
	 * Create empty rental system.
	 */
	public RentalSystem2() {
		orderedDays = new ArrayList<Integer>();
	}

	/**
	 * Add car to the system.
	 * 
	 * @param car
	 *            car to add
	 */
	@Override
	public void add(Car car) {
		super.add(car);
		Integer value = carTitles.get(car.getTitle());
		if (value == null)
			value = 0;
		carTitles.put(car.getTitle(), value + 1);
	}

	/**
	 * Remove car from the system.
	 * 
	 * @param car
	 *            car to remove
	 */
	@Override
	public void remove(Car car) {
		super.remove(car);
		String title = car.getTitle();
		Integer value = carTitles.get(title);
		value--;
		if (value == 0)
			carTitles.remove(title);
		else
			carTitles.put(title, value);
	}

	/**
	 * Order car for customer.
	 * 
	 * @param car
	 *            car to order
	 * @param days
	 *            amount of days to order
	 * @param customer
	 *            customer who orders
	 */
	@Override
	public void order(Car car, int days, Customer customer) {
		super.order(car, days, customer);
		orderedDays.add(days);
	}

	/**
	 * Get statistics about cars.
	 * 
	 * @return set of entries, consisting of car title and frequency it occurred
	 */
	public Set<Map.Entry<String, Integer>> getCarStatistics() {
		return carTitles.entrySet();
	}

	/**
	 * Get statistics about renting duration time.
	 * 
	 * @return set if entries, consisting of duration number in days and
	 *         frequency it occurred
	 */
	public Set<Map.Entry<Integer, Integer>> getRentTimeStatistics() {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < orderedDays.size(); ++i) {
			Integer now = orderedDays.get(i);
			Integer value = map.get(now);
			if (value == null)
				value = 0;
			map.put(now, value + 1);
		}
		return map.entrySet();
	}

}