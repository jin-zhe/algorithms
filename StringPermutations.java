import java.util.ArrayList;
import java.util.Scanner;


public class StringPermutations {
	String string;
	StringPermutations(String str){
		string = str;
	}
	
	// recursively solve for permutations of substrings
	public ArrayList<StringBuffer> permutate(String str){
		ArrayList<StringBuffer> permutations = new ArrayList<StringBuffer>();
		// base case
		if (str.length() == 1) permutations.add(new StringBuffer(str));
		
		else if (str.length() > 1){
			char lastChar = str.charAt(str.length() - 1);
			ArrayList<StringBuffer> subPermutations = permutate(str.substring(0, str.length() - 1));
			
			// for each permutation of substring, insert lastChar at every possible locations
			for (StringBuffer i: subPermutations)
				for (int j=0; j<=i.length(); j++){	// Note: <=i.length() because we want to insert behind too
					StringBuffer aPermutation = new StringBuffer(i);
					permutations.add(aPermutation.insert(j, lastChar));
				}
		}
		return permutations;
	}
	public void run(){
		ArrayList<StringBuffer> outputsList = permutate(string);
		for(StringBuffer i: outputsList) System.out.println(i.toString());
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		(new StringPermutations(sc.nextLine())).run();
		sc.close();

	}
}