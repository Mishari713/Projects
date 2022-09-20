package edu.iastate.cs228.hw2;

/**
 *  
 * @author Mishari Alharbi
 *
 */

/**
 * 
 * This class executes four sorting algorithms: selection sort, insertion sort, mergesort, and
 * quicksort, over randomly generated integers as well integers from a file input. It compares the 
 * execution times of these algorithms on the same input. 
 *
 */

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;

public class CompareSorters {
/**
 * Repeatedly take integer sequences either randomly generated or read from
 * files. Use them as coordinates to construct points. Scan these points with
 * respect to their median coordinate point four times, each time using a
 * different sorting algorithm.
 * 
 * @param args
 **/
public static void main(String[] args) throws FileNotFoundException {

	System.out.println("Performances of Four Sorting Algorithms in Point Scanning\n"
			+ "keys: 1 (random integers)  2 (file input)  3 (exit)\n");
	int trialCount = 1;
	Scanner scan = new Scanner(System.in);
	int input = 0;
	Point[] points;
	PointScanner[] scanners = new PointScanner[4];
	while (input != 3) {
		System.out.print("Trail " + trialCount + ": ");
		input = scan.nextInt();
		if (input == 1) {
			System.out.print("Enter number of random points: ");
			int rpoints = scan.nextInt();
			Random generator = new Random();
			points = generateRandomPoints(rpoints, generator);
			scanners[0] = new PointScanner(points, Algorithm.SelectionSort);
			scanners[1] = new PointScanner(points, Algorithm.InsertionSort);
			scanners[2] = new PointScanner(points, Algorithm.MergeSort);
			scanners[3] = new PointScanner(points, Algorithm.QuickSort);
		}

		if (input == 2) {
			System.out.print("Points from a file\n" + "File name: ");
			String fileName = scan.next();
			PointScanner scnr1 = new PointScanner(fileName, Algorithm.SelectionSort);
			PointScanner scnr2 = new PointScanner(fileName, Algorithm.InsertionSort);
			PointScanner scnr3 = new PointScanner(fileName, Algorithm.MergeSort);
			PointScanner scnr4 = new PointScanner(fileName, Algorithm.QuickSort);
			scanners[0] = scnr1;
			scanners[1] = scnr2;
			scanners[2] = scnr3;
			scanners[3] = scnr4;
		}
		if (input != 3) {
			for (int i = 0; i < scanners.length; i++) {
				scanners[i].scan();
				scanners[i].writeMCPToFile();
			}
			System.out.println("algorithm size  time (ns) \r\n" + "----------------------------------");
			for (int i = 0; i < scanners.length; i++) {
				System.out.println(scanners[i].stats());
			}
			System.out.println("----------------------------------");
			trialCount++;
		}
	}
	scan.close();

}

/**
 * This method generates a given number of random points. The coordinates of
 * these points are pseudo-random numbers within the range [-50,50] × [-50,50].
 * Please refer to Section 3 on how such points can be generated.
 * 
 * Ought to be private. Made public for testing.
 * 
 * @param numPts number of points
 * @param rand   Random object to allow seeding of the random number generator
 * @throws IllegalArgumentException if numPts < 1
 */
public static Point[] generateRandomPoints(int numPts, Random rand) throws IllegalArgumentException {
	int length = numPts / 2;
	Point[] points = new Point[length];
	for (int i = 0; i < length; i++) {
		int x = rand.nextInt(101) - 50;
		int y = rand.nextInt(101) - 50;
		points[i] = new Point(x, y);
	}

	return points;
}

}
