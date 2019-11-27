public class Edge implements EdgeInterface{
	Point p1;
	Point p2;
	public Edge(Point p1, Point p2){
		this.p1 = p1;
		this.p2 = p2;
	}
	//check string print fuction
	public String printEdge(){
		String a = p1.getX() + " " + p1.getY() + " " + p1.getZ() + " " + 
					p2.getX() + " " + p2.getY() + " " + p2.getZ();
		return a;
	}
	public String toString(){
		return this.p1.printPoint()  + this.p2.printPoint();
	}
	public PointInterface[] edgeEndPoints(){
		return new PointInterface[]{p1, p2};
	}
}