import java.util.Stack;

/**
 * Implement a queue using 2 stacks
 */
public class QueueStack<T> {
	Stack<T> stackIn;
	Stack<T> stackOut;
	
	/**
	 * constructor
	 */
	public QueueStack(){
		stackIn = new Stack<T>();
		stackOut = new Stack<T>();
	}
	
	public void offer(T item){
		stackIn.push(item);
	}
	
	public T poll(){
		if (!stackOut.empty())
			return stackOut.pop();
		else if (!stackIn.empty()){
			transfer();
			return stackOut.pop();
		}
		else 
			return null;
	}
	
	public T peek(){
		if (!stackOut.empty())
			return stackOut.peek();
		else if (!stackIn.empty()){
			transfer();
			return stackOut.peek();
		}
		else 
			return null;
	}
	
	/**
	 * @return true if the queue is empty, false otherwise
	 */
	public boolean isEmpty(){
		return (stackIn.empty() && stackOut.empty());
	}
	
	/**
	 * Transfers all items in stackIn to stackOut
	 */
	public void transfer(){
		while (!stackIn.empty()){
			stackOut.push(stackIn.pop());
		}
	}
	public static void main(String[] args) {
		QueueStack<Character> qs = new QueueStack<Character>();
		qs.offer('a');
		qs.offer('b');
		System.out.println(qs.peek()); 		// should print 'a'
		qs.offer('c');
		qs.offer('d');
		System.out.println(qs.poll()); 		// should print 'a' 
		System.out.println(qs.poll()); 		// should print 'b'
		System.out.println(qs.poll()); 		// should print 'c'
		System.out.println(qs.poll()); 		// should print 'd'
		System.out.println(qs.isEmpty());	// should print true
		qs.offer('e');
		System.out.println(qs.poll());		// should print 'e'
	}
}