// { Driver Code Starts
import java.util.*;
import java.lang.*;
import java.io.*;

class GFG {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int tc = scan.nextInt();

        while (tc-- > 0) {
            int N = scan.nextInt();
            int M = scan.nextInt();
            int E = scan.nextInt();

            boolean graph[][] = new boolean[N][N];

            for (int i = 0; i < E; i++) {
                int u = scan.nextInt() - 1;
                int v = scan.nextInt() - 1;
                graph[u][v] = true;
                graph[v][u] = true;
            }

            System.out.println(new solve().graphColoring(graph, M, N) ? 1 : 0);
        }
    }
}
// } Driver Code Ends


class solve {
    // Function to determine if graph can be coloured with at most M colours
    // such
    // that no two adjacent vertices of graph are coloured with same colour.
    public boolean graphColoring(boolean[][] adjacencyMatrixGraph, int m, int n) {
        int[] nodeColorMap = new int[n];
        
        return canColorGraph(0, n, nodeColorMap, m, adjacencyMatrixGraph);
    }

    public boolean canColorGraph(int node, int n, int[] nodeColorMap, int m, boolean[][] adjacencyMatrix){
        if (node == n)
            return true;

        for (int color = 1; color <= m; color++){
            if (canColorNodeWithGivenColor(nodeColorMap, color, node, n, adjacencyMatrix)){
                nodeColorMap[node] = color;

                if (canColorGraph(node + 1, n, nodeColorMap, m, adjacencyMatrix))
                    return true;

                nodeColorMap[node] = 0;
            }
        }
        return false;
    }

    private boolean canColorNodeWithGivenColor(int[] nodeColorMap, int color, int node, int n, boolean[][] adjacencyMatrix){
        for (int neighbour = 0; neighbour < n; neighbour++){
            boolean isNeighbour = adjacencyMatrix[node][neighbour]; 
            if (isNeighbour &&  nodeColorMap[neighbour] == color)
                return false;
        }
        return true;
    }
}