public class Shape implements ShapeInterface{
	public Graph graph;
	public Helper helper;
	public int triangle_insert_pos = 0;
	public boolean boundary_edges_calculated = false;
	public boolean proper_mesh = false;
	public boolean count_connected_calculaed = false;

	public Shape(){
		this.graph = new Graph();
		this.helper = new Helper();
	}

	public boolean ADD_TRIANGLE(float [] triangle_coord){
		Point[] point_arr = helper.changeToPoint(triangle_coord);
		Triangle t = new Triangle(point_arr[0], point_arr[1], point_arr[2]);
		Triangle check_t = graph.isTriangleAvai(t);
		if(check_t != null){
			return false;
		}
		float row1 = (t.p2.getY()-t.p1.getY())*(t.p3.getZ()- t.p1.getZ())
						- (t.p3.getY()-t.p1.getY())*(t.p2.getZ() - t.p1.getZ());
		float row2 = (t.p3.getX()-t.p1.getX())*(t.p2.getZ()- t.p1.getZ())
						- (t.p2.getX()-t.p1.getX())*(t.p3.getZ() - t.p1.getZ());				
		float row3 = (t.p3.getY()-t.p1.getY())*(t.p2.getX()- t.p1.getX())
						- (t.p2.getY()-t.p1.getY())*(t.p3.getX() - t.p1.getX());
		if(row1 == 0.0 && row2 == 0.0 && row3 == 0.0 ){
			return false;
		}
		t.triangle_insertion_pos = triangle_insert_pos;
		triangle_insert_pos++;

		graph.addTriangle(t);
		count_connected_calculaed = false;
		return true;
	}
	public int TYPE_MESH(){
		if(graph.improper_mesh == true){
			return 3;
		}
		Edge[] temp = (Edge[]) this.BOUNDARY_EDGES();
		if(temp == null ){
			return 1;
		}
		return 2;
	}
	public EdgeInterface [] BOUNDARY_EDGES(){
		boundary_edges_calculated = true;
		ArrayList<Edge> ans = new ArrayList<Edge>();
		for(int i = 0; i < graph.edges.size(); i++){
			Node<Edge> temp = (Node<Edge>) graph.edges.get(i);
			if(temp.trianglePoint.size() < 2){
				ans.add(temp.p);
			}
		}
		if(ans.size() ==0){
			proper_mesh = true;
			return null;
		}
		Edge[] ans_edge = new Edge[ans.size()];
		for(int i = 0; i<ans.size(); i++){
			ans_edge[i] = ans.get(i);
		}
		ans_edge = this.helper.edgeSorting(ans_edge);
		return ans_edge;
	}

