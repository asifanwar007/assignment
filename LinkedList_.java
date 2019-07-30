import java.util.Iterator;
import java.io.*;

public interface LinkedList_<T> {
	public Position_<T> add(T e);
	public Iterator<Position_<T>> positions();
	public int count();
}