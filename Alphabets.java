import java.util.Scanner;

/** 	1: a, 2: b ... 26: z
	Given a digit, return the count of the possible output
	Eg.
	'1313' returns 4: acac mac mm acm
	'101' returns 1: ja
	Ans: http://www.glassdoor.com/Interview/1-a-2-b-26-z-given-a-digit-return-the-count-of-the-possible-output-eg-1313-4-acac-mac-mm-acm-101-1-ja-QTN_785194.htm
 **/
public class Alphabets {
	int num;
	Integer[] mem;
	
	Alphabets(int num){
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
		(new Alphabets(sc.nextInt())).run();
		sc.close();
	}

}