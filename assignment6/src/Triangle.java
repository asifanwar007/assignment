public class Triangle implements TriangleInterface{
	Point p1;
	Point p2;
	Point p3;

	Edge e1; //p1-p2
	Edge e2; //p2-p3
	Edge e3; //p3-p4
	public int triangle_insertion_pos;
	public int max_dia_count = 214748364;

	public Triangle(Point p1, Point p2, Point p3){
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		this.e1 = new Edge(p1, p2);
		this.e2 = new Edge(p2, p3);
		this.e3 = new Edge(p3, p1);
	}


	public PointInterface [] triangle_coord(){
		return new PointInterface[]{p1,p2,p3};
	}
}