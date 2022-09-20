package mini2;

import java.util.ArrayList;

import java.util.Arrays;

public class ArrayOfSunshine {

// disable instantiation
private ArrayOfSunshine() {
}

/**
 * Returns the longest string that is a prefix of all strings in the given
 * array. For example, if the array is ["foo", "four", "football"], the method
 * returns "fo". If the array is empty, returns an empty string.
 * 
 * @param arr any array of strings.
 * @return longest common prefix of the given strings
 */
public static String longestCommonPrefix(String[] arr) {
	String result = "";
	if (arr.length > 0) { // Check if array length is one, do we print out the string??
		int smallest = arr[0].length();
		for (int i = 1; i < arr.length; i++) {
			if (arr[i].length() < smallest) {
				smallest = arr[i].length();
			}
		}
		boolean check = true;
		for (int j = 0; j < smallest; j++) {
			if (check) {
				char c = arr[0].charAt(j);
				for (int k = 0; k < arr.length; k++) {
					if (c == arr[k].charAt(j) && k == arr.length - 1) {
						result += c;
					}
					if (c != arr[k].charAt(j)) {
						check = false;
						break;
					}

				}
			}

		}
	}
	return result;
}

/**
 * Modifies the given array by exchanging the first half with the second half.
 * If the array has odd length, the center element is not moved. For example,
 * given array
 * 
 * <pre>
 * [2, 4, 6, 99, 1, 2, 3]
 * </pre>
 * 
 * after swapFirstLast the array contains
 * 
 * <pre>
 * [1, 2, 3, 99, 2, 4, 6]
 * </pre>
 * 
 * Note this method modifies the given array and returns void.
 * 
 * @param arr the int array to be modified
 */
public static void swapFirstLast(int[] arr) {
	int m = 0;
	if (arr.length % 2 == 0) {
		m = arr.length / 2 + 1;
	} else {
		m = arr.length / 2 + 2;
	}
	for (int i = 0; i < arr.length - m + 1; i++) {
		int temp = arr[i];
		arr[i] = arr[m + i - 1];
		arr[m + i - 1] = temp;
	}
}

/**
 * Rearrange the elements of arr according to the given list of indices. After
 * calling this method, arr[i] should be the value formerly located at
 * arr[swizzler[i]]. For example, if swizzler is [3, 0, 3, 1] and arr is
 * 
 * <pre>
 * [10, 20, 30, 40]
 * </pre>
 * 
 * and then after this method executes, arr is
 * 
 * <pre>
 * [40, 10, 40, 20].
 * </pre>
 * 
 * If the swizzler length does not match the array length, or if it contains any
 * number that is out of range as an index in arr, the method does nothing. Note
 * that this method modifies the given array and returns void.
 * 
 * @param arr      the int array to be modified
 * @param swizzler array of indices indicating new positions of elements
 */
public static void swizzle(int[] arr, int[] swizzler) {
	int i;
	for (i = 0; i < swizzler.length; i++) {
		if (swizzler.length != arr.length || swizzler[i] >= arr.length || swizzler[i] < 0) {
			return;
		}
	}
	int[] temp = new int[arr.length];
	for (i = 0; i < arr.length; i++) {
		temp[i] = arr[i];
	}
	for (i = 0; i < arr.length; i++) {
		arr[i] = temp[swizzler[i]];
	}
}

/**
 * Creates a new array from the given one by adding up groups of k adjacent
 * entries. The length of the returned array is always
 * <code>arr.length / k</code> (integer division), and its ith element is the
 * sum
 * <p>
 * arr[ik] + arr[ik + 1] + arr[ik + 2] + ... + arr[ik + (k - 1)]
 * <p>
 * If the array length is not an exact multiple of k, the excess elements are
 * ignored. For example, if arr is [1, 2, 3, 4, 5, 6, 7] and k is 3, the result
 * is [6, 15].
 * 
 * @param arr any int array
 * @param k   number of adjacent elements in each group
 * @return new array obtained by adding up groups of adjacent elements
 */
public static int[] condense(int[] arr, int k) {
	int[] result = new int[arr.length / k];
	int sum = 0;
	int i = 0;
	for (int j = 0; j < arr.length; j++) {
		sum += arr[j];
		if ((j + 1) % k == 0) {
			result[i] = sum;
			i++;
			sum = 0;
		}
	}
	return result;
}

/**
 * Determines whether arr is a permutation. We define a permutation as an array
 * in which each number 0 through n - 1 appears exactly once, where n is the
 * length of the array.
 * 
 * @param arr an int array
 * @return true if the given array is a permutation, false otherwise
 */
public static boolean isPermutation(int arr[]) {
	int[] correct = new int[arr.length];
	for (int i = 0; i < arr.length; i++) {
		correct[i] = i;
	}
	Arrays.sort(arr);
	for (int i = 0; i < arr.length; i++) {
		if (correct[i] != arr[i]) {
			return false;
		}
	}
	return true;
}

/**
 * Returns the cycle in arr beginning with the given starting index. A
 * <em>cycle</em> in an array is a sequence of indices i<sub>0</sub>,
 * i<sub>1</sub>, i<sub>2</sub>, ...i<sub>k</sub> for some k &gt;= 0, such that:
 * <p>
 * i<sub>1</sub> = arr[i<sub>0</sub>], i<sub>2</sub> = arr[i<sub>1</sub>], ...,
 * and i<sub>0</sub> = arr[i<sub>k</sub>].
 * <p>
 * The sequence of indices is returned as an int array with the given start
 * index at position 0. For example, given [2, 4, 0, 1, 3] and start = 3, the
 * method returns [3, 1, 4]. (Note that if arr[start] == start, then the
 * resulting cycle has length 1.) Returns null if the given array is not a
 * permutation, or if the start index is out of bounds in arr.
 * 
 * @param arr   an int array
 * @param start starting point for finding a cycle
 * @return array containing cycle indices, beginning with given start index
 * 
 */
public static int[] findOneCycle(int[] arr, int start) {
	int i;
	int[] copy = new int[arr.length];
	for (i = 0; i < copy.length; i++) {
		copy[i] = arr[i];
	}
	int[] correct = new int[copy.length];
	for (i = 0; i < copy.length; i++) {
		correct[i] = i;
	}
	Arrays.sort(copy);
	for (i = 0; i < copy.length; i++) {
		if (correct[i] != copy[i] || copy.length - 1 < start) {
			return null;
		}
	}
	int next = arr[start];
	ArrayList<Integer> result = new ArrayList<Integer>();
	result.add(start);
	if (start != arr[start]) {
		while (next != start) {
			result.add(next);
			next = arr[next];

		}
	}
	int[] cycle = new int[result.size()];
	for (i = 0; i < result.size(); i += 1) {
		cycle[i] = result.get(i);
	}
	return cycle;
}

/**
 * Returns a list of all cycles in the given array. For example, given [2, 1, 3,
 * 0, 5, 4], the resulting list should contain [0, 2, 3], [1], and [4, 5]. The
 * cycles in the resulting list should be disjoint; that is, for example, the
 * list should not contain [2, 3, 0] in addition to [0, 2, 3], even though both
 * arrays describe the same cycle. The order of cycles in the list is not
 * specified. Returns an empty list if arr is not a permutation.
 * 
 * @param arr an int array
 * @return list of all cycles
 */
public static ArrayList<int[]> findAllCycles(int[] arr) {
	int i;
	boolean[] visit = new boolean[arr.length];
	ArrayList<int[]> result = new ArrayList<>();
	int[] temp = {};
	if (findOneCycle(arr, 0) != null) {
		for (i = 0; i < arr.length; i++) {
			if (visit[i] == true) {
				continue;
			}
			temp = findOneCycle(arr, i);
			for (int j = 0; j < temp.length; j++) {
				visit[temp[j]] = true;
			}
			result.add(temp);
		}
	}
	return result;
}
}
