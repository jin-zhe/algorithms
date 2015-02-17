import java.util.Scanner;

// Implement integer division without using / or % in O(logN)
// Same as Sqrt.java
public class IntegerDivision {
	int dividend;
	int divisor;
	IntegerDivision(int numerator, int denominator){
		dividend = numerator;
		divisor = denominator;
	}
	int solve(){
		if (divisor > dividend) return 0;
		int low = 1;
		int high = dividend;
		
		while (high - low > 1){
			int mid = (low + high)/2;
			int test = mid * divisor;
			if (test == dividend) return mid;
			if (test > dividend) high = mid;
			else low = mid;
		}
		return low;
	}
	
	void run(){
		System.out.println(solve());
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		(new IntegerDivision(sc.nextInt(), sc.nextInt())).run();
		sc.close();
		
	}

}
