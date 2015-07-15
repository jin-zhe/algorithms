import java.util.Scanner;


public class RotateMatrix {
	int[][] matrix;
	
	RotateMatrix(int[][] arr){
		matrix = arr;
	}
	
	// rotate matrix clockwise by 90 deg
	void rotate(){
		// layer increments inwards
		for(int layer = 0; matrix.length - layer*2 >= 1; layer++){
			int length = matrix.length - layer*2; // length of current layer

			// swap top with right
			for (int i=layer; i<layer+length-1; i++){
				int temp = matrix[layer][i];			// store top
				matrix[layer][i] = matrix[i][layer+length-1];
				matrix[i][layer+length-1] = temp;
			}
			
			// swap left with top
			for (int i=layer+1; i<layer+length; i++){
				int temp = matrix[i][layer];			// store left
				matrix[i][layer] = matrix[layer][2*layer+length-1-i];
				matrix[layer][2*layer+length-1-i] = temp;
			}
			
			// swap bot with left
			for (int i=layer+1; i<layer+length; i++){
				int temp = matrix[layer+length-1][i];	// store bot
				matrix[layer+length-1][i] = matrix[i][layer];
				matrix[i][layer] = temp;
			}
		}
	}
	
	void run(){
		rotate();
		printMatrix();
	}
	
	// prints a snapshot of matrix
	void printMatrix(){
		for (int i=0; i<matrix.length; i++){
			for (int j=0; j<matrix.length; j++){
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int size = sc.nextInt();
		int[][] arr = new int[size][size];
		
		// read in array
		for (int i=0; i<size; i++)
			for (int j=0; j<size; j++)
				arr[i][j] = sc.nextInt();
		
		(new RotateMatrix(arr)).run();
		sc.close();
	}
}