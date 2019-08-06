import java.util.Iterator;
import java.io.*;

public class CourseGrade_ll{
	LinkedList<String[]> ll;
	String entry_no;
	int gradepoint = 0;
	int I_grade_counter = 0;
	public CourseGrade_ll(LinkedList<String[]> ll, String entry_no){
		this.ll = ll;
		this.entry_no = entry_no;
	}
	public LinkedList<CourseGrade> getCourseGrade_ll(){
		
		LinkedList<CourseGrade> coursegrade_ll = new LinkedList<CourseGrade>();
		Position<String[]> temp = ll.head;
		while(temp.after() != null){
			if ((temp.after().value()[0])==(entry_no)){
			String gi = (temp.after().value()[2]);
			String cn = (temp.after().value()[1]);
			String ct = (temp.after().value()[3]);
			CourseGrade cg1 = new CourseGrade(gi, cn, ct);
			coursegrade_ll.add(cg1);
			temp = temp.after();
			if (gi.equals("I")){
				I_grade_counter++;
			} else{
				gradepoint = gradepoint + cg1.grade().gradepoint(LetterGrade.valueOf(gi));
			} else {
				temp = temp.after();
			// while(ll.positions().hasNext()){
			// 	if ((ll.positions().next().value()[0]).equals(name)){
			// 		String gi = (ll.positions().value()[2]);
			// 		String cn = (ll.positions().value()[1]);
			// 		String ct = (ll.positions().value()[3]);
			// 		CourseGrade cg1 = new CourseGrade(gi, cn, ct);
			// 		coursegrade_ll.add(cg1);
			// 		if (gi.equals(I)){
			// 			I_grade_counter++;
			// 		} else{
			// 		gradepint = gradepoint + coursegrade_ll.grade().gradepoint(LetterGrade.valueOf(gi));
			// 		}
			// 	}
			// }
		

		
		}
	}
}