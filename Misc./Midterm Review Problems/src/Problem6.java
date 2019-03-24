
public class Problem6 {
	
	static String s = "";
	
	public static void main(String[] args)
	{
		System.out.println(integerToString(100, 8));
//		for(int b=2; b<=100; b++)
//		{
//			System.out.println("base = "+b+"\t"+integerToString(43981,b));}
	}
	
	static String integerToString(int n, int m)
	{
		
		if(n>0)
		{
			if(n/m>0)
			{
				integerToString(n/m, m);
			}
			
			s += n%m;
		}
		return s;
	}
}
