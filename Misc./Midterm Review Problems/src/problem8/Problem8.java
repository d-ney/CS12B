package problem8;

public class Problem8 {
	public static void main(String[] args)
	{
		IntegerList test = new IntegerList();
		
		for(int i = 1; i <= 6; i++)
		{
			test.add(i, i);
			
		}
		
		
		//swap(test, 5, 2);
		
		reverse(test);
		
		print(test);
		
		//System.out.println("\n" + search(test, 4));
	}
	
	static void swap(IntegerList L, int i, int j)
	{
		int temp = L.get(j);
		int temp2 = L.get(i);
		
		L.remove(i);
		L.add(i, temp);
		
		
		L.remove(j);
		L.add(j, temp2);
				
	}
	
	static int search(IntegerList L, int x)
	{
		for(int i = 1; i <= L.size(); i++)
		{
			if(L.get(i) == x)
			{
				return i;
			}
		}
		return 0;
	}
	
	static void print(IntegerList L)
	{
		for(int i = 1; i <= L.size(); i++)
		{
			System.out.println(L.get(i));

		}
	}
	
	static void reverse(IntegerList L)
	{
		
		for(int i = 1; i <= L.size(); i++)
		{
			int t = L.get(L.size());
			L.remove(L.size());
			L.add(i, t);
		}
		
		
	}
}
