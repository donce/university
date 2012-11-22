import java.util.List;
import java.util.Random;

import rental_system.Customer;
import rental_system.FilterData;
import rental_system.InvalidFormDataException;
import rental_system.RentalSystem;
import rental_system.RentalSystemWindow;
import system_additions.RentalSystem3;
import system_additions.RentalSystemWindow3;


class Main {
	
	public static void main(String[] args) {
		RentalSystem system = new RentalSystem3("cars.xml");
		generate(system);
		RentalSystemWindow window = new RentalSystemWindow3(system);
		window.setVisible(true);
	}

	private static void generate(RentalSystem system) {
		String[] firstNames = {"Donatas", "Marius", "Rytis", "Vidmantas", "Karolis"};
		String[] lastNames = {"Pavardenis", "Pavardžius", "Pavardienius", "Pavadžius", "Pavardenis"};
		Random rand = new Random(System.currentTimeMillis());
		
		//customers
		for (int i = 0; i < Math.min(firstNames.length, lastNames.length); ++i)
			try {
				system.add(new Customer(firstNames[i], lastNames[i], "Lithuania", "Vilnius", "Naugarduko", 10, 2));
			}
			catch (InvalidFormDataException e) {}
		
		List<Customer> customers = system.getCustomers();
		//orders
		for (int i = 0; i < 10; ++i) {
			system.order(system.getCars(new FilterData()).get(rand.nextInt(system.getCars(new FilterData()).size())),
					1+rand.nextInt(14),
					customers.get(rand.nextInt(customers.size())));
		}
		
	}
	
}