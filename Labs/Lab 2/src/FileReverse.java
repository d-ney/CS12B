import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileReverse {

private static String sOut = "";

	public static void main(String[] args) throws IOException
	{

	      Scanner in = null;
	      PrintWriter out = null;
	      String line = null;
	      String[] token = null;
	      int i = 0;
	      // check number of command line arguments is at least 2
	      if(args.length < 2)
	      {
	         System.out.println("Usage: FileCopy <input file> <output file>");
	         System.exit(1);
	      }

	      in = new Scanner(new File(args[0]));
	      out = new PrintWriter(new FileWriter(args[1]));
	      
	      while( in.hasNextLine() )
	      {

	         line = in.nextLine().trim() + " "; 

	         token = line.split("\\s+");  
    
	         for( i = 0;  i <token.length; i++){
	        	 out.print(stringReverse(token[i], token[i].length() - 1) + "\n");
	        	 sOut = "";
	          }
	        
	      }
	      // close files
	      in.close();
	      out.close();
	   }
		
	public static String stringReverse(String s, int n)
	{
		
		if(n >= 0)
		{
			sOut += s.charAt(n);
			stringReverse(s, n-1);
			
		}
		
		return sOut;	
	}
}
	

