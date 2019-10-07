class Receiver{
	void call(String msg){
		System.out.print("[" + msg);
		try{
			Thread.sleep(1000);
			
		} catch(InterruptedException e){
			System.out.println("Interrupted ");
		}
		System.out.print("]");
	}
}

class Caller implements Runnable{
	Thread t;
	String msg;
	Receiver receiver;
	Caller(String msg, Receiver receiver){
		this.msg = msg;
		this.receiver = receiver;
		t = new Thread(this);
		t.start();
	}

	public void run(){
		synchronized(receiver) {receiver.call(msg);}
	}

}

public class Demnostration_6{
	public static void main(String[] args) throws InterruptedException {
		Receiver r = new Receiver();
		Caller c1 = new Caller("Hello" , r);
		Caller c2 = new Caller("synchronized" , r);
		Caller c3 = new Caller("method" , r);
		try{
			c1.t.join();
			c2.t.join();
			c3.t.join();
		} catch(InterruptedException e){System.out.println("Interrupted in main: ");}
		System.out.println();
	}
}