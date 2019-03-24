
public class ModdedBinarySearch {

	public static void main(String[] args) {
		int[] B = {1,2,3,4,5,6,7,8,9,10};

	      System.out.println("Target is located in index " + binarySearch(B, 0, B.length-1, 7));
	     // System.out.println(binarySearch(B, 0, B.length-1, 2));
	      //System.out.println(binarySearch(B, 0, B.length-1, 11));

	}
	
	static int binarySearch(int[] A, int p, int r, int target)
	{
	      int q;
	      if(p > r) {
	         return -1;
	      }else{
	         q = (p+r)/2;
	         if(target == A[q]){
	            return q;
	         }else if(target < A[q]){
	        	 System.out.print("{");
	            for(int i = q; i < A.length; i++)
	            {
	            	
	            	System.out.print(A[i] + ", ");
	            	
	            }
	            System.out.println("}");
	            System.out.println(target + " is less than values in array");
	            return binarySearch(A, p, q-1, target);
	         }else{ // target > A[q]
	        	 System.out.print("{");
		            for(int i = q; i < A.length; i++)
		            {
		            	
		            	System.out.print(A[i] + ", ");
		            	
		            }
		            System.out.println("}");
		            System.out.println(target + " is greater than values in array");
	            return binarySearch(A, q+1, r, target);
	         }
	      }
	}
	
	
}
