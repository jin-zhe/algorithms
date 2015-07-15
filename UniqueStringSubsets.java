import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;

/**
 *  Given a string write a function which prints all the subsets of the string. Now make the function to return only unique solutions
 */
public class UniqueStringSubsets {
	char[] arr;
	HashMap<String, Boolean> map;
	Vector<String> outputList;
	boolean mem[][];
	
	UniqueStringSubsets(String string){
		arr = string.toCharArray();
		map = new HashMap<String,Boolean>();
		mem = new boolean[arr.length + 1][arr.length + 1];
		outputList = new Vector<String>();
	}
	
	void printSubsets(int start, int end){
		if (mem[start][end] == true) return;
		// prints current subset if not already done so (ensure uniqueness)
		StringBuffer sb = new StringBuffer();
		sb.append(arr, start, end - start + 1);
		String curr = sb.toString();
		if (!map.containsKey(curr)){
			System.out.println(curr);
			map.put(curr, true);
		}
		
		// base case
		if (end - start == 0) return;
		
		printSubsets(start + 1, end);
		printSubsets(start, end - 1);
		mem[start][end] = true;
	}
	
	// prints all subsequences at the end of recursion O(2^N) solution
	void populateSubsequences(int curr, StringBuffer sb){
		// base case
		if (curr == arr.length){
			String output = sb.toString();
			// if not printed before, print
			if (!map.containsKey(output)){
				map.put(output, true);
				outputList.add(output);
			}
			// else do nothing
			return;	// terminate recursion
		}
		
		// do not include current character
		populateSubsequences(curr + 1, sb);
		
		// include current character
		StringBuffer includeCurrent = new StringBuffer(sb);
		includeCurrent.append(arr[curr]);
		populateSubsequences(curr + 1, includeCurrent);
	}
	
	void run(){
		System.out.println("Substrings:");
		printSubsets(0, arr.length - 1);
		System.out.println("Subsequences:");
		map = new HashMap<String, Boolean>();
		populateSubsequences(0, new StringBuffer());
		// sort outputs
		Collections.sort(outputList);
		for(String i: outputList) System.out.println(i);
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		UniqueStringSubsets uss = new UniqueStringSubsets(sc.next());
		uss.run();
		sc.close();
	}

}
