package rental_system;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


class OrdersFrame extends JPanel {
//	private List<Order> orders;
	private Collection<Order> orders;
	private JTable table = new JTable();
	private JComboBox<String> comboBox;
	
	private RentalSystemWindow systemWindow;
	
	private ActionListener buttonListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			int nr = table.getSelectedRow();
			if (nr != -1)
				systemWindow.showInvoice((Order)orders.toArray()[nr]);
		}
	};
	
	private void updateTable() {
		Object[][] data = new Object[orders.size()][6];
		Iterator<Order> it = orders.iterator();
		for (int i = 0; it.hasNext(); ++i) {
			Order order = it.next();
			DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
			
			data[i][0] = order.getCar().getTitle();
			data[i][1] = df.format(order.getDate());
			data[i][2] = order.getDays();
			data[i][3] = order.getCustomer();
			data[i][4] = Money.toString(order.getPrice());
		}
		((DefaultTableModel)table.getModel()).setDataVector(data,
				new Object[] {"Car", "Date", "Days", "Customer", "Price"});
	}
	
	public void update() {
		orders = systemWindow.system.getOrders();
		List<Customer> customers = systemWindow.system.getCustomers();
		String[] stringC = new String[customers.size()+1];
		stringC[0] = "All";
		Iterator<Customer> iter = customers.iterator();
		for (int i = 1; iter.hasNext(); ++i)
			stringC[i] = iter.next().toString();
		
		comboBox.setModel(new JComboBox<String>(stringC).getModel());
//		((DefaultComboBoxModel<String>)comboBox.getModel()).
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				RentalSystem system = systemWindow.system;
				int nr = comboBox.getSelectedIndex();
				if (nr == 0)
					orders = system.getOrders();
				else
					orders = system.getOrders(system.getCustomers().get(nr-1));
				updateTable();
			}
		});
		
		JButton button = new JButton("Return");
		button.addActionListener(buttonListener);
		add(button, BorderLayout.PAGE_END);

		updateTable();
	}
	
	public OrdersFrame(RentalSystemWindow systemWindow) {
		super(new BorderLayout());
		this.systemWindow = systemWindow;
//		this.orders = systemWindow.system.getOrders();
		add(new JScrollPane(table));
		comboBox = new JComboBox<String>();
		add(comboBox, BorderLayout.PAGE_START);
		update();
	}
}