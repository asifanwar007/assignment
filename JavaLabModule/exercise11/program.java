public class program
{
	public int test(int n, int m)
	{
		int lcm = n*m;
		if (m%n == 0 || n%m == 0){
			if (m>n){
				lcm = m;
			} else {lcm = n;}
		} else if (n>m){
			for (int i = 2; i < m; i++){
				if (n*i % m == 0){
					lcm = n*i;
				} else {
					lcm = n*m;
				}
			}
		} else {
			for (int i = 2; i < n; i++){
				if (m*i % n == 0){
					lcm = n*i;
				} else {
					lcm = n*m;
				}
			}

		}
		/*
		Exercise 11: Least common multiple- Given two integers n and m, the objective
		is to compute the LCM (least common multiple) of n and m. LCM is the smallest
		number that is divisble by both n amd m. For e.g. is n is 12 and m is 14, the
		LCM is 84. If n is 32 and m is 16, the LCM is 32.
		*/
		return lcm;
	}
}
