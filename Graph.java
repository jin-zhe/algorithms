import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * @author Kilroy
 * A collection of graph algorithms:
 * • BFS
 * • DFS
 * • Kruskal (MST)
 * • Prim (MST)
 * • modified Dijkstra (SSSP)
 * • Bellman-Ford (SSSP)
 * • Floyd-Warshall (APSP)
 * 
 * Note:
 * Edges are stored using adjacency matrix
 * 
 * General notes on SSSP problems:
 * 	1. If graph is un-weighted, use a modified BFS treating every edge
 * 	   with a weight of 1
 * 	2. If graph is a tree, use either modified BFS or DFS
 * 	3. If graph is a Directed-Acyclic Graph
 * 		3.1 topologically sort graph vertices using DFS
 * 		3.2 relax all outgoing edges in topologically sorted order in a
 * 			single pass
 * 	4. If graph have no negative weight cycle but have negative weight
 * 	   edge, use modified Dijkstra
 * 	5. If graph might have negative weight cycle, use Bellman-Ford to
 * 	   check
 */
public class Graph {
	Integer[][] adjMat;	// adjacency matrix
	int cardinality;	// number of Vertices in the graph
	
	public Graph(Integer[][] adjMat) {
		this.adjMat = adjMat;
		this.cardinality = adjMat.length;
	}
	
	/**
	 * Breadth-First Search
	 * O(|V|^2) 	with adjacency matrix
	 * O(|V|+|E|) 	with adjacency list
	 * @param start		starting vertex to conduct BFS
	 * @return			the sequence of vertices visited
	 */
	public ArrayList<Integer> bfs(int start) {
		ArrayList<Integer> sequence = new ArrayList<Integer>();
		boolean[] visited = new boolean[cardinality];
		LinkedList<Integer> q = new LinkedList<Integer>();
		q.offer(start);
		visited[start] = true;
		while (!q.isEmpty()) {
			Integer currVert = q.poll();
			sequence.add(currVert);
			for (Integer i: getNeighbors(currVert)) {
				if (!visited[i]) {
					q.offer(i);
					visited[i] = true;
				}
			}
		}
		return sequence;
	}
	
	/**
	 * Depth-First Search
	 * O(|V|^2) 	with adjacency matrix
	 * O(|V|+|E|) 	with adjacency list
	 * @param start		starting vertex to conduct DFS
	 * @return			the sequence of vertices visited
	 */
	public ArrayList<Integer> dfs(int start) {
		boolean[] visited = new boolean[cardinality];
		ArrayList<Integer> sequence = new ArrayList<Integer>();
		dfs(start, visited, sequence);
		return sequence;
	}
	public void dfs(int currVert, boolean[] visited,
					ArrayList<Integer> sequence) {
		visited[currVert] = true;
		sequence.add(currVert);
		for (Integer i: getNeighbors(currVert)) {
			if (!visited[i]) {
					dfs(i, visited, sequence);
			}
		}
	}
	
	/**
	 * Kruskal's greedy algorithm for generating Minimum Spanning Tree (MST)
	 * O(|E|log|V|)
	 */
	public void kruskal() {
		ArrayList<Edge> MST = new ArrayList<Edge>();
		ArrayList<Edge> edges = getEdges(false); // undirected graph
		int MSTwt = 0;
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>(edges);
		ArrayList<HashSet<Integer>> sets =
				new ArrayList<HashSet<Integer>>(cardinality);
		/* create sets for every vertex, each starting as a singleton */
		for (int i=0; i<cardinality; i++) {
			HashSet<Integer> componentSet = new HashSet<Integer>();
			componentSet.add(i);
			sets.add(componentSet);
		}
		while (!pq.isEmpty()) {
			Edge edge = pq.poll();
			int vert1 = edge.vert1;
			int vert2 = edge.vert2;
			int edgeWt = edge.edgeWt;
			HashSet<Integer> vert1Set = sets.get(vert1);
			HashSet<Integer> vert2Set = sets.get(vert2);
			if (Collections.disjoint(vert1Set, vert2Set)) {
				/* merge the sets */
				mergeSets(sets, vert1Set, vert2Set);
				MST.add(edge);
				MSTwt += edgeWt;
			}
		}
		System.out.println("MST weight:        " + MSTwt);
		System.out.println("MST edges:         " + MST);
	}
	public void mergeSets(ArrayList<HashSet<Integer>> sets,
						  HashSet<Integer> vert1Set,
						  HashSet<Integer> vert2Set) {
		vert1Set.addAll(vert2Set);
		for (Integer vertex: vert1Set) {
			sets.set(vertex, vert1Set);
		}
	}
	
