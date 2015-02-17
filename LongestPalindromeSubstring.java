import java.util.Scanner;

public class LongestPalindromeSubstring {
	String string;		// string to be tested
	Integer[][] mem;	// [startIndex][endIndex]
	
	// constructor
	public LongestPalindromeSubstring(String aString){
		string = aString;
		mem = new Integer[string.length()][string.length()];
	}
	
	// test substring from startIndex to endIndex inclusive for Palindrome
	public boolean isPalindrome(int startIndex, int endIndex){
		while (startIndex < endIndex){
			if (string.charAt(startIndex) != string.charAt(endIndex)) return false;
			startIndex++;
			endIndex--;
		}
		return true;
	}
	
	// DP recursion for substring from startIndex to endIndex inclusive
	public int solve(int startIndex, int endIndex){
		if (mem[startIndex][endIndex] != null) return mem[startIndex][endIndex];
		if (isPalindrome(startIndex, endIndex)){
			mem[startIndex][endIndex] = endIndex - startIndex + 1;
			return mem[startIndex][endIndex];
		}
		// a letter itself is palindrome
		if (endIndex == startIndex){
			mem[startIndex][endIndex] = 1;
			return mem[startIndex][endIndex];
		}
		
		mem[startIndex][endIndex] = (solve(startIndex + 1, endIndex) > solve(startIndex, endIndex - 1))? solve(startIndex + 1, endIndex): solve(startIndex, endIndex - 1);
		return mem[startIndex][endIndex];
	}
	
	// prints out final answer
	public void run(){
		if (string.length() == 0) System.out.println(0);		// if empty string
		else System.out.println(solve(0, string.length()-1));	// else
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		sc.nextLine();
		for (int i=0; i<T; i++){
			LongestPalindromeSubstring LP = new LongestPalindromeSubstring(sc.nextLine());
			LP.run();
		}
		sc.close();
	}
}