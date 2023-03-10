package Graphs.PrintShortestPaths;
import java.util.*;

// https://youtu.be/rp1SMw7HSO8
// https://takeuforward.org/data-structure/g-35-print-shortest-path-dijkstras-algorithm/
// Have a look:
// https://www.geeksforgeeks.org/printing-paths-dijkstras-shortest-path-algorithm/
// https://practice.geeksforgeeks.org/problems/shortest-path-in-weighted-undirected-graph/1

public class PrintShortestPaths_Compact {
    /************************************* BFS Solution **************************************
     * Time Complexity: O(E * log(E)) + O(V)
        * O(E * log(E)) for Dijkstra algorithm.
        * O(V) for tracing the path
     * Space Complexity: O(V + E)
        * O(V) for Shortest_path_array and Parent_array
        * O(E) for MinHeap PQ
     */
    public static List<Integer> shortestPath(int V, int E, ArrayList<ArrayList<int[]>> adj) {
        int src = 1, dest = V;

        // Shortest path array
        int[] shortestPath = new int[V + 1];
        Arrays.fill(shortestPath, Integer.MAX_VALUE);
        shortestPath[src] = 0;

        // Parent array
        int[] parent = new int[V + 1];
        Arrays.fill(parent, -1);

        // Minheap for Dijkstra algo
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> shortestPath[a] - shortestPath[b]);
        minHeap.add(src);

        while (!minHeap.isEmpty()){
            int node = minHeap.remove();

            for (int[] adjacent : adj.get(node)){
                int neighbour = adjacent[0];
                int edgeWt = adjacent[1];
                if (shortestPath[neighbour] > shortestPath[node] + edgeWt){
                    shortestPath[neighbour] = shortestPath[node] + edgeWt;
                    parent[neighbour] = node;
                    minHeap.add(neighbour);
                }
            }
        }

        ArrayList<Integer> path = new ArrayList<>();
        if (shortestPath[dest] == Integer.MAX_VALUE){
            path.add(-1);
            return path;
        }
        path.add(dest);
        int ptr = dest;
        while (parent[ptr] != -1) {
            path.add(parent[ptr]);
            ptr = parent[ptr];
        }
        Collections.reverse(path);    // Since the path stored is in a reverse order, we reverse the list
        return path;
    }
}
