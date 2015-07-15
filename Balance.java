import java.util.*;
// Facebook's Hackerrank screening interview
// Jin Zhe's O(N) solution
public class Balance {
	Node[] balancesMap;		// map of balances, where array index corresponds to id of balance
	String[] output;		// output String where index corresponds to balance
	
	// recursively get the total balanced weight of a node (inclusive of itself)
	// Time complexity is O(N) since all nodes are visited once
	public int getBalancedWeight(Node aNode){
		if (!aNode._isBalance) return aNode._weight;	// if node is just a weight, return weight
		
		// total balanced weight of left children
		int totalLeftWeight = 0;
		for (Node leftChild: aNode._left){
			totalLeftWeight += getBalancedWeight(leftChild);
		}
		
		// total balanced weight of left children
		int totalRightWeight = 0;
		for (Node rightChild: aNode._right){
			totalRightWeight += getBalancedWeight(rightChild);
		}
		
		// formulate output string for current balance
		int diff = difference(totalLeftWeight, totalRightWeight);
		if (totalLeftWeight > totalRightWeight)			// if left children are heavier than right
			output[aNode._index] = aNode._index + ": 0 " + diff;
		else if (totalLeftWeight < totalRightWeight)	// if right children are heavier than left
			output[aNode._index] = aNode._index + ": " + diff + " 0";
		else											// else if equal
			output[aNode._index] = aNode._index + ": 0 0";
		
		return (aNode._weight + totalLeftWeight + totalRightWeight + diff);
	}
	
	// returns the difference between 2 numbers
	public int difference(int a, int b){
		return Math.abs(a - b);
	}
	
	// Shell
	void run(){
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		balancesMap = new Node[N];
		output = new String[N];
		sc.nextLine();
		
		// read from inputs and populate data structure balancesMap
		// Time Complexity: O(N)
		for (int i=0; i<2*N; i+=2){
			int index = i/2;	// index of current balance
			Node balance;		// current balance
			if (balancesMap[index]!=null) balance = balancesMap[index];	// if balance is already init, reference from map
			else{
				balance = new Node(true, index);						// else init and reference
				balancesMap[index] = balance;							// store in balancesMap
			}
			String firstInput = sc.nextLine();
			String secondInput = sc.nextLine();
			
			// tokenize inputs by space
			String[] leftChildInfo = firstInput.split(" ");
			String[] rightChildInfo = secondInput.split(" ");
			
			// for all the children on the left
			for (int j=0; j<leftChildInfo.length; j++){
				int value = Integer.parseInt(leftChildInfo[j]);
				// if weight column
				if (j==0){
					// if weight is non zero, add weight to left child list
					if (value!=0){
						balance._left.add(new Node(false, value));
					}
					// else do nothing
				}
				// if balance columns
				else{
					// add balance to left child list
					Node childBalance;
					if (balancesMap[value]!=null) childBalance = balancesMap[value];
					else{
						childBalance = new Node(true, value);
						balancesMap[value] = childBalance;
					}
					balance._left.add(childBalance);
					
					// point parent of childBalance to balance
					childBalance._parent = balance;
				}
			}
			
			// for all the children on the right
			for (int j=0; j<rightChildInfo.length; j++){
				int value = Integer.parseInt(rightChildInfo[j]);
				// if weight column
				if (j==0){
					// if weight is non zero, add weight to right child list
					if (value!=0){
						balance._right.add(new Node(false, value));
					}
					// else do nothing
				}
				// if balance columns
				else{
					// add balance to right child list
					Node childBalance;
					if (balancesMap[value]!=null) childBalance = balancesMap[value];
					else{
						childBalance = new Node(true, value);
						balancesMap[value] = childBalance;
					}
					
					balance._right.add(childBalance);
					
					// point parent of childBalance to balance
					childBalance._parent = balance;
				}
			}
		}// END: read inputs
		sc.close();
		
		// Input 2 suggests that there could be more than 1 root node ==> disjoint sets
		// find the balances without any parents (root nodes)
		// Time Complexity: O(N)
		Vector<Node> rootBalances = new Vector<Node>();
		for (Node i: balancesMap){
			// if balance has no parents, then it is the root node
			if (i._parent == null) rootBalances.add(i);
		}
		
		// runs recursive function
		// Time Complexity: O(N)
		for (Node i: rootBalances) getBalancedWeight(i);
		
		// prints output in order
		// Time Complexity: O(N)
		for (String i: output) System.out.println(i);
		
		/* Total Time complexity: O(N) + O(N) + O(N) + O(N) = O(N)*/
	}// END: run()
	
	// Main method
	public static void main(String[] args){
		Balance sol = new Balance();
		sol.run();
	}
	
	// Node class: Nodes are either balances or weights
	public class Node{
		Vector <Node> _left;	// left child list  (list of weights and balances on the left)
		Vector <Node> _right;	// right child list (list of weights and balances on the right)
		Node _parent;			// parent node
		int _index;				// index of balance, -1 for weights
		int _weight;			// intrinsic weight of node
		boolean _isBalance;		// true if node is a balance, else node is a weight
		
		// constructor
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