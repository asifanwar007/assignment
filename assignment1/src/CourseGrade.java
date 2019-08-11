import java.util.Iterator;
import java.io.*;

public class CourseGrade implements CourseGrade_{
	String gradeinfo;
	String courseNum;
	String courseTitle;	
	public CourseGrade(String gradeinfo, String courseNum, String courseTitle){
		this.gradeinfo = gradeinfo;
		this.courseNum = courseNum;
		this.courseTitle = courseTitle;
	}
	
	public String coursetitle(){
		return courseTitle;
	}
	public String coursenum(){
		return courseNum;
	}
	public String getgradeinfo(){
		return gradeinfo;
	}

	
	public GradeInfo grade(){
		GradeInfo s = new GradeInfo();
		return s;
	}
	
}

