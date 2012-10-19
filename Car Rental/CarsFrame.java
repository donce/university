import java.awt.BorderLayout;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;


class CarsFrame extends JPanel {
	private List<Car> cars;
	private JPanel panelCars = new JPanel();
	private FilterData filterData = new FilterData();
	public CustomerForm customerForm = new CustomerForm();
	
	public CarsFrame() {
		super(new BorderLayout());
		JPanel leftPanel = new JPanel();
		JPanel filterFrame = new FilterFrame();
		leftPanel.add(filterFrame);//TODO: top vertical align for filterPanel
		leftPanel.add(customerForm);
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		add(leftPanel, BorderLayout.LINE_START);
		add(panelCars);
		update();
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