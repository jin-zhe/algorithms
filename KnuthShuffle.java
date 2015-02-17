import java.util.Random;

public class KnuthShuffle {
	public static void main(String[] args){
		int[] arr = {1,2,3,4,5,6,7,8,9,10};
		Random gen = new Random();
		
		int n = arr.length;
		while(n > 1){
			// swap k with n
			int k = gen.nextInt(n--);
			int temp = arr[n];
			arr[n] = arr[k];
			arr[k] = temp;
		}
		
		for(int i=0; i<arr.length; i++) System.out.print(arr[i] + " ");
	}
}
