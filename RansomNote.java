/**
 * URL: http://www.careercup.com/question?id=67086
 * A kidnaper wishes to write a ransom note using letters from a magazine
 * article. You are given with the ransom note and magazine article find whether
 * kidnaper can write a note using the article or not.
 *
 */
public class RansomNote {
	
	/**
	 * Checks to see if ransom note can be created using the given article
	 * O(M*N) time complexity
	 * @param article	magazine article to create the ransom note with
	 * @param note		ransom note to be created
	 * @return
	 */
	public static boolean isRansomNoteValid(String article, String note) {
		int[] map = new int[128];	// O(1) space
		int i = 0; 					// current search index in article
		for (char c: note.toCharArray()) {
			/* if map contains character */
			if (map[(int) c] != 0) { map[(int) c]--; }
			/* else proceed searching in article until character is found */
			else {
				for (int j=i; j<article.length(); j++) {
					map[(int) article.charAt(i)]++;	// increment count
					i++;
					if (article.charAt(j) == c) { break; }
				}
				if (map[(int) c] != 0) { map[(int) c]--; }
				/* else if article exhausted without finding character */
				else { return false; }
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		String article = "Lorem ipsum dolor sit amet, consectetur adipiscing eli"
				+ "t, sed do eiusmod tempor incididunt ut labore et dolore magna"
				+ " aliqua. Ut enim ad minim veniam, quis nostrud exercitation u"
				+ "llamco laboris nisi ut aliquip ex ea commodo consequat. Duis "
				+ "aute irure dolor in reprehenderit in voluptate velit esse cil"
				+ "lum dolore eu fugiat nulla pariatur. Excepteur sint occaecat "
				+ "cupidatat non proident, sunt in culpa qui officia deserunt mo"
				+ "llit anim id est laborum.";
		
		String note = "i am still in office";
		System.out.println(isRansomNoteValid(article, note));	// true
		note = "I will kill you!";
		System.out.println(isRansomNoteValid(article, note));	// false
	}
}
