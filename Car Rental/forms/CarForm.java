package forms;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import rental_system.Car;
import rental_system.RentalSystemWindow;
import rental_system.Transmission;
import rental_system.WheelSide;


public class CarForm extends JFrame {
	private JTextField inputId;
	private JTextField inputTitle;
	private JTextField inputColor;
	private JComboBox<String> inputWheel;
	private JComboBox<String> inputTransmission;
	private JSpinner inputSeats;
	private JSpinner inputPrice;
	
	private RentalSystemWindow systemWindow;

	private ActionListener buttonListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String id, title, color;
			id = inputId.getText();
			title = inputTitle.getText();
			color = inputColor.getText();
			if (id.length() == 0 || title.length() == 0)
				JOptionPane.showMessageDialog(null, "Car identifier and title cannot be empty");
			else
				try {
					systemWindow.add(new Car(id, title, color, (int)inputSeats.getValue(), WheelSide.values()[inputWheel.getSelectedIndex()], Transmission.values()[inputTransmission.getSelectedIndex()], (int)inputPrice.getValue()));
					dispose();
				} catch (IllegalArgumentException e) {
					JOptionPane.showMessageDialog(null, "Car with this identifier is already in system.");
				}
		}
	};
	
	public CarForm(RentalSystemWindow systemWindow) {
		super("Add car");
		this.setLayout(new GridBagLayout());
		this.systemWindow = systemWindow;
		GridBagConstraints ca = new GridBagConstraints();
		ca.gridx = 0;
		ca.anchor = GridBagConstraints.WEST;
		GridBagConstraints cb = new GridBagConstraints();
		cb.gridx = 1;
		cb.fill = GridBagConstraints.HORIZONTAL;
		cb.anchor = GridBagConstraints.WEST;

		this.add(new JLabel("Identifier"), ca);
		this.add(inputId = new JTextField(), cb);
		
		this.add(new JLabel("Title"), ca);
		this.add(inputTitle = new JTextField(), cb);
		
		this.add(new JLabel("Color"), ca);
		this.add(inputColor = new JTextField(), cb);
		
		this.add(new JLabel("Wheel on"), ca);
		this.add(inputWheel = new JComboBox<String>(new String[] {"Left", "Right"}), cb);

		this.add(new JLabel("Transmission"), ca);
		this.add(inputTransmission = new JComboBox<String>(new String[] {"Manual", "Automatic"}), cb);

		this.add(new JLabel("Seats"), ca);
		this.add(inputSeats = new JSpinner(), cb);

		this.add(new JLabel("Price"), ca);
		this.add(inputPrice = new JSpinner(), cb);

		JButton button = new JButton("Add");
		button.addActionListener(buttonListener);
		this.add(button, cb);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
}