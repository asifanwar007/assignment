import java.lang.Math;
public class program
{
	public float[] test(int b, int c)
	{
		/*
		Exercise 12: Roots of polynomial- Write a Java program that given b and c,
		computes the roots of the polynomial x*x+b*x+c. You can assume that the
		roots are real valued and need to be return in an array.
		Return the result in an array [p,q] where p<=q meaning the smaller 
		element should be the first element of the array
		*/
		
		float s1 = (float) (-b  + Math.sqrt(b*b - 4*c)); 
		s1 = s1 / 2;
		float s2 = (float) (-b -  Math.sqrt(b*b - 4*c));
		s2 = s2 / 2;
		System.out.println(s1 + " " + s2);
		if (s1 < s2){
			float ret[] = {s1,s2};
			return ret;		
		} else {
			float ret[] = {s2,s1};
			return ret;
		}
		
	}
}
