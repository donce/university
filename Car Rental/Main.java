import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

class OrderListener implements ActionListener {
	private Car car;
	
	public OrderListener(Car car) {
		this.car = car;
	}
	
	public void actionPerformed(ActionEvent e) {
		Main.openCar(car);
	}
}

class Main {
	private static RentalSystem system = new RentalSystem();
	public static CarsFrame carsFrame;
	private static OrdersFrame ordersFrame;
	
	private static JFrame frame = new JFrame("Car rental system");
	
	public static void main(String[] args) {
		Car test0 = new Car("Nissan", "Red", 5, WheelSide.LEFT, Transmission.MANUAL, 10000);
		system.add(test0);
		Car test = new Car("Ford", "Blue", 5, WheelSide.RIGHT, Transmission.MANUAL, 12000);
		system.add(test);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		
		JTabbedPane pane = new JTabbedPane();
		carsFrame = new CarsFrame();
		pane.add("Search", carsFrame);
		ordersFrame = new OrdersFrame(system.getOrders());
		pane.add("Orders", ordersFrame);
		pane.add("Cars", new CarForm());
		frame.add(pane);
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void openCar(Car car) {
		System.out.println("Open " + car.getTitle());
		CarFrame frame = new CarFrame(car);
		frame.setVisible(true);
	}
	
	public static void filter(FilterData filterData) {
		carsFrame.setFilter(filterData);
	}
	
	public static List<Car> getCars(FilterData filterData) {
		return system.getCars(filterData);
	}
	
	public static void order(Car car, int days, String customer) {
		system.order(car, days, customer);
		updateData();
	}

	public static void showInvoice(Order order) {
		new InvoiceFrame(order).setVisible(true);
	}
	
	private static void updateData() {
		ordersFrame.update();
		carsFrame.update();
	}
	
	public static void giveBack(Order order) {
		system.giveBack(order);
		updateData();
	}
	
	public static void add(Car car) {
		system.add(car);
		updateData();
	}
}