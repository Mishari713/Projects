package edu.iastate.cs228.hw1;

public class streamer extends TownCell {

	public streamer(Town p, int r, int c) {
		super(p, r, c);

	}

	@Override
	public State who() {
		return State.STREAMER;
	}

	// retuns the updated towncell
	@Override
	public TownCell next(Town tNew) {
		census(nCensus);//runs census
		if(nCensus[OUTAGE]+nCensus[EMPTY] < 2) {//Any cell that (1) is not a Reseller or Outage and (2) and has (Number of Empty + Number of Outage neighbors less than or equal to 1) converts to Reseller.
			reseller r = new reseller(tNew,row,col);
			
			return r;
		}
		else if (nCensus[RESELLER] > 0) {//If there is any reseller in the neighborhood, the reseller causes outage for the streamer as well.
			return new outage(tNew, row, col);
		}
		
		else if (nCensus[OUTAGE]>0) {//Otherwise, if there is any Outage in the neighborhood, then the streamer leaves and the cell becomes Empty
			empty e = new empty(tNew, row, col);
			return e;
		}
		else { //if none of the conditions above happen the cell continues to be a streamer doesnt matter if he has 5 or more casuals he is already a streamer
			streamer s = new streamer(tNew, row, col);

		return s;
	}
	}

}
