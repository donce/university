import java.util.Random;

import rental_system.Car;
import rental_system.FilterData;
import rental_system.RentalSystem;
import rental_system.RentalSystemWindow;
import rental_system.Transmission;
import rental_system.WheelSide;
import system_statistics.RentalSystem2;
import system_statistics.RentalSystemWindow2;


class Main {
	
	public static void main(String[] args) {
		//TODO: change cars list to table?
		RentalSystem2 system = new RentalSystem2();
		generate(system);
		RentalSystemWindow window = new RentalSystemWindow2(system);
		window.setVisible(true);
	}

	private static void generate(RentalSystem system) {
		String[] cars = {"Golf", "BMW", "Audi", "Opel", "Mazda", "Ferrari"};
		String[] customers = {"Donatas", "Marius", "Rytis", "Vidmantas", "Ugnė", "Karolis"};
		String[] colors = {"Red", "Blue", "Yellow", "Black", "White", "Green"};
		int[] seats = {5, 2};
		Random rand = new Random(System.currentTimeMillis());
		for (int i = 0; i < 20; ++i) {
			system.add(new Car(cars[rand.nextInt(cars.length)],
					colors[rand.nextInt(colors.length)],
					seats[rand.nextInt(seats.length)],
					WheelSide.values()[rand.nextInt(WheelSide.values().length)],
					Transmission.values()[rand.nextInt(Transmission.values().length)],
					5000 + rand.nextInt(10000)));
		}
		for (int i = 0; i < 10; ++i) {
			system.order(system.getCars(new FilterData()).get(rand.nextInt(system.getCars(new FilterData()).size())),
					1+rand.nextInt(14),
					customers[rand.nextInt(customers.length)]);
		}
	}
	
}