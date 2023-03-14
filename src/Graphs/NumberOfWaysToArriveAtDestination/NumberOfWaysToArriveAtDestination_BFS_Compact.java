package Graphs.NumberOfWaysToArriveAtDestination;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

// https://youtu.be/_-0mx0SmYxA
// https://takeuforward.org/data-structure/g-40-number-of-ways-to-arrive-at-destination/
// https://leetcode.com/problems/number-of-ways-to-arrive-at-destination/description/
// https://www.geeksforgeeks.org/count-total-ways-to-reach-destination-from-source-in-an-undirected-graph/

public class NumberOfWaysToArriveAtDestination_BFS_Compact {
    /*********************************** Compact & Efficient BFS Solution *****************************
     * Intuition: See Video
        * Number of ways to reach any node 'A', is the sum of all the ways to reach all the nodes
            connected to node 'A'
     * Time Complexity: O(E * log(E))
        * Same as Dijkstra Algorithm
     * Space Complexity: O(V + E)
        * Same as Dijkstra Algorithm
     */
    public int countPaths(int V, int[][] roads) {
        // Adjacency list
        ArrayList<int[]>[] adj = getAdjacencyList(V, roads);
        final int MOD = (int) (1e9 + 7);

        // Shortest path array
        int[] shortestPath = new int[V];
        Arrays.fill(shortestPath, Integer.MAX_VALUE);
        shortestPath[0] = 0;

        // Arrays to store all the number of ways in which nodes can be reached
        int[] ways = new int[V];
        ways[0] = 1;

        // MinHeap
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        minHeap.add(new int[]{0, 0});

        while (!minHeap.isEmpty()){
            int node = minHeap.peek()[0];
            int dist = minHeap.remove()[1];

            for (int[] adjacent : adj[node]){
                int neighbour = adjacent[0];
                int edgeWt = adjacent[1];

                if (shortestPath[neighbour] > dist + edgeWt){
                    shortestPath[neighbour] = dist + edgeWt;
                    minHeap.add(new int[]{neighbour, shortestPath[neighbour]});
                    // first time visiting the node with the shortest distance
                    ways[neighbour] = ways[node];
                }
                // it's not the first time we're visiting this node with the shortest distance
                // increment the total ways to reach this node by the count of current ways
                else if (shortestPath[neighbour] == dist + edgeWt){
                    ways[neighbour] = (ways[neighbour] + ways[node]) % MOD;
                }
            }
        }
        return ways[V - 1] % MOD;
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
