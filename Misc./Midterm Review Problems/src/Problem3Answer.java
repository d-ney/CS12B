
public class Problem3Answer {

	
	static int sumArray1(int[] A, int n){
		int a, b;
		if( n<=0 ){                
	
		// if array is empty
		return 0;                  // return zero
		}else{                     // else if array is non-empty
			a = A[n-1];                // get nth element from the left
			b = sumArray1(A, n-1);     // compute sum of leftmost (n-1) elements
			return a+b;
		}
	}
	
	static int sumArray2(int[] A, int n){
		int a, b;
		if( n<=0 ){                // if array is empty
			return 0;                  // return zero
			}else{                     // else if array is non-empty
				a = A[A.length-n];         // get nth element from the right
				b = sumArray2(A, n-1);      // compute sum of rightmost (n-1) elements
				return a+b;
		}
	}
	
	static int sumArray3(int[] A, int p, int r)
	{
		int a, b, q;
		if( p<r )
		{
			q = (p+r)/2;
			a = sumArray3(A, p, q);
			b = sumArray3(A, q+1, r);
			return a+b;
			}else{
				
				return A[p];
		}
	}
	
	public static void main(String[] args){int[] X = {1, 2, 3, 4, 5, 6, 7};System.out.println(sumArray1(X, X.length));System.out.println(sumArray2(X, X.length));System.out.println(sumArray3(X, 0, X.length-1));}
}
