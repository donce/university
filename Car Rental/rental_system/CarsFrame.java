package rental_system;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;



class CarsFrame extends JPanel {
	private List<Car> cars;
	private JScrollPane tablePane;
	private JTable tableCars;
	private FilterData filterData = new FilterData();
	private CustomerForm customerForm;
	private RentalSystemWindow systemWindow;

	private Car getSelectedCar() {
		int row = tableCars.getSelectedRow();
		return row == -1 ? null : cars.get(row);
	}

	private ActionListener orderListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Car car = getSelectedCar();
			if (car != null)
				systemWindow.order(car, (int)customerForm.inputDays.getValue(),
						(Customer)customerForm.inputCustomer.getSelectedItem());
		}
	};
	
	private ActionListener addListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			new CarForm(systemWindow);
		}
	};

	private ActionListener removeListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Car car = getSelectedCar();
			if (car != null)
				systemWindow.remove(car);
		}
	};
	
	public CarsFrame(RentalSystemWindow systemWindow) {
		super(new GridBagLayout());
		this.systemWindow = systemWindow;
		
		JPanel filterFrame = new FilterFrame(systemWindow);
		customerForm = new CustomerForm(systemWindow);
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.HORIZONTAL;
		add(filterFrame, c);
		c.gridy = 1;
		add(customerForm, c);
		
		JButton buttonOrder = new JButton("Order"), buttonAdd = new JButton("Add"), buttonRemove = new JButton("Remove");
		buttonOrder.addActionListener(orderListener);
		buttonAdd.addActionListener(addListener);
		buttonRemove.addActionListener(removeListener);
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Car"));
		panel.add(buttonOrder);
		panel.add(buttonAdd);
		panel.add(buttonRemove);
		c.gridy = 2;
		c.anchor = GridBagConstraints.SOUTH;
		add(panel, c);
		
		update();
	}

	public void setFilter(FilterData filterData) {
		this.filterData = filterData;
		update();
	}
	
	public void update() {
		cars = systemWindow.getCars(filterData);
		Object[][] data = new Object[cars.size()][6];
		Iterator<Car> it = cars.iterator();
		for (int i = 0; it.hasNext(); ++i) {
			Car car = it.next();
			data[i][0] = car.getTitle();
			data[i][1] = car.getWheelSide();
			data[i][2] = car.getTransmission();
			data[i][3] = car.getSeats();
			data[i][4] = car.getColor();
			data[i][5] = Money.toString(car.getPrice());
		}
		tableCars = new JTable(data, new Object[] {"Title", "Wheel side", "Transmission", "Seats", "Color", "Price"});
		
		if (tablePane != null)
			remove(tablePane);
		tablePane = new JScrollPane();
		tablePane.setViewportView(tableCars);

		GridBagConstraints c = new GridBagConstraints();
		c.gridy = 0;
		c.gridheight = 3;
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		add(tablePane, c);
		customerForm.update();
	}
}