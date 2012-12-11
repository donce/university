package Frame;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;

import rental_system.FilterData;
import rental_system.RentalSystemWindow;
import rental_system.Transmission;
import rental_system.WheelSide;


public class FilterFrame extends JPanel {
	private JComboBox<String> inputWheel;
	private JComboBox<String> inputTransmission;
	private JSpinner inputMinSeats;
	private JSpinner inputMaxPrice;
	private RentalSystemWindow systemWindow;
	
	private ActionListener buttonListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			int minSeats = (int)inputMinSeats.getValue();
			WheelSide wheelSide = inputWheel.getSelectedIndex() != 0 ? WheelSide.values()[inputWheel.getSelectedIndex()-1] : null;
			Transmission transmission = inputTransmission.getSelectedIndex() != 0 ? Transmission.values()[inputTransmission.getSelectedIndex()-1] : null;
			int maxPrice = (int)inputMaxPrice.getValue();
			if (maxPrice == 0)
				maxPrice = -1;
			systemWindow.filter(new FilterData(minSeats, wheelSide, transmission, maxPrice));
		}
	};
	
	public FilterFrame(RentalSystemWindow systemWindow) {
		this.systemWindow = systemWindow;
		setBorder(BorderFactory.createTitledBorder("Filter"));
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints ca = new GridBagConstraints();
		ca.gridx = 0;
		ca.anchor = GridBagConstraints.WEST;
		GridBagConstraints cb = new GridBagConstraints();
		cb.gridx = 1;
		cb.fill = GridBagConstraints.HORIZONTAL;
		cb.anchor = GridBagConstraints.WEST;
		super.setLayout(layout);
		
		//TODO: change enum generation from toString()
		this.add(new JLabel("Wheel on"), ca);
		this.add(inputWheel = new JComboBox<String>(new String[] {"Any", "Left", "Right"}), cb);

		this.add(new JLabel("Transmission"), ca);
		this.add(inputTransmission = new JComboBox<String>(new String[] {"Any", "Manual", "Automatic"}), cb);

		this.add(new JLabel("Min seats"), ca);
		this.add(inputMinSeats = new JSpinner(), cb);

		this.add(new JLabel("Max price"), ca);
		this.add(inputMaxPrice = new JSpinner(), cb);

		JButton filterButton = new JButton("Filter");
		filterButton.addActionListener(buttonListener);
		this.add(filterButton, cb);
	}
}
