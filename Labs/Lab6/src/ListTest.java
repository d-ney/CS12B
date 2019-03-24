/*
 * ListTest.java
 * Tests List ADT
 * Author: Dillon Ney ID # 1705097
 */
public class ListTest {

	public static void main(String[] args){
	      List<String> ps = new List<String>();
	      ps.add(1, "very");
	      ps.add(1, "sad");
	      
	      
	      System.out.println(ps.get(1));
	      
	      ps.removeAll();
	      System.out.println(ps.isEmpty());
	      
	      
	      System.out.println(ps.size());
	      
	      // causes Index out of Bonds  
	      //  System.out.println(ps.get(1));
	      
	      //causes invalid Index
	      // ps.add(4, "all");
	      
	      //causes invalid Index
	      // ps.remove(1);
	      
	      List<Double> pd = new List<Double>();
	      pd.add(1, 2.5);
	      pd.add(1, 5.7);
	      
	      System.out.println(pd.get(1));
	      System.out.println(pd.get(2));
	      System.out.println(pd.isEmpty());
	      pd.remove(1);
	      pd.add(1, 9.9);
	      System.out.println(pd.size());
	      System.out.println(pd.get(1));
	      System.out.println(pd.get(2));
	      

	}
}
