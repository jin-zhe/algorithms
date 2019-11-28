import java.util.Scanner;

/**
 * URL: http://www.geeksforgeeks.org/count-possible-decodings-given-digit-sequence/
 * Question:
 * Let 1 represent ‘A’, 2 represents ‘B’, etc. Given a digit sequence, count the
 * number of possible decodings of the given digit sequence.
 * E.g.
 * 	Input:  digits[] = "121"
 *	Output: 3
 *	The possible decodings are "ABA", "AU", "LA"
 *
 *	Input: digits[] = "1234"
 *	Output: 3
 *	The possible decodings are "ABCD", "LCD", "AWD"
 */

public class CountPossibleDecodings {
	int num;
	Integer[] mem;
	
	CountPossibleDecodings(int num){
		this.num = num;
		mem = new Integer[num + 1];
	}
	
	int solve(int n){
		if (mem[n] != null) return mem[n];
		// base case: 
		if (n < 10) return 1;
		
		int ans = 0;
		// if last digit forms an alphabet
		if (n%10 > 0)
			ans += solve(n/10);			// use last digit as character
		
		// if last 2 digits forms a alphabet and does not have leading 0
		if (n%100 <= 26 && n%100 > 9)
			ans += solve(n/100);	// use last 2 digits as character
		
		mem[n] = ans;
		return ans;
	}
	
	void run(){
		System.out.println(solve(num));
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		(new CountPossibleDecodings(sc.nextInt())).run();
		sc.close();
	}

}