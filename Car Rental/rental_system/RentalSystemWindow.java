package rental_system;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import Frame.CarsFrame;
import Frame.OrdersFrame;
import forms.InvoiceFrame;

//import javax.swing.UIManager;
//import javax.swing.UnsupportedLookAndFeelException;


public class RentalSystemWindow extends JFrame {
	public RentalSystem system;
	public CarsFrame carsFrame;
	private OrdersFrame ordersFrame;
	protected JTabbedPane pane;
	
	public RentalSystemWindow() {
		this(new RentalSystem());
	}
	
	public RentalSystemWindow(RentalSystem system) {
		super("Car rental system");
		if (system == null)
			throw new IllegalArgumentException();
		this.system = system;
//		try {
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//		} catch (ClassNotFoundException | InstantiationException
//				| IllegalAccessException | UnsupportedLookAndFeelException e1) {
//			e1.printStackTrace();
//		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pane = new JTabbedPane();
		carsFrame = new CarsFrame(this);
		pane.add("Cars", carsFrame);
		ordersFrame = new OrdersFrame(this);
		pane.add("Orders", ordersFrame);
		add(pane);
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public RentalSystem getSystem() {
		return system;
	}
	
	public void filter(FilterData filterData) {
		carsFrame.setFilter(filterData);
	}
	
	public List<Car> getCars(FilterData filterData) {
		return system.getCars(filterData);
	}
	
	public void order(Car car, int days, Customer customer) {
		system.order(car, days, customer);
		updateData();
	}

	public void showInvoice(Order order) {
		new InvoiceFrame(order, this);
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
	
	public boolean add(Customer customer) {
		try {
			system.add(customer);
		}
		catch (InvalidFormDataException e) {
			JOptionPane.showMessageDialog(null, e.getUserMessage());
			return false;
		}
		updateData();
		return true;
	}
	
}
