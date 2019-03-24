//Problem 2
//REMEMBER PRECONDITIONS

class Stack {
	private class Node {
		
		int item;
		Node next;
		Node(int item)
		{
			this.item = item;
			this.next = null;
		}
	}
	
	private Node top; 
	private int numItems;
	public Stack()
	{
		top = null;
		numItems = 0;
	}
	
	void push(int x)
	{

		Node P = new Node(x);
		
		P.next = top;
		top = P;
		P = top.next;
		numItems ++;
					
	}
	
	int pop()
	{
		int p = top.item;
		
		top = top.next;
		
		numItems --;
		
		return p;
	}
	
	public static void main(String[] args)
	{
		Stack S = new Stack();
		
		S.push(1);
		S.push(2);
		S.push(3);
		
		System.out.println(S.numItems);
		
		System.out.println(S.pop());
		System.out.println(S.pop());
		
		System.out.println(S.numItems);
		
	}
}