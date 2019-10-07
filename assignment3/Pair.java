public class Pair<K, T>{
	K first_name;
	T last_name;

	public Pair(K first_name, T last_name){
		this.first_name = first_name;
		this.last_name = last_name;
	}
	public K get_fn(){
		return this.first_name;
	}
	public T get_ln(){
		return this.last_name;
	}
	public String toString(){
		return this.first_name + " " + this.last_name;
	}
	// public static void main(String[] args) {
	// 	Pair p = new Pair("Asif", "Anwar");
	// 	System.out.println(p.get_ln());
	// 	System.out.println(p.toString());
	// }

}