import java.util.ArrayList;
import java.util.HashSet;

/**
 * Implement deepcopy on the class Thing
 */
public class Thing {
    String label;
	public final ArrayList<Thing> things;
    
	/* constructor */
	public Thing(String label){
		this.label = label;
		things = new ArrayList<Thing>();
	}
	
	// add a reachable neighbor to the list of things
	public void addReachableNode(Thing aThing){
		things.add(aThing);
	}
	
    /* Return a deep copy of this. The object graph formed by the copy
     * should have the same structure as the object graph of the original,
     * but they should share no references.
     */
    
    // driver function
    public Thing deepCopy() {
        // implement this
        return deepCopyHelper(new HashSet<Thing>());
    }
    // deep-copy function
    private Thing deepCopyHelper(HashSet<Thing> set) {
        set.add(this);                  			// add caller to set
        Thing newThing = new Thing(this.label);   	// to be returned
        // for each child
        for (Thing aThing: things){
            if (!set.contains(aThing)){       					// if node not yet processed
                Thing childThing = aThing.deepCopyHelper(set);  // recursion step: get deep copied child
                newThing.things.add(childThing);                // append deep copied child to list of things
            }
        }
        return newThing;
    }
    
    // Depth-first search which prints node label
    public void dfs_label(){
    	dfs_label(new HashSet<Thing>());
    }
    public void dfs_label(HashSet<Thing> set){
    	set.add(this);
    	System.out.print(label + " ");
    	for (Thing aThing: things)
    		if (!set.contains(aThing))
    			aThing.dfs_label(set);
    }
    
    // Depth-first search which prints node references
    public void dfs_ref(){
    	dfs_ref(new HashSet<Thing>());
    }
    public void dfs_ref(HashSet<Thing> set){
    	set.add(this);
    	System.out.print(this + " ");
    	for (Thing aThing: things)
    		if (!set.contains(aThing))
    			aThing.dfs_ref(set);
    }
    public static void main(String[] args) {
 
/* 		Create Graph:
*    	A --> B --> E --> H
*    	^     |     |
*    	|     v     v
*    	D <-- C <-- F --> G
*/
    	Thing thingA = new Thing("A");
    	Thing thingB = new Thing("B");
    	Thing thingC = new Thing("C");
    	Thing thingD = new Thing("D");
    	Thing thingE = new Thing("E");
    	Thing thingF = new Thing("F");
    	Thing thingG = new Thing("G");
    	Thing thingH = new Thing("H");
    	
    	thingA.addReachableNode(thingB);	// A --> B
    	thingB.addReachableNode(thingC);	// B --> C
    	thingC.addReachableNode(thingD);	// C --> D
    	thingD.addReachableNode(thingA);	// D --> A
    	thingB.addReachableNode(thingE);	// B --> E
    	thingE.addReachableNode(thingF);	// E --> F
    	thingF.addReachableNode(thingC);	// F --> C
    	thingF.addReachableNode(thingG);	// F --> G
    	thingE.addReachableNode(thingH);	// E --> H
    	
    	/* Run DFS on original and deep-copied Thing and juxtapose results */
    	System.out.println("thingA labels:");
    	thingA.dfs_label();
    	System.out.println();
    	
    	System.out.println("deep-copied thingA labels:");
    	Thing thingACopied = thingA.deepCopy();
    	thingACopied.dfs_label();
    	System.out.println();
    	
    	System.out.println("thingA references:");
    	thingA.dfs_ref();
    	System.out.println();
    	
    	System.out.println("deep-copied thingA references:");
    	thingACopied = thingA.deepCopy();
    	thingACopied.dfs_ref();
    }
}