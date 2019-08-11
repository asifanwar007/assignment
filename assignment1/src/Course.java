import java.util.Iterator;
import java.io.*;

public class Course extends Entity{
	String name;
	LinkedList<Student> studentList;
	public Course(String name){
		super(name);
	}
}