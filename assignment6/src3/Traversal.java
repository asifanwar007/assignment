public class Traversal<T>{
	ArrayList<T> t_array;

	public ArrayList<T> RBTreeTraversal(RedBlackNode<String ,T> node){
		t_array = new ArrayList<T>();
		this.RBtreeTraversalHelper(node);
		return t_array;
	}

	public ArrayList<T> RBtreeTraversalHelper(RedBlackNode<String ,T> node){
		if(node == null){
			return null;
		}
		T nodeP1 = node.value_arr.get(0);
		t_array.add(nodeP1);
		RBtreeTraversalHelper(node.ln);
		RBtreeTraversalHelper(node.rn);
		return t_array;
	}
}