//-----------------------------------------------------------------------------
// DictionaryTest.java
// Tests Dictionary ADT
// Author: Dillon Ney
// ID: 1705097
//-----------------------------------------------------------------------------
public class DictionaryTest 
{

	public static void main(String[] args) 
	{
		Dictionary D = new Dictionary();
		
		System.out.println(D.size());
		
		D.insert("a", "apples");
		D.insert("b", "bananas");
		D.insert("c", "cucumbers");
		
		System.out.println(D.size());
		
		String out = D.lookup("b") + " || return successful!";
	if(out != null)
		System.out.println(out);
	else
		System.out.println("Nothing at that key");
	
	System.out.println(D.toString());
	
	D.delete("a");
	
	System.out.println(D.toString());
	
	D.insert("d", "durian");
	
	System.out.println(D.toString());
	
	//D.insert("d", "date");
	
	//System.out.println(D.toString());
	
	//D.delete("e");
	
	D.makeEmpty();
	
	System.out.println(D.isEmpty() + " ... is empty");
	
	
	

	
	

	      String v;
	      Dictionary A = new Dictionary();
	      A.insert("1","a");
	      A.insert("2","b");
	      A.insert("3","c");
	      A.insert("4","d");
	      A.insert("5","e");
	      A.insert("6","f");
	      A.insert("7","g");
	      System.out.println(A);

	      v = A.lookup("1");
	      System.out.println("key=1 "+(v==null?"not found":("value="+v)));
	      v = A.lookup("3");
	      System.out.println("key=3 "+(v==null?"not found":("value="+v)));
	      v = A.lookup("7");
	      System.out.println("key=7 "+(v==null?"not found":("value="+v)));
	      v = A.lookup("8");
	      System.out.println("key=8 "+(v==null?"not found":("value="+v)));
	      System.out.println();

	      // A.insert("2","f");  // causes DuplicateKeyException

	      A.delete("1");
	      A.delete("3");
	      A.delete("7");
	      System.out.println(A);

	      // A.delete("8");  // causes KeyNotFoundException

	      System.out.println(A.isEmpty());
	      System.out.println(A.size());
	      A.makeEmpty();
	      System.out.println(A.isEmpty());
	      System.out.println(A);

	   }
}

