package edu.iastate.cs228.hw1;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Sami Bensellam
 *
 * The ISPBusiness class performs simulation over a grid 
 * plain with cells occupied by different TownCell types.
 *
 */
public class ISPBusiness {

	/**
	 * Returns a new Town object with updated grid value for next billing cycle.
	 * @param tOld: old/current Town object.
	 * @return: New town object.
	 */
	public static Town updatePlain(Town tOld) {
		Town tNew = new Town(tOld.getLength(), tOld.getWidth());
		for(int i = 0 ; i < tNew.getLength(); i++) {//runs a 2d loop to get through all the the Town grids
			for(int j = 0 ; j < tNew.getWidth(); j++) {
				tNew.grid[i][j] = tOld.grid[i][j].next(tNew); //initializes the new grid with the next of each towncell of the old grid 
				
			}
		}
		
		return tNew;//returns the new town with the next cells of the old grid.
	}
	
	/**
	 * Returns the profit for the current state in the town grid.
	 * @param town
	 * @return
	 */
	public static int getProfit(Town town) {
		int profit = 0;
		for(int i = 0 ; i < town.getLength(); i++) {//runs a 2d grid and compares each cell to casual if a cell is casual it adds a one to the int profit
			for(int j = 0 ; j < town.getWidth(); j++) {
			
				if(town.grid[i][j].who() == State.CASUAL) {
				profit++;
			}
			
			}
			}
		return profit;//returns the profit
	}
	

	/**
	 *  Main method. Interact with the user and ask if user wants to specify elements of grid
	 *  via an input file (option: 1) or wants to generate it randomly (option: 2).
	 *  
	 *  Depending on the user choice, create the Town object using respective constructor and
	 *  if user choice is to populate it randomly, then populate the grid here.
	 *  
	 *  Finally: For 12 billing cycle calculate the profit and update town object (for each cycle).
	 *  Print the final profit in terms of %. You should print the profit percentage
	 *  with two digits after the decimal point:  Example if profit is 35.5600004, your output
	 *  should be:
	 *
	 *	35.56%
	 *  
	 * Note that this method does not throw any exception, so you need to handle all the exceptions
	 * in it.
	 * 
	 * @param args
	 * @throws FileNotFoundException 
	 * 
	 */
	public static void main(String []args) {
		Town weewee;
		System.out.println("How to populate grid (type 1 or 2): 1: from a file. 2: randomly with seed");//prints out the instructions
		Scanner scan = new Scanner(System.in);//scans the user input using the scanner
		int d = scan.nextInt();
		int totalprofit = 0;//initializes the total profit to zero
		int iterations_number = 12;//intializes the number of iterations
		if(d == 1) {
		try {
			System.out.println("Enter file path");//prints out instructions
			Scanner sc = new Scanner(System.in);//scans the string user input using .next
			String filepath = sc.next();
			weewee = new Town(filepath);
			
			for (int i =0; i< iterations_number;i++) {//runs the loop
				
//				System.out.println(weewee.toString());
//				System.out.println("Profit:"+getProfit(weewee));
//				System.out.println("After itr:"+ (i+1));
				weewee = updatePlain(weewee);//updates the town
				totalprofit += getProfit(weewee);//adding the profit from each town to the total count

			
		}
System.out.println(String.format("%.2f",(double)( totalprofit*100 / (double)(weewee.getLength()*weewee.getWidth()*(iterations_number)) ))+ "% profit utilization" );//prints out the total profit utilization

		}
		catch (FileNotFoundException b) {
			
		}
		

		}
		else {
			System.out.println("Provide rows, cols and seed integer separated by space:");//same thing as the first option but instead the town is generated randomly according to the seed input
			Scanner sc = new Scanner(System.in);
			String inputs = sc.nextLine();
			Scanner scaan = new Scanner(inputs);
			int X = scaan.nextInt();
			int Y = scaan.nextInt();
			int Z = scaan.nextInt();
			
			weewee = new Town(X,Y);
			weewee.randomInit(Z);
			//totalprofit += getProfit(weewee);

			for (int i =0; i< iterations_number;i++) {
				
//				System.out.println(weewee.toString());
//				System.out.println("Profit:"+getProfit(weewee));
//				System.out.println("After itr:"+ (i+1));
				weewee = updatePlain(weewee);
				totalprofit += getProfit(weewee);


		}
		
System.out.println(String.format("%.2f",(double)( totalprofit*100 / (double)(weewee.getLength()*weewee.getWidth()*(iterations_number)) ))+ "% profit utilization" );

			
sc.close();
scaan.close();//closes the scanners
		}
scan.close();	
	}
}
