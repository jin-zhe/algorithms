import java.util.Arrays;
import java.util.HashMap;

/**
 * Given an array and a number n, can you obtain n using a sum of numbers in the array?
 */
public class ObtainableSum {

	public static void main(String[] args) {
		int[] arr = {10, 1, 44, 4, 9, 65, 2, 11, 3};
		int n = 6;
		System.out.println("Obtain 2 alternative: " + obtain2_alternative(n, arr));
		System.out.println("Obtain 2: " + obtain2(n, arr, 0));
		System.out.println("Obtain 3: " + obtain3(n, arr));
	}

	/**
	 * can n be obtained from exactly 3 items from arr? Returns the number triplet if yes, null otherwise. O(NlogN)
	 */	
	static Triplet<Integer> obtain3(int n, int[] arr){
		Arrays.sort(arr); // sorts the array
		for (int i=0; i<arr.length-2; i++){
			int one = arr[i];
			Pair<Integer> pair = obtain2(n - one, arr, i+1);
			/* if solution found */
			if (pair != null){
				int two = pair.one;
				int three = pair.two;
				return new Triplet<Integer>(one, two, three);
			}
		}
			
		return null;
	}
	
	/**
	 * can n be obtained from exactly 2 items from arr? Returns the number pair if yes, null otherwise. O(NlogN)
	 */
	static Pair<Integer> obtain2(int n, int[] arr, int startPos){
		Arrays.sort(arr); // sorts the array
		for (int i=startPos; i<arr.length-1; i++){
			int one = arr[i];
			/* for each number larger than one */
			for (int j=i+1; j<arr.length; j++){
				int two = arr[j];
				int sum = one + two;
				/* if sum exceeded, no point continuing */
				if (sum > n)
					break;
				/* else if answer is obtained */
				else if (sum == n)
					return new Pair<Integer>(one, two);
				/* else continue loop */
			}
		}
		return null;
	}
	
	/**
	 * Alternative O(N) implementation of obtain2
	 */
	static Pair<Integer> obtain2_alternative(int n, int[] arr){
		/* preprocess hashmap */
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i: arr){
			if (map.containsKey(i))
				map.put(i, map.get(i)+1);
			else
				map.put(i, 0);
		}
		/* iterates each number i array to test if n-i also exists */
		for (int i: arr){
			if (map.containsKey(n-i))
				if (i != n-i || (i==n-i && map.get(n-i)>1))
					return new Pair<Integer>(i, n-i);
		}
		
		return null;
	}
	
	/* helper classes */
	static class Triplet<T>{
		T one;
		T two;
		T three;
		public Triplet(T one, T two, T three){
			this.one = one;
			this.two = two;
			this.three = three;
		}
		public String toString(){
			return one.toString() + " + " + two.toString() + " + " + three.toString();
		}
	}
	static class Pair<T>{
		T one;
		T two;
		public Pair (T one, T two){
			this.one = one;
			this.two = two;
		}
		public String toString(){
			return one.toString() + " + " + two.toString();
		}
	}
}
