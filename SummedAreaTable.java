/**
 * Implement Summed Area Table of a given matrix.
 * URL: http://stackoverflow.com/questions/2277749/calculate-the-sum-of-elements-in-a-matrix-efficiently
 * 
 * Task:
 * Given an n*m matrix, for each cell, calculate the sum of values in its
 * implicit sub-matrix where the top-left coordinate is (0, 0) and bottom right
 * corner is the cell itself. Can you do it in-place?
 * Example:
 * 
 * Input:
 * 3  1  2  1
 * 2  1  1  1
 * 3  1  2  1
 * 
 * Output:
 * 3  4  6  7 
 * 5  7  10 12 
 * 8  11 16 19 
 */
public class SummedAreaTable {
	/**
	 * Get the summed area table for given matrix
	 * O(MN) time, O(MN) space
	 * @param matrix	source matrix
	 * @return		summed area table
	 */
	public static int[][] getSummedAreaTable(int[][] matrix) {
		int rowSize = matrix.length;
		int colSize = matrix[0].length;
		int[][] cumulativeMatrix = new int[rowSize][colSize];
		for (int i=0; i<rowSize; i++) {
			for (int j=0; j<colSize; j++) {
				cumulativeMatrix[i][j] = getSubmatrixSum(i, j, matrix);
			}
		}
		return cumulativeMatrix;
	}

	/**
	 * Get the sum of all cells in sub-matrix defined by bottom right coordinates
	 * @param row		row number for bottom right coordinate
	 * @param col		column number for bottom right coordinate
	 * @param matrix	parent matrix
	 * @return		sub-matrix sum
	 */
	public static int getSubmatrixSum(int row, int col, int[][] matrix) {
		int sum = 0;
		for (int i=0; i<=row; i++) {
			for (int j=0; j<=col; j++) {
				sum += matrix[i][j];
			}
		}
		return sum;
	}

	/**
	 * Process given matrix into its summed area table (in-place)
	 * O(MN) time, O(1) space
	 * @param matrix	source matrix
	 */
	public static void processSummedAreaTable(int[][] matrix) {
		int rowSize = matrix.length;
		int colSize = matrix[0].length;
		for (int i=0; i<rowSize; i++) {
			for (int j=0; j<colSize; j++) {
				matrix[i][j] = getVal(i, j, matrix);
			}
		}
	}
	/**
	 * Helper method for processSummedAreaTable
	 * @param row		current row number
	 * @param col		current column number
	 * @param matrix	source matrix
	 * @return		sub-matrix sum
	 */
	public static int getVal (int row, int col, int[][] matrix) {
		int leftSum;					// sub matrix sum of left matrix
		int topSum;						// sub matrix sum of top matrix 
		int topLeftSum;					// sub matrix sum of top left matrix
		int curr = matrix[row][col];	// current cell value
		/* top left value is itself */
		if (row == 0 && col == 0) {
			return curr;
		}
		/* top row */
		else if (row == 0 && col != 0) {
			leftSum = matrix[row][col - 1];
			return curr + leftSum;
		}
		/* left-most column */
		if (row !=0 && col == 0) {
			topSum = matrix[row - 1][col];
			return curr + topSum;
		}
		else {
			leftSum = matrix[row][col - 1];
			topSum = matrix[row - 1][col];
			topLeftSum = matrix[row - 1][col - 1]; // overlap between leftSum and topSum
			return curr + leftSum + topSum - topLeftSum;
		}
	}

	/**
	 * Prints matrix row-wise
	 * @param matrix
	 */
	public static void printMatrix(int[][] matrix) {
		int rowSize = matrix.length;
		int colSize = matrix[0].length;
		for (int i=0; i<rowSize; i++) {
			for (int j=0; j<colSize; j++) {
				System.out.print(matrix[i][j]+ " ");
			}
			System.out.println();
		}
	}

	public static void main (String[] args) {
		int[][] image = {{3, 1, 2, 1},
				{2, 1, 1, 1},
				{3, 1, 2, 1}};
		System.out.println("Non-in-place implementation:");
		printMatrix(getSummedAreaTable(image));
		System.out.println("In-place implementation:");
		processSummedAreaTable(image);
		printMatrix(image);
	}
}