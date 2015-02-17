import java.util.Scanner;
// http://www.careercup.com/question?id=6262507668766720
public class KudaneBitFlip {
	int[] arr;
	boolean isAllOnes; // corner case: all zeroes
	
	KudaneBitFlip(int[] arr, boolean isAllOnes){
		this.arr = arr;
		this.isAllOnes = isAllOnes;
	}
	
	// run modified Kadane's algorithm O(N): returns optimal range (start, end) to flip
	Pair<Integer,Integer> solve(){
		if (isAllOnes) return new Pair<Integer, Integer>(0, arr.length);	// corner case
		int temp_start = -1;
		int start = -1;						// start index of flip
		int end = -1;						// end index of flip
		int max = 0;
		
		int max_ending_here = 0;
		
		for (int i=0; i<arr.length; i++){
			int max_ending_prev = max_ending_here;			// store previous value
			max_ending_here += arr[i];						// update max_ending_here
			max_ending_here = Math.max(0, max_ending_here);	// determine max_ending_here

			// if new starting point began
			if (max_ending_prev == 0 && max_ending_here > 0){
				temp_start = i;			// restart temp_start at current
			}	
			// if higher value for max_ending_here is found
			if (max_ending_here > max){
				max = max_ending_here;	// update max
				start = temp_start;		// update start
				end = i;				// update end
			}
		}
		
		return new Pair<Integer, Integer>(start, end);
	}
	
	void run(){
		Pair<Integer, Integer> startEnd = solve();
//		System.out.println(startEnd);	// check
		int start = startEnd.first;
		int end = startEnd.second;
		int ans = 0;
		for (int i=0; i<arr.length; i++){
			// if within range
			if (i >= start && i <= end){
				if (arr[i] == 1) ans++;		// count flipped 0's
			}
			// else if not within range
			else{
				if (arr[i] == -1) ans++;	// count 1's
			}
		}
		System.out.println(ans);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N];
		boolean isAllOnes = true;
		for (int i=0; i<N; i++){
			int bit = sc.nextInt();
			// if bit is 1, change to -1
			if (bit == 1) arr[i] = -1;
			// else if bit is 0, change to 1
			else{
				arr[i] = 1;
				isAllOnes = false;
			}
		}
		KudaneBitFlip sol = new KudaneBitFlip(arr, isAllOnes);
		sol.run();
		sc.close();
	}
	
	class Pair<T,U>{
		T first;
		U second;
		// constructor
		public Pair(T first, U second){
			this.first = first;
			this.second = second;
		}
		
		public String toString(){
			return "(" + first + ", " + second + ")";
		}
	}
}