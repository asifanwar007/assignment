import java.util.*;
public class program
{
	public int test(String number)
	{
		/*
		Exercise 17: Most frequent digit- Given a number, the objective is to find out
		the most frequently occuring digit in the number. If more than 2 digits have
		the same frequency, return the smallest digit. The number is input as a string
		and the output should be the digit as an integer. For e.g. if the number is
		12345121, the most frequently occuring digit is 1. If the number is 9988776655
		the output should be 5 as it is the smallest of the digits with the highest frequency.
		*/
		int count = 1;
		ArrayList<Integer> frequency = new ArrayList<Integer>(); 
		ArrayList fre_of_number = new ArrayList();
		for (int i = 0; i < number.length()-1; i++){
			if (number.charAt(i) == number.charAt(i + 1)) {
				count ++;
			} else{
				frequency.add(count);
				fre_of_number.add(number.charAt(i));
				count =0;
			}
		}
		if(number.charAt(number.length()-2) == number.charAt(number.length()-1)){
			frequency.add(count);
			fre_of_number.add(number.charAt(number.length()-1));
		} else{
			frequency.add(1);
			fre_of_number.add(number.charAt(number.length()-1));
		}

		int max = frequency.get(0);
        for(int i = 0; i < frequency.size(); i++)
        {
            if(max < frequency.get(i))
            {
                max = frequency.get(i);
            }
        }

        int frequency_max_count = 0;
        ArrayList<Integer> frequecy_max_count_arr = new ArrayList<Integer>();
        for (int i = 0; i < frequency.size(); i++){
        	if(frequency.get(i) == max){
        		frequecy_max_count_arr.add(frequency_max_count);
        	}
        	frequency_max_count++;
        }

        if (frequecy_max_count_arr.length > 1){
        	for(int i = 0; i < frequecy_max_count_arr.length; i++){
        		
        	}
        }

		return 4;
	}
}
