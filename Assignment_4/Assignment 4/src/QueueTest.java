/*
 * QueueTest.java
 * Tests Queue ADT
 * Author: Dillon Ney ID# 1705097
 */
public class QueueTest {

	static class StringBean
	{
				String s;
				
				StringBean(String s)
				{
					this.s = s;
				}
				
				public String toString()
				{
					return this.s;
				}
	}
	
	public static void main(String[] args)
	{
		
		
	Queue D = new Queue();
	
	
	StringBean a = new StringBean("a");
	
	StringBean b = new StringBean("b");
	
	StringBean c = new StringBean("c");

	
	D.enqueue(a);
	D.enqueue(b);
	D.enqueue(c);
	
	System.out.println(D.toString());
	
	System.out.println("len:" + D.length());
	
	D.dequeue();
	
	System.out.println(D.toString());
	
	System.out.println(D.peek());
	
	System.out.println("len:" + D.length());
	
	D.dequeue();
	System.out.println("len:" + D.length());
	D.dequeue();
	//D.dequeueAll();
	
	D.enqueue(a);
	D.enqueue(b);
	D.enqueue(c);
	
	D.dequeueAll();
	
	//D.dequeueAll();
	//causes QueueEmptyException
	
	//D.peek();
	//causes QueueEmptyException
	System.out.println("Empty if queue was all dequeued: " + D.toString());
	
	System.out.println("len:" + D.length());


	}
	
}
