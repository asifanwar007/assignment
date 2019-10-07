package RedBlack;

import Util.RBNodeInterface;

import java.util.List;

public class RedBlackNode<T extends Comparable, E> implements RBNodeInterface<E> {
	public boolean isRed;
	public T key;
	public RedBlackNode<T, E> rn;
	public RedBlackNode<T, E> ln;
	public List<E> value_arr;

	public RedBlackNode(T key, E val, boolean bool){
		this.isRed = false;
		this.key = key;
		this.value_arr.add(val);
		this.isRed = bool;
		// this.value_arr = new List<E>();
	}

    @Override
    public E getValue() {
        return value_arr.get(0);
    }

    @Override
    public List<E> getValues() {
        return value_arr;
    }
    public void makeRed(){
    	this.isRed = true;
    }
    public void makeBlack(){
    	this.isRed = false;
    }
    public int compareTo(T key){
    	return this.key.compareTo(key);
    }
}
