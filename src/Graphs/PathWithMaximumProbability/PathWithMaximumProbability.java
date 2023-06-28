package Graphs.PathWithMaximumProbability;
import java.util.*;

// https://leetcode.com/problems/path-with-maximum-probability/description/
// https://leetcode.com/problems/path-with-maximum-probability/editorial

public class PathWithMaximumProbability {
    /*********************************** Efficient Dijkstra algorithm ************************************
     * Intuition: Dijkstra algorithm
        * In normal Dijkstra algo, whenever we pop a node from minHeap, it had the shortest path from source
        * Similarly, here when we take out a node from maxHeap, it will have max. probability from source
     * Time Complexity: O(E*log(E))
        * Modification of Dijkstra algorithm
     * Space Complexity: O(V+E) + O(V) + O(E)
        * Adjacency list (V+E)
        * Maximum Probability array (V)
        * MaxHeap (E)
     */
    public double maxProbability_dijkstra(int n, int[][] edges, double[] succProb, int start, int end) {
        // Adjacency list
        ArrayList<Node>[] adj = adjacencyList(n, edges, succProb);

        // Shortest/Maximum probability path array
        double[] maxProbability = new double[n];
        maxProbability[start] = 1;

        // Max Heap to find maximum path from src node.
        PriorityQueue<Pair<Integer, Double>> maxHeap = new PriorityQueue<>((a, b) -> Double.compare(b.second, a.second));
        maxHeap.add(new Pair<Integer, Double>(start, 1.0));

        while (!maxHeap.isEmpty()){
            Pair<Integer, Double> pair = maxHeap.remove();
            if (pair.first == end)
                return pair.second;

            for (Node neighbour : adj[pair.first]){
                if (maxProbability[neighbour.node] < neighbour.edgeProb * maxProbability[pair.first]){
                    maxProbability[neighbour.node] = neighbour.edgeProb * maxProbability[pair.first];
                    maxHeap.add(new Pair<Integer, Double>(neighbour.node, maxProbability[neighbour.node]));
                }
            }
        }
        return 0;
    }


    /*************************************** Brute DFS Solution **************************************
     * Intuition:
        * Straight forward DFS traversal
     * Time Complexity: O(E*E)
        * DFS will try out all the possible paths from source to end & return the one with max probability.
     * Space Complexity: O(V+E) + O(2*V)
        * Adjacency list (V+E)
        * Stack space and visited array
     */
    public double maxProbability_dfs(int n, int[][] edges, double[] succProb, int start, int end) {
         ArrayList<Node>[] adj = adjacencyList(n, edges, succProb);
         boolean[] visited = new boolean[n];

         return dfs(start, end, adj, visited);
    }
    private double dfs(int u, int end, ArrayList<Node>[] adj, boolean[] visited){
        if (u == end)
            return 1;

        visited[u] = true;
        double probability = 0;
        for (Node neighbour : adj[u]){
            if (!visited[neighbour.node]){
                probability = Math.max(probability, neighbour.edgeProb * dfs(neighbour.node, end, adj, visited));
            }
        }
        visited[u] = false;
        return probability;
    }


    // **************************** Return Adjacency list ****************************
    private ArrayList<Node>[] adjacencyList(int V, int[][] edges, double[] wts){
        ArrayList<Node>[] adj = new ArrayList[V];
        for (int i = 0; i < V; i++){
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < edges.length; i++){
            adj[edges[i][0]].add(new Node(edges[i][1], wts[i]));
            adj[edges[i][1]].add(new Node(edges[i][0], wts[i]));
        }
        return adj;
    }

    // Graph node to store "neighbour node" and "Edge probability cost"
    static class Node{
        int node;
        double edgeProb;
        public Node(int node, double edgeProb){
            this.node = node;
            this.edgeProb = edgeProb;
        }
    }
    static class Pair<A, B>{
        A first;
        B second;
        public Pair(A a, B b){
            first = a;
            second = b;
        }
    }
}
