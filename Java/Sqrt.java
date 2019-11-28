import java.util.Scanner;


public class Sqrt {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		if (N < 0) System.out.println("Negative number!");
		else if (N == 0) System.out.println(0);
		else{
			int max = N;
			int min = 1;
			// while difference between max and min is larger than 1
			while (max - min > 1){
				int mid = (min + max)/2;
				int square = mid*mid;
				// if new upper bound is found, update max
				if (square > N) max = (min + max)/2;
				// else if new lower bound is found, update min
				else min = (min + max)/2;
			}
			System.out.println(min);
		}
		sc.close();

	}

}
