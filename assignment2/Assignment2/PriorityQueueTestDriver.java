// Use this driver for the testing the correctness of your priority queue implementation
// You can change the add, remove sequence to test for various scenarios.
public class PriorityQueueTestDriver {
    public static void main(String[] args) {
	PriorityQueue<String> pq = new PriorityQueue<String>(5);
	System.out.println(pq.isFull());
	pq.add(4, "A");
	// pq.display();
	pq.add(10, "B");
	// pq.dequeue();
	pq.add(3, "C");
	pq.add(5, "E");
	pq.add(2, "F");

	
	//pq.display();
	int size = pq.size();
	System.out.println(size);
	// pq.display();
	System.out.println();
	for (int i=0; i<size; i++) {
            // Node<String> n = (Node<String>) pq.removeMin();
            Node<String> n = (Node<String>) pq.dequeue();
            n.show();
	}
    }
}
