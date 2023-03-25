package Graphs.CountUnreachablePairsOfNodesInAnUndirectedGraph;
import java.util.ArrayList;

// https://leetcode.com/problems/count-unreachable-pairs-of-nodes-in-an-undirected-graph/description/

public class CountUnreachablePairsOfNodes_DFS {
    /************************************ Disjoint Set Solution ******************************************8
     * Intuition:
        * We need to find the total number of nodes present in all the components of Graph and store it.
        * Finding total no. of nodes in a Graph can also be done by SFS traversal
         * To optimize the pair finding code, we can optimize the O(n^2) loop to O(n) using a suffix array.

     * Time Complexity: O(V + E)
        * O(V + E) to generate graph
        * O(V + E) for DFS traversal
        * O(V) to find al pairs
     * Space Complexity: O(V + E)
        * To store graph in an adjacency list
     */
    public long countPairs(int V, int[][] edges) {
        ArrayList<Integer>[] adj = adjacencyList(V, edges);

        // Store Size and parents of each Graph components
        boolean[] visited = new boolean[V];
        long[] size = new long[V];
        ArrayList<Integer> parents = new ArrayList<>();

        // Find thr parent and size of all nodes using DFS
        for (int u = 0; u < V; u++){
            if (!visited[u]){
                parents.add(u);
                size[u] = dfs(u, adj, visited);
            }
        }

        // Find the size of all parents nodes in graph's components
        // we can optimize the O(n^2) loop to O(n) using a suffix array
        // A * B  +  A * C  =  A * (B + C)
        long pairs = 0;
        int numParents = parents.size();
        long[] suffixSum = new long[numParents];
        suffixSum[numParents - 1] = size[parents.get(numParents - 1)];

        for (int i = numParents - 2; i >= 0; i--){
            suffixSum[i] = suffixSum[i + 1] + size[parents.get(i)];
            pairs += size[parents.get(i)] * suffixSum[i + 1];
        }
        return pairs;
    }

    private long dfs(int node, ArrayList<Integer>[] adj, boolean[] visited){
        visited[node] = true;
        int totalNodes = 1;
        for (int neighbour : adj[node]){
            if (!visited[neighbour]){
                totalNodes += dfs(neighbour, adj, visited);
            }
        }
        return totalNodes;
    }

    private ArrayList<Integer>[] adjacencyList(int V, int[][] edges){
        ArrayList<Integer>[] adj = new ArrayList[V];
        for (int u = 0; u < V; u++)
            adj[u] = new ArrayList<>();
        for (int[] edge : edges){
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }
        return adj;
    }
}
