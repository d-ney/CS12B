/*
 * Queue.java
 * Queue ADT
 * Author: Dillon Ney ID# 1705097
 */
public class Queue implements QueueInterface {
	
	// private inner Node class
	   private class Node 
	   {
	      Object obj;
	      Node next;


	      Node(Object o)
	      {
	         this.obj = o;
	         this.next = null;

	      }
	   }
			
	   
	// private files for Queue ADT
	   
	  private int numItems;
	  private Node head;
		
	public Queue()
	{
		this.numItems = 0;
		this.head = null;

	}
	
   // isEmpty()
   // pre: none
   // post: returns true if this Queue is empty, false otherwise
   public boolean isEmpty()
   {
	   if(this.numItems == 0)
		   return true;
	   
	   return false;
   }
   
// length()
   // pre: none
   // post: returns the length of this Queue.
   public int length()
   {
	   return numItems;
   }
   
// enqueue()
   // adds newItem to back of this Queue
   // pre: none
   // post: !isEmpty()
   public void enqueue(Object newItem)
   {
	   Node N = new Node(newItem);
	   
	   if(this.numItems == 0)
	   {
       	   head = N;
	   }
	   else
	   {
		   Node temp = head;
		   while(temp.next != null) temp = temp.next;
		   
		   temp.next = N;
			   
		   
		  
	   }
	   
	   numItems++;
   }
   
   // dequeue()
   // deletes and returns item from front of this Queue
   // pre: !isEmpty()
   // post: this Queue will have one fewer element
   public Object dequeue() throws QueueEmptyException
   {
	   if(isEmpty())
	   {
		   throw new QueueEmptyException(
				  "Cannot dequeue from an empty Queue"
				   );
	   }
	   
	   Object target = head.obj;
	   
	   if(numItems > 1)
	   {
		   
		   	head = head.next;
		   	
	   }
	   else
	   {
		   head = null;
	   }
	   
	   	
	 numItems --;
	return target;
	
   }
   
   public Object peek() throws QueueEmptyException
   {
	   if(isEmpty())
	   {
		   throw new QueueEmptyException(
				  "Cannot peek at an empty Queue"
				   );
	   }
	   
	   return head.obj;
   }
   
// dequeueAll()
   // sets this Queue to the empty state
   // pre: !isEmpty()
   // post: isEmpty()
   public void dequeueAll() throws QueueEmptyException
   {
	   if(isEmpty())
	   {
		   throw new QueueEmptyException(
				  "Cannot dequeue an empty Queue"
				   );
	   }
	   
	  Node N = head;
	  
	  for( ; N!=null; N=N.next)
	  {
		   dequeue();
	  }
	   
	   head = null;
	   numItems = 0;
   }

   public String toString()
   {

	      StringBuffer sb = new StringBuffer();
	      Node N = head;

	      for( ; N!=null; N=N.next){
	    	  sb.append(N.obj.toString()).append(" ");
	       
	      }
	      return new String(sb);
   }
   
}
