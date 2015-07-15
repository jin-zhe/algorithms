import java.util.Scanner;


public class StringCompression {
	void run(String str){
		if (str.isEmpty()){
			System.out.println("");
			return;
		}
		
		StringBuffer sb = new StringBuffer();
		char prev = str.charAt(0);
		int freq = 1;
		sb.append(prev);
		
		for (int i=1; i<str.length(); i++){
			// if current character same as previous, update frequency
			if (str.charAt(i) == prev) freq++;
			// else append frequency to sb, update prev to current and reset frequency
			else{
				sb.append(Character.forDigit(freq, 10));
				prev = str.charAt(i);
				sb.append(prev);
				freq = 1;
			}
		}
		
		// IMPORTANT! add last frequency
		sb.append(Character.forDigit(freq, 10));
		
		System.out.println(sb.toString());
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		(new StringCompression ()).run(sc.nextLine());
		sc.close();
	}

}