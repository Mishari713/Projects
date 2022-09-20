package lab1;

public class Greeter {
	public static void main(String[] args) {
		
		int[] arr = new int[] {3,2,2,2,5,5,2};
		int[] temp = new int[arr.length];
		int counter =0;
		
		for(int i =0; i<arr.length; i++) {
			for(int j = i + 1; j < arr.length; j++) {
				if (arr[i] != arr[j]) {
					temp[i] = arr[i];
					break;
				}
				else {
					counter++;
				}
			}
		}
		for (counter = counter +1; counter < temp.length; counter++) {
			temp[counter] = 0;
		}
		for (int i =0; i< arr.length; i++) {
			arr[i] = temp[i];
		}
		for (int i =0; i< arr.length; i++) {
			System.out.print(arr[i]+ ", ");
		}
	}

}
