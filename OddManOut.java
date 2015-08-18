import java.util.HashSet;

/**
 * Problem Description:
 * An array is given consisting of integer numbers. Each number except one
 * appears exactly twice. The remaining number appears only once in the array.
 * Write a function which returns a number which appears only once in the array.
 * What if we have a different array where every number appears thrice except
 * one number which appear once?
 */
public class OddManOut {
	/**
	 * Gets the single number out from an array of pairs
	 * @param arr	the given array
	 * @return		the odd one out
	 */
	public static int getOddOneOutOfDoubles(int [] arr) {
		int oddOneOut = 0;
		for (int i: arr) { oddOneOut ^= i; }
		return oddOneOut;
	}
	
	/**
	 * Gets the single number out from an array of triples
	 * @param arr	the given array
	 * @return		the odd one out
	 */
	public static int getOddOneOutOfTriples(int[] arr) {
		int sum = 0; // sum of all numbers in arr
		int temp = 0;
		HashSet<Integer> map = new HashSet<Integer>();
		for (int i: arr) {
			sum += i;
			if (!map.contains(i)) {
				map.add(i);
				temp += i;
			}
			else {
				temp -= i;
			}
		}
		return (temp*3 + sum)/4;
	}
	
	public static void main(String[] args) {
		int[] arrDoubles = {1, 4, 2, 1, 3, 4, 2};
		System.out.println("Odd one out of doubles: " +
						 getOddOneOutOfDoubles(arrDoubles));
		int[] arrTriples = {1, 4, 2, 2, 1, 4, 3, 1, 4, 2};
		System.out.println("Odd one out of triples: " +
						 getOddOneOutOfTriples(arrTriples));
	}

}
