import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;


class CarForm extends JPanel {
	private JTextField inputTitle;
	private JTextField inputColor;
	private JComboBox<String> inputWheel;
	private JComboBox<String> inputTransmission;
	private JSpinner inputSeats;
	private JSpinner inputPrice;

	public CarForm() {
		super(new GridBagLayout());
		GridBagConstraints ca = new GridBagConstraints();
		ca.gridx = 0;
		ca.anchor = GridBagConstraints.WEST;
		GridBagConstraints cb = new GridBagConstraints();
		cb.gridx = 1;
		cb.fill = GridBagConstraints.HORIZONTAL;
		cb.anchor = GridBagConstraints.WEST;

		this.add(new JLabel("Title"), ca);
		this.add(inputTitle = new JTextField(), cb);
		
		this.add(new JLabel("Color"), ca);
		this.add(inputColor = new JTextField(), cb);
		
		this.add(new JLabel("Wheel on"), ca);
		this.add(inputWheel = new JComboBox<String>(new String[] {"Left", "Right"}), cb);

		this.add(new JLabel("Transmission"), ca);
		this.add(inputTransmission = new JComboBox<String>(new String[] {"Manual", "Automatic"}), cb);

		this.add(new JLabel("Seats"), ca);
		this.add(inputSeats = new JSpinner(), cb);

		this.add(new JLabel("Price"), ca);
		this.add(inputPrice = new JSpinner(), cb);

		JButton button = new JButton("Add");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Main.add(new Car(inputTitle.getText(), inputColor.getText(), (int)inputSeats.getValue(), WheelSide.values()[inputWheel.getSelectedIndex()], Transmission.values()[inputTransmission.getSelectedIndex()], (int)inputPrice.getValue()));
			}
		});
		this.add(button, cb);
	}
}