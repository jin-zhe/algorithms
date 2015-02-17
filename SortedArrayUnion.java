// Given two sorted arrays and a number k, find the kth largest number in the union of the two arrays. Do it in place
public class SortedArrayUnion {
	int[] arr1;
	int[] arr2;
	
	SortedArrayUnion(int[] arr1, int[] arr2){
		this.arr1 = arr1;
		this.arr2 = arr2;
	}
	
	// O(K)
	int solve(int k){
		// start iterating from the last index of arr1 and arr2 respectively
		int i = arr1.length - 1;
		int j = arr2.length - 1;
		
		Integer KMax = null;
		
		// loop runs exactly k times
		while(k>0){
			// if either has reached the end of its list, progress down the other list
			if (i<0 || j<0){
				if (i<0){
					KMax = arr2[j];
					j--;
				}
				if (j<0){
					KMax = arr1[i];
					i--;
				}
			}
			// else, always choose the larger element and progress down chosen list
			else{
				if (arr1[i] > arr2[j]){
					KMax = arr1[i];
					i--;	// progress arr1
				}
				else if (arr2[j] > arr1[i]){
					KMax = arr2[j];
					j--;	// progress arr2
				}
			}
			k--;
		}
		return KMax;
	}
	
	void run(int k){
		System.out.println(solve(k));
	}
	
	public static void main(String[] args) {
		int[] arr1 = {1,3,5,7,9};
		int[] arr2 = {2,4,6,8,10};
		(new SortedArrayUnion(arr1, arr2)).run(10);
	}
}