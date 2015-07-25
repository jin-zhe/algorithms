import java.util.Arrays;
import java.util.Scanner;

/*
 * Implementation of Karatsuba's multiplication algorithm
 * 	This code supports non-negative numeric multiplications of up to base 36 ('Z' is max permitted by alphabets)
 */
public class Karatsuba {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
    	int base = sc.nextInt();	// base in decimal
		sc.nextLine();
		String x = sc.nextLine();
		String y = sc.nextLine();
		System.out.println(multiply(x, y, base));
		sc.close();
	}
	 /**
     *  Converts a numeric string into an array of int, preserving right-left order and ignoring radix
     */
    private static int[] toIntArray(String num, boolean hasRadix){
    	int[] arr = (hasRadix)? new int[num.length()-1]: new int[num.length()];
    	int index = 0;
    	for (int i=0; i<num.length(); i++){
    		char character = num.charAt(i);
    		// if radix
    		if (character == '.') continue;	// skip
    		arr[index] = parseDigit(character);
    		index++;
    	}
    	return arr;
    }
    
    /** 
     * Converts a int array to numeric string
     */
    private static String intArrayToString(int[] arr){
    	StringBuffer sb = new StringBuffer();
    	for (int i: arr){
    		sb.append(toDigit(i));
    	}
    	if (sb.charAt(0) == '0') sb.deleteCharAt(0);
    	return sb.toString();
    }
    
    /**
     * Driver function for KaratsubaMultiply
     * 	Formats input parameters and output
     */
    private static String multiply(String x, String y, int base){
		
		int radix_x_Index = x.indexOf('.');
		int radix_y_Index = y.indexOf('.');

		// reversed (numeric) index of radix points
		int radix_x_pos = (radix_x_Index == -1)? 0: x.length() - x.indexOf('.') - 1;	
		int radix_y_pos = (radix_y_Index == -1)? 0: y.length() - y.indexOf('.') - 1;
		
		// convert to int arrays (ignores radixes)
		int[] x_arr = (radix_x_Index == -1)? toIntArray(x, false): toIntArray(x, true);
		int[] y_arr = (radix_y_Index == -1)? toIntArray(y, false): toIntArray(y, true);
		
		// compute multiplication
		int[] ans_arr = karatsubaMultiply(x_arr, y_arr, base);	// by karatsuba
		StringBuffer sb = new StringBuffer(intArrayToString(ans_arr));
		
		// incorporating back the radix point
		if (radix_x_pos + radix_y_pos != 0){
			int radix_pos = radix_x_pos + radix_y_pos;
			if (radix_pos > ans_arr.length){
				char[] shiftZeros = new char[radix_pos - ans_arr.length];
			    Arrays.fill(shiftZeros, '0');	// string of amount number of '0's
				sb.insert(0, shiftZeros);
			}
			sb.insert(ans_arr.length - radix_pos - 1, '.');
		}

		return trimZeros(sb.toString());
    }
    
    /**
     * Karatsuba's multiplication algorithm (recursive)
     */
	private static int[] karatsubaMultiply(int[] x, int[] y, int base){
		
		// equalize string lengths by prepending '0's to shorter string
		if (x.length > y.length)
			y = shift("right", y, x.length - y.length);
		else if (y.length > x.length)
			x = shift("right", x, y.length - x.length);

		// base case: less than or equal to 61 digits
		// Reason: Karatsuba works best for multiplicands above 320 bits ~ 186 digits for decimal
		if (x.length <= 141 && y.length <= 141){
			return longMultiplication(x, y, base);
		}
		int n = x.length;
		int nHalf = x.length - n/2;
		int[] x1 = copyOfRange(x, 0, n/2);
		int[] x0 = copyOfRange(x, n/2, x.length);
		int[] y1 = copyOfRange(y, 0, n/2);
		int[] y0 = copyOfRange(y, n/2, y.length);
		
		// 3 recursive calls
		int[] x1y1 = karatsubaMultiply(x1, y1, base);	// x1 * y1
		int[] x1x0y1y0 = karatsubaMultiply(addition(x1, x0, base), addition(y1 , y0, base), base);	// (x1 + x0)*(y1 * y0)
		int[] x0y0 = karatsubaMultiply(x0, y0, base);	// x0 * y0
		
//		System.out.println("x = " + x + ", y = " + y + ", n/2 = " + n/2);						// check
//		System.out.println("x1 = " + x1 + ", x0 = " + x0 + ", y1 = " + y1 + ", y0 = " + y0);	// check
//		System.out.println("x1y1 = " + x1y1 + ", x1x0y1y0 = " + x1x0y1y0 + ", x0y0 = " + x0y0);	// check
//		System.out.println("------------------------------");									// check
		int[] part1 = shift("left", x1y1, 2*nHalf);
		int[] part2 = shift("left", subtraction(subtraction(x1x0y1y0, x1y1, base), x0y0, base), nHalf);

		return addition(addition(part1, part2, base), x0y0, base);
	}
    
    /**
     *  Long multiplication algorithm
     */
    private static int[] longMultiplication(int[] x, int[] y, int base){
    	int[] top, bot;	// top and bottom operands in long multiplication
    	if (x.length >= y.length){
    		top = x;
    		bot = y;
    	}
    	else{
    		top = y;
    		bot = x;
    	}
    	int[] ans = new int[x.length + y.length];
		int offset = 0;								// right offset when adding the rows
		
    	// for each digit in bot string from right to left
    	for (int i=bot.length-1; i>=0; i--){
        	int carryOver_product = 0;				// carryover due to multiplication
        	int carryOver_add = 0;					// carryover due to addition 
        	
        	// for each digit in top string from right to left
        	for (int j=top.length-1; j>=0; j--){
    			/* derive product of 2 digits */
        		int product = bot[i] * top[j] + carryOver_product;	// multiply top digit with bottom digit and add with carryover
//    			System.out.println("top[j] = " + top[j] + ", bot[i] = " + bot[i] + ", carryOver = " + carryOver + ", product = " + product);	// check
        		carryOver_product = product/base;					// carry over from product
    			product %= base;									// product after forwarding carryover
    			
    			/* add product to ans[] */
    			int ans_index = ans.length - (top.length - j) - offset;	// corresponding index in ans[]
    			ans[ans_index] += product + carryOver_add;
    			carryOver_add = ans[ans_index]/base;
    			ans[ans_index] = ans[ans_index]%base;
    		}
        	// handle final carryovers
    		if (carryOver_product != 0 || carryOver_add != 0){
    			// NOTE: ans[ans_index] is definitely == 0, mathematically (carryOver_product + carryOver_add < base) is guaranteed
    			int ans_index = ans.length - (top.length + 1) - offset;
    			ans[ans_index] += carryOver_product + carryOver_add;
    		}
    		offset++;	// update: pad one more '0' to the right of next row
    	}
    	return ans;
    }

	/** 
	 * Shifts array by amount given and in the direction stated
	 */
	private static int[] shift(String direction, int[] arr, int amount){
//		System.out.println("[shift] numericString,amount: " + numericString + "," + amount);	// check
		int[] shiftedArr = new int[arr.length + amount];
	    
		// if pad right
		if (direction == "left"){
			for (int i=0; i<arr.length; i++){
				shiftedArr[i] = arr[i];
			}
		}
		// else if pad left
		else if (direction == "right"){
			for (int i=0; i<arr.length; i++){
				shiftedArr[i + amount] = arr[i];
			}
		}
		return shiftedArr;
	}
    
    /**
     *  Adds two int arrays of the given base
     */
    private static int[] addition(int[] a, int[] b, int base){
    	int[] ans = new int[Math.max(a.length, b.length) + 1];
    	int carryOver = 0;	// 0 or 1
    	
		// scans from right to left
    	for (int i=ans.length-1; i>=0; i--){
			int sum = carryOver;
			int numericPos = ans.length - i;	// numeric position of ans
			
			// if within region of a
			if (numericPos <= a.length){
				sum += a[a.length - numericPos];
			}
			
			// if within region of b
			if (numericPos <= b.length){
				sum += b[b.length - numericPos];
			}
			ans[i] = sum%base;		// update ans
			carryOver = sum/base;	// update carry over
		}
		return ans;
    }

    /**
     *  Subtracts 'smaller' from 'greater' int arrays of the given base
     *  will not produce negative numbers since greater and smaller are guaranteed by caller
     */
    // 
	private static int[] subtraction(int[] greater, int[] smaller, int base){
		int borrowOver = 0;	// 0 or 1
		for (int i=smaller.length-1; i>=0; i--){
			int index_greater = greater.length - (smaller.length - i);	// corresponding index in greater
			greater[index_greater] = greater[index_greater] - smaller[i] - borrowOver;
			// if need to borrow over, borrowOver = 1
			if (greater[index_greater] < 0){
				greater[index_greater] += base;
				borrowOver = 1;
			}
			// else if no need to borrow over, borrowOver = 0
			else borrowOver = 0;
		}
		// handle final borrowOver
		if (borrowOver != 0) greater[greater.length - smaller.length - 1] -= borrowOver;
		return greater;
    }
	
	/**
	 * Creates subarray from the given range
	 */
	private static int[] copyOfRange(int[] src, int start, int end){
		int[] dest = new int[end - start];
		System.arraycopy(src, start, dest, 0, end - start);
		return dest;
	}

	/**
	 * Use to trim leading and trailing zeros on a result string.
	 */
	private static String trimZeros(String input) {
		int left = 0;
		int right = input.length()-1;
		int fp = input.indexOf('.');
		if (fp == -1) {
			fp = input.length();
		}
		
		while(left < fp-1) {
			if (input.charAt(left) != '0')
				break;
			left++;
		}
		
		while (right >= fp) {
			if (input.charAt(right) != '0') {
				if (input.charAt(right) == '.')
					right--;
				break;
			}
			right--;
		}
		
		if (left >= fp)
			return "0" + input.substring(left,right+1);
		return input.substring(left,right+1);
	}
    
	/**
	 * Convert digit to int (for reading)
	 */
	private static int parseDigit(char c) {
		if (c <= '9') {
			return c - '0';
		} 
		return c - 'A' + 10;
	}
	
	/**
	 * Convert int to digit. (for printing)
	 */
	private static char toDigit(int digit) {
		if (digit <= 9) {
			return (char)(digit + '0');
		} 
		return (char)(digit - 10 + 'A');
	}

	/**
	 * For checking: prints out the int array
	 */
	@SuppressWarnings("unused")
	private static void printIntArray(int[] arr){
		System.out.print("[printIntArray]: ");
		for (int i: arr) System.out.print(i);
		System.out.println();
	}
}
