
public class LongestIncreasingSequence {
	int[] arr = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
	int[] mem = new int[100]; 
	
	/** O(N) */
	int getLongestSequence(){
		// initial conditions
		int max = 1;
		int i = 0;
		
		while (i<arr.length){
			int test = 1;
			int curr = arr[i];
			
			for (int j=i+1; j<arr.length; j++){
				if (arr[j] > curr){
					test++;			// increment test
					curr = arr[j];	// update new current
				}
				else break;
			}
			
			if (test > max) max = test;
			i += test;	// increment test
		}
		return max;
	}
	
	int LIS(int start){
		if (mem[start] != 0) return mem[start];
		
		int curr = arr[start];
		int max = 1;
		for (int i=start+1; i<arr.length; i++){
			// try next starting subsequence iff it is larger than curr
			if (arr[i] > curr){
				int ans = 1 + LIS(i);
				if (ans > max) max = ans;				// update max
			}
		}
		mem[start] = max;	// memorize
		return max;
	}
	
	void run(){
		System.out.println("Longest Sequence: " + getLongestSequence());
		System.out.println("Longest Subsequence: " + LIS(0));
	}
	
	public static void main(String[] args) {
		LongestIncreasingSequence lis = new LongestIncreasingSequence();
		lis.run();
	}

}
