public class Graph{
	public RBTree<String, Triangle> triangle;
	// public LinkedList<Triangle> triangle;

	public RBTree<String, Node<Point>> points;
	// public LinkedList<Point> points;

	public RBTree<String, Node<Edge>> edges;
	// public LinkedList<Edge> edges;
	public boolean semi_proper_mesh= false;//for checkin only
	public boolean proper_mesh = true;//for checking only
	public boolean improper_mesh = false;

	Node<Point> rootPointNode;
	public Graph(){
		this.triangle = new RBTree<String, Triangle>();
		this.points = new RBTree<String, Node<Point>>();
		this.edges = new RBTree<String, Node<Edge>>();

		// this.triangle = new LinkedList<Triangle>();
		// this.points = new LinkedList<Point>();
		// this.edges = new LinkedList<Edge>();
	}

	public void addTriangle(Triangle t){
		Point point1 = t.p1;
		Point point2 = t.p2;
		Point point3 = t.p3;

		Edge edge1 = t.e1;
		Edge edge2 = t.e2;
		Edge edge3 = t.e3;

		String key_triangle = this.keyTriangle(point1, point2, point3);
		triangle.insert(key_triangle, t);
		// triangle.insert(t);
		this.addPoint(point1, point2, point3, t);
		// System.out.println("addPoint not working-------");
		this.addEdge(edge1, edge2, edge3, t);
		

	}	

	public Triangle[] searchEdge(Edge edge){
		String key_edge  = this.keyEdge(edge);
		RedBlackNode<String, Node<Edge>> node_e = edges.search(key_edge);
		if (node_e != null) {
			int i = node_e.value_arr.get(0).trianglePoint.size();
			Triangle[] ans = new Triangle[i];
			while(i > 0){
				ans[i-1] = node_e.value_arr.get(0).trianglePoint.get(i-1);
				i--; 
			}
			return ans;
		}
		return null;
	}

