/**
 * CLASSIC QUESTION!
 * Problem Description:
 * Write a program that prints the numbers from 1 to 100. But for multiples of
 * three print “Fizz” instead of the number and for the multiples of five print
 * “Buzz”. For numbers which are multiples of both three and five print
 * “FizzBuzz” 
 *
 */
public class FizzBuzz {
	/**
	 * Checks if a is a multiple of b
	 * @param a
	 * @param b
	 * @return	true if a is a multiple of b, else false
	 */
	public static boolean isMultipleOf(int a, int b) {
		return (a % b == 0);
	}
	
	public static void main(String[] args) {
		for (int i=1; i<101; i++) {
			boolean flag = false;	// if i is either multiple of 3 or 5
			if (isMultipleOf(i, 3)) {
				flag = true;
				System.out.print("Fizz");
			}
			if (isMultipleOf(i, 5)) {
				flag = true;
				System.out.print("Buzz");
			}
			if (!flag) {
				System.out.print(i);
			}
			System.out.println(); // separator
		}
	}
}