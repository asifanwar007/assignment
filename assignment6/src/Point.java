public class Point implements PointInterface{
	float x;
	float y;
	float z;

	public Point(float x, float y, float z){
		this.x = x;
		this.y = y;
		this.z = z;
	}

    // public String printPoint(){
    //     return x + " " + y + " " + z;
    // }
    // public String toString(){
    //     return x + " " + y + " " + z;
    // }

    public float getX(){
    	return x;
    }
    public float getY(){
    	return y;
    }
    public float getZ(){
    	return z;
    }

	//[x,y,z]  3 dimensions first is x second y and third is z.
	// This order should be followed

    public float [] getXYZcoordinate(){
    	return new float[]{x,y,z};
    }
    public int compareTo(Point p){
        if(this.x > p.x && this.y>p.y && this.z > p.z){
            return 1;
        }
        return -1;
    }
}