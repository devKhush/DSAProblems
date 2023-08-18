package Graphs.MaximalNetworkRank;
import java.util.HashSet;

// https://leetcode.com/problems/maximal-network-rank/description/

public class MaximalNetworkRank {
    /*********************************** Efficient Solution 1 *************************************
     * Intuition: Hashing
     * Time Complexity: O(n*n)
     * Space Complexity: O(n*n)
     */
    public int maximalNetworkRank_(int n, int[][] roads) {
        HashSet<Integer>[] adjList = new HashSet[n];
        for (int i = 0; i < n; i++){
            adjList[i] = new HashSet<>();
        }
        for (int[] edge : roads){
            adjList[edge[0]].add(edge[1]);
            adjList[edge[1]].add(edge[0]);
        }
        int maxRank = 0;
        for (int u = 0; u < n; u++){
            for (int v = u + 1; v < n; v++){
                int connections = adjList[u].size() + adjList[v].size() + (adjList[u].contains(v) ? -1 : 0);
                maxRank = Math.max(maxRank, connections);
            }
        }
        return maxRank;
    }

    /*********************************** Efficient Solution 2 *************************************
     * Intuition: Hashing
     * Time Complexity: O(n*n)
     * Space Complexity: O(n*n)
     */
    public int maximalNetworkRank(int n, int[][] roads) {
        int[] inDegree = new int[n];
        boolean[][] directlyConnected = new boolean[n][n];
        for (int[] edge : roads){
            inDegree[edge[0]]++;
            inDegree[edge[1]]++;
            directlyConnected[edge[0]][edge[1]] = true;
            directlyConnected[edge[1]][edge[0]] = true;
        }
        int maxRank = 0;
        for (int u = 0; u < n; u++){
            for (int v = u + 1; v < n; v++){
                int connections = inDegree[u] + inDegree[v] + (directlyConnected[u][v] ? -1 : 0);
                maxRank = Math.max(maxRank, connections);
            }
        }
        return maxRank;
    }
}
