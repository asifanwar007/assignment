import java.util.Iterator;
import java.io.*;

public class Student implements Student_{
	String name;
	String entryNo;
	String hostel;
	String department;
	String completedCredits;
	String cgpa;

	public Student(String name, String entryNo, String hostel, String department, String completedCredits, String cgpa){
		this.name = name;
		this.entryNo = entryNo;
		this.hostel = hostel;
		this.department = department;
		this.completedCredits = completedCredits;
		this.cgpa = cgpa;
	}
	public String name(){
		return name;
	}
	public String entryNo(){
		return entryNo;
	}
	public String hostel(){
		return hostel;
	}
	public String department(){
		return department;
	}
	public String completedCredits(){
		return completedCredits;
	}
	public String cgpa(){
		return cgpa;
	}
	
	public Iterator<CourseGrade> courseList(){
		
	}
	
}