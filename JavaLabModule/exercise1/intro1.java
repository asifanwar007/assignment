import java.util.*;


public class intro1{
	public static void main(String[] args) {
		System.out.println("Please Enter your input: ");
		Scanner s = new Scanner(System.in);
		int inp = s.nextInt();
		switch (inp%3) {
			case 0:
				System.out.println("Number is divisible 3");
				break;
			case 1:
				System.out.println("Remainder 1");
				break;
			default:
				System.out.println("Remainder 2");
			
		}
	}
}