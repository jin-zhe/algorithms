import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;


public class Anagrams {
	String first;
	String second;
	Anagrams(String first, String second){
		this.first = first;
		this.second = second;
	}
	
	/** Given a list of strings, create a list of buckets with all anagrams in each*/
	void sortAnagrams(Vector<String> list){
		HashMap<String, Vector<String>> bucketList = new HashMap<String, Vector<String>>();
		
		// store each string into their anagram list
		for (String string: list){
			char[] charArr = string.toCharArray();
			Arrays.sort(charArr);	// N(logN)
			String sortedString = new String(charArr);
			
			if (!bucketList.containsKey(sortedString)) bucketList.put(sortedString, new Vector<String>());
			bucketList.get(sortedString).add(string);
		}
	}
	
	/** compares 2 strings if they are anagrams of each other O(N) */
	boolean isAnagram(String stringA, String stringB){
		if (stringA.length() != stringB.length()) return false;
		HashMap<Character, Integer> charMap = new HashMap<Character, Integer>();
		
		// iterate through each character
		for (int i=0; i<stringA.length(); i++){
			char charA = stringA.charAt(i);
			char charB = stringB.charAt(i);
			
			// increment charA in map by 1
			if (charMap.containsKey(charA)){
				int occurence = charMap.get(charA);
				charMap.put(charA, occurence + 1);
			}
			else charMap.put(charA, 1);
			
			// decrement charB in map by 1
			if (charMap.containsKey(charB)){
				int occurence = charMap.get(charB);
				charMap.put(charB, occurence - 1);
			}
			else charMap.put(charB, -1);
		}
		
		// searches map to find differences in character occurence
		for (HashMap.Entry<Character, Integer> entry: charMap.entrySet())
			if (entry.getValue() != 0) return false;
		
		return true;
	}
	
	void run(){
		System.out.println(isAnagram(first, second));
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		(new Anagrams(sc.next(), sc.next())).run();
		sc.close();
	}
}
