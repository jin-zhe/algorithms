import java.util.HashSet;
import java.util.ArrayList;

// Given an array of integers, return all possible subsets of length n
public class SubsetsN {
	int[] arr = {2,3,5,7,4,3,22,4,6,7,889,4,3,2,4,6,7,8,4,34};
	Integer[] newArr;
	
	// remove duplicates in array O(N)
	void preprocess(){
		HashSet<Integer> map = new HashSet<Integer>();
		ArrayList<Integer> temp = new ArrayList<Integer>();
		
		for(int i=0; i<arr.length; i++){
			int curr = arr[i];
			if (map.contains(curr)) continue;
			else{
				temp.add(curr);
				map.add(curr);
			}
		}
		newArr = temp.toArray(new Integer[temp.size()]);
	}
	
	void getAllSubsets(int start, int length, ArrayList<Integer> list){
		// base case
		if (length == 0){
			System.out.println(list.toString());	// prints the subset
			return;
		}
		
		for (int i=start; i + (length-1) < newArr.length - length + 1 ; i++){
			ArrayList<Integer> newList = new ArrayList<Integer>(list);	// create new list for thread safety
			newList.add(newArr[i]);										// add current item
			getAllSubsets(i + 1, length - 1, newList); 					// recurse with current item added
		}
	}
	
	void run(){
		preprocess();
		getAllSubsets(0, 5, new ArrayList<Integer>());
	}
	
	public static void main(String[] args) {
		(new SubsetsN()).run();
	}

}