	/**
	 * Prim's greedy algorithm for generating Minimum Spanning Tree (MST)
	 * O(|E|log|V|)
	 * @param source	starting vertex to conduct Prim's algorithm
	 */
	public void prim(int source) {
		ArrayList<Edge> MST = new ArrayList<Edge>();
		boolean[] visited = new boolean [cardinality];
		int MSTwt = 0;
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		visited[0] = true;
		
		for (int i: getNeighbors(0)) {
			pq.offer(new Edge(0, i, getEdgeWt(0, i)));
		}
		
		while (!pq.isEmpty()) {
			Edge edge = pq.poll();
			int vert1 = edge.vert1;
			int vert2 = edge.vert2;
			int edgeWt = edge.edgeWt;
			if (!(visited[vert1] && visited[vert2])) {
				int newVert = (visited[vert1])? vert2: vert1;	// unvisited
				visited[newVert] = true;
				MSTwt += edgeWt;
				MST.add(edge);
				for (int i: getNeighbors(newVert)) {
					if (!visited[i]) {
						pq.offer(new Edge(newVert, i, getEdgeWt(newVert, i)));
					}
				}
			}
		}
		System.out.println("MST weight:        " + MSTwt);
		System.out.println("MST edges:         " + MST);
	}
	
	/**
	 * Modified Dijkstra for resolving Single Source Shortest Path (SSSP)
	 * It can handle graphs with negative edge weights
	 * (but not graphs with negative weighted cycles)
	 * O((|V|+|E|)log|V|)
	 * @param source	starting vertex to conduct Dijkstra's algorithm
	 */
	public void modifiedDijkstra(int source) {
		Integer[] SSSP = new Integer[cardinality];	// initially null: infinity
		Integer[] predecessor = new Integer[cardinality];
		SSSP[source] = 0;
		PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
		pq.offer(new Pair(source, 0));
		while (!pq.isEmpty()) {
			Pair curr = pq.poll();
			int vertex = curr.vertex;
			int pathWt = curr.pathWt;
			
			/* Lazy deletion technique:
			 * 	pathWt can only be lower or equal to SSSP[vertex]
			 * 	SSSP[vertex] can only decrease during enqueue operations
			 * 	if shortestPath lower: it is inferior (not updated) so we ignore
			 * 	if shortestPath equal: it is current so we process
			 */
			if (pathWt == SSSP[vertex]) {
				for (Integer i: getNeighbors(vertex)) {
					relaxEdge(SSSP, predecessor, vertex, i);
					pq.offer(new Pair(i, SSSP[i]));
				}
			}
		}
		System.out.println("Source vertex:     " + source);
		System.out.println("SSSP array:        " + Arrays.toString(SSSP));
		System.out.println("Predecessor array: " + Arrays.toString(predecessor));
	}
	
