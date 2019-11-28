import java.util.LinkedList;
/**
 * Algorithms on binary trees:
 * +--------------------+------------------------------------------------------+
 * |	Method Name		|					Description						   |
 * +--------------------+------------------------------------------------------+
 * | find 				| given a node value, finds the node in tree using dfs |
 * | printPreOrder 		| Pre-order traversal								   |
 * | printInOrder		| In-order traversal								   |
 * | printPostOrder		| post-order traversal								   |
 * | isBST				| checks if tree is a BST							   |
 * | getHeight			| gets the height of the BST						   |
 * | isTreeBalanced		| checks if the tree is height balanced				   |
 * | getLCA				| determines the Lowest Common Ancestor of two nodes   |
 * | printLevelWise		| prints the tree level wise						   |
 * | printLevelAverage	| prints the average value for each level in tree	   |
 * | getInOrderSuccessor| determines the in-order successor for a node		   |
 * 	+-------------------+------------------------------------------------------+
 */
public class BinaryTree {
	Node root;
	
	public BinaryTree(Node root){
		this.root = root;
	}
	
	/**
	 * Retrieves the first node found in the tree for the given value using DFS
	 * @param value	the node value
	 * @return		a node possessing the value, null if not present in tree
	 */
	public Node find(Node subtree, int value) {
		/* base case: not found */
		if (subtree == null) {
			return null;
		}
		int curr = subtree.value;
		if (curr == value) {
			return subtree;
		}
		/* check if found in left subtree */
		Node leftAns = find(subtree.left, value);
		if (leftAns != null) {
			return leftAns;
		}
		/* check if found in right subtree */
		Node rightAns = find(subtree.right, value);
		if (rightAns != null) {
			return rightAns;
		}
		return null;	// if value not present in subtree
	}
	public Node find(int value) {
		return find(root, value);
	}
	
	/**
	 * Prints tree in Pre-order traversal
	 */
	public void printPreOrder(Node node) {
		System.out.print(node + " ");
		if (node.left != null) {
			printPreOrder(node.left);
		}
		if (node.right != null) {
			printPreOrder(node.right);
		}
	}
	public void printPreOrder() {
		printPreOrder(root);
	}
	
	/**
	 * Prints tree in In-order traversal
	 */
	public void printInOrder(Node node) {
		if (node.left != null) {
			printInOrder(node.left);
		}
		System.out.print(node + " ");
		if (node.right != null) {
			printInOrder(node.right);
		}
	}
	public void printInOrder() {
		printInOrder(root);
	}
	
	/**
	 * Prints tree in Post-order traversal
	 */
	public void printPostOrder(Node node){
		if (node.left != null) {
			printPostOrder(node.left);
		}
		if (node.right != null) {
			printPostOrder(node.right);
		}
		System.out.print(node + " ");
	}
	public void printPostOrder() {
		printPostOrder(root);
	}
	
