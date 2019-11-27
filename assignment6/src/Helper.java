public class Helper{
	public float[] chageToCoPoint(Point p){
		float[] co = new float[3];
		float x = p.getX();
		float y = p.getY();
		float z = p.getZ();
		co[0] = x; co[1] = y; co[2] = z;
		return co;
	}
	public float[] changeToCoTr(Triangle t){
		float[] f1 = this.chageToCoPoint(t.p1);
		float[] f2 = this.chageToCoPoint(t.p2);
		float[] f3 = this.chageToCoPoint(t.p3);
		float[] ans = new float[9];
		int i = 0;

		for(float c:f1){
			ans[i] = c;
			i++;
		}for(float c:f2){
			ans[i] = c;
			i++;
		}for(float c:f3){
			ans[i] = c;
			i++;
		}
		return ans;

	}
	public Point[] changeToPoint(float[] triangle_coord){

		if(triangle_coord.length == 9){
			Point point1 = new Point(triangle_coord[0], triangle_coord[1], triangle_coord[2]);
			Point point2 = new Point(triangle_coord[3], triangle_coord[4], triangle_coord[5]);
			Point point3 = new Point(triangle_coord[6], triangle_coord[7], triangle_coord[8]);
			return new Point[]{point1, point2, point3};
		}
		if(triangle_coord.length == 6){
			Point point1 = new Point(triangle_coord[0], triangle_coord[1], triangle_coord[2]);
			Point point2 = new Point(triangle_coord[3], triangle_coord[4], triangle_coord[5]);
			return new Point[]{point1, point2};
		}
		Point point1 = new Point(triangle_coord[0], triangle_coord[1], triangle_coord[2]);
		return new Point[]{point1};

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
				if(e_l > e_j){
					Edge temp = e[i];
					e[i] = e[j];
					e[j] = temp;
				}
			}
		}
		return e;
	}

	public Triangle[] triangleSorting(Triangle[] ans){
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



}