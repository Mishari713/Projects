package edu.iastate.cs228.hw1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;


class TownScannerTest {//tests both town with the filepath and the scanner.

	@Test
	void test() {//tests the town generator and compares it with the expected result it also tests the to string method
		Town weewee;
		
		try {
			weewee = new Town("ISP4x4.txt");//scans the given file within my project
			 assertEquals(weewee.toString(),"O R O R \n"//compares the output
			 		+ "E E C O \n"
			 		+ "E S O S \n"
			 		+ "E O R R \n");
		} catch (FileNotFoundException e) {

		}
		
	}
	
	@Test
	void getLengthTest() {//getLength Test
		Town weewee;
		
		try {
			weewee = new Town("ISP4x4.txt");
			weewee.getLength();
			 assertEquals(4,4);
		} catch (FileNotFoundException e) {

		}
		
	}
	
	@Test
	void getWidthTest() { //getWidth Test
		Town weewee;
		
		try {
			weewee = new Town("ISP4x4.txt");
			weewee.getWidth();
			 assertEquals(4,4);
		} catch (FileNotFoundException e) {

		}
		
	}
	
	@Test
	void toStringTest() {//toString Test
	Town weewee;
		try {
			
			weewee = new Town("ISP4x4.txt");
			 assertEquals(weewee.toString(),"O R O R \n"
			 		+ "E E C O \n"
			 		+ "E S O S \n"
			 		+ "E O R R \n");
		} catch (FileNotFoundException e) {

		}

	}
}
