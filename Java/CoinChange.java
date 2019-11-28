import java.util.Scanner;

/**
URL: http://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=615
Problem Description:
Suppose there are 5 types of coins: 50-cent, 25-cent, 10-cent, 5-cent, and
1-cent. We want to make changes with these coins for a given amount of money.

For example, if we have 11 cents, then we can make changes with one 10-cent coin
and one 1-cent coin, two 5-cent coins and one 1-cent coin, one 5-cent coin and
six 1-cent coins, or eleven 1-cent coins. So there are four ways of making
changes for 11 cents with the above coins. Note that we count that there is one
way of making change for zero cent.

Write a program to find the total number of different ways of making changes for
any amount of money in cents. Your program should be able to handle up to 7489
cents.
 */
public class CoinChange{
	Integer[][] mem;	// [cents][largetDenom]
	
	public CoinChange(){
		mem = new Integer[7489 + 1][51];
		/* Pre-process: leafs are 1 way each */
		mem[0][50] = 1;
		mem[0][25] = 1;
		mem[0][10] = 1;
		mem[0][5] = 1;
		mem[0][1] = 1;
	}
	
	/**
	 * Recursive method to determine number of coin combinations for value n
	 * @param n				current value left
	 * @param largestDenom	the last largest coin denomination used
	 * @return
	 */
	public int solve(int n, int largestDenom){
		if (mem[n][largestDenom] != null) {
			return mem[n][largestDenom];
		}
		int totalWays = 0;
		
		/* choose 50c */
		if (largestDenom == 50 && n >= 50)  {
			totalWays += solve(n - 50, 50);
		}
		/* choose 25c */
		if (largestDenom >= 25 && n >= 25)  {
			totalWays += solve(n - 25, 25);
		}
		/* choose 10c */
		if (largestDenom >= 10 && n >= 10) {
			totalWays += solve(n - 10, 10);
		}
		/* choose 5c */
		if (largestDenom >= 5 && n >= 5) {
			totalWays += solve(n - 5, 5);
		}
		/* choose 1c */
		if (largestDenom >= 1 && n >= 1) {
			totalWays += solve(n - 1, 1);
		}
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