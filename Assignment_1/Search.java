import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Search {


	public static void main(String[] args) throws IOException 
	{
		if(args.length < 2)
		{
			System.out.println("Usage: Search file target1 [target2 ..]");
			System.exit(1);
		}
		
		try {
			Scanner in = new Scanner(new File(args[0]));
		      in.useDelimiter("\\Z"); // matches the end of file character
		      String s = in.next(); 
		      // read in whole file as a single String
		      in.close();
		      String[] lIn = s.split("\n");  // split s into individual lines
			
		
	      for(int i = 0; i < lIn.length; i++)
	      {
	    	  
	    	  lIn[i] = lIn[i].replaceAll("\\s","");
	      }
		int[] loc = new int[lIn.length];
		
		ln(loc);
		mergeSort(lIn, loc, 0, lIn.length - 1);
		
		 for (int i = 1; i<args.length; i++) 
		 {
			 int target = search(lIn, 0, lIn.length - 1, args[i]);
			
			 if( target != -1) 
				{
				 System.out.println(args[i] + " found on line " + loc[target]);
					
				} 
			 else
				{
				 System.out.println(args[i] + " not found");
				 
				}
	     }
		
		}
		catch(Exception e) {
			
		         System.err.println(args[0] + " (No such file or directory)");
		         System.out.println("Usage: Search file target1 [target2 ..]");
		         System.exit(1);
		}

	}
	


public static int search(String[] lIn, int p, int r, String target) 
{

	int q;

    if(p > r) {
       return -1;
    }else{
       q = (p+r)/2;
       if(target.compareTo(lIn[q]) == 0){
          return q;
       }else if(target.compareTo(lIn[q]) < 0){
          return search(lIn, p, q-1, target);
       }else{ // target > lIn[q]
          return search(lIn, q+1, r, target);
       }
    }
	
}

public static void ln(int[] loc )
{
	for(int i = 0; i < loc.length; i++)
	{
		loc[i] = i+1;
	}
	
}


public static void mergeSort(String[] lIn, int[] lineNumber, int p, int r) 
{
	 int q;
     if(p < r) {
        q = (p+r)/2;

        mergeSort(lIn, lineNumber, p, q);
        mergeSort(lIn, lineNumber, q+1, r);
        merge(lIn, lineNumber, p, q, r);

     }

}


public static void merge(String[] A, int[] lineNumber, int p, int q, int r){
    int n1 = q-p+1;
    int n2 = r-q;
    String[] L = new String[n1];
    String[] R = new String[n2];
    int[] iL = new int[n1];
    int[] iR = new int[n1];
    int i, j, k;

    for(i=0; i<n1; i++){
       L[i] = A[p+i];
       iL[i] = lineNumber[p+i];
    }
    for(j=0; j<n2; j++){ 
       R[j] = A[q+j+1];
       iR[j] = lineNumber[q+j+1];
    }

    i = 0; j = 0;
    for(k=p; k<=r; k++){
       if( i<n1 && j<n2 ){
          if( L[i].compareTo(R[j]) < 0){
             A[k] = L[i];
             lineNumber[k] = iL[i];
             i++;
          }else{
             A[k] = R[j];
             lineNumber[k] = iR[j];
             j++;
          }
       }else if( i<n1 ){
          A[k] = L[i];
          lineNumber[k] = iL[i];
          i++;
       }else{ // j<n2
          A[k] = R[j];
          lineNumber[k] = iR[j];
          j++;
       }
    }
 }
}

