package system_additions;

import rental_system.RentalSystem;
import system_statistics.RentalSystemWindow2;

public class RentalSystemWindow3 extends RentalSystemWindow2 {
	
	public RentalSystemWindow3() {
		
	}
	
	public RentalSystemWindow3(RentalSystem system) {
		super(system);
		if (system instanceof RentalSystem3) {
			//TODO: additions
		}
	}
	
}
