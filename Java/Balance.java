import java.util.*;
/**
URL: http://www.careercup.com/question?id=12150672
Problem Description:
You have a room-full of balances and weights. Each balance weighs ten pounds and
is considered perfectly balanced when the sum of weights on its left and right
sides are exactly the same. You have placed some weights on some of the
balances, and you have placed some of the balances on other balances. Given a
description of how the balances are arranged and how much additional weight is
on each balance, determine how to add weight to the balances so that they are
all perfectly balanced. 

There may be more than one way to balance everything, but always choose the way
that places additional weight on the lowest balances. 

The input file will begin with a single integer, N, specifying how many balances
there are. Balance 0 is specified by lines 1 and 2, balance 1 is specified by
lines 3 and 4, etc... Each pair of lines is formatted as follows: 

WL <balances> 
WR <balances> 

WL and WR indicate the weight added to the left and right sides, respectively. 
<balances> is a space-delimited list of the other balance that are on that side
of this balance. <balances> may contain zero or more elements. 

Consider the following input: 
4 
0 1 
0 2 
0 
0 3 
3 
0 
0 
0 

Balance 0 has balance 1 on its left side and balance 2 on its right side 
Balance 1 has balance 3 on its right side 
Balance 2 has three pounds on its left side 
Balance 3 has nothing on it 

Since balance 3 has nothing on it it is already perfectly balanced, and weighs a
total of 10 pounds. Balance 2 has no other balance on it, so all we need to do
is balance it by putting three pounds on its right side. Now it weighs a total
of 16 pounds. Balance 1 has balance three on its right side, which weighs 10
pounds, so we just put 10 pounds on its left side. Balance 1 weighs a total of
30 pounds. Balance 0 has balance 1 on its left side (30 pounds), and balance 2
on its right side (16 pounds), we can balance it by adding 14 pounds to the
right side. 

The output should be N lines long, with the nth line listing the weight added to
the nth balance, formatted as follows: 
<index>: <weight added to left side> <weight added to right side> 

So the output for this problem would be: 
0: 0 14 
1: 10 0 
2: 0 3 
3: 0 0
 */
public class Balance {
	Node[] balancesMap;	// map of balances, array index -> id of balance
	String[] output;	// output String where index corresponds to balance
	
	/**
	 * recursively get the total balanced weight of a node (inclusive of itself)
	 * O(N) since all nodes are visited once
	 * @param aNode		the node of interest
	 * @return			the total balanced weight
	 */
	public int getBalancedWeight(Node aNode) {
		if (!aNode._isBalance) {
			return aNode._weight;	// if node is just a weight, return weight
		}
		
		/* total balanced weight of left children */
		int totalLeftWeight = 0;
		for (Node leftChild: aNode._left) {
			totalLeftWeight += getBalancedWeight(leftChild);
		}
		
		/* total balanced weight of left children */
		int totalRightWeight = 0;
		for (Node rightChild: aNode._right) {
			totalRightWeight += getBalancedWeight(rightChild);
		}
		
		/* formulate output string for current balance */
		int diff = difference(totalLeftWeight, totalRightWeight);
		/* if left children are heavier than right */
		if (totalLeftWeight > totalRightWeight)	{
			output[aNode._index] = aNode._index + ": 0 " + diff;
		}
		/* if right children are heavier than left */
		else if (totalLeftWeight < totalRightWeight) {
			output[aNode._index] = aNode._index + ": " + diff + " 0";
		}
		/* else if equal */
		else {
			output[aNode._index] = aNode._index + ": 0 0";
		}
		
		return (aNode._weight + totalLeftWeight + totalRightWeight + diff);
	}
	
	/**
	 * Helper method: returns the absolute difference between two numbers
	 * @param a		first number
	 * @param b		second number
	 * @return		absolute difference between a and b
	 */
	public int difference(int a, int b) {
		return Math.abs(a - b);
	}
	
