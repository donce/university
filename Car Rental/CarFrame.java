import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;


class CarFrame extends JPanel {
	private Car car;

	public Car getCar() {
		return car;
	}
	
	public CarFrame(Car _car) {
//		super("Order");
		this.car = _car;
		GridBagLayout layout = new GridBagLayout();
		super.setLayout(layout);
//		JPanel panel = new JPanel();
//		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
//		panel.add(new JLabel("Ordering"));
		Vector<String> titles = new Vector<String>();
		titles.add("Title");
		titles.add(car.getTitle());
		titles.add("Wheel on");
		titles.add(car.getWheelSide().toString());
		titles.add("Transmission");
		titles.add(car.getTransmission().toString());
		titles.add("Color");
		titles.add(car.getColor());
		titles.add("Price");
		titles.add(car.getPriceString());
		titles.add("Seats");
		titles.add(Integer.toString(car.getSeats()));
		titles.add("Wheels");
		titles.add(Integer.toString(car.getWheels()));
		
		GridBagConstraints ca = new GridBagConstraints();
		ca.anchor = GridBagConstraints.WEST;
		ca.ipadx = 10;
		ca.gridx = 0;
		GridBagConstraints cb = new GridBagConstraints();
		cb.anchor = GridBagConstraints.WEST;
		cb.ipadx = 0;
		cb.gridx = 1;
		
		for (int i = 0; i+1 < titles.size(); i += 2) {
			this.add(new JLabel(titles.elementAt(i)), ca);
			this.add(new JLabel(titles.elementAt(i+1)), cb);
		}

		this.add(new JLabel("Days"), ca);
		JSpinner spinnerDays = new JSpinner();
		spinnerDays.setValue(1);
		this.add(spinnerDays, cb);
		this.add(new JLabel("Customer"), ca);
		JTextField fieldCustomer = new JTextField(20);
		this.add(fieldCustomer, cb);
		
		JButton orderButton = new JButton("Order");
		orderButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.order(car, 5, "Donatas");
//				System.out.println("ORDER");
//				System.out.println(this.);
			}
		});
		this.add(orderButton, cb);
//		this.add(panel);
//		this.setLayout(new BoxL(this, BoxLayout.PAGE_AXIS));)));
	}
}