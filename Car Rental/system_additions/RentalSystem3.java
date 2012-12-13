package system_additions;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Collections;
import java.util.Comparator;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import rental_system.Car;
import system_statistics.RentalSystem2;

/**
 * Rental system with additional ability to load cars from XML file and to sort
 * cars by title and price.
 * 
 * @author Donatas Kučinskas <donce.lt@gmail.com>
 * 
 */
public class RentalSystem3 extends RentalSystem2 {

	/**
	 * Comparator to compare cars by price
	 * 
	 * @author donatas
	 * 
	 */
	class CarPriceComparator implements Comparator<Car> {
		@Override
		public int compare(Car c1, Car c2) {
			int p1 = c1.getPrice(), p2 = c2.getPrice();
			return (p1 > p2 ? 1 : (p1 < p2 ? -1 : 0));
		}
	}

	/**
	 * Add cars from XML file.
	 * 
	 * @param filename
	 *            XML file with cars to load
	 * @return true if loading succeeded
	 */
	public boolean loadCars(String filename) {
		Unmarshaller u;
		try {
			u = JAXBContext.newInstance(Cars.class).createUnmarshaller();
		} catch (JAXBException e) {
			return false;
		}

		Cars cars;
		try {
			cars = (Cars) u.unmarshal(new FileReader(filename));
		} catch (FileNotFoundException | JAXBException e) {
			return false;
		}
		for (Car car : cars.list)
			add(car);
		return true;
	}

	/**
	 * Sort system cars by title
	 */
	public void sortByTitle() {
		Collections.sort(cars);
	}

	/**
	 * Sort system cars by price
	 */
	public void sortByPrice() {
		Collections.sort(cars, new CarPriceComparator());
	}

}
