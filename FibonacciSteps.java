import java.util.Scanner;

// Essentially fibonacci
public class FibonacciSteps {
	int n;			// number of steps
	Integer mem[];	// memorization array where [i] holds answer for i steps left
	
	// constructor
	public FibonacciSteps(int steps){
		n = steps;
		mem = new Integer[n + 1];
		mem[0] = 1;
	}
	
	public int solve(int stepsLeft){
		if (mem[stepsLeft] != null) return mem[stepsLeft];
		
		int ways = 0;
		if (stepsLeft >= 2) ways += solve(stepsLeft - 2);
		if (stepsLeft >= 1) ways += solve(stepsLeft - 1);
		mem[stepsLeft] = ways;
		return mem[stepsLeft];
	}
	
	public void run(){
		System.out.println(solve(n));
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		FibonacciSteps steps = new FibonacciSteps(sc.nextInt());
		steps.run();
		sc.close();
	}

}
