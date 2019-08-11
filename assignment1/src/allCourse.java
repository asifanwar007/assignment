public class allCourse {
	LinkedList<String[]> ll;
	LinkedList<String[]> course_no_name = new LinkedList<String[]>();

	public allCourse(LinkedList<String[]> ll){
		this.ll = ll;
	}

	public LinkedList<String[]> getAllCourse_ll(){
		// LinkedList<Student> student_ll = new LinkedList<Student>();
		Position<String[]> temp = ll.head;
		// String[] temp1 = temp.value();
		while(temp.after() != null){
			String cn = (temp.value()[1]);
			String ct = (temp.value()[3]);
			String entryNo1 = (temp.value()[0]);
			String[] addll = {entryNo1, cn, ct};
			course_no_name.add(addll);
			temp = temp.after();
		}
		String cn = (temp.value()[1]);
		String ct = (temp.value()[3]);
		String entryNo1 = (temp.value()[0]);
		String[] addll = {entryNo1, cn, ct};
		course_no_name.add(addll);
		// temp = temp.after();
		return course_no_name;
	}
}