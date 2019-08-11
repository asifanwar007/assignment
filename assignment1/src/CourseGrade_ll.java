import java.util.Iterator;
import java.io.*;

public class CourseGrade_ll{
	LinkedList<String[]> ll;
	String entry_no;
	int gradepoint_count = 0;
	int I_grade_counter = 0;
	int No_of_course_with_grade_counter = 0;
	public CourseGrade_ll(LinkedList<String[]> ll, String entry_no){
		this.ll = ll;
		this.entry_no = entry_no;
	}
	public LinkedList<CourseGrade> getCourseGrade_ll(){
		
		LinkedList<CourseGrade> coursegrade_ll = new LinkedList<CourseGrade>();
		Position<String[]> temp = ll.head;
		// System.out.println("check1");
		while(temp.after() != null){
			// System.out.println("check2");
			if ((temp.value()[0]).equals(entry_no)){
				// System.out.println("check3");
				String gi = (temp.value()[2]);
				String cn = (temp.value()[1]);
				String ct = (temp.value()[3]);
				// System.out.println(gi + " " + cn+ " " + ct);
				CourseGrade cg1 = new CourseGrade(gi, cn, ct);
				coursegrade_ll.add(cg1);
				temp = temp.after();
				// System.out.println(gi);
				// System.out.println(gi.equals("I"));
				if (gi.equals("I")){
					// System.out.println("I times");
					// System.out.println(gi);
					I_grade_counter++;
				} else{
					No_of_course_with_grade_counter++;
					GradeInfo.LetterGrade ig1 = GradeInfo.LetterGrade.valueOf(gi);
					gradepoint_count = gradepoint_count + cg1.grade().gradepoint(ig1);
					// System.out.println(gradepoint_count);
				} 
			}
			else {
				temp = temp.after();}
		}
			//Last element of LinkedList
		if ((temp.value()[0]).equals(entry_no)){
			String gi = (temp.value()[2]);
			String cn = (temp.value()[1]);
			String ct = (temp.value()[3]);
			CourseGrade cg1 = new CourseGrade(gi, cn, ct);
			coursegrade_ll.add(cg1);
			if (gi.equals("I")){
				I_grade_counter++;
			} else{
				No_of_course_with_grade_counter++;
				GradeInfo.LetterGrade ig1 = GradeInfo.LetterGrade.valueOf(gi);
				gradepoint_count = gradepoint_count + cg1.grade().gradepoint(ig1);
				// System.out.println(gradepoint_count);
			} 
		}
		return coursegrade_ll;
	}
	public int getGradePoint(){
		return gradepoint_count;
	}
	public int get_I_counter(){
		return I_grade_counter;
	}
	public int getNoOfCoursewithGrade(){
		return No_of_course_with_grade_counter;
	}
}