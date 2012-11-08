package rental_system;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
//import javax.swing.UIManager;
//import javax.swing.UnsupportedLookAndFeelException;


public class RentalSystemWindow extends JFrame {
	protected RentalSystem system;
	public CarsFrame carsFrame;
	private OrdersFrame ordersFrame;
	protected JTabbedPane pane;
	
	public RentalSystemWindow() {
		this(new RentalSystem());
	}
	
	public RentalSystemWindow(RentalSystem system) {
		super("Car rental system");
		this.system = system;
		Car test0 = new Car("Nissan", "Red", 5, WheelSide.LEFT, Transmission.MANUAL, 10000);
		system.add(test0);
		Car test = new Car("Ford", "Blue", 5, WheelSide.RIGHT, Transmission.MANUAL, 12000);
		system.add(test);
//		try {
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//		} catch (ClassNotFoundException | InstantiationException
//				| IllegalAccessException | UnsupportedLookAndFeelException e1) {
//			e1.printStackTrace();
//		}
		
		pane = new JTabbedPane();
		carsFrame = new CarsFrame(this);
		pane.add("Search", carsFrame);
		ordersFrame = new OrdersFrame(system.getOrders(), this);
		pane.add("Orders", ordersFrame);
		pane.add("Cars", new CarForm(this));
		add(pane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	public void filter(FilterData filterData) {
		carsFrame.setFilter(filterData);
	}
	
	public List<Car> getCars(FilterData filterData) {
		return system.getCars(filterData);
	}
	
	public void order(Car car, int days, String customer) {
		system.order(car, days, customer);
		updateData();
	}

	public void showInvoice(Order order) {
		new InvoiceFrame(order, this).setVisible(true);
	}
	
	protected void updateData() {
		ordersFrame.update();
		carsFrame.update();
	}
	
	public void giveBack(Order order) {
		system.giveBack(order);
		updateData();
	}
	
	public void add(Car car) {
		system.add(car);
		updateData();
	}
	
	public void remove(Car car) {
		system.remove(car);
		updateData();
	}
}
