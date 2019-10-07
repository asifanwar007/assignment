class Q{
	int n;
	synchronized int get(){
		System.out.println("Got:" + " " + n);
		return n;
	}
	synchronized void put(int n){
		this.n = n;
		System.out.println("Put: " + n);
	}
}

class Producer implements Runnable{
	Q q;
	Thread t;
	Producer(Q q){
		this.q = q;
		t = new Thread(this);
		t.start();
	}
	@Override
	public void run(){
		int i = 0;
		while(i < 10){
			q.put(i++);
		}
	}
}

class Consumer implements Runnable{
	Q q;
	Thread t;
	Consumer(Q q){
		this.q = q;
		t = new Thread(this);
		t.start();
	}
	@Override
	public void run(){
		int i = 0;
		i++;
		while(i < 10){
			q.get();
		}
	}
}

public class Demonstration_7{
	public static void main(String[] args) throws InterruptedException {
		Q q = new Q();
		new Producer(q);
		new Consumer(q);
	}
}