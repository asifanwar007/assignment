import java.util.Iterator;
import java.io.*;

public class allStudents_ll{
	LinkedList<String[]> ll;
	LinkedList<String[]> course_ll;
	int totalnumberofcourse = 0;

	public allStudents_ll(LinkedList<String[]> ll, LinkedList<String[]> course_ll){
		this.ll = ll;
		this.course_ll= course_ll;
	}

	public LinkedList<Student> getAllStudents_ll(){
		LinkedList<Student> student_ll = new LinkedList<Student>();
		Position<String[]> temp = ll.head;

		while(temp.after() != null){

			
			String name = temp.value()[1];
			String entry_no = temp.value()[0];
			String departmetn = temp.value()[2];
			String hostel = temp.value()[3];
			CourseGrade_ll coursegrade1 = new CourseGrade_ll(course_ll, entry_no);
			totalnumberofcourse = coursegrade1.get_I_counter() + coursegrade1.getNoOfCoursewithGrade();
			LinkedList<CourseGrade> coursegrade2 = coursegrade1.getCourseGrade_ll();
			int course_credit = 3*coursegrade1.getNoOfCoursewithGrade();
			int grade_point = 3*coursegrade1.getGradePoint();

			String cgpa;
			if (course_credit ==0){
				cgpa = "0.00";
			} else{
				float cgpa_cal = (float) grade_point/course_credit;
				cgpa = String.format("%.2f", cgpa_cal);
			}
			
			int completed_credits_cal = course_credit;

			String completed_credits = "" + completed_credits_cal; 
			
			Student cg1 = new Student(name, entry_no, hostel, departmetn, completed_credits, cgpa, coursegrade2);
			student_ll.add(cg1);
			temp = temp.after();
		}
			//Last element of LinkedList
			String name = temp.value()[1];
			String entry_no = temp.value()[0];
			String departmetn = temp.value()[2];
			String hostel = temp.value()[3];
			CourseGrade_ll coursegrade1 = new CourseGrade_ll(course_ll, entry_no);
			LinkedList<CourseGrade> coursegrade2 = coursegrade1.getCourseGrade_ll();
			int course_credit = 3*coursegrade1.getNoOfCoursewithGrade();
			int grade_point = 3*coursegrade1.getGradePoint();
			// float cgpa_cal = (float) grade_point/course_credit;

			String cgpa;
			if (course_credit ==0){
				cgpa = "0.00";
			} else{
				float cgpa_cal = (float) grade_point/course_credit;
				cgpa = String.format("%.2f", cgpa_cal);
			}
			
			int completed_credits_cal = course_credit;

			String completed_credits = "" + completed_credits_cal; 
			// String cgpa = String.format("%.2f", cgpa_cal);
			Student cg1 = new Student(name, entry_no, hostel, departmetn, completed_credits, cgpa, coursegrade2);
			student_ll.add(cg1);

			return student_ll;
	}
}