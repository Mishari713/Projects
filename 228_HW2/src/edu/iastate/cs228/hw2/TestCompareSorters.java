package edu.iastate.cs228.hw2;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import edu.iastate.cs228.hw2.CompareSorters;
import edu.iastate.cs228.hw2.Point;

class TestCompareSorters {

	@Test
	void testMain() {
		/*
		 * note that the file input is entered as
		 * [/src/junitTests/JUnitTest2-InconsistantNumberBreaks.txt] by default, so if
		 * your file is in a different location, you may need to change this
		 * 
		 * note2: formatting for this is slightly off.
		 * 
		 * note3: if you end the program by calling System.exit(0);, not all of the tests will finish, so just comment out this test.
		 */
		String file = "/src/junitTests/JUnitTest2-InconsistantNumberBreaks.txt";
		byte[] line = ("1\n20\n2\n"+file+"\n3").getBytes();
		
		System.setIn(new InputStream() {
			int index = -1;
			@Override
			public int read() throws IOException {
				index++;
				if(index < line.length) {
					return line[index];
				} else {
					return '\n';
				}
			}
		});
		
		try {
			CompareSorters.main(null);
		} catch (FileNotFoundException e) {
			fail("Check File locations, and path in test file, error reading from file");
		}

	}
	
	@Test
	void testGenerateRandomPoints() {
		Point[] pts = CompareSorters.generateRandomPoints(50, new Random());
		for(Point p : pts) {
			if(p == null) {
				fail("list not filled");
			}
		}
		if(pts.length != 50) {
			fail("incorrect array length");
		}
		
		pts = CompareSorters.generateRandomPoints(100, new Random());
		if(pts.length != 100) {
			fail("incorrect array length");
		}
		for(Point p : pts) {
			if(p == null) {
				fail("list not filled");
			}
		}
	}

}
