import java.util.Scanner;

/**
 * Taken from wikipedia: https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life
 * "
 * The universe of the Game of Life is an infinite two-dimensional orthogonal
 * grid of square cells, each of which is in one of two possible states, alive
 * or dead. Every cell interacts with its eight neighbours, which are the cells
 * that are horizontally, vertically, or diagonally adjacent. At each step in
 * time, the following transitions occur:
 * 
 * 1. Any live cell with fewer than two live neighbours dies, as if caused by
 * 	  under-population.
 * 2. Any live cell with two or three live neighbours lives on to the next
 * 	  generation.
 * 3. Any live cell with more than three live neighbours dies, as if by
 * 	  overcrowding.
 * 4. Any dead cell with exactly three live neighbours becomes a live cell, as
 * 	  if by reproduction.
 * 5. The initial pattern constitutes the seed of the system. The first
 * 	  generation is created by applying the above rules simultaneously to every
 * 	  cell in the seed—births and deaths occur simultaneously, and the discrete
 * 	  moment at which this happens is sometimes called a tick (in other words,
 * 	  each generation is a pure function of the preceding one). The rules
 * 	  continue to be applied repeatedly to create further generations.
 * "
 * 
 * Task:
 * Given an input of a M row x N col matrix of cells containing either 1 or 0,
 * output the next state for the matrix.
 * 
 * Input:
 * M N
 * <M x N matrix>
 * 
 * Output:
 * <M x N matrix>
 * 
 * e.g.
 * Input:
 * 3 3
 * 0 0 0
 * 1 1 1
 * 0 0 0
 * 
 * Output:
 * 0 1 0
 * 0 1 0
 * 0 1 0
 */
public class GameOfLife {

	public static void main(String[] args) {
		/* read in values */
		Scanner sc = new Scanner(System.in);
		int M = sc.nextInt();
		int N = sc.nextInt();
		int[][] arr = new int[M][N];
		for (int i=0; i<M*N; i++){
			arr[i/N][i%M] = sc.nextInt();
		}
		sc.close();
		
		int[][] nextState = getNextState(arr);
		printMatrix(nextState);
	}
	
	/**
	 * Determines the next state for the given state
	 * @param arr 	matrix of the current state
	 * @return		matrix for the next state
	 */
	static int[][] getNextState(int[][] arr){
		int M = arr.length;
		int N = arr[0].length;
		int[][] nextState = new int[M][N];
		for (int i=0; i<M; i++){
			for (int j=0; j<N; j++){
				nextState[i][j] = getNextVal(arr, i, j);
			}
		}
		return nextState;
	}
	
	/**
	 * Determine the value of given cell for the next state
	 * @param arr	matrix for the current state
	 * @param i		row co-ordinate
	 * @param j		col co-ordinate
	 * @return		1 if cell alive in next state, 0 otherwise
	 */
	static int getNextVal(int[][] arr, int i, int j){
		boolean isLiving = (arr[i][j]==1); // if current cell is living
		int neighborCount = countLivingNeighbors(arr, i, j);
		if (isLiving) {
			return (neighborCount==2 || neighborCount==3)? 1: 0;
		}
		else {
			return (neighborCount==3)? 1: 0;
		}
	}
	
	/**
	 * Counts the number of living neighbors surrounding current cell
	 * @param arr	matrix for current state
	 * @param i		row co-ordinate
	 * @param j		col co-ordinate
	 * @return		the number of living neighbors around current cell
	 */
	static int countLivingNeighbors(int[][] arr, int i, int j){
		int count = 0;
		int M = arr.length;
		int N = arr[0].length;
		for (int addI=-1; addI<2; addI++){
			for (int addJ=-1; addJ<2; addJ++){
				if (!(addI==0 && addJ==0) &&	// if not cell itself AND
					(i+addI>=0 && i+addI<M) &&	// within row range AND
					(j+addJ>=0 && j+addJ<N) &&	// within col range AND
					(arr[i+addI][j+addJ]==1)){	// is living cell
						count++;
				}
			}
		}
		return count;
	}
	
	/**
	 * Prints the given array to console
	 * @param arr	matrix to be printed
	 */
	static void printMatrix(int[][] arr){
		int M = arr.length;
		int N = arr[0].length;
		for (int i=0; i<M*N; i++) {
			if (i%N==0) {
				System.out.println();
			}
			System.out.print(arr[i/N][i%M] + " ");
		}
	}
}
