package mini1;

import java.util.Scanner;

/**
 * Utility class with a bunch of static methods for loop practice.
 * @author smkautz
 */
public class Looperman
{

  // disable instantiation
  private Looperman() {}
  
  /**
   * Returns a string obtained from the given string by doubling all the vowels.
   * For example, given the string "Aardvark", the method returns "AAaardvaark".
   * @param s
   *   given string
   * @return
   *   a new string with all vowels doubled
   */
  public static String doubleAllVowels(String s)
  {
	  String output = "";
	  for (int i = 0; i < s.length(); i++) {
		  char c = s.charAt(i);
		  if (isVowel(c)){
			  output += "" + c + c;
		  }
		  else {
			  output += c;
		  }
	  }
	  
    return output;
  }

  private static boolean isVowel(char c) {
	  
	  return ("aeiouAEIOU".indexOf(c) >= 0);
  }
  
  /**
   * Returns the number of iterations required until <code>n</code>
   * is equal to 1, where each iteration updates <code>n</code> in the following
   * way:
   * <pre>
   *     if n is even
   *         divide it in half
   *     else
   *         multiply n by three and add 1
   * </pre>
   * For example, given <code>n == 6</code>, the successive values
   * of <code>n</code> would be 3, 10, 5, 16, 8, 4, 2, 1, so the method returns 8.
   * If <code>n</code> is less than 1, the method returns -1.
   * <p>
   * <em>(Remark:</em> How do we know this won't be an infinite loop for some values of
   * <code>n</code>?  In general, we don't: in fact this is a well-known open problem in 
   * mathematics.  However, it has
   * been checked for numbers up to 10 billion, which covers the range of possible values 
   * of the Java <code>int</code> type.)  
   * @param n
   *     given starting number
   * @return
   *     number of iterations required to reach <code>n == 1</code>, or
   *     -1 if <code>n</code> is not positive
   */
  public static int collatzCounter(int n)
  {
	  int count = 0;
	  while (n != 1) {
		  if (n % 2 == 0) {
			  n /= 2;
		  }
		  else {
			  n = n * 3 + 1;
		  }
		  if (n < 0) {
			  return -1;
		  }
		  count++;
	  }
	  return count;
  }
  
  
  /**
   * Given a string consisting of a name followed by numbers, returns a 
   * string consisting of the name, followed by a colon and space,
   * followed by the average of the numbers.  For example, given the string
   * <pre>
   * "Edna von Humboldt van der Scooch 10 20 30 40", 
   * </pre>
   * returns the string
   * <pre>
   * "Edna von Humboldt van der Scooch: 25.0"
   * </pre>
   * There is always at least one token for the name and at least one number.
   * The resulting string should have exactly one space between the parts of the name,
   * and should not have any leading or trailing spaces.
   * @param text
   *   string to be parsed
   * @return
   *   name followed by colon, space, and the average of the numbers
   */
  public static String parseNameNumbers(String text) {
	  Scanner scnr = new Scanner(text);
	  String result = "";
	  while(scnr.hasNext()) {
		  int i;
		  int numList = 0;
		  double count = 0.0;
		  String name = "";
		  for(i = 0; i < text.length(); i++) {
			  char c = text.charAt(i);
			  if ('0' <= c && c <= '9') {
				  break;
			  }
			  name = text.substring(0, i);
		  }
		  for(String s : text.substring(i).split(" ")) {
			  numList += Integer.parseInt(s);
			  count++;
		  }
		  result = name + ": " + (numList / count);
		  break;
	  }
    return result;
  }
  
  /**
   * Returns true if string t can be obtained from string s by inserting
   * exactly two characters.  For example, given "banana" and "bannanaa", 
   * the method returns true.
   * @param s
   *   shorter string
   * @param t
   *   longer string
   * @return
   *   true if you can insert two characters into s to make it equal t
   */
  public static boolean differByTwoInsertions(String s, String t){
	  if (s.length() + 2 != t.length()) {
		  return false;
	  }
	  int count = 0;
	  for (int i = 0; i < s.length(); i++) {
		  if (s.charAt(i) != t.charAt(i + count)) {
			  count++;
			  i--;
		  }
		  if(count > 2) {
			  
			  return false;
		  }
		  
	  }
	  return true;
  }
  
