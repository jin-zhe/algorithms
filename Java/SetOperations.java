import java.util.Arrays;


public class SetOperations {

	public static void main(String[] args) {
		int[] set1 = {1, 3, 2, 66, 23, 92, 8};
		int[] set2 = {66, 8, 567, 1024, 512};
		int[][] list = {{1, 3, 6, 2, 11},
						{6, 44, 3, 1, 2},
						{3, 44, 10, 2, 1},
						{3, 2, 1, 44, 11}};
		System.out.println("Intersection: " + Arrays.toString(intersect(set1, set2)));
		System.out.println("Union:        " + Arrays.toString(union(set1, set2)));
		System.out.println("n-way intersection: " + Arrays.toString(nWayIntersect(list)));
	}
	
	/**
	 * Gets the intersection of two sorted lists
	 * @param arr1	list 1
	 * @param arr2	list 2
	 * @return		intersection
	 */
	public static int[] intersect(int[] arr1, int[] arr2) {
		int[] res = new int[arr1.length + arr2.length]; // result set
		/* sort arrays */
		Arrays.sort(arr1);
		Arrays.sort(arr2);
		
		int ptrRes = 0;	// pointer for result set
		int ptr1 = 0;	// pointer for arr1
		int ptr2 = 0;	// pointer for arr2
		
		while (ptr1 < arr1.length && ptr2 < arr2.length) {
			int curr1 = arr1[ptr1];
			int curr2 = arr2[ptr2];
			
			/* if intersection found */
			if (curr1 == curr2){
				res[ptrRes] = curr1;
				ptrRes++;
				ptr1++;
				ptr2++;
			}
			/* else if curr1 is lower */
			else if (curr1 < curr2){
				ptr1++;
			}
			/* else if curr2 is lower */
			else{
				ptr2++;
			}
		}
		return Arrays.copyOfRange(res, 0, ptrRes);
	}
	
	/**
	 * Gets the union of two sorted lists
	 * @param arr1	list 1
	 * @param arr2	list 2
	 * @return		union
	 */
	public static int[] union(int[] arr1, int[] arr2) {
		int[] res = new int[arr1.length + arr2.length];
		/* sort arrays */
		Arrays.sort(arr1);
		Arrays.sort(arr2);
		
		int ptrRes = 0;
		int ptr1 = 0;
		int ptr2 = 0;
		
		while (ptr1 < arr1.length || ptr2 < arr2.length) {
			/* if both pointers have not ended */
			if (ptr1 < arr1.length && ptr2 < arr2.length) {
				int curr1 = arr1[ptr1];
				int curr2 = arr2[ptr2];
				/* if both values equal, take value and advance both pointers */
				if (curr1 == curr2){
					res[ptrRes] = curr1;
					ptrRes++;
					ptr1++;
					ptr2++;
				}
				/* else take the lower value */
				else if (curr1 < curr2){
					res[ptrRes] = curr1;
					ptrRes++;
					ptr1++;
				}
				else{
					res[ptrRes] = curr2;
					ptrRes++;
					ptr2++;
				}
			}
			/* if pointer 2 ended */
			else if (ptr1 < arr1.length){
				res[ptrRes] = arr1[ptr1];
				ptrRes++;
				ptr1++;
			}
			/* if pointer 1 ended */
			else{
				res[ptrRes] = arr2[ptr2];
				ptrRes++;
				ptr2++;
			}
		}
		return Arrays.copyOfRange(res, 0, ptrRes);
	}
	
	/**
	 * Performs intersection on a list of lists
	 * @param list	list of n lists
	 * @return		n-way intersection
	 */
	public static int[] nWayIntersect(int[][] list) {
		return nWayIntersect(list, list.length - 1);
	}
	/**
	 * Recursively gets the intersection of list 0 to list i
	 * @param list	list of lists
	 * @param i		current list of interest
	 * @return		intersection of lists 0 to i
	 */
	public static int[] nWayIntersect(int[][] list, int i) {
		if (i == 1) { return intersect(list[0], list[1]); }
		int[] currList = list[i];
		Arrays.sort(currList);
		int[] prevIntersections = nWayIntersect(list, i - 1);
		return intersect(currList, prevIntersections);
	}
}
