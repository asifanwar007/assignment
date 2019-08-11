public class allDepartment{
	LinkedList<Student> ll;

	public allDepartment(LinkedList<Student> ll){
		this.ll = ll;
	}

	public LinkedList<Department> getDepartment_ll(){
		LinkedList<Student> h = new LinkedList<Student>();
		LinkedList<Department> allDepartment = new LinkedList<Department>();
		
		Position<Student> temp = ll.head;
		int count_ll_ele1 = ll.count();
		int l = 0;
		for(int j = 0; j < count_ll_ele1; j++){
			Student student_value = temp.value();
			temp = temp.after();
			int i = 0;
			if (l == 0){
				// h.add(student_value);
				Department h1 = new Department(student_value.department());
				h1.addData(student_value);
				allDepartment.add(h1);
				// System.out.println(student_value.entryNo());
				l++;
			} else {
				Position<Department> temp_dep = allDepartment.head;
				int count_deprtment = allDepartment.count();
				for(int k = 0; k < count_deprtment; k++){
					Department name_of_Department = temp_dep.value();
					temp_dep = temp_dep.after();
					// System.out.println("---");
					// System.out.println(student_value.department());
					// System.out.println(name_of_Department.name());
					// System.out.println(student_value.department().equals(name_of_Department.name()));
					// System.out.println("---");
					if(student_value.department().equals(name_of_Department.name())){
						// System.out.println("check5");
						// System.out.println(student_value.entryNo());
						name_of_Department.addData(student_value);
						// System.out.println(name_of_Department.student_List.value());
						i++;
						break;
					}
					 
				}
				if (i <= 0){
					// h.add(student_value);
					Department h1 = new Department(student_value.department());
					h1.addData(student_value);
					allDepartment.add(h1);

				}
			}

		}



		// System.out.println("check1");

		// Student student_value = ll.positions().next().value(); 
		// h.add(student_value);
		// Department h1 = new Department(student_value.department(), h);
		// allDepartment.add(h1);
		// for(int j = 0; j<count_ll_ele1; j++){
		// 	System.out.println("check2");
		// 	ll.positions().next();
		// 	int i =0;
		// 	student_value = ll.positions().next().value(); 
		// 	System.out.println(student_value.department());
		// 	for(int k = 0; k < count_deprtment; k++){
		// 		Department name_of_Department = allDepartment.positions().next().value();
		// 		System.out.println("check4");
		// 		if(student_value.department() == name_of_Department.name()){
		// 			System.out.println("check5");
		// 			name_of_Department.student_List.add(student_value);
		// 			i++;
		// 		} 	
		// 	}
		// 	System.out.println("check3");
		// 	// Department name_of_Department = allDepartment.positions().next().value();
		// 	// if(student_value.department() == name_of_Department.name()){
		// 	// 	name_of_Department.student_List.add(student_value);
		// 	// 	i++;
		// 	// }
		// 	if (i < 0){
		// 		h.add(student_value);
		// 		h1 = new Department(student_value.department(), h);
		// 		allDepartment.add(h1);
		// 	}

		// }
		return allDepartment;
	}
}