package Graphs.MinimumScoreOfPathBetweenTwoCities;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

// https://leetcode.com/problems/minimum-score-of-a-path-between-two-cities/description/

public class MinimumScoreOfPathBetweenTwoCities {
    /***********************************  DFS Solution ********************************************
     * Intuition: Since we can visit a travelled path again, so we can always traverse path having minimum
            edge and come back to "node 1" again. Then, afterwards we can visit the "node n".
        * So, we just need to find min. edge cost present in the graph.
        * Simple DFS Traversal to find out the edge with min cost in the graph

     *  Time Complexity: O(V + E)
        * Graph Generation + DFS traversal
     *  Space Complexity: O(V + E)
        * Adjacency List Space
     */
    public int minScore_DFS(int n, int[][] roads) {
        ArrayList<int[]>[] adj = adjacencyList(n, roads);
        boolean[] visited = new boolean[n + 1];

        int minEdge =  dfs(1, adj, visited);
        return visited[n] ? minEdge : Integer.MAX_VALUE;
    }

    private int dfs(int node, ArrayList<int[]>[] adj, boolean[] visited){
        visited[node] = true;
        int minEdge = Integer.MAX_VALUE;

        for (int[] edge : adj[node]){
            int neighbour = edge[0];
            int cost = edge[1];
            minEdge = Math.min(minEdge, cost);
            if (!visited[neighbour])
                minEdge = Math.min(minEdge, dfs(neighbour, adj, visited));
        }
        return minEdge;
    }


    /***********************************  BFS Solution ********************************************
     * Intuition: Since we can visit a travelled path again, so we can always traverse path having minimum
        edge and come back to "node 1" again. Then, afterwards we can visit the "node n".
        * So, we just need to find min. edge cost present in the graph.
        * Simple DFS Traversal to find out the edge with min cost in the graph

     *  Time Complexity: O(V + E)
        * Graph Generation + DFS traversal
     *  Space Complexity: O(V + E)
        * Adjacency List Space
     */
    public int minScore(int n, int[][] roads) {
        ArrayList<int[]>[] adj = adjacencyList(n, roads);
        boolean[] visited = new boolean[n + 1];

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(1);
        visited[1] = true;
        int minEdge = Integer.MAX_VALUE;

        while (!queue.isEmpty()){
            int node = queue.remove();

            for (int[] adjacent : adj[node]){
                int neighbour = adjacent[0];
                int edge = adjacent[1];
                minEdge = Math.min(minEdge, edge);
                if (!visited[neighbour]) {
                    queue.add(neighbour);
                    visited[neighbour] = true;
                }
            }
        }
        return visited[n] ? minEdge : Integer.MAX_VALUE;
    }


    private ArrayList<int[]>[] adjacencyList(int V, int[][] roads){
        ArrayList<int[]>[] adj = new ArrayList[V + 1];
        for (int u = 0; u <= V; u++){
            adj[u] = new ArrayList<>();
        }
        for (int[] edge : roads){
            adj[edge[0]].add(new int[]{edge[1], edge[2]});
            adj[edge[1]].add(new int[]{edge[0], edge[2]});
        }
        return adj;
    }
}
