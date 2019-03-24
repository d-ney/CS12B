
public class Prob5 {

	static void sortWords(String[] W)
	{
		for(int i = 1; i < W.length; i++)
		{
			
			for(int j = i-1; j >= 0; j--)
			{
				if(W[i].charAt(0) < W[j].charAt(0))
				{
					String temp = W[i];
					W[i] = W[j];
					W[j] = temp;
				}
			}
		}
	}
	
	
	public static void main(String[] args)
	{
		String[] words = {"yeet", "but", "rekt", "orange", "apple", "banana"};
		sortWords(words);
		for(int i = 0; i < words.length; i++)
			System.out.println(words[i]);
	}
}

