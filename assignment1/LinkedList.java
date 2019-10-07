import java.util.Iterator;
import java.io.*;

public class LinkedList<T> implements LinkedList_<T>
{
	public Position<T> head;
	public T e;
	public int count = 0;
	public Position<T> add(T e){
		Position<T> node = new Position<T>(e, null);
		// System.out.println(node.value());
		if(head == null){
			head = node;
			count++;
			// System.out.println(head.value() + " inside the if statement");
			return head;
		} 
		else{
			Position<T> temp = head;
			while(temp.after() != null){
				temp = temp.after();
			}
			
			temp.n = node;

			count++;

			// System.out.println(n.value() + " inside else statement");
			return temp;

		}		
	}

	public Iterator<Position<T>> positions()
	{
		return new LinkedListIterator<T>(head);
	}

	// public void show(){
	// 	Position<T> n = head;
	// 	while(n.after() != null){

	// 		System.out.println(n.value());
	// 		n = n.after();
	// 	}
	// 	System.out.println(n.value());

	// }

	public int count(){
		int i = 1;
		Position<T> node = head;
		while(node.after() != null){
			i++;
			node = node.after();
			// System.out.println("inside while statement");
			
		}
		return i;
	}

	
	// public static void main(String[] args) {
	// 	LinkedList<Integer> n = new LinkedList<Integer>();
	// 	n.add(1);
	// 	n.add(2);
	// 	n.add(3);
	// 	n.add(4);
	// 	n.add(5);
	// 	n.add(2123);
	// 	// Position<Integer> check = n.head;
	// 	// while(check!=null){
	// 	// 	System.out.println(check.v);
	// 	// 	check = check.n;
	// 	// }
	// 	// n.show();


	// 	Iterator<Position<Integer>> temp = n.positions();
	// 	Position<Integer> temp1 = n.head;
	// 	// System.out.println(temp.next().value());
	// 	// System.out.println(temp.next().value());
	// 	// System.out.println(temp.next().value());
	// 	// System.out.println(temp.next().value());
	// 	// System.out.println(temp.next().value());
	// 	int totala = 0;
	// 	// System.out.println(totala);
	// 	while(temp.hasNext()){
	// 		totala++;
	// 		System.out.println(temp.hasNext());
	// 		System.out.println(temp.next().value());
	// 		// System.out.println(n.positions().value());

	// 	}	


	// 	System.out.println(" this is end-----" + totala);
	// 	// System.out.println(temp.next().value());

	// 	// System.out.println(temp.hasNext());
	// 	// // System.out.println(temp.value());
	// 	// System.out.println(temp.next().value());
	// 	// System.out.println("---");

	// 	// System.out.println(temp.hasNext());
	// 	// System.out.println(temp.next());
	// 	// System.out.println("---");

	// 	// System.out.println(temp.hasNext());
	// 	// System.out.println(temp.next());
	// 	// System.out.println("---");


	// 	// System.out.println(temp.hasNext());
	// 	// System.out.println(temp.next());
	// 	// System.out.println("---");

	// 	// System.out.println(temp.hasNext());
	// 	// System.out.println(temp.hasNext());
	// 	// System.out.println(temp.hasNext());
	// 	// System.out.println(temp.hasNext());
	// 	// System.out.println(temp.hasNext());
	// 	// System.out.println(temp.hasNext());
	// 	// System.out.println(temp.hasNext());
	// 	// System.out.println(temp.hasNext());
	// 	// System.out.println(temp.hasNext());
	// 	// System.out.println(temp.hasNext());
	// 	// System.out.println(temp.hasNext());
	// 	// System.out.println(temp.hasNext());
	// 	// System.out.println(temp.hasNext());
	// 	// System.out.println(temp.hasNext());


		
	// 	// while(temp.hasNext()){
			
	// 	// 	System.out.println(temp.next());
	// 	// }

	// 	// System.out.println(n.count());
	// 	// n.show();

	// 	// System.out.println(n);

	// }
}

