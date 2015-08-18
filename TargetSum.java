import java.util.Arrays;
import java.util.HashMap;

/**
 * Problem Description:
 * Given an array and a number n, can you obtain n using a sum of 2 numbers in
 * the array? What about 3 numbers?
 */
public class TargetSum {

	public static void main(String[] args) {
		int[] arr = {10, 1, 44, 4, 9, 65, 2, 11, 3};
		int n = 6;
		System.out.println("Obtain 2 alternative: " + obtain2_alternative(n, arr));
		System.out.println("Obtain 2: " + obtain2(n, arr));
		System.out.println("Obtain 3: " + obtain3(n, arr));
	}

	/**
	 * Checks if the given number may be obtained from the given array, returns
	 * the number triplet if obtainable, else null
	 * Time complexity: O(N^2)
	 * @param n		number to be obtained
	 * @param arr	the given array
	 * @return		triplet of integers in array that sum up to n, null if they
	 * 				are not found
	 */
	public static Triplet obtain3(int n, int[] arr){
		Arrays.sort(arr); // sorts the array O(NlogN)
		for (int i=0; i<arr.length-2; i++){
			int one = arr[i];
			Pair pair = obtain2(n - one, arr, i+1);
			/* if solution found */
			if (pair != null){
				int two = pair.one;
				int three = pair.two;
				return new Triplet(one, two, three);
			}
		}
		return null;
	}
	
	/**
	 * Checks if the given number may be obtained from the given array, returns
	 * the number pair if obtainable, else null
	 * Time complexity: O(NlogN)
	 * @param n		number to be obtained
	 * @param arr	the given array
	 * @return		pair of integers in array that sum up to n, null if they
	 * 				are not found
	 */
	public static Pair obtain2(int n, int[] arr) {
		Arrays.sort(arr); // sorts the array O(NLogN)
		return obtain2(n, arr, 0);
	}
	
	/**
	 * Checks if the given number may be obtained from the current sorted
	 * sub-array. Returns the number pair if obtainable, else null
	 * Time complexity: O(N)
	 * @param n			the number to be obtained
	 * @param arr		the given array
	 * @param start		start index of sub-array (ends at end of arr)
	 * @param isSorted	indicates if the array is already sorted
	 * @return			pair of integers in arr that sum up to n, null if they
	 * 					are not found 
	 */
	public static Pair obtain2(int n, int[] arr, int start){
		/* optimizations */
		int len = arr.length;
		if (arr[start] > n || (len > 1 && (arr[len-1] + arr[len-2] < n))) {
			return null;
		}
		
		int front = start;
		int rear = arr.length - 1;
		
		while (front < rear) {
			int sum = arr[front] + arr[rear];
			/* if pair found, return */
			if (sum == n) { return new Pair(arr[front], arr[rear]); }
			/* if too low, increment front index */
			if (sum < n) { front++; }
			/* if too large, decrement rear index */
			else { rear--; }
		}
		return null;
	}
	
	/**
	 * Alternative O(N) implementation of obtain2
	 */
	public static Pair obtain2_alternative(int n, int[] arr){
		/* preprocess hashmap */
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i: arr){
			if (map.containsKey(i))
				map.put(i, map.get(i)+1);
			else
				map.put(i, 0);
		}
		/* iterates each number i array to test if n-i also exists */
		for (int i: arr){
			if (map.containsKey(n-i))
				if (i != n-i || (i==n-i && map.get(n-i)>1))
					return new Pair(i, n-i);
		}
		
		return null;
	}
	
	/* helper classes */
	public static class Triplet{
		int one;
		int two;
		int three;
		public Triplet (int one, int two, int three){
			this.one = one;
			this.two = two;
			this.three = three;
		}
		public String toString(){
			return one + " + " + two + " + " + three;
		}
	}
	
	static class Pair{
		int one;
		int two;
		public Pair (int one, int two){
			this.one = one;
			this.two = two;
		}
		public String toString(){
			return one + " + " + two;
		}
	}
}