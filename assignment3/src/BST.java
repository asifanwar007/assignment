public class BST<K, T> {
	// public Node<K, T> root;
	// public T e;
	public int count_update = 0;//count_update updateBST
	public int count_add = 0;//count_update for add
	public int count_del = 0;
	public int count_search = 0;
	public Node<K, T> add(Node<K, T> root, K key, T e){ //should return number of steps to complete this steps
		Node<K, T> node = new Node<K, T>(key, e, null, null);
		count_add = 0;
        if (root == null) { 
            root = node; 
            count_add++;	
            return root;
        } 
        String[] root_value_key = root.key_value().toString().split(" ");
        String root_fn = root_value_key[0];
        String root_ln = root_value_key[1];

		String[] sv = key.toString().split(" ");
		String key_first = sv[0];
		String key_ln = sv[1];

		if(root_fn.equals(key_first) && root_ln.equals(key_ln)){
			count_add = -1;
			return null;
		}
        if ((root_fn.equals(key_first) != true) && root_fn.compareTo(key_first) < 0){
            root.right_node = add(root.right_node, key, e); 
            count_add++;
        } else if(root_fn.equals(key_first) && root_ln.compareTo(key_ln) < 0){
        	root.right_node = add(root.right_node, key, e); 
            count_add++;
        }
        else if ((root_fn.equals(key_first) != true) && root_fn.compareTo(key_first) > 0) {
        	root.left_node = add(root.left_node, key, e); 
        	count_add++;
            } 
        else if(root_fn.equals(key_first) && root_ln.compareTo(key_ln) > 0){
        	root.left_node = add(root.left_node, key, e); 
        	count_add++;
        }

        return root; 
	}
	public int updateBst(Node<K, T> root, K key, T e){
		count_update = 1;
		if(root == null){
			// return root;
			return -1;
		}

		String[] root_value_key = root.key_value().toString().split(" ");
        String root_fn = root_value_key[0];
        String root_ln = root_value_key[1];

		String[] sv = key.toString().split(" ");
		String key_first = sv[0];
		String key_ln = sv[1];

		if((root_fn.equals(key_first) && root_ln.equals(key_ln))){
			// System.out.println("h1");
			// count_update++;
			root.v = e;
			// return root;
			return count_update;
		}
		if ((root_fn.equals(key_first) != true) && root_fn.compareTo(key_first) < 0){
			// System.out.println("h2");
			// count_update++;
			// return updateBst(root.rightAfter(),key, e);
			return count_update + updateBst(root.rightAfter(),key, e);
		} else if((root_fn.equals(key_first) != true) && root_fn.compareTo(key_first) > 0){
			// System.out.println("h3");
			// count_update++;
			// return updateBst(root.leftAfter(), key, e);
			return count_update + updateBst(root.leftAfter(), key, e);
		}
		else if(root_fn.equals(key_first) && root_ln.compareTo(key_ln) > 0) {
			// System.out.println("h4");
			// count_update++;
			// return updateBst(root.leftAfter(),key, e); 
			return count_update + updateBst(root.leftAfter(), key, e);
		} 
		// count_update++;
		return count_update + updateBst(root.rightAfter(),key, e);
	}
	public Node<K, T> search(Node<K, T> root, K key){
		count_search = 0;
		if(root == null){
			return root;
		}
		String[] root_value_key = root.s.toString().split(" ");
        String root_fn = root_value_key[0];
        String root_ln = root_value_key[1];

		String[] sv = key.toString().split(" ");
		String key_first = sv[0];
		String key_ln = sv[1];

		if((root_fn.equals(key_first) && root_ln.equals(key_ln))){
			count_search++;
			return root;
		}
		if ((root_fn.equals(key_first) != true) && root_fn.compareTo(key_first) < 0){
			count_search++;
			return search(root.rightAfter(),key);
		} else if((root_fn.equals(key_first) != true) && root_fn.compareTo(key_first) > 0){
			count_search++;
			return search(root.leftAfter(), key);
		}
		else if(root_fn.equals(key_first) && root_ln.compareTo(key_ln) > 0) {
			count_search++;
			return search(root.leftAfter(),key); 
		}
		count_search++;
		return search(root.rightAfter(),key);

	}
	// public String addressBst()
	public String addressBst(Node<K, T> root, K key){
		String address = "";
		if(root == null){
			// return root;
			return "";
		}
		String[] root_value_key = root.key_value().toString().split(" ");
        String root_fn = root_value_key[0];
        String root_ln = root_value_key[1];

		String[] sv = key.toString().split(" ");
		String key_first = sv[0];
		String key_ln = sv[1];

		if((root_fn.equals(key_first) && root_ln.equals(key_ln))){
			// count_search++;
			return address;
		}
		if ((root_fn.equals(key_first) != true) && root_fn.compareTo(key_first) < 0){
			// count_search++;
			address = "R";
			return address + addressBst(root.rightAfter(),key);
		} else if((root_fn.equals(key_first) != true) && root_fn.compareTo(key_first) > 0){
			// count_search++;
			address = "L";
			return address + addressBst(root.leftAfter(), key);
		}
		else if(root_fn.equals(key_first) && root_ln.compareTo(key_ln) > 0) {
			// count_search++;
			address = "L";
			return address + addressBst(root.leftAfter(),key); 
		}
		// count_search++;
		address = "R";
		return address + addressBst(root.rightAfter(),key);

	}
	public boolean containsBst(Node<K, T> root, K key){
		// int count_nodes = 0;
		if(root == null){
			// return root;
			return false;
		}
		String[] root_value_key = root.key_value().toString().split(" ");
        String root_fn = root_value_key[0];
        String root_ln = root_value_key[1];

		String[] sv = key.toString().split(" ");
		String key_first = sv[0];
		String key_ln = sv[1];

		if((root_fn.equals(key_first) && root_ln.equals(key_ln))){
			// count_nodes++;
			return true;
		}
		if ((root_fn.equals(key_first) != true) && root_fn.compareTo(key_first) < 0){
			// count_nodes++;
			return containsBst(root.rightAfter(),key);
		} else if((root_fn.equals(key_first) != true) && root_fn.compareTo(key_first) > 0){
			// count_nodes++;
			return containsBst(root.leftAfter(), key);
		}
		else if(root_fn.equals(key_first) && root_ln.compareTo(key_ln) > 0) {
			// count_nodes++;
			return containsBst(root.leftAfter(),key); 
		}
		// count_nodes++;
		return containsBst(root.rightAfter(),key);

	}



	
	public Node<K, T> deleteBst(Node<K, T> root, K key){
		count_del = 1;
		// System.out.println("hhh" + count_del);
        if (root == null){
        // System.out.println("hh-0--------");
        // count_add++;
        	return root;
        }

		String[] root_value_key = root.key_value().toString().split(" ");
        String root_fn = root_value_key[0];
        String root_ln = root_value_key[1];

		String[] sv = key.toString().split(" ");
		String key_first = sv[0];
		String key_ln = sv[1];
		//  if((root_fn+root_ln).equals(key_first+key_ln)){
		// 	count_del++;
		// 	// System.out.println("asifa and");
		// }

		// System.out.println(root_fn + " " + root_ln + " " + key_first + " " + key_ln );
		// System.out.println(root_fn.compareTo(key_first) + "-----");

        if ((root_fn.equals(key_first) != true) && root_fn.compareTo(key_first) > 0) 
	        // {	System.out.println("befor hhh1");
	        	{root.left_node = deleteBst(root.left_node, key);
			// System.out.println("hhh1 " + count_del);
	         count_del++;} 
	    else if(root_fn.equals(key_first) && root_ln.compareTo(key_ln) > 0)
	        {root.left_node = deleteBst(root.left_node, key); 
				// System.out.println("hhh2 " + count_del);
	        	count_del++;} 
	    else if ((root_fn.equals(key_first) != true) && root_fn.compareTo(key_first) < 0){
            {root.right_node = deleteBst(root.right_node, key); 
            	// System.out.println("hhh3 " + count_del);
            	count_del++;}
        } else if(root_fn.equals(key_first) && root_ln.compareTo(key_ln) < 0)
            {root.right_node = deleteBst(root.right_node, key); 
            	// System.out.println("hhh4 " + count_del);
            	count_del++;}
        else{ 
        	// System.out.println("insidasd");
           if (root.left_node == null) 
                {	
                	// System.out.println("helo");
            	
            		count_del++;
            		// System.out.println(root.right_node.s.toString());
                	return root.right_node;} 
            else if (root.right_node == null) 
                {	
                	// System.out.println("helo1");
            	
            		count_del++;
                return root.left_node;}
                
    		Node<K,T> temp_min = minSuccesorNode(root.right_node);
    		Node<K,T> temp_rn = root.right_node;
    		Node<K,T> temp_ln = root.left_node;
    		// root = temp_min;(root_fn+root_ln).equals(key_first+key_ln)){
		// 	count_del++;
		// 	// System.out.println("asifa and");
		// }
            // root.s = this.minKey(root.right_node); 
            // root.right_node.v = this.minValue(root.right_node);
            K key_to_remove = temp_min.s;
            // System.out.println(key_to_remove.toString());
            temp_min.right_node = deleteBst(root.right_node, key_to_remove); 
    		root = temp_min;
    		temp_min.left_node = temp_ln;
            // count_del++;
            // System.out.println("hhh6 " + count_del);
        } 
      		// System.out.println("ek dum last");
       
            return root;
	}
	public Node<K, T> minSuccesorNode(Node<K, T> root) 
    { 
          
        while (root.left_node != null) 
        { 
           // System.out.println("----------------------");
           // System.out.println(root.left_node.s.toString());
           // System.out.println("----------------------");
        	count_del++;
            root = root.left_node; 
        } 
        // System.out.println(root.s.toString());
        return root; 
    }


    // public void inorderRec(Node root) 
    // { 
    //     if (root != null) 
    //     { 
    //         inorderRec(root.left_node); 
    //         System.out.print(root.s.toString() + " "); 
    //         inorderRec(root.right_node); 
    //     } 
    // } 


    // public static void main(String[] args) {
    // 	BST<Pair_sc, String> n = new BST();
    // 	Node<Pair_sc, String> node = new Node(Pair_sc("asif", "anwar"), "altamasg1", null, null);
    // 	Node<Pair_sc, String> node1 = new Node(Pair_sc("asifa", "anwar"), "altamasg2", null, null);
    // 	Node<Pair_sc, String> node2 = new Node(Pair_sc("asifb", "anwar"), "altamasg3", null, null);
    // 	Node<Pair_sc, String> node3 = new Node(Pair_sc("asifc", "anwar"), "altamasg4", null, null);
    // 	Node<Pair_sc, String> node4 = new Node(Pair_sc("asifd", "anwar"), "altamasg5", null, null);
    // 	n.add(node,node1.s, node1.v );
    // 	n.add(node,node2.s, node2.v );
    // 	n.add(node,node3.s, node3.v );
    // 	n.add(node,node4.s, node4.v );
    // }

}

