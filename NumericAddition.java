import java.util.Scanner;


public class NumericAddition {
	void addDecimal(int intA, int intB){
		String a = Integer.toString(intA);
		String b = Integer.toString(intB);
		// if b is shorter
		if (a.length() > b.length()){
			int diff = a.length() - b.length();
			StringBuffer sb = new StringBuffer();
			for (int i=0; i<diff; i++) sb.append('0');
			sb.append(b);
			b = sb.toString();
		}
		// if a is shorter
		else if (b.length() > a.length()){
			int diff = b.length() - a.length();
			StringBuffer sb = new StringBuffer();
			for (int i=0; i<diff; i++) sb.append('0');
			sb.append(a);
			a = sb.toString();
		}

		StringBuffer sb = new StringBuffer();
		int carry = 0;
		for (int i=a.length()-1; i>=0; i--){
			int digitA = a.charAt(i) - '0';
			int digitB = b.charAt(i) - '0';
			
			int sum = carry + digitA + digitB;
			if (sum >= 10){
				carry = sum/10;
				int curr = sum%10 + '0';
				sb.append((char) curr);
			}
			else{
				carry = 0;
				int curr = sum + '0';
				sb.append((char) curr);
			}
		}
		// handle final carry if any
		if (carry != 0){
			int curr = carry + '0';
			sb.append((char) curr);
		}
		sb.reverse();
		System.out.println(sb.toString());
	}
	
	void addBinary(int intA, int intB){
		String a = Integer.toBinaryString(intA);
		String b = Integer.toBinaryString(intB);
		
		// equalize strings by prepending 0's
		if (a.length() > b.length()){
			int diff = a.length() - b.length();
			StringBuffer sb = new StringBuffer();
			for (int i=0; i<diff; i++) sb.append('0');
			sb.append(b);
			b = sb.toString();
		}
		else if (b.length() > a.length()){
			int diff = b.length() - a.length();
			StringBuffer sb = new StringBuffer();
			for (int i=0; i<diff; i++) sb.append('0');
			sb.append(a);
			a = sb.toString();
		}
		int carry = 0;
		StringBuffer sb = new StringBuffer();
		for (int i=a.length()-1; i>=0; i--){
			int aBit = a.charAt(i) - '0';
			int bBit = b.charAt(i) - '0';
			int sum = carry + aBit + bBit;
			if (sum == 3){
				sb.append('1');
				carry = 1;
			}
			else if (sum == 2){
				sb.append('0');
				carry = 1;
			}
			else{
				sb.append((char)((char) sum + '0'));	// '1' or '0'
				carry = 0;
			}
		}
		if (carry == 1) sb.append('1');
		sb.reverse();
		System.out.println(Integer.parseInt(sb.toString(),2));
	}
	
	void run(){
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		sc.close();
		NumericAddition na = new NumericAddition();
		System.out.println("Binary add:");
		na.addBinary(a, b);
		System.out.println("Decimal add:");
		na.addDecimal(a, b);
	}

}
