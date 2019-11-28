import java.util.HashSet;

/**
 * URL: http://thenoisychannel.com/2011/08/08/retiring-a-great-interview-problem
 * Problem description:
 * Given an input string and a dictionary of words, segment the input string
 * into a space-separated sequence of dictionary words if possible. For example,
 * if the input string is "applepie" and dictionary contains a standard set of
 * English words, then we would return the string "apple pie" as output.
 */
public class WordBreak {
	HashSet<String> dictionary;
	
	public WordBreak (String[] words) {
		dictionary = new HashSet<String>();
		for (String word: words) { dictionary.add(word); }
	}
	
	/**
	 * Prints out all possible word breaks for given string
	 * @param str	string of interest
	 */
	public void printPossibleSentences(String str) {
		printPossibleSentences("", str, 0);
	}
	/**
	 * Recursive method
	 * @param sentence	the sentence of words formed thus far
	 * @param str		the original string
	 * @param start		the starting index of current substring to examine
	 */
	public void printPossibleSentences(String sentence, String str, int start) {
		if (start == str.length()) { System.out.println(sentence); }
		for (int end=start+1; end<=str.length(); end++) {
			String substr = str.substring(start, end);
			if (isWord(substr)) {
				printPossibleSentences(sentence + substr + " ", str, end); 
			}
		}
	}
	
	/**
	 * Get a valid instance of sentence given the input string
	 * O(N^2)
	 * @param str	string of interest
	 * @return		a valid instance of sentence of words
	 */
	public String getValidInstance(String str) {
		String[] mem = new String[str.length()];
		/* pre-process. Note: we cannot use null as that is a valid memorization */
		for (int i=0; i<str.length(); i++) { mem[i] = ""; }
		return getValidInstance(str, mem, 0);
	}
	/**
	 * Dynamic Programming
	 * @param str		the original string
	 * @param mem		memorization
	 * @param start		the starting index of current substring to examine
	 * @return			a valid sentence instance of substring or null otherwise
	 */
	public String getValidInstance(String str, String[] mem, int start) {
		int len = str.length();
		/* base case */
		if (start == len) { return ""; }
		/* if memorized, return */
		if (mem[start] == null || !mem[start].equals("")) { return mem[start]; }
		/* return first valid instance found */
		for (int i=start+1; i<=len; i++) {
			String prefix = str.substring(start, i);
			if (isWord(prefix)) {
				String remaining = getValidInstance(str, mem, i);
				/* return first valid instance found */
				if (remaining != null) {
					mem[start] = prefix + " " + remaining;
					return mem[start];
				}
			}
		}
		return null; // if substring beginning at start cannot be resolved
	}
	
	
	/**
	 * Checks if given string is a word in the dictionary
	 * @param str	given string
	 * @return		true if string is in dictionary, else false
	 */
	public boolean isWord(String str) {
		return dictionary.contains(str);
	}
	
	public static void main(String[] args) {
		String[] words = {"mobile", "samsung", "sam", "sung", "man", "mango",
                		  "icecream", "and", "go", "i", "like", "ice", "cream"};
		WordBreak wb = new WordBreak(words);
		System.out.println("One valid instance: ");
		System.out.println(wb.getValidInstance("ilikeicecreamandmango"));
		System.out.println("All valid instances: ");
		wb.printPossibleSentences("ilikeicecreamandmango");;
	}
}