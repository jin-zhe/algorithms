// reverse an array in place
public class ReverseArray {

	public static void main(String[] args) {
		int[] arr = {1,2,3,4,5,6,7,8,9,10};
		
		int i=0;
		int j=arr.length-1;
		
		while(j>i){
			// swap arr[i] with arr[j]
			int temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
			
			// update indexes
			i++;
			j--;
		}
		
		for (int k: arr)
			System.out.print(k + " ");
	}
}
