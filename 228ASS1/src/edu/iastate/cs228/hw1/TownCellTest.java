package edu.iastate.cs228.hw1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TownCellTest {

	@Test
	void test() {//tests if towncell works properly by initializing a random towncell class 
Town weewee = new Town(3,3);
		TownCell TestCell = new casual(weewee, 0 , 3);
		assertEquals( State.CASUAL , TestCell.who());//compares it with the expected result
		
	}

}
