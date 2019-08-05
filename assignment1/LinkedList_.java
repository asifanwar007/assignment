import java.util.Iterator;
import java.io.*;

public interface LinkedList_<T> {
	public Position<T> add(T e);
	public Iterator<Position<T>> positions();
	public int count();
}