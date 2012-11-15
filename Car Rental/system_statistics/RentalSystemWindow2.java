package system_statistics;

import rental_system.*;


public class RentalSystemWindow2 extends RentalSystemWindow {
	
	private PieChart rentTimeChart = null;
	private BarChart myBarChart = null;
	
	public RentalSystemWindow2() {
		this(new RentalSystem2());
	}

	public RentalSystemWindow2(Object systemObject) {
		super((RentalSystem)systemObject);
		if (systemObject instanceof RentalSystem2) {
			RentalSystem2 system = (RentalSystem2)systemObject;
			rentTimeChart = new PieChart(system);
			pane.add("Rent duration chart", rentTimeChart);
			myBarChart = new BarChart(system);
			pane.add("Cars chart", myBarChart);
		}
	}
	
	@Override
	protected void updateData() {
		super.updateData();
		if (system instanceof RentalSystem2) {
			rentTimeChart.update();
			myBarChart.update();
		}
	}
}