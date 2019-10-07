class NotFoundException extends Exception {} 

public interface MyHashTable_<K, T>{
	public int insert(K key, T obj);
	public int update(K key, T obj);
	public int delete(K key);
	public boolean contains(K key);
	public T get(K key) throws NotFoundException;
	public String address(K key) throws NotFoundException;
}