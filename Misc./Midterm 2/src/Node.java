

//Problem 1

public	class Node {
	static int pro = 1;
		int item;
		
		Node next;
		
		Node(int x)
		{
			item = x;
			next = null;
		}
	
	static int product(Node H)
	{
		
		if(H == null)
			return 1;
		
		pro = H.item * pro;
		
		product(H.next);
		
		return pro;

	}
	
	public static void main(String[] args)
	{
		Node H = new Node(1);
		
		Node A = new Node(2);
		H.next = A;
		
		Node B = new Node(4);
		
		A.next = B;
		
		Node C = new Node(8);
		
		B.next = C;
		
		System.out.println(product(H));
		
		
	}
}
