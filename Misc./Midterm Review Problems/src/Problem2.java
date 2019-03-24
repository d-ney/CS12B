
public class Problem2 {

private static int sum;
	public static void main(String[] args) {

		//System.out.print(sum(3));
		
		System.out.print(sumV2(2, 4));
	}

	
	static int sum(int n)
	{
		
		if (n == 0)
		{
			return 1;
		}
		
		sum += n;
		sum(n - 1);
		
		return sum;
	}

	static int sumV2(int n, int m)
	{
		if (n > m)
		{
			return 1;
		}
		
		sum += n;
		sumV2(n + 1, m);
		
		return sum;
	}
	
}