import java.util.Random;

/**
 * Comprises of the following algorithms:
 * 	get Kth smallest
 * 	get median
 */
public class OrderStatistics {

	public static void main(String[] args) {
		int[] arr = {92, 23, 21, 5, 57, 63, 7, 12, 21, 43, 25, 24, 1};
		int k = 6;	// kth smallest or equivalently k-1 rank
		System.out.println(k + "th smallest: " + getKthSmallest(arr, k));
		System.out.println("Median: " + getMedian(arr));
	}
	
	/**
	 * Given an array of numbers, return the Kth smallest number in the list
	 * (where 1st smallest refers to the smallest item)
	 * @param arr	given array
	 * @return		Kth smallest item
	 */
	public static int getKthSmallest (int[] arr, int k) {
		return getRankItem(arr, 0, arr.length, k-1);
	}
	
	public static double getMedian(int[] arr) {
		/* odd number of items */
		if (arr.length % 2 != 0) {
			return getRankItem(arr, 0, arr.length, arr.length/2);
		}
		
		/* even number of items */
		int val1 = getRankItem(arr, 0, arr.length, arr.length/2);
		int val2 = getRankItem(arr, arr.length/2 + 1, arr.length, arr.length/2 + 1);
		return (double) (val1 + val2)/2;
	}
	
	/**
	 * Gets the item corresponding to the given rank i from the array.
	 * O(N) average case where N = end - start
	 * @param arr	the array of interest
	 * @param start	start point of subarray (inclusive)
	 * @param end	end point of subarray (exclusive)
	 * @param rank	the rank of interest in the array. Note that this is
	 * 				absolute and not relative to the current range
	 * @return		the item at rank
	 */
	static int getRankItem(int[] arr, int start, int end, int rank) {
		/* if rank exceeds range */
		if (rank < start || rank >= end) {
			return -1;
		}
		setPivot(arr, start, end);					// generate random pivot
		int pivotPtr = partition(arr, start, end);	// partition array based on pivot
		
		/* if rank is found */
		if (rank == pivotPtr) {
			return arr[rank];
		}
		/* else if rank is on right partition */
		else if (rank > pivotPtr) {
			return getRankItem(arr, pivotPtr + 1, end, rank);
		}
		/* else if rank is on left partition */
		else {
			return getRankItem(arr, start, pivotPtr, rank);
		}
	}
	
	/**
	 * Randomly selects a pivot and set it to be the first element in array.
	 * O(1)
	 * @param arr	the array of interest
	 * @param start	start point of subarray (inclusive)
	 * @param end	end point of subarray (exclusive)
	 */
	static void setPivot(int[] arr, int start, int end) {
		Random gen = new Random();
		int pivot = gen.nextInt(end - start) + start;	// generate random pivot
		
		/* swap pivot item with start item */
		int pivotVal = arr[pivot];
		arr[pivot] = arr[start];
		arr[start] = pivotVal;
	}
	
	/**
	 * Partitions array using first item as pivot. O(end - start)
	 * @param arr	the array of interest
	 * @param start	start point of subarray (inclusive)
	 * @param end	end point of subarray (exclusive)
	 * @return		final rank of pivot
	 */
	static int partition(int[] arr, int start, int end) {
		int i = start;				// right boundary for left partition
		int pivotVal = arr[start];	// value of pivot
		
		for (int j=i+1; j<end; j++) {
			int curr = arr[j];
			/* if item belongs to left partition*/
			if (curr < pivotVal) {
				i++;	// increment boundary
				/* swap arr[i] with arr[j] */
				arr[j] = arr[i];
				arr[i] = curr;
			}
			/* else, continue */
		}
		
		/* swap pivot to its rightful rank */
		arr[start] = arr[i];
		arr[i] = pivotVal;
		
		return i;	// return final pivot rank
	}
}