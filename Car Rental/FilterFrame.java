import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;


class FilterFrame extends JPanel {
	private JComboBox<String> inputWheel;
	private JComboBox<String> inputTransmission;
	private JSpinner inputMinSeats;
	private JSpinner inputMaxPrice;
	public FilterFrame() {
		super();
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		super.setLayout(layout);
		
		//TODO: change enum
		this.add(new JLabel("Wheel on"));
		this.add(inputWheel = new JComboBox<String>(new String[] {"Any", "Left", "Right"}));

		c.gridy = 1;
		this.add(new JLabel("Transmission"), c);
		this.add(inputTransmission = new JComboBox<String>(new String[] {"Any", "Manual", "Automatic"}), c);

		c.gridy = 2;
		this.add(new JLabel("Min seats"), c);
		this.add(inputMinSeats = new JSpinner(), c);

		c.gridy = 3;
		this.add(new JLabel("Max price"), c);
		this.add(inputMaxPrice = new JSpinner(), c);

		JButton filterButton = new JButton("Filter");
		filterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int minSeats = (int)inputMinSeats.getValue();
				Car.WheelSide wheelSide = inputWheel.getSelectedIndex() != 0 ? Car.WheelSide.values()[inputWheel.getSelectedIndex()] : null;
				Car.Transmission transmission = inputTransmission.getSelectedIndex() != 0 ? Car.Transmission.values()[inputTransmission.getSelectedIndex()] : null;
				int maxPrice = (int)inputMaxPrice.getValue();
				if (maxPrice == 0)
					maxPrice = -1;
				Main.filter(minSeats, wheelSide, transmission, maxPrice);
			}
		});
		c.gridy = 4;
		c.gridx = 1;
		this.add(filterButton, c);
	}
}
