
public class Problem3{
private static int sum;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] X = {1, 2, 3, 4, 5, 6, 7};
		System.out.println(sumArrayA(X, X.length));
		sum = 0;
		System.out.println(sumArrayB(X, X.length));
		sum = 0;
		System.out.println(sumArray3(X, 0, X.length - 1));
	}

	
	static int sumArrayA(int[] A, int n)
	{
		if(n <= 0)
		{
			return A[n];
		}
		sum += A[n-1];
		sumArrayA(A, n - 1);
		
		return sum;
	}
	
	static int sumArrayB(int[] A, int n)
	{
		
		if(n <= 0)
		{
			return 0;
		}
		
		sum += A[A.length - n];
		sumArrayB(A, n - 1);
		
		return sum ;
	}
	
	static int sumArray3(int[] A, int p, int r)
	{
		if(p > r)
		{
			return 0;
		}
		
		sum += A[p];
		sumArray3(A, p + 1, r);
		
		return sum;
	
		
	}
}
