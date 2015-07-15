import java.util.LinkedList;
/**
 * Algorithms on binary trees
 */
public class BinaryTree {
	Node root;
	
	public BinaryTree(Node root){
		this.root = root;
	}
	
	/**
	 * Prints binary tree in postorder
	 */
	public void printPostOrderDFS(Node node){
		if (node.left != null) printPostOrderDFS(node.left);
		if (node.right != null) printPostOrderDFS(node.right);
		System.out.print(node.value + " ");	// postorder handling here
	}
	
	// driver function
	public void printLCA(int xValue, int yValue){
		Node LCAxy = getLCA(root, xValue, yValue);
		if (LCAxy == null) System.out.println("both X and Y are not found in tree");
		else{
			if (LCAxy.value == xValue && xValue != yValue) System.out.println("Y is not found in tree");
			else if (LCAxy.value == yValue && xValue != yValue) System.out.println("X is not found in tree");
			else System.out.println("LCA of X and Y is " + LCAxy.value);
		}
	}
	/**
	 * Returns the Lowest Common Ancestor (LCA) of node with xValue and node with yValue
	 * @param node		the root node for current subtree being searched
	 * @param xValue	value of the first child node
	 * @param yValue	value of the second child node
	 * @return			the LCA or node with xValue or node with yValue or null if nothing found
	 */
	public Node getLCA(Node node, int xValue, int yValue){
		
		Node leftAns = null;
		if (node.left != null) leftAns = getLCA(node.left, xValue, yValue);
		Node rightAns = null;
		if (node.right != null) rightAns = getLCA(node.right, xValue, yValue);
		
		/* MUST BE CHECKED FIRST: else if both leftAns and rightAns are defined, current node is LCA */
		if (leftAns != null && rightAns != null) return node;
		
		/* if current node is either x or y*/
		if (node.value == xValue || node.value == yValue) return node;
		
		/* else if left subtree contains only 1 (could be LCA or X or Y) */
		if (leftAns != null && rightAns == null) return leftAns;
		
		/* else if right subtree contains only 1 (could be LCA or X or Y) */
		if (rightAns != null && leftAns == null) return rightAns;
		
		/* if both returns null -> x and y are not found, hence LCA not found in current subtree */
		else return null;
	}
	
	/**
	 *  prints binary tree level-wise O(N) time, O(N) space. Q will be at most all the leaves
	 */
	public void printLevelWise(){
		LinkedList<Pair<Node,Integer>> Q = new LinkedList<Pair<Node,Integer>>();

		/* initial conditions */
		int prevLevel = 0;
		Q.offer(new Pair<Node,Integer>(root, 0));

		while(!Q.isEmpty()){
			Pair<Node, Integer> currPair = Q.poll();
			Node currNode = currPair.left;
			int currLevel = currPair.right;
			
			/* special handling for new levels */
			if (currLevel != prevLevel){
				System.out.println("");	// print new line
				prevLevel = currLevel;	// update prevLevel
			}

			/* handle current node */
			System.out.print(currNode.value + " ");

			if (currNode.left != null) 	Q.offer(new Pair<Node,Integer>(currNode.left, currLevel + 1));
			if (currNode.right != null) Q.offer(new Pair<Node,Integer>(currNode.right, currLevel + 1));
		}
	}
	
	/**
	 *  prints level-wise, the average of each level
	 */
	public void printLevelAverage(){
		LinkedList<Pair<Node, Integer>> Q = new LinkedList<Pair<Node, Integer>>();
		
		// initial conditions
		int prevLevel = 0;
		int sum = 0;
		int levelSize = 0;
		Q.offer(new Pair<Node, Integer>(root, 0));
		
		while(!Q.isEmpty()){
			Pair<Node, Integer> currPair = Q.poll();
			Node currNode = currPair.left;
			int currLevel = currPair.right;
			
			// special handling if new level is reached
			if (currLevel != prevLevel){
				System.out.println((double) sum/levelSize);
				
				// resets sum and levelSize
				sum = 0;
				levelSize = 0;
				
				// update prevLevel
				prevLevel = currLevel;
			}
			
			// update sum and levelSize
			sum += currNode.value;
			levelSize ++;
			
			if (currNode.left != null) Q.offer(new Pair<Node,Integer>(currNode.left, currLevel + 1));
			if (currNode.right != null) Q.offer(new Pair<Node,Integer>(currNode.right, currLevel + 1));
		}
		System.out.println((double) sum/levelSize);
	}
	
	public static void main(String[] args){
		/* hardcoded tree implementation:
		 * 
		 *	     1
		 *	    / \
		 *	   /   \
		 *	  /     \
		 *   2       3
		 *  / \     / \
	     * 4   5   6   7
	     *    / \   \
		 *   8   9   0 
		 *    
		 */
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.left = new Node(4);
		root.left.right = new Node(5);
		root.left.right.left = new Node(8);
		root.left.right.right = new Node(9);
		root.right = new Node(3);
		root.right.left = new Node(6);
		root.right.left.right = new Node(0);
		root.right.right = new Node(7);
		
		BinaryTree bt = new BinaryTree(root);
		System.out.println("-- Level Wise --");
		bt.printLevelWise();
		System.out.println();
		System.out.println("-- Level Wise Average --");
		bt.printLevelAverage();
		System.out.println("-- Post Order --");
		bt.printPostOrderDFS(root);
		System.out.println();
		System.out.println("-- LCA of 8 and 3 --");
		bt.printLCA(8, 3);
		
	}
	public static class Node{
		Integer value;
		Node left;
		Node right;
		public Node(int value){
			this.value = value;
			left = null;
			right = null;
		}
	}
	public class Pair<X,Y>{
		X left;
		Y right;
		public Pair(X left, Y right){
			this.left = left;
			this.right = right;
		}
	}
}
