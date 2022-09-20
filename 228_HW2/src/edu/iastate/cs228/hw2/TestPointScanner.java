package edu.iastate.cs228.hw2;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import org.junit.jupiter.api.Test;

import edu.iastate.cs228.hw2.Algorithm;
import edu.iastate.cs228.hw2.Point;
import edu.iastate.cs228.hw2.PointScanner;

class TestPointScanner {

	private Algorithm[] algs = {Algorithm.InsertionSort, Algorithm.MergeSort, Algorithm.QuickSort, Algorithm.SelectionSort};
	
	private String path = new File("").getAbsolutePath();
	
	class PSTester extends PointScanner{	// so protected method can be accessed by class
		protected PSTester(String inputFileName, Algorithm algo) throws FileNotFoundException, InputMismatchException {
			super(inputFileName, algo);
		}
	}
	
	@Test
	void testPointScannerThrowsIllegalArgumentException() {
		for(Algorithm al : algs) {
			try{
				new PointScanner(new Point[] {}, al);
				fail("Point scanner not throwing illegal argument exception on " + al.toString() + " when given an empty list");
			} catch (IllegalArgumentException e) {}
		}
		for(Algorithm al : algs) {
			try{
				new PointScanner((Point[])null, al);
				fail("Point scanner not throwing illegal argument exception on " + al.toString() + " when given null");
			} catch (IllegalArgumentException e) {}
		}
	}

	@Test
	void testPointScannerFileNotFound() {
		for(Algorithm al : algs) {
			try {
				new PSTester(path + "/src/edu/iastate/cs228/hw2/JUnitTest1-OddNumberCount.txt", al);
				fail("Point scanner should throw inputmismatchexception for odd number count in file");
			} catch (InputMismatchException e) {
				// do nothing, expected
			} catch (FileNotFoundException e) {
				fail("Make sure file JUnitTest1-OddNumberCount.txt exists in src/junitTests/");
			}
		}
	}
	
	@Test
	void testPointScannerFileDoesntExist() {
		for(Algorithm al : algs) {
			try {
				new PSTester(path + "/src/edu/iastate/cs228/hw2/ThisDoesntExist.txt", al);
				fail("Point scanner should throw inputmismatchexception for odd number count in file");
			} catch (InputMismatchException e) {
				fail("file being read from doesnt exist");
			} catch (FileNotFoundException e) {
				//correct
			}
		}
	}
	@Test
	void testPointScannerScanWithStrangeFormatting() {
		for(Algorithm al : algs) {
			try {
				new PSTester(path + "/src/edu/iastate/cs228/hw2/JUnitTest2-InconsistantNumberBreaks.txt", al);
			} catch (InputMismatchException e) {
				fail("Point scanner should not throw inputmismatchexception for various number breaks");
			} catch (FileNotFoundException e) {
				fail("Make sure file JUnitTest1-InconsistantNumberBreaks.txt exists in src/junitTests/");
			}
		}
	}
	
	@Test 
	void testScan(){
		for(Algorithm al : algs) {
			try {
				PSTester pst = new PSTester(path + "/src/edu/iastate/cs228/hw2/JUnitTest2-InconsistantNumberBreaks.txt", al);
				pst.scan();
				
			} catch (InputMismatchException e) {
				fail("see: testPointScannerScanWithStrangeFormatting");
			} catch (FileNotFoundException e) {
				fail("Make sure file JUnitTest1-InconsistantNumberBreaks.txt exists in src/junitTests/");
			}
		}
	}
	
	@Test
	void testStats() {
//		System.setOut(new PrintStream(new OutputStream() {	 // throws all System.out.println(); to the void
//		    @Override
//		    public void write(int arg0) throws IOException {
//		    }
//		}));
		
		for(Algorithm al : algs) {
			try {
				PSTester pst = new PSTester(path + "/src/edu/iastate/cs228/hw2/JUnitTest2-InconsistantNumberBreaks.txt", al);
				pst.scan();
				
				String out = "";
				out += al.toString();
				for(int i = 0; i < 15-al.toString().length(); i++) {
					out+=" ";
				}
				out += 5;
				System.out.println(out);
				System.out.println( pst.stats().substring(0, out.length()));
				assertEquals(out, pst.stats().substring(0, out.length()));
			} catch (InputMismatchException e) {
				fail("see: testPointScannerScanWithStrangeFormatting");
			} catch (FileNotFoundException e) {
				fail("Make sure file JUnitTest1-InconsistantNumberBreaks.txt exists in src/junitTests/");
			}
		}
	}
	
	@Test
	void testToString() {
		List<Character> chars = new ArrayList<Character>();
		System.setOut(new PrintStream(new OutputStream() {	 // throws all System.out.println(); to the void
		    @Override
		    public void write(int arg0) throws IOException {
		    	chars.add((char) arg0);
		    }
		}));
		
		for(Algorithm al : algs) {
			try {
				PSTester pst = new PSTester(path + "/src/edu/iastate/cs228/hw2/JUnitTest2-InconsistantNumberBreaks.txt", al);
				pst.scan();
				
				assertEquals("MCP: (3, 6)", pst.toString(), "should return in format MCP: (x, y)");
				String build = "";
				for(char c : chars) {
					if(c != '\n') {
						build += c;
					}
				}
				build = build.substring(0, build.length()-1);
				chars.clear();
				assertEquals("MCP: (3, 6)", build, "should print to System.out in format MCP: (x, y)");
			} catch (InputMismatchException e) {
				fail("see: testPointScannerScanWithStrangeFormatting");
			} catch (FileNotFoundException e) {
				fail("Make sure file JUnitTest1-InconsistantNumberBreaks.txt exists in src/junitTests/");
			}
		}
	}
	
	@Test
	void testWriteMCPToFile() {
//		fail("this one should be checked manually");
	}
}
