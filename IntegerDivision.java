import java.util.ArrayList;
import java.util.Scanner;

/** 
 * Implement integer division without using / or % in O(logN)
 */
public class IntegerDivision {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println(divide(sc.nextInt(), sc.nextInt()));
		sc.close();
	}
	
	public static int divide(int dividend, int divisor){
		/* handle polarity */
		int polarity = ((dividend<0 && divisor>0) || (divisor<0 && dividend>0))? -1: 1;
		dividend = Math.abs(dividend);
		divisor = Math.abs(divisor);
		
		/* corner case */
		if (dividend < divisor)
			return 0;
		
		ArrayList<Integer> powerSeries = new ArrayList<Integer>(); // power series of 2
		powerSeries.add(1);	// 2^0 == 1
		int exponent = 0; 	// current index in power list
		int quotient = 0; 	// ans
		
		while (dividend >= divisor){
			int deduct = powerSeries.get(exponent) * divisor;
			if (dividend >= deduct){
				dividend -= deduct;
				quotient += powerSeries.get(exponent);
				
				/* update next value in power series if necessary */
				int last = powerSeries.get(powerSeries.size()-1); // last value in power series
				if (powerSeries.get(exponent) == last){
					powerSeries.add(last*2);
				}
				exponent++;	// increment exponent
			}
			else
				exponent--;	// decrement exponent
		}
	return quotient * polarity;
	}
}