package system_statistics;

import rental_system.*;

public class RentalSystemWindow2 extends RentalSystemWindow {

	private RentTimeChart rentTimeChart = null;
	private CarsChart carsChart = null;

	public RentalSystemWindow2() {
		this(new RentalSystem2());
	}

	public RentalSystemWindow2(RentalSystem system) {
		super(system);
		if (system instanceof RentalSystem2) {
			rentTimeChart = new RentTimeChart();
			pane.add("Rent duration chart", rentTimeChart);
			carsChart = new CarsChart();
			pane.add("Cars chart", carsChart);
		}
	}

	@Override
	protected void updateData() {
		super.updateData();
		if (system instanceof RentalSystem2 && rentTimeChart != null
				&& carsChart != null) {
			RentalSystem2 system2 = (RentalSystem2) system;
			rentTimeChart.updateData(system2.getRentTimeStatistics());
			carsChart.updateData(system2.getCarStatistics());
		}
	}

}