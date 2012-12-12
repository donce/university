package frames;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import forms.CarForm;
import forms.CustomerForm;

import rental_system.Car;
import rental_system.Customer;
import rental_system.FilterData;
import rental_system.Money;
import rental_system.RentalSystemWindow;
import system_additions.RentalSystem3;
import system_additions.RentalSystemWindow3;
import system_additions.SortFrame;


public class CarsFrame extends JPanel {
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
			Customer customer = (Customer)customerForm.inputCustomer.getSelectedItem();
			if (car == null)
				JOptionPane.showMessageDialog(null, "No car is chosen!");
			else if (customer == null)
				JOptionPane.showMessageDialog(null, "No customer is chosen!");
			else
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
	
	private ActionListener cloneListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Car car = getSelectedCar();
			if (car != null)
				try {
					Car newCar = (Car)car.clone();
					boolean finished = false;
					while (!finished) {
						String newId = JOptionPane.showInputDialog("Please enter new identifier", car.getId());
						if (newId == null)
							finished = true;
						else {
							newCar.setId(newId);
							try {
								systemWindow.add(newCar);
								finished = true;
							} catch (IllegalArgumentException e) {
								JOptionPane.showMessageDialog(null, "This identifier is already used!", "ERROR", JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				} catch (CloneNotSupportedException e) {
					System.out.println("Car clone not implemented.");
				}
		}
	};
	
	public CarsFrame(RentalSystemWindow systemWindow) {
		super(new GridBagLayout());
		this.systemWindow = systemWindow;
		
		JPanel filterFrame = new FilterFrame(systemWindow);
		customerForm = new CustomerForm(systemWindow);
		
		JButton buttonOrder = new JButton("Order"),
				buttonAdd = new JButton("Add"),
				buttonRemove = new JButton("Remove"),
				buttonClone = new JButton("Dublicate");
		buttonOrder.addActionListener(orderListener);
		buttonAdd.addActionListener(addListener);
		buttonRemove.addActionListener(removeListener);
		buttonClone.addActionListener(cloneListener);
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Car"));
		panel.add(buttonOrder);
		panel.add(buttonAdd);
		panel.add(buttonRemove);
		panel.add(buttonClone);
		
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weighty = 0.1;
		c.gridx = 0;
		c.anchor = GridBagConstraints.NORTH;
		
		add(filterFrame, c);
		if (systemWindow instanceof RentalSystemWindow3 && systemWindow.system instanceof RentalSystem3)
			add(new SortFrame((RentalSystemWindow3)systemWindow), c);
		add(customerForm, c);
		
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
		Object[][] data = new Object[cars.size()][7];
		Iterator<Car> it = cars.iterator();
		for (int i = 0; it.hasNext(); ++i) {
			Car car = it.next();
			data[i][0] = car.getTitle();
			data[i][1] = car.getWheelSide();
			data[i][2] = car.getTransmission();
			data[i][3] = car.getSeats();
			data[i][4] = car.getColor();
			data[i][5] = car.getId();
			data[i][6] = Money.toString(car.getPrice());
		}
		tableCars = new JTable(data, new Object[] {"Title", "Wheel side", "Transmission", "Seats", "Color", "Identifier", "Price"});
		
		if (tablePane != null)
			remove(tablePane);
		tablePane = new JScrollPane();
		tablePane.setViewportView(tableCars);

		GridBagConstraints c = new GridBagConstraints();
		c.gridy = 0;
		c.gridheight = 4;
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		add(tablePane, c);
		customerForm.update();
	}
}