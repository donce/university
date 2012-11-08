package system_statistics;

import rental_system.*;


public class RentalSystemWindow2 extends RentalSystemWindow {
	
	private RentTimeChart rentTimeChart = null;
	private MyBarChart myBarChart = null;
	
	public RentalSystemWindow2() {
		this(new RentalSystem2());
	}

	public RentalSystemWindow2(Object systemObject) {
		super((RentalSystem)systemObject);
		if (systemObject instanceof RentalSystem2) {
			rentTimeChart = new RentTimeChart();
			pane.add("Rent duration chart", rentTimeChart);
			myBarChart = new MyBarChart();
			pane.add("Cars chart", myBarChart);
		}
	}
	
	@Override protected void updateData() {
		super.updateData();
		if (system instanceof RentalSystem2) {
			rentTimeChart.setData(((RentalSystem2) system).getRentTimeStatistics());
			myBarChart.setData(((RentalSystem2) system).getCarStatistics());
		}
	}
}