package edu.iastate.cs228.hw1;

public class casual extends TownCell{

	public casual(Town p, int r, int c) {
		super(p, r, c);
	}

	@Override
	public State who() {
		return State.CASUAL;//return the enum state of the towncell.
	}

	@Override
	public TownCell next(Town tNew) {
		census(nCensus);//runs the census to scan the neighborhood and count each number type
		if(nCensus[OUTAGE]+nCensus[EMPTY] < 2) { // Any cell that (1) is not a Reseller or Outage and (2) and has (Number of Empty + Number of Outage neighbors less than or equal to 1) converts to Reseller.
			reseller r = new reseller(tNew,row,col);

			return r;
		}
		else if (nCensus[RESELLER] > 0) {//If there is any reseller in its neighborhood, then the reseller causes outage in the casual user cell. Thus, the state of the cell changes from C (Casual) to O (Outage).
			return new outage(tNew, row, col);
		}
		else if(nCensus[STREAMER]>0) {//Otherwise, if there is any neighbor who is a streamer, then the casual user also becomes a streamer, in the hopes of making it big on the internet.
			streamer s = new streamer(tNew, row, col);

			return s;
		}
		else if(nCensus[CASUAL]>4) {//If none of the above rules apply, any cell with 5 or more casual neighbors becomes a Streamer
			streamer s = new streamer(tNew, row, col);

			return s;
		}
		else {//else it continues to be a casual cell
			casual c = new casual(tNew, row, col);
			return c;
		}
	}

}
