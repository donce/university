import java.text.DateFormat;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;


class OrdersFrame extends JPanel {
	OrdersFrame(List<Order> orders) {
		super();
		Object[][] data = new Object[orders.size()][5];
		for (int i = 0; i < orders.size(); ++i) {
			Order order = orders.get(i);
			DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
			
			data[i][0] = order.getCar().getTitle();
			data[i][1] = df.format(order.getDate());
			data[i][2] = order.getDays();
			data[i][3] = order.getCustomer();
			data[i][4] = order.getPrice();
		}
		this.add(new JTable(data, new Object[] {"Car", "Date", "Days", "Customer", "Price"}));
	}
}