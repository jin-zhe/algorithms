import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;


public class DigitPermutations {
	String num;	// given number
	char[] arr;	// given array
	
	DigitPermutations(long num){
		this.num = Long.toString(num);
		arr = (Long.toString(num)).toCharArray();
	}
	
	/** 
	 * returns the next larger integer with the given digits 
	 * N(logN) solution: Worst case 15432
	 * 
	 * */
	String getNextPermutation(){
		int pivot = arr.length-1; // pivot position in arr
		for (int i=arr.length-1; i>0; i--){
			char curr = arr[i];
			char left = arr[i-1];
			if (left < curr){
				pivot = i - 1;	// update pivot
				break;
			}
		}
		// Edge case: where it is in descending order e.g. 54321 (no next larger available)
		if (pivot == arr.length-1) return num;
		
		char[] arrLeft = Arrays.copyOfRange(arr, 0, pivot + 1);
		char[] arrRight = Arrays.copyOfRange(arr, pivot + 1, arr.length);
		
		char leftLast = arrLeft[arrLeft.length - 1];
		int swapPos = 0; // position in arrRight to swap with arrLeft[arrLeft.length - 1]
		
		// position of the minimal larger character than leftLast in right arr
		for (int i=0; i<arrRight.length; i++){
			char curr = arrRight[i];
			if (curr > leftLast && curr < arrRight[swapPos])
				swapPos = i;
		}
		
		// swap element at arrRight[rightMinLargerPosition] with arrLeft[arr.length - 1]
		arrLeft[arrLeft.length - 1] = arrRight[swapPos];
		arrRight[swapPos] = leftLast;
		
		// sort right array from smallest to largest (ensure minimum number)
		Arrays.sort(arrRight);
		
		StringBuffer sb = new StringBuffer();
		sb.append(arrLeft);
		sb.append(arrRight);
		
		return sb.toString();
	}
	
	/** returns the next smaller integer with the given digits */
	String getPrevPermutation(){
		// 14123
		// 14 123
		// 13 124
		// 13 421
		// 13421 vs 14123
		int pivot = arr.length - 1;
		// determine pivot
		for (int i=arr.length-1; i>0; i--){
			char left = arr[i - 1];
			char curr = arr[i];
			if (left > curr){
				pivot = i - 1;
				break;
			}
		}
		// Edge case i.e. 12345
		if (pivot == arr.length - 1) return num;
		
		char[] arrLeft 	= Arrays.copyOfRange(arr, 0, pivot + 1);
		char[] arrRight = Arrays.copyOfRange(arr, pivot + 1, arr.length);;
		
		char leftLast = arrLeft[arrLeft.length - 1];
		int swapPos = 0;	// index in arrRight to be swapped with the last element of arrLeft
		
		for (int i=0; i<arrRight.length; i++){
			char curr = arrRight[i];
			// if current element is larger in arrRight and lower than leftLast
			if (curr > arrRight[swapPos] && curr < leftLast)
				swapPos = i;
		}
		
		// swap numbers
		arrLeft[arrLeft.length - 1] = arrRight[swapPos];
		arrRight[swapPos] = leftLast;
		
		// sort arrRight
		Character[] arrRightObj = new Character[arrRight.length];			// convert to Character[]
		for (int i=0; i<arrRight.length; i++) arrRightObj[i] = (Character) arrRight[i];
		Arrays.sort(arrRightObj, new Comparator<Character>(){
            public int compare(Character o1, Character o2){
                return o2 - o1;
            }
        });
		for (int i=0; i<arrRight.length; i++) arrRight[i] = arrRightObj[i];	// convert back to char[]
		
		StringBuffer sb = new StringBuffer();
		sb.append(arrLeft);
		sb.append(arrRight);
		
		return sb.toString();
	}
	
	void run(){
		System.out.println("Next Permutation:");
		System.out.println(getNextPermutation());
		System.out.println("Previous Permutation:");
		System.out.println(getPrevPermutation());
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		DigitPermutations N = new DigitPermutations(sc.nextLong());
		N.run();
		sc.close();
	}

}