	public ArrayList<ArrayList<Triangle>> connected_comp = new ArrayList<ArrayList<Triangle>>();
	public int COUNT_CONNECTED_COMPONENTS(){
		count_connected_calculaed = true;
		int ans = 0;
		ArrayList<ArrayList<Triangle>> diff_t = new ArrayList<ArrayList<Triangle>>();
		boolean[] tr_v = new boolean[graph.triangle.size()];

		for(int i = 0; i<graph.triangle.size(); i++){
			Triangle ta_t =(Triangle) graph.triangle.get(i);
			if(!tr_v[i]){
				ArrayList<Triangle> toPop = new ArrayList<Triangle>();
				toPop.add(ta_t);
				// tr_v[i] = true;
				ArrayList<Triangle> comp = new ArrayList<Triangle>();
				while(toPop.size() > 0){

					Triangle ta = toPop.pop();
					if(!tr_v[ta.triangle_insertion_pos]){
						// checked_tr.add(ta);
						tr_v[ta.triangle_insertion_pos] = true;
						float[] f_ta = helper.changeToCoTr(ta);
						Triangle[] edge_tr = (Triangle[]) this.NEIGHBORS_OF_TRIANGLE(f_ta);
						comp.add(ta);
						if(edge_tr == null){
							continue;
						}else{
							for(Triangle c: edge_tr){
								if(!tr_v[c.triangle_insertion_pos])
									toPop.add(c);
							}
							
						}

					}
					
				}
				connected_comp.add(comp);
				ans++;

			}
		}

		return ans;
	}
	public TriangleInterface [] NEIGHBORS_OF_TRIANGLE(float [] triangle_coord){
		Point[] point_arr = helper.changeToPoint(triangle_coord);
		Triangle t = new Triangle(point_arr[0], point_arr[1], point_arr[2]);
		Triangle check_t = graph.isTriangleAvai(t);
		if(check_t == null){
			return null;
		}
		// Triangle[] arr1 = this.TRIANGLE_NEIGHBOR_OF_EDGE(triangle_coord[0], triangle_coord[1], triangle_coord[2]);
		// Triangle[] arr2 = this.TRIANGLE_NEIGHBOR_OF_EDGE(triangle_coord[3], triangle_coord[4], triangle_coord[5]);
		// Triangle[] arr3 = this.TRIANGLE_NEIGHBOR_OF_EDGE(triangle_coord[6], triangle_coord[7], triangle_coord[8]);
		 ArrayList<Triangle> arr1 = graph.searchEdge(t.e1).trianglePoint;
		 ArrayList<Triangle> arr2 = graph.searchEdge(t.e2).trianglePoint;
		 ArrayList<Triangle> arr3 = graph.searchEdge(t.e3).trianglePoint;

		 ArrayList<Triangle> ans = new ArrayList<Triangle>();
		 String key_triangle = graph.keyTriangle(t);
		 for(int i = 0; i<arr1.size(); i++){
		 	Triangle c = arr1.get(i);
			String key_compare = graph.keyTriangle(c);
			if(!key_triangle.equals(key_compare)){
				ans.add(c);
			}
		}
		for(int i = 0; i<arr2.size(); i++){
		 	Triangle c = arr2.get(i);
			String key_compare = graph.keyTriangle(c);
			if(!key_triangle.equals(key_compare)){
				ans.add(c);
			}
		}
		for(int i = 0; i<arr3.size(); i++){
		 	Triangle c = arr3.get(i);
			String key_compare = graph.keyTriangle(c);
			if(!key_triangle.equals(key_compare)){
				ans.add(c);
			}
		}
		Triangle[] ans_tri = new Triangle[ans.size()];
		for(int i = 0; i<ans.size(); i++){
			ans_tri[i] = ans.get(i);
		}
		ans_tri = helper.triangleSorting(ans_tri);
		return ans_tri;

	}
	public EdgeInterface [] EDGE_NEIGHBOR_TRIANGLE(float [] triangle_coord){
		Point[] point_arr = helper.changeToPoint(triangle_coord);
		Triangle t = new Triangle(point_arr[0], point_arr[1], point_arr[2]);
		Triangle check_t = graph.isTriangleAvai(t);
		if(check_t == null){
			return null;
		}
		// ArrayList<Edge> arr1 = graph.searchEdge(t.e1).pointList;
		// ArrayList<Edge> arr2 = graph.searchEdge(t.e2).pointList;
		// ArrayList<Edge> arr3 = graph.searchEdge(t.e3).pointList;
		// ArrayList<Edge> ans = new ArrayList<Edge>();
		// for(int i = 0; i<arr1.size(); i++)
		// 	ans.add(arr1.get(i));
		// for(int i = 0; i<arr2.size(); i++)
		// 	ans.add(arr2.get(i));
		// for(int i = 0; i<arr3.size(); i++)
		// 	ans.add(arr3.get(i));

		// String key_edge1 = graph.keyEdge(t.e1);
		// String key_edge2 = graph.keyEdge(t.e2);
		// String key_edge3 = graph.keyEdge(t.e3);

		// ArrayList<Edge> ans_edge = new ArrayList<Edge>();
		// for(int i = 0; i<ans.size(); i++){
		// 	Edge c= ans.get(i);
		// 	String compare_edge1 = graph.keyEdge(c);
		// 	// String compare_edge2 = graph.keyEdge(c.e2);
		// 	// String compare_edge3 = graph.keyEdge(c.e3);
		// 	if(!compare_edge1.equals(key_edge1) || !compare_edge1.equals(key_edge2) || !compare_edge1.equals(key_edge3))
		// 		ans_edge.add(c);
		// 	// if(!compare_edge2.equals(key_edge1) || !compare_edge2.equals(key_edge2) || !compare_edge2.equals(key_edge3))
		// 	// 	ans_edge.add(c.e2);
		// 	// if(!compare_edge3.equals(key_edge1) || !compare_edge3.equals(key_edge2) || !compare_edge3.equals(key_edge3))
		// 	// 	ans_edge.add(c.e3);
		// }
		// Edge[] temp = new Edge[ans_edge.size()];
		// for(int i = 0; i<ans_edge.size(); i++){
		// 	temp[i] = ans_edge.get(i);
		// }
		Edge[] temp = new Edge[]{t.e1, t.e2, t.e3};
		return temp;
	}

