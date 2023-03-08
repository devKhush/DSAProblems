package Graphs.ShortestPathInGraph.ShortestPath_GraphWithUnitWeights;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

// https://youtu.be/hwCWi7-bRfI
// https://youtu.be/C4gxoTaI71U (new)
// https://takeuforward.org/data-structure/shortest-path-in-undirected-graph-with-unit-distance-g-28/
// https://practice.geeksforgeeks.org/problems/shortest-path-in-undirected-graph-having-unit-distance/1

public class ShortestPathInUndirectedGraphWithUnitWeights {
    /* ********************************** Efficient BFS Solution ***********************************
     * For Unit Weighted edges, BFS Solution is much efficient & faster than DFS Solution
       This is because, BFS traverse all the vertices in breadth wise manner. So, starting from source
       vertex, BFS assigns the shortest distance breadth wise.
     * Approach: Instead of visited array, we take a Distance array to store the shortest path
       from the source to any node. This distance array is initialized with infinity, for Disconnected
       components n the graphs, the shortest path remains infinity only.
     * The Intuition is to use the BFS algorithm.

     * Updating the distance of the neighboring nodes is more appropriate for weighted graphs.
     * For unweighted graphs, the first time when we reach the target node, that should be the
       shortest distance, because BFS goes level wise.

     * Time Complexity : O(V + 2E)         Same as BFS for Graph with adjacency list
     * Space Complexity: O(V)              BFS Queue
        * Here, we eliminated the need of visited array
     */
    public int[] shortestPathToEveryNode_BFS(int V, ArrayList<ArrayList<Integer>> adjList, int source){
        Queue<Integer> bfsQueue = new ArrayDeque<>();
        bfsQueue.add(source);

        // Array to store "Shortest path from source to the nodes", initialized with Infinity
        int[] shortestPath = new int[V];
        Arrays.fill(shortestPath, Integer.MAX_VALUE);
        shortestPath[source] = 0;

        while (!bfsQueue.isEmpty()){
            int node = bfsQueue.remove();

            // Traverse every adjacent vertex of current vertex
            for (int neighbour : adjList.get(node)){
                // If the "Distance from source to 'node' plus 1" is smaller than "Distance from source to neighbour"
                // Then we found a shorter path to the 'neighbour' from the source, add adjacent vertex to the queue (as in BFS)
                if (shortestPath[node] + 1 < shortestPath[neighbour]){
                    shortestPath[neighbour] = shortestPath[node] + 1;
                    bfsQueue.add(neighbour);
                }
            }
        }
        return shortestPath;
    }


    /****************************** Another Efficient BFS Solution **************************************
     * Intuition: BFS follows level/distance wise traversal, neighbour nodes with equal distance
            will be reached at the same time.
     * Time Complexity:   O(V + 2E)
        * Standard BFS Time
     * Space Complexity:  O(V) + O(V)  ~  O(V)
     */
    public int[] shortestPath(int V, ArrayList<Integer>[] adj, int src) {
        // Distance array
        int[] shortestPath = new int[V];
        Arrays.fill(shortestPath, -1);
        shortestPath[src] = 0;

        // Visited array
        boolean[] visited = new boolean[V];

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(src);
        visited[src] = true;
        int distance = 1;

        while (!queue.isEmpty()){
            int size = queue.size();
            while (size-- > 0){
                int node = queue.remove();

                for (int neighbour : adj[node]){
                    if (!visited[neighbour]){
                        shortestPath[neighbour] = distance;
                        visited[neighbour] = true;
                        queue.add(neighbour);
                    }
                }
            }
            distance++;
        }
        return shortestPath;
    }
}
