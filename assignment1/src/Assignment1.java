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
	// System.out.println(courses_txt_input.positions().next().value()[0]);
		// Position<String[]> ch = courses_txt_input.head;
		// while(ch.after() != null){

		// 	System.out.println(ch.value()[0] + " " + ch.value()[1] + " "+ ch.value()[2] + " " + ch.value()[0] );
		// 	ch = ch.after();
		// }
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
					store_value = st.split(" ");
					query_txt_input.add(store_value);
					number_of_query++;
				}
			} catch(Exception e){
				System.out.println(e);}

		String[][] queries_to_solve = new String[number_of_query][3];
		Position<String[]> query_txt_head = query_txt_input.head;
		for(int i = 0; i < number_of_query; i++){
			queries_to_solve[number_of_query-i-1] = query_txt_head.value();
			query_txt_head = query_txt_head.after();
		}
		// for(int i = 0; i < number_of_query; i++){
		// 	System.out.println(queries_to_solve[i][0] + " " + queries_to_solve[i][1]);
		// }
		// System.out.println("-----------------------------------------------------");


		allStudents_ll allStudentsClass = new allStudents_ll(stuednts_txt_input, courses_txt_input);
		LinkedList<Student> all_students = allStudentsClass.getAllStudents_ll();
		Position<Student> student_head = all_students.head;
		// for(int i= 0; i < 15; i++){
		// 	System.out.println(student_head.value().cgpa());
		// 	Position<CourseGrade> nerva = student_head.value().getHeadEntity();
			
		// 	while(nerva.after() != null){

				
		// 		System.out.println(nerva.value().coursenum());
		// 		nerva = nerva.after();
				
		// 	}
		// 	System.out.println(nerva.value().coursenum());
		// 		nerva = nerva.after();
		// student_head = student_head.after();}

		


		allDepartment allDepartment1 = new allDepartment(all_students);
		LinkedList<Department> all_department = allDepartment1.getDepartment_ll();
		Position<Department> dep_head = all_department.head;
		// System.out.println(all_department.count());
		// for(int i = 0; i < 1; i++){
		// 	Department tempr = dep_head.value();
		// 	dep_head = dep_head.after();
		// 	tempr = dep_head.value();
		// 	Position<Student> new1 = tempr.getHeadEntity();
		// 	for (int k = 0; k<8; k++){
		// 	System.out.println(tempr.name());
		// 	System.out.println(new1.value().entryNo());
		// 	new1 = new1.after();}
			// System.out.println(tempr.name());
			// System.out.println(new1.value().entryNo());
			// new1 = new1.after();
		// }
		

		// System.out.println("end of allDeparment");
		allHostel allhostel1 = new allHostel(all_students);
		LinkedList<Hostel> all_hostel = allhostel1.getHostel_ll();
		Position<Hostel> hostel_head = all_hostel.head;
		// System.out.println(all_hostel.count());
		// for(int i = 0; i < 3; i++){
		// 	Hostel tempr = hostel_head.value();
		// 	hostel_head = hostel_head.after();
		// 	Position<Student> new1 = tempr.getHeadEntity();
		// 	System.out.println(tempr.name());
		// 	// System.out.println(new1.value().entryNo());
		// 	// new1 = new1.after();
			
		// }
		// System.out.println("end of allhostle");

		allCourse allcourse1 = new allCourse(courses_txt_input);
		LinkedList<String[]> all_course = allcourse1.getAllCourse_ll();
		Position<String[]> temp9 = all_course.head;
		// for(int i = 0; i<all_course.count(); i++){

		// 	System.out.println(temp9.value()[0]+ " "+ temp9.value()[1] +" "+ temp9.value()[2]);
		// 	temp9 = temp9.after();
		// }
		// System.out.println("-----------------------------end of allcourse-------------");

		// starts taking query and solving them
		
		for(int i = 0; i < number_of_query; i++){
			String[] temp = queries_to_solve[i];
			// queries_to_solve1 = queries_to_solve1.after();
			// System.out.println("--");
			// System.out.println(temp[0]);
			// System.out.println(temp[0].equals("COURSETITLE"));
			// System.out.println(temp[0].equals("INFO"));
			if(temp[0].equals("COURSETITLE")){
				int total_count= all_course.count();
				
				Position<String[]> all_course_head = all_course.head;
				for (int j = 0; j < total_count; j++){
					String[] compareValue = all_course_head.value();
					// System.out.println(compareValue[2]);
					all_course_head = all_course_head.after();
					if(temp[1].equals(compareValue[1])){
						System.out.println(compareValue[2]);
						break;
					}
				}
			} else if(temp[0].equals("INFO")){
				int total_ll = all_students.count();
				String entry_number = temp[1];
				String[] answer_store = new String[5];
				int no_of_courses = 1;
				Position<Student> temp_stu_head = all_students.head;
				for (int k = 0; k < total_ll; k++){
					Student temp1 = temp_stu_head.value();
					temp_stu_head = temp_stu_head.after();
					if (temp1.entryNo().equals(entry_number)) {
						Position<CourseGrade> temp2 = temp1.getHeadEntity();

						while(temp2.after() != null){
						no_of_courses++;
						temp2 =temp2.after();
						// String[] temp_var = {temp2.coursenum(), temp2.getgradeinfo()};
						// course123.add(temp_var);
						}
						
						answer_store[0] = temp1.entryNo();
						answer_store[1] = temp1.name();
						answer_store[2] = temp1.department();
						answer_store[3] = temp1.hostel();
						answer_store[4] = temp1.cgpa();
						
						break;

					}
				}
				Position<Student> temp_stu_head1 = all_students.head;
				String[][] course_no_data_store = new String[no_of_courses][2];
				for(int k = 0; k< total_ll; k++){
					Student temp1 = temp_stu_head1.value();
					temp_stu_head1 = temp_stu_head1.after();
					if (temp1.entryNo().equals(entry_number)){
						Position<CourseGrade> temp2 = temp1.getHeadEntity();
						// System.out.println("---- --");

						for(int m = 0; m < no_of_courses; m++ ){
						// no_of_courses++;
							// System.out.println("check");
							String cna = (temp2.value().coursenum());
							String cnab = temp2.value().getgradeinfo();
							String[] cb = {cna, cnab};
							course_no_data_store[m] = cb;
							temp2 = temp2.after();

						// temp2 =temp.after();
						// String[] temp_var = {temp2.coursenum(), temp2.getgradeinfo()};
						// course123.add(temp_var);
						}
						break;
					}
				}
				for(int m = 0; m<5; m++){
					System.out.print(answer_store[m] + " ");
				}
				sort sortedAnsr = new sort(course_no_data_store);
				String[][] sortedAnswer = sortedAnsr.getSorted();
				// System.out.println("---");
				// System.out.println(sortedAnswer.length);
				for (int m = 0; m< sortedAnswer.length-1; m++){
					System.out.print(sortedAnswer[m][0] + " " + sortedAnswer[m][1] + " ");
				}
				System.out.println(sortedAnswer[sortedAnswer.length-1][0] + " " + sortedAnswer[sortedAnswer.length-1][1]);
				
			
				
			} else{
				String entryNN = temp[1];
				String shareAll = temp[2];
				// Position<Department> dep_head2 = all_department.head;
				
				// LinkedList<String[]> all_course2 = allcourse1.getAllCourse_ll();


				// System.out.println("Start");

				int studnet_count1 = all_students.count();
				int dep_count1 = all_department.count();
				int hostel_count1 = all_hostel.count();
				int course_count1 = all_course.count();


				//course type SHARE

				//counting number of eleement if present in the allCourse
				int total_no_of_entry_for_course_n = 0;
				Position<String[]> all_course_head3 = all_course.head;
				for (int j = 0; j < course_count1; j++){
					String[] compareValue = all_course_head3.value();
					all_course_head3 = all_course_head3.after();
					if(temp[2].equals(compareValue[1])){
						total_no_of_entry_for_course_n++;
						
					}
				}
				// System.out.println(total_no_of_entry_for_course_n);

				//counting for hostel
				int total_no_of_entry_for_hostel = 0;
				Position<Hostel> hostel_head3 = all_hostel.head;
				for(int j = 0; j < hostel_count1; j++){	

					Hostel compareValue = hostel_head3.value();
					hostel_head3 = hostel_head3.after();
					// System.out.println("--------" + shareAll + " "  + (compareValue.name()));
					if(shareAll.equals(compareValue.name())){
						Position<Student> new2 = compareValue.getHeadEntity();
						
						while(new2.after() != null){
						new2 = new2.after();
						total_no_of_entry_for_hostel++;
							
						}
						total_no_of_entry_for_hostel++;
						break;
					}
				}
				// System.out.println(total_no_of_entry_for_hostel);
				//count for department
				int total_no_of_entry_for_dep = 0;
				Position<Department> dep_head3 = all_department.head;
				for(int j = 0; j < dep_count1; j++){
					Department compareValue = dep_head3.value();
					dep_head3 = dep_head3.after();
					// System.out.println(compareValue.name());
					// System.out.println("--------" + shareAll + " "  + (compareValue.name()));
					if(shareAll.equals(compareValue.name())){
						Position<Student> new2 = compareValue.getHeadEntity();
						
						while(new2.after() != null){
						// System.out.println(new2.after().value().entryNo());
						total_no_of_entry_for_dep++;
						new2 = new2.after();
							
						}
						// System.out.println(new2.after().value().entryNo());
						total_no_of_entry_for_dep++;
						break;
					}
				}
				// System.out.println(total_no_of_entry_for_dep);





				//printing element of students in that course lexicographically
				if (total_no_of_entry_for_course_n > 0){
					String[] entryNoTOPrint = new String[total_no_of_entry_for_course_n-1];
					Position<String[]> all_course_head = all_course.head;
					int k = 0;
					for (int j = 0; j < course_count1; j++){
						
						String[] compareValue = all_course_head.value();
						all_course_head = all_course_head.after();
						if(temp[2].equals(compareValue[1])){
							if(compareValue[0].equals(entryNN)){}
								else{entryNoTOPrint[k] = compareValue[0];k++;}							
						}
					}
					sort1 sortEntryNo = new sort1(entryNoTOPrint);
					String[] sortedEntryNo = sortEntryNo.getSorted();
					for(int b = 0; b < sortedEntryNo.length-1; b++){
						System.out.print(sortedEntryNo[b] + " ");
					}
					System.out.println(sortedEntryNo[sortedEntryNo.length-1]);
				} else if(total_no_of_entry_for_hostel > 0){
					String[] hostelToPrint = new String[total_no_of_entry_for_hostel-1];
					Position<Hostel> hostel_head2 = all_hostel.head;
					for(int j = 0; j < hostel_count1; j++){
						Hostel compareValue = hostel_head2.value();
						hostel_head2 = hostel_head2.after();
						if(shareAll.equals(compareValue.name())){
							Position<Student> new2 = compareValue.getHeadEntity();
							int k = 0;
							while(new2.after() != null){
							if(new2.value().entryNo().equals(entryNN)){
								new2 = new2.after();
							}
								else{
									// String ab = new2.value().entryNo();
									hostelToPrint[k] = new2.value().entryNo();
									new2 = new2.after();k++;}
							}
							if(new2.value().entryNo().equals(entryNN)){
								new2 = new2.after();
							}
								else{
									// String ab = new2.value().entryNo();
									hostelToPrint[k] = new2.value().entryNo();
									new2= new2.after();}
									break;
						}
					}
					sort1 sortEntryNo = new sort1(hostelToPrint);
					String[] sortedEntryNo = sortEntryNo.getSorted();
					for(int b = 0; b < sortedEntryNo.length-1; b++){
						System.out.print(sortedEntryNo[b] + " ");
					}
					System.out.println(sortedEntryNo[sortedEntryNo.length-1]);
				} else if(total_no_of_entry_for_dep > 0){
					int z = total_no_of_entry_for_dep-1;
					String[] depToPrint = new String[z];
					Position<Department> dep_head2 = all_department.head;
					for(int j = 0; j < dep_count1; j++){
						Department compareValue = dep_head2.value();
						dep_head2 = dep_head2.after();
						if(shareAll.equals(compareValue.name())){
							Position<Student> new2 = compareValue.getHeadEntity();
							int k = 0;
							while(new2.after() != null){
							if(new2.value().entryNo().equals(entryNN)){
								new2 = new2.after();
							}
								else{
									// String ab = new2.value().entryNo();
									depToPrint[k] = new2.value().entryNo();
									new2 =new2.after();k++;}
							}
							if(new2.value().entryNo().equals(entryNN)){
								new2 = new2.after();
							}
								else{
									// String ab = new2.value().entryNo();
									depToPrint[k] = new2.value().entryNo();
									new2 = new2.after();}
									// depToPrint[k+1] = "9999999999";

									break;
						}

					}
					sort1 sortEntryNo = new sort1(depToPrint);
					String[] sortedEntryNo = sortEntryNo.getSorted();
					for(int b = 0; b < sortedEntryNo.length-1; b++){
						System.out.print(sortedEntryNo[b] + " ");
					}
					System.out.println(sortedEntryNo[sortedEntryNo.length-1]);

				} 








			}
		}



		



	// 	//adding studtnst dalata in linked list inside answer queries
	// 	LinkedList<Student> allStudents = new LinkedList<Student>();
	// 	while (stuednts_txt_input.positions().hasNext()){

	// 		//constructor for students class
	// 		String name = stuednts_txt_input.positions().next().value()[1];
	// 		String entry_no = stuednts_txt_input.positions().next().value()[0];
	// 		String departmetn = stuednts_txt_input.positions().next().value()[2];
	// 		String hostel = stuednts_txt_input.positions().next().value()[3];

	// 		//adding courseGrade data from courses then appending it to student linked list
	// 		CourseGrade_ll courseGrade_ll = new CourseGrade_ll(courses_txt_input, entry_no);
	// 		//calculation of completed credits
	// 		String completed_credits_cal = coursegrade_ll.count()*3 - 3*I_grade_counter;
	// 		String completed_credits = completed_credits_cal; 

	// 		//calculation for cgpa of that students


	// 		String cGpA = (gradepoint*3)/(coursegrade_ll.count()*3 - 3*I_grade_counter);
	// 		Student s1 = new Student(name, entry_no, departmetn, hostel, completed_credits, cGpA);
	// 		allStudents.add(s1);
	// 		stuednts_txt_input.positions().next();
				
	// 	}
	// 	System.out.println("inside answerQueries: " + number_of_query);
	// 	System.out.println(courses_txt_input.positions().next().value()[0]);
	}


	public static void main(String[] args) {


		String studnet1 = args[0];
		String corsses1 = args[1];
		String queyr1 = args[2]; 
		Assignment1 assign = new Assignment1();
		assign.getData(studnet1,corsses1);
		assign.answerQueries(queyr1);		
	}
}