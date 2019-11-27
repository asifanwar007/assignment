public class Graph<T>{
	public ArrayList<Triangle> triangle;
	public ArrayList<Node<Edge>> edges;
	public ArrayList<Node<Point>> points;

	public boolean improper_mesh = false;

	public Graph(){
		this.triangle = new ArrayList<Triangle>();
		this.edges = new ArrayList<Node<Edge>>();
		this.points = new ArrayList<Node<Point>>();
	}

	public Triangle isTriangleAvai(Triangle p){
		for(int i = 0; i < triangle.size(); i++){
			if(this.keyTriangle(triangle.get(i)).equals(this.keyTriangle(p))){
				return triangle.get(i);
			}
		}
		return null;
	}
	public void addTriangle(Triangle t){
		triangle.add(t);
		this.addPoint(t);
		this.addEdge(t);
	}

	public Node<Point> searchPoint(Point p){
		for(int i = 0; i < points.size(); i++){
			if(this.keyPoint(points.get(i).p).equals(this.keyPoint(p))){
				return points.get(i);
			}
		}
		return null;
	}

	public Point isPointAvai(ArrayList<Point> arr, Point p){
		for(int i = 0; i < arr.size(); i++){
			if(this.keyPoint(arr.get(i)).equals(this.keyPoint(p))){
				return arr.get(i);
			}
		}
		return null;
	}
	public void addPoint(Triangle t){
		Node<Point> node1 = new Node<Point>(t.p1);
		Node<Point> node2 = new Node<Point>(t.p2);
		Node<Point> node3 = new Node<Point>(t.p3);

		if(points.size() == 0){
			node1.pointList.add(t.p2);
			node1.pointList.add(t.p3);
			node1.trianglePoint.add(t);

			node2.pointList.add(t.p1);
			node2.pointList.add(t.p3);
			node2.trianglePoint.add(t);

			node3.pointList.add(t.p1);
			node3.pointList.add(t.p2);
			node3.trianglePoint.add(t);

			points.add(node1);
			points.add(node2);
			points.add(node3);

			// rootPointNode = node1;
			// return rootPointNode;

		} else{
			// Node<Point> root = rootPointNode;
			Node<Point> node_s1 = this.searchPoint(t.p1);
			Node<Point> node_s2 = this.searchPoint(t.p2);
			Node<Point> node_s3 = this.searchPoint(t.p3);
			if(node_s1 == null){
				node1.pointList.add(t.p2);
				node1.pointList.add(t.p3);
				node1.trianglePoint.add(t);
				points.add(node1);
			} else{
				node_s1.trianglePoint.add(t);
				Point node_check2 = this.isPointAvai(node_s1.pointList, t.p2);
				Point node_check3 = this.isPointAvai(node_s1.pointList, t.p3);
				if(node_check2 == null){
					node_s1.pointList.add(t.p2);
				}
				if(node_check3 == null){
					node_s1.pointList.add(t.p3);
				}

			}
			if(node_s2 == null){

				node2.pointList.add(t.p1);
				node2.pointList.add(t.p3);
				node2.trianglePoint.add(t);
				points.add(node2);
			} else{
			// System.out.println("Inside point query");
				node_s2.trianglePoint.add(t);
				Point node_check2 = this.isPointAvai(node_s2.pointList, t.p1);
				Point node_check3 = this.isPointAvai(node_s2.pointList, t.p3);
				if(node_check2 == null){
					node_s2.pointList.add(t.p1);
				}
				if(node_check3 == null){
					node_s2.pointList.add(t.p3);
				}

			}
			if(node_s3 == null){
				node3.pointList.add(t.p1);
				node3.pointList.add(t.p2);
				node3.trianglePoint.add(t);
				points.add(node3);
			} else{
				node_s3.trianglePoint.add(t);
				Point node_check2 = this.isPointAvai(node_s3.pointList, t.p1);
				Point node_check3 = this.isPointAvai(node_s3.pointList, t.p2);
				if(node_check2 == null){
					node_s3.pointList.add(t.p1);
				}
				if(node_check3 == null){
					node_s3.pointList.add(t.p2);
				}
			}
			


		}
	}


	public Node<Edge> searchEdge(Edge p){
		for(int i = 0; i < edges.size(); i++){
			if(this.keyEdge(edges.get(i).p).equals(this.keyEdge(p))){
				return edges.get(i);
			}
		}
		return null;
	}

	public Edge isEdgeAvai(ArrayList<Edge> arr, Edge p){
		for(int i = 0; i < arr.size(); i++){
			if(this.keyEdge(arr.get(i)).equals(this.keyEdge(p))){
				return arr.get(i);
			}
		}
		return null;
	}

	public void addEdge(Triangle t){

		Node<Edge> node1 = new Node<Edge>(t.e1);
		Node<Edge> node2 = new Node<Edge>(t.e2);
		Node<Edge> node3 = new Node<Edge>(t.e3);

		

		if(edges.size() == 0){

			node1.pointList.add(t.e2);
			node1.pointList.add(t.e3);
			node1.trianglePoint.add(t);

			node2.pointList.add(t.e1);
			node2.pointList.add(t.e3);
			node2.trianglePoint.add(t);

			node3.pointList.add(t.e1);
			node3.pointList.add(t.e2);
			node3.trianglePoint.add(t);

			edges.add(node1);
			edges.add(node2);
			edges.add(node3);
			// semi_proper_mesh = true;
			// proper_mesh = false;
		} else{
			Node<Edge> node_s1 = this.searchEdge(t.e1);
			Node<Edge> node_s2 = this.searchEdge(t.e2);
			Node<Edge> node_s3 = this.searchEdge(t.e3);
			if(node_s1 == null){
				node1.pointList.add(t.e2);
				node1.pointList.add(t.e3);
				node1.trianglePoint.add(t);
				edges.add(node1);
			} else{
				node_s1.trianglePoint.add(t);
				Edge node_check2 = this.isEdgeAvai(node_s1.pointList, t.e2);
				Edge node_check3 = this.isEdgeAvai(node_s1.pointList, t.e3);
				if(node_check2 == null){
					node_s1.pointList.add(t.e2);
				}
				if(node_check3 == null){
					node_s1.pointList.add(t.e3);
				}
				if(node_s1.trianglePoint.size() > 2){
					improper_mesh = true;
				}

			}
			if(node_s2 == null){

				node2.pointList.add(t.e1);
				node2.pointList.add(t.e3);
				node2.trianglePoint.add(t);
				edges.add(node2);
			} else{
			// System.out.println("Inside Edge query");
				node_s2.trianglePoint.add(t);
				Edge node_check2 = this.isEdgeAvai(node_s2.pointList, t.e1);
				Edge node_check3 = this.isEdgeAvai(node_s2.pointList, t.e3);
				if(node_check2 == null){
					node_s2.pointList.add(t.e1);
				}
				if(node_check3 == null){
					node_s2.pointList.add(t.e3);
				}
				if(node_s2.trianglePoint.size() > 2){
					improper_mesh = true;
				}

			}
			if(node_s3 == null){
				node3.pointList.add(t.e1);
				node3.pointList.add(t.e2);
				node3.trianglePoint.add(t);
				edges.add(node3);
			} else{
				node_s3.trianglePoint.add(t);
				Edge node_check2 = this.isEdgeAvai(node_s3.pointList, t.e1);
				Edge node_check3 = this.isEdgeAvai(node_s3.pointList, t.e2);
				if(node_check2 == null){
					node_s3.pointList.add(t.e1);
				}
				if(node_check3 == null){
					node_s3.pointList.add(t.e2);
				}
				if(node_s3.trianglePoint.size() > 2){
					improper_mesh = true;
				}
			}
		}
	}



	public String keyPoint(Point p){
		float x1 = p.getX();
		float y1 = p.getY();
		float z1 = p.getZ();

		return x1+""+y1+""+z1;
	}
	public String keyEdge(Edge e){
		Point point1 = e.p1;
		Point point2 = e.p2;
		String key_point1 = this.keyPoint(point1);
		String key_point2 = this.keyPoint(point2);
		if(key_point1.compareTo(key_point2) > 0){
			return key_point1 + key_point2;
		}
		return key_point2 + key_point1;
	}
	public String keyTriangle(Triangle t){
		Point a = t.p1; Point b = t.p2; Point c = t.p3;

		String key_point1 = this.keyPoint(a);
		String key_point2 = this.keyPoint(b);
		String key_point3 = this.keyPoint(c);
		// System.out.print("key poinnt ----");
		// System.out.println(key_point1 + " " + key_point2 + " " +
		// 	key_point3);

		String[] k = {key_point1, key_point2, key_point3};

		for(int i = 0; i < 2 ; i++){
			if(k[i].compareTo(k[i+1]) < 0){
				String temp = k[i];
				k[i] = k[i+1];
				k[i+1] =temp;
			}
		}
		for(int i = 0; i < 2 ; i++){
			if(k[i].compareTo(k[i+1]) < 0){
				String temp = k[i];
				k[i] = k[i+1];
				k[i+1] = temp;
			}
		}
		return k[0] + k[1] + k[2];
		
	}
}