	public PointInterface [] VERTEX_NEIGHBOR_TRIANGLE(float [] triangle_coord){
		Point[] point_arr = helper.changeToPoint(triangle_coord);
		Triangle t = new Triangle(point_arr[0], point_arr[1], point_arr[2]);
		Triangle check_t = graph.isTriangleAvai(t);
		if(check_t == null){
			return null;
		}
		// ArrayList<Point> arr1 = graph.searchPoint(t.p1).pointList;
		// ArrayList<Point> arr2 = graph.searchPoint(t.p2).pointList;
		// ArrayList<Point> arr3 = graph.searchPoint(t.p3).pointList;
		// ArrayList<Point> ans = new ArrayList<Point>();
		// for(int i = 0; i<arr1.size(); i++)
		// 	ans.add(arr1.get(i));
		// for(int i = 0; i<arr2.size(); i++)
		// 	ans.add(arr2.get(i));
		// for(int i = 0; i<arr3.size(); i++)
		// 	ans.add(arr3.get(i));

		// ArrayList<Point> ans_arr_list = new ArrayList<Point>();
		// boolean toAdd = true;
		// for(int i = 0; i < ans.size(); i++){
		// 	String ans_arr_list1Key = graph.keyPoint(ans.get(i));
		// 	for(int j = 0; j<ans_arr_list.size(); j++){
		// 		String ans_arr_listKey = graph.keyPoint(ans_arr_list.get(j));
		// 		if(ans_arr_listKey.equals(ans_arr_list1Key)){
		// 			toAdd = false;
		// 		}
		// 	}
		// 	if(toAdd)
		// 		ans_arr_list.add(ans.get(i));
		// }
		// Point[] ans_temp = new Point[ans_arr_list.size()];
		// for(int i = 0; i<ans_arr_list.size(); i++){
		// 	ans_temp[i] = ans_arr_list.get(i);
		// }
		Point[] ans_temp = new Point[]{t.p1, t.p2, t.p3};
		return ans_temp;
	}

