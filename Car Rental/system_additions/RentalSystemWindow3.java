package system_additions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import orders.Consumer;
import orders.Order;
import orders.Orders;
import orders.Producer;

import rental_system.RentalSystem;
import system_statistics.RentalSystemWindow2;

public class RentalSystemWindow3 extends RentalSystemWindow2 {
	private final static String DATA_FILE = "data";

	public RentalSystemWindow3() {
		this(new RentalSystem3());
	}

	public RentalSystemWindow3(RentalSystem system) {
		super(system);
		try {
			loadSystem();
		} catch (FileNotFoundException e) {
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("Error loading system from file.");
			e.printStackTrace();
		}
		if (system instanceof RentalSystem3) {
			addWindowListener(windowListener);

			JPanel panel = new JPanel();

			JButton buttonXML = new JButton("Load cars from XML");
			buttonXML.addActionListener(actionXMLLoad);
			panel.add(buttonXML);

			JButton buttonGenerate = new JButton("Add default customers");
			buttonGenerate.addActionListener(actionGenerateCustomers);
			panel.add(buttonGenerate);

			JButton buttonLoadOrders = new JButton("Load orders");
			buttonLoadOrders.addActionListener(actionLoadOrders);
			panel.add(buttonLoadOrders);

			pane.add(panel, "Actions");
		}
	}

	private ActionListener actionXMLLoad = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				if (((RentalSystem3) system).loadCars("cars.xml")) {
					updateData();
					JOptionPane.showMessageDialog(null,
							"Cars were added successfully.");
				} else
					JOptionPane.showMessageDialog(null,
							"Failed to load XML file!");
			} catch (IllegalArgumentException e) {
				JOptionPane
						.showMessageDialog(null,
								"Not all cars were loaded, because some identifiers were already used!");
			}
		}
	};

	private ActionListener actionGenerateCustomers = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			system.generateCustomers();
			updateData();
			JOptionPane
					.showMessageDialog(null, "Customers added successfully.");
		}
	};

	private ActionListener actionLoadOrders = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			loadOrders();
		}
	};

	public void sortByTitle() {
		((RentalSystem3) system).sortByTitle();
		updateData();
	}

	public void sortByPrice() {
		((RentalSystem3) system).sortByPrice();
		updateData();
	}

	private List<Order> loadOrdersList() {
		Unmarshaller unmarshaller;
		try {
			unmarshaller = JAXBContext.newInstance(Orders.class)
					.createUnmarshaller();
			Orders orders = (Orders) unmarshaller
					.unmarshal(new FileInputStream("orders.xml"));
			return orders.getList();
		} catch (JAXBException | FileNotFoundException e) {
			return null;
		}
	}

	private void loadOrders() {
		LinkedBlockingQueue<Order> queue = new LinkedBlockingQueue<>();

		List<Order> orders = loadOrdersList();
		if (orders == null)
			return;

		Producer producer = new Producer(queue, orders);
		Consumer consumer = new Consumer(this, queue, orders.size());
		producer.run();
		consumer.run();
	}

	private WindowListener windowListener = new WindowAdapter() {

		@Override
		public void windowClosing(WindowEvent arg0) {
			try {
				saveSystem();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Can't save system state!");
			}
		}

	};

	private void saveSystem() throws IOException {
		FileOutputStream fileStream = new FileOutputStream(DATA_FILE);
		ObjectOutputStream stream = new ObjectOutputStream(fileStream);
		stream.writeObject(system);
		stream.close();
	}

	private void loadSystem() throws IOException, ClassNotFoundException {
		FileInputStream fileStream = new FileInputStream(DATA_FILE);
		ObjectInputStream stream = new ObjectInputStream(fileStream);
		system = (RentalSystem) stream.readObject();
		stream.close();
		updateData();
	}
}
