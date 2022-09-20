package edu.iastate.cs228.hw3;

import java.util.ListIterator;

public class MultiListPrintTest {

	public static void main(String[] args) {
		MultiList<String> list = new MultiList<String>();
		list.add("X");
		list.add("D");
		list.add("B");
		list.add("A");
		list.add("R");
	
		ListIterator<String> iter = list.listIterator();
		System.out.println(list.toStringInternal());

//Test: 1
//	iter.next();
//	iter.set("S");
//	iter.add("K");
//	iter.add("P");
//	iter.next();
//	iter.next();
//	iter.previous();
//	iter.set("M");
//	iter.previous();
//	iter.add("F");
//	iter.next();
//	//list.sortReverse(); // expected [(S, R, P, M), (F, D, A, -)]
//	list.sort();
//		iter.previous(); 
//		iter.remove(); 
//		System.out.println(iter.previousIndex());  // expected 3
//		System.out.println(list.toStringInternal());// expected [(A, D, F, K), (P, R, S, -)]
		
		
		
//Test: 2
//		while (iter.hasPrevious()) 
//		{ 
//		    iter.previous();  
//		    if (iter.hasNext())   
//		 System.out.println(iter.next());  
//		    iter.previous();  
//		} 
//		while (iter.hasNext()) 
//		{ 
//		    iter.set(iter.next() + iter.previous());  
//		    System.out.println(iter.next());  //expected XX DD BB AA RR
//		} 
//		System.out.println(list.toStringInternal()); //expected [(XX, DD, BB, AA), (RR, -, -, -)]
		
//Test: 3 		
		
//		ListIterator<String> iter2 = list.listIterator(list.size());
//		while (iter.nextIndex() < iter2.previousIndex()) 
//		{ 
//		    String s = iter.next();  
//		    String t = iter2.previous();  
//		iter.set(t);      
//		iter2.set(s);  
//		} 
//		System.out.println(list.toStringInternal()); //expected [(R, A, B, D), (X, -, -, -)]
	
// Test: 4
//		ListIterator<String> iter2 = list.listIterator(1);
//		while (iter2.hasNext())  
//		{ 
//		    iter.next();  
//		    iter.set(iter2.next());  
//		    System.out.println(iter.previous());  //expected D B A R
//		} 
//		System.out.println(list.toStringInternal()); // expected [(R, D, B, A), (R, -, -, -)]
		
// Test: 5

//		iter.add("G");
//		iter.next();
//		iter.next();
//		iter.remove();
//		iter.previous();
//		iter.set("T");
//		iter.previous();
//		iter.set("K");
//		list.sort();
//	System.out.println(list.toStringInternal()); // expected [(A, B, K, R), (T, -, -, -)]
	}

}
