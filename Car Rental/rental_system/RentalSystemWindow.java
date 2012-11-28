package rental_system;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
		if (system == null)
			throw new IllegalArgumentException();
		this.system = system;
//		try {
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//		} catch (ClassNotFoundException | InstantiationException
//				| IllegalAccessException | UnsupportedLookAndFeelException e1) {
//			e1.printStackTrace();
//		}

		addWindowListener(windowListener);
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

	private WindowListener windowListener = new WindowListener() {
		
		@Override
		public void windowOpened(WindowEvent arg0) {
			loadSystem();
		}

		@Override
		public void windowClosing(WindowEvent arg0) {
			saveSystem();
		}
		
		@Override
		public void windowIconified(WindowEvent arg0) {}
		@Override
		public void windowDeiconified(WindowEvent arg0) {}
		@Override
		public void windowDeactivated(WindowEvent arg0) {}
		@Override
		public void windowClosed(WindowEvent arg0) {}
		@Override
		public void windowActivated(WindowEvent arg0) {}
	};

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
	
	private void saveSystem() {
		try {
			ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream("data"));
			stream.writeObject(system);
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void loadSystem() {
		try {
			ObjectInputStream stream = new ObjectInputStream(new FileInputStream("data"));
			system = (RentalSystem)stream.readObject();
			stream.close();
		} catch (IOException|ClassNotFoundException e) {
			e.printStackTrace();
		}
		updateData();
	}
}
