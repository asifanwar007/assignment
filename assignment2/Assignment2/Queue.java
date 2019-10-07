// This class implements the Queue
public class Queue<V> implements QueueInterface<V>{

    //TODO Complete the Queue implementation
    private NodeBase<V>[] queue;
    private int capacity, currentSize, front, rear;
    
	
    public Queue(int capacity) {    
        this.capacity = capacity;
        this.queue = new NodeBase[capacity];
        this.currentSize = 0;
        this.front = 0;
        this.rear = 0;

        // final NodeBase<v>[] queue = (NodeBase<V>[]) Array.newInstance()
    }

    public int size() {
        return currentSize;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }
	
    public boolean isFull() {
        return capacity == currentSize;
    }

    public void enqueue(Node<V> node) {
        if(!this.isFull()){
            queue[rear%capacity] = node;
            rear++;
            currentSize++;
        }

    }
    public NodeBase<V> dequeue() {
        if(!this.isEmpty()){
            NodeBase<V> temp  = queue[front%capacity];
            front++;
            currentSize--;
            return temp;
        } else{
            return null;
        }
    }

}

