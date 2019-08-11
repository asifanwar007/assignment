import java.io.*;
import java.util.Iterator;

public class Entity implements Entity_{
	String n;
	LinkedList<Student> student_List = new LinkedList<Student>();
	
	
	public Entity(String n){
		this.n = n;
		
	}
	public String name(){
		return n;
	}
	public Position<Student> getHeadEntity(){
		return student_List.head;
	}
	public void addData(Student a){
		student_List.add(a);
	}
	public Iterator<Student> studentList(){
		return new LinkedListIterator(student_List.head);
		
	}
}