	public void addEdge(Edge edge1, Edge edge2, Edge edge3, Triangle t){
		String key_edge1 = this.keyEdge(edge1);
		String key_edge2 = this.keyEdge(edge2);
		String key_edge3 = this.keyEdge(edge3);
		Node<Edge> node1 = new Node<Edge>(edge1);
		Node<Edge> node2 = new Node<Edge>(edge2);
		Node<Edge> node3 = new Node<Edge>(edge3);

		

		if(edges.root == null){

			node1.trianglePoint.add(t);
			node2.trianglePoint.add(t);
			node3.trianglePoint.add(t);

			node1.pointList.insert(key_edge2, edge2);
			node1.pointList.insert(key_edge3, edge3);

			node2.pointList.insert(key_edge1, edge1);
			node2.pointList.insert(key_edge3, edge3);

			node3.pointList.insert(key_edge2, edge2);
			node3.pointList.insert(key_edge1, edge1);

			edges.insert(key_edge1, node1);
			edges.insert(key_edge2, node2);
			edges.insert(key_edge3, node3);

			// semi_proper_mesh = true;
			// proper_mesh = false;
		} else{
			RedBlackNode<String, Node<Edge>> node_s1 = edges.search(key_edge1);
			RedBlackNode<String, Node<Edge>> node_s2 = edges.search(key_edge2);
			RedBlackNode<String, Node<Edge>> node_s3 = edges.search(key_edge3);
			if(node_s1 == null){
				// node_s1.value_arr.get(0).pointList.insert(key_point2, node2);
				// node_s1.value_arr.get(0).pointList.insert(key_point3, node3);
				node1.pointList.insert(key_edge2, edge2);
				node1.pointList.insert(key_edge3, edge3);
				node1.trianglePoint.add(t);
				edges.insert(key_edge1, node1);
				// semi_proper_mesh = true;
				// proper_mesh = false;
			} else{
				Node<Edge> nodeP1 = node_s1.value_arr.get(0);
				// RedBlackNode<String, Node<Edge>> node_check = nodeP1.pointList.search(key_edge1);
				nodeP1.trianglePoint.add(t);
				nodeP1.pointList.insert(key_edge2, edge2);
				nodeP1.pointList.insert(key_edge3, edge3);

				 if(nodeP1.trianglePoint.size() > 2){
				 	improper_mesh = true;
				 } 
				 // else if(nodeP1.trianglePoint.size() == 2){
				 // 	proper_mesh = true;
				 // 	semi_proper_mesh = false;
				 // } else{
				 // 	semi_proper_mesh = true;
				 // 	proper_mesh = false;
				 // }
				
				// if(node_check == null){
				// 	nodeP1.pointList.insert(key_edge1, node1);
				// }

			}
			if(node_s2 == null){
				// node_s2.pointList.insert(key_edge1, node1);
				// node_s2.pointList.insert(key_edge3, node3);
				node2.pointList.insert(key_edge1, edge1);
				node2.pointList.insert(key_edge3, edge3);
				node2.trianglePoint.add(t);
				edges.insert(key_edge2, node2);
				// semi_proper_mesh = true;
				// proper_mesh = false;
			} else{
				Node<Edge> nodeP2 = node_s2.value_arr.get(0);
				// RedBlackNode<String, Node<Edge>> node_check = nodeP2.pointList.search(key_edge1);
				nodeP2.trianglePoint.add(t);
				nodeP2.pointList.insert(key_edge1, edge1);
				nodeP2.pointList.insert(key_edge3, edge3);

				if(nodeP2.trianglePoint.size() > 2){
				 	improper_mesh = true;
				 } 
				 // else if(nodeP2.trianglePoint.size() == 2){
				 // 	proper_mesh = true;
				 // } else{
				 // 	semi_proper_mesh = true;
				 // 	proper_mesh = false;
				 // }
				// if(node_check == null){
				// 	nodeP2.pointList.insert(key_edge2, node2);
				// }
			}
			if(node_s3 == null){
				// node_s3.pointList.insert(key_edge2, node2);
				// node_s3.pointList.insert(key_edge1, node1);
				node3.pointList.insert(key_edge1, edge1);
				node3.pointList.insert(key_edge2, edge2);
				node3.trianglePoint.add(t);
				// semi_proper_mesh = true;
				// proper_mesh = false;
				edges.insert(key_edge3, node3);
			} else{
				Node<Edge> nodeP3 = node_s3.value_arr.get(0);
				// RedBlackNode<String, Node<Edge>> node_check = nodeP3.pointList.search(key_edge1);
				nodeP3.trianglePoint.add(t);
				nodeP3.pointList.insert(key_edge1, edge1);
				nodeP3.pointList.insert(key_edge2, edge2);

				if(nodeP3.trianglePoint.size() > 2){
				 	improper_mesh = true;
				 } 
				 // else if(nodeP3.trianglePoint.size() == 2){
				 // 	proper_mesh = true;
				 // } else{
				 // 	semi_proper_mesh = true;
				 // 	proper_mesh = false;
				 // }
				// if(node_check == null){
				// 	nodeP3.pointList.insert(key_edge2, node2);
				// }
			}

		}


	}

	// public ArrayList<T> RBtreeTraversal(RBTree<String ,Node<T>> node){
	// 	if(node == null){
	// 		return null;
	// 	}
	// 	Node<T> nodeP1 = node.value_arr.get(0);
		
