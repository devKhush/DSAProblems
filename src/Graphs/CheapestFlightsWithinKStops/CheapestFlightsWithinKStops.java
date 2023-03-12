package Graphs.CheapestFlightsWithinKStops;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

// https://youtu.be/9XybHVqTHcQ
// https://leetcode.com/problems/cheapest-flights-within-k-stops/description/
// https://takeuforward.org/data-structure/g-38-cheapest-flights-within-k-stops/

public class CheapestFlightsWithinKStops {
    /************************************** DFS + DP Solution ****************************************
     * Intuition: Explore all the paths from source to destination that has
        "atmost 'k' nodes between the src and dest" OR "atmost k+1 edges in that path" and figure out
        the path with minimum cost.

     * Time Complexity:  O(V + E) +  O(V * k)
        * O(V + E): to generate the graph
        * O(V * k): Standard DP problem's TC = number_of_states
     * Space Complexity: O(V + E) +  O(V * k) + O(V)  ~  O(V + E) +  O(V * k)
        * O(V + E): Adjacency_list
        * O(V * k): DP_Array
        * O(V): recursion_stack_space
     */
    public int findCheapestPrice(int V, int[][] flights, int src, int dest, int k){
        ArrayList<int[]>[] adj = getAdjacencyList(V, flights);

        // DP Array to store the various states, there can be multiple overlapping sub-problems
        Integer[][] dp = new Integer[V][k + 2];

        // Path from src to dest can have atmost "K+1" edges.
        int minFlightCost = dfs(src, k + 1, dest, adj, dp);
        return minFlightCost < (int)1e9 ? minFlightCost : -1;
    }

    private int dfs(int node, int edgesRemaining, int dest, ArrayList<int[]>[] adj, Integer[][] dp){
        // if we reach the dest. node, return 0 as we don't go further
        if (node == dest)
            return 0;

        // if all the stops exhausted, and we didn't reach dest node, return Infinity to exclude that path
        if (edgesRemaining <= 0)
            return (int)1e9;

        // Check for overlapping sub-problem
        if (dp[node][edgesRemaining] != null)
            return dp[node][edgesRemaining];

        // Go to every neighbour and ask for min. flight cost
        int minFlightCost = (int)1e9;
        for (int[] adjacent : adj[node]){
            int neighbour = adjacent[0];
            int edgeCost = adjacent[1];
            minFlightCost = Math.min(minFlightCost,
                    edgeCost + dfs(neighbour, edgesRemaining - 1, dest, adj, dp));
        }
        return dp[node][edgesRemaining] = minFlightCost;
    }


    /**************************************** BFS Solution ********************************************
     * Variant of "Dijkstra Algorithm" and "Shortest Path in Undirected Graph with Unit Weights"
     * Intuition: From the source, explore all the paths that has "atmost 'k' nodes between itself"
        OR "atmost k+1 edges in that path". Update the distance array accordingly in same way as Dijkstra Algo.
     * Starting from the source, reach/traverse all the nodes within "k+1" edges. And take the minimum
        cost to reach the nodes.
     * Priority will be number of stops instead of edge weights. So, we traverse the nodes level wise as
        normal BFS. Problem becomes BFS in Graphs with unit weights.

     * Time Complexity: O(V + E)
        * Instead of PQ, we are using a simple queue. So, the time complexity is O(E) instead of O(E*log(E))
     * Space Complexity: O(V + E)
        * Same as Dijkstra Algorithm
     */
    public int findCheapestPrice_bfs(int V, int[][] flights, int src, int dest, int k) {
        ArrayList<int[]>[] adj = getAdjacencyList(V, flights);

        // Minimum_Cost_Array, similar to Shortest_path_array
        int[] flightCost = new int[V];
        Arrays.fill(flightCost, Integer.MAX_VALUE);
        flightCost[src] = 0;

        // Queue for Dijkstra Algorithm
        Queue<Pair> queue = new ArrayDeque<>();
        queue.add(new Pair(src, 0, 0));

        // Shortest Path in Undirected Graph with Unit Weights
        while (!queue.isEmpty()){
            Pair node = queue.remove();

            for (int[] adjacent : adj[node.node]){
                int neighbour = adjacent[0];
                int edgeCost = adjacent[1];
                if (flightCost[neighbour] > node.cost + edgeCost){
                    if (node.stops <= k){
                        flightCost[neighbour] = node.cost + edgeCost;
                        queue.add(new Pair(neighbour, flightCost[neighbour], node.stops + 1));
                    }
                }
            }
        }
        return flightCost[dest] != Integer.MAX_VALUE ? flightCost[dest] : -1;
    }

    static class Pair{
        int node, cost, stops;
        public Pair(int node, int cost, int stops){
            this.node = node;
            this.cost = cost;
            this.stops = stops;
        }
    }

    // Return the adjacency list
    private ArrayList<int[]>[] getAdjacencyList(int V, int[][] edges){
        ArrayList<int[]>[] adj = new ArrayList[V];
        for (int u = 0; u < V; u++)
            adj[u] = new ArrayList<>();
        for (int[] edge : edges){
            adj[edge[0]].add(new int[]{edge[1], edge[2]});
        }
        return adj;
    }
}
