import java.util.Scanner;

public class LongestPalindromeSubsequence {
	Integer[][] mem;	// memory hashmap
	String string;
	
	/* constructor */
	public LongestPalindromeSubsequence(String string){
		this.string = string;
	}
	
	/* preprocess */
	public void preprocess(){
		mem = new Integer[string.length()][string.length()];
		/*  i: start index, j: end index (inclusive)*/
		for (int i=0; i<string.length(); i++)
			for (int j=0; j<string.length(); j++){
				if (i == j)
					mem[i][j] = 1; // base case: solo letter (it is palindromic to itself)
				else if (i > j)
					mem[i][j] = 0; // base case: invalid start-end indices (when pointers cross)
			}
	}
	
	/* DP recursion for substring from startIndex to endIndex (inclusive) */
	public int LPS_topDown(int startIndex, int endIndex){
		if (mem[startIndex][endIndex] != null)
			return mem[startIndex][endIndex];
		
		int ans = 0;
		/* if start and end characters are the same */
		if (string.charAt(startIndex) == string.charAt(endIndex))
			ans = 2 + LPS_topDown(startIndex + 1, endIndex - 1);
		else
			ans = Math.max(LPS_topDown(startIndex + 1, endIndex), LPS_topDown(startIndex, endIndex - 1));
		
		mem[startIndex][endIndex] = ans;	// memorize
		return ans;
	}
	/* driver function */
	public int LPS_topDown(){
		preprocess();
		return LPS_topDown(0, string.length()-1);
	}
	
	/* DP iterative */
	public int LPS_bottomUp(){
		preprocess();
		
		/* i: start index, j: end index (inclusive) */
		for (int i=string.length(); i>=0; i--) 		// row from bottom to top
			for (int j=0; j<string.length(); j++){	// column from left to right
				/* if top triangle of memory table */
				if (j > i)
					if (string.charAt(i) == string.charAt(j))
						mem[i][j] = 2 + mem[i+1][j-1];
					else
						mem[i][j] = Math.max(mem[i+1][j], mem[i][j-1]);
				/* skip bottom-half triangle (including diagonal) as they were preprocessed */
				else
					continue;
			}
				
		return mem[0][string.length()-1];
	}
	
	/* Reconstructs the LPS */
	String backtrack(){
		StringBuffer sb1 = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
		int i=0;
		int j=string.length()-1;
		while(mem[i][j]>0){
			char front = string.charAt(i);
			char back = string.charAt(j);
			if (front == back){
				sb1.append(string.charAt(i));
				sb2.append(string.charAt(i));
				i++;
				j--;
			}
			else if (mem[i][j] == mem[i+1][j])
				i++;
			else if (mem[i][j] == mem[i][j-1])
				j--;
		}
		
		/* generate palindrome */
		sb2.reverse();
		/* if palindrome length is even */
		if (mem[0][string.length()-1] % 2 == 0)
			sb1.append(sb2);
		else{
			sb1.append(sb2.substring(1));	// ignore duplicated first character (present in sb1)
		}
			
		return sb1.toString();
	}
	
	public void run(){
		if (string.isEmpty())
			System.out.println("0");
		else{
			System.out.println("Top-down: " + LPS_topDown());
			System.out.println("Bottom-up: " + LPS_bottomUp());
			System.out.println("One valid LPS is: " + backtrack());
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		LongestPalindromeSubsequence LPS = new LongestPalindromeSubsequence(sc.nextLine());
		LPS.run();
		sc.close();
	}
}