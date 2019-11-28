import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;


public class Anagrams {
	String first;
	String second;
	Anagrams(String first, String second){
		this.first = first;
		this.second = second;
	}
	
	/**
	 * Given a list of strings, distribute them into anagram buckets
	 * @param list	list of strings to be sorted into buckets
	 */
	public void sortAnagrams(ArrayList<String> list) {
		HashMap<String, ArrayList<String>> bucketList =
				new HashMap<String, ArrayList<String>>();
		
		/* store each string into their anagram list */
		for (String string: list) {
			char[] charArr = string.toCharArray();
			Arrays.sort(charArr);	// N(logN)
			String sortedString = new String(charArr);
			
			if (!bucketList.containsKey(sortedString)) {
				bucketList.put(sortedString, new ArrayList<String>());
			}
			bucketList.get(sortedString).add(string);
		}
	}
	
	/**
	 * Compares 2 strings if they are anagrams of each other without using sort
	 * O(N)
	 * @param stringA	first string
	 * @param stringB	second string
	 * @returns			true if both strings are anagrams of each other,
	 * 					false otherwise
	 */
	boolean isAnagram(String stringA, String stringB) {
		if (stringA.length() != stringB.length()) return false;
		HashMap<Character, Integer> charMap = new HashMap<Character, Integer>();
		
		/* iterate through each character */
		for (int i=0; i<stringA.length(); i++) {
			char charA = stringA.charAt(i);
			char charB = stringB.charAt(i);
			
			/* increment charA in map by 1 */
			if (charMap.containsKey(charA)) {
				int occurence = charMap.get(charA);
				charMap.put(charA, occurence + 1);
			}
			else {
				charMap.put(charA, 1);
			}
			
			/* decrement charB in map by 1 */
			if (charMap.containsKey(charB)) {
				int occurence = charMap.get(charB);
				charMap.put(charB, occurence - 1);
			}
			else {
				charMap.put(charB, -1);
			}
		}
		
		/* searches map to find differences in character occurence */
		for (HashMap.Entry<Character, Integer> entry: charMap.entrySet()) {
			if (entry.getValue() != 0) {
				return false;
			}
		}
		
		return true;
	}
	
	void run() {
		System.out.println(isAnagram(first, second));
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		(new Anagrams(sc.next(), sc.next())).run();
		sc.close();
	}
}
