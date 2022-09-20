package edu.iastate.cs228.hw1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class OutageTest {

	
	@Test
	void test() {//tests if outage initializes properly and tests the who function
		Town weewee = new Town(3,3);
		TownCell TestCell = new outage(weewee,1 ,1 );
		assertEquals( State.OUTAGE , TestCell.who());//compares it with the expected output

	}
		
	@Test
	void test2() {
		Town weewee = new Town(3,3);
		TownCell TestCell = new outage(weewee,1 ,1 );
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
		
		assertEquals( State.EMPTY , weewee.grid[1][1].who());//tests outage's next method with a random neighborhood testing all the possibilities
		}

}