	/**
	 * Bellman-Ford algorithm for resolving Single Source Shortest Path (SSSP)
	 * O(|V||E|)
	 * @param source	starting vertex to conduct Bellman-Ford's algorithm
	 */
	public void bellmanFord(int source) {
		Integer[] SSSP = new Integer[cardinality];
		Integer[] predecessor = new Integer[cardinality];
		ArrayList<Edge> edges = getEdges(true);
		SSSP[source] = 0;
		/* runs |V|-1 times */
		for (int i=0; i<cardinality-1; i++) {
			for (Edge edge: edges) {
				Integer vert1 = edge.vert1;
				Integer vert2 = edge.vert2;
				relaxEdge(SSSP, predecessor, vert1, vert2);
			}
		}
		
		/* To check if graph has negative weighted cycle:
		 * loop one more time and check if SSSP has changed. If it did, negative
		 * weighted cycle(s) are present
		 */

		System.out.println("Source vertex:     " + source);
		System.out.println("SSSP array:        " + Arrays.toString(SSSP));
		System.out.println("Predecessor array: " + Arrays.toString(predecessor));
	}
	
	/**
	 * Used by Dijkstra and Bellman-Ford algorithms
	 * @param SSSP
	 * @param predecessor
	 * @param outVert
	 * @param inVert
	 */
	public void relaxEdge(Integer[] SSSP, Integer[] predecessor,
						  int outVert, int inVert) {
		int weight = getEdgeWt(outVert, inVert);
		Integer value = SSSP[inVert];
		/* if distance is currently at infinity */
		if (value == null) {
			SSSP[inVert] = SSSP[outVert] + weight;
			predecessor[inVert] = outVert;
		}
		else if (value > SSSP[outVert] + weight) {
			SSSP[inVert] = SSSP[outVert] + weight;
			predecessor[inVert] = outVert;
		}
	}
	
	/**
	 * Floyd-Warshall algorithm for resolving All-Pairs Shortest Path (APSP)
	 * O(|V|^3)
	 */
	public void floydWarshall() {
		Integer[][] APSP = new Integer[cardinality][cardinality];
		Integer[][] successor = new Integer[cardinality][cardinality];
		ArrayList<Edge> edges = getEdges(true);
		
		/* initializations */
		for (int i=0; i<cardinality; i++) {
			APSP[i][i] = 0;
		}
		for (Edge edge: edges) {
			Integer vert1 = edge.vert1;
			Integer vert2 = edge.vert2;
			Integer edgeWt = edge.edgeWt;
			APSP[vert1][vert2] = edgeWt;
			successor[vert1][vert2] = vert2;
		}
		
		/* bottom-up dynamic programming */
		for (int k=0; k<cardinality; k++) {
			for (int i=0; i<cardinality; i++) {
				if (successor[i][k] == null) {
					continue;
				}
				for (int j=0; j<cardinality; j++) {
					if (APSP[i][k]==null || APSP[k][j]==null) {
						continue;
					}
					if ((APSP[i][j] == null) ||	// if distance at infinity
						(APSP[i][j] > APSP[i][k] + APSP[k][j])) {
						APSP[i][j] = APSP[i][k] + APSP[k][j];
					    successor[i][j] = successor[i][k];
					}
				}
			}
		}
		
		System.out.println("APSP matrix:");
		printMatrix(APSP);
		System.out.println("Successor matrix:");
		printMatrix(successor);
	}

	/* Helper functions */
	/**
	 * Get all neighbors of current vertex
	 * O(|V|)
	 * @param vertex	current vertex
	 * @return			list of all neighbors
	 */
	public ArrayList<Integer> getNeighbors(int vertex) {
		ArrayList<Integer> neighbors = new ArrayList<Integer>();
		for (int i=0; i<cardinality; i++) {
			if (getEdgeWt(vertex, i) != null) {
				neighbors.add(i);
			}
		}
		return neighbors;
	}
	
	/**
	 * Gets the edge weight between two vertices
	 * O(1)
	 * @param outVert	source vertex
	 * @param inVert	destination vertex
	 * @return			the weight between outVert and inVert if edge exists,
	 * 					null otherwise
	 */
	public Integer getEdgeWt(int outVert, int inVert) {
		return adjMat[outVert][inVert];
	}
	
