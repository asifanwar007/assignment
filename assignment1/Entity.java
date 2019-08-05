import java.io.*;
import java.util.Iterator;

public class Entity implements Entity_{
	String n;
	Iterator<Student> s;
	LinkedList<Student> student_List;
	
	public Entity(String n, Iterator<Student> s){
		this.n = n;
		this.s = s;
	}
	public String name(){
		return n;
	}
	public Iterator<Student> studentList(){
		return new LinkedListIterator(student_List.head);
		
	}
}