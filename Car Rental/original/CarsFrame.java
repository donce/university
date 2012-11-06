package original;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;


class CarsFrame extends JPanel {
	private List<Car> cars;
	private JPanel panelCars = new JPanel(new FlowLayout(FlowLayout.LEFT));
//	private JPanel panelCars = new JPanel(new GridBagLayout());
	private FilterData filterData = new FilterData();
	private CustomerForm customerForm = new CustomerForm();
	private RentalSystemWindow systemWindow;
	
	//TODO: ScrollPane for car list(FlowLayout in JScrollPane?)
	public CarsFrame(RentalSystemWindow systemWindow) {
		super(new BorderLayout());
		this.systemWindow = systemWindow;
		JPanel leftPanel = new JPanel();
		JPanel filterFrame = new FilterFrame(systemWindow);
		filterFrame.setAlignmentX(Component.LEFT_ALIGNMENT);
		customerForm.setAlignmentX(Component.LEFT_ALIGNMENT);
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		leftPanel.add(filterFrame);//TODO: top vertical align for filterPanel
		leftPanel.add(customerForm);
		add(leftPanel, BorderLayout.LINE_START);
//		JScrollPane pane = new JScrollPane(panelCars, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(panelCars);
//		add(pane);
		update();
	}

	public void setFilter(FilterData filterData) {
		this.filterData = filterData;
		update();
	}
	
	public void update() {
		panelCars.removeAll();
		cars = systemWindow.getCars(filterData);
		for (int i = 0; i < cars.size(); ++i)
			panelCars.add(new CarFrame(cars.get(i), systemWindow));
		if (cars.size() == 0)
			panelCars.add(new JLabel("No cars found."));
		updateUI();
	}
	
	public int getInputDays() {
		return (int)customerForm.inputDays.getValue();
	}
	
	public String getInputCustomer() {
		return customerForm.inputCustomer.getText();
	}
}