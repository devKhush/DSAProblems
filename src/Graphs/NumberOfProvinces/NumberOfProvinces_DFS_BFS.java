package Graphs.NumberOfProvinces;
import java.util.ArrayDeque;
import java.util.Queue;

// https://leetcode.com/problems/number-of-provinces/description/

public class NumberOfProvinces_DFS_BFS {
    /************************************* DFS Solution ********************************************
     * Time Complexity: O(V*V)
        * Adjacency matrix solution
     * Space Complexity: O(V)
     */
    public int numProvinces(int[][] adj) {
        int V = adj.length;
        int components = 0;
        boolean[] visited  =  new boolean[V];

        for (int node = 0; node < V; node++){
            if (!visited[node]){
                components++;
                dfs(node, V, adj, visited);
            }
        }
        return components;
    }

    private void dfs(int node, int V, int[][] adj, boolean[] visited){
        visited[node] = true;

        for (int neighbour = 0; neighbour < V; neighbour++){
            if (adj[node][neighbour] == 1 && !visited[neighbour])
                dfs(neighbour, V, adj, visited);
        }
    }


    /************************************ BFS Solution ********************************************
     * Time Complexity: O(V*V)
        * Adjacency matrix solution
     * Space Complexity: O(V)
     */
    public int numProvinces(int[][] adj, int V) {
        boolean[] visited = new boolean[V];
        int components = 0;

        for (int node = 0; node < V; node++){
            if (!visited[node]){
                components++;

                Queue<Integer> queue = new ArrayDeque<>();
                queue.add(node);
                visited[node] = true;

                while (!queue.isEmpty()){
                    int u = queue.remove();
                    for (int v = 0; v < V; v++){
                        if (adj[u][v] == 1 && !visited[v]){
                            queue.add(v);
                            visited[v] = true;
                        }
                    }
                }
            }
        }
        return components;
    }
}
