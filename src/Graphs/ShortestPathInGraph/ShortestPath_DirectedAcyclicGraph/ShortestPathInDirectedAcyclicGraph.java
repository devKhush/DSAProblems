package Graphs.ShortestPathInGraph.ShortestPath_DirectedAcyclicGraph;
import java.util.*;

// DAG's always have a topological sort Solution
// Topological Sort Solution:
// https://youtu.be/ZUFQfFaU-8U  (new) VERY OP INTUITION
// https://youtu.be/CrxG4WJotgg
// https://www.geeksforgeeks.org/shortest-path-for-directed-acyclic-graphs/
// https://www.scaler.com/topics/data-structures/shortest-path-in-directed-acyclic-graph/
// INTUITION: https://stackoverflow.com/questions/37253739/intuition-behind-the-algorithm-to-compute-single-source-shortest-path-for-every
// INTUITION: https://www.codingninjas.com/codestudio/library/shortest-path-in-a-directed-acyclic-graph

public class ShortestPathInDirectedAcyclicGraph {
    /* ************************************ Efficient Topological Sort Solution **************************************
     * In this solution, fewer comparisons are made as compared to BFS/DFS solution.
     * PRE_REQUISITE: "Topological Sort"
     * The Intuition is to use the Topological Sort Algorithm.
     *
     * Time Complexity: O(V+E) + O(V) + O(V+E)  ~  O(V+E)
       For a graph G=(V,E) time taken to find the topological ordering is O(V+E). After that, for
       every vertex V we run a loop to its adjacent vertices.
       So time taken in this step is also O(V + E). Hence, the overall time complexity is O(V+E)
     * Space Complexity: O(3 * V) = O(V)         Same as DFS for Graph with adjacency list
     *
     * Conclusion: In the case of the Directed Acyclic Graphs (DAG), finding the topological ordering
       of the vertices can help us find the single source shortest paths in O(V+E) time.
       Unlike the Bellman Ford algorithm which takes O(V×E) time to calculate the same.
     */
    public int[] shortestPathsInDAG_TopologicalSort(int source, int V, ArrayList<ArrayList<int[]>> adjList){
        // Topological Sort in a stack
        Stack<Integer> topoSort = getTopologicalSort(V, adjList);

        // Array to store "Shortest path from source to the nodes"
        int[] shortestPath = new int[V];
        Arrays.fill(shortestPath, Integer.MAX_VALUE);
        shortestPath[source] = 0;

        // Process all the vertices in Graph in topological order
        // Here, processing a vertex means, updating distances of its adjacent vertices using the distance
        // of the current vertex from start.
        // If there are some vertices in the ordering before the ‘src’ vertex, they are not reachable from the
        // ‘src’ node, hence no need to update their distances from the ‘src’ node.
        while (!topoSort.isEmpty()){
            // Get the next vertex from topological order
            int node = topoSort.pop();

            // Update distances of all adjacent vertices if those vertices have been previously visited
            // We will check if shortestPath[from source to vertex] != INF, which means if vertex is reachable from source
            if (shortestPath[node] != Integer.MAX_VALUE){
                for (int[] adjacent : adjList.get(node)){
                    int neighbour = adjacent[0];
                    int pathCost = adjacent[1];

                    if (shortestPath[neighbour] > shortestPath[node] + pathCost)
                        shortestPath[neighbour] = shortestPath[node] + pathCost;
                }
            }
        }
        return shortestPath;
    }

    // DFS Algorithm for Topological Sort for DAGs
    private Stack<Integer> getTopologicalSort(int V, ArrayList<ArrayList<int[]>> adj){
        Stack<Integer> topoSort = new Stack<>();

        boolean[] visited = new boolean[V];
        for (int node = 0; node < V; node++){
            if (!visited[node]){
                dfs(node, adj, topoSort, visited);
            }
        }
        return topoSort;
    }

    public void dfs(int node, ArrayList<ArrayList<int[]>> adj, Stack<Integer> topoSort,
                    boolean[] visited){
        visited[node] = true;

        for (int[] neighbour : adj.get(node)){
            if (!visited[neighbour[0]]) {
                dfs(neighbour[0], adj, topoSort, visited);
            }
        }
        topoSort.push(node);
    }
}

/******************************** INTUITION BEHIND TOPOLOGICAL SORT SOLUTION ******************************
 * 1) Finding the shortest path to a vertex is easy if you already know the shortest paths to all the
      vertices that can precede it.
      OR SAY
      In topo-sort, say the order is 'a b c d', if we follow the topo-sort order for source 'a':
            Suppose a and b are connected to d, when we reach at 'd', we know that 'a' and 'b' have
            relaxed the distance to 'd' (or distance of a and b have been computed), and we can reach
            'd' only via 'a' and 'b', because they come earlier in topo-sort.
            Refer to NEW VIDEO of STRIVER.

      Finding the longest path to a vertex in DAG is easy if you already
      know the longest path to all the vertices that can precede it.
      Processing the vertices in topological order ensures that by the time you get to a vertex,
      you've already processed all the vertices that can precede it.

    IMP: Dijkstra's algorithm is necessary for graphs that can contain cycles, because they can't be
    topologically sorted.

 * 2) Bellman-Ford algorithm, which can be used here to compute the shortest distances from a given
      ‘src’ node to all nodes in O(V*E).
      Note that if you are trying to use Dijkstra here, it won’t work because the weighted DAG can
      also have negative weights.
      Can we think of some better algorithm other than the Bellman-Ford algorithm?
      If we can have an ordering of vertices, all nodes that are not reachable from the ‘src’ node
      are kept on the left side of the ‘src’ node, and all reachable nodes are kept on its right.
      Why are we looking for such an order? Because we will not have to update the shortest path of
      the unreachable nodes.
      Another advantage is that from a vertex, we can relax all the edges connecting the other
      vertices. This process can be done for all vertices in this ordering one by one for all
      their respective edges.
      The intuition behind this is that, since we will find the shortest distance from the ‘src’ node,
      the ‘src’ node will be present in this ordering before all nodes are reachable from the ‘src’ node. So all vertices which form edges with ‘src’ nodes will get updated first. Then all the other edges will get relaxed one by one while visiting the vertices in this ordering.
      This will find the shortest distance from the ‘src’ node to all nodes.
      The ordering which we have been using in this discussion is the Topological sorted ordering
      of a graph.
 * 3) We moved according to the reachability, this results in minimization of computations.
 */
