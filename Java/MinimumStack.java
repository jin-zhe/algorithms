import java.util.Stack;

/**
 * Task Description:
 * Implement a data structure that supports "find minimum" on top of the other
 * stack operations "push", "peek" and "pop" in O(1). Also, space Complexity for
 * finding minimum element should be O(1)
 */
public class MinimumStack {
	Stack<Integer> mainStack, minStack;
	
	public MinimumStack() {
		mainStack = new Stack<Integer>();
		minStack = new Stack<Integer>();
	}
	
	/**
	 * Push an item to the stack
	 * @param num	item to be pushed
	 */
	public void push (Integer num) {
		if (minStack.empty()) {
			minStack.push(num);
			mainStack.push(num);
		}
		else {
			int min = minStack.peek();
			if (num <= min) {
				minStack.push(num);
				mainStack.push(num);
			}
			else { mainStack.push(num); }
		}
	}
	
	/**
	 * Get the most recently pushed item
	 * @return	the most recently pushed item
	 */
	public Integer peek() {
		return mainStack.peek();
	}
	
	/**
	 * Remove the most recently pushed item
	 * @return	the most recently pushed item
	 */
	public Integer pop() {
		if (mainStack.peek() == minStack.peek()) {	minStack.pop();	}
		return mainStack.pop();
	}
	
	/**
	 * Gets the minimum item in the current stack
	 * @return	minimum item
	 */
	public Integer getMin() {
		return minStack.peek();
	}
	
	public static void main(String[] args) {
		MinimumStack ms = new MinimumStack();
		ms.push(2);
		System.out.println(ms.peek()); 		// should print 2
		ms.push(3);
		System.out.println(ms.peek());		// should print 3
		System.out.println(ms.getMin());	// should print 2
		ms.push(1);
		System.out.println(ms.peek());		// should print 1
		System.out.println(ms.getMin());	// should print 1
		ms.pop();	// pop 1
		System.out.println(ms.peek());		// should print 3
		System.out.println(ms.getMin());	// should print 2
	}
}
