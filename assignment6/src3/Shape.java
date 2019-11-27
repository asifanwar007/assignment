public class Shape implements ShapeInterface{

	public int triangle_insert_pos = 0;

	Graph graph;
	public Shape(){
		this.graph = new Graph();
	}
	public boolean ADD_TRIANGLE(float[] triangle_coord){
		if(triangle_coord.length != 9) return  false;
		Point point1 = new Point(triangle_coord[0], triangle_coord[1], triangle_coord[2]);
		Point point2 = new Point(triangle_coord[3], triangle_coord[4], triangle_coord[5]);
		Point point3 = new Point(triangle_coord[6], triangle_coord[7], triangle_coord[8]);

		//check that point is triangle or not
		float row1 = (point2.getY()-point1.getY())*(point3.getZ()- point1.getZ())
						- (point3.getY()-point1.getY())*(point2.getZ() - point1.getZ());
		float row2 = (point3.getX()-point1.getX())*(point2.getZ()- point1.getZ())
						- (point2.getX()-point1.getX())*(point3.getZ() - point1.getZ());				
		float row3 = (point3.getY()-point1.getY())*(point2.getX()- point1.getX())
						- (point2.getY()-point1.getY())*(point3.getX() - point1.getX());
		if(row1 == 0.0 && row2 == 0.0 && row3 == 0.0 ){
			return false;
		}
		Triangle t = new Triangle(point1, point2, point3);
		String key_triangle = graph.keyTriangle(point1, point2, point3);
			// System.out.println("hello brohter");
		if(graph.triangle.search(key_triangle) !=  null){
			return false;
		}

		t.triangle_insertion_pos = triangle_insert_pos;
		triangle_insert_pos++;

		Edge edge1 = t.e1;
		Edge edge2 = t.e2;
		Edge edge3 = t.e3;
		// RedBlackNode search_graph = graph.triangle.search(key_triangle);

		graph.addTriangle(t);


		return true;
	}
	public int TYPE_MESH(){
		if(graph.improper_mesh){
			return 3;
		}
		if(graph.proper_mesh){
			return 1;
		}
		return 2;
	}

	public Edge[] edgeSorting(Edge[] e){
		// System.out.println(e.length);

		for(int i = 0; i<e.length; i++){
			Point point1 = e[i].p1;
			Point point2 = e[i].p2;
			float e_l = (point1.getX()-point2.getX())*(point1.getX()-point2.getX()) + 
						(point1.getY()-point2.getY())*(point1.getY()-point2.getY()) +
						(point1.getZ()-point2.getZ())*(point1.getZ()-point2.getZ());
			for(int j = i; j<e.length; j++){
				point1 = e[j].p1;
				point2 = e[j].p2;
				float e_j = (point1.getX()-point2.getX())*(point1.getX()-point2.getX()) + 
							(point1.getY()-point2.getY())*(point1.getY()-point2.getY()) +
							(point1.getZ()-point2.getZ())*(point1.getZ()-point2.getZ());
		// System.out.println("bhaiya boudary edge ke andar @@@@@" +i + " " + e_l + " " + e_j);
				if(e_l < e_j){
					Edge temp = e[i];
					e[i] = e[j];
					e[j] = temp;
				}
			}
		}
		return e;
	}

	public void boundary_edges_helper(RedBlackNode<String, Node<Edge>> node){
		if(node == null){
			return;
		}
		Node<Edge> nodeP1 = node.value_arr.get(0);
		if(nodeP1.trianglePoint.size() <= 1){
			// System.out.println(nodeP1.p.printEdge());
			ans_arr_boundary_edges.add(nodeP1.p);
			// System.out.println(ans_arr_boundary_edges.size() + " where I'm worln");
		}
		boundary_edges_helper(node.ln);
		boundary_edges_helper(node.rn);
		// return ans_arr_boundary_edges;
	}
	public ArrayList<Edge> ans_arr_boundary_edges;
	public EdgeInterface [] BOUNDARY_EDGES(){
		ans_arr_boundary_edges = new ArrayList<Edge>();
		this.boundary_edges_helper(graph.edges.root);
		ArrayList<Edge> ans_arr = ans_arr_boundary_edges;
		// System.out.println(ans_arr.size());
		 Edge[] ans = new Edge[ans_arr.size()];
		 for(int i = 0; i < ans_arr.size(); i++ ){
		 	// System.out.println(ans_arr.get(i).printEdge());
		 	ans[i] = ans_arr.get(i);
		 }
		 	// System.out.println("heloo brohter =---------");
		 if(ans_arr.size() <= 0){
		 	return null;
		 }
		 ans = edgeSorting(ans);
		// System.out.println("inside add triangel bhaiya    ");

		return ans;
	}


	public int COUNT_CONNECTED_COMPONENTS(){
		return 0;
	}

	public TriangleInterface [] NEIGHBORS_OF_TRIANGLE(float [] triangle_coord){
		if(triangle_coord.length != 9) return  null;
		Point point1 = new Point(triangle_coord[0], triangle_coord[1], triangle_coord[2]);
		Point point2 = new Point(triangle_coord[3], triangle_coord[4], triangle_coord[5]);
		Point point3 = new Point(triangle_coord[6], triangle_coord[7], triangle_coord[8]);

		Triangle t = new Triangle(point1, point2, point3);

		Edge edge1 = t.e1;
		Edge edge2 = t.e2;
		Edge edge3 = t.e3;
		String key_triangle = graph.keyTriangle(point1, point2, point3);
		RedBlackNode<String, Triangle> node_t = graph.triangle.search(key_triangle);
		if(node_t == null){
			return null;
		}

		Triangle[] arr1 = graph.searchEdge(edge1);
		Triangle[] arr2 = graph.searchEdge(edge2);
		Triangle[] arr3 = graph.searchEdge(edge3);

		int t_size = arr1.length + arr2.length + arr3.length - 3;
		if(t_size <= 0){
			return null;
		}
		Triangle[] ans = new Triangle[t_size];
		int ans_pos = 0;
		for(Triangle c : arr1){
			String key_compare = graph.keyTriangle(c.p1, c.p2, c.p3);
			if(!key_triangle.equals(key_compare)){
				ans[ans_pos] = c;
				ans_pos++;
			}
		}
		for(Triangle c : arr2){
			String key_compare = graph.keyTriangle(c.p1, c.p2, c.p3);
			if(!key_triangle.equals(key_compare)){
				ans[ans_pos] = c;
				ans_pos++;
			}
		}
		for(Triangle c : arr3){
			String key_compare = graph.keyTriangle(c.p1, c.p2, c.p3);
			if(!key_triangle.equals(key_compare)){
				ans[ans_pos] = c;
				ans_pos++;
			}
		}
		for(int k = 0; k < ans.length; k++){
			for(int j = k; j< ans.length; j++){
				int temp_k = ans[k].triangle_insertion_pos;
				int temp_j = ans[j].triangle_insertion_pos;
				if( temp_k > temp_j){
					Triangle temp = ans[k];
					ans[k] = ans[j];
					ans[j] = temp;
				}
			}
		}
		return ans;
	}

	public EdgeInterface [] EDGE_NEIGHBOR_TRIANGLE(float [] triangle_coord){
		if(triangle_coord.length != 9) return  null;
		Point point1 = new Point(triangle_coord[0], triangle_coord[1], triangle_coord[2]);
		Point point2 = new Point(triangle_coord[3], triangle_coord[4], triangle_coord[5]);
		Point point3 = new Point(triangle_coord[6], triangle_coord[7], triangle_coord[8]);

		Triangle t = new Triangle(point1, point2, point3);

		Edge edge1 = t.e1;
		Edge edge2 = t.e2;
		Edge edge3 = t.e3;

		String key_edge1 = graph.keyEdge(edge1);
		String key_edge2 = graph.keyEdge(edge2);
		String key_edge3 = graph.keyEdge(edge3);

		String key_triangle = graph.keyTriangle(point1, point2, point3);
		RedBlackNode<String, Triangle> node_t = graph.triangle.search(key_triangle);
		if(node_t == null){
			return null;
		}

		Triangle[] arr1 = graph.searchEdge(edge1);
		Triangle[] arr2 = graph.searchEdge(edge2);
		Triangle[] arr3 = graph.searchEdge(edge3);

		int t_size = arr1.length + arr2.length + arr3.length - 3;
		if(t_size <= 0){
			return null;
		}
		Triangle[] ans = new Triangle[t_size];
		int ans_pos = 0;
		for(Triangle c : arr1){
			String key_compare = graph.keyTriangle(c.p1, c.p2, c.p3);
			if(!key_triangle.equals(key_compare)){
				ans[ans_pos] = c;
				ans_pos++;
			}
		}
		for(Triangle c : arr2){
			String key_compare = graph.keyTriangle(c.p1, c.p2, c.p3);
			if(!key_triangle.equals(key_compare)){
				ans[ans_pos] = c;
				ans_pos++;
			}
		}
		for(Triangle c : arr3){
			String key_compare = graph.keyTriangle(c.p1, c.p2, c.p3);
			if(!key_triangle.equals(key_compare)){
				ans[ans_pos] = c;
				ans_pos++;
			}
		}
		// ans[ans_pos] = t;
		ArrayList<Edge> ans_edge = new ArrayList<Edge>();
		// for(int k = 0; k < ans.length; k++){
		// 	for(int j = k; j< ans.length; j++){
		// 		int temp_k = ans[k].triangle_insertion_pos;
		// 		int temp_j = ans[j].triangle_insertion_pos;
		// 		if( temp_k > temp_j){
		// 			Triangle temp = ans[k];
		// 			ans[k] = ans[j];
		// 			ans[j] = temp;
		// 		}
		// 	}
		// }

		for(Triangle c: ans){
			String compare_edge1 = graph.keyEdge(c.e1);
			String compare_edge2 = graph.keyEdge(c.e2);
			String compare_edge3 = graph.keyEdge(c.e3);
			if(!compare_edge1.equals(key_edge1) || !compare_edge1.equals(key_edge2) || !compare_edge1.equals(key_edge3))
				ans_edge.add(c.e1);
			if(!compare_edge2.equals(key_edge1) || !compare_edge2.equals(key_edge2) || !compare_edge2.equals(key_edge3))
				ans_edge.add(c.e2);
			if(!compare_edge3.equals(key_edge1) || !compare_edge3.equals(key_edge2) || !compare_edge3.equals(key_edge3))
				ans_edge.add(c.e3);
		}

		Edge[] ans_edge_array = new Edge[ans_edge.size()+3];
		for(int i = 0; i<ans_edge.size(); i++){
			ans_edge_array[i] = ans_edge.get(i);
		}
		ans_edge_array[ans_edge.size()] = edge1;
		ans_edge_array[ans_edge.size()+1] = edge2;
		ans_edge_array[ans_edge.size()+2] = edge3;
		return ans_edge_array;
	}


	public PointInterface [] VERTEX_NEIGHBOR_TRIANGLE(float [] triangle_coord){
		if(triangle_coord.length != 9) return  null;
		Point point1 = new Point(triangle_coord[0], triangle_coord[1], triangle_coord[2]);
		Point point2 = new Point(triangle_coord[3], triangle_coord[4], triangle_coord[5]);
		Point point3 = new Point(triangle_coord[6], triangle_coord[7], triangle_coord[8]);

		Triangle t = new Triangle(point1, point2, point3);

		Edge edge1 = t.e1;
		Edge edge2 = t.e2;
		Edge edge3 = t.e3;

		String key_point1 = graph.keyPoint(point1);
		String key_point2 = graph.keyPoint(point2);
		String key_point3 = graph.keyPoint(point3);

		String key_triangle = graph.keyTriangle(point1, point2, point3);
		RedBlackNode<String, Triangle> node_t = graph.triangle.search(key_triangle);
		if(node_t == null){
			return null;
		}

		RedBlackNode<String, Node<Point>> rbNode_p1 = graph.points.search(key_point1);
		RedBlackNode<String, Node<Point>> rbNode_p2 = graph.points.search(key_point2);
		RedBlackNode<String, Node<Point>> rbNode_p3 = graph.points.search(key_point3);

		Node<Point> node_p1 = rbNode_p1.value_arr.get(0);
		Node<Point> node_p2 = rbNode_p2.value_arr.get(0);
		Node<Point> node_p3 = rbNode_p3.value_arr.get(0);

		Traversal<Point> traversal = new Traversal<Point>();
		ArrayList<Point> ans_arr_list = new ArrayList<Point>();

		ans_arr_list = traversal.RBTreeTraversal(rbNode_p1.value_arr.get(0).pointList.root);
		ArrayList<Point> ans_arr_list1 = traversal.RBTreeTraversal(rbNode_p2.value_arr.get(0).pointList.root);
		ArrayList<Point> ans_arr_list2 = traversal.RBTreeTraversal(rbNode_p3.value_arr.get(0).pointList.root);


		boolean toAdd = true;
		for(int i = 0; i < ans_arr_list1.size(); i++){
			String ans_arr_list1Key = graph.keyPoint(ans_arr_list1.get(i));
			for(int j = 0; j<ans_arr_list.size(); j++){
				String ans_arr_listKey = graph.keyPoint(ans_arr_list.get(j));
				if(ans_arr_listKey.equals(ans_arr_list1Key)){
					toAdd = false;
				}
			}
			if(toAdd)
				ans_arr_list.add(ans_arr_list1.get(i));
		}

		toAdd = true;
		for(int i = 0; i < ans_arr_list2.size(); i++){
			String ans_arr_list2Key = graph.keyPoint(ans_arr_list2.get(i));
			for(int j = 0; j<ans_arr_list.size(); j++){
				String ans_arr_listKey = graph.keyPoint(ans_arr_list.get(j));
				if(!ans_arr_listKey.equals(ans_arr_list2Key)){
					toAdd = false;
				}
			}
			if(toAdd)
				ans_arr_list.add(ans_arr_list2.get(i));
		}
		Point[] ans = new Point[ans_arr_list.size()];
		for(int i = 0; i<ans_arr_list.size(); i++){
			ans[i] = ans_arr_list.get(i);
		}
		return ans;
	}

	public TriangleInterface [] EXTENDED_NEIGHBOR_TRIANGLE(float [] triangle_coord){
		if(triangle_coord.length != 9) return  null;
		Point point1 = new Point(triangle_coord[0], triangle_coord[1], triangle_coord[2]);
		Point point2 = new Point(triangle_coord[3], triangle_coord[4], triangle_coord[5]);
		Point point3 = new Point(triangle_coord[6], triangle_coord[7], triangle_coord[8]);

		Triangle t = new Triangle(point1, point2, point3);

		Edge edge1 = t.e1;
		Edge edge2 = t.e2;
		Edge edge3 = t.e3;

		String key_point1 = graph.keyPoint(point1);
		String key_point2 = graph.keyPoint(point2);
		String key_point3 = graph.keyPoint(point3);

		String key_triangle = graph.keyTriangle(point1, point2, point3);
		RedBlackNode<String, Triangle> node_t = graph.triangle.search(key_triangle);
		if(node_t == null){
			return null;
		}
		System.out.println("insie EXTENDED_NEIGHBOR_TRIANGLE dkfj");
		Triangle[] arr1 = graph.searchPoint(point1);
		Triangle[] arr2 = graph.searchPoint(point2);
		Triangle[] arr3 = graph.searchPoint(point3);
		int t_size = arr1.length + arr2.length + arr3.length - 3;
		if(t_size <= 0){
			return null;
		}
		Triangle[] ans = new Triangle[t_size];
		int ans_pos = 0;
		for(Triangle c : arr1){
			String key_compare = graph.keyTriangle(c.p1, c.p2, c.p3);
			if(!key_triangle.equals(key_compare)){
				ans[ans_pos] = c;
				ans_pos++;
			}
		}
		for(Triangle c : arr2){
			String key_compare = graph.keyTriangle(c.p1, c.p2, c.p3);
			if(!key_triangle.equals(key_compare)){
				ans[ans_pos] = c;
				ans_pos++;
			}
		}
		for(Triangle c : arr3){
			String key_compare = graph.keyTriangle(c.p1, c.p2, c.p3);
			if(!key_triangle.equals(key_compare)){
				ans[ans_pos] = c;
				ans_pos++;
			}
		}

		for(int k = 0; k < ans.length; k++){
			for(int j = k; j< ans.length; j++){
				int temp_k = ans[k].triangle_insertion_pos;
				int temp_j = ans[j].triangle_insertion_pos;
				if( temp_k > temp_j){
					Triangle temp = ans[k];
					ans[k] = ans[j];
					ans[j] = temp;
				}
			}
		}
		return ans;
	}

	public TriangleInterface [] INCIDENT_TRIANGLES(float [] point_coordinates){
		Point point1 = new Point(point_coordinates[0], point_coordinates[1], point_coordinates[2]);

		Triangle[] ans = graph.searchPoint(point1);
		if(ans == null){
			return null;
		}
		System.out.println("incident triangle inside");
		System.out.println("beta answer " + ans.length);
		for(int k = 0; k < ans.length; k++){
			int temp_k = ans[k].triangle_insertion_pos;
			for(int j = k; j< ans.length; j++){
				int temp_j = ans[j].triangle_insertion_pos;
				if( temp_k > temp_j){
					Triangle temp = ans[k];
					ans[k] = ans[j];
					ans[j] = temp;
				}
			}
		}
		return ans;
	}

	public PointInterface [] NEIGHBORS_OF_POINT(float [] point_coordinates){
		Point point1 = new Point(point_coordinates[0], point_coordinates[1], point_coordinates[2]);

		String key_point1 = graph.keyPoint(point1);
		// System.out.println("helo-------------------");
		RedBlackNode<String, Node<Point>> rbNode_p1 = graph.points.search(key_point1);
		// System.out.println("hello brohter");
		if(rbNode_p1 == null){
			return null;
		}

		Traversal traversal = new Traversal<Point>();

		ArrayList<Point> ans_arr_list = new ArrayList<Point>();

		ans_arr_list = traversal.RBTreeTraversal(rbNode_p1.value_arr.get(0).pointList.root);
		Point[] ans = new Point[ans_arr_list.size()];
		for(int i = 0; i<ans_arr_list.size(); i++){
			ans[i] = ans_arr_list.get(i);
		}
		return ans;
	}	

	public EdgeInterface [] EDGE_NEIGHBORS_OF_POINT(float [] point_coordinates){
		Point point1 = new Point(point_coordinates[0], point_coordinates[1], point_coordinates[2]);
		Traversal traversal = new Traversal<Point>();

		// ArrayList<Node<Point>> np = traversal.RBTreeTraversal(graph.points.root);
		// for(int i = 0; i < np.size(); i++){
		// 	System.out.println(graph.keyPoint(np.get(i).p));
		// }

		String key_point1 = graph.keyPoint(point1);
		// System.out.println("helo-------------------" + key_point1);
		RedBlackNode<String, Node<Point>> rbNode_p1 = graph.points.search(key_point1);
		// System.out.println("hello brohter");
		if(rbNode_p1 == null){
			return null;
		}


		ArrayList<Point> ans_arr_list = new ArrayList<Point>();

		ans_arr_list = traversal.RBTreeTraversal(rbNode_p1.value_arr.get(0).pointList.root);

		Point[] point_array = (Point[]) this.NEIGHBORS_OF_POINT(point_coordinates);
		System.out.println("je;;p brother");
		// if(point_array == null){
		// 	return null;
		// }
		Edge[] edge_ans = new Edge[ans_arr_list.size()];

		int i = 0;
		for(i = 0; i<ans_arr_list.size(); i++){
			Edge temp = new Edge(point1, ans_arr_list.get(i));
			edge_ans[i] = temp;
			
		}
		return edge_ans;
		// return null;
	}


	public TriangleInterface [] FACE_NEIGHBORS_OF_POINT(float [] point_coordinates){ 
		Point point1 = new Point(point_coordinates[0], point_coordinates[1], point_coordinates[2]);

		// String key_point1 = graph.keyPoint(point1);
		// Triangle[] ans = graph.searchPoint(point1);
		// System.out.println("helo");
		// if(ans == null){
		// 	return null;
		// }
		// for(int k = 0; k < ans.length; k++){
		// 	for(int j = k; j< ans.length; j++){
		// 		int temp_k = ans[k].triangle_insertion_pos;
		// 		int temp_j = ans[j].triangle_insertion_pos;
		// 		if( temp_k > temp_j){
		// 			Triangle temp = ans[k];
		// 			ans[k] = ans[j];
		// 			ans[j] = temp;
		// 		}
		// 	}
		// }


		// return ans;
		return null;
	}

	public boolean IS_CONNECTED(float [] triangle_coord_1, float [] triangle_coord_2){
		Point point1 = new Point(triangle_coord_1[0], triangle_coord_1[1], triangle_coord_1[2]);
		Point point2 = new Point(triangle_coord_1[3], triangle_coord_1[4], triangle_coord_1[5]);
		Point point3 = new Point(triangle_coord_1[6], triangle_coord_1[7], triangle_coord_1[8]);

		Point point4 = new Point(triangle_coord_2[0], triangle_coord_2[1], triangle_coord_2[2]);
		Point point5 = new Point(triangle_coord_2[3], triangle_coord_2[4], triangle_coord_2[5]);
		Point point6 = new Point(triangle_coord_2[6], triangle_coord_2[7], triangle_coord_2[8]);

		Triangle ta = new Triangle(point1, point2, point3);
		Triangle tb = new Triangle(point4, point5, point6);

		Edge edge1 = ta.e1;
		Edge edge2 = ta.e2;
		Edge edge3 = ta.e3;

		Edge edge4 = tb.e1;
		Edge edge5 = tb.e2;
		Edge edge6 = tb.e3;

		// RedBlackNode nodeEdge = graph.edges.search(edge1);
		return false;
	}


	public TriangleInterface [] TRIANGLE_NEIGHBOR_OF_EDGE(float [] edge_coordinates){ 
		if(edge_coordinates.length != 6) return  null;
		Point point1 = new Point(edge_coordinates[0], edge_coordinates[1], edge_coordinates[2]);
		Point point2 = new Point(edge_coordinates[3], edge_coordinates[4], edge_coordinates[5]);


		Edge edge1 = new Edge(point1, point2);

		String key_edge1 = graph.keyEdge(edge1);
		// String key_edge2 = graph.keyEdge(edge2);
		// String key_edge3 = graph.keyEdge(edge3);

		// String key_triangle = graph.keyTriangle(point1, point2, point3);
		RedBlackNode<String, Node<Edge>> node_t = graph.edges.search(key_edge1);
		if(node_t == null){
			return null;
		}

		Triangle[] ans = graph.searchEdge(edge1);
		// Triangle[] arr2 = graph.searchEdge(edge2);
		// Triangle[] arr3 = graph.searchEdge(edge3);
		if(ans == null){
			return null;
		}

		for(int k = 0; k < ans.length; k++){
			for(int j = k; j< ans.length; j++){
				int temp_k = ans[k].triangle_insertion_pos;
				int temp_j = ans[j].triangle_insertion_pos;
				if( temp_k > temp_j){
					Triangle temp = ans[k];
					ans[k] = ans[j];
					ans[j] = temp;
				}
			}
		}


		return ans;
	}

	public int MAXIMUM_DIAMETER(){
		
		return 0;
	}

	public PointInterface [] CENTROID (){
		Traversal<Triangle> traversal = new Traversal<Triangle>();
		ArrayList<Triangle> ans_arr_triangle = traversal.RBTreeTraversal(graph.triangle.root);
		System.out.println("inside CENTROID--------");
		Point[] ans = new Point[ans_arr_triangle.size()];
		for(int i = 0; i < ans_arr_triangle.size(); i++){
			Triangle temp = ans_arr_triangle.get(i);
			float x = temp.p1.getX() + temp.p2.getX() + temp.p3.getX();
			float y = temp.p1.getY() + temp.p2.getY() + temp.p3.getY();
			float z = temp.p1.getZ() + temp.p2.getZ() + temp.p3.getZ();
			Point point = new Point(x/3, y/3, z/3);
			ans[i] = point;
		}
		return ans;
	}

	public PointInterface CENTROID_OF_COMPONENT (float [] point_coordinates){
		Point point1 = new Point(point_coordinates[0], point_coordinates[1], point_coordinates[2]);
		Triangle[] triangle_temp = (Triangle[]) this.FACE_NEIGHBORS_OF_POINT(point_coordinates);
		if(triangle_temp == null){
			return null;
		}

		// Point[] ans = new Point[triangle_temp.length];
		float x = 0;
		float y = 0;
		float z = 0;
		for(int i = 0; i < triangle_temp.length; i++){
			Triangle temp = triangle_temp[i];
			x = x + temp.p1.getX() + temp.p2.getX() + temp.p3.getX();
			y = y +  temp.p1.getY() + temp.p2.getY() + temp.p3.getY();
			z = z + temp.p1.getZ() + temp.p2.getZ() + temp.p3.getZ();
		}
		Point point = new Point(x/3, y/3, z/3);
		return point;

	}

	public 	PointInterface [] CLOSEST_COMPONENTS(){
		return null;
	}

}

