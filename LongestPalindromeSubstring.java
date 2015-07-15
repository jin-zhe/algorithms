import java.util.Scanner;

public class LongestPalindromeSubstring {
	String string;		// string to be tested
	Boolean[][] mem;	// [startIndex][endIndex]
	
	// constructor
	public LongestPalindromeSubstring(String aString){
		string = aString;
	}
	
	/* O(N^2) DP test substring from startIndex to endIndex inclusive for palindrome */
	public boolean LPS_DP(int startIndex, int endIndex){
		/* if answer is in memory */
		if (mem[startIndex][endIndex] != null)
			return mem[startIndex][endIndex];
		
		boolean ans = false;
		/* if outermost characters match, recurse inwards */
		if (string.charAt(startIndex) == string.charAt(endIndex))
			ans = LPS_DP(startIndex + 1, endIndex - 1); /* |-> ... <-| */
		mem[startIndex][endIndex] = ans;
		return ans;
	}
	
	/* driver function */
	public int LPS_DP(){
		/* preprocess */
		mem = new Boolean [string.length()][string.length()];
		for (int i=0; i<string.length(); i++)
			for (int j=0; j<string.length(); j++)
				/* base case: if index at same character or index crossed paths, substring is palindrome */
				if (i >= j)
					mem[i][j] = true;
		
		
		int max = 0;
		// for each possible substring in string
		for (int i=0; i<string.length(); i++)
			for (int j=i; j<string.length(); j++){
				// if substring is a palindrome, update max
				if (LPS_DP(i, j) && (j - i + 1) > max)
					max = j - i + 1;
			}
		return max;
	}
	
	/* O(N^2) solution by expanding around possible palindrome centers */
	public int LPS(){
		int maxLen = 1; // maximum palindrome length recorded
		
		/* test the N possible single centers (each character as a center) */
		for (int i=1; i<string.length()-1; i++){
			int len = 1;	// resets length
			int l = i-1;	// left expansion pointer
			int r = i+1;	// right expansion pointer
			
			/* expands around current center */
			while (l>=0 && r<string.length()){
				/* if palindrome can expand */
				if (string.charAt(l) == string.charAt(r)){
					len += 2;	// update expanded length
					l--;		// <-|
					r++;		// |->
				}
				else
					break;
			}
			maxLen = Math.max(len, maxLen);
		}
		
		/* test the N-1 possible paired centers (each pair of matching character as a center) */
		for (int i=0; i<string.length()-1; i++){
			/* only if current pair is palindromic */
			if (string.charAt(i) == string.charAt(i+1)){
				int len = 2;	// resets length
				int l = i-1;	// left expansion pointer
				int r = i+2;	// right expansion pointer
				
				/* expands around current paired center */
				while (l>=0 && r<string.length()){
					if (string.charAt(l) == string.charAt(r)){
						len += 2;	// update expanded length
						l--;		// <-|
						r++;		// |->
					}
					else
						break;
				}
				
				maxLen = Math.max(len, maxLen);
			}
		}
		
		return maxLen;
	}
	
	public void run(){
		System.out.println(LPS());
		System.out.println(LPS_DP());
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();	// number of test cases
		sc.nextLine();
		for (int i=0; i<T; i++){
			LongestPalindromeSubstring LP = new LongestPalindromeSubstring(sc.nextLine());
			LP.run();
		}
		sc.close();
	}
}