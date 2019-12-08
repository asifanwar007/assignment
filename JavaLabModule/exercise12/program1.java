public class program1{
	int a;
	int b;
	public program1(int a, int b){
		this.a = a;
		this.b = b;
	}
	public int lcm(){
		if (a > b){
			return a;
		} else {
			return b;
		}
	}
	public static void main(String[] args) {
		program1 l = new program1(12, 17);
		System.out.println(l.lcm());
	}
}