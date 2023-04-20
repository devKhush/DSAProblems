//{ Driver Code Starts
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
    // such that no two adjacent vertices of graph are coloured with same colour.
    public boolean graphColoring(boolean graph[][], int m, int n) {
        int[] colors = new int[n];
        return canColor(0, graph, n, m, colors);
    }

    private boolean canColor(int node, boolean[][] graph, int n, int m, int[] colors) {
        if (node == n)
            return true;

        for (int color = 1; color <= m; color++) {
            if (canBeColored(node, graph, n, color, colors)) {
                colors[node] = color;
                if (canColor(node + 1, graph, n, m, colors))
                    return true;
                colors[node] = 0;
            }
        }
        return false;
    }

    private boolean canBeColored(int node, boolean[][] graph, int n, int color, int[] colors){
        for (int neighbour = 0; neighbour < n; neighbour++){
            if (graph[node][neighbour]  && colors[neighbour] == color)
                return false;
        }
        return true;
    }
}