//-----------------------------------------------------------------------------
// Dictionary.java
// Linked List implementation of the Dictionary ADT
// Author: Dillon Ney
// ID: 1705097
//-----------------------------------------------------------------------------

public class Dictionary implements DictionaryInterface {

   // private inner Node class
   private class Node {
      String item;
      String key;
      Node next;

      Node(String k, String i){
         item = i;
         key = k;
         next = null;
      }
   }

   // Fields for the Dictionary class
   private Node head;     // reference to first Node in List
   private int numItems;  // number of items in this Dictionary

   // Dictionary()
   // constructor for the Dictionary class
   public Dictionary()
   {
      head = null;
      numItems = 0;
   }


   // private helper function -------------------------------------------------

   // lookup()
   // returns a reference to the Node at entry key in this Dictionary
   
   private Node find(String key)
   {

	   Node N = head;
	   
	      for( ; N!=null; N=N.next)
	      {  
	         if(N.key.contentEquals(key))
	        	return N;
	         
	      }
	      return null;
	      

   }
   

   // isEmpty()
   // pre: none
   // post: returns true if this Dictionary is empty, false otherwise
   public boolean isEmpty(){
      return(numItems == 0);
   }

   // size()
   // pre: none
   // post: returns the number of elements in this Dictionary
   public int size() {
      return numItems;
   }
   
   
   // lookup()
   // pre: none
   // returns value associated key, or null reference if no such key exists
   public String lookup(String key)
   {
      Node N = find(key);
      if(N != null)
    	  return N.item;
      else
    	  return null;
   }

// insert()
// inserts new (key,value) pair into this Dictionary
// pre: lookup(key)==null
   public void insert(String key, String item) throws DuplicateKeyException
   {
	   
	   numItems++;
		 if(size() == 0)
	      {
			 Node N = new Node(key, item);
	         N.next = head;
	         head = N;
	      }
		 else if(lookup(key) != null)
		 {
			 throw new DuplicateKeyException
			 (
				"cannot insert duplicate keys"
			 );
		 }
		{
	     Node N = new Node(key, item);
         N.next = head;
         head = N;
		}
	 
	 
   }

// delete()
// deletes pair with the given key
// pre: lookup(key)!=null
   public void delete(String key) throws KeyNotFoundException
   {

      {
      if( lookup(key) == null)
      {
         throw new KeyNotFoundException
         (
            "cannot delete non-existent key "
         );
      }

      numItems--;
      
      Node N = new Node(null, null);
      N.next = head;
      if(N.next.key.contentEquals(key))
      {
		  head = N.next.next;
      }
      while(N.next != null)
      {
    	  
    	   if(N.next.key.contentEquals(key))
    	  {
    		 Node T = N.next;
    		 N.next = T.next;
    	  }
    	  else
    	  {
    		  N = N.next;
    	  }
      }

      
      }
   }

   // makeEmpty()
   // pre: none
   public void makeEmpty(){
      head = null;
      numItems = 0;
   }

   // toString()
   // returns a String representation of this Dictionary
   // overrides Object's toString() method
   // pre: none
   public String toString(){

      StringBuffer sb = new StringBuffer();
      Node N = head;

      for( ; N!=null; N=N.next){
    	  sb.insert(0, N.key + " " + N.item + "\n");
       
      }
      return new String(sb);
   }




}
