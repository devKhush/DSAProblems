package Graphs.NumberOfWaysToArriveAtDestination;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

// https://leetcode.com/problems/number-of-ways-to-arrive-at-destination/description/

public class NumberOfWaysToArriveAtDestination_DFS_DP {
    /************************************** DFS + DP Solution ******************************************
     * Time Complexity: O(V+E) + O(E*log(E)) + O(V+E)   ~    O(E*log(E)) + O(V+E)
        * O(V+E) to Generate_Graph
        * O(E*log(E)) for Dijkstra algorithm
        * O(V+E) for DFS to find the total ways
     * Space Complexity: O(V+E) + O(E) + O(V)   ~   O(V+E)
        * Adjacency_list, MinHeap, Distance_Array, DP_Array & Recursion_Stack_Space
     */
    public int countPaths(int V, int[][] roads) {
        ArrayList<int[]>[] adj = getAdjacencyList(V, roads);
        long[] shortestPath = dijkstraAlgo(V, 0, adj);

        // DP array
        Integer[] dp = new Integer[V];
        return dfs(V - 1, 0, adj, shortestPath, dp);
    }

    // Find total ways from source to destination with the shortest distance
    private int dfs(int node, int src, ArrayList<int[]>[] adj, long[] shortestPath, Integer[] dp){
        if (node == src)
            return 1;
        if (dp[node] != null)
            return dp[node];

        long totalWays = 0;
        for (int[] adjacent : adj[node]){
            int neighbour = adjacent[0];
            int edgeWt = adjacent[1];
            if (shortestPath[node] - shortestPath[neighbour] == edgeWt)
                totalWays += dfs(neighbour, src, adj, shortestPath, dp) % 1000000007;
        }
        return dp[node] = (int) (totalWays % 1000000007);
    }

    // Dijkstra Algorithm
    private long[] dijkstraAlgo(int V, int src, ArrayList<int[]>[] adj){
        long[] shortestPath = new long[V];
        Arrays.fill(shortestPath, Long.MAX_VALUE);
        shortestPath[src] = 0;

        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> (int)(shortestPath[a] - shortestPath[b]));
        minHeap.add(src);

        while (!minHeap.isEmpty()){
            int node = minHeap.remove();

            for (int[] adjacent : adj[node]){
                int neighbour = adjacent[0];
                long edgeWt = adjacent[1];
                if (shortestPath[neighbour] > shortestPath[node] + edgeWt){
                    shortestPath[neighbour] = shortestPath[node] + edgeWt;
                    minHeap.add(neighbour);
                }
            }
        }
        return shortestPath;
    }

    // Return the adjacency list
    private ArrayList<int[]>[] getAdjacencyList(int V, int[][] edges){
        ArrayList<int[]>[] adj = new ArrayList[V];
        for (int u = 0; u < V; u++)
            adj[u] = new ArrayList<>();
        for (int[] edge : edges){
            adj[edge[0]].add(new int[]{edge[1], edge[2]});
            adj[edge[1]].add(new int[]{edge[0], edge[2]});
        }
        return adj;
    }
}
