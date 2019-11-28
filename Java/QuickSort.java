import java.util.Arrays;
import java.util.Random;

/**
 * This is an implementation of the Quicksort algorithm
 */
public class QuickSort {

	public static void main(String[] args) {
		int[] arr = {92, 23, 21, 5, 57, 63, 7, 12, 21, 43, 25, 24, 1};
		sort(arr, 0, arr.length);
		System.out.println(Arrays.toString(arr));
	}
	
	/**
	 * Sorts the array using for the index range from start to end (exclusive). O(NlgN) average case
	 * @param arr		array to be sorted
	 * @param start		start index for range in array (inclusive)
	 * @param end		end index for range in array (exclusive)
	 */
	static void sort(int[] arr, int start, int end){
		if (start != end){
			setPivot(arr, start, end);					// set the pivot
			int rankPivot = partition(arr, start, end);	// partition array range based on pivot
			/* recurse down */
			sort(arr, start, rankPivot);	// sort left (lower half)
			sort(arr, rankPivot + 1, end);	// sort right (larger half)
		}
	}
	
	/**
	 * Choose a random index in the range [start,end) and sets it to be the pivot. O(1)
	 * @param arr		the given array
	 * @param start		start index for range in array (inclusive)
	 * @param end		end index for range in array (exclusive)
	 */
	static void setPivot(int[] arr, int start, int end){
		Random gen = new Random();
		int pivot = start + gen.nextInt(end - start);	// generate random pivot
		
		/* swaps pivot item with that at start index */
		int pivotVal = arr[pivot];
		arr[pivot] = arr[start];
		arr[start] = pivotVal;
	}
	
	/**
	 * Partitions the array for range [start,end) using start item as pivot. O(end - start)
	 * @param arr		array for in-place partitioning
	 * @param start		start index for range in array (inclusive)
	 * @param end		end index for range in array (exclusive)
	 * @return 			final ranking for the pivot
	 */
	static int partition(int arr[], int start, int end){
		int pivotVal = arr[start];
		int i = start;	// last index of left partition (<= pivotVal). Also rank for pivot at the end
		
		/* for each subsequent item till the end */
		for (int j=i+1; j<end; j++){
			int curr = arr[j];
			
			/* if item lower than pivotVal discovered */
			if (curr < pivotVal){
				i++;	// increment new boundary for left partition
				
				/* swap arr[i] with arr[j] */
				arr[j] = arr[i];
				arr[i] = curr;
			}
			/* else, do nothing */
		}
		
		/* swap pivot to its rightful rank */
		arr[start] = arr[i];
		arr[i] = pivotVal;
		
		return i;	// return final rank of pivot
	}
}