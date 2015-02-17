import java.util.Scanner;

public class LongestPalindromeSubsequence {
	Integer[][] mem;	// memory hashmap
	String string;
	
	// constructor
	public LongestPalindromeSubsequence(String string){
		this.string = string;
		mem = new Integer[1000][1000];
	}
	
	// DP recursion for substring from startIndex to endIndex
	public int LPS(int startIndex, int endIndex){
		if (mem[startIndex][endIndex] != null) return mem[startIndex][endIndex];
		
		// base case: solo letter (it is palindromic to itself)
		if (endIndex - startIndex == 1){
			mem[startIndex][endIndex] = 1;
			return 1;	
		}
		
		int ans = 0;
		// if start and end characters are the same
		if (string.charAt(startIndex) == string.charAt(endIndex - 1)){
			if (endIndex - startIndex == 2) ans = 2;	// base case: paired letters
			else ans = 2 + LPS(startIndex + 1, endIndex - 1);
		}
		// else
		else ans = Math.max(LPS(startIndex + 1, endIndex), LPS(startIndex, endIndex - 1));
		
		// memorize
		mem[startIndex][endIndex] = ans;
		return ans;
	}
	
	// prints out final answer
	public void run(){
		if (string.isEmpty()) System.out.println("0");
		else System.out.println(LPS(0, string.length()));
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		sc.nextLine();
		for (int i=0; i<T; i++){
			LongestPalindromeSubsequence LPS = new LongestPalindromeSubsequence(sc.nextLine());
			LPS.run();
		}
		sc.close();
	}
}