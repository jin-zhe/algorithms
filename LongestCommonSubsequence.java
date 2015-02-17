import java.util.Scanner;


public class LongestCommonSubsequence {
	String a;
	String b;
	Integer[][] mem;	// mem[endpointA][endpointB], where endpoint is the length of substring (startin at 0) to consider
	
	public LongestCommonSubsequence(String a, String b) {
		this.a = a;
		this.b = b;
		mem = new Integer[a.length()+1][b.length()+1];
		
		// preprocess
		// set all mem[0][endpointA] = 0
		for (int endpointB=0; endpointB<b.length()+1; endpointB++) mem[0][endpointB] = 0;
		// set all mem[endpointB][0] = 0
		for (int endpointA=0; endpointA<a.length()+1; endpointA++) mem[endpointA][0] = 0;
	}
	
	int LCS(int endpointA, int endpointB){
		if (mem[endpointA][endpointB] != null) return mem[endpointA][endpointB];
		
		int ans = 0;
		// if current character is the same, begin subsequence count
		if (a.charAt(endpointA - 1) == b.charAt(endpointB - 1))
			ans = 1 + LCS(endpointA - 1, endpointB - 1);
		// else continue searching by decrementing either endpoint
		else
		 ans = Math.max(LCS(endpointA - 1, endpointB), LCS(endpointA, endpointB - 1));
		
		mem[endpointA][endpointB] = ans;
		return ans;
	}
	
	void run(){
		System.out.println(LCS(a.length(), b.length()));
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