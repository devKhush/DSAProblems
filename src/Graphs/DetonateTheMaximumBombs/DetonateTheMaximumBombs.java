package Graphs.DetonateTheMaximumBombs;
import java.util.ArrayList;

// https://youtu.be/WPU7JlvUud8
// https://leetcode.com/problems/detonate-the-maximum-bombs/
// https://leetcode.com/problems/detonate-the-maximum-bombs/editorial/

public class DetonateTheMaximumBombs {
    /************************************ DFS Solution ********************************************
     * Intuition: Blasting bombs
        * "How bombs can be blasted if I blast current bomb"  ==  "How bombs can be reached if I blast current bomb"
        * So, we need to find the maximum no. of nodes/bombs that can be reached starting from a given node

     * Time Complexity: O(n*n) + o(n*(n+n))
        * O(n*n) to make graph
        * O(n*(n+n)) for DFS traversal for n nodes (V+E) ~ O(n+n)
     * Space Complexity: O(n*n) + O(n)
        * In worst case, size of adjacency list will be n*n (when all bombs can be blasted from any starting bomb)
        * O(n) for visited array
     */
    public int maximumDetonation(int[][] bombs) {
        int n = bombs.length;

        // Generate an Adjacency List of Graph nodes (no. of nodes = no. of bombs)
        ArrayList<Integer>[] adjList = new ArrayList[n];
        for (int i = 0; i < n; i++){
            adjList[i] = new ArrayList<>();
            for (int j = 0; j < n; j++){
                if (i == j) continue;
                double c1_x = bombs[i][0];
                double c1_y = bombs[i][1];
                double c2_x = bombs[j][0];
                double c2_y = bombs[j][1];
                double radius = bombs[i][2];
                if (Math.pow(c1_x - c2_x, 2) + Math.pow(c1_y - c2_y, 2) <= radius*radius)
                    adjList[i].add(j);
            }
        }

        int maxNodesBlasted = 0;
        for (int node = 0; node < n; node++){
            // Here we need to have a separate visited array for each node, bcoz we need to count the
            // no. of maximum visited nodes/bombs starting from each node/bomb
            boolean[] visited = new boolean[n];

            // No, of visited nodes starting from current 'node'
            int nodesVisited = dfs(node, adjList, visited);
            maxNodesBlasted = Math.max(maxNodesBlasted, nodesVisited);
        }
        return maxNodesBlasted;
    }

    // DFS Traversal to count the no. of visited nodes from the starting node
    private int dfs(int node, ArrayList<Integer>[] adjList, boolean[] visited){
        visited[node] = true;

        int count = 1;
        for (int neighbour : adjList[node]){
            if (!visited[neighbour])
                count += dfs(neighbour, adjList, visited);
        }
        return count;
    }
}
