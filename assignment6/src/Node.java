public class Node<T>{
	public T p;
	// public String keyStringPoint;
	public ArrayList<T> pointList;
	public ArrayList<Triangle> trianglePoint;
	// public LinkedList<Node> pointList;
	public Node(T p){
		this.p = p;
		this.pointList = new ArrayList<T>();
		this.trianglePoint = new ArrayList<Triangle>();
		// this.pointList = new LinkedList<Node>();
	}

	public String toString(){
		return p.toString();
	}

}