import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Seller<V> extends SellerBase<V> {

    public Seller (int sleepTime, int catalogSize, Lock lock, Condition full, Condition empty, PriorityQueue<V> catalog, Queue<V> inventory) {
        //TODO Complete the constructor method by initializing the attibutes
        // ...
        this.lock = lock;
        this.full = full;
        this.setSleepTime(sleepTime);
        this.empty = empty;
        this.catalog = catalog;
        this.inventory = inventory;
    }
    
    public void sell() throws InterruptedException {
	try {
        //TODO Complete the try block for produce method
            // ...
        lock.lock();
        while(catalog.isFull()){
            full.await();
        }
        if(inventory.isEmpty()){

        }else{
            NodeBase<V> d1 = inventory.dequeue();
            Node<V> d2 = (Node<V>)d1;
            catalog.enqueue(d2);
            empty.signal();
        }
	} catch(Exception e) {
            e.printStackTrace();
	} finally {
        lock.unlock();
            //TODO Complete this block
	}		
    }
}