  /**
   * Given a string, returns a new string in which text between 
   * the character '+' and the next '-' is converted to uppercase.
   * Any '+' characters encountered while converting to uppercase
   * are ignored, and likewise, any '-' characters encountered
   * without a corresponding preceding '+' are also ignored.
   * If a '+' is encountered that would ordinarily not be ignored, but
   * there is no matching '-' anywhere in the rest of the string, that 
   * '+' is ignored.
   * <p>
   * For example, 
   * <ul>
   * <li> given string "aa+rdv-ark" returns "aaRDVark"
   * <li> given string "aa+r++++dv-a+---+-+r-+k" returns "aaRDVaRk"
   * </ul>
   * @param s
   *   any string
   * @return
   *   new string in which text between matching '+' and '-' characters
   *   has been converted to uppercase
   */
  public static String plusMode(String s){
	  while(s.indexOf("-") != -1 || s.indexOf("+") != -1){
		  int plus = s.indexOf("+");
		  int minus = s.indexOf("-");
		  boolean pin = true;
		  
		  if(minus == -1) {
			  pin = false;
			  s= s.substring(0, plus) + s.substring(plus + 1, s.length());
			  minus = plus;
		  }
		  if(minus < plus || plus == -1) {
			  pin = false;
			  s = s.substring(0, minus) + s.substring(minus + 1, s.length());
		  }
		  if(pin){
			  s = s.substring(0, plus) + s.substring(plus+1, minus).toUpperCase() + s.substring(minus, s.length());  
		  }
	  }
    return s;
  }
  
  /**
   * Returns a new string in which any vowel appearing by itself
   * is doubled, but groups of adjacent vowels are left alone.
   * For example, given the string "beautiful", returns "beautiifuul".
   * @param s
   *   any string
   * @return
   *   new string in which vowels are doubled, unless already part of a group
   *   of multiple vowels
   */
  

  public static String doubleVowelsMaybe(String s) {
	  String result = "";
	  for (int i = 0; i < s.length(); i++) {
		  int j = i;
		  int k = i;
		  if (i != 0) {
			  k = i-1;
		  }
		  if (i != s.length()-1) {
			  j = i + 1;
		  }
		  char c = s.charAt(i);
		  char a = s.charAt(j);
		  char b = s.charAt(k);
		  if (isVowel(c) && !isVowel(a) && !isVowel(b)){
			  result += "" + c + c;
		  }
		  else {
			  result += c;
		  }
	  }
    return result;
  }
  
  

  /**
   * Prints a pattern of n rows containing slashes, backslashes, 
   * underscores and stars, as illustrated below for n = 6.  
   * Note that the bottom row starts and ends with exactly n underscores.
   * 
   * <pre>    
   *            /\
   *           /**\
   *          /****\
   *         /******\
   *        /********\
   * ______/**********\______
   * </pre>
   * 
   * @param n
   *   number of rows in the output
   */
  public static void witchHat(int n) {
	  if (n == 0) {
		  System.out.println("/\\");
	  }
	  for(int i = 0; i < n; i++) {
		  int j;
		if (i == n - 1) {
			for (j = n * 2 - i; j > 1; j--) {
				System.out.print("_");
			}
		}
		for (j = n * 2 - i; j > 1; j--) {
			if (i < n - 1) {
				System.out.print(" ");
			}
		}
		System.out.print("/");
		for (j = 0; j <= i - 1; j++) {
			System.out.print("**");
		}
		System.out.print("\\");
		if( i == n - 1) {
			for (j = n * 2 - i; j > 1; j--) {
				System.out.print("_");
			}
		}
		System.out.println();
	  }
	}
}
