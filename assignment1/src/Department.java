import java.util.Iterator;
import java.io.*;

public class Department extends Entity{
	String name;
	
	LinkedList<Student> studentList;
	public Department(String name){
		super(name);
	}
}