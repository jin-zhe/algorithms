import java.util.Arrays;


public class SetOperations {

	public static void main(String[] args) {
		int[] set1 = {1, 3, 2, 66, 23, 92, 8};
		int[] set2 = {66, 8, 567, 1024, 512};
		System.out.println("Intersection: " + Arrays.toString(intersect(set1, set2)));
		System.out.println("Union: " + Arrays.toString(union(set1, set2)));
	}
	static int[] intersect(int[] arr1, int[] arr2){
		int[] res = new int[arr1.length + arr2.length]; // result set
		/* sort arrays */
		Arrays.sort(arr1);
		Arrays.sort(arr2);
		
		int ptrRes = 0;	// pointer for result set
		int ptr1 = 0;	// pointer for arr1
		int ptr2 = 0;	// pointer for arr2
		
		while (ptr1 < arr1.length && ptr2 < arr2.length){
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
	static int[] union(int[] arr1, int[] arr2){
		int[] res = new int[arr1.length + arr2.length];
		/* sort arrays */
		Arrays.sort(arr1);
		Arrays.sort(arr2);
		
		int ptrRes = 0;
		int ptr1 = 0;
		int ptr2 = 0;
		
		while (ptr1 < arr1.length || ptr2 < arr2.length){
			/* if both pointers have not ended */
			if (ptr1 < arr1.length && ptr2 < arr2.length){
				int curr1 = arr1[ptr1];
				int curr2 = arr2[ptr2];
				/* if both values equal, take intersection */
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
}
