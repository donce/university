package system_additions;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.JOptionPane;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import rental_system.Car;
import system_statistics.RentalSystem2;

public class RentalSystem3 extends RentalSystem2 {
	
	public RentalSystem3() {
		this(null);
	}
	
	public RentalSystem3(String carsFile) {
		super();
		if (carsFile != null && !loadCars(carsFile))
			JOptionPane.showMessageDialog(null, "Failed to load XML file!");
				
	}
	
	public boolean loadCars(String filename) {
		Unmarshaller u;
		try {
			u = JAXBContext.newInstance(Car.class).createUnmarshaller();
		} catch (JAXBException e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println("failed create");
			return false;
		}
		Car car;
		try {
			car = (Car)u.unmarshal(new FileReader(filename));
		} catch (FileNotFoundException e) {
			System.out.println("file");
			return false;
		} catch (JAXBException e) {
			System.out.println("load");
			return false;
		}
		car.println();
		return true;
	}

}
