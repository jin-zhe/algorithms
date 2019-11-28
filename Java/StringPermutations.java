import java.util.ArrayList;
import java.util.Scanner;
/**
 * Problem Description:
 * Given a string, return a list of all its permutations
 * 
 * Note:
 * These implementations do not differentiate between strings of same sequences
 * i.e. "aaa" will generate 6 permutations despite them all being "aaa"
 */
public class StringPermutations {
	
	public static void main(String[] args) {
		ArrayList<String> permutations;
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		sc.close();
		System.out.println("== Method 1 ========");
		permutations = permutate1(str);
		printPermutations(permutations);
		System.out.println("== Method 2 ========");
		permutations = permutate2(str);
		printPermutations(permutations);
	}
	
	/**
	 * Method 1:
	 * To get all permutations of String:
	 * 	1. For each character in current range
	 * 		1.1 let it be the suffix by swapping with the last character
	 * 		1.2 Fix suffix character by recursing down with decremented range
	 * 		1.3 Swap back character to restore configuration for next iteration 
	 * @param str			character array representing string to be permutated
	 * @param len			defines implicit range [0, len-1] in str to be permutated
	 * @param permutations	string array to be populated at the base case
	 */
	public static void permutate1(char[] str, int len,
								  ArrayList<String> permutations) {
		/* base case: single character left */
		if (len == 1) {
			permutations.add(new String(str));
		}
		else {
			int lastPos = len - 1; // index of the last position in current range
			/* let every character in current range be the suffix */
			for (int i=0; i<len; i++) {
				swap(str, i, lastPos);	// let item at i be the suffix
				permutate1(str, len-1, permutations); // recurse by decrementing range (i.e. fixing the suffix)
				swap(str, i, lastPos);	// restore for next iteration
			}
		}
	}
	public static ArrayList<String> permutate1(String str) {
		ArrayList<String> permutations = new ArrayList<String>();
		permutate1(str.toCharArray(), str.length(), permutations);
		return permutations;
	}
	
	/**
	 * Method 2:
	 * To get all permutations of string:
	 * 	1. For each character in string
	 * 		1.1 Remove character from string, we call it current prefix
	 * 		1.2 Recurse: For each permutation of string (exclusive of prefix),
	 * 			prepend prefix to it to form new string
	 * E.g.
	 * perm("avx")
	 * 		'a': perm("vx")		// denotes 'a' as prefix for every permutation of "vx"
	 * 			'v': perm("x")
	 * 				"avx"		// 1
	 * 			'x': perm("v")
	 * 				"axv"		// 2
	 * 		'v': perm("ax")
	 * 			'a': perm("x")
	 * 				"vax"		// 3
	 * 			'x': perm("a")
	 * 				"vxa"		// 4
	 * 		'x': perm("av")
	 * 			'a': perm("v")
	 * 				"xav"		// 5
	 * 			'v': perm("a")
	 * 				"xva"		// 6 total permutations
	 * @param prefix		accumulated prefixes
	 * @param str			string to be permutated
	 * @param permutations	string array to be populated at the base case
	 */
	public static void permutate2(String prefix, String str,
								  ArrayList<String> permutations) {
		/* base case */
		if (str.length() == 1) {
			permutations.add(prefix + str);
		}
		else {
			/* every character as a prefix candidate */
			for (int i=0; i<str.length(); i++) {
				char currPrefix = str.charAt(i);
				/* remove currPrefix from str */
				String newStr = str.substring(0, i) +
								str.substring(i+1, str.length());
				/* recurse down with updated prefix */
				permutate2(prefix + currPrefix, newStr, permutations);
			}
		}
	}
	public static ArrayList<String> permutate2(String str) {
		ArrayList<String> permutations = new ArrayList<String>();
		permutate2("", str, permutations);
		return permutations;
	}
	
	/* helper methods */
	/**
	 * Prints each permutation line by line
	 * @param permutations	list of StringBuffers representing permutations
	 */
	public static void printPermutations (ArrayList<String> permutations) {
		System.out.println(permutations.size() + " permutations: ");
		for (String i: permutations) {
			System.out.println(i);
		}
	}
	
	/**
	 * Swaps item at position x with item at position y in array arr
	 * @param arr	array to perform swapping on
	 * @param x		first position
	 * @param y		second position
	 */
	public static void swap(char[] arr, int x, int y) {
		char temp = arr[x];
		arr[x] = arr[y];
		arr[y] = temp;
	}
}