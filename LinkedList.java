import java.util.Iterator;
import java.io.*;

public class LinkedList<T> extends Position<T> implements LinkedList_<T>{
	public Position<T> head;
	public T e;
	public static int pos = 0;
	
	public Iterator<Position<T>> positions(){
		return new LinkedListIterator<T>(head);
	}
	public int count(){
		int c = 0;
		LinkedListIterator<T> check = head;
		while (true){
			if (check.hasNext()){
				c++;
			} else{
				break
			}
		}
		return c;
		
	}
	public Position<T> add(T e){
		pos++;
		Position<T>(e, pos);
		return pos;
		
	}
}


class LinkedListIterator<T> implements Iterator<Position<T>> {
	public Position<T> current;
	
	
	public LinkedListIterator(Position<T> head) {
		current = head;
	}
	public boolean hasNext(){
		return current.after() != null;
	}
}