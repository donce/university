package system_additions;

import rental_system.RentalSystem;
import system_statistics.RentalSystemWindow2;

public class RentalSystemWindow3 extends RentalSystemWindow2 {
	
	public RentalSystemWindow3() {
		this(new RentalSystem3());
	}
	
	public RentalSystemWindow3(RentalSystem system) {
		super(system);
	}

	public void sortByTitle() {
		((RentalSystem3)system).sortByTitle();
		updateData();
	}
	
	public void sortByPrice() {
		((RentalSystem3)system).sortByPrice();
		updateData();
	}
	
}
