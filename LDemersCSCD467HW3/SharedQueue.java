import java.util.LinkedList;
import java.util.Queue;

public class SharedQueue {
	
	Queue<String> Q = new LinkedList<String>();
	int MaxSize;
	
	public SharedQueue(int capacity) {
		MaxSize = capacity;
	}
	
	public synchronized void enqueue(String val) {
		if (Q.size() >= MaxSize) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Q.add(val);
		notifyAll();
	}
	
	public synchronized String dequeue() {
		String val;
		
		if (Q.peek() == null) {
			try {
				//this.doKill(true);
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		val = Q.remove();
		notify();
		return val;
	}
}
