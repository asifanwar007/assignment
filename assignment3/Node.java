public class Node<K, T>{
	public K s;
	public T v;
	public Node<K, T> left_node;
	public Node<K, T> right_node;
	public Node(K s, T v, Node<K, T> left_node, Node<K, T> right_node){
		this.v = v;
		this.left_node = left_node;
		this.right_node = right_node;
		this.s = s;
	}
	public T value(){
		return this.v;
	}
	public K key_value(){
		return this.s;
	}
	public Node<K, T> rightAfter(){
		return this.right_node;
	}
	public Node<K, T> leftAfter(){
		return this.left_node;
	}

}