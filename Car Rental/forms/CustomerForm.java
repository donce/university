package forms;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;

import rental_system.Customer;
import rental_system.RentalSystemWindow;



public class CustomerForm extends JPanel {
	private RentalSystemWindow systemWindow;
	public JSpinner inputDays;
	public JComboBox<Customer> inputCustomer;
	
	public void update() {
		inputCustomer.removeAllItems();
		Iterator<Customer> it = systemWindow.system.getCustomers().iterator();
		while (it.hasNext())
			inputCustomer.addItem(it.next());
	}
	
	private ActionListener registerListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			new RegistrationWindow(systemWindow);
		}
	};
	
	public CustomerForm(RentalSystemWindow systemWindow) {
		super(new GridBagLayout());
		this.systemWindow = systemWindow;
		setBorder(BorderFactory.createTitledBorder("Order information"));
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
		buttonAdd.addActionListener(registerListener);
		cb.gridx = 2;
		cb.gridy = 1;
		this.add(buttonAdd, cb);
	}
}