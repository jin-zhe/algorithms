import java.util.Scanner;

// http://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=615
public class CoinChange{
	Integer[][] mem;	// [cents][largetDenom]
	
	// constructor
	public CoinChange(){
		mem = new Integer[7489 + 1][51];
		// leafs are 1 way each
		mem[0][50] = 1;
		mem[0][25] = 1;
		mem[0][10] = 1;
		mem[0][5] = 1;
		mem[0][1] = 1;
	}
	
	// DP recursion
	public int solve(int n, int largestDenom){
		if (mem[n][largestDenom] != null) return mem[n][largestDenom];
		int totalWays = 0;
		
		// choose 50c as possible
		if (largestDenom == 50 && n >= 50) 	totalWays += solve(n - 50, 50);

		// choose 25c
		if (largestDenom >= 25 && n >= 25) 	totalWays += solve(n - 25, 25);

		// choose 10c
		if (largestDenom >= 10 && n >= 10) 	totalWays += solve(n - 10, 10);
		
		// choose 5c
		if (largestDenom >= 5 && n >= 5)	totalWays += solve(n - 5, 5);
		
		// choose 1c
		if (largestDenom >= 1 && n >= 1) 	totalWays += solve(n - 1, 1);
		
		mem[n][largestDenom] = totalWays;
		return totalWays;
	}
	
	public void run(int cents){
		System.out.println(solve(cents, 50));
	}
	
	public static void main (String[] args){
		Scanner sc = new Scanner(System.in);
		CoinChange cc = new CoinChange();
		while(sc.hasNextInt()) {
			cc.run(sc.nextInt());
		}
		sc.close();
	}
}