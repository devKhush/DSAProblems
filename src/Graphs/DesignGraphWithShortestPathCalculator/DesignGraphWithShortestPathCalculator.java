package Graphs.DesignGraphWithShortestPathCalculator;
import java.util.Arrays;

// https://leetcode.com/problems/design-graph-with-shortest-path-calculator/

public class DesignGraphWithShortestPathCalculator {
    int[][] minCost;
    int V;
    int[][] edges;

    public DesignGraphWithShortestPathCalculator(int n, int[][] edges) {
        this.minCost = new int[n][n];
        this.V = n;
        this.edges = edges;

        // Run Floyd Warshall Algorithm
        for (int[] row : minCost) {
            Arrays.fill(row, (int) 1e9);
        }
        for (int[] edge : edges) {
            minCost[edge[0]][edge[1]] = edge[2];
        }
        for (int u = 0; u < V; u++) {
            minCost[u][u] = 0;
        }
        for (int k = 0; k < V; k++) {
            for (int u = 0; u < V; u++) {
                for (int v = 0; v < V; v++) {
                    minCost[u][v] = Math.min(minCost[u][v], minCost[u][k] + minCost[k][v]);
                }
            }
        }
    }


    // Relax the edges connecting to these two edges using Floyd Warshall algorithm
    public void addEdge(int[] edge) {
        if (minCost[edge[0]][edge[1]] < edge[2])
            return;
        minCost[edge[0]][edge[1]] = edge[2];

        for (int u = 0; u < V; u++) {
            for (int v = 0; v < V; v++) {
                minCost[u][v] = Math.min(minCost[u][v], minCost[u][edge[0]] + minCost[edge[0]][v]);
            }
        }
        for (int u = 0; u < V; u++) {
            for (int v = 0; v < V; v++) {
                minCost[u][v] = Math.min(minCost[u][v], minCost[u][edge[1]] + minCost[edge[1]][v]);
            }
        }
    }


    public int shortestPath(int node1, int node2) {
        return minCost[node1][node2] != (int)1e9 ? minCost[node1][node2] : -1;
    }
}
