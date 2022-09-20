package edu.iastate.cs228.hw1;

public class outage extends TownCell{

	public outage(Town p, int r, int c) {
		super(p, r, c);
		// TODO Auto-generated constructor stub
	}

	@Override
	public State who() {
		// TODO Auto-generated method stub
		return State.OUTAGE;
	}

	@Override
	public TownCell next(Town tNew) {
		census(nCensus);//calls census to get the neighbor hood of this towncell.
	empty e = new empty(tNew,row,col);//An Outage cell becomes an Empty cell, meaning internet access is restored on the billing cycle after an outage.
		return e;
	}



}
