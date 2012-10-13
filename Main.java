import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


class Main {
	private static RentalSystem system = new RentalSystem();

	public static void main(String[] args) {
		system.add(new Car("Nissan", "Red", 5, Car.WheelSide.LEFT, Car.Transmission.MANUAL, 10000));
		Car test = new Car("Ford", "Blue", 5, Car.WheelSide.RIGHT, Car.Transmission.MANUAL, 12000);
		system.add(test);
//		system.println();
		try {
			system.order(test, 1, "Donatas");
		}
		catch (IllegalArgumentException e) {
			System.out.println("Error while ordering: " + e.getMessage());
		}
//		system.println();
		try {
		system.giveBack(test);
		}
		catch (IllegalArgumentException e) {}
		system.println();
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		
		JFrame frame = new JFrame("Car rental");
//		frame.setLayout(new GridLayout(0, 4));
		
		JPanel carsPanel = new JPanel();
		List<Car> cars = system.getCars();
		for (int k = 0; k < 5; ++k)
			for (int i = 0; i < cars.size(); ++i) {
				Car car = cars.get(i);
				JPanel item = new JPanel();
				item.setName("test");

				JLabel label = new JLabel(car.getTitle());
				item.add(label);
				
				JButton button = new JButton("Order");
				item.add(button);
				
//				item.setBorder(new Border())
				carsPanel.add(item);
			}
		frame.add(carsPanel);

		JPanel filterPanel = new JPanel(new GridLayout(0, 2));
		filterPanel.add(new JLabel("Wheel on"));
		filterPanel.add(new JComboBox<String>());
		
		filterPanel.add(new JLabel("Transmission"));
		filterPanel.add(new JTextField());
		
		filterPanel.add(new JLabel("Min seats"));
		filterPanel.add(new JTextField());
		JTextField field = new JTextField();
		
		filterPanel.add(new JLabel("Max price"));
		filterPanel.add(new JTextField());
		
		filterPanel.add(new JPanel());
		filterPanel.add(new JButton("Filter"));
		frame.add(filterPanel, BorderLayout.LINE_END);
		
		frame.setVisible(true);
	}
}