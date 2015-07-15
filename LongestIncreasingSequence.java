public class LongestIncreasingSequence {
	int[] arr = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
	int[] mem = new int[100]; 
	
	/* Longest Increasing Sequence */
	int LIS(){
		// initial conditions
		int globalMax = (arr.length > 0)? 1: 0; // maximum increasing sequence recorded
		int i = 0;
		
		/* O(N) single pass */
		while (i<arr.length){
			int localMax = 1;	// longest increasing local sequence
			int curr = arr[i]; 	// value at current position of inspection
			
			for (int j=i+1; j<arr.length; j++){
				if (arr[j] > curr){
					localMax++;		// increment test
					curr = arr[j];	// update new current
				}
				else break;	// break if sequence cannot continue
			}
			
			globalMax = Math.max(localMax, globalMax);	// update globalMax
			i += localMax;	// **SMART** increment index by length of local increasing subsequence
		}
		return globalMax;
	}

	void run(){
		System.out.println(LIS());
	}
	
	public static void main(String[] args) {
		LongestIncreasingSequence lis = new LongestIncreasingSequence();
		lis.run();
	}
}
