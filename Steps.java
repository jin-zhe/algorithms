import java.util.Scanner;
/**
 * Question: https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=787
 */
public class Steps {
	int steps;			// number of steps
	Integer mem[][];	// memorization array where [i] holds answer for i steps left
	int max;			// unattainable upper bound for value 
	
	// constructor
	public Steps(int steps){
		this.steps = steps;
		max = (int) Math.sqrt(steps);
		mem = new Integer[steps + 1][max + 1];
	}
	
	// flag to indicate if the last step can be reached under current circumstances
	boolean canReachLastStep(int stepsLeft, int previousStep){
		// if it satisfies worst case: when every subsequent step has to be smallerStep
		return ((previousStep - 1) - stepsLeft <= 0);
	}
	
	public int solve(int stepsLeft, int previousStep){
		System.out.println(stepsLeft+", "+previousStep);
		if (mem[stepsLeft][previousStep] != null) return mem[stepsLeft][previousStep];
		// base cases
		// if we are currently at the last step and the next step can be 1
		if (stepsLeft == 1 && previousStep <= 2){
			mem[stepsLeft][previousStep] = 1;
			return 1;
		}
		// else if we can never reach the last step OR we can never cross the last step with 1 step 
		if (!canReachLastStep(stepsLeft, previousStep)){
			mem[stepsLeft][previousStep] = steps;
			return steps;
		}
		
		int min = steps;
		if (previousStep > 1){
			int smallerStep	= 1 + solve(stepsLeft - (previousStep - 1), previousStep - 1);
			min = (smallerStep < min)? smallerStep: min;	// update min
//			System.out.println(stepsLeft+","+previousStep+" (smallerStep):" + smallerStep);
		}
		if (previousStep > 0 && stepsLeft - previousStep >= 1){
			int sameStep = 1 + solve(stepsLeft - previousStep, previousStep);
			min = (sameStep < min)? sameStep: min;			// update min
//			System.out.println(stepsLeft+","+previousStep+" (sameStep):" + sameStep);
		}
		if ((previousStep + 1) <= max){
			int largerStep 	= 1 + solve(stepsLeft - (previousStep + 1), previousStep + 1);
			min = (largerStep < min)? largerStep: min;		// update min
//			System.out.println(stepsLeft+","+previousStep+" (largerStep):" + largerStep);
		}
		mem[stepsLeft][previousStep] = min;
		return min;
	}
	
	public void run(){
		System.out.println(solve(steps, 0));
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		for (int i=0; i<N; i++){
			int start = sc.nextInt();
			int end = sc.nextInt();
			Steps s = new Steps(end - start);
			s.run();
		}
		sc.close();
	}
}