	/**
	 * Driver function
	 * O(N)
	 */
	void run() {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		balancesMap = new Node[N];
		output = new String[N];
		sc.nextLine();
		
		/* read from inputs and populate data structure balancesMap
		 * Time Complexity: O(N)
		 */
		for (int i=0; i<2*N; i+=2) {
			int index = i/2;	// index of current balance
			Node balance;		// current balance
			/* if balance is already init, reference from map */
			if (balancesMap[index]!=null) {
				balance = balancesMap[index];
			}
			/* else init and reference */
			else {
				balance = new Node(true, index);
				balancesMap[index] = balance;	// store in balancesMap
			}
			String firstInput = sc.nextLine();
			String secondInput = sc.nextLine();
			
			/* tokenize inputs by space */
			String[] leftChildInfo = firstInput.split(" ");
			String[] rightChildInfo = secondInput.split(" ");
			
			/* for all the children on the left */
			for (int j=0; j<leftChildInfo.length; j++) {
				int value = Integer.parseInt(leftChildInfo[j]);
				/* if weight column */
				if (j==0) {
					/* if weight is non zero, add weight to left child list */
					if (value!=0) {
						balance._left.add(new Node(false, value));
					}
					/* else do nothing */
				}
				/* if balance columns */
				else {
					/* add balance to left child list */
					Node childBalance;
					if (balancesMap[value]!=null) {
						childBalance = balancesMap[value];
					}
					else {
						childBalance = new Node(true, value);
						balancesMap[value] = childBalance;
					}
					balance._left.add(childBalance);
					
					/* point parent of childBalance to balance */
					childBalance._parent = balance;
				}
			}
			
			/* for all the children on the right */
			for (int j=0; j<rightChildInfo.length; j++) {
				int value = Integer.parseInt(rightChildInfo[j]);
				/* if weight column */
				if (j==0) {
					/* if weight is non zero, add weight to right child list */
					if (value!=0) {
						balance._right.add(new Node(false, value));
					}
					/* else do nothing */
				}
				/* if balance columns */
				else {
					/* add balance to right child list */
					Node childBalance;
					if (balancesMap[value]!=null) {
						childBalance = balancesMap[value];
					}
					else{
						childBalance = new Node(true, value);
						balancesMap[value] = childBalance;
					}
					
					balance._right.add(childBalance);
					
					/* point parent of childBalance to balance */
					childBalance._parent = balance;
				}
			}
		}// END: read inputs
		sc.close();
		
		/* Input 2 suggests that there could be more than 1 root node ==> disjoint sets
		 * find the balances without any parents (root nodes)
		 * Time Complexity: O(N)
		 */
		Vector<Node> rootBalances = new Vector<Node>();
		for (Node i: balancesMap) {
			// if balance has no parents, then it is the root node
			if (i._parent == null) {
				rootBalances.add(i);
			}
		}
		
		/* runs recursive function
		 * Time Complexity: O(N)
		 */ 
		for (Node i: rootBalances) {
			getBalancedWeight(i);
		}
		
		/* prints output in order
		 * Time Complexity: O(N)
		 */
		for (String i: output) {
			System.out.println(i);
		}
		
		/* Total Time complexity: O(N) + O(N) + O(N) + O(N) = O(N)*/
	}// END: run()
	
	public static void main(String[] args){
		Balance sol = new Balance();
		sol.run();
	}
	
	/**
	 *  Node class: Nodes are either balances or weights
	 */
	public class Node{
		Vector <Node> _left;	// left child list  (list of weights and balances on the left)
		Vector <Node> _right;	// right child list (list of weights and balances on the right)
		Node _parent;			// parent node
		int _index;				// index of balance, -1 for weights
		int _weight;			// intrinsic weight of node
		boolean _isBalance;		// true if node is a balance, else node is a weight
		
		public Node(boolean isBalance, int index_or_weight){
			_isBalance = isBalance;
			if (isBalance){
				_left = new Vector<Node>();
				_right = new Vector<Node>();
				_parent = null;
				_index = index_or_weight;
				_weight = 10;
			}
			else{
				_index = -1;
				_weight = index_or_weight;
			}
		}
	}
}