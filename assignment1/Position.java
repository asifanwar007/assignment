import java.util.Iterator;
import java.io.*;

public class Position<T> implements Position_<T>{
	public T v;
	public Position<T> n;
	public Position<T>(T v, Position<T> n){
		this.v = v;
		this.n = n;
	}
	public T value(){
		return v;
	}
	public Position<T> after(){
		return n;
	}
}