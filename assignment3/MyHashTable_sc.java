import java.lang.Math;


// class NotFoundException extends Exception{}

//Double hashing approach
public class MyHashTable_sc<K, T> implements MyHashTable_<K, T>{
	int t;//size of array which is given as argument
	Node<K, T>[] bst_root_node_arr;
	BST<K, T> bst;


	public MyHashTable_sc(int t){
		this.t = t;
		this.bst_root_node_arr = new Node[t];
		this.bst = new BST();
		// this.key_arr = new String[t];
	}
	//djb2 h1
	public static long djb2(String str, int hashtableSize) { 
    	long hash = 5381; 
    	for (int i = 0; i < str.length(); i++) { 
        	hash = ((hash << 5) + hash) + str.charAt(i); 
    	} 
    	return Math.abs(hash) % hashtableSize; 
	}

	public int insert(K key, T obj){
		int i = 0;
		String[] sv;
		sv = key.toString().split(" ");
		String key_first = sv[0];
		String key_second = sv[1];
		int h1 = (int) djb2(key_first + key_second, this.t);
		if (bst_root_node_arr[h1] == null){
			Node<K, T> root_node = new Node<K, T>(key , obj, null, null);
			bst_root_node_arr[h1] = root_node;
			i = 1;
		} else{
			Node<K, T> bst_root = bst_root_node_arr[h1];
			Node<K, T> temp_check = bst.add(bst_root, key, obj);
			// bst.inorderRec(bst_root);
			if (temp_check == null){
				return -1;
			}
			i = bst.count_add;
		}
		return i;
	}
	public int update(K key, T obj){
		int i = 0;
		String[] sv = key.toString().split(" ");
		String key_first = sv[0];
		String key_second = sv[1];
		int h1 = (int) djb2(key_first + key_second, this.t);
		if (bst_root_node_arr[h1] == null){
			i = -1;
		} else{
			Node<K, T> bst_root = bst_root_node_arr[h1];
			// if(bst.search(bst_root, key) == null){
			// 	return -1;
			// }
			
			i = bst.updateBst(bst_root, key, obj);
			// System.out.println(check == null);
			// if(check == null){
			// 	return -1;
			// }
			// i = bst.count_update;
		}
		return i;
	}
	public int delete(K key){
		int i = 0;
		//TODO K type here Key then how to get fn and ln
		String[] sv = key.toString().split(" ");
		String key_first = sv[0];
		String key_second = sv[1];
		int h1 = (int) djb2(key_first + key_second, this.t);
		if (bst_root_node_arr[h1] == null){
		// System.out.println(h1 + " delete hash index---------------");
			// Node<K, T> root_node = new Node<K, T>(obj, null, null);
			// bst_root_node_arr[h1] = root_node;
			i = -1;
		} else{
			Node<K, T> bst_root = bst_root_node_arr[h1];
			String[] root_value_key = bst_root.key_value().toString().split(" ");
        	String root_fn = root_value_key[0];
        	String root_ln = root_value_key[1];
			// System.out.println(bst_root.s.toString());

			if(bst.search(bst_root, key) == null){
				return -1;
			}
			// Node<K, T> temp_check = bst.deleteBst(bst_root, key);
			// System.out.println(temp_check == bst_root_node_arr[h1]);
			// System.out.println("delete in sc starts");
			// bst.inorderRec(bst_root_node_arr[h1]);
			// System.out.println();
			bst_root_node_arr[h1]  = bst.deleteBst(bst_root, key);
			// bst.inorderRec(bst_root_node_arr[h1]);
			// System.out.println();
			if(root_fn.equals(key_first) && root_ln.equals(key_second))
			{
				// i = bst.count_del + 1;
				// System.out.println(bst_root_node_arr[h1].s.toString());
				// bst.inorderRec(bst_root_node_arr[h1]);
			}
			else{
				
				
				// System.out.println(bst_root_node_arr[h1].s.toString());
				// bst.inorderRec(bst_root_node_arr[h1]);
			}
			// System.out.println("delete in sc endHere");
			
			i = bst.count_del;
		}
		return i;

	}
	// public Node<K, T> checkf(K key){
	// 	String[] sv = key.toString().split(" ");
	// 	String key_first = sv[0];
	// 	String key_second = sv[1];
	// 	int h1 = (int) djb2(key_first, this.t);
	// 	Node<K, T> bst_root = bst_root_node_arr[h1];
	// 	Node<K, T> temp_check1 = bst.deleteBst(bst_root, key);
	// 	Node<K, T> temp_check = bst.search(bst_root, key);
	// 	return temp_check;
	// }
	public boolean contains(K key){
		int i = 0;
		String[] sv = key.toString().split(" ");
		String key_first = sv[0];
		String key_second = sv[1];
		int h1 = (int) djb2(key_first + key_second, this.t);
		if (bst_root_node_arr[h1] == null){
			// Node<K, T> root_node = new Node<K, T>(obj, null, null);
			// bst_root_node_arr[h1] = root_node;
			i = -1;
			return false;
		} else{
			Node<K, T> bst_root = bst_root_node_arr[h1];
			if(bst.containsBst(bst_root, key)){
				// System.out.println("inside ocntai");
				return true;
			} 
			
		}
				// System.out.println("inside ocntai1");
		return false;


	}
	public T get(K key) throws NotFoundException{
		int i = 0;
		String[] sv = key.toString().split(" ");
		String key_first = sv[0];
		String key_second = sv[1];
		int h1 = (int) djb2(key_first+ key_second, this.t);
		Node<K, T> temp_n = bst_root_node_arr[h1];
		if (bst_root_node_arr[h1] == null){
			// Node<K, T> root_node = new Node<K, T>(obj, null, null);
			// bst_root_node_arr[h1] = root_node;
			i = -1;
			return null;

		} else{
			Node<K, T> bst_root = bst_root_node_arr[h1];
			// if(bst.search(bst_root, key) == null){
			// 	return null;
			// }
			temp_n = bst.search(bst_root, key);
			return temp_n.value();
		}
		// return temp_n.value();
		// return null;
	}
	public String address(K key) throws NotFoundException{
		int i = 0;
		String[] sv = key.toString().split(" ");
		String key_first = sv[0];
		String key_second = sv[1];
		int h1 = (int) djb2(key_first + key_second, this.t);
		// System.out.println(key_first + key_second + "----" + h1);
		// // System.out.println(h1+ " --------");
		String ans = h1+ "-";
		if (bst_root_node_arr[h1] == null){
			// Node<K, T> root_node = new Node<K, T>(obj, null, null);
			// bst_root_node_arr[h1] = root_node;
			
			return null;

		} else{
			Node<K, T> bst_root = bst_root_node_arr[h1];
			ans = ans + bst.addressBst(bst_root, key);
			return ans;
		}
	}
}