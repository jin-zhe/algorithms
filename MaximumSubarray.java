// Kadane's algorithm (would not work if all array elements are negative)
public class MaximumSubarray {

	public static void main(String[] args) {
		int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
		
		int max = 0;
		int max_ending_here = 0;
		for (int i: arr){
			max_ending_here = Math.max(0, max_ending_here + i);
			max = Math.max(max, max_ending_here);
		}
		System.out.println(max);
	}

}