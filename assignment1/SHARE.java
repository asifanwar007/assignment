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
		// for(int i = 0; i < 2; i++){
		// 	Department tempr = dep_head.value();
		// 	dep_head = dep_head.after();
		// 	Position<Student> new1 = tempr.getHeadEntity();
		// 	System.out.println(tempr.name());
		// 	System.out.println(new1.value().entryNo());
		// 	new1 = new1.after();
			
		// }

allHostel allhostel1 = new allHostel(all_students);
LinkedList<Hostel> all_hostel = allhostel1.getHostel_ll();
Position<Hostel> hostel_head = all_hostel.head;
		// System.out.println(all_hostel.count());
		// for(int i = 0; i < 3; i++){
		// 	Hostel tempr = hostel_head.value();
		// 	hostel_head = hostel_head.after();
		// 	Position<Student> new1 = tempr.getHeadEntity();
		// 	System.out.println(tempr.name());
		// 	System.out.println(new1.value().entryNo());
		// 	new1 = new1.after();
			
		// 

allCourse allcourse1 = new allCourse(courses_txt_input);
LinkedList<String[]> all_course = allcourse1.getAllCourse_ll();