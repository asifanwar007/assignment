import java.util.Iterator;
import java.io.*;


public class LinkedListIterator<T> implements Iterator<Position<T>> 
{
	public Position<T> current;
	
	
	public LinkedListIterator(Position<T> head) 
	{
		current = head;
	}
	public boolean hasNext()
	{
		return current.after() != null;
	}
	// public Position<T> get_current(){
	// 	return current;
	// }
	public Position<T> next(){
		current = current.after();
		return current;
		

	}


}