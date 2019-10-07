public class Student implements Student_{
	// String fname;
	// String lname;
	Pair<String, String> name;
	String hostel;
	String department;
	String cgpa;
	public Student(Pair<String, String> name, String hostel, String department, String cgpa){
		this.name = name;
		this.hostel = hostel;
		this.department = department;
		this.cgpa =cgpa;
	}
	public String fname(){
		return this.name.get_fn();
	}
	public String lname(){
		return this.name.get_ln();
	}
	public String hostel(){
		return this.hostel;
	}
	public String department(){
		return this.department;
	}
	public String cgpa(){
		return this.cgpa;
	}
}