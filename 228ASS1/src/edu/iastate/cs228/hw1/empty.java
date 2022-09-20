package edu.iastate.cs228.hw1;

public class empty extends TownCell{

	public empty(Town p, int r, int c) {
		super(p, r, c);
	}

	@Override
	public State who() {
		return State.EMPTY;
	}

	@Override
	public TownCell next(Town tNew) {
		census(nCensus);//calls the census to get the neighborhood values
		
		if((nCensus[OUTAGE]+ nCensus[EMPTY]) < 2) {//Any cell that (1) is not a Reseller or Outage and (2) and has (Number of Empty + Number of Outage neighbors less than or equal to 1) converts to Reseller.
			reseller r = new reseller(tNew,row,col);
			return r;
		}
		else { //If the cell was empty, then a Casual user takes it and it becomes a C
			casual c = new casual(tNew, row, col);
			return c;
		}
	}
}
