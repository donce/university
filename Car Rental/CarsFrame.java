import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;


class CarsFrame extends JPanel {
	private List<Car> cars;
	private JPanel panelCars = new JPanel();
	private FilterData filterData = new FilterData();
	
	public CarsFrame() {
		super(new FlowLayout(FlowLayout.LEADING));
		JPanel filterPanel = new FilterFrame();
		add(filterPanel, BorderLayout.LINE_START);//TODO: top vertical align for filterPanel
		add(panelCars);
	}

	public void setFilter(FilterData filterData) {
		this.filterData = filterData;
		update();
	}
	
	public void update() {
		panelCars.removeAll();
		cars = Main.getCars(filterData);
		for (int i = 0; i < cars.size(); ++i)
			panelCars.add(new CarFrame(cars.get(i)));
		if (cars.size() == 0)
			panelCars.add(new JLabel("No cars found."));
		updateUI();
	}
	
}