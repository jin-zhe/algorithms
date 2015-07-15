import java.util.Scanner;


public class LongestCommonSubstring {
	String a;
	String b;
	Integer[][] mem;
	
	LongestCommonSubstring(String a, String b){
		this.a = a;
		this.b = b;
	}
	
	/**
	 * Methodology:
	 * 	Line up two strings using an implicit slot using 2 starting pointers, one for each string.
	 * 	e.g.
	 * 		string 1:       aba123
	 * 		string 2: 456789aba
	 * 						^
	 * 		The starting pointer for string 1 is 0 and the one for string 2 is 6.
	 * 		That is our slot, as indicated by the caret.
	 * 	We define mem[i][j] as the length of the common substring,
	 * 	with starting pointer i in string 1 and j in string 2.
	 * 	If the two characters in the slot matches, mem[i][j] = 1 + mem[j+1][j+1].
	 * 	Think of it as dialing the slot down by one for both strings and continuing the comparison
	 * 	If the two characters don't match, mem[i][j] = 0
	 * 	Also if either starting pointers has exceeded the length of its string, mem[i][j] is also 0.
	 * 	We try all slots by trying all possible pointer combinations and this populates the table mem[j][j].
	 * 	We simply get the largest value from the table and that is our answer
	 */
	/* O(MN) bottom-up DP */
	int LCS(){
		/* preprocess */
		mem = new Integer[a.length()+1][b.length()+1];
		/* when start pointer for b exceeded */
		for (int i=0; i<a.length()+1; i++)
			mem[i][b.length()] = 0;
		/* when start pointer for a exceeded */
		for (int i=0; i<b.length(); i++)
			mem[a.length()][i] = 0;
		
		/* bottom-up DP */
		int max = 0;
		/* starting points of a from bottom to top */
		for (int i=a.length()-1; i>=0; i--)
			/* starting points of b from left to right */
			for (int j=0; j<b.length(); j++){
				int len = 0;	// length of overlapping substrings starting from current slot
				/* if characters at current slot matches */
				if (a.charAt(i) == b.charAt(j))
					len = 1 + mem[i+1][j+1];
				
				max = Math.max(len, max);	// update max
				mem[i][j] = len;			// memorize
			}
		
		return max;
	}
	
	/**
	 * Reconstructs the longest common substring
	 */
	String backtrack(){
		int max = 0;	// max value in memory table
		int iMax = -1;	// i coordinate for cell with max value
		int jMax = -1;	// j coordinate for cell with max value
		StringBuffer sb = new StringBuffer();
		
		/* determine iMax and jMax */
		for (int i=0; i<a.length(); i++)
			for (int j=0; j<b.length(); j++)
				/* if new max encountered, update */
				if (mem[i][j] > max){
					max = mem[i][j];
					iMax = i;
					jMax = j;
				}
		
		/* reconstructs longest substring by moving down diagonally */
		while (mem[iMax][jMax] > 0){
			if (mem[iMax][jMax] == 0)
				break;
			sb.append(a.charAt(iMax));
			iMax++;
			jMax++;
		}
		return sb.toString();
	}
	
	void run(){
		System.out.println(LCS());
		System.out.print("One valid LCS is: " + backtrack());
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		(new LongestCommonSubstring(sc.nextLine(), sc.nextLine())).run();
		sc.close();
	}

}