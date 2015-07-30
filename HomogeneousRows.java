import java.util.HashMap;
import java.util.Scanner;
/**
Problem Description:
You are given an M by N matrix containing only the characters 'P' and 'T'.
You can flip the characters in any number of columns. I.e. 'P' becomes 'T'
and 'T' becomes 'P' for all characters under that column. Your aim is to
maximize the number of rows for which all the characters are the same. We
shall refer to such rows as being homogeneous. 

Given the M by N matrix as input, write a program that outputs a single
number representing the maximum number of homogeneous rows achievable by
flipping any number of columns. 

Input Format:
The first line contains 2 space-separated integers M and N, denoting the
number of rows and the number of columns. Then M lines follow with each line
containing N characters ( not separated by any space ) 'P' or 'T'.

Output Format:
A single number representing the maximum number of homogeneous rows
achievable
 
Constraints
1<=M<=100000
1<=N<=500
 
Sample Input
5 3
TTP
PTP
TPP
PTP
TPT
 
Sample Output
3
 
Explanation
Flipping column 2 yields:
TPP
PPP
TTP
PPP
TTT
Giving us 3 homogeneous rows. If we try to get the first row to match
instead, we have to either flip columns 1 and 2 or only column 3, and this
gives only 1 homogeneous row. If we want to get row 3 to match, we'd have to
flip either column 1 or columns 2 and 3, again achieving only 1 homogeneous
row.
*/

/*
 * Main idea behind algorithm:
 * 	Since we would like to find out the maximum number of rows which will be
 * 	homogeneous after flipping columns, it suffices to chuck all row complements
 * 	into the same bucket (e.g. 'PTP' and 'TPT') and then at the end, find out
 * 	which bucket has the most number of items. We can do this because when we
 * 	homogenize a row, we also homogenize its complement. e.g. when we turn 'PTP'
 * 	to 'PPP', we also turn the 'TPT' to 'TTT'
 */
public class HomogeneousRows {
	HashMap<String,Integer> complementsMap;	// Key: row string, Value: number of rows with the same string or complements of the string
	int M,N;
	String[] rows;
	int maxCount;
	
	/**
	 * Class constructor
	 * @param M		number of rows
	 * @param N		number of columns
	 * @param rows	array rows
	 */
	HomogeneousRows(int M, int N, String[] rows){
		this.M = M;
		this.N = N;
		this.rows = rows;
		complementsMap = new HashMap<String, Integer>();
	}
	
	/**
	 * Main algorithm
	 * O(MN) time complexity
	 */
	public void solve(){
		/* Iterate each row in matrix (M times) */
		for (String row: rows){
			int count = 1;	// number of occurrence for current normalized row
			
			/* 1. Normalize current row (we arbitrarily define a row to be normalized if it begins with 'P') */
			String normalizedRow = row;
			if (row.charAt(0) != 'P')
				normalizedRow = getComplement(normalizedRow);	// take complement of row if it is not normalized
			
			/* 2. Update hashmap for complements */
			if (complementsMap.containsKey(normalizedRow))
				count = complementsMap.get(normalizedRow) + 1;
			complementsMap.put(normalizedRow, count);
			
			/* 3. Update maxCount */
			if (count > maxCount)
				maxCount = count;
		}
		System.out.println(maxCount);	// print answer
	}
	
	/**
	 * @param row
	 * @return complement of given row string (used for row normalizations)
	 * O(N) time complexity
	 */
	public String getComplement(String row){
		StringBuffer sb = new StringBuffer();
		/* Flip each character in the given row */
		for (int i=0; i<N; i++){
			char currentChar = row.charAt(i);
			if (currentChar == 'P') sb.append('T');
			else sb.append('P');
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		/* Read inputs */
		Scanner sc = new Scanner(System.in);
		int M = sc.nextInt();			// rows of matrix
		int N = sc.nextInt();			// columns of matrix
		sc.nextLine();
		String[] rows = new String[M];	// list of rows as strings 
		for (int i=0; i<M; i++)			// populate rows[]
			rows[i] = sc.nextLine();
		sc.close();
		
		/* Run main algorithm */
		(new HomogeneousRows(M,N,rows)).solve();
	}
}