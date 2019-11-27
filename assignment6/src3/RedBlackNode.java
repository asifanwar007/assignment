

public class RedBlackNode<T extends Comparable, E> {
	public boolean isRed;
	public T key;
	public RedBlackNode<T, E> rn;
	public RedBlackNode<T, E> ln;
	public RedBlackNode<T, E> pn;
	public ArrayList<E> value_arr;

	public RedBlackNode(T key, E val, boolean bool){
		this.isRed = false;
		this.key = key;
		this.value_arr = new ArrayList<E>();
		this.value_arr.add(val);
        // System.out.println("insiede node");
        // this.rn = null;
        // this.ln = null;
		this.isRed = bool;
		// this.value_arr = new List<E>();
	}

    public E getValue() {
        return value_arr.get(0);
    }

    public ArrayList<E> getValues() {
        return value_arr;
    }
    public void makeRed(){
    	this.isRed = true;
    }
    public void makeBlack(){
    	this.isRed = false;
    }
    public int compareTo(T keys){
    	return this.key.compareTo(keys);
    }
}