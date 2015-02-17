import java.util.Scanner;


public class LongestCommonSubstring {
	String a;
	String b;
	Integer[][] mem;
	
	LongestCommonSubstring(String a, String b){
		this.a = a;
		this.b = b;
		mem = new Integer[a.length()][b.length()];
	}
	
	int solve(int startA, int startB){
		if (startA == a.length() || startB == b.length()) return 0;
		if (mem[startA][startB] != null) return mem[startA][startB];
		
		int ans = 0;
		// if current characters match, start counting
		if (a.charAt(startA) == b.charAt(startB)) ans = 1 + solve(startA + 1, startB + 1);
		// else do nothing (ans already 0)
		
		mem[startA][startB] = ans;
		return ans;
	}
	
	void run(){
		// populate mem[][] by going through all possible combinations of startA and startB
		for (int i=0; i<a.length(); i++)
			for (int j=0; j<b.length(); j++)
				solve(i,j);
		
		// find max element in mem[][]
		int max = 0;
		for (int i=0; i<a.length(); i++)
			for (int j=0; j<b.length(); j++)
				if (mem[i][j] > max) max = mem[i][j];
		
		System.out.println(max);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		(new LongestCommonSubstring(sc.nextLine(), sc.nextLine())).run();
		sc.close();
	}

}
