public class program
{
	public String test(String hex)
	{
		/*
		Exercise 15: Hex to binary- Given a string representing a number in hexadecimal
		format, convert it into its equivalent binary string. For e.g. if the input if ”1F1”
		then its binary equivalent is ”111110001”. If the input is ”13AFFFF”, the output
		should be ”1001110101111111111111111”.
		*/

		String binary="";
		String[] bin1 = {"0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111"};
		char[] hexa = {'A', 'B', 'C', 'D', 'E', 'F'};
		int[] hax_int = {10, 11, 12, 13, 14, 15};
		for (int i = 0; i < hex.length(); i++){
			char a = hex.charAt(i);
			boolean test = false;
			int count = 0;
			// System.out.println(a);
			for(char ele : hexa){
				// System.out.println(ele);
				if (ele == a){
					test = true;
					count++;
					break;
				}
			}
			// System.out.println(test);
			// System.out.println(bin[hax_int[count]]);
			if(test){
				binary += bin1[hax_int[count]];
				// System.out.println(binary);

			} else{
				int b = a - '0';
				b = b - 1;
				// System.out.println(b);

				binary += bin1[b];
				// System.out.println(bin1[b]);

			}
			// System.out.println(binary);
		}
		int cou = 0;
		for(int i = 0; i < 4; i++){
			if(binary.charAt(i) != '0'){

				break;
			} else {
				cou++;
			}
		}
		String ans = "";
		for (int i = cou; i < binary.length(); i++){
			ans += binary.charAt(i);

		}
		System.out.println(ans);

		return ans;
	}
}
