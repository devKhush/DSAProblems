package Graphs.PrintShortestPaths;
import java.util.*;

// PRE_REQUISITE: "Word Ladder II"
// Similar approach as Word Ladder problem has been used.
/*
* Both the solutions have Same Time Complexity and Space Complexity
 */

// To print all the shortest path from a given source to given destination,
// below BFS + DFS method has to be followed (same as My Solution):
// https://www.geeksforgeeks.org/print-all-shortest-paths-between-given-source-and-destination-in-an-undirected-graph/

public class PrintShortestPaths {
    /************************************* BFS + DFS Solution  **************************************
     * Time Complexity: O(E * log(E)) + O(V)
        * O(E * log(E))  to find the shortest path from source to all vertices by Dijkstra Algo
        * O(V) to find the path from source to destination
     * Space Complexity:  O(V) + O(E)
        * O(V) for Shortest_path_array and Recursion_stack_space_in_DFS
        * O(E) for MinHeap in Dijkstra algo
     */
    public static List<Integer> shortestPath(int V, int E, ArrayList<ArrayList<int[]>> adj) {
        ArrayList<Integer> path = new ArrayList<>();

        // Shortest Path by Dijkstra algorithm
        int src = 1, dest = V;
        int[] shortestPaths = dijkstraAlgorithm(V, adj, src);

        // Instead of going from "source to destination", go from "destination to source" to avoid
        // all the redundant paths in DFS traversal.
        if (shortestPaths[dest] != Integer.MAX_VALUE)
            dfs(dest, src, shortestPaths, adj, path);
        else
            path.add(-1);
        return path;
    }

    // Same Technique used in Word Ladder-II Problem
    private static boolean dfs(int node, int source, int[] shortestPath,
                               ArrayList<ArrayList<int[]>> adj, ArrayList<Integer> path) {
        path.add(node);
        if (node == source) {
            Collections.reverse(path);
            return true;
        }

        for (int[] adjacent : adj.get(node)) {
            int neighbour = adjacent[0];
            int edgeWt = adjacent[1];

            if (edgeWt == shortestPath[node] - shortestPath[neighbour]){
                if (dfs(neighbour, source, shortestPath, adj, path))
                    return true;
            }
        }
        path.remove(path.size() - 1);
        return false;
    }

    // Plain Dijkstra Algorithm
    private static int[] dijkstraAlgorithm(int V, ArrayList<ArrayList<int[]>> adj, int src) {
        int[] shortestPaths = new int[V + 1];
        Arrays.fill(shortestPaths, Integer.MAX_VALUE);
        shortestPaths[src] = 0;

        PriorityQueue<Integer> minheap = new PriorityQueue<>((a, b) -> shortestPaths[a] - shortestPaths[b]);
        minheap.add(src);

        while (!minheap.isEmpty()) {
            int node = minheap.remove();

            for (int[] adjacent : adj.get(node)) {
                int neighbour = adjacent[0];
                int edgeWt = adjacent[1];
                if (shortestPaths[neighbour] >= shortestPaths[node] + edgeWt) {
                    shortestPaths[neighbour] = shortestPaths[node] + edgeWt;
                    minheap.add(neighbour);
                }
            }
        }
        return shortestPaths;
    }
}
