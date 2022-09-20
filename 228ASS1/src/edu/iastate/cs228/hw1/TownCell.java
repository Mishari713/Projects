package edu.iastate.cs228.hw1;

/**
 * 
 * @author Sami Bensellam
 *	Also provide appropriate comments for this class
 *
 */
public abstract class TownCell {

	protected Town plain;
	protected int row;
	protected int col;
	
	
	// constants to be used as indices.
	protected static final int RESELLER = 0;
	protected static final int EMPTY = 1;
	protected static final int CASUAL = 2;
	protected static final int OUTAGE = 3;
	protected static final int STREAMER = 4;
	
	public static final int NUM_CELL_TYPE = 5;
	
	//Use this static array to take census.
	public static final int[] nCensus = new int[NUM_CELL_TYPE];

	public TownCell(Town p, int r, int c) {
		plain = p;
		row = r;
		col = c;
	}
	
	/**
	 * Checks all neigborhood cell types in the neighborhood.
	 * Refer to homework pdf for neighbor definitions (all adjacent
	 * neighbors excluding the center cell).
	 * Use who() method to get who is present in the neighborhood
	 *  
	 * @param counts of all customers
	 */
	public void census(int nCensus[]) {
		// zero the counts of all customers
		nCensus[RESELLER] = 0; 
		nCensus[EMPTY] = 0; 
		nCensus[CASUAL] = 0; 
		nCensus[OUTAGE] = 0; 
		nCensus[STREAMER] = 0; 
		
		if(checkBounds(plain, row,col) == 0) {//calls check bounds which figures out if the TownCell is top left or top right or bottom left or bottom right or left top right or bottom
			compare(plain.grid[row][col+1].who());//right
			compare(plain.grid[row+1][col+1].who());//bottom right
			compare(plain.grid[row+1][col].who());//bottom

		}
		if(checkBounds(plain, row,col) == 1) {
			compare(plain.grid[row][col-1].who());//left
			compare(plain.grid[row+1][col-1].who());//bottom left
			compare(plain.grid[row+1][col].who());//bottom
		}
		if(checkBounds(plain, row,col) == 2) {
			compare(plain.grid[row][col+1].who());//right
			compare(plain.grid[row-1][col+1].who());//top right
			compare(plain.grid[row-1][col].who());//top

		}
		if(checkBounds(plain, row,col) == 3) {
			compare(plain.grid[row][col-1].who());//left
			compare(plain.grid[row-1][col-1].who());//top left
			compare(plain.grid[row-1][col].who());//top

		}
		if(checkBounds(plain, row,col) == 4) {
			compare(plain.grid[row+1][col].who());//bottom
			compare(plain.grid[row+1][col+1].who());//bottom right
			compare(plain.grid[row][col+1].who());//right
			compare(plain.grid[row-1][col].who());//top
			compare(plain.grid[row-1][col+1].who());//top right

		}
		if(checkBounds(plain, row,col) == 5) {
			compare(plain.grid[row][col+1].who());//right
			compare(plain.grid[row+1][col+1].who());//bottom right
			compare(plain.grid[row+1][col].who());//bottom
			compare(plain.grid[row][col-1].who());//left
			compare(plain.grid[row+1][col-1].who());//bottom left

		}
		if(checkBounds(plain, row,col) == 6) {
			compare(plain.grid[row-1][col+1].who());//top right
			compare(plain.grid[row][col+1].who());//right
			compare(plain.grid[row-1][col].who());//top
			compare(plain.grid[row-1][col-1].who());//top left
			compare(plain.grid[row][col-1].who());//left

			
		}
		if(checkBounds(plain, row,col) == 7) {
			compare(plain.grid[row+1][col].who());//right
			compare(plain.grid[row-1][col].who());//top
			compare(plain.grid[row-1][col-1].who());//top left
			compare(plain.grid[row][col-1].who());//left
			compare(plain.grid[row+1][col-1].who());//bottom left
		}
		if(checkBounds(plain, row,col) == 8) {
			compare(plain.grid[row+1][col+1].who());//bottom right
			compare(plain.grid[row][col+1].who());//right
			compare(plain.grid[row-1][col+1].who());//top right
			compare(plain.grid[row+1][col].who());//bottom
			compare(plain.grid[row-1][col].who());//top
			compare(plain.grid[row+1][col-1].who());//bottom left
			compare(plain.grid[row][col-1].who());//left
			compare(plain.grid[row-1][col-1].who());//top left

		}
	}
	public void compare(State input) {//checks the neighborhood cell types and adds the type to the census array
		if(input == State.RESELLER) {
			nCensus[RESELLER]++;

		}
		else if (input == State.EMPTY) {
			nCensus[EMPTY]++;

		}
		else if (input == State.CASUAL) {
			nCensus[CASUAL]++;

		}
		else if (input == State.OUTAGE) {
			nCensus[OUTAGE]++;

		}
		else if (input == State.STREAMER) {
			nCensus[STREAMER]++;

		}

	}
	
	

	/**
	 * Gets the identity of the cell.
	 * 
	 * @return State
	 */
	public abstract State who();

	/**
	 * Determines the cell type in the next cycle.
	 * 
	 * @param tNew: town of the next cycle
	 * @return TownCell
	 */
	public abstract TownCell next(Town tNew);
	
	
	public int checkBounds(Town c, int x, int y) {//checks the cells position to not go out of bounds
		if(x == 0 && y == 0) {
		return 0;
	}
		if(x == 0 && y == c.getWidth()-1) {
		return 1;
	}
		if(x == c.getLength()-1 && y == 0) {
		return 2;
	}
		if(x == c.getLength()-1 && y == c.getWidth()-1) {
		return 3;
	}
		if(y == 0) {
		return 4;
	}
		if(x == 0) {
		return 5;
	}
		if(x == c.getLength()-1) {
		return 6;
	}
		if(y == c.getWidth()-1) {
		return 7;
	}
		
		return 8;
	}
	
	public int[] returnCensus() {//this is for my census test method it checks if the census is correct
		census(nCensus);//calls the census for the neighborhood of the towncell
		return nCensus;//returns the array census
	}
	
}
