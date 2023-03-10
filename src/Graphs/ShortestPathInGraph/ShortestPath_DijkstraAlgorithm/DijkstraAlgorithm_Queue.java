package Graphs.ShortestPathInGraph.ShortestPath_DijkstraAlgorithm;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

// UN-EFFICIENT SOLUTION USING QUEUE, BFS & DFS

public class DijkstraAlgorithm_Queue {
    /* ************************************* BFS Solution *****************************************
    * Solution is same as the "Shortest path in Undirected Graphs with Unit Weights" except that past cost is
      taken into consideration.
    * NOTE: In Queue Solution, while doing BFS there will be lots of unnecessary computations
    * Approach: Instead of visited array, we take a Distance array to store the shortest path
      from the source to any node. This distance array is initialized with infinity, for Disconnected
      components n the graphs, the shortest path remains infinity only.

    * The Intuition is to use the BFS algorithm.
    * Actual time complexity is multiple times of O(V+ E), as there will be unnecessary iterations
    * Time Complexity : O(n * (V + E))                Same as BFS for Graph with adjacency list
        * n can vary as there will be multiple repetitions
    * Space Complexity: O(2 * V) = O(V)         Same as BFS for Graph with adjacency list
    */
    public int[] shortestPathsInDAG_BFS(int V, ArrayList<ArrayList<int[]>> adjList, int source){
        // Array to store "Shortest path from source to the nodes"
        int[] shortestPath = new int[V];
        Arrays.fill(shortestPath, Integer.MAX_VALUE);
        shortestPath[source] = 0;

        Queue<Integer> bfsQueue = new ArrayDeque<>();
        bfsQueue.add(source);

        while (!bfsQueue.isEmpty()){
            int node = bfsQueue.remove();

            // Traverse every adjacent vertex of current vertex
            for (int[] adjacent : adjList.get(node)){
                int neighbour = adjacent[0];
                int pathCost = adjacent[1];

                // If the "Distance from node plus path cost to adjacent vertex" is smaller than
                // "Distance from source to neighbour". Then we found a shorter path to the
                // 'neighbour' from the source, add adjacent vertex to the queue (as in BFS)
                if (shortestPath[node] + pathCost < shortestPath[neighbour]){
                    shortestPath[neighbour] = shortestPath[node] + pathCost;
                    bfsQueue.add(neighbour);
                }
            }
        }
        return shortestPath;
    }
}
