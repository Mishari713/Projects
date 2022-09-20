package edu.iastate.cs228.hw1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CasualTest {

	@Test
	void test() {
		Town weewee = new Town(3,3);
		TownCell TestCell = new casual(weewee,1 ,1 );
		assertEquals( State.CASUAL , TestCell.who());//checks if the casual cell initializes properly

	}
	@Test
	void testnext1() {//runs through every possible iteration of the next if statements for casual in the next 5 tests
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

		weewee = ISPBusiness.updatePlain(weewee);
		
		assertEquals( State.RESELLER , weewee.grid[1][1].who());

}
	@Test
	void testnext2() {
		Town weewee = new Town(3,3);
		TownCell TestCell = new casual(weewee,1 ,1 );
		TownCell TestCell2 = new outage(weewee,0 ,0 );
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

		weewee = ISPBusiness.updatePlain(weewee);
		
		assertEquals( State.OUTAGE , weewee.grid[1][1].who());

}
	@Test
	void testnext3() {
		Town weewee = new Town(3,3);
		TownCell TestCell = new casual(weewee,1 ,1 );
		TownCell TestCell2 = new outage(weewee,0 ,0 );
		TownCell TestCell3 = new outage(weewee,0 ,1 );
		TownCell TestCell4 = new outage(weewee,0 ,2 );
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

		weewee = ISPBusiness.updatePlain(weewee);
		
		assertEquals( State.STREAMER , weewee.grid[1][1].who());

}
	@Test
	void testnext4() {
		Town weewee = new Town(3,3);
		TownCell TestCell = new casual(weewee,1 ,1 );
		TownCell TestCell2 = new outage(weewee,0 ,0 );
		TownCell TestCell3 = new outage(weewee,0 ,1 );
		TownCell TestCell4 = new outage(weewee,0 ,2 );
		TownCell TestCell5 = new casual(weewee,1 ,0 );
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

		weewee = ISPBusiness.updatePlain(weewee);
		
		assertEquals( State.STREAMER , weewee.grid[1][1].who());

}
	@Test
	void testnext5() {
		Town weewee = new Town(3,3);
		TownCell TestCell = new casual(weewee,1 ,1 );
		TownCell TestCell2 = new empty(weewee,0 ,0 );
		TownCell TestCell3 = new empty(weewee,0 ,1 );
		TownCell TestCell4 = new empty(weewee,0 ,2 );
		TownCell TestCell5 = new empty(weewee,1 ,0 );
		TownCell TestCell6 = new empty(weewee,1 ,2 );
		TownCell TestCell7 = new empty(weewee,2 ,0 );
		TownCell TestCell8 = new empty(weewee,2 ,1 );
		TownCell TestCell9 = new empty(weewee,2 ,2 );
		weewee.grid[1][1] = TestCell;
		weewee.grid[0][0] = TestCell2;
		weewee.grid[0][1] = TestCell3;
		weewee.grid[0][2] = TestCell4;
		weewee.grid[1][0] = TestCell5;
		weewee.grid[1][2] = TestCell6;
		weewee.grid[2][0] = TestCell7;
		weewee.grid[2][1] = TestCell8;
		weewee.grid[2][2] = TestCell9;

		weewee = ISPBusiness.updatePlain(weewee);
		
		assertEquals( State.CASUAL , weewee.grid[1][1].who());

}
	

	
}