package forms;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import rental_system.Customer;
import rental_system.RentalSystemWindow;

public class RegistrationWindow extends JFrame {
	private RentalSystemWindow systemWindow;

	private JTextField inputFirstName = new JTextField(20);
	private JTextField inputLastName = new JTextField(20);
	private JTextField inputCountry = new JTextField(20);
	private JTextField inputCity = new JTextField(20);
	private JTextField inputStreet = new JTextField(20);
	private JSpinner inputNumberA = new JSpinner();
	private JSpinner inputNumberB = new JSpinner();

	private ActionListener registerListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (systemWindow.add(new Customer(inputFirstName.getText(),
					inputLastName.getText(), inputCountry.getText(), inputCity
							.getText(), inputStreet.getText(),
					(int) inputNumberA.getValue(), (int) inputNumberB
							.getValue())))
				dispose();
		}
	};

	public RegistrationWindow(RentalSystemWindow systemWindow) {
		super("Registration");
		this.systemWindow = systemWindow;

		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints ca = new GridBagConstraints();
		ca.gridx = 0;
		ca.anchor = GridBagConstraints.WEST;
		GridBagConstraints cb = new GridBagConstraints();
		cb.gridx = 1;
		cb.fill = GridBagConstraints.HORIZONTAL;
		cb.anchor = GridBagConstraints.WEST;
		cb.gridwidth = 3;
		super.setLayout(layout);
		add(new JLabel("First name"), ca);
		add(inputFirstName, cb);
		add(new JLabel("Last name"), ca);
		add(inputLastName, cb);

		add(new JLabel("Country"), ca);
		add(inputCountry, cb);
		add(new JLabel("City"), ca);
		add(inputCity, cb);
		add(new JLabel("Street"), ca);
		add(inputStreet, cb);

		add(new JLabel("Number"), ca);
		cb.gridy = 5;
		cb.gridx = -1;
		cb.gridwidth = 1;
		add(inputNumberA, cb);
		add(new JLabel(" - "), cb);
		add(inputNumberB, cb);
		cb.gridwidth = 3;
		cb.gridy = -1;
		cb.gridx = 1;

		JButton buttonCreate = new JButton("Register");
		buttonCreate.addActionListener(registerListener);
		add(buttonCreate, cb);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
