public class queue_and_pqChecker{
	public static void main(String[] args) {
		Node<Integer> n1 = new Node<Integer>(1, 2);
        Node<Integer> n2 = new Node<Integer>(2, 3);
        Node<Integer> n3 = new Node<Integer>(3, 4);
        Node<Integer> n4 = new Node<Integer>(4, 5);
        Node<Integer> n5 = new Node<Integer>(5, 6);
        Node<Integer> n6 = new Node<Integer>(6, 7);
        Node<Integer> n7 = new Node<Integer>(7, 8);
        Node<Integer> n8 = new Node<Integer>(8, 9);
        Queue<Integer> que = new Queue<Integer>(0);
		// System.out.println(que.dequeue());
		// System.out.println(que.dequeue());

        

        // que.show();
        // System.out.println(que.dequeue());
        que.enqueue(n1);
        que.enqueue(n2);
        que.enqueue(n3);
        que.enqueue(n4);
        System.out.println(que.isFull());
        que.dequeue().show();
        que.dequeue().show();
        que.dequeue().show();
        // System.out.println(que.size());
        // System.out.println(que.isEmpty());
        // System.out.println(que.isFull());
        // System.out.println(que.dequeue());
	}
}