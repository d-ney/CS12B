
public class Problem1 {
		   
		   public static int C(int n, int k){
		      if( k>n || k<0 )
		         return 0;
		      else if(k==0 || k==n)
		         return 1;
		      else
		         return C(n-1, k-1)+C(n-1,k);
		   }

		   public static void main(String[] args){

		      
		         System.out.println(C(5,3));
		   }
}

