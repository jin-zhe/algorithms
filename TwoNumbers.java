import java.util.HashMap;

// given an array of numbers and a number k, find if two numbers in the array add up to k
public class TwoNumbers {
	int[] arr;
	HashMap<Integer,Integer> map;
	
	TwoNumbers(int[] arr){
		this.arr = arr;
		map = new HashMap<Integer, Integer>();
		
		// preprocess: iterate through hashmap
		for(int i: arr){
			if (!map.containsKey(i)) map.put(i, 1);
			else map.put(i, map.get(i)+1);
		}
	}
	
	// O(N)
	boolean solve(int k){
		for (int i: arr){
			int check = k - i;
			if (map.containsKey(check)){
				if (check != i || ((check != i) && (map.get(check) > 1))) return true;
			}
		}
		return false;
	}
	
	void run(int k){
		System.out.println(solve(k));
	}
	
	public static void main(String[] args) {
		int[] arr = {1,2,3,4,5,6,7,89,2,3,66,43,23};
		(new TwoNumbers(arr)).run(43);
	}
}