	// }
	public Triangle[] searchPoint(Point p){
		String key_point  = this.keyPoint(p);
		RedBlackNode<String, Node<Point>> node_e = points.search(key_point);
		if (node_e != null) {
			int i = node_e.value_arr.get(0).trianglePoint.size();
			Triangle[] ans = new Triangle[i];
			while(i > 0){
				ans[i-1] = node_e.value_arr.get(0).trianglePoint.get(i-1);
				i--; 
			}
			return ans;
		}
		return null;
	}
	public void addPoint(Point point1, Point point2, Point point3, Triangle t){
		String key_point1 = this.keyPoint(point1);
		String key_point2 = this.keyPoint(point2);
		String key_point3 = this.keyPoint(point3);
		Node<Point> node1 = new Node<Point>(point1);
		Node<Point> node2 = new Node<Point>(point2);
		Node<Point> node3 = new Node<Point>(point3);
		// System.out.println("---------------");
		// System.out.println(key_point1 + " " + key_point2 + " "+ key_point3);
		// System.out.println("---------------");
		if(points.root == null){
			node1.pointList.insert(key_point2, point2);
			node1.pointList.insert(key_point3, point3);
			node1.trianglePoint.add(t);

			node2.pointList.insert(key_point1, point1);
			node2.pointList.insert(key_point3, point3);
			node2.trianglePoint.add(t);

			node3.pointList.insert(key_point1, point1);
			node3.pointList.insert(key_point2, point2);
			node3.trianglePoint.add(t);

			points.insert(key_point1, node1);
			points.insert(key_point2, node2);
			points.insert(key_point3, node3);

			// rootPointNode = node1;
			// return rootPointNode;

		} else{
			// Node<Point> root = rootPointNode;
			RedBlackNode<String, Node<Point>> node_s1 = points.search(key_point1);
			RedBlackNode<String, Node<Point>> node_s2 = points.search(key_point2);
			RedBlackNode<String, Node<Point>> node_s3 = points.search(key_point3);
			if(node_s1 == null){
				// node_s1.value_arr.get(0).pointList.insert(key_point2, node2);
				// node_s1.value_arr.get(0).pointList.insert(key_point3, node3);
				node1.pointList.insert(key_point2, point2);
				node1.pointList.insert(key_point3, point3);
				node1.trianglePoint.add(t);
				points.insert(key_point1, node1);
			} else{
				// if(node_s1.getValue() ==null)
				// System.out.println("helo brother");
				Node<Point> nodeP1 = node_s1.value_arr.get(0);
				// Node<Point> nodeP1 = node_s1.getValue();
			// System.out.println("above trianglePoint ");
				nodeP1.trianglePoint.add(t);
				RedBlackNode<String, Point> node_check1 = nodeP1.pointList.search(key_point2);
				RedBlackNode<String, Point> node_check2 = nodeP1.pointList.search(key_point3);
				if(node_check1 == null){
					nodeP1.pointList.insert(key_point2, point2);
				}
				if(node_check2 == null){
					nodeP1.pointList.insert(key_point3, point3);
				}

			}
			if(node_s2 == null){
				// node_s2.pointList.insert(key_point1, node1);
				// node_s2.pointList.insert(key_point3, node3);
				node2.pointList.insert(key_point1, point1);
				node2.pointList.insert(key_point3, point3);
				node2.trianglePoint.add(t);
				points.insert(key_point2, node2);
			} else{
			// System.out.println("Inside point query");
				Node<Point> nodeP1 = node_s2.value_arr.get(0);
				nodeP1.trianglePoint.add(t);
				RedBlackNode<String, Point> node_check1 = nodeP1.pointList.search(key_point1);
				RedBlackNode<String, Point> node_check2 = nodeP1.pointList.search(key_point3);
				if(node_check1 == null){
					nodeP1.pointList.insert(key_point1, point1);
				}
				if(node_check2 == null){
					nodeP1.pointList.insert(key_point3, point3);
				}
			}
			if(node_s3 == null){
				// System.out.println("Inside node s3 null");
				// node_s3.pointList.insert(key_point2, node2);
				// node_s3.pointList.insert(key_point1, node1);
				node3.pointList.insert(key_point1, point1);
				node3.pointList.insert(key_point2, point2);
				node3.trianglePoint.add(t);
				points.insert(key_point3, node3);
			} else{
				Node<Point> nodeP1 = node_s3.value_arr.get(0);
				nodeP1.trianglePoint.add(t);
				RedBlackNode<String, Point> node_check1 = nodeP1.pointList.search(key_point1);
				RedBlackNode<String, Point> node_check2 = nodeP1.pointList.search(key_point2);
				if(node_check1 == null){
					nodeP1.pointList.insert(key_point1, point1);
				}
				if(node_check2 == null){
					nodeP1.pointList.insert(key_point2, point2);
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
	public String keyTriangle(Point a, Point b, Point c){
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