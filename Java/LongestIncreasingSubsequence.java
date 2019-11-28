import java.util.Arrays;

/* Longest Increasing Subsequence */
/* O(N^2) because 1+2+3+4...*/
public class LongestIncreasingSubsequence {
	int[] arr = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
//	int[] arr = {99,1,2,3};
//	int[] arr = {1,2,3};
//	int[] arr = {3,2,1};
//	int[] arr = {1};
	
	int[] mem = new int[arr.length]; 
	
	/* recursive: define endIndex as the last item that is picked for the sequence */
	int LIS_topDown(int endIndex){
		if (mem[endIndex] != 0)
			return mem[endIndex];
		
		int curr = arr[endIndex];
		int max = 1; // an individual item is a sequence of 1
		// from previous item down to first item
		for (int i=endIndex-1; i>=0; i--){
			// try choosing previous item iff it is lower than curr
			if (arr[i] < curr)
				max = Math.max(1 + LIS_topDown(i), max);	// update max
		}
		mem[endIndex] = max;	// memorize
		return max;
	}
	// driver function
	int LIS_topDown(){
		mem = new int[arr.length]; // initialize memory table
		int max = 1;
		for (int i=0; i<arr.length; i++)
			max = Math.max(LIS_topDown(i), max);
		return max;
	}
	
	/* iterative */
	int LIS_bottomUp(){
		mem = new int[arr.length]; // initialize memory table
		int maxLIS = 1;
		for (int i=0; i<arr.length; i++){
			
			int max = 1; // max LIS of previous sequences
			for (int j=0; j<i; j++)
				/* if jth item can be the previous picked item, (current item picked is arr[i]) */
				if (arr[j] < arr[i])
					max = Math.max(mem[j]+1, max);
			
			mem[i] = max;
			maxLIS = Math.max(max, maxLIS);
		}
		return maxLIS;
	}
	
	/* reconstructs the LIS */
	int[] backtrack(){
		/* determine index of last chosen item for longest increasing subsequence */
		int iMax = -1;	// index to be determined
		int len = 1;	// LIS length
		for (int i=0; i<mem.length; i++){
			if (mem[i] > len){
				len = mem[i];
				iMax = i;
			}
		}
		
		int[] subsequence = new int[len];
		subsequence[--len] = arr[iMax];
		/* determines subsequence from back to front */
		for (int i=iMax-1; i>=0; i--){
			int prevVal = subsequence[len];	// the succeeding subsequence value chosen in previous iteration
			int curr = arr[i];
			/* if curr is a possible candidate */
			if (len == mem[i] && prevVal > curr){
				subsequence[len-1] = curr; 	// assign value
				len--;						// decrement len (also array pointer)
				prevVal = curr;				// update prevVal
			}
		}
		
		return subsequence;

	}
	void run(){
		System.out.println("Top-down: " + LIS_topDown());
		System.out.println("Bottom-up: " + LIS_bottomUp());
		System.out.println("One valid LIS is: " + Arrays.toString(backtrack()));
	}
	
	public static void main(String[] args) {
		LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();
		lis.run();
	}
}