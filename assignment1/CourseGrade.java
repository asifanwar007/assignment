import java.util.Iterator;
import java.io.*;

public class CourseGrade implements CourseGrade_{
	Triplet<String, String, String> triplet; 	
	public CourseGrade(Triplet<String, String, String> triplet){
		this.triplet = triplet;
	}
	Course n = new Course(triplet);
	public String coursetitle(){
		return n.getTitle();
	}
	public String coursenum(){
		return n.getCourseNum();
	}
	String gradeinfo = n.getGrade();

	public GradeInfo grade(){
		GradeInfo s = new GradeInfo(gradeinfo);
		return s;
	}
	
}

class Course{
	
	Triplet<String, String, String> triplet; 
	public Course(Triplet<String, String, String> triplet){
		this.triplet = triplet;
	}
	String title  = triplet.getValue0();

	String courseNum = triplet.getValue1();
	String grade = triplet.getValue2();
	public String getTitle(){
		return title;
	}
	public String getCourseNum(){
		return courseNum;
	}
	public String getGrade(){
		return grade;
	}
}