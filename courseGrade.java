import java.util.Iterator;
import java.io.*;

public class courseGrade extends GradeInfo implements CourseGrade_{
	String courseTitle;
	String courseNum;
	LetterGrade gradeinfo;
	
	public courseGrade(String courseTitle, String courseNum,LetterGrade gradeinfo){
		this.courseTitle = courseTitle;
		this.courseNum = courseNum;
		this.gradeinfo = gradeinfo;
	}
	public String coursetitle(){
		return courseTitle;
	}
	public String coursenum(){
		return courseNum;
	}
	public courseGrade grade(GradeInfo.LetterGrade gradeinfo){
		return GradeInfo.gradepoint(gradeinfo);
	}
	
}