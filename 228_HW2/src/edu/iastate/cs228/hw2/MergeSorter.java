package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException;
import java.lang.IllegalArgumentException;
import java.util.InputMismatchException;

/**
 *  
 * @author Mishari Alharbi
 *
 */

/**
 * 
 * This class implements the mergesort algorithm.
 *
 */
public class MergeSorter extends AbstractSorter {

/**
 * Constructor takes an array of points. It invokes the superclass constructor,
 * and also set the instance variables algorithm in the superclass.
 * 
 * @param pts input array of integers
 */
public MergeSorter(Point[] pts) {
	super(pts);
	algorithm = "mergesort";
}

/**
 * Perform mergesort on the array points[] of the parent class AbstractSorter.
 * 
 */
@Override
public void sort() {
	mergeSortRec(points);
}

/**
 * This is a recursive method that carries out mergesort on an array pts[] of
 * points. One way is to make copies of the two halves of pts[], recursively
 * call mergeSort on them, and merge the two sorted subarrays into pts[].
 * 
 * @param pts point array
 */
private void mergeSortRec(Point[] pts) {
	if (pts.length <= 1) {
		return;
	}
	
	int midIndex = pts.length / 2;
	Point[] left = new Point[midIndex];
	for (int i = 0; i < midIndex; i++) {
		left[i] = pts[i];
	}
	
	Point[] right = new Point[pts.length - midIndex];
	for (int i = 0; i < pts.length - midIndex; i++) {
		right[i] = pts[i + midIndex];
	}
	mergeSortRec(left);
	mergeSortRec(right);
	
	// Merging the two sides
	int i, j, k;
	i = j = k = 0;
	while (i < left.length || j < right.length) {
		if (i < left.length && j < right.length) {
			if (left[i].compareTo(right[j]) < 0) {
				pts[k++] = left[i++];
			} else {
				pts[k++] = right[j++];
			}
		} else if (i >= left.length) {
			pts[k++] = right[j++];
		} else {
			pts[k++] = left[i++];
		}
	}
}

}
