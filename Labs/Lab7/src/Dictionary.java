/*
 *Dictionary.java
 *Dictionary ADT using BinarySearch data structure
 *Author: Dillon Ney ID# 1705097 
 */

public class Dictionary implements DictionaryInterface{

	
	private class Node
	{
		  private String key;
		  private String value;
		  private Node left;
		  private Node right;
		   
		   Node (String k, String v) 
		   {
			   this.key = k;
			   this.value = v;
			   this.left = this.right = null;
		   }
	}
	
	Node root;
	int numPairs;
	
	Dictionary()
	{
		this.root = null;
		this.numPairs = 0;
	}
	
	// findKey()
	// returns the Node containing key k in the subtree rooted at R, or returns
	// null if no such Node exists
	private Node findKey(Node R, String k)
	{
	   if(R==null || k.compareTo(R.key) == 0) 
	      return R;
	   if( k.compareTo(R.key) < 0 ) 
	      return findKey(R.left, k);
	   else // compRes > 0
	      return findKey(R.right, k);
	}

	
	// findParent()
	// returns the parent of N in the subtree rooted at R, or returns null 
	// if N is equal to R. (pre: R != null)
	private Node findParent(Node N, Node R){
	   Node P=null;
	   if( N!=R )
	   {
	      P = R;
	      while( P.left!=N && P.right!=N )
	      {
	    	 int compRes = N.key.compareTo(P.key);
	    	 
	         if(compRes<0)
	            P = P.left;
	         else
	            P = P.right;
	      }
	   }
	   return P;
	}
	
	// findLeftmost()
	// returns the leftmost Node in the subtree rooted at R, or null if R is null.
	private Node findLeftmost(Node R)
	{
	   Node L = R;
	   if( L!=null ) for( ; L.left!=null; L=L.left) ;
	   return L;
	}
	
	// printInOrder()
	// prints the (key, value) pairs belonging to the subtree rooted at R in order
	// of increasing keys to file pointed to by out.
	private StringBuffer printInOrder(StringBuffer sb, Node R){
	   if( R!=null ){
	      printInOrder(sb, R.left);

	       sb.append(R.key +" " + R.value + "\n");
	      printInOrder(sb, R.right);
	   }
	   return sb;
	}
	
	// deleteAll()
	// deletes all Nodes in the subtree rooted at N.
	private void deleteAll(Node N)
	{
	   if( N!=null )
	   {
	      deleteAll(N.left);
	      deleteAll(N.right);
	      N = null;
	   }
	}
	
	public boolean isEmpty()
	{
		  return(this.numPairs == 0 );
	}
	
	public int size()
	{
		   return(this.numPairs);
	}
	
	// lookup()
	// returns the value v such that (k, v) is in D, or returns null if no 
	// such value v exists.
	// pre: none
	public String lookup(String k)
	{
		
	  Node N = findKey(this.root, k);
	  
	  return ( N==null ? null : N.value );
	   
	}
	
	// insert()
	// inserts new (key,value) pair into D
	// pre: lookup(D, k)==null
	public void insert( String k, String v)
	{
	   Node N, A, B;
	   
	   if( findKey(this.root, k)!=null )
	   {
	      throw new DuplicateKeyException( 
	         "Dictionary Error: cannot insert() duplicate key: \""+ k +"\"\n");
	   }
	   N = new Node(k, v);
	   B = null;
	   A = this.root;
	   while( A != null ){
	      B = A;
	      if( k.compareTo( A.key) < 0 ) A = A.left;
	      else A = A.right;
	   }
	   if( B==null ) this.root = N;
	   else if( k.compareTo( B.key) < 0 ) B.left = N;
	   else B.right = N;
	   this.numPairs++;
	}

	// delete()
	// deletes pair with the key k
	// pre: lookup(D, k)!=null
	public void delete(String k){
	   Node N, P, S;
	   N = findKey(this.root, k);
	   if( N==null )
	   {
	      throw new KeyNotFoundException(
	         "Dictionary Error: cannot delete() non-existent key: \""+ k +"\"\n");

	   }
	   if( N.left==null && N.right==null )	// case 1 (no children)
	   {  
	      if( N==this.root )
	      {
	         this.root = null;
	      }else
	      {
	         P = findParent(N, this.root);
	         if( P.right==N ) P.right = null;
	         else P.left = null;
	      }
	   }else if( N.right==null )	// case 2 (left but no right child)
	   {  
	      if( N==this.root )
	      {
	         this.root = N.left;
	      }else
	      {
	         P = findParent(N, this.root);
	         if( P.right==N ) P.right = N.left;
	         else P.left = N.left;
	      }
	   }else if( N.left==null )	// case 2 (right but no left child)
	   {  
	      if( N==this.root )
	      {
	         this.root = N.right;
	         
	      }else
	      {
	         P = findParent(N, this.root);
	         if( P.right==N ) P.right = N.right;
	         else P.left = N.right;
	      }
	   }else	// case3: (two children: N.left!=null && N.right!=null)
	   {                     
	      S = findLeftmost(N.right);
	      N.key = S.key;
	      N.value = S.value;
	      P = findParent(S, N);
	      if( P.right==S ) P.right = S.right;
	      else P.left = S.right;

	   }
	   this.numPairs--;
	}

	// makeEmpty()
	// re-sets D to the empty state.
	// pre: none
	public void makeEmpty(){
	   deleteAll(this.root);
	   this.root = null;
	   this.numPairs = 0;
	}

	// toString()
	   // returns a String representation of this Dictionary
	   // overrides Object's toString() method
	   // pre: none
	   public String toString(){	      
	     
	      Node N = this.root;	  
	      StringBuffer sb = new StringBuffer();
	      
	      return new String(printInOrder(sb, N));
	   }
	
}