	/**
	 * Extracts all the edges from the adjacency matrix
	 * @param isDirected	is the graph directed
	 * @return				the list of all edges
	 */
	public ArrayList<Edge> getEdges(boolean isDirected) {
		ArrayList<Edge> edges = new ArrayList<Edge>();
		for (int i=0; i<cardinality; i++) {
			for (int j=0; j<cardinality; j++) {
				if (adjMat[i][j] != null) {
					/* if undirected graph, ignore bottom half */
					if (!isDirected && j<=i) {
						continue;
					}
					Edge edge = new Edge(i, j, adjMat[i][j]);
					edges.add(edge);
				}
			}
		}
		return edges;
	}
	
	/**
	 * Prints a 2x2 matrix
	 * @param mat	matrix to be printed
	 */
	public void printMatrix(Integer[][] mat) {
		int rowSize = mat.length;
		for (int i=0; i<rowSize; i++) {
			System.out.println(Arrays.toString(mat[i]));
		}
	}
	
	public static void main(String[] args) {
		/* Test BFS/DFS algorithms
		 * Undirected Weighted Graph:
		 * http://www.geeksforgeeks.org/wp-content/uploads/Fig-0.jpg
		 */
		Integer[][] adjMat1 = 
			{{null, 4,	  null, null, null, null, null, 8,    null}, // 0
			 {4,    null, 8,    null, null, null, null, 11,   null}, // 1
			 {null, 8,    null, 7,    null, 4,    null, null, 2   }, // 2
			 {null, null, 7,    null, 9,    14,   null, null, null}, // 3
			 {null, null, null, 9,    null, 10,   null, null, null}, // 4
			 {null, null, 4,    14,   10,   null, 2,    null, null}, // 5
			 {null, null, null, null, null, 2,    null, 1,    6   }, // 6
			 {8,    11,   null, null, null, null, 1,    null, 7   }, // 7
			 {null, null, 2,    null, null, null, 6,    7,    null}};// 8
			//0     1     2     3     4     5     6     7     8
		Graph graph1 = new Graph(adjMat1);
		System.out.println("************* Graph 1 *************");
		System.out.println("== BFS ============================");
		System.out.println(graph1.bfs(0));
		System.out.println("== DFS ============================");
		System.out.println(graph1.dfs(0));
		System.out.println("== Kruskal ========================");
		graph1.kruskal();
		System.out.println("== Prim ===========================");
		graph1.prim(0);
		
		System.out.println();
		/* Test Dijkstra/Bellman-Ford/Floyd-Warshall algorithms
		 * Directed Weighted Graph with negative weighted edge but no negative
		 * weighted cycles
		 * http://www.geeksforgeeks.org/wp-content/uploads/Johnson1.png
		 */
		Integer[][] adjMat2 = {{null,	-5,		2,		3	},
							   {null,	null,	4, 		null},
							   {null,	null,	null, 	1	},
							   {null,	null,	null, 	null}};
		Graph graph2 = new Graph(adjMat2);
		System.out.println("************* Graph 2 *************");
		System.out.println("== Modified Dijkstra ==============");
		graph2.modifiedDijkstra(0);
		System.out.println("== Bellman-Ford ===================");
		graph2.bellmanFord(0);
		System.out.println("== Floyd-Warshall =================");
		graph2.floydWarshall();
	}
	
	static class Pair implements Comparable<Graph.Pair> {
		Integer vertex;
		Integer pathWt;
		public Pair(int vertex, int pathWt) {
			this.vertex = vertex;
			this.pathWt = pathWt;
		}
		public int compareTo(Pair other) {
			return this.pathWt.compareTo(other.pathWt);
		}
	}
	
	static class Edge implements Comparable<Graph.Edge>{
		Integer vert1;
		Integer vert2;
		Integer edgeWt;
		public Edge(int vert1, int vert2, int edgeWt) {
			this.vert1 = vert1;
			this.vert2 = vert2;
			this.edgeWt = edgeWt;
		}
		public String toString() {
			return "(" + vert1 + "--" + vert2 + ", " + edgeWt + ")";
		}
		public int compareTo(Edge other) {
			return this.edgeWt.compareTo(other.edgeWt);
		}
	}
}