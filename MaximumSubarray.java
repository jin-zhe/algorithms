// Kadane's algorithm (would not work if all array elements are negative)
public class MaximumSubarray {

	public static void main(String[] args) {
		int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
//		int[] arr = {1, 2, 3 ,4 ,5};
//		int[] arr = {1};
//		int[] arr = {-1};
		
		int localMax = 0;
		int globalMax = 0;
		int startPointLocal = 0;
		int startPointGlobal = 0;
		int endPointGlobal = -1;
		
		// preliminary check
		boolean isAllNegative = true;
		for (int i: arr){
			if (i > 0){
				isAllNegative = false;
				break;
			}
		}
		if (isAllNegative) {
			System.out.println("All array elements are negative! Answer is indeterminate!");
			return;
		}
		
		for (int i=0; i<arr.length; i++) {
			int current = arr[i]; 
			localMax += current;
			
			if (localMax > globalMax){
				globalMax = localMax;
				endPointGlobal = i;
				startPointGlobal = startPointLocal;
			}
			else if (localMax < 0){
				localMax = 0;			// resets localMax
				startPointLocal = i+1; 	// update new start point as next index (Note it's okay if i+1 = arr.length because its the end of loop anyway)
			}
		}
		
		System.out.println("Maximum subarray is " + globalMax + " from positions " + startPointGlobal + " to " + endPointGlobal + " inclusive");
	}
}