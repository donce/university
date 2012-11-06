package original;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;


class CustomerForm extends JPanel {

	public JSpinner inputDays;
	public JTextField inputCustomer; 
	
	public CustomerForm() {
		super(new GridBagLayout());
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
		inputCustomer = new JTextField(20);
		this.add(inputCustomer, cb);
	}
}