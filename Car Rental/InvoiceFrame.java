import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


class InvoiceFrame extends JFrame {
	private Order order;
	JButton button;
	
	public InvoiceFrame(Order _order) {
		super("Invoice");
		this.order = _order;
		GridBagLayout layout = new GridBagLayout();
		super.setLayout(layout);
		GridBagConstraints ca = new GridBagConstraints();
		ca.gridx = 0;
		ca.anchor = GridBagConstraints.WEST;
		ca.ipadx = 10;
		GridBagConstraints cb = new GridBagConstraints();
		cb.gridx = 1;
		cb.anchor = GridBagConstraints.WEST;

		Date now = new Date();
		long diff = now.getTime() - order.getDate().getTime();
		float payDays = (float)diff / (1000*60*60*24);
		if (payDays < order.getDays())
			payDays = order.getDays();
		int additionalPrice = (int)((payDays - order.getDays()) * order.getCar().getPrice());
		
		add(new JLabel("Car"), ca);
		add(new JLabel(order.getCar().getTitle()), cb);
		add(new JLabel("Base price"), ca);
		add(new JLabel(Money.toString(order.getPrice())), cb);
		add(new JLabel("Price for delay"), ca);
		add(new JLabel(Money.toString(additionalPrice)), cb);
		add(new JLabel("Total price"), ca);
		add(new JLabel(Money.toString(additionalPrice + order.getPrice())), cb);
		button = new JButton("OK");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.giveBack(order);
				dispose();
			}
		});
		add(button, cb);
		pack();
	}
}