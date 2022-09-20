package edu.iastate.cs228.hw2;

/**
 * 
 * @author Mishari Alharbi
 *
 */

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
/**
 * 
 * This class sorts all the points in an array of 2D points to determine a reference point whose x and y 
 * coordinates are respectively the medians of the x and y coordinates of the original points. 
 * 
 * It records the employed sorting algorithm as well as the sorting time for comparison. 
 *
 */
public class PointScanner  
{
	private Point[] points; 
	
	private Point medianCoordinatePoint;  // point whose x and y coordinates are respectively the medians of 
	                                      // the x coordinates and y coordinates of those points in the array points[].
	private Algorithm sortingAlgorithm;    
	
		
	protected long scanTime; 	       // execution time in nanoseconds. 
	
	/**
	 * This constructor accepts an array of points and one of the four sorting algorithms as input. Copy 
	 * the points into the array points[].
	 * 
	 * @param  pts  input array of points 
	 * @throws IllegalArgumentException if pts == null or pts.length == 0.
	 */
	public PointScanner(Point[] pts, Algorithm algo) throws IllegalArgumentException
	{
		if (pts == null || pts.length == 0) {
			throw new IllegalArgumentException();
		}
		points = new Point[pts.length];
		for (int i = 0; i < pts.length; i++) {
			points[i] = pts[i];
		}
		sortingAlgorithm = algo;
	}

	
	/**
	 * This constructor reads points from a file. 
	 * 
	 * @param  inputFileName
	 * @throws FileNotFoundException 
	 * @throws InputMismatchException   if the input file contains an odd number of integers
	 */
	protected PointScanner(String inputFileName, Algorithm algo) throws FileNotFoundException, InputMismatchException
	{
		sortingAlgorithm = algo;
		File file = new File(inputFileName);
		Scanner scnr = new Scanner(file);
		if(findLength(file )% 2 != 0) {
			scnr.close();
			throw new InputMismatchException();
		}
		int length = findLength(file) / 2;
		points = new Point[length];
		while(scnr.hasNextInt()) {
			for(int i = 0; i < length; i++) {
				int x = scnr.nextInt();
				int y = scnr.nextInt();
				points[i] = new Point(x, y);
			}

		}
		scnr.close();
		
	}

	
	/**
	 * A helper method to find how many numbers the file has to determine the length of the array points;
	 * @param scan - Scanner used to find how many numbers in the file.
	 * @return The length for the array.
	 * @throws FileNotFoundException 
	 */
	private int findLength(File f) throws FileNotFoundException {
		Scanner scan = new Scanner(f);
		ArrayList<Integer> arr = new ArrayList<Integer>();
		while (scan.hasNextInt()) {
			arr.add(scan.nextInt());
		}
		scan.close();
		return arr.size();
	}
	
	
	/**
	 * Carry out two rounds of sorting using the algorithm designated by sortingAlgorithm as follows:  
	 *    
	 *     a) Sort points[] by the x-coordinate to get the median x-coordinate. 
	 *     b) Sort points[] again by the y-coordinate to get the median y-coordinate.
	 *     c) Construct medianCoordinatePoint using the obtained median x- and y-coordinates.     
	 *  
	 * Based on the value of sortingAlgorithm, create an object of SelectionSorter, InsertionSorter, MergeSorter,
	 * or QuickSorter to carry out sorting.       
	 * @param algo
	 * @return
	 */
	public void scan()
	{
		int x;
		int y;
		AbstractSorter aSorter; 
		if (sortingAlgorithm == Algorithm.SelectionSort) {
			aSorter = new SelectionSorter(points);
		}
		else if(sortingAlgorithm == Algorithm.InsertionSort) {
			aSorter = new InsertionSorter(points);
		}
		else if(sortingAlgorithm == Algorithm.MergeSort) {
			aSorter = new MergeSorter(points);
		}
		else {
			aSorter = new QuickSorter(points);
		}
		
		//Sorting the x coordinate.
		long timeX = System.nanoTime();
		aSorter.setComparator(0);
		aSorter.sort();
		timeX = System.nanoTime() - timeX;
		x = aSorter.getMedian().getX();
		
		//Sorting the y coordinate.
		long timeY = System.nanoTime();
		aSorter.setComparator(1);
		aSorter.sort();
		timeY = System.nanoTime() - timeY;
		y = aSorter.getMedian().getY();
		
		medianCoordinatePoint = new Point(x, y);
		
		scanTime = timeX + timeY;
		
	}
	
	
	/**
	 * Outputs performance statistics in the format: 
	 * 
	 * <sorting algorithm> <size>  <time>
	 * 
	 * For instance, 
	 * 
	 * selection sort   1000	  9200867
	 * 
	 * Use the spacing in the sample run in Section 2 of the project description. 
	 */
	public String stats()
	{
		String algo;
		if (sortingAlgorithm == Algorithm.SelectionSort) {
			algo = "SelectionSort";
		}
		else if(sortingAlgorithm == Algorithm.InsertionSort) {
			algo = "InsertionSort";
		}
		else if(sortingAlgorithm == Algorithm.MergeSort) {
			algo = "MergeSort    ";
		}
		else {
			algo = "QuickSort    ";
		}
		return algo + "  " + points.length * 2 + "  " + scanTime; 
	}
	
	
	/**
	 * Write MCP after a call to scan(),  in the format "MCP: (x, y)"   The x and y coordinates of the point are displayed on the same line with exactly one blank space 
	 * in between. 
	 */
	@Override
	public String toString()
	{
		return "MCP: " + "(" + medianCoordinatePoint.getX() + ", " + medianCoordinatePoint.getY() + ")";
	}

	
	/**
	 *  
	 * This method, called after scanning, writes point data into a file by outputFileName. The format 
	 * of data in the file is the same as printed out from toString().  The file can help you verify 
	 * the full correctness of a sorting result and debug the underlying algorithm. 
	 * 
	 * @throws FileNotFoundException
	 */
	public void writeMCPToFile() throws FileNotFoundException
	{
		File file = new File("MCPTest.txt");
		PrintWriter writer = new PrintWriter(file);
		writer.print(this.toString());
		writer.print("\n");
		writer.close();
	}	
		
}
