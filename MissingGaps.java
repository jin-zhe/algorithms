/**
 * Given a sorted list of numbers, write a function to return a string representing
 * the missing numbers from 0 to 99 inclusive.
 * e.g.
 * 	Given [0, 1, 2, 3, 5, 40, 90], the function should return "4, 6-39, 41-89, 91-99"
 */
public class MissingGaps {

	public static void main(String[] args) {
		int[] arr = {0, 1, 2, 3, 5, 40, 90};
//		int[] arr = {1, 98};
//		int[] arr = {5};
//		int[] arr = new int[0];
		System.out.println(getGaps(arr));
	}
	
	public static String getGaps(int[] arr){
		/* corner case */
		if (arr.length == 0){
			return "0-99";
		}
		
		StringBuffer sb = new StringBuffer();
		
		/* check starting missing numbers */
		int first = arr[0];
		int gap = first - 0;
		if (gap >= 1) {
			/* if just missing 0 */
			if (gap == 1)
				sb.append(0);
			/* else if missing initial range */
			else {
				sb.append("0-" + (arr[0] - 1) );
			}
			sb.append(", ");
		}
		
		for (int i=0; i<arr.length-1; i++){
			int curr = arr[i];
			int next = arr[i+1];
			gap = next - curr;
			/* if missing value(s) */
			if (gap > 1){
				/* if exactly one number is missing in progression */
				if (gap == 2){
					sb.append(curr + 1);
				}
				/* else if a range is missing */
				else {
					sb.append((curr + 1) + "-" + (next - 1));
				}
				sb.append(", ");
			}
		}
		
		/* check ending missing numbers */
		int last = arr[arr.length - 1];
		gap = 99 - last;
		if (gap > 0) {
			/* if just missing the previous number */
			if (gap == 1) {
				sb.append(99);
			}
			else {
				sb.append((last + 1) + "-99");
			}
			sb.append(", ");
		}
		
		
		if (sb.length() > 0){
			return sb.substring(0, sb.length()-2);
		}
		
		return sb.toString();
	}
}