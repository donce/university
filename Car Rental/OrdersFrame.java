import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.util.List;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


class OrdersFrame extends JPanel {
	private List<Order> orders; 
	private JTable table;
	
	public void update() {
		//TODO: change data, not whole component
		this.removeAll();
		Object[][] data = new Object[orders.size()][6];
		for (int i = 0; i < orders.size(); ++i) {
			Order order = orders.get(i);
			DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
			
			data[i][0] = order.getCar().getTitle();
			data[i][1] = df.format(order.getDate());
			data[i][2] = order.getDays();
			data[i][3] = order.getCustomer();
			data[i][4] = Money.toString(order.getPrice());
		}
		table = new JTable(data, new Object[] {"Car", "Date", "Days", "Customer", "Price"});
		this.add(new JScrollPane(table));
		JButton button = new JButton("Return");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.showInvoice(orders.get(table.getSelectedRow()));
			}
		});
		this.add(button);
	}
	
	public OrdersFrame(List<Order> orders) {
		super();
		this.orders = orders;
		update();
	}
}