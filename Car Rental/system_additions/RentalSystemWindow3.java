package system_additions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import rental_system.RentalSystem;
import system_statistics.RentalSystemWindow2;

public class RentalSystemWindow3 extends RentalSystemWindow2 {
	
	public RentalSystemWindow3() {
		this(new RentalSystem3());
	}
	
	public RentalSystemWindow3(RentalSystem system) {
		super(system);
		if (system instanceof RentalSystem3) {
			JPanel panel = new JPanel();
			JButton buttonXML = new JButton("Add cars from XML");
			buttonXML.addActionListener(actionXML);
			panel.add(buttonXML);
			pane.add(panel, "Actions");
		}
	}
	
	private ActionListener actionXML = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (((RentalSystem3)system).loadCars("cars.xml"))
				JOptionPane.showMessageDialog(null, "Cars were added successfully.");
			else
				JOptionPane.showMessageDialog(null, "Failed to load XML file!");
		}
	};
	
	public void sortByTitle() {
		((RentalSystem3)system).sortByTitle();
		updateData();
	}
	
	public void sortByPrice() {
		((RentalSystem3)system).sortByPrice();
		updateData();
	}
	
}
