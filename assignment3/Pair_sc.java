//for sc use this pair fuction
public class Pair_sc{
	String first_name;
	String last_name;

	public Pair_sc(String first_name, String last_name){
		this.first_name = first_name;
		this.last_name = last_name;
	}
	public String get_fn(){
		return this.first_name;
	}
	public String get_ln(){
		return this.last_name;
	}
	public String toString(){
		return this.first_name + " " + this.last_name;
	}
}