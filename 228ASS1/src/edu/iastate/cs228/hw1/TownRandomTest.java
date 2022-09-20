package edu.iastate.cs228.hw1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TownRandomTest {//tests both random Init and town with the two parameters

	@Test
	void test() {//tests the town with the inputs and tests the random generator and compares its tostring with the expected output
		Town weewee = new Town(4,4);
		weewee.randomInit(10);
		 assertEquals(weewee.toString(),"O R O R \n"
			 		+ "E E C O \n"
			 		+ "E S O S \n"
			 		+ "E O R R \n");

	}
	
	@Test
	void test2() {//tests the getwidth and compares it with the expected result
		Town weewee = new Town(5,6);
		int i = weewee.getLength();
		assertEquals(5,i);
	}
	
	
	@Test
	void test3() {//tests the getlength and compares it with the expected result
		Town weewee = new Town(5,6);
		int i = weewee.getWidth();
		assertEquals(6,i);
	}

}