	public TriangleInterface [] EXTENDED_NEIGHBOR_TRIANGLE(float [] triangle_coord){
		Point[] point_arr = helper.changeToPoint(triangle_coord);
		Triangle t = new Triangle(point_arr[0], point_arr[1], point_arr[2]);
		Triangle check_t = graph.isTriangleAvai(t);
		if(check_t == null){
			return null;
		}
		ArrayList<Triangle> arr1 = graph.searchPoint(t.p1).trianglePoint;
		ArrayList<Triangle> arr2 = graph.searchPoint(t.p2).trianglePoint;
		ArrayList<Triangle> arr3 = graph.searchPoint(t.p3).trianglePoint;
		ArrayList<Triangle> ans = new ArrayList<Triangle>();
		for(int i = 0; i<arr1.size(); i++)
			ans.add(arr1.get(i));
		for(int i = 0; i<arr2.size(); i++)
			ans.add(arr2.get(i));
		for(int i = 0; i<arr3.size(); i++)
			ans.add(arr3.get(i));

		String key_triangle = graph.keyTriangle(t);
		ArrayList<Triangle> ans_new = new ArrayList<Triangle>();
		for(int i = 0; i<ans.size(); i++){
		 	Triangle c = ans.get(i);
			String key_compare = graph.keyTriangle(c);
			if(!key_triangle.equals(key_compare)){
				ans_new.add(c);
			}
		}
		Triangle[] ans_tri = new Triangle[ans_new.size()];
		for(int i = 0; i<ans_new.size(); i++){
			ans_tri[i] = ans_new.get(i);
		}
		ans_tri = helper.triangleSorting(ans_tri);
		return ans_tri;
	}
	public TriangleInterface [] INCIDENT_TRIANGLES(float [] point_coordinates){
		Point[] point_arr = helper.changeToPoint(point_coordinates);
		Point point = point_arr[0];
		Node<Point> check_t = graph.searchPoint(point);
		if(check_t == null){
			return null;
		}
		ArrayList<Triangle> arr = check_t.trianglePoint;
		Triangle[] ans = new Triangle[arr.size()];
		for(int i = 0; i < arr.size(); i++){
			ans[i] = arr.get(i);
		}

		ans = helper.triangleSorting(ans);
		return ans;
	}
	public PointInterface [] NEIGHBORS_OF_POINT(float [] point_coordinates){
		Point[] point_arr = helper.changeToPoint(point_coordinates);
		Point point = point_arr[0];
		Node<Point> check_t = graph.searchPoint(point);
		if(check_t == null){
			return null;
		}
		ArrayList<Point> arr = check_t.pointList;
		Point[] ans = new Point[arr.size()];
		for(int i = 0; i<arr.size(); i++){
			ans[i] = arr.get(i);
		}
		return ans;
	}
	public EdgeInterface [] EDGE_NEIGHBORS_OF_POINT(float [] point_coordinates){
		Point[] point_arr = helper.changeToPoint(point_coordinates);
		Point point = point_arr[0];
		Node<Point> check_t = graph.searchPoint(point);
		if(check_t == null){
			return null;
		}
		ArrayList<Point> arr = check_t.pointList;
		Edge[] ans = new Edge[arr.size()];
		for(int i = 0; i<arr.size(); i++){
			Edge temp = new Edge(point, arr.get(i));
			ans[i] = temp;
		}
		return ans;
	}
	public TriangleInterface [] FACE_NEIGHBORS_OF_POINT(float [] point_coordinates){ 
		Point[] point_arr = helper.changeToPoint(point_coordinates);
		Point point = point_arr[0];
		Node<Point> check_t = graph.searchPoint(point);
		if(check_t == null){
			return null;
		}
		ArrayList<Triangle> arr = check_t.trianglePoint;
		Triangle[] ans = new Triangle[arr.size()];
		for(int i = 0; i < arr.size(); i++){
			ans[i] = arr.get(i);
		}

		ans = helper.triangleSorting(ans);
		return ans;
		
	}

