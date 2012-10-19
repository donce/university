import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JViewport;
import javax.swing.Scrollable;


class CarsFrame extends JPanel {
	private List<Car> cars;
	private JPanel panelCars = new JPanel(new FlowLayout(FlowLayout.LEFT));
//	private JPanel panelCars = new JPanel(new GridBagLayout());
	private FilterData filterData = new FilterData();
	public CustomerForm customerForm = new CustomerForm();
	
	//TODO: ScrollPane for car list(FlowLayout in JScrollPane?)
	public CarsFrame() {
		super(new BorderLayout());
		JPanel leftPanel = new JPanel();
		JPanel filterFrame = new FilterFrame();
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
		cars = Main.getCars(filterData);
		for (int i = 0; i < cars.size(); ++i) {
//			GridBagConstraints c = new GridBagConstraints();
//			c.gridx = i % 5;
			panelCars.add(new CarFrame(cars.get(i)));//, c);
		}
		if (cars.size() == 0)
			panelCars.add(new JLabel("No cars found."));
		updateUI();
	}
	
}