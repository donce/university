package rental_system;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;


class CustomerForm extends JPanel {
	private RentalSystem system;
	public JSpinner inputDays;
	public JComboBox<Customer> inputCustomer;
	
	public void update() {
		inputCustomer.removeAll();
		Iterator<Customer> it = system.getCustomers().iterator();
		while (it.hasNext())
			inputCustomer.addItem(it.next());
	}
	
	public CustomerForm(RentalSystem system) {
		super(new GridBagLayout());
		this.system = system;
		GridBagConstraints ca = new GridBagConstraints();
		ca.anchor = GridBagConstraints.WEST;
		ca.ipadx = 10;
		ca.gridx = 0;
		GridBagConstraints cb = new GridBagConstraints();
		cb.anchor = GridBagConstraints.WEST;
		cb.ipadx = 0;
		cb.gridx = 1;
		
		this.add(new JLabel("Days"), ca);
		inputDays = new JSpinner();
		inputDays.setValue(1);
		this.add(inputDays, cb);
		this.add(new JLabel("Customer"), ca);
		inputCustomer = new JComboBox<Customer>();
		this.add(inputCustomer, cb);
		
		JButton buttonAdd = new JButton("Add");
		buttonAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new RegistrationWindow();
			}
		});
		cb.gridx = 2;
		cb.gridy = 1;
		this.add(buttonAdd, cb);
	}
}