	//iconneceted helopere file
	public boolean checkTr(Triangle t, Triangle[] arr){
		for(int i = 0; i < arr.length; i++){
			if(graph.keyTriangle(arr[i]).equals(graph.keyTriangle(t))){
				return true;
			}
		}
		return false;
	}

	
	public boolean IS_CONNECTED(float [] triangle_coord_1, float [] triangle_coord_2){
		Point[] point_arr1 = helper.changeToPoint(triangle_coord_1);
		Point[] point_arr2 = helper.changeToPoint(triangle_coord_2);
		Triangle t1 = new Triangle(point_arr1[0], point_arr1[1], point_arr1[2]);
		Triangle t2 = new Triangle(point_arr2[0], point_arr2[1], point_arr2[2]);

		Triangle check_t1 = graph.isTriangleAvai(t1);
		Triangle check_t2 = graph.isTriangleAvai(t1);
		if(check_t1 == null || check_t2 == null)
			return false;

		Triangle[] edge_tr1 = (Triangle[])this.NEIGHBORS_OF_TRIANGLE(triangle_coord_1);
		Triangle[] edge_tr2 = (Triangle[])this.NEIGHBORS_OF_TRIANGLE(triangle_coord_2);

		if(edge_tr1 == null || edge_tr2 == null)
			return false;
		
		ArrayList<Triangle> toPop = new ArrayList<Triangle>();
		for(Triangle c : edge_tr2)
			toPop.add(c);

		ArrayList<Triangle> checked_tr = new ArrayList<Triangle>();


		if(this.checkTr(t1, edge_tr2)){
			return true;
		}
		while(toPop.size() > 0){

			Triangle ta = toPop.pop();
			if(!this.checkTrList(ta, checked_tr)){
				checked_tr.add(ta);
				float[] f_ta = helper.changeToCoTr(ta);
				Triangle[] edge_tr = (Triangle[]) this.NEIGHBORS_OF_TRIANGLE(f_ta);
				if(edge_tr == null){
					continue;
				}else{
					if(this.checkTr(t1, edge_tr)){
						return true;
					}
					for(Triangle c: edge_tr)
						toPop.add(c);
					
				}

			}
			
		}

		return false;
	}

	public TriangleInterface [] TRIANGLE_NEIGHBOR_OF_EDGE(float [] edge_coordinates){ 
		Point[] point_arr = helper.changeToPoint(edge_coordinates);
		Edge edge = new Edge(point_arr[0], point_arr[1]);
		ArrayList<Triangle> arr = graph.searchEdge(edge).trianglePoint;
		if(arr == null){
			return null;
		}
		Triangle[] ans = new Triangle[arr.size()];

		for(int i = 0; i< arr.size(); i++){
			ans[i] = arr.get(i);
		}
		ans = helper.triangleSorting(ans);
		return ans;
	}

	public boolean checkTrList(Triangle t, ArrayList<Triangle> arr){
		for(int i = 0; i < arr.size(); i++){
			if(graph.keyTriangle(arr.get(i)).equals(graph.keyTriangle(t))){
				return true;
			}
		}
		return false;
	}


	public int MAXIMUM_DIAMETER(){
		if(!count_connected_calculaed)
			this.COUNT_CONNECTED_COMPONENTS();
		 // float close_d = 0.0;
		int ans = 0;
		for(int i = 0; i < connected_comp.size(); i++){
			 ArrayList<Triangle> t_arr  = connected_comp.get(i);
			 if(t_arr.size() <= 1){
				continue;			 	
			 } else{

			 	for(int j = 0; j<t_arr.size(); j++){
				 	int count = 0;
			 		// t_ans = 0;
			 		Triangle ta_t =(Triangle) t_arr.get(j);

				 	// ArrayList<Triangle> checked_tr = new ArrayList<Triangle>();
			 		// if(count ){
					 	float[] triangle_coord_2 = helper.changeToCoTr(ta_t);
					 	Triangle[] edge_tr2 = (Triangle[])this.NEIGHBORS_OF_TRIANGLE(triangle_coord_2);

					 	ArrayList<Triangle> toPop = new ArrayList<Triangle>();
			 			toPop.add(ta_t);
					 	for(Triangle c : edge_tr2)
					 		toPop.add(c);

			 			// tr_v[i] = true;
			 			while(toPop.size() > 0){

			 				Triangle ta = toPop.popQueue();
			 				if(ta.max_dia_count > count){
			 					// checked_tr.add(ta);
			 					// tr_v[ta.triangle_insertion_pos] = true;

			 					float[] f_ta = helper.changeToCoTr(ta);
			 					Triangle[] edge_tr = (Triangle[]) this.NEIGHBORS_OF_TRIANGLE(f_ta);
			 					// comp.add(ta);
			 					if(edge_tr == null){
			 						continue;
			 					}else{
			 						for(Triangle c: edge_tr){
			 							if(c.max_dia_count > count){
			 								c.max_dia_count = count;
			 								toPop.add(c);
			 							}
			 						}
			 								count++;
			 						
			 					}

			 				}
			 				
			 			}
			 			if(ans < count){
			 				ans = count;
			 			}
		 		}
			 }


		}

		return ans;
	}

