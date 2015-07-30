import java.util.Scanner;

/**
 * Write a function strstr (char[] haystack, char[] needle) to return the first
 * index occurrence of needle in haystack.  * If needle is not present in
 * haystack, return -1
 */

public class NeedleHaystack {
	// implement String indexOf method O(MN)
	int strstr(char[] haystack, char[] needle){
		int ans = -1;
		// edge cases
		if (needle.length > haystack.length || needle.length == 0 || haystack.length == 0)
			return ans;
		
		// loop N-M times
		for (int i=0; i<=haystack.length-needle.length; i++){
			// loop M times
			for (int j=0; j<needle.length; j++){
				if (haystack[i+j] != needle[j]) break;
				if (j == needle.length-1) ans = i;
			}
			if (ans != -1) break;
		}
		return ans;
	}
	
	void run(char[] haystack, char[] needle){
		System.out.println(strstr(haystack, needle));
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		(new NeedleHaystack()).run(sc.nextLine().toCharArray(), sc.nextLine().toCharArray());
		sc.close();
	}
}