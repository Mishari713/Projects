package lab2;

public class StringTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String message;
		message = "Hello, World!";
		System.out.println(message);
		int theLength = message.length();
		System.out.println(theLength);
		
		char theChar = message.charAt(0);
		System.out.println(theChar);
		
		theChar = message.charAt(1);
		System.out.println(theChar);
		
		theChar = message.charAt(12);
		System.out.println(theChar);
		
		String S = message.toUpperCase();
		System.out.println(S);
		
		S = message.substring(0, 5);
		System.out.println(S);
		

	}

}
