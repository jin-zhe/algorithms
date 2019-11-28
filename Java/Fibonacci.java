import java.util.Scanner;
// implementing Fibonacci iteratively
public class Fibonacci {
	// 1,1,2,3,5,8
	/**
	 * O(N) Bottom-up DP
	 */
	void solve(int num){
		if (num == 0 || num == 1){
			System.out.println(1);
			return;
		}
		int prev = 1;
		int ans = 1;
		for (int i=0; i<num-1; i++){
			int temp = ans;
			ans += prev;
			prev = temp;
		}
		System.out.println(ans);
	}
	
	public static void main(String[] args){
		Fibonacci fib = new Fibonacci();
		Scanner sc = new Scanner(System.in);
		fib.solve(sc.nextInt());
		sc.close();
	}
}
