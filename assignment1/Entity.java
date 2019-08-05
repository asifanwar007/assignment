import java.io.*;
import java.util.Iterator;

public class Entity implements Entity_{
	String n;
	LinkedList<Student> student_List;
	
	public Entity(String n){
		this.n = n;
	}
	public String name(){
		return n;
	}
	public Iterator<Student> studentList(){
		return new LinkedListIterator(student_List.head);
		
	}
}