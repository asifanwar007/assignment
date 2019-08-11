import java.util.Iterator;
import java.io.*;

public class GradeInfo implements GradeInfo_{
	// String grade;
	// public GradeInfo(String grade){
	// 	this.grade = grade;
	// }


	
	enum LetterGrade{
		A, Aminus, B, Bminus, C, Cminus, D, E, F, I;
	}
	
	// GradeInfo.LetterGrade a = LetterGrade;
	public static int gradepoint(GradeInfo.LetterGrade grade){
		int a = 0;
		switch (grade){
		case A:
			a = 10;
			break;
		case Aminus:
			a =  9;
			break;
		case B:
			a = 8;
			break;
		case Bminus:
			a = 7;
			break;
		case C:
			a = 6;
			break;
		case Cminus:
			a = 5;
			break;
		case D:
			a = 4;
			break;
		
		default:
			a = 0;
		}
		return a;
	}

	// public static void main(String[] args) {
	// 	String grade = "B";

	// 	GradeInfo c = new GradeInfo();
	// 	int d = c.gradepoint(LetterGrade.valueOf(grade));
	// 	System.out.println(d);
	// }
}
	