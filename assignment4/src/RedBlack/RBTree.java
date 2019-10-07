package RedBlack;


public class RBTree<T extends Comparable, E> implements RBTreeInterface<T, E>  {
	public RedBlackNode<T, E> root;


    @Override
    public void insert(T key, E value) {
    	RedBlackNode<T, E> node = new RedBlackNode<T, E>(key, value, true);
    	if(root == null){
    		root = node;
    		root.makeBlack();
    	} else{
    		RedBlackNode<T, E> temp = root;
    		RedBlackNode<T, E> temp_uncle = root.rn;
    		RedBlackNode<T, E> temp_father = root;
    		while(temp != null){
	    		if(temp.compareTo(key) > 0){
	    			temp_father = temp;
    				temp_uncle = temp.rn;
	    			temp = temp.ln;
	    		} else if(temp.compareTo(key) == 0){
	    			// temp.getValues(); 

	    			temp.value_arr.add(value);

	    			break;	
	    		} else{
	    			temp_father = temp;
    				temp_uncle = temp.ln;
	    			temp = temp.rn;
	    		}
    		}
    		if(temp == null)
	    		if(temp_father.isRed != true){
	    			temp = node;
	    		} else{
	    			remedyDoubleRed(root, key);
	    		}

    	}
    }

    public RedBlackNode<T, E> remedyDoubleRed(RedBlackNode<T, E> root_node, T key){
		RedBlackNode<T, E> temp_father = root_node;
		RedBlackNode<T, E> temp_uncle = root_node.rn;
    	RedBlackNode<T, E> temp_son = root_node.ln; //to resolve this node
    	RedBlackNode<T, E> temp_grand_son = root_node;
    	if(root_node == null){
    		return null;}
    	while(temp_father != null){
	    	if (root_node.compareTo(key) > 0){
	    		temp_father = root_node;
	    		temp_uncle = root_node.rn;
	    		temp_son = root_node.ln;
	    		if(temp_son != null){temp_grand_son = temp_son.ln;}
	    		if(temp_son != null && temp_father.isRed != true && temp_son.isRed && temp_uncle.isRed && temp_grand_son.isRed){
	    			temp_father.makeBlack();
	    			temp_uncle.makeBlack();
	    			temp_son.makeBlack();
	    			break;
	    		} else if(temp_son != null && temp_father.isRed != true && temp_son.isRed && temp_uncle.isRed != red && temp_grand_son.isRed){
	    			temp_father.makeBlack();
	    			temp_uncle.makeBlack();
	    			temp_son.makeBlack();
	    			break;
	    		}

	    	} else{
	    		temp_father = root_node;
	    		temp_uncle = root_node.ln;
	    		temp_son = root_node.rn;
	    		if(temp_son != null){temp_grand_son = temp_son.ln;}
	    		if(temp_son != null && temp_father.isRed != true && temp_son.isRed && temp_uncle.isRed && temp_grand_son.isRed){
	    			temp_father.makeBlack();
	    			temp_uncle.makeBlack();
	    			temp_son.makeBlack();
	    			break;
	    		}

	    	}
	    }
	    return remedyDoubleRed(root_node, key);
    }

    @Override
    public RedBlackNode<T, E> search(T key) {
        return null;
    }
}