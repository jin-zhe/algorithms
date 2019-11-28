import java.util.Scanner;

/**
 * URL: http://www.geeksforgeeks.org/reverse-words-in-a-given-string/
 * Problem Description:
 * Reverse the order of words in a sentence, but keep words themselves
 * unchanged. Words in a sentence are divided by blanks. For instance, the
 * reversed output should be “student. a am I” when the input is “I am a student."
 */
public class ReverseWords {
	/**
	 * Reverses all the words in the given sentence
	 * @param str	the given string
	 * @return
	 */
	public static String reverseWords(String str) {
		/* Step 1: trimming */
		String sentence = str.trim();
		sentence = sentence.replace("\\s+", " ");
		
		char[] charArr = sentence.toCharArray();
		
		/* Step 2: reverse all the characters */
		reverse(charArr, 0, charArr.length);
		
		/* Step 3: reverse all the word tokens */
		int start = 0; 	// start index of current word
		for (int i=1; i<charArr.length; i++) {
			char curr = charArr[i];
			if (isDelimiter(curr)) {
				reverse(charArr, start, i);
				/* update start */
				start = i + 1;
				/* if there are other delimiters than spaces */
//				while (start < charArr.length && isDelimiter(charArr[start])) {
//					start++;
//				}
			}
		}
		/* Step 4: perform final word reversal if applicable */
		if (start < charArr.length) {
			reverse(charArr, start, charArr.length);
		}
		return new String(charArr);
	}
	
	/**
	 * Reverses the given character sequence of array in-place. The sequence to
	 * be reversed is defined by [start, end)
	 * @param charArr	character array
	 * @param start		start inclusive
	 * @param end		end exclusive
	 */
	public static void reverse(char[] charArr, int start, int end) {
		int i = start;
		int j = end - 1;
		while (i < j) {
			swap(charArr, i, j);
			i++;
			j--;
		}
	}
	
	/**
	 * Checks if given character is a token delimiter
	 * @param c		character to test
	 * @return		true if character is a delimiter, false otherwise
	 */
	public static boolean isDelimiter(char c) {
		return (c == ' ');
//		return (c == ' ' || c == '.' || c == ',');
	}
	
	/**
	 * Swaps in-place the item at position i with item at position j
	 * @param charArr	character array
	 * @param i			index 1
	 * @param j			index 2
	 */
	public static void swap(char[] charArr, int i, int j) {
		char temp = charArr[i];
		charArr[i] = charArr[j];
		charArr[j] = temp;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		System.out.println(reverseWords(str));
		sc.close();
	}
}