import java.util.Scanner;

/**
 * Task Description:
 * Write a function that parses a string into integer
 */
public class StringToInteger {
	/**
	 * Converts ASCII to Integer
	 * @param str	target string
	 * @return		parsed integer
	 */
	public static int atoi(String str) {
		int ans = 0;
		boolean isNeg = (str.charAt(0) == '-');
		int start = (isNeg)? 1: 0;
		for (int i=start; i<str.length(); i++) {
			ans *= 10;
			int digit = str.charAt(i) - '0';
			ans += digit;
		}
		return ans;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = atoi(sc.nextLine());
		System.out.println(num);
		sc.close();
	}
}
