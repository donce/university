package orders;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class Producer extends Thread {
	
	private LinkedBlockingQueue<Order> queue;
	private List<Order> orders;
	
	public Producer(LinkedBlockingQueue<Order> queue, List<Order> orders) {
		this.queue = queue;
		this.orders = orders;
	}
	
	@Override
	public void run() {
		for (Order order : orders)
			try {
				queue.put(order);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
	
}