	public PointInterface [] CENTROID (){
		if(!count_connected_calculaed)
			this.COUNT_CONNECTED_COMPONENTS();

		// ArrayList<Triangle> ans_arr_triangle = graph.triangle;
		// Point[] ans = new Point[ans_arr_triangle.size()];
		// for(int i = 0; i < ans_arr_triangle.size(); i++){
		// 	Triangle temp = ans_arr_triangle.get(i);
		// 	float x = temp.p1.getX() + temp.p2.getX() + temp.p3.getX();
		// 	float y = temp.p1.getY() + temp.p2.getY() + temp.p3.getY();
		// 	float z = temp.p1.getZ() + temp.p2.getZ() + temp.p3.getZ();
		// 	Point point = new Point(x/3, y/3, z/3);
		// 	ans[i] = point;
		// }
		int ans_index = 0;
	 	Point[] ans = new Point[connected_comp.size()];
		for(int i = 0; i < connected_comp.size(); i++){
			 ArrayList<Triangle> t  = connected_comp.get(i);
		 	float x = 0; float y = 0; float z = 0;
			 for(int j = 0; j<t.size(); j++){
		 		Triangle temp = t.get(j);
		 		x = x + (temp.p1.getX() + temp.p2.getX() + temp.p3.getX())/3;
		 		y = y + (temp.p1.getY() + temp.p2.getY() + temp.p3.getY())/3;
		 		z = z + (temp.p1.getZ() + temp.p2.getZ() + temp.p3.getZ())/3;
		 	}
	 		Point point1 = new Point(x, y, z);
	 		ans[i] = point1; 
		}
		ans = this.centroidSorting(ans);
		return ans;
	}
	public Point[] centroidSorting(Point[] point){
		for(int i = 0; i < point.length; i++){
			Point p1 = point[i];
			float x1 = p1.getX();
			float y1 = p1.getY();
			float z1 = p1.getZ();
			for(int j = i; j<point.length; j++){
				Point p2 = point[j];
				float x2 = p2.getX();
				float y2 = p2.getY();
				float z2 = p2.getZ();

				if(x2 > x1){
					point[j] = p1;
					point[i] = p2;
				} else if(x2 < x1){
					continue;
				}else if(y2 > y1){
					point[j] = p1;
					point[i] = p2;
				} else if(y2 < y1){
					continue;
				}else if(z2 > z1){
					point[j] = p1;
					point[i] = p2;
				} 
			}
		}
		return point;
	}

