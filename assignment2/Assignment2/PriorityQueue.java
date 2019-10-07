
public class PriorityQueue<V> implements QueueInterface<V>{

    private NodeBase<V>[] queue;
    private int capacity, currentSize;
	
    //TODO Complete the Priority Queue implementation
    // You may create other member variables/ methods if required.
    public PriorityQueue(int capacity) {  
        this.capacity = capacity;
        this.currentSize =0;
        this.queue = new NodeBase[capacity];
    }

    public int size() {
        return currentSize;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }
	
    public boolean isFull() {
        return currentSize == capacity;
    }

    public void enqueue(Node<V> node) {
        // System.out.println("currentSize :" + currentSize);
        if(!this.isFull()){
            if (currentSize == 0){
                queue[0] = node;
            }
            int i = 0;
            for(i = currentSize-1; i>=0; i--){
                if(node.getPriority()>queue[i].getPriority()){
                    queue[i+1] = queue[i];
                } else{break;}
            }
            queue[i+1] = node;
            currentSize++;
        }
        
    }

    // In case of priority queue, the dequeue() should 
    // always remove the element with minimum priority value
    public NodeBase<V> dequeue() {
        if(!this.isEmpty()){
            return queue[--currentSize];  
        }else{
            return null;
        }
      
    }
    public void add(int priority, V value){
        Node<V> n1 = new Node<V>(priority, value);
        this.enqueue(n1);
    }

    public void display () {
	if (this.isEmpty()) {
            System.out.println("Queue is empty");
	}
	for(int i=0; i<currentSize; i++) {
            queue[i].show();
	}
    }
}

