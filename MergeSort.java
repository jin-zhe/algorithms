import java.util.Arrays;

/**
 * @author Kilroy
 * This is an implementation of the Mergesort
 */
public class MergeSort {

	public static void main(String[] args) {
		int[] arr = {4, 223, 343, 62, 11, 29, 306, 247, 244, 78};
		System.out.println(Arrays.toString(sort(arr)));
	}
	
	/**
	 * Sorts the given array
	 * @param arr	the given array to be sorted
	 * @return 		the sorted array
	 */
	static int[] sort(int[] arr){
		/* base case */
		if (arr.length <= 2){
			if (arr.length == 2 && arr[0] > arr[1]){
				int temp = arr[0];
				arr[0] = arr[1];
				arr[1] = temp;
			}
			return arr;
		}
		
		int[] arr1 = sort(copyOfRange(arr, 0, arr.length/2));
		int[] arr2 = sort(copyOfRange(arr, arr.length/2, arr.length));
		
		return merge(arr1, arr2);
	}
	
	/**
	 * Merges the two given sorted arrays
	 * @param arr1	first sorted array
	 * @param arr2	second sorted array
	 * @return		the merged array
	 */
	static int[] merge(int[] arr1, int[] arr2){
		int[] arrMerge = new int[arr1.length + arr2.length];
		int ptrMerge = 0;	// ptr for arrMerge
		int ptr1 = 0;		// ptr for arr1
		int ptr2 = 0;		// ptr for arr2
		
		while (ptr1 < arr1.length || ptr2 < arr2.length){
			/* if both array not depleted */
			if (ptr1 < arr1.length && ptr2 < arr2.length){
				int curr1 = arr1[ptr1]; // value at ptr1
				int curr2 = arr2[ptr2];	// value at ptr2
				
				if (curr1 < curr2){
					arrMerge[ptrMerge] = curr1;	// insert curr1 into arrMerge at ptrMerge
					ptr1++;						// increment ptr1
				}
				else {
					arrMerge[ptrMerge] = curr2;	// insert curr2 into arrMerge at ptrMerge
					ptr2++;						// increment ptr2
				}
			}
			/* if array 2 depleted */
			else if (ptr1 < arr1.length){
				arrMerge[ptrMerge] = arr1[ptr1];
				ptr1++;
			}
			/* if array 1 depleted */
			else{
				arrMerge[ptrMerge] = arr2[ptr2];
				ptr2++;
			}
			ptrMerge++;					// increment ptrMerge
		}
		return arrMerge;
	}
	
	static int[] copyOfRange(int[] arr, int start, int end){
		int length = end - start;
		int[] arrDest = new int[length];
		System.arraycopy(arr, start, arrDest, 0, length);
		return arrDest;
	}
}