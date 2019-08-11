public class allHostel{
	LinkedList<Student> ll;

	public allHostel(LinkedList<Student> ll){
		this.ll = ll;
	}

	public LinkedList<Hostel> getHostel_ll(){
		LinkedList<Student> h = new LinkedList<Student>();
		LinkedList<Hostel> allHostel = new LinkedList<Hostel>();
		
		Position<Student> temp = ll.head;
		int count_ll_ele1 = ll.count();
		int l = 0;
		for(int j = 0; j < count_ll_ele1; j++){
			Student student_value = temp.value();
			temp = temp.after();
			if (l == 0){
				// h.add(student_value);
				Hostel h1 = new Hostel(student_value.hostel());
				h1.addData(student_value);
				allHostel.add(h1);
				l++;
			} else {
				Position<Hostel> temp_dep = allHostel.head;
				int count_deprtment = allHostel.count();
				int i = 0;
				for(int k = 0; k < count_deprtment; k++){
					Hostel name_of_Hostel = temp_dep.value();
					temp_dep = temp_dep.after();
					// System.out.println("---");
					// System.out.println(student_value.Hostel());
					// System.out.println(name_of_Hostel.name());
					// System.out.println(student_value.hostel().equals(name_of_Hostel.name()) + " " + k + " " + j);
					// System.out.println(student_value.hostel());
					// System.out.println(name_of_Hostel.name());
					// // System.out.println("---");
					if(student_value.hostel().equals(name_of_Hostel.name())){
						// System.out.println("check5");
						name_of_Hostel.addData(student_value);
						i++;
						break;
					}
					 
				}
				if (i <= 0){
					// h.add(student_value);
					Hostel h1 = new Hostel(student_value.hostel());
					h1.addData(student_value);
					allHostel.add(h1);
				}
			}

		}

		return allHostel;
	}
}