package system_statistics;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import rental_system.*;

public class RentalSystem2 extends RentalSystem {
	private Map<String, Integer> carTitles = new HashMap<String, Integer>();
	private List<Integer> orderedDays;
	
	public RentalSystem2() {
		System.out.println("create");
		orderedDays = new ArrayList<Integer>();
	}
	
	@Override
	public void order(Car car, int days, Customer customer) {
		super.order(car, days, customer);
		System.out.println(car.getTitle());
		orderedDays.add(days);
		System.out.println("add new");
		System.out.println(orderedDays.size());
	}
	
	public Set<Map.Entry<Integer, Integer>> getRentTimeStatistics() {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		System.out.println("collect");
		System.out.println(orderedDays.size());
		for (int i = 0; i < orderedDays.size(); ++i) {
			Integer now = orderedDays.get(i);
			Integer value = map.get(now);
			if (value == null)
				value = 0;
			map.put(now,  value+1);
		}
		return map.entrySet();
	}
	
	public Set<Map.Entry<String, Integer>> getCarStatistics() {
		return carTitles.entrySet();
	}

	@Override
	public void add(Car car) {
		super.add(car);
		Integer value = carTitles.get(car.getTitle());
		if (value == null)
			value = 0;
		carTitles.put(car.getTitle(), value+1);
	}
	
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
	
}