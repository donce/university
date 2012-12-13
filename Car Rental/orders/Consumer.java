package orders;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.Timer;

import rental_system.Car;
import rental_system.Customer;
import system_additions.RentalSystemWindow3;

public class Consumer extends Thread {
	private LinkedBlockingQueue<Order> queue;
	private int length;
	private RentalSystemWindow3 window;
	private JProgressBar progressBar;
	private JFrame frame;
	private Timer timer;
	
	public Consumer(RentalSystemWindow3 window, LinkedBlockingQueue<Order> queue, int length) {
		this.window = window;
		this.queue = queue;
		this.length = length;
		progressBar = new JProgressBar(0, length);
		progressBar.setValue(0);
	}
	
	private ActionListener orderListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			int now = progressBar.getValue();
			if (now == length) {
				timer.stop();
				frame.dispose();
				System.out.println("Job finished.");
				return;
			}
			
			progressBar.setValue(progressBar.getValue()+1);
			progressBar.setString(Integer.toString(progressBar.getValue()) + " / " + Integer.toString(length) + " orders");
			try {
				Order order = queue.take();
				System.out.println("Ordering " + order.getCar() + " " + order.getCustomer() + " " + Integer.toString(order.getDays()));
				if (order.getCar() == null || order.getCustomer() == null) {
					System.out.println("Not all data are supplied.");
					return;
				}
				String[] words = order.getCustomer().split(" ");
				if (words.length != 2) {
					System.out.println("First name and last name are needed.");
					return;
				}
				Customer customer = window.system.getCustomer(words[0], words[1]);
				if (customer == null) {
					System.out.println("Customer not found.");
					return;
				}
				
				Car car = window.system.getCar(order.getCar());
				if (car == null) {
					System.out.println("Car not found.");
					return;
				}

				window.order(car, order.getDays(), customer);
			} catch (InterruptedException exception) {
				System.out.println("Consumer was interrupted");
			}
		}
	};
	
	@Override
	public void run() {
		frame = new JFrame();
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		progressBar.setString("0 / " + Integer.toString(length) + " orders");
		frame.add(progressBar);
		frame.pack();
		frame.setVisible(true);

		timer = new Timer(1000, orderListener);
		timer.start();
	}
	
}