	/**
	 * Determines if tree is a BST
	 * @param subtree	root node of subtree
	 * @param min		min value for range
	 * @param max		max value of range
	 * @return			true if subtree is a BST, false otherwise
	 */
	public boolean isBST(Node subtree, int min, int max) {
		if (subtree == null) {
			return true;	// empty tree is a BST
		}
		int value = subtree.value;
		/* if value is within range */
		if (value>min && value<=max){
			return (isBST(subtree.left, min, value) &&
					isBST(subtree.right, value, max));
		}
		else {
			return false;
		}
	}
	public boolean isBST() {
		return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	/**
	 * Determines the height of the subtree
	 * @param subtree	root node of subtree
	 * @return			height of subtree
	 */
	public int getHeight(Node subtree) {
		if (subtree == null) {
			return 0;
		}
		return Math.max(getHeight(subtree.left), getHeight(subtree.right)) + 1;
	}
	
	/**
	 * Checks if tree is a balanced tree. A tree is balanced if its subtrees do
	 * not differ more than 1 level in height
	 * @return true if tree is balanced, false otherwise
	 */
	public boolean isTreeBalanced() {
		int diff = Math.abs(getHeight(root.left) - getHeight(root.right));
		return (diff < 2);
	}
	
	/**
	 * Returns the Lowest Common Ancestor (LCA) of node with xValue and node
	 * with yValue
	 * @param node		the root node for current subtree being searched
	 * @param xValue	value of the first child node
	 * @param yValue	value of the second child node
	 * @return			the LCA or node with xValue or node with yValue or null
	 * 					if nothing found
	 */
	public Node getLCA(Node node, int xValue, int yValue){
		
		Node leftAns = null;
		if (node.left != null) {
			leftAns = getLCA(node.left, xValue, yValue);
		}
		Node rightAns = null;
		if (node.right != null) {
			rightAns = getLCA(node.right, xValue, yValue);
		}
		
		/* MUST BE CHECKED FIRST: else if both leftAns and rightAns are defined,
		 * current node is LCA */
		if (leftAns != null && rightAns != null) {
			return node;
		}
		
		/* if current node is either x or y*/
		if (node.value == xValue || node.value == yValue) {
			return node;
		}
		
		/* else if left subtree contains only 1 (could be LCA or X or Y) */
		if (leftAns != null && rightAns == null) {
			return leftAns;
		}
		
		/* else if right subtree contains only 1 (could be LCA or X or Y) */
		if (rightAns != null && leftAns == null) {
			return rightAns;
		}
		
		/* if both returns null -> x and y are not found, hence LCA not found in
		 * current subtree */
		else return null;
	}
	public void getLCA(int xValue, int yValue){
		Node LCAxy = getLCA(root, xValue, yValue);
		if (LCAxy == null) {
			System.out.println("both X and Y are not found in tree");
		}
		else {
			if (LCAxy.value == xValue && xValue != yValue) {
				System.out.println("Y is not found in tree");
			}
			else if (LCAxy.value == yValue && xValue != yValue) {
				System.out.println("X is not found in tree");
			}
			else {
				System.out.println("LCA of " + xValue + " and " + yValue +
								   " is " + LCAxy.value);
			}
		}
	}
	
	/**
	 *  prints binary tree level-wise O(N) time, O(N) space. Q will be at most
	 *  all the leaves
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

			if (currNode.left != null) {
				Q.offer(new Pair<Node,Integer>(currNode.left, currLevel + 1));
			}
			if (currNode.right != null) {
				Q.offer(new Pair<Node,Integer>(currNode.right, currLevel + 1));
			}
		}
	}
	
	/**
	 *  prints level-wise, the average of each level
	 */
	public void printLevelAverage(){
		LinkedList<Pair<Node, Integer>> Q = 
			new LinkedList<Pair<Node, Integer>>();
		
		/* initial conditions */
		int prevLevel = 0;
		int sum = 0;
		int levelSize = 0;
		Q.offer(new Pair<Node, Integer>(root, 0));
		
		while(!Q.isEmpty()){
			Pair<Node, Integer> currPair = Q.poll();
			Node currNode = currPair.left;
			int currLevel = currPair.right;
			
			/* special handling if new level is reached */
			if (currLevel != prevLevel){
				System.out.println((double) sum/levelSize);
				
				/* resets sum and levelSize */
				sum = 0;
				levelSize = 0;
				
				/* update prevLevel */
				prevLevel = currLevel;
			}
			
			/* update sum and levelSize */
			sum += currNode.value;
			levelSize ++;
			
			if (currNode.left != null) {
				Q.offer(new Pair<Node,Integer>(currNode.left, currLevel + 1));
			}
			if (currNode.right != null) {
				Q.offer(new Pair<Node,Integer>(currNode.right, currLevel + 1));
			}
		}
		System.out.println((double) sum/levelSize);
	}
	
	/**
	 * Determines the in-order successor of a given node
	 * @param node	node of interest
	 * @return		in-order successor of node, null if non existent
	 */
	public Node getInorderSuccessor(Node node) {
		Node ptr = node;
		/* if node had right child, return leftmost node of right subtree */
		if (ptr.right != null) {
			ptr = ptr.right;
			while (ptr.left != null) {
				ptr = ptr.left;
			}
			return ptr;
		}
		
		/* else find first parent where node is the left descendant */
		else {
			while (ptr.parent != null) {
				if (ptr.parent.left == ptr) {
					return ptr.parent;
				}
				ptr = ptr.parent;
			}
			return null;	// if no in-order successor
		}
	}
	
	public static void main(String[] args){
		Node node1; Node node2; Node node3; Node node4; Node node5; Node node6;
		Node node7; Node node8; Node node9; Node node0;
		
		Node root;
		BinaryTree bt;
		
		/* BST:
		 * 
		 *	     5
		 *	    / \
		 *	   /   \
		 *	  /     \
		 *   1       8
		 *  / \     / \
	     * 0   3   6   9
	     *    / \   \
		 *   2   4   7 
		 *    
		 */
		System.out.println("*************** BST ***************");
		System.out.println();
		node1 = new Node(1); node2 = new Node(2); node3 = new Node(3);
		node4 = new Node(4); node5 = new Node(5); node6 = new Node(6);
		node7 = new Node(7); node8 = new Node(8); node9 = new Node(9);
		node0 = new Node(0);
		root = node5;
		bt = new BinaryTree(root);
		root.setLeft(node1);
		root.left.setLeft(node0);
		root.left.setRight(node3);
		root.left.right.setLeft(node2);
		root.left.right.setRight(node4);
		root.setRight(node8);
		root.right.setLeft(node6);
		root.right.left.setRight(node7);
		root.right.setRight(node9);
		
		bt = new BinaryTree(root);
		runAlgorithms(bt);
		
		
		/* not BST:
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
		System.out.println();
		System.out.println("************* NOT BST *************");
		System.out.println();
		node1 = new Node(1); node2 = new Node(2); node3 = new Node(3);
		node4 = new Node(4); node5 = new Node(5); node6 = new Node(6);
		node7 = new Node(7); node8 = new Node(8); node9 = new Node(9);
		node0 = new Node(0);
		
		root = node1;
		root.setLeft(node2);
		root.left.setLeft(node4);
		root.left.setRight(node5);
		root.left.right.setLeft(node8);
		root.left.right.setRight(node9);
		root.setRight(node3);
		root.right.setLeft(node6);
		root.right.left.setRight(node0);
		root.right.setRight(node7);
		
		bt = new BinaryTree(root);
		runAlgorithms(bt);
		
	}
	
	
	public static void runAlgorithms(BinaryTree bt) {
		System.out.println("=== Level-Wise ====================");
		bt.printLevelWise();
		System.out.println();
		System.out.println("=== Level-Wise Average ============");
		bt.printLevelAverage();
		System.out.println("=== Traversals ====================");
		System.out.print("Pre-Order:  ");
		bt.printPreOrder();
		System.out.println();
		System.out.print("In-Order:   ");
		bt.printInOrder();
		System.out.println();
		System.out.print("Post-Order: ");
		bt.printPostOrder();
		System.out.println();
		System.out.println("=== Lowest Common Ancestor +=======");
		bt.getLCA(8, 3);
		System.out.println("=== Inorder Successors ============");
		Node node1 = bt.find(1);
		Node node9 = bt.find(9);
		Node node7 = bt.find(7);
		System.out.println("Successor of 1: " + bt.getInorderSuccessor(node1));
		System.out.println("Successor of 9: " + bt.getInorderSuccessor(node9));
		System.out.println("Successor of 7: " + bt.getInorderSuccessor(node7));
		System.out.println("=== Is Valid BST? =================");
		System.out.println(bt.isBST());
		System.out.println("=== Tree Height ===================");
		System.out.println("Height: " + bt.getHeight(bt.root));
		System.out.println("Is tree balanced? " + bt.isTreeBalanced());
	}
	
	public static class Node{
		Integer value;
		Node left;
		Node right;
		Node parent;
		public Node(int value){
			this.value = value;
			left = null;
			right = null;
			parent = null;
		}
		public void setLeft(Node child) {
			left = child;
			child.parent = this;
		}
		public void setRight(Node child) {
			right = child;
			child.parent = this;
		}
		public String toString() {
			return value.toString();
		}
	}
	
	public static class Pair<X, Y>{
		X left;
		Y right;
		public Pair(X left, Y right){
			this.left = left;
			this.right = right;
		}
	}
}