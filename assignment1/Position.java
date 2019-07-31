import java.util.Iterator;
import java.io.*;

public class Position<T> implements Position_<T>{
	public T v;
	public Position<T> n;
	public Position(T v, Position<T> n){
		this.v = v;
		this.n = n;
	}
	public T value(){
		return v;
	}
	public Position<T> after(){
		return n;
	}
	// public static void main(String[] args) {
	// 	Position<Integer> t = new Position<Integer>(1, null);
	// 	System.out.println(t.value());
	// 	Position<Integer> k = new Position<Integer>(2, t);
	// 	System.out.println(k.value());
	// }
}