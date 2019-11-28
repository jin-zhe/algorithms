import java.util.Scanner;
/**
 * URL: http://www.careercup.com/question?id=7449675
 * Problem Description:
 * Compress a given string "aabbbccc" to "a2b3c3"
 * constraint: in-place compression, no extra space to be used
 * assumption : output size will not exceed input size..
 * ex input:"abb" -> "a1b2" buffer overflow.. such inputs will not be given.
 */
public class StringCompression {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println(compress(sc.nextLine().toCharArray()));
		sc.close();
	}
	
	/**
	 * Compresses a character array
	 * @param str	character array to be compressed
	 * @return		character array representing the compressed str
	 */
	public static String compress(char[] str) {
		if (str.length <= 1) {
			return new String(str);
		}
		char prevChar = str[0];	// character to keep count of
		int count = 1;			// count of prevChar encountered
		int nxtPos = 1;			// next position to write
		for (int i=1; i<str.length; i++) {
			char curr = str[i];
			if (curr == prevChar) {
				count++;
			}
			/* if new character encountered, compress previous sequence */
			else {
				nxtPos = writeCount(str, count, nxtPos);	// write compression
				str[nxtPos++] = curr; 	// write new character and update nxtPos
				prevChar = curr;	 	// update new character to keep count of
				count = 1;				// reset count
			}
		}
		/* handle final character compression */
		if (count > 1) {
			nxtPos = writeCount(str, count, nxtPos);
		}
		return new String(str, 0, nxtPos);
	}
	
	/**
	 * Writes the value of count to the array at current position for writing
	 * and returns the next position for writing 
	 * @param str		character array
	 * @param count		the character representation of count to be written
	 * @param start		the starting position in str to write at
	 * @return			the next available position for writing
	 */
	public static int writeCount(char[] str, int count, int start) {
		int places = getPlaces(count);
		int pos = places;
		while(pos-- > 0) {
			str[start+pos] = Character.forDigit(count%10, 10);
			count /= 10;
		}
		return start + places;
	}
	
	/**
	 * Gets the number of decimal digit places that count needs
	 * @param count		number of interest
	 * @return 			number of decimal positions that count requires
	 */
	public static int getPlaces(int count) {
		int places = 0;
		while (count > 0) {
			places++;
			count /= 10;
		}
		return places;
	}
}