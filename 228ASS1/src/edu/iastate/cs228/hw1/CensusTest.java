package edu.iastate.cs228.hw1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CensusTest {

	public static final int[] nCensusTest = new int[5];

	
	@Test
	void testnext1() {//tests the census counter with a random 3 by 3 array to see if it returns the expected output.
		Town weewee = new Town(3,3);
		TownCell TestCell = new casual(weewee,1 ,1 );
		TownCell TestCell2 = new casual(weewee,0 ,0 );
		TownCell TestCell3 = new outage(weewee,0 ,1 );
		TownCell TestCell4 = new reseller(weewee,0 ,2 );
		TownCell TestCell5 = new streamer(weewee,1 ,0 );
		TownCell TestCell6 = new casual(weewee,1 ,2 );
		TownCell TestCell7 = new casual(weewee,2 ,0 );
		TownCell TestCell8 = new casual(weewee,2 ,1 );
		TownCell TestCell9 = new casual(weewee,2 ,2 );
		weewee.grid[1][1] = TestCell;
		weewee.grid[0][0] = TestCell2;
		weewee.grid[0][1] = TestCell3;
		weewee.grid[0][2] = TestCell4;
		weewee.grid[1][0] = TestCell5;
		weewee.grid[1][2] = TestCell6;
		weewee.grid[2][0] = TestCell7;
		weewee.grid[2][1] = TestCell8;
		weewee.grid[2][2] = TestCell9;
//RECOS
		nCensusTest[0] = 1;//EXPECTED RESELLER
		nCensusTest[1] = 0;//EXPECTED EMPTY
		nCensusTest[2] = 5;//EXPECTED CASUAL
		nCensusTest[3] = 1;//EXPECTED OUTAGE
		nCensusTest[4] = 1;//EXPPECTED STREAMER

		
		assertEquals( nCensusTest[0] , weewee.grid[1][1].returnCensus()[0]);//runs the functions return census to acces the array ran and compares the returning variable with the expected ones
		assertEquals( nCensusTest[1] , weewee.grid[1][1].returnCensus()[1]);//this took so much uneccessary time to figure out if I dont get full credit for this assignment I will skin a sheep alive this is a warning 
		assertEquals( nCensusTest[2] , weewee.grid[1][1].returnCensus()[2]);//Im jk
		assertEquals( nCensusTest[3] , weewee.grid[1][1].returnCensus()[3]);
		assertEquals( nCensusTest[4] , weewee.grid[1][1].returnCensus()[4]);

	}
}


