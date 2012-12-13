package frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import system_additions.RentalSystemWindow3;

public class SortFrame extends JPanel {
	private RentalSystemWindow3 systemWindow;

	private ActionListener titleSortListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			systemWindow.sortByTitle();
		}
	};

	private ActionListener priceSortListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			systemWindow.sortByPrice();
		}
	};

	public SortFrame(RentalSystemWindow3 systemWindow) {
		this.systemWindow = systemWindow;
		setBorder(BorderFactory.createTitledBorder("Sort"));

		JButton titleButton = new JButton("Title"), priceButton = new JButton(
				"Price");
		titleButton.addActionListener(titleSortListener);
		priceButton.addActionListener(priceSortListener);
		this.add(titleButton);
		this.add(priceButton);
	}
}
