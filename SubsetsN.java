import java.util.HashMap;
import java.util.Vector;

// Given an array of integers, return all possible subsets of length n
public class SubsetsN {
	int[] arr = {2,3,5,7,4,3,22,4,6,7,889,4,3,2,4,6,7,8,4,34};
	Integer[] newArr;
	
	// remove duplicates in array O(N)
	void preprocess(){
		HashMap<Integer,Boolean> map = new HashMap<Integer,Boolean>();
		Vector<Integer> temp = new Vector<Integer>();
		
		for(int i=0; i<arr.length; i++){
			int curr = arr[i];
			if (map.containsKey(curr)) continue;
			else{
				temp.add(curr);
				map.put(curr, true);
			}
		}
		newArr = temp.toArray(new Integer[temp.size()]);
	}
	
	// leaves: arr.length choose n. Worst case: n=arr.length/2
	void getAllSubsets(int start, int n, Vector<Integer> list){
		// base case
		if (n == 0){
			System.out.println(list.toString());
			return;
		}
		
		// pick the current one
		for (int i=start; i + (n-1) < newArr.length ; i++){
			Vector<Integer> newList = new Vector<Integer>(list);
			newList.add(newArr[i]);
			getAllSubsets(i + 1, n - 1, newList);
		}
	}
	
	void run(){
		preprocess();
		getAllSubsets(0, 5, new Vector<Integer>());
	}
	
	public static void main(String[] args) {
		(new SubsetsN()).run();
	}

}
