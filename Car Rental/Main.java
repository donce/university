import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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

class CarPreview extends JPanel {
	public CarPreview(Car car) {
		JLabel label = new JLabel(car.getTitle());
		label.setIcon(new ImageIcon("car.jpg"));
		this.add(label);
		JButton button = new JButton("Order");
		button.addActionListener(new OrderListener(car));
		this.add(button);
	}
}

class CarView extends JPanel {
	public CarView(Car car) {
		this.add(new JLabel("VIEW " + car.getTitle()));
	}
}

class Main {
	private static RentalSystem system = new RentalSystem();
	private static CarsFrame carsFrame;
	private static OrdersFrame ordersFrame;
	
	private static JFrame frame = new JFrame("Car rental");
	
	public static void main(String[] args) {
		Car test0 = new Car("Nissan", "Red", 5, WheelSide.LEFT, Transmission.MANUAL, 10000);
		system.add(test0);
		Car test = new Car("Ford", "Blue", 5, WheelSide.RIGHT, Transmission.MANUAL, 12000);
		system.add(test);
		try {
			system.order(test, 1, "Donatas");
			system.order(test0, 1, "Donatas");
		}
		catch (IllegalArgumentException e) {
			System.out.println("Error while ordering: " + e.getMessage());
		}
//		try {
//		system.giveBack(test);
//		}
//		catch (IllegalArgumentException e) {}
//		system.println();
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		
		
//		loadCarList(system.getCars());
//		frame.add(carsPanel);
		JTabbedPane pane = new JTabbedPane();
		carsFrame = new CarsFrame();
		pane.add("Search", carsFrame);
//		pane.add("TEST", new JLabel("asd"));
//		new OrdersFrame(system.getOrders());
		ordersFrame = new OrdersFrame(system.getOrders());
		pane.add("Orders", ordersFrame);
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
	//TODO: add new car
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
}