package edu.iastate.cs228.hw1;

public class reseller extends TownCell{

	public reseller(Town p, int r, int c) {
		super(p, r, c);
	}

	@Override
	public State who() {
		return State.RESELLER;
	}

	//returns the updated towncell 
	@Override
	public TownCell next(Town tNew) {
		census(nCensus);//returns the census
		if(nCensus[CASUAL]<4) {//If there are 3 or fewer casual users in the neighborhood, then Reseller finds it unprofitable to maintain the business and leaves, making the cell Empty.
			empty e = new empty(tNew, row, col);
			
			return e;
		}
		if(nCensus[EMPTY]>2) {//Also, if there are 3 or more empty cells in the neighborhood, then the Reseller leaves, making the cell Empty. Resellers do not like unpopulated regions.
			empty e = new empty(tNew, row, col);
			
			return e;
		}
		if(nCensus[CASUAL]>4) {//If none of the above rules apply, any cell with 5 or more casual neighbors becomes a Streamer
			streamer s = new streamer(tNew, row, col);
			
			return s;
		}		
		else {//if none of the above conditions happen this towncell continues to be a reseller
			reseller r = new reseller(tNew,row,col);
			return r;
		}
		}

}
