import java.util.Scanner;


public class LongestCommonSubsequence {
	String a;
	String b;
	Integer[][] mem;	// mem[lengthA][lengthB], where substring for x is from index 0 to (lengthX - 1) inclusive
	
	public LongestCommonSubsequence(String a, String b) {
		this.a = a;
		this.b = b;
	}
	
	/* preprocess base cases: the LCS between a string and an empty string is 0 */
	void preprocess(){
		mem = new Integer[a.length()+1][b.length()+1];
		// set all mem[0][lengthA] = 0
		for (int lengthB=0; lengthB<b.length()+1; lengthB++)
			mem[0][lengthB] = 0;
		// set all mem[lengthB][0] = 0
		for (int lengthA=0; lengthA<a.length()+1; lengthA++)
			mem[lengthA][0] = 0;
	}
	
	/* top-down (recursive) DP */
	int LCS_topDown(int lengthA, int lengthB){
		if (mem[lengthA][lengthB] != null)
			return mem[lengthA][lengthB];
		
		int ans = 0;
		// if current character is the same, begin subsequence count
		if (a.charAt(lengthA - 1) == b.charAt(lengthB - 1))
			ans = 1 + LCS_topDown(lengthA - 1, lengthB - 1);
		// else continue searching by decrementing either length
		else
			ans = Math.max(LCS_topDown(lengthA - 1, lengthB), LCS_topDown(lengthA, lengthB - 1));
		
		mem[lengthA][lengthB] = ans;	// memorize
		return ans;
	}
	
	/* bottom-up (iterative) DP */
	int LCS_bottomUp(){
		for (int i=1; i<=a.length(); i++)
			for (int j=1; j<=b.length(); j++)
				if (a.charAt(i-1) == b.charAt(j-1))
					mem[i][j] = 1 + mem[i-1][j-1];
				else
					mem[i][j] = Math.max(mem[i-1][j], mem[i][j-1]);
		
		return mem[a.length()][b.length()];
	}
	
	/* reconstruct a possible path by printing a valid longest subsequence */
	void backtrack_recursive(int i, int j){
		int val = mem[i][j];
		if (val == 0)
			return;
		
		if (a.charAt(i-1) == b.charAt(j-1)){
			backtrack_recursive(i-1, j-1);
			System.out.print(a.charAt(i-1));	// prints common character
		}
		else if (mem[i][j] == mem[i-1][j])
			backtrack_recursive(i-1, j);
		else if (mem[i][j] == mem[i][j-1])
			backtrack_recursive(i, j-1);
	}
	String backtrack_iterative(){
		StringBuffer sb = new StringBuffer();
		int i = a.length();
		int j = b.length();
		while(mem[i][j] > 0){
			char currA = a.charAt(i-1);	// current character at a
			char currB = b.charAt(j-1);	// current character at b
			if (currA == currB){
				sb.append(currA);
				i--;
				j--;
			}
			else if (mem[i][j] == mem[i-1][j])
				i--;
			else if (mem[i][j] == mem[i][j-1])
				j--;
		}
		return sb.reverse().toString();
	}
	
	void run(){
		preprocess();
		System.out.println("Top-down: " + LCS_topDown(a.length(), b.length()));
		preprocess();
		System.out.println("Bottom-up: " + LCS_bottomUp());
		System.out.println("One valid LCS is : " + backtrack_iterative());
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext()){
			LongestCommonSubsequence lcs = new LongestCommonSubsequence(sc.nextLine(), sc.nextLine());
			lcs.run();
		}
		sc.close();
	}
}