import java.util.Iterator;
import java.io.*;


public class Assignment1 {	
	static LinkedList<String[]> courses_txt_input = new LinkedList<String[]>();
	static LinkedList<String[]> stuednts_txt_input = new LinkedList<String[]>();
	private static void getData(String studentRecordFile, String courseFile){
		try {
			File file = new File(studentRecordFile);
				BufferedReader br = new BufferedReader(new FileReader(file));
				String st;
				String[] store_value;
				while ((st = br.readLine()) != null){
					// System.out.println(st);
					store_value = st.split(" ", 4);
					stuednts_txt_input.add(store_value);			
				}
			} catch(Exception e){
				System.out.println(e);}
		System.out.println("-----------------------------------------------------");
		try {
			File file = new File(courseFile);
				BufferedReader br = new BufferedReader(new FileReader(file));
				String st;
				String[] store_value;	
				while ((st = br.readLine()) != null){
					// System.out.println(st);
					store_value = st.split(" ", 4);
					courses_txt_input.add(store_value);					
				}
			} catch(Exception e){
				System.out.println(e);}	

	//this is how acces the data
	System.out.println(courses_txt_input.positions().next().value()[0]);

	}


	static int number_of_query = 0;
	private static void answerQueries(String studentQueryFile){
		LinkedList<String[]> query_txt_input = new LinkedList<String[]>();
		try {
			File file = new File(studentQueryFile);
				BufferedReader br = new BufferedReader(new FileReader(file));
				String st;
				String[] store_value;
				while ((st = br.readLine()) != null){
					// System.out.println(st);
					store_value = st.split(" ", 4);
					query_txt_input.add(store_value);
					number_of_query++;
				}
			} catch(Exception e){
				System.out.println(e);}

		String[][] queries_to_solve = new String[number_of_query][3];
		for(int i = 0; i < number_of_query; i++){
			queries_to_solve[number_of_query-i-1] = query_txt_input.positions().next().value();
		}

		



		//adding studtnst dalata in linked list inside answer queries
		LinkedList<Student> allStudents = new LinkedList<Student>();
		while (stuednts_txt_input.positions().hasNext()){

			//constructor for students class
			String name = stuednts_txt_input.positions().next().value()[1];
			String entry_no = stuednts_txt_input.positions().next().value()[0];
			String departmetn = stuednts_txt_input.positions().next().value()[2];
			String hostel = stuednts_txt_input.positions().next().value()[3];

			//adding courseGrade data from courses then appending it to student linked list
			CourseGrade_ll courseGrade_ll = new CourseGrade_ll(courses_txt_input, entry_no);
			//calculation of completed credits
			String completed_credits_cal = coursegrade_ll.count()*3 - 3*I_grade_counter;
			String completed_credits = completed_credits_cal; 

			//calculation for cgpa of that students


			String cGpA = (gradepoint*3)/(coursegrade_ll.count()*3 - 3*I_grade_counter);
			Student s1 = new Student(name, entry_no, departmetn, hostel, completed_credits, cGpA);
			allStudents.add(s1);
			stuednts_txt_input.positions().next();
				
		}
		System.out.println("inside answerQueries: " + number_of_query);
		System.out.println(courses_txt_input.positions().next().value()[0]);
	}


	public static void main(String[] args) {


		Assignment1 assign = new Assignment1();
		assign.getData("students.txt","courses.txt");
		assign.answerQueries("query.txt");		
	}
}