	//SEARCH POINT IN A COMPONENT
	public boolean searchPointComp(ArrayList<Triangle> arr, Point p){
		String key_point = graph.keyPoint(p);
		for(int i = 0; i < arr.size(); i++){
			Triangle temp = arr.get(i);
			if(key_point.equals(graph.keyPoint(temp.p1)) ||key_point.equals(graph.keyPoint(temp.p2)) ||key_point.equals(graph.keyPoint(temp.p3))){
				return true;
			}
		}
		return false;
	}
	public PointInterface CENTROID_OF_COMPONENT (float [] point_coordinates){
		Point[] point_arr = helper.changeToPoint(point_coordinates);
		Point point = point_arr[0];
		Node<Point> check_t = graph.searchPoint(point);
		if(check_t == null){
			return null;
		}
		if(!count_connected_calculaed)
			this.COUNT_CONNECTED_COMPONENTS();

		int ans_index = 0;
		for(int i = 0; i < connected_comp.size(); i++){
			 ArrayList<Triangle> t  = connected_comp.get(i);
			 if(searchPointComp(t, point)){
			 	ans_index = i;
			 	break;
			 }
		}
		float x = 0; float y = 0; float z = 0;
		Point[] ans = new Point[connected_comp.get(ans_index).size()];
		for(int i = 0; i < connected_comp.get(ans_index).size(); i++){
			Triangle temp = connected_comp.get(ans_index).get(i);
			x = x + (temp.p1.getX() + temp.p2.getX() + temp.p3.getX())/3;
			y = y+ (temp.p1.getY() + temp.p2.getY() + temp.p3.getY())/3;
			z = z + (temp.p1.getZ() + temp.p2.getZ() + temp.p3.getZ())/3;
		}
			Point point1 = new Point(x, y, z);
		return point1;
	}

	public 	PointInterface [] CLOSEST_COMPONENTS(){
		if(!count_connected_calculaed)
			this.COUNT_CONNECTED_COMPONENTS();

		 Point[] close_d = new Point[]{new Point(0, 0, 0), new Point(0, 0, 0)};
		for(int i = 0; i < connected_comp.size()-1; i++){
			 ArrayList<Triangle> ta  = connected_comp.get(i);
			 ArrayList<Point> pa = closesetComponentPoint(ta);
			 // close_d[0] = pa.get(0);
			 // close_d[1] = pa.get(0);
			 for(int j = i+1; j<connected_comp.size(); j++){
				 ArrayList<Triangle> tb = connected_comp.get(j);
				 ArrayList<Point> pb = closesetComponentPoint(tb);

				 close_d = closeDistance(pa, pb, close_d);
				 // if(close_d == 0.0){
				 // 	b
				 // }

			 }

			 
		}
		return close_d;
	}
	public Point[] closeDistance(ArrayList<Point> pa, ArrayList<Point> pb, Point[] poi){
		// if(poi[0] != null)
		Point po1 = poi[0];
		Point po2 = poi[1];

		float d = (po1.getX()-po2.getX())*(po1.getX()-po2.getX()) + 
						(po1.getY()-po2.getY())*(po1.getY()-po2.getY()) +
						(po1.getZ()-po2.getZ())*(po1.getZ()-po2.getZ());
		for(int i = 0; i<pa.size(); i++){
			Point point1 = pa.get(i);
			for(int j = 0; j<pb.size(); j++){
				Point point2 = pb.get(j);
				float e_l = (point1.getX()-point2.getX())*(point1.getX()-point2.getX()) + 
						(point1.getY()-point2.getY())*(point1.getY()-point2.getY()) +
						(point1.getZ()-point2.getZ())*(point1.getZ()-point2.getZ());
				if(d > e_l){
					d = e_l;
					poi = new Point[]{point1, point2};
				}

			}

		}
		return poi;
	}
	public ArrayList<Point> closesetComponentPoint(ArrayList<Triangle> arr_ta){
		ArrayList<Point> point_ta = new ArrayList<Point>();
		for(int i = 0; i<arr_ta.size(); i++){
			Triangle t = arr_ta.get(i);
			if(graph.isPointAvai(point_ta, t.p1) == null){
				point_ta.add(t.p1);
			}if(graph.isPointAvai(point_ta, t.p2) == null){
				point_ta.add(t.p2);
			}if(graph.isPointAvai(point_ta, t.p3) == null){
				point_ta.add(t.p3);
			}
		}
		return point_ta;
	}


}

