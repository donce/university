package threads;

import java.util.concurrent.LinkedBlockingQueue;

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
		while (length > 0) {
			try {
				Order order = queue.take();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			length--;
			
//			Car car = order.getCar();
//			Customer customer = order.getCustomer();
			
//			window.order(car, order.getDays(), customer);
		}
	}
	
}
