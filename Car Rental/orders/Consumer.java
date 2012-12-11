package orders;

import java.util.concurrent.LinkedBlockingQueue;

import rental_system.Car;
import rental_system.Customer;
import system_additions.RentalSystemWindow3;

public class Consumer extends Thread {

	private LinkedBlockingQueue<Order> queue;
	private int length;
	private RentalSystemWindow3 window;
	
	public Consumer(RentalSystemWindow3 window, LinkedBlockingQueue<Order> queue, int length) {
		this.window = window;
		this.queue = queue;
		this.length = length;
	}
	
	@Override
	public void run() {
		while (length-- > 0) {
			try {
				Order order = queue.take();
				System.out.println("add");
				System.out.println(order.getCar() + order.getCustomer() + Integer.toString(order.getDays()));
				if (order.getCar() == null || order.getCustomer() == null) {
					System.out.println("Not all data are supplied.");
					continue;
				}
				String[] words = order.getCustomer().split(" ");
				if (words.length != 2) {
					System.out.println("First name and last name are needed.");
					continue;
				}
				Customer customer = window.system.getCustomer(words[0], words[1]);
				if (customer == null) {
					System.out.println("Customer not found.");
					continue;
				}
				
				Car car = window.system.getCar(order.getCar());
				if (car == null) {
					System.out.println("Car not found.");
					continue;
				}
				
				window.order(car, order.getDays(), customer);
			} catch (InterruptedException e) {
				System.out.println("Consumer was interrupted");
			}
		}
	}
	
}
