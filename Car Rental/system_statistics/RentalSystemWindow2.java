package system_statistics;

import rental_system.*;


public class RentalSystemWindow2 extends RentalSystemWindow {
	
	private PieChart rentTimeChart = null;
	private BarChart myBarChart = null;
	
	public RentalSystemWindow2() {
		this(new RentalSystem2());
	}

	public RentalSystemWindow2(RentalSystem system) {
		super(system);
		if (system instanceof RentalSystem2) {
			rentTimeChart = new PieChart();
			pane.add("Rent duration chart", rentTimeChart);
			myBarChart = new BarChart();
			pane.add("Cars chart", myBarChart);
		}
	}
	
	@Override
	protected void updateData() {
		super.updateData();
		if (system instanceof RentalSystem2 && rentTimeChart != null && myBarChart != null) {
			RentalSystem2 system2 = (RentalSystem2)system;
			rentTimeChart.updateData(system2.getRentTimeStatistics());
			myBarChart.updateData(system2.getCarStatistics());
		}
	}
	
}