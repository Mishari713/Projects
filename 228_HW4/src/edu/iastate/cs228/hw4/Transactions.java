package edu.iastate.cs228.hw4;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Scanner;
/**
 *  
 * @author Mishari Alharbi
 *
 */

/**
 * 
 * The Transactions class simulates video transactions at a video store.
 *
 */
public class Transactions {

/**
 * The main method generates a simulation of rental and return activities.
 * 
 * @param args
 * @throws FileNotFoundException
 */
public static void main(String[] args) throws FileNotFoundException, IllegalArgumentException,
		FilmNotInInventoryException, AllCopiesRentedOutException {
	
	//
	// 1. Construct a VideoStore object.
	// 2. Simulate transactions as in the example given in Section 4 of the
	// the project description.
	String input = "";
	String s = "";
	VideoStore V = new VideoStore("videoList1.txt");
	System.out.println("Transactions at a Video Store\r\n"
			+ "keys: 1 (rent) 2 (bulk rent)\r\n"
			+ "3 (return) 4 (bulk return)\r\n"
			+ "5 (summary) 6 (exit)");
	Scanner scanI = new Scanner(System.in);
	Scanner scanS = new Scanner(System.in);
	int key = 0;
	while(key != 6){
		System.out.print("\nTransaction: ");
		key = scanI.nextInt();
		if (key == 1) {
			System.out.print("Film to rent: ");
			input = scanS.nextLine();
			try
			{
				s = "";
				V.videoRent(parseFilmName(input), parseNumCopies(input));
			}
			catch(IllegalArgumentException e) {
				s += e.getMessage() + "\n";
			}
			catch(FilmNotInInventoryException e)
			{
				s += e.getMessage() + "\n";
			}
			catch(AllCopiesRentedOutException e)
			{
				s += e.getMessage() + "\n";
			}
			System.out.print(s);
		}
		else if (key == 2) {
			System.out.print("Video file (rent): ");
			input = scanS.nextLine();
			try {
				s = "";
				V.bulkRent(input);
			}
			catch(FileNotFoundException e)
			{
				s += e.getMessage() + "\n";
			}
			System.out.print(s);
		}
		else if (key == 3) {
			System.out.print("Film to return: ");
			input = scanS.nextLine();
			try {
				s= "";
				V.videoReturn(parseFilmName(input), parseNumCopies(input));
			}
			catch(IllegalArgumentException e)
			{
				s += e.getMessage() + "\n";
			}
			catch(FilmNotInInventoryException e)
			{
				s += e.getMessage() + "\n";
			}
			System.out.print(s);

		}
		else if (key == 4) {
			System.out.print("Video file (return): ");
			input = scanS.nextLine();
			try {
				s = "";
				V.bulkReturn(input);
			}
			catch(FileNotFoundException e)
			{
				s += e.getMessage() + "\n";
			}
			System.out.print(s);
		}
		else if (key == 5) {
			System.out.println(V.transactionsSummary());
		}
		else if (key == 6) {
			scanS.close();
			System.exit(0);
		}
	}
	scanI.close();
}


/**
 * Parse the number of copies from an input line. 
 * 
 * @param line
 * @return int number of copies parsed out of line
 */
private static int parseNumCopies(String line) {
	if (line.contains("(") && line.contains(")")) {
		return Integer.parseInt(line.substring(line.indexOf("(") + 1, line.indexOf(")")));
	}
	return 1;
}
/**
 * Parse the film name from an input line. 
 * 
 * @param line
 * @return string the name of the film
 */
private static String parseFilmName(String line) {
	if (line.contains("(") && line.contains(")")) {
		return line.substring(0, line.indexOf("(") - 1);
	}
	return line;
}
}
