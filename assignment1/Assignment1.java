import java.util.Iterator;
import java.io.*;
import java.util.*;

public class Assignment1 {


	// String studentRecordFile; //contains personal data about the students
	// String courseFile; //contains the list of courses taken by students
	// String studentQueryFile; //contains some queries to process


	/**

	 getData reads information per student from 
	the specified student record file and course file, inserting 
	the information in the respective 
	linked lists (e.g., allHostels, allDepartments, allCourses), updating all data structures.
	*/
	private static void getData(String studentRecordFile, String courseFile){
	LinkedList<String[]> courses_txt_input = new LinkedList<String[]>();
	try {
		File file = new File(studentRecordFile);
			BufferedReader br = new BufferedReader(new FileReader(file));
			String st;
			String[] store_value = new String[10];	
			while ((st = br.readLine()) != null){
				// System.out.println(st);
				store_value = st.split(" ");
				System.out.println(Arrays.toString(store_value));
				
			}
		} catch(Exception e){
			System.out.println(e);
	}


	}
	
	/*
	 getData reads information per student from the specified student 
	record file and course file, inserting the 
	information in the respective 
	linked lists (e.g., allHostels, allDepartments, allCourses), updating all data structures.
	*/
	private static void answerQueries(String studentQueryFile){


	}
	public static void main(String[] args) {


		Assignment1 get = new Assignment1();
		get.getData("hello.txt","asif");

		// try{
		// 	FileInputStream fo = new FileInputStream("hello.txt");
		// 	int i = 0;
		// 	// i = fo.read();
		// 	// System.out.println(i);
		// 	while((i=fo.read())!= -1){
		// 		// System.out.print((char)i + "i");
		// 		// System.out.println("print inside while loop");
		// 	}
		// 	fo.close();
		// } catch(Exception e){
		// 	System.out.println(e);
		// } 

		// try {
		// 	File file = new File("hello.txt");
		// 	BufferedReader br = new BufferedReader(new FileReader(file));
		// 	String st;
		// 	while ((st = br.readLine()) != null){
		// 		System.out.println(st);
		// 	} 
		// }catch(Exception e){
		// 		System.out.println(e + " file not able to read");
		// 	}
		

		// course extends entity;
		// LinkedList<Entity> allCourses = new LinkedList<Entity>;
		// public LinkedList<Entity>


		
	}
}