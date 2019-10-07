// import java.util.Iterator;
// interface Dictionary_<K, V>{
// 	public V get(K key);
// 	public void put(K key, V value);
// 	public V remove(K key);
// 	public Iterator<V> allvalues();
// 	public Iterator<K> allkeys();
// }
public class Dictionary<K, T>{
	K key;
	T value;
	public Dictionary(K key, T value){
		this.key = key;
		this.value = value;
	}
	public K get_key(){
		return this.key;
	}
	public T get_value(){
		return this.value;
	}
}
