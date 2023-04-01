package Graphs.StronglyConnectedComponents;
import java.util.ArrayList;
import java.util.Stack;

// https://youtu.be/V8qIqJxCioo
// https://youtu.be/R6uoSjZ2imo (new)
// https://takeuforward.org/graph/strongly-connected-components-kosarajus-algorithm-g-54/
// https://takeuforward.org/data-structure/kosarajus-algorithm-for-strongly-connected-componentsscc/
// MUST READ for Intuition: https://www.geeksforgeeks.org/strongly-connected-components/

/**
 * Strongly Connected components are valid for only "Directed Graphs"

 * Intuition:  https://youtu.be/V8qIqJxCioo
               https://www.geeksforgeeks.org/strongly-connected-components/
        The idea behind KosaRaju’s algorithm is to do a DFS in a controlled fashion such that we won’t
        be able to go from one SCC to other SCC. One DFS call would visit all the nodes in an SCC only.

 * Time Complexity: O(3 * (V + E))  =  O(V + E)
        One O(V + E) to find the Topological ordering of vertices
        Another O(V + E) to find the transpose of the given graph
        Another O(V + E) to find the Strongly Connected Components (SCC) in the graph
 * Space Complexity: O(V + E) + O(2 * V)  =  O(V + E)
        O(V + E) space to store the transpose of the given graph
        O(V) to store the Topological ordering of vertices
        O(V) for the visited array to keep track of visited vertices in DFS
 */

public class KosaRajuAlgorithm {
    // ********************** Finding the Topological Sort Ordering of the graph **********************
    // BFS Topological sort method can't be used here, it won't work here
    private void topoSort_dfs(int node, ArrayList<ArrayList<Integer>> adj, boolean[] visited,
                              Stack<Integer> topoSort) {
        visited[node] = true;
        for (int neighbour : adj.get(node)) {
            if (!visited[neighbour])
                topoSort_dfs(neighbour, adj, visited, topoSort);
        }
        // All vertices reachable from "vertex" are processed by now, push "vertex" to Stack
        // Adding all the vertices according to their finishing time/order (i.e, no new nodes to traverse)
        topoSort.push(node);
    }


    // ********************************* Simple DFS Traversal *********************************
    public void dfs(int node, ArrayList<ArrayList<Integer>> adj, boolean[] visited, ArrayList<Integer> scc){
        visited[node] = true;
        scc.add(node);

        for (int adjacentVertex : adj.get(node)){
            if (!visited[adjacentVertex])
                dfs(adjacentVertex, adj, visited, scc);
        }
    }


    // ********************************* Reversing the graph *********************************
    public ArrayList<ArrayList<Integer>> transposeGraph(int V, ArrayList<ArrayList<Integer>> adj){
        ArrayList<ArrayList<Integer>> reverse = new ArrayList<>();
        for (int u = 0; u < V; u++)
            reverse.add(new ArrayList<>());

        // Reversing the direction of Edges in the transposed graph
        // If there was an edge from 'u' -> 'v', then in transposed graph edge will be from 'v' to 'u'
        for (int u = 0; u < V; u++){
            for (int v : adj.get(u)){
                reverse.get(v).add(u);
            }
        }
        return reverse;
    }

    /** ************************************ KosaRaju Algorithm *************************************/
    public ArrayList<ArrayList<Integer>> getStronglyConnectedComponents_KosaRajuAlgorithm(int V, ArrayList<ArrayList<Integer>> adj){

        // Stack to store the topological sorting of the Graph
        // Since Directed graph may contain cycles, so it is exactly not the topological sorting
        // It just the ordering in the topological way
        // That's why Topological sorting for BFS can't be used here (Kahn's algo for topological sort
        // using BFS is not possible for Directed Cyclic graphs)
        // * Stack will store the vertices in the order of their finishing time/order,
        // * Bottom element in stack will be finished first (its out-degree will be minimum & in-degree will be maximum)
        // * Top element in stack will be finished at last (its out-degree will be maximum & in-degree will be minimum)
        // Recall it is same as topological sort
        Stack<Integer> topoSort = new Stack<>();

        // Visited  boolean array to keep track of visited vertices in finding topological sort
        boolean[] visited = new boolean[V];

        // Call DSF for topological sort
        for (int u = 0; u < V; u++){
            if (!visited[u]){
                topoSort_dfs(u, adj, visited, topoSort);
            }
        }

        // Find the Transposed/Reversed graph of the given graph
        ArrayList<ArrayList<Integer>> reverseGraph = transposeGraph(V, adj);

        ArrayList<ArrayList<Integer>> all_SCCs = new ArrayList<>();     // ArrayList to store all the SCCs
        int scc_Count = 0;                                              // Count of the all thr SCCs

        visited = new boolean[V];           // Visited array for DFS traversal in Reversed graph

        while (!topoSort.isEmpty()){
            int node = topoSort.pop();

            if (!visited[node]){
                ArrayList<Integer> scc = new ArrayList<>();
                all_SCCs.add(scc);
                scc_Count++;
                dfs(node, reverseGraph, visited, scc);
            }
        }
        return all_SCCs;
    }
}
