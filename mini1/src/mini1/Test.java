package mini1;

public class Test {
public static void main(String args[]) {
    int[] arr = {1, 9 , 2 ,6, 10, 19, 0, 20, - 55};
    System.out.println("MergeSort:");
    for(int i = 0; i < arr.length; ++i) {
    System.out.print(arr[i] + " ");
        
    }
    System.out.println();
    int[] arr1 = mergeSort(arr);
    for(int i = 0; i < arr1.length; ++i) {
    System.out.print(arr1[i] + " ");
    }
    quickSortRec(arr, 0, arr.length - 1);
    System.out.println("\nQuickSort:");
    for(int i = 0; i < arr.length; ++i) {
    System.out.print(arr[i] + " ");
    }
}
public static int[] mergeSort(int[] arr){
    int n = arr.length;
    if(n > 1) {
    int m = n / 2;
    int[] left = new int[m];
    int[] right = new int[n - m];
    for (int i = 0; i < m; i++){
        left[i] = arr[i];
    }
    for (int i = 0; i < n - m; i++){
        right[i] = arr[m + i];
    }
    left = mergeSort(left);
    right = mergeSort(right);
    int[] sorted = merge(left, right);
    return sorted;
    }
	return arr;
}

public static int[] merge(int[] left, int[] right){
    int p = left.length;
    int q = right.length;
    int[] sorted = new int[p + q];
    int s, i, j; s = i = j = 0;
    while (i < p && j < q){
        if (left[i] <= right[j]){
            sorted[s] = left[i];
            i++;
        }
        else{
            sorted[s] = right[j];
            j++;
        }
        s++;
    }
    if (i >= p){
        for(int k = j; k <= q - 1; k++){
            sorted[s] = right[k];
            s++;
        }
    }
    else{
        for(int k = i; k <= q -1; k++){
            sorted[s] = left[k];
            s++;
        }
    }
    return sorted;
}

public static void quickSortRec (int[] arr, int first, int last) {
	if (first >= last) return;
	
	int p = partition(arr, first, last);
	quickSortRec(arr, first, p -1);
	quickSortRec(arr, p + 1, last);
}

public static int partition(int[] arr, int first, int last) {
    int pivot = arr[first];
    int i = first - 1, j = last + 1;

    while (true)
    {
        // Find leftmost element greater
        // than or equal to pivot
        do {
            i++;
        } while (arr[i] < pivot);

        // Find rightmost element smaller
        // than or equal to pivot
        do {
            j--;
        } while (arr[j] > pivot);

        // If two pointers met.
        if (i >= j)
            return j;
       
        // swap(arr[i], arr[j]);
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
         
    }